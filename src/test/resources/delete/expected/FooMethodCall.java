
public class FooMethodCall {

    public boolean bar(int a, final boolean b) {

        // This is a print
        if (b) {
            return false;
        }

        // TODO: Should not be deleted after process
        return true;
    }
}