package vedportal.archive;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipArchive {

    //скачиваем архив
    public static void zipArchiveDownload3() throws IOException {

        URL url = new URL("https://storage.yandexcloud.net/lm-ved-bucket/25065238.zip?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=V4z29OOjnDiQHo3vtUFi%2F20210609%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210609T073639Z&X-Amz-Expires=43200&X-Amz-Signature=a694cf119a18720c096efa954241056432413f59cdcdcd90563a013cedade9a3&X-Amz-SignedHeaders=host");
        InputStream inputStream = url.openStream();
        Files.copy(inputStream, new File("src/main/resources/ZipArchive.zip").toPath());

    }

    //разворачиваем архив
    public static void openZipArchiveAndSaveFile() {

        try (ZipInputStream zin = new ZipInputStream(new FileInputStream("src/main/resources/ZipArchive.zip"))) {
            ZipEntry entry;
            String name;
            long size;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName(); // получим название файла
                size = entry.getSize();  // получим его размер в байтах
                System.out.printf("File name: %s \t File size: %d \n", name, size); //форматирование,вывод данных
                FileOutputStream fout = new FileOutputStream("src/main/resources/filesFromArchive/" + name); //достаём и помещаем файл в папку
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

    public static void deleteArchive(String path) throws IOException {

        File zout = new File(path);
        zout.delete();
    }

    public static void deleteFiles(String path) throws IOException {

        File dir = new File(path);

        File[] txtFiles = dir.listFiles();

        for (File txtFile : txtFiles) {
            txtFile.delete();
        }



        }






    }







