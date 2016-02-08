import com.github.gumtreediff.actions.ActionGenerator;
import com.github.gumtreediff.actions.model.*;
import com.github.gumtreediff.client.Run;
import com.github.gumtreediff.gen.Generators;
import com.github.gumtreediff.matchers.MappingStore;
import com.github.gumtreediff.matchers.Matcher;
import com.github.gumtreediff.matchers.Matchers;
import com.github.gumtreediff.tree.ITree;
import org.apache.log4j.Logger;
import spoon.Launcher;
import spoon.processing.Processor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SniperSpoon {

    private static Logger logger = Logger.getLogger(SniperSpoon.class);

    private Launcher spoonLauncher;
    private String srcPath;
    private String dstPath;

    private Matcher matcher;
    private List<Action> actions;


    public SniperSpoon(final Processor processor) {
        if (processor == null) {
            throw new IllegalArgumentException();
        }

        this.spoonLauncher = new Launcher();
        spoonLauncher.addProcessor(processor);
    }

    public SniperSpoon() {
        this.spoonLauncher = new Launcher();
    }

    public void compareFiles(final String srcPath, final String spoonedPath) throws IOException {
        this.srcPath = srcPath;
        this.dstPath = spoonedPath;

        Run.initGenerators();
        ITree src = Generators.getInstance().getTree(this.srcPath).getRoot();
        ITree dst = Generators.getInstance().getTree(this.dstPath).getRoot();
        this.matcher = Matchers.getInstance().getMatcher(src, dst);
        this.matcher.match();

        ActionGenerator generator = new ActionGenerator(src, dst, this.matcher.getMappings());
        generator.generate();

        this.actions = generator.getActions();

        if (this.actions.isEmpty()) {
            this.logger.info("No actions found.");
            return;
        }

        boolean dispatch = true;

        for(Action action : this.actions) {
            this.logger.info(action.toString());

            // Get root action
            ITree parent = action.getNode().getParent();
            if (parent != null) {
                for (Action _action : this.actions) {
                    if (_action.getNode().equals(parent)) {
                        dispatch = false;
                        break;
                    }
                }
            }

            if (dispatch) {
                this.dispatchAction(action);
            }

            dispatch = true;
        }
    }

    private void dispatchAction(Action action) throws IOException {
        if (action instanceof Update) {
            this.updateProcess(action);
        } else if (action instanceof Delete) {
            this.deleteProcess(action);
        } else if (action instanceof Insert) {
            this.insertProcess(action);
        } else if (action instanceof Move) {
            this.moveProcess(action);
        } else {
            this.logger.info("Unknown action.");
        }
    }

    private void moveProcess(Action action) throws IOException {
        ITree srcNode = action.getNode();
        MappingStore mappingStore = this.matcher.getMappings();

        // DELETE PART
        int srcStartDeletePosition = srcNode.getPos();

        ITree parent = srcNode.getParent();
        int srcEndDeletePosition = getPositionOfNextNodeInParent(srcNode, parent);

        if (srcStartDeletePosition > srcEndDeletePosition) {
            this.logger.error("Bad value for srcEndPosition (srcStartPosition > srcEndPosition).");
            return;
        }

        this.logger.info("Src start delete position: " + srcStartDeletePosition);
        this.logger.info("Src end delete position: " + srcEndDeletePosition);
        // END DELETE PART

        //INSERT PART
        ITree dstNode = mappingStore.getDst(srcNode);

        ITree parentDstNode = dstNode.getParent();
        ITree parentSrcNode = srcNode.getParent();

        List<ITree> dstDescs = parentDstNode.getDescendants();
        List<ITree> srcDescs = parentSrcNode.getDescendants();

        int indexDesc = -1;
        int nbDesc = dstDescs.size();
        for (int i = 0; i < nbDesc; i++) {
            if (dstDescs.get(i).equals(dstNode)) {
                indexDesc = i;
                break;
            }
        }

        if (indexDesc == -1) {
            this.logger.error("No descendant found.");
            return;
        }

        for (int i = indexDesc + 1; i < nbDesc; i++) {
            if (dstDescs.get(i).getParent().equals(dstNode.getParent())) {
                indexDesc = i;
                break;
            }
        }

        this.logger.info("Descendant index: " + indexDesc);

        if (srcDescs.size() < indexDesc) {
            this.logger.error("No descendant source correpondance.");
            return;
        }

        ITree nodeToTake = dstDescs.get(indexDesc);

        ITree nodeInSrc = mappingStore.getSrc(nodeToTake);

        while (nodeInSrc.getParent() != parentSrcNode) {
            nodeInSrc = nodeInSrc.getParent();
        }

        int srcStartInsertPosition = nodeInSrc.getPos();

        this.logger.info("Src start insert position: " + srcStartInsertPosition);
        //END INSERT PART

        String srcContent = this.getSrcContent();
        String dstContent = this.getDstContent();

        int dstStartPosition = dstNode.getPos();
        int dstEndPosition = dstStartPosition + dstNode.getLength();

        String newSrcContent = "";

        if (srcStartDeletePosition < srcStartInsertPosition) {
            //DELETE FIRST
            newSrcContent += srcContent.substring(0, srcStartDeletePosition);
            newSrcContent += srcContent.substring(srcEndDeletePosition, srcStartInsertPosition);

            int cursor = 0;
            while (srcContent.charAt(srcStartInsertPosition - cursor) != '\n') {
                cursor++;
            }

            // copy insertion in dst to newSrc
            newSrcContent += dstContent.substring(dstStartPosition, dstEndPosition);

            //add previous indentation
            newSrcContent += srcContent.substring(srcStartInsertPosition - cursor, srcStartInsertPosition);

            // add the rest of file
            newSrcContent += srcContent.substring(srcStartInsertPosition);
        } else {
            //INSERT FIRST
            newSrcContent += srcContent.substring(0, srcStartInsertPosition);

            int cursor = 0;
            while (srcContent.charAt(srcStartInsertPosition - cursor) != '\n') {
                cursor++;
            }

            // copy insertion in dst to newSrc
            newSrcContent += dstContent.substring(dstStartPosition, dstEndPosition);

            //add previous indentation
            newSrcContent += srcContent.substring(srcStartInsertPosition - cursor, srcStartInsertPosition);

            newSrcContent += srcContent.substring(srcStartInsertPosition, srcStartDeletePosition);

            newSrcContent += srcContent.substring(srcEndDeletePosition);
        }

        this.writeSrcContent(newSrcContent);
    }

    private void insertProcess(Action action) throws IOException {
        ITree dstNode = action.getNode();

        MappingStore mappingStore = this.matcher.getMappings();

        ITree node = action.getNode();
        ITree srcNode = mappingStore.getSrc(node);

        while(srcNode == null) {
            node = node.getParent();
            srcNode = mappingStore.getSrc(node);
        }

        if (srcNode == null) {
            this.logger.error("No parents found.");
            return;
        }

        List<ITree> dstDescs = node.getDescendants();
        List<ITree> srcDescs = srcNode.getDescendants();

        // Get node inserted
        int indexDesc = -1;
        int nbDesc = dstDescs.size();
        for (int i = 0; i < nbDesc; i++) {
            if (dstDescs.get(i).equals(dstNode)) {
                indexDesc = i;
                break;
            }
        }

        if (indexDesc == -1) {
            this.logger.error("No descendant found.");
            return;
        }

        // get node after inserted
        boolean noNextNode = false;
        for (int i = indexDesc + 1; i < nbDesc; i++) {
            if (!dstDescs.get(i).getParents().contains(dstNode)) {
                indexDesc = i;
                break;
            }

            if (i == nbDesc - 1) {
                noNextNode = true;
            }
        }

        if (noNextNode) {
            for (int i = indexDesc - 1; i >= 0; i--) {
                if (!dstDescs.get(i).getParents().contains(dstNode)) {
                    indexDesc = i;
                    break;
                }
            }

            while (indexDesc > 0 && dstDescs.get(indexDesc).getParent() != dstNode.getParent()) {
                indexDesc--;
            }
        }

        this.logger.info("Descendant index: " + indexDesc);

        if (dstDescs.size() < indexDesc) {
            this.logger.error("No descendant source correpondance.");
            return;
        }

        // get src node in descendant parent
        srcNode = mappingStore.getSrc(dstDescs.get(indexDesc));

        int srcStartPosition = srcNode.getPos();
        int dstStartPosition = dstNode.getPos();
        int dstEndPosition = dstDescs.get(indexDesc).getPos();

        String srcContent = this.getSrcContent();
        String dstContent = this.getDstContent();

        if (noNextNode) {
            int cursor = 0;
            while (dstContent.charAt(dstStartPosition - cursor) != '\n') {
                cursor++;
            }

            srcStartPosition = srcNode.getEndPos();

            dstStartPosition = dstNode.getPos() - cursor;
            dstEndPosition = dstNode.getEndPos() + 1;
        }

        String newSrcContent = srcContent.substring(0, srcStartPosition);

        newSrcContent += dstContent.substring(dstStartPosition, dstEndPosition);

        newSrcContent += srcContent.substring(srcStartPosition);

        this.writeSrcContent(newSrcContent);
    }

    private void deleteProcess(Action action) throws IOException {
        ITree srcNode = action.getNode();

        int srcStartPosition = srcNode.getPos();

        ITree parent = srcNode.getParent();
        int srcEndPosition = getPositionOfNextNodeInParent(srcNode, parent);

        if (srcStartPosition > srcEndPosition) {
            this.logger.error("Bad value for srcEndPosition (srcStartPosition > srcEndPosition).");
            return;
        }

        this.logger.info("Src start position: " + srcStartPosition);
        this.logger.info("Src end position: " + srcEndPosition);

        String srcContent = this.getSrcContent();

        String newSrcContent = srcContent.substring(0, srcStartPosition);
        newSrcContent += srcContent.substring(srcEndPosition);

        this.writeSrcContent(newSrcContent);
    }

    private int getPositionOfNextNodeInParent(ITree node, ITree parent) {
        List<ITree> descs = parent.getDescendants();

        int indexNode = -1;
        int nbDesc = descs.size();
        for (int i = 0; i < nbDesc; i++) {
            if (descs.get(i).equals(node)) {
                indexNode = i;
                break;
            }
        }

        if (indexNode == -1 && (indexNode + 1 < nbDesc)) {
            this.logger.error("Research node in parent error.");
            return indexNode;
        }

        for(int i = indexNode + 1; i < nbDesc; i++) {
            if (descs.get(i).getParent().equals(parent)) {
                return descs.get(i).getPos();
            }
        }

        return node.getPos() + node.getLength();
    }

    private void updateProcess(Action action) throws IOException {
        ITree srcNode = action.getNode();
        ITree dstNode = this.matcher.getMappings().getDst(srcNode);

        int srcStartPosition = srcNode.getPos();
        int modificationLength = srcNode.getLength();

        int srcEndPosition = srcStartPosition + modificationLength;

        this.logger.info("Src start position: " + srcStartPosition);
        this.logger.info("Src end position: " + srcEndPosition);
        this.logger.info("Modification length: " + modificationLength);

        String srcContent = this.getSrcContent();

        String newSrcContent = srcContent.substring(0, srcStartPosition);
        newSrcContent += dstNode.getLabel();
        newSrcContent += srcContent.substring(srcEndPosition);

        this.writeSrcContent(newSrcContent);
    }

    private void writeSrcContent(String newSrcContent) throws IOException {
        FileWriter writer = null;

        writer = new FileWriter(new File(this.srcPath));

        writer.write(newSrcContent);
        writer.close();
    }

    private String getSrcContent() throws IOException {
        return new String(Files.readAllBytes(Paths.get(this.srcPath)));
    }

    private String getDstContent() throws IOException {
        return new String(Files.readAllBytes(Paths.get(this.dstPath)));
    }

    public void spoonSrcFile(final String srcPath, final String spoonedPath) {
        this.spoonLauncher.run(new String[] { "-i", srcPath, "-o", spoonedPath, "--with-imports"});
    }
}
