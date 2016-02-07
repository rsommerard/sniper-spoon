package processors;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtMethod;

public class SoutProcessor extends AbstractProcessor<CtMethod> {

    public void process(CtMethod ctMethod) {
        CtStatement statement = getFactory().Core().createCodeSnippetStatement().setValue("System.out.println(\"OK\")");
        ctMethod.getBody().insertBegin(statement);
    }
}
