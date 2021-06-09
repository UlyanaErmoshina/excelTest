package vedportal;


import org.testng.Assert;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;


public class CompareFiles {

    //парсим url на параметры (ключ-значение)
    public static Map<String, String> parseURL(String query) {


        String[] params = query.split("&");
        Map<String, String> map = new HashMap<String, String>();

        for (String param : params) {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }

        System.out.println(map);

        return map;
    }




    //?сравниваем файлы
    public static boolean compareFiles() throws IOException {

        File file1 = new File("src/main/resources/downloadFiles/OC_CDF_LM_RU_OUEST_USD-CDF-CDF-HANGZHOU KINGSUN IMP.& EXP.CO.,LTD_date-28-4-2021_time-18-5.xlsx");
        File file2 = new File("src/main/resources/zipFiles/OC_CDF_LM_RU_OUEST_USD-CDF-CDF-HANGZHOU KINGSUN IMP.& EXP.CO.,LTD_date-28-4-2021_time-18-5.xlsx");
        if
        (file1.length() != file2.length()) {
            return false;
        }
        FileInputStream fis1 = new FileInputStream(file1);
        FileInputStream fis2 = new FileInputStream(file2);

        try {
            int byte1;
            while ((byte1 = fis1.read()) != -1) {
                int byte2 = fis2.read();
                if (byte1 != byte2) return false;
            }
        } finally {
            fis1.close();
            fis2.close();
        }
        return true;
    }



    //?скачиваем файлы из chronology

    public static void downloadFilesChronology(List<String> urlFiles) throws IOException {


        for (int i = 0; i < urlFiles.size(); i++) {
            URL url = new URL(urlFiles.get(i));

            InputStream inputStream = url.openStream();
            Files.copy(inputStream, new File("src/main/resources/filesFromChronology/filename" + i + "").toPath());
        }

    }


    //?сравнение файлов по строкам
    public static void compareFilesString() throws IOException {

        String first = "src/main/resources/filesFromDocuments/CD,FInvalidDeadline of PI sending date.xlsx";
        String second = "src/main/resources/zipFiles/ARF_K506_HANGZHOU KINGSUN IMP.& EXP.CO.,LTD._date-28-4-2021_time-18-16.xlsx";
        BufferedReader fBr = new BufferedReader(new FileReader(first));
        BufferedReader sBr = new BufferedReader(new FileReader(second));

        ArrayList<String> strings = new ArrayList<String>();

        while ((first = fBr.readLine()) != null) {
            strings.add(first);
        }
        fBr.close();

        while ((second = sBr.readLine()) != null) {
            if (strings.contains(second)) {
                System.out.println(second);
            }
        }
        sBr.close();
    }

    //получение MD5 файла1
    public static String getMD5File1(String path) throws IOException, NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(Files.readAllBytes(Paths.get(path)));
        byte[] digest = md.digest();

        String digestInHex1 = DatatypeConverter.printHexBinary(digest).toLowerCase();

        System.out.println(digestInHex1);

        return digestInHex1;


    }



    //СРАВНИВАЕМ ФАЙЛЫ

    //1. получение MD5 файлов из папки documents

    public static ArrayList<String> getMD5FileFromDocuments() throws IOException, NoSuchAlgorithmException {

        File folder = new File("src/main/resources/filesFromDocuments");
        File[] listOfFiles = folder.listFiles();

        ArrayList<String> digestInHex2 = new ArrayList<String>();


        for (File file : listOfFiles) {
            if (file.isFile()) {
                //System.out.println(file.getPath());

            }
            String path = file.getPath();


            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(Files.readAllBytes(Paths.get(path)));
            byte[] digest = md.digest();
            String digestInHex3;

            digestInHex3 = DatatypeConverter.printHexBinary(digest).toLowerCase();
            digestInHex2.add(digestInHex3);

            System.out.println(digestInHex2);

        }

        return digestInHex2;

    }


        //2. получение MD5 файлов из папки zipFiles

        public static ArrayList<String> getMD5FileFromZipFiles (String path) throws IOException, NoSuchAlgorithmException {

            File folder = new File(path);
            File[] listOfFiles = folder.listFiles();

            ArrayList<String> digestInHex2 = new ArrayList<String>();

            for (File file : listOfFiles) {

                //String path = file.getPath();

                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(Files.readAllBytes(Paths.get(path)));
                byte[] digest = md.digest();
                String digestInHex3;

                digestInHex3 = DatatypeConverter.printHexBinary(digest).toLowerCase();
                digestInHex2.add(digestInHex3);

                System.out.println(digestInHex2);
            }

            return digestInHex2;

        }

        //3. сравниваем MD5 файлов из папки filesFromDocuments и zipFiles

        public static void compareMD5FilesFrom2Folders () throws IOException, NoSuchAlgorithmException {

            ArrayList <String> actual = getMD5FileFromDocuments();
            ArrayList <String> expected = getMD5FileFromZipFiles("src/main/resources/filesFromArchive");

            Assert.assertEquals(actual, expected);
        }


        //сравниваем MD5 файлов 1 и 2
        //public static void compareMD5 () throws IOException, NoSuchAlgorithmException {

            //String actual = getMD5File1();
            //String expected = getMD5File2();

            //Assert.assertEquals(actual, expected);
        //}


    //сравниваем файл ОС последний по дате из chronology и файл ОС из documents

    //4. получение MD5 файла OC последнего по дате из chronology

    public static String getMD5LatestOCFromChronology(String path) throws IOException, NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(Files.readAllBytes(Paths.get(path)));
        byte[] digest = md.digest();

        String digestInHex1 = DatatypeConverter.printHexBinary(digest).toLowerCase();
        System.out.println(digestInHex1);

        return digestInHex1;
    }

    //5. получение MD5 файла OC из documents
    public static String getMD5OCFromDocuments(String path) throws IOException, NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(Files.readAllBytes(Paths.get(path)));
        byte[] digest = md.digest();

        String digestInHex1 = DatatypeConverter.printHexBinary(digest).toLowerCase();
        System.out.println(digestInHex1);

        return digestInHex1;
    }

    //6. сравниваем MD5 двух ОС
    public static void compareMD5OCFiles () throws IOException, NoSuchAlgorithmException {

        String actual = getMD5LatestOCFromChronology("src/main/resources/filesFromChronology/filename");
        String expected = getMD5OCFromDocuments("src/main/resources/filesFromDocuments/Order Confirmation.xls");

        Assert.assertEquals(actual, expected);
    }

    //сравниваем файл ARF последний по дате из chronology и файл ARF из documents

    //7. получение MD5 файла ARF последнего по дате из chronology

    public static String getMD5LatestARFFromChronology(String path) throws IOException, NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(Files.readAllBytes(Paths.get(path)));
        byte[] digest = md.digest();

        String digestInHex1 = DatatypeConverter.printHexBinary(digest).toLowerCase();
        System.out.println(digestInHex1);

        return digestInHex1;
    }

    //8. получение MD5 файла ARF из documents
    public static String getMD5ARFFromDocuments(String path) throws IOException, NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(Files.readAllBytes(Paths.get(path)));
        byte[] digest = md.digest();

        String digestInHex1 = DatatypeConverter.printHexBinary(digest).toLowerCase();
        System.out.println(digestInHex1);

        return digestInHex1;
    }

    //9. сравниваем MD5 двух ARF
    public static void compareMD5ARFFiles () throws IOException, NoSuchAlgorithmException {

        String actual = getMD5LatestARFFromChronology("src/main/resources/filesFromChronology/filenameARF");
        String expected = getMD5ARFFromDocuments("src/main/resources/filesFromDocuments/Atlas Russian File.xls");

        Assert.assertEquals(actual, expected);
    }

    //сравниваем файл pdf из chronology и файл pdf из documents


    //10. получение MD5 файла pdf из chronology

    public static String getMD5PdfFromChronology(String path) throws IOException, NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(Files.readAllBytes(Paths.get(path)));
        byte[] digest = md.digest();

        String digestInHex1 = DatatypeConverter.printHexBinary(digest).toLowerCase();
        System.out.println(digestInHex1);

        return digestInHex1;
    }



    //11. получение MD5 файла pdf из documents
    public static String getMD5PdfFromDocuments(String path) throws IOException, NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(Files.readAllBytes(Paths.get(path)));
        byte[] digest = md.digest();

        String digestInHex1 = DatatypeConverter.printHexBinary(digest).toLowerCase();
        System.out.println(digestInHex1);

        return digestInHex1;
    }


    //12. сравниваем MD5 двух pdf
    public static void compareMD5PdfFiles () throws IOException, NoSuchAlgorithmException {

        String actual = getMD5LatestOCFromChronology("src/main/resources/filesFromChronology/filenamePdf");
        String expected = getMD5PdfFromDocuments("src/main/resources/filesFromDocuments/Order Confirmation.pdf");

        Assert.assertEquals(actual, expected);
    }




















    }

































