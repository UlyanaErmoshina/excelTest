
import org.testng.annotations.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static archive.CreateArchive.*;


public class ArchiveTest {

    @Test

    public void createArchiveTest() {

        createArchive();
    }

    @Test

    public void openArchiveAndSaveFileTest() {

        openArchiveAndSaveFile();
    }


    @Test

    public void compareFilesTest() throws IOException {
        compareFiles2();
    }

    @Test

    public void compareFilesTestMd5() throws IOException, NoSuchAlgorithmException {
        compareFiles();
    }


}


