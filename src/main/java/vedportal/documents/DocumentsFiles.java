package vedportal.documents;


import com.jayway.jsonpath.JsonPath;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;


public class DocumentsFiles {

    public static String jsonDocuments() throws IOException {
        //todo вынеси путь в переменную
        FileInputStream fs = new FileInputStream("src/main/resources/json/jsonResponseDocuments");
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        String jsonString = br.readLine();
        System.out.println(jsonString);
        return jsonString;


    }


    //парсинг json documents, получение url файлов
    //todo из названия не понятно, что будет на выходе
    public static List<String> parseJsonDocuments() throws IOException {


        String jsonString = jsonDocuments();

        List<String> urlFiles = JsonPath.read(jsonString, "$..url");
        for (String url : urlFiles) {
            System.out.println(url);
        }
        return urlFiles;

    }

    //парсинг json documents, получение name файлов

    public static List<String> parseJsonDocumentsAndGetNames() throws IOException {


        String jsonString = jsonDocuments();

        List<String> nameFiles = JsonPath.read(jsonString, "$..data[0].files[0,1,2].fileName");
        for (String url : nameFiles) {
            System.out.println(url);
        }
        return nameFiles;

    }

    //скачиваем файлы из documents, присваиваем имя

    public static void downloadFilesDocuments(List<String> urlFiles, List<String> nameFiles) throws IOException {


        for (int i = 0; i < urlFiles.size(); i++) {
            URL url = new URL(urlFiles.get(i));

            InputStream inputStream = url.openStream();
            Files.copy(inputStream, new File("src/main/resources/filesFromDocuments/" + nameFiles.get(i) + "").toPath());

        }
    }








}
