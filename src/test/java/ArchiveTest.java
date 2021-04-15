
import archive.CreateArchive;
import org.junit.Before;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
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
        compareFilesMD5();
    }

    @BeforeSuite
    @AfterSuite


    void deleteArchive() throws IOException {
        CreateArchive.deleteArchiveAndNewFile(); //вызов метода удаления архива и файла в нем
    }

}


