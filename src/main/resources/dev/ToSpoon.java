
public class ToSpoon {

    public boolean foo(int a, final boolean b) {

        // This is a print

        if (b) {
            System.out.println("ko");
            return false;
        }


        // TODO: Should not be deleted after process
        System.out.println("ok");
        return true;
    }
}