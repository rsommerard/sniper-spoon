package processors;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtMethod;

import java.util.List;


public class ReturnFalseProcessor extends AbstractProcessor<CtMethod> {

    public void process(CtMethod ctMethod) {
        CtStatement lastStatement = ctMethod.getBody().getLastStatement();

        CtStatement returnFalse = getFactory().Core().createReturn().setReturnedExpression(getFactory().Core().createLiteral().setValue(false));

        lastStatement.replace(returnFalse);
        System.out.println(ctMethod);
    }

    @Override
    public boolean isToBeProcessed(CtMethod candidate) {
        return candidate.getType().getSimpleName().equals("boolean");
    }
}
