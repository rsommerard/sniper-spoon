
public class FooMethod {

    public boolean bar(int a, final boolean b) {

        // This is a print
        if (b) {
            return false;
        }

        // TODO: Should not be deleted after process
        return true;
    }
    public int toBeInserted(boolean flag) {
        if (flag) {
            return 0;
        }
        return 1;
    }

}