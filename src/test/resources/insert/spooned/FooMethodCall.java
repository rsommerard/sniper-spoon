// default package (CtPackage.TOP_LEVEL_PACKAGE_NAME in Spoon= unnamed package)



public class FooMethodCall {
    public boolean bar(int a, final boolean b) {
        System.out.println("ok");
        if (b) {
            System.out.println("Cool!");
            return false;
        }
        return true;
    }
}
