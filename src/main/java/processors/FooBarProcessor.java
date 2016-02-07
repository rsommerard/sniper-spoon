package processors;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtMethod;

public class FooBarProcessor extends AbstractProcessor<CtMethod> {
    public void process(CtMethod ctMethod) {
        ctMethod.setSimpleName("bar");
    }

    @Override
    public boolean isToBeProcessed(CtMethod candidate) {
        return candidate.getSimpleName().equals("foo");
    }
}
