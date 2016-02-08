
public class FooMethodCall {

    public boolean bar(int a, final boolean b) {

        // This is a print
        System.out.println("ok");

        if (b) {
            System.out.println("Cool!");
            return false;
        }

        // TODO: Should not be deleted after process
        return true;
    }
}