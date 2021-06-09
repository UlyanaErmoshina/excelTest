package vedportal.chronology;

import com.jayway.jsonpath.JsonPath;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.fail;


public class ChronologyFiles {


    //парсинг json chronology, получение url файлов


    public static String jsonChronology() throws IOException {

        FileInputStream fs = new FileInputStream("src/main/resources/json/jsonResponseChronology");
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        String jsonString = br.readLine();
        System.out.println(jsonString);
        return jsonString;


    }


    public static List<String> parseJsonChronologyAndGetUrl() throws IOException {

        String jsonString = jsonChronology();
        List<String> urlFiles = JsonPath.read(jsonString, "$..url");
        for (String url : urlFiles) {
            System.out.println(url);
        }
        return urlFiles;

    }


    //парсинг json chronology, получение date файлов

    public static List<String> parseJsonChronologyAndGetDate() throws IOException {

        String jsonString = jsonChronology();
        List<String> dateFiles = JsonPath.read(jsonString, "$..date");
        for (String date : dateFiles) {
            System.out.println(date);
        }
        return dateFiles;

    }

    //парсинг json chronology, получение name файлов

    public static List<String> parseJsonChronologyAndGetName() throws IOException {


        String jsonString = jsonChronology();
        List<String> nameFiles = JsonPath.read(jsonString, "$..name");
        for (String name : nameFiles) {
            System.out.println(name);
        }
        return nameFiles;


    }

    //получение даты в формате Date

    public static ArrayList<Date> getDate(List<String> dateFiles) throws IOException, ParseException {

        ArrayList<Date> docDate = new ArrayList<Date>();


        for (int i = 0; i < dateFiles.size(); i++) {

            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            //Date docDate1;
            Date docDate1 = format.parse(dateFiles.get(i));
            docDate.add(docDate1);
            System.out.println(docDate);

        }

        return docDate;
    }


    //создание объектов файлов циклом
    public static ArrayList<VedFile> createVedFileObject() throws IOException, ParseException {

        ArrayList<VedFile> vedFilesArrayList = new ArrayList<VedFile>();
        for (int i = 0; i < parseJsonChronologyAndGetUrl().size(); i++) { //длина массива
            //
            vedFilesArrayList.add(new VedFile(parseJsonChronologyAndGetUrl().get(i), (getDate(parseJsonChronologyAndGetDate()).get(i)), parseJsonChronologyAndGetName().get(i)));

        }
        return vedFilesArrayList;


    }

    //проверяем, что в массивах одинаковое кол-во значений
    public static void checkArraySize() throws IOException {

        List<String> urls = parseJsonChronologyAndGetUrl();
        List<String> names = parseJsonChronologyAndGetName();
        List<String> dates = parseJsonChronologyAndGetDate();

        urls.equals(names);
        names.equals(dates);


    }

    //todo 2 метода, которые отличаются одной строчкой. подумай, как их можно объединить, чтобы не было дубля кода
    //получение ОС из всех файлов
    public static ArrayList<String> getOCFile() throws IOException, ParseException {

        ArrayList<VedFile> vedFilesArrayList = createVedFileObject();
        ArrayList<String> FilesOC = new ArrayList<>();

        for (int i = 0; i < vedFilesArrayList.size(); i++) {

            if (vedFilesArrayList.get(i).getName().contains("OC")) {
                FilesOC.add(vedFilesArrayList.get(i).getName());

            }
        }
        System.out.println(FilesOC);
        return FilesOC;

    }

    //получаем массив дат файлов OC
    public static ArrayList<Date> getArrayDatesOfOCFiles() throws IOException, ParseException {

        ArrayList<VedFile> vedFilesArrayList = createVedFileObject();
        ArrayList<Date> datesOC = new ArrayList<>();


        for (int i = 0; i < vedFilesArrayList.size(); i++) {

            if (vedFilesArrayList.get(i).getName().contains("OC")) {
                datesOC.add(vedFilesArrayList.get(i).getDate());

            }
        }
        System.out.println(datesOC);
        return datesOC;

    }


    //проверяем, что последняя дата OC самая ранняя

    public static Date getLatestOCDate() throws ParseException, IOException {

        //массив дат ОС файлов
        ArrayList<Date> datesOC = getArrayDatesOfOCFiles();

        //список рандомных дат для проверки
        ArrayList<Date> randomDates = new ArrayList<Date>();
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        randomDates.add(format.parse("2021-04-28T18:04:40.820Z"));
        randomDates.add(format.parse("2021-04-29T18:03:40.820Z"));
        //randomDates.add(format.parse("2021-04-30T18:03:40.820Z"));


        int length = datesOC.size();
        Date Date1 = datesOC.get(length - 1);

        for (int i = 0; i < length - 1; i++) {
            if (!datesOC.get(length - 1).after(datesOC.get(i))) {
                System.out.println("Последний эллемент массива по времени раньше чем предыдущие");
                Date1 = datesOC.get(length - 1);
                fail();
            }

        }
        return Date1;
    }

    //Получаем url последнего по дате ОС
    public static String getUrlLatestOCFileFromChronology(Date Date1) throws IOException, ParseException {

        ArrayList<VedFile> vedFilesArrayList = createVedFileObject();
        String urlFileOCLastDate = new String();

        for (int i = 0; i < vedFilesArrayList.size(); i++) {
            if (vedFilesArrayList.get(i).getDate().equals(Date1)) {
                VedFile fileOCLastDate = new VedFile(parseJsonChronologyAndGetUrl().get(i), (getDate(parseJsonChronologyAndGetDate()).get(i)), parseJsonChronologyAndGetName().get(i));
                fileOCLastDate.getUrl();
                //String urlFileOCLastDate = new String();

                urlFileOCLastDate = fileOCLastDate.getUrl();
                System.out.println(urlFileOCLastDate);
            }

        }

        return urlFileOCLastDate;


    }

    //скачиваем последнее по дате ОС
    public static void downloadLatestOCFileFromChronology(String urlFileOCLastDate) throws IOException {

        URL url = new URL(urlFileOCLastDate);


        InputStream inputStream = url.openStream();
        Files.copy(inputStream, new File("src/main/resources/filesFromChronology/filename").toPath());

    }


    //todo так же 2 метода одинаковых, есть способ объединить их
    //получение ARF из всех файлов
    public static ArrayList<String> getARFFile() throws IOException, ParseException {

        ArrayList<VedFile> vedFilesArrayList = createVedFileObject();
        ArrayList<String> FilesARF = new ArrayList<>();

        for (int i = 0; i < vedFilesArrayList.size(); i++) {

            if (vedFilesArrayList.get(i).getName().contains("ARF")) {
                FilesARF.add(vedFilesArrayList.get(i).getName());

            }
        }
        System.out.println(FilesARF);
        return FilesARF;

    }

    //получаем массив дат файлов ARF
    public static ArrayList<Date> getArrayDatesOfARFFiles() throws IOException, ParseException {

        ArrayList<VedFile> vedFilesArrayList = createVedFileObject();
        ArrayList<Date> datesARF = new ArrayList<>();


        for (int i = 0; i < vedFilesArrayList.size(); i++) {

            if (vedFilesArrayList.get(i).getName().contains("ARF")) {
                datesARF.add(vedFilesArrayList.get(i).getDate());

            }
        }
        System.out.println(datesARF);
        return datesARF;

    }

    //проверяем, что последняя дата ARF самая ранняя

    public static Date getLatestARFDate() throws IOException, ParseException {

        ArrayList<Date> datesARF = getArrayDatesOfARFFiles();


        int length = datesARF.size();
        Date Date2 = datesARF.get(length - 1);

        for (int i = 0; i < length - 1; i++) {
            if (!datesARF.get(length - 1).after(datesARF.get(i))) {
                //todo здесь достаточно просто fail с комментарием
                System.out.println("Последний эллемент массива по времени раньше чем предыдущие");
                Date2 = datesARF.get(length - 1);
                fail();
            }

        }
        return Date2;
    }

    //Получаем url последнего по дате ARF

    public static String getUrlLatestARFFileFromChronology(Date Date2) throws IOException, ParseException {

        ArrayList<VedFile> vedFilesArrayList = createVedFileObject();
        String urlFileARFLastDate = new String();

        for (int i = 0; i < vedFilesArrayList.size(); i++) {
            if (vedFilesArrayList.get(i).getDate().equals(Date2)) {
                VedFile fileARFLastDate = new VedFile(parseJsonChronologyAndGetUrl().get(i), (getDate(parseJsonChronologyAndGetDate()).get(i)), parseJsonChronologyAndGetName().get(i));
                fileARFLastDate.getUrl();

                urlFileARFLastDate = fileARFLastDate.getUrl();
                System.out.println(urlFileARFLastDate);
            }

        }

        return urlFileARFLastDate;

    }

    //скачиваем последний по дате ARF

    public static void downloadLatestARFFileFromChronology(String urlFileARFLastDate) throws IOException {

        URL url = new URL(urlFileARFLastDate);


        InputStream inputStream = url.openStream();
        Files.copy(inputStream, new File("src/main/resources/filesFromChronology/filenameARF").toPath());

    }


    //получение pdf из всех файлов
    public static String getPdfFile() throws IOException, ParseException {

        ArrayList<VedFile> vedFilesArrayList = createVedFileObject();
        String FilePdf = new String();

        for (int i = 0; i < vedFilesArrayList.size(); i++) {

            if (vedFilesArrayList.get(i).getName().contains("pdf")) {
                FilePdf = vedFilesArrayList.get(i).getName();

            }
        }
        System.out.println(FilePdf);
        return FilePdf;

    }

    //Получаем url pdf

    public static String getUrlPdfFileFromChronology(String FilePdf) throws IOException, ParseException {

        ArrayList<VedFile> vedFilesArrayList = createVedFileObject();
        FilePdf = getPdfFile();

        String urlFilePdf = new String();

        VedFile filePdfLastDate = new VedFile(parseJsonChronologyAndGetUrl().get(2), (getDate(parseJsonChronologyAndGetDate()).get(2)), parseJsonChronologyAndGetName().get(2));
        filePdfLastDate.getUrl();

        urlFilePdf = filePdfLastDate.getUrl();
        System.out.println(urlFilePdf);



        return urlFilePdf;

    }

    //скачиваем pdf

    public static void downloadPdfFileFromChronology(String urlFilePdf) throws IOException {

        URL url = new URL(urlFilePdf);


        InputStream inputStream = url.openStream();
        Files.copy(inputStream, new File("src/main/resources/filesFromChronology/filenamePdf").toPath());

    }







}








