import processors.ReturnFalseProcessor;
import spoon.processing.Processor;


public class Main {

    public static void main(String[] args) throws Exception {
        Processor spoonProcessor = new ReturnFalseProcessor();
        SniperSpoon sniperSpoon = new SniperSpoon(spoonProcessor);

        String srcPath = "src/main/resources/ToSpoon.java";
        String spoonedPath = "spooned/";

        sniperSpoon.spoonSrcFile(srcPath, spoonedPath);
        sniperSpoon.compareFiles(srcPath, spoonedPath);
    }
}
