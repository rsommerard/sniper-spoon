// default package (CtPackage.TOP_LEVEL_PACKAGE_NAME in Spoon= unnamed package)



public class ToSpoon {
    public boolean foo(int a, final boolean b) {
        System.out.println("ok");
        if (b) {
            System.out.println("ko");
            return false;
        }
        return true;
    }
}

