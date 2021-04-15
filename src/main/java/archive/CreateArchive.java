package archive;

import org.testng.Assert;

import java.io.*;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipOutputStream;
import java.util.zip.*;

public class CreateArchive {

    public static void createArchive() {

        String filename = "src/main/resources/NumbersFile.csv";
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("src/main/resources/output.zip"));
             FileInputStream fis = new FileInputStream(filename);) {
            ZipEntry entry1 = new ZipEntry("NumbersFile.csv");
            zout.putNextEntry(entry1);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }

    }

    //todo нашла метод-молодец. А поняла что он делает? Сможешь рассказать?
    public static void openArchiveAndSaveFile() {

        try (ZipInputStream zin = new ZipInputStream(new FileInputStream("src/main/resources/output.zip"))) {
            ZipEntry entry;
            String name;
            long size;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName(); // получим название файла
                size = entry.getSize();  // получим его размер в байтах
                System.out.printf("File name: %s \t File size: %d \n", name, size); //форматирование,вывод данных
                FileOutputStream fout = new FileOutputStream("src/main/resources/newFile/" + name); //достаём и помещаем файл в папку
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }

    }

    public static File getFile(String path) {

        return new File("src/main/resources/NumbersFile.csv");//указываем нужный path
    }


    public static boolean compareFiles2() throws IOException {

        File file1 = new File("src/main/resources/NumbersFile.csv");
        File file2 = new File("src/main/resources/newFile/NumbersFile.csv");
        if
        (file1.length() != file2.length()) {
            return false;
        }
        FileInputStream fis1 = new FileInputStream(file1);
        FileInputStream fis2 = new FileInputStream(file2);
        //todo разобралась что этот код делает? и зачем тут try/catch/finally?
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

    //второй способ сравнения файлов, используя md5
    public static void compareFilesMD5() throws NoSuchAlgorithmException, IOException {


        MessageDigest md_1 = MessageDigest.getInstance("MD5");
        MessageDigest md_2 = MessageDigest.getInstance("MD5");
        InputStream is_1 = new FileInputStream("src/main/resources/NumbersFile.csv");
        InputStream is_2 = new FileInputStream("src/main/resources/newFile/NumbersFile.csv");
        try {
            is_1 = new DigestInputStream(is_1, md_1);
            is_2 = new DigestInputStream(is_2, md_2);
        }
        finally {
            is_1.close();
            is_2.close();
        }
        byte[] digest_1 = md_1.digest();
        byte[] digest_2 = md_2.digest();

        Assert.assertEquals(digest_1, digest_2);


        }

    public static void deleteArchiveAndNewFile() throws IOException {

        File zout = new File("src/main/resources/output.zip");
        zout.delete();
    }


    }
