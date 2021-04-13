package archive;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;
import java.util.zip.*;

public class createArchive {

    public static void createArchive() {
//todo файлики назови понятнее. пока не откроешь не поймешь что в нём
        String filename = "src/main/resources/New2.csv";
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("src/main/resources/output.zip"));
             FileInputStream fis = new FileInputStream(filename);) {
            ZipEntry entry1 = new ZipEntry("New2.csv");
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
                System.out.printf("File name: %s \t File size: %d \n", name, size);
                FileOutputStream fout = new FileOutputStream("src/main/resources/newFile/" + name);
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
        //todo ты так и не пробросила параметр path в строчку new File(path). Кажется ты не понимаешь, зачем это нужно.
        // Но без этого никак
        File file1 = new File("src/main/resources/New2.csv");
        File file2 = new File("src/main/java/resources/newFile/New2.csv");

        return new File(path);
    }


    public static boolean compareFiles2() throws IOException {
        //todo при использовании метода getFile ты бы сразу файлы записала бы в FileInputStream и это минус две строчки и читается легко
        File file1 = new File("src/main/resources/New2.csv");
        File file2 = new File("src/main/java/resources/newFile/New2.csv");
        //todo Знаю, что можно так написать, но старайся этого не делать, потому что пределы логики не всегда можно отследить.
        // Велика вероятность сделать ошибку
        if (file1.length() != file2.length()) return false;
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

    //todo код в комментах без текста это плохо. что куда и зачем забудешь
    //public static void compareFiles() {
    //ArchiveTest.openArchiveAndSaveFile();

    //File actual = getFile("src/main/resources/New2.csv");
    //File expected = getFile("src/main/java/resources/newFile/New2.csv");

    //Assert.assertEquals(actual, expected);

    // }


}
