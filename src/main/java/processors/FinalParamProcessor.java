package processors;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.declaration.ModifierKind;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FinalParamProcessor extends AbstractProcessor<CtMethod> {

    private Set<ModifierKind> finalModifier;

    public FinalParamProcessor() {
        this.finalModifier = new HashSet<ModifierKind>();
        this.finalModifier.add(ModifierKind.FINAL);
    }

    public void process(CtMethod ctMethod) {
        List<CtParameter> parameters = ctMethod.getParameters();

        for (CtParameter parameter : parameters) {
            if (parameter.hasModifier(ModifierKind.FINAL)) {
                continue;
            }

            parameter.setModifiers(this.finalModifier);
        }
    }

    @Override
    public boolean isToBeProcessed(CtMethod candidate) {
        return candidate.getParameters().size() > 0;
    }
}
