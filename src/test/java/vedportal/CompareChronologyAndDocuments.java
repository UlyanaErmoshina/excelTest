package vedportal;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import vedportal.archive.ZipArchive;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import static vedportal.CompareFiles.compareMD5OCFiles;
import static vedportal.chronology.ChronologyFiles.*;
import static vedportal.documents.DocumentsFiles.*;


public class CompareChronologyAndDocuments {


    @Test
    public void shouldCompareChronologyAndDocumentsTest() throws IOException, ParseException, NoSuchAlgorithmException {

        createVedFileObject(); //Создаём объекты файлов и записываем в них данные из массивов

        //OC
        getOCFile();//Получаем только файлы ОС
        downloadLatestOCFileFromChronology(getUrlLatestOCFileFromChronology(getLatestOCDate()));//Скачиваем последний по дате ОС
        downloadFilesDocuments(parseJsonDocuments(),parseJsonDocumentsAndGetNames());//скачиваем файлы из Documents
        compareMD5OCFiles();//Сравниваем MD5 двух ОС


    }

    @BeforeSuite
    @AfterSuite
    void deleteArchive() throws IOException {

        ZipArchive.deleteFiles("src/main/resources/filesFromChronology"); //удаление файлов из папки chronology
        ZipArchive.deleteFiles("src/main/resources/filesFromDocuments"); //удаление файлов из папки documents

    }



}
