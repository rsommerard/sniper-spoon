import com.google.common.io.Files;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertEquals;

public class SniperSpoonUpdateTest {

    @Test
    public void testUpdateMethodCall() throws IOException {
        String samplePath = "src/test/resources/update/sample/FooMethodCall.java";
        String srcPath = "src/test/resources/tmp/FooMethodCall.java";
        String spoonedPath = "src/test/resources/update/spooned/FooMethodCall.java";
        String expectedPath = "src/test/resources/update/expected/FooMethodCall.java";

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
    public void testUpdateField() throws IOException {
        String samplePath = "src/test/resources/update/sample/FooField.java";
        String srcPath = "src/test/resources/tmp/FooField.java";
        String spoonedPath = "src/test/resources/update/spooned/FooField.java";
        String expectedPath = "src/test/resources/update/expected/FooField.java";

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
    public void testUpdateIf() throws IOException {
        String samplePath = "src/test/resources/update/sample/FooIf.java";
        String srcPath = "src/test/resources/tmp/FooIf.java";
        String spoonedPath = "src/test/resources/update/spooned/FooIf.java";
        String expectedPath = "src/test/resources/update/expected/FooIf.java";

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
    public void testUpdateMethod() throws IOException {
        String samplePath = "src/test/resources/update/sample/FooMethod.java";
        String srcPath = "src/test/resources/tmp/FooMethod.java";
        String spoonedPath = "src/test/resources/update/spooned/FooMethod.java";
        String expectedPath = "src/test/resources/update/expected/FooMethod.java";

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
        String samplePath = "src/test/resources/update/sample/FooParam.java";
        String srcPath = "src/test/resources/tmp/FooParam.java";
        String spoonedPath = "src/test/resources/update/spooned/FooParam.java";
        String expectedPath = "src/test/resources/update/expected/FooParam.java";

        File tmp = new File(srcPath);
        Files.copy(new File(samplePath), tmp);

        SniperSpoon sniperSpoon = new SniperSpoon();
        sniperSpoon.compareFiles(srcPath, spoonedPath);

        String contentExpected = new String(java.nio.file.Files.readAllBytes(Paths.get(expectedPath)));
        String contentSniped = new String(java.nio.file.Files.readAllBytes(Paths.get(srcPath)));

        //tmp.delete();

        assertEquals("should be the same", contentExpected, contentSniped);
    }
}
