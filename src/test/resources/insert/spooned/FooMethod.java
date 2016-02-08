// default package (CtPackage.TOP_LEVEL_PACKAGE_NAME in Spoon= unnamed package)



public class FooMethod {
    public boolean bar(int a, final boolean b) {
        if (b) {
            return false;
        }
        return true;
    }
    public int toBeInserted(boolean flag) {
        if (flag) {
            return 0;
        }
        return 1;
    }
}
