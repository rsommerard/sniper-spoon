import com.google.common.io.Files;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertEquals;

public class SniperSpoonInsertTest {

    @Test
    public void testInsertMethodCall() throws IOException {
        String samplePath = "src/test/resources/insert/sample/FooMethodCall.java";
        String srcPath = "src/test/resources/tmp/FooMethodCall.java";
        String spoonedPath = "src/test/resources/insert/spooned/FooMethodCall.java";
        String expectedPath = "src/test/resources/insert/expected/FooMethodCall.java";

        File tmp = new File(srcPath);
        Files.copy(new File(samplePath), tmp);

        SniperSpoon sniperSpoon = new SniperSpoon();
        sniperSpoon.compareFiles(srcPath, spoonedPath);

        String contentExpected = new String(java.nio.file.Files.readAllBytes(Paths.get(expectedPath)));
        String contentSniped = new String(java.nio.file.Files.readAllBytes(Paths.get(srcPath)));

        tmp.delete();

        assertEquals("should be the same", contentExpected, contentSniped);
    }

    @Test
    public void testInsertField() throws IOException {
        String samplePath = "src/test/resources/insert/sample/FooField.java";
        String srcPath = "src/test/resources/tmp/FooField.java";
        String spoonedPath = "src/test/resources/insert/spooned/FooField.java";
        String expectedPath = "src/test/resources/insert/expected/FooField.java";

        File tmp = new File(srcPath);
        Files.copy(new File(samplePath), tmp);

        SniperSpoon sniperSpoon = new SniperSpoon();
        sniperSpoon.compareFiles(srcPath, spoonedPath);

        String contentExpected = new String(java.nio.file.Files.readAllBytes(Paths.get(expectedPath)));
        String contentSniped = new String(java.nio.file.Files.readAllBytes(Paths.get(srcPath)));

        tmp.delete();

        assertEquals("should be the same", contentExpected, contentSniped);
    }

    @Test
    public void testInsertIf() throws IOException {
        String samplePath = "src/test/resources/insert/sample/FooIf.java";
        String srcPath = "src/test/resources/tmp/FooIf.java";
        String spoonedPath = "src/test/resources/insert/spooned/FooIf.java";
        String expectedPath = "src/test/resources/insert/expected/FooIf.java";

        File tmp = new File(srcPath);
        Files.copy(new File(samplePath), tmp);

        SniperSpoon sniperSpoon = new SniperSpoon();
        sniperSpoon.compareFiles(srcPath, spoonedPath);

        String contentExpected = new String(java.nio.file.Files.readAllBytes(Paths.get(expectedPath)));
        String contentSniped = new String(java.nio.file.Files.readAllBytes(Paths.get(srcPath)));

        tmp.delete();

        assertEquals("should be the same", contentExpected, contentSniped);
    }

    @Test
    public void testInsertMethod() throws IOException {
        String samplePath = "src/test/resources/insert/sample/FooMethod.java";
        String srcPath = "src/test/resources/tmp/FooMethod.java";
        String spoonedPath = "src/test/resources/insert/spooned/FooMethod.java";
        String expectedPath = "src/test/resources/insert/expected/FooMethod.java";

        File tmp = new File(srcPath);
        Files.copy(new File(samplePath), tmp);

        SniperSpoon sniperSpoon = new SniperSpoon();
        sniperSpoon.compareFiles(srcPath, spoonedPath);

        String contentExpected = new String(java.nio.file.Files.readAllBytes(Paths.get(expectedPath)));
        String contentSniped = new String(java.nio.file.Files.readAllBytes(Paths.get(srcPath)));

        tmp.delete();

        assertEquals("should be the same", contentExpected, contentSniped);
    }

    @Test
    public void testInsertParam() throws IOException {
        String samplePath = "src/test/resources/insert/sample/FooParam.java";
        String srcPath = "src/test/resources/tmp/FooParam.java";
        String spoonedPath = "src/test/resources/insert/spooned/FooParam.java";
        String expectedPath = "src/test/resources/insert/expected/FooParam.java";

        File tmp = new File(srcPath);
        Files.copy(new File(samplePath), tmp);

        SniperSpoon sniperSpoon = new SniperSpoon();
        sniperSpoon.compareFiles(srcPath, spoonedPath);

        String contentExpected = new String(java.nio.file.Files.readAllBytes(Paths.get(expectedPath)));
        String contentSniped = new String(java.nio.file.Files.readAllBytes(Paths.get(srcPath)));

        tmp.delete();

        assertEquals("should be the same", contentExpected, contentSniped);
    }
}
