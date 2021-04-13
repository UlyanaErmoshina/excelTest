
import org.testng.annotations.Test;

import java.io.IOException;

import static archive.createArchive.*;


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
}


