
public class FooMethod {

    public boolean bar(int a, final boolean b) {

        // This is a print
        System.out.println("ok");

        if (b) {
            return false;
        }

        // TODO: Should not be deleted after process
        return true;
    }

    
    // This is a comment outside method
}