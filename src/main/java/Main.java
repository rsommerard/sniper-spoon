import processors.FooBarProcessor;
import processors.ReturnFalseProcessor;
import spoon.processing.Processor;


public class Main {

    public static void main(String[] args) throws Exception {
        Processor spoonProcessor = new ReturnFalseProcessor();
        SniperSpoon sniperSpoon = new SniperSpoon(spoonProcessor);

        String path = "src/main/resources/ToSpoon.java";

        sniperSpoon.run(path);
    }
}
