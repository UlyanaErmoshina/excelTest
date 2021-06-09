package vedportal;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import vedportal.archive.ZipArchive;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import static vedportal.CompareFiles.*;
import static vedportal.archive.ZipArchive.openZipArchiveAndSaveFile;
import static vedportal.archive.ZipArchive.zipArchiveDownload3;
import static vedportal.documents.DocumentsFiles.*;


public class CompareZipArchiveAndDocuments {


    @Test
    public void shouldCompareZipArchiveAndDocumentsFilesTest() throws IOException, NoSuchAlgorithmException {

        zipArchiveDownload3();//загружаем архив
        openZipArchiveAndSaveFile();//разворачиваем архив
        downloadFilesDocuments(parseJsonDocumentsAndGetUrl(),parseJsonDocumentsAndGetNames());//скачиваем файлы из Documents
        compareMD5FilesFrom2Folders();//сравниваем MD5 файлов из documents и zipFiles

    }


    @BeforeSuite
    @AfterSuite
    void deleteArchive() throws IOException {

    ZipArchive.deleteArchive("src/main/resources/ZipArchive.zip"); //удаление архива
    ZipArchive.deleteFiles("src/main/resources/filesFromArchive"); //удаление файлов архива

    }


}
