
import org.testng.annotations.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import static vedportal.CompareFiles.*;
import static vedportal.archive.ZipArchive.*;
import static vedportal.chronology.ChronologyFiles.*;
import static vedportal.documents.DocumentsFiles.*;


public class VEDTest {

    //@Test

    //public void shouldDownloadArchiveTest() throws IOException {

        //String paramOfUrl = "X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=V4z29OOjnDiQHo3vtUFi%2F20210429%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210429T111342Z&X-Amz-Expires=43200&X-Amz-Signature=20c2a7bfa256cab67d219d959b1f92a9d4ac2b61e0382188da44adbf248c0b13&X-Amz-SignedHeaders=host";
        //zipArchiveDownload(parseURL(paramOfUrl), "25065238");


    //}

    @Test

    public void shouldParseUrlTest() throws IOException {

        parseURL("X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=V4z29OOjnDiQHo3vtUFi%2F20210429%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210429T111342Z&X-Amz-Expires=43200&X-Amz-Signature=20c2a7bfa256cab67d219d959b1f92a9d4ac2b61e0382188da44adbf248c0b13&X-Amz-SignedHeaders=host");


    }
    //1.скачивание архива
    @Test

    public void shouldDownloadArchiveTest() throws IOException {

        zipArchiveDownload3();

    }

    //2.Разворачиваем архив
    @Test

    public void shouldOpenZipArchiveAndSaveFileTest() throws IOException {

        openZipArchiveAndSaveFile();


    }

    //1.Парсим JSON Documents и получаем URL файлов
    @Test

    public void shouldParseJsonDocumentsTest() throws IOException {

        parseJsonDocumentsAndGetUrl();
    }

    //2.Парсим JSON Documents и получаем Name файлов
    @Test

    public void shouldParseJsonDocumentsAndGetNamesTest() throws IOException {

        parseJsonDocumentsAndGetNames();
    }


    //3.Скачиваем файлы из Documents
    @Test

    public void shouldDownloadFilesDocumentsTest() throws IOException {

        downloadFilesDocuments(parseJsonDocumentsAndGetUrl(),parseJsonDocumentsAndGetNames());
    }

    //1.Парсим JSON Chronology и получаем url файлов
    @Test

    public void shouldParseJsonChronologyAndGetUrlTest() throws IOException {

        parseJsonChronologyAndGetUrl();
    }

    //2.Парсим JSON Chronology и получаем date файлов
    @Test

    public void shouldParseJsonChronologyAndGetDateTest() throws IOException {

        parseJsonChronologyAndGetDate();
    }

    //3.Парсим JSON Chronology и получаем name файлов
    @Test

    public void shouldParseJsonChronologyAndGetNameTest() throws IOException {

        parseJsonChronologyAndGetName();
    }

    //4.Получаем формат date
    @Test

    public void shouldGetDateTest() throws IOException, ParseException {

        getDate(parseJsonChronologyAndGetDate());


    }

    //5.Создаём объекты файлов и записываем в них данные из массивов
    @Test

    public void shouldCreateVedFilesTest() throws IOException, ParseException {

        createVedFileObject();



        //OC
    }
    //6.Получаем только файлы ОС
    @Test

    public void shouldGetOCFilesTest() throws IOException, ParseException {

        getOCFile();

    }

    //7.Получаем массив дат файлов ОС
    @Test

    public void shouldGetArrayOfOCFilesTest() throws IOException, ParseException {

        getArrayDatesOfOCFiles();


    }

    //8.Проверяем, что последняя дата OC самая ранняя
    @Test

    public void shouldGetLatestDateTest() throws IOException, ParseException {

        getLatestOCDate();

    }

    //9.Получаем url последнего по дате ОС
    @Test

    public void shouldGetUrlLatestOCFileFromChronologyTest() throws IOException, ParseException {

        getUrlLatestOCFileFromChronology(getLatestOCDate());

    }

    //10.Скачиваем последний по дате ОС
    @Test

    public void shouldDownloadLatestOCFileTest() throws IOException, ParseException {

        downloadLatestOCFileFromChronology(getUrlLatestOCFileFromChronology(getLatestOCDate()));

    }

    //ARF

    //11.Получаем только файлы ARF
    @Test

    public void shouldGetARFFilesTest() throws IOException, ParseException {

        getARFFile();

    }

    //12.Получаем массив дат файлов ARF
    @Test

    public void shouldGetArrayOfARFFilesTest() throws IOException, ParseException {

        getArrayDatesOfARFFiles();

    }

    //13.Проверяем, что последняя дата ARF самая ранняя
    @Test

    public void shouldGetLatestARFDateTest() throws IOException, ParseException {

        getLatestARFDate();

    }

    //14.Получаем url последнего по дате ARF
    @Test

    public void shouldGetUrlLatestARFFileTest() throws IOException, ParseException {

        getUrlLatestARFFileFromChronology(getLatestARFDate());

    }

    //15.Cкачиваем последний по дате ARF
    @Test

    public void shouldDownloadLatestARFFileTest() throws IOException, ParseException {

        downloadLatestARFFileFromChronology(getUrlLatestARFFileFromChronology(getLatestARFDate()));

    }

    //Получаем только файлы Pdf
    @Test

    public void shouldGetPdfFileTest() throws IOException, ParseException {

        getPdfFile();

    }

    //Получаем url Pdf
    @Test

    public void shouldGetUrlPdfFileTest() throws IOException, ParseException {

        getUrlPdfFileFromChronology(getPdfFile());

    }

    //Cкачиваем pdf

    @Test

    public void shouldDownloadPdfFileTest() throws IOException, ParseException {

        downloadPdfFileFromChronology(getUrlPdfFileFromChronology(getPdfFile()));

    }

    @Test

    public void shouldCompareMD5PdfFilesTest() throws IOException, NoSuchAlgorithmException {

        compareMD5PdfFiles();

    }





    @Test

    public void shouldCompareFilesTest() throws IOException {

        compareFiles();

    }



    @Test

    public void shouldDownloadFilesChronologyTest() throws IOException {

        downloadFilesChronology(parseJsonChronologyAndGetUrl());

    }


    @Test

    public void shouldGetMD5File1Test() throws IOException, NoSuchAlgorithmException {

        getMD5File1("src/main/resources/filesFromDocuments/Order Confirmation.pdf");
    }



    //6.Получаем MD5 файлов из documents
    @Test

    public void shouldGetMD5FileFromDocumentsTest() throws IOException, NoSuchAlgorithmException {

        getMD5FileFromDocuments();
    }


    // 7.Получаем MD5 файлов из папки zipFiles
    @Test

    public void shouldGetMD5FileFromZipFilesTest() throws IOException, NoSuchAlgorithmException {

        getMD5FileFromZipFiles("src/main/resources/filesFromArchive");
    }



    //8.Сравниваем MD5 файлов из documents и zipFiles
    @Test

    public void shouldCompareMD5From2FoldersTest() throws IOException, NoSuchAlgorithmException {

        compareMD5FilesFrom2Folders();
    }




    //второй вариант сравнеия файлов, по строкам
    @Test

    public void shouldCompareFilesFromArchiveAndDocuments3Test() throws IOException {

        compareFilesString();
    }



    //Получаем MD5 файла ARF последнего по дате из chronology
    @Test

    public void shouldGetMD5LatestARFTest() throws IOException, NoSuchAlgorithmException {

        getMD5LatestARFFromChronology("src/main/resources/filesFromChronology/filenameARF");

    }

    //Получаем MD5 файла ARF из documents
    @Test

    public void shouldGetMD5ARFFromDocumentsTest() throws IOException, NoSuchAlgorithmException {

        getMD5ARFFromDocuments("src/main/resources/filesFromDocuments/Atlas Russian File.xls");

    }

    //Сравниваем MD5 двух ARF
    @Test

    public void shouldCompareMD5ARFFilesTest() throws IOException, NoSuchAlgorithmException {

        compareMD5ARFFiles();

    }

    //22.Получаем MD5 файла OC последнего по дате из chronology
    @Test

    public void shouldGetMD5LatestOCTest() throws IOException, NoSuchAlgorithmException {

        getMD5LatestOCFromChronology("src/main/resources/filesFromChronology/filename");

    }

    //23.Получаем MD5 файла OC из documents
    @Test

    public void shouldGetMD5OCFromDocumentsTest() throws IOException, NoSuchAlgorithmException {

        getMD5OCFromDocuments("src/main/resources/filesFromDocuments/Order Confirmation.xls");

    }

    //24.Сравниваем MD5 двух ОС
    @Test

    public void shouldCompareMD5OCFilesTest() throws IOException, NoSuchAlgorithmException {

        compareMD5OCFiles();

    }








    @Test

    public void shouldDeleteTest() throws IOException, NoSuchAlgorithmException {

        deleteFiles("src/main/resources/filesFromArchive");

    }

    @Test

    public void shouldDeleteArchiveTest() throws IOException, NoSuchAlgorithmException {

        deleteArchive("src/main/resources/ZipArchive.zip");

    }
    @Test

    public void shouldJsonTest() throws IOException, NoSuchAlgorithmException {

        jsonChronology();

    }
    @Test

    public void shouldJsonDocumentsTest() throws IOException, NoSuchAlgorithmException {

        jsonDocuments("src/main/resources/json/jsonResponseDocuments");

    }





}



