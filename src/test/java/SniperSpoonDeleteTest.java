import com.google.common.io.Files;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertEquals;

public class SniperSpoonDeleteTest {

    @Test
    public void testDeleteMethodCall() throws IOException {
        String samplePath = "src/test/resources/delete/sample/FooMethodCall.java";
        String srcPath = "src/test/resources/tmp/FooMethodCall.java";
        String spoonedPath = "src/test/resources/delete/spooned/FooMethodCall.java";
        String expectedPath = "src/test/resources/delete/expected/FooMethodCall.java";

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
    public void testDeleteMethod() throws IOException {
        String samplePath = "src/test/resources/delete/sample/FooMethod.java";
        String srcPath = "src/test/resources/tmp/FooMethod.java";
        String spoonedPath = "src/test/resources/delete/spooned/FooMethod.java";
        String expectedPath = "src/test/resources/delete/expected/FooMethod.java";

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
    public void testDeleteIf() throws IOException {
        String samplePath = "src/test/resources/delete/sample/FooIf.java";
        String srcPath = "src/test/resources/tmp/FooIf.java";
        String spoonedPath = "src/test/resources/delete/spooned/FooIf.java";
        String expectedPath = "src/test/resources/delete/expected/FooIf.java";

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
    public void testDeleteParam() throws IOException {
        String samplePath = "src/test/resources/delete/sample/FooParam.java";
        String srcPath = "src/test/resources/tmp/FooParam.java";
        String spoonedPath = "src/test/resources/delete/spooned/FooParam.java";
        String expectedPath = "src/test/resources/delete/expected/FooParam.java";

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
    public void testDeleteField() throws IOException {
        String samplePath = "src/test/resources/delete/sample/FooField.java";
        String srcPath = "src/test/resources/tmp/FooField.java";
        String spoonedPath = "src/test/resources/delete/spooned/FooField.java";
        String expectedPath = "src/test/resources/delete/expected/FooField.java";

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
