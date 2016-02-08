
public class FooIf {

    public boolean bar(boolean a, final boolean b) {

        // This is a print
        System.out.println("ok");

        // TODO: Should not be deleted after process
        if (b) {
            return false;
        }

        // TODO: Should not be deleted after process
        return true;
    }
}