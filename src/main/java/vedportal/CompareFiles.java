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

    //?второй способ сравнения файлов, используя md5
    public static void compareFilesMD5() throws NoSuchAlgorithmException, IOException {


        MessageDigest md_1 = MessageDigest.getInstance("MD5");
        MessageDigest md_2 = MessageDigest.getInstance("MD5");
        MessageDigest md_3 = MessageDigest.getInstance("MD5");
        MessageDigest md_4 = MessageDigest.getInstance("MD5");
        MessageDigest md_5 = MessageDigest.getInstance("MD5");
        MessageDigest md_6 = MessageDigest.getInstance("MD5");
        InputStream is_1 = new FileInputStream("src/main/resources/localFile/End-to-end Import Process_date-28-4-2021_time-18-5.pdf");
        InputStream is_2 = new FileInputStream("src/main/resources/newFile/End-to-end Import Process_date-28-4-2021_time-18-5.pdf");
        InputStream is_3 = new FileInputStream("src/main/resources/localFile/OC_CDF_LM_RU_OUEST_USD-CDF-CDF-HANGZHOU KINGSUN IMP.& EXP.CO.,LTD_date-28-4-2021_time-18-5.xlsx");
        InputStream is_4 = new FileInputStream("src/main/resources/newFile/OC_CDF_LM_RU_OUEST_USD-CDF-CDF-HANGZHOU KINGSUN IMP.& EXP.CO.,LTD_date-28-4-2021_time-18-5.xlsx");
        InputStream is_5 = new FileInputStream("src/main/resources/localFile/ARF_K506_HANGZHOU KINGSUN IMP.& EXP.CO.,LTD._date-28-4-2021_time-18-16.xlsx");
        InputStream is_6 = new FileInputStream("src/main/resources/newFile/ARF_K506_HANGZHOU KINGSUN IMP.& EXP.CO.,LTD._date-28-4-2021_time-18-16.xlsx");
        try {
            is_1 = new DigestInputStream(is_1, md_1);
            is_2 = new DigestInputStream(is_2, md_2);
            is_3 = new DigestInputStream(is_3, md_3);
            is_4 = new DigestInputStream(is_4, md_4);
            is_5 = new DigestInputStream(is_5, md_5);
            is_6 = new DigestInputStream(is_6, md_6);
        } finally {
            is_1.close();
            is_2.close();
            is_3.close();
            is_4.close();
            is_5.close();
            is_6.close();
        }
        byte[] digest_1 = md_1.digest();
        byte[] digest_2 = md_2.digest();
        byte[] digest_3 = md_3.digest();
        byte[] digest_4 = md_4.digest();
        byte[] digest_5 = md_5.digest();
        byte[] digest_6 = md_6.digest();

        Assert.assertEquals(digest_1, digest_2);
        Assert.assertEquals(digest_3, digest_4);
        Assert.assertEquals(digest_5, digest_6);


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
    public static String getMD5File1() throws IOException, NoSuchAlgorithmException {

        String path = "src/main/resources/filesFromDocuments/CD,FInvalidDeadline of PI sending date.xlsx";
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(Files.readAllBytes(Paths.get(path)));
        byte[] digest = md.digest();

        String digestInHex1 = DatatypeConverter.printHexBinary(digest).toLowerCase();
        System.out.println(digestInHex1);

        return digestInHex1;
    }


    //получение MD5 файла2
    public static String getMD5File2() throws IOException, NoSuchAlgorithmException {

        String path = "src/main/resources/zipFiles/ARF_K506_HANGZHOU KINGSUN IMP.& EXP.CO.,LTD._date-28-4-2021_time-18-16.xlsx";
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(Files.readAllBytes(Paths.get(path)));
        byte[] digest = md.digest();

        String digestInHex2 = DatatypeConverter.printHexBinary(digest).toLowerCase();
        System.out.println(digestInHex2);

        return digestInHex2;


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

        public static ArrayList<String> getMD5FileFromZipFiles () throws IOException, NoSuchAlgorithmException {

            File folder = new File("src/main/resources/filesFromArchive");
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

        //3. сравниваем MD5 файлов из папки filesFromDocuments и zipFiles
        public static void compareMD5FilesFrom2Folders () throws IOException, NoSuchAlgorithmException {

            ArrayList <String> actual = getMD5FileFromDocuments();
            ArrayList <String> expected = getMD5FileFromZipFiles();

            Assert.assertEquals(actual, expected);
        }


        //сравниваем MD5 файлов 1 и 2
        public static void compareMD5 () throws IOException, NoSuchAlgorithmException {

            String actual = getMD5File1();
            String expected = getMD5File2();

            Assert.assertEquals(actual, expected);
        }


    //сравниваем файл ОС последний по дате из chronology и файл ОС из documents

    //4. получение MD5 файла OC последнего по дате из chronology
    public static String getMD5LatestOCFromChronology() throws IOException, NoSuchAlgorithmException {

        String path = "src/main/resources/filesFromChronology/filename";
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(Files.readAllBytes(Paths.get(path)));
        byte[] digest = md.digest();

        String digestInHex1 = DatatypeConverter.printHexBinary(digest).toLowerCase();
        System.out.println(digestInHex1);

        return digestInHex1;
    }

    //5. получение MD5 файла OC из documents
    public static String getMD5OCFromDocuments() throws IOException, NoSuchAlgorithmException {

        String path = "src/main/resources/filesFromDocuments/Order Confirmation.xls";
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(Files.readAllBytes(Paths.get(path)));
        byte[] digest = md.digest();

        String digestInHex1 = DatatypeConverter.printHexBinary(digest).toLowerCase();
        System.out.println(digestInHex1);

        return digestInHex1;
    }

    //6. сравниваем MD5 двух ОС
    public static void compareMD5OCFiles () throws IOException, NoSuchAlgorithmException {

        String actual = getMD5LatestOCFromChronology();
        String expected = getMD5OCFromDocuments();

        Assert.assertEquals(actual, expected);
    }

    //7. сравниваем ARF и OC.pdf






    }

































