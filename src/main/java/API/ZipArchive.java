package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;


public class ZipArchive {


    public static Map<String, String> parseURL(String query) {

        //String site = "https://storage.yandexcloud.net/lm-ved-bucket/24982262.zip?";

        String[] params = query.split("&");
        Map<String, String> map = new HashMap<String, String>();

        for (String param : params) {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }

        System.out.println(map);
        //String url = site + map;
        return map;
    }


    public static void zipArchiveDownload(Map<String, String> maps) throws IOException {


        File outputFile = new File("src/main/resources/newFile/", "ZipArchive.zip");
        String site = "https://storage.yandexcloud.net/lm-ved-bucket/24982262.zip?";



        //String urlToDownload = "https://storage.yandexcloud.net/lm-ved-bucket/24895667.zip?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=V4z29OOjnDiQHo3vtUFi%2F20210419%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210419T113233Z&X-Amz-Expires=43200&X-Amz-Signature=d0731c352f386f0db2533819cdb57de4e09e887857a3103f3c4da40225098af4&X-Amz-SignedHeaders=host";
        String urlToDownload = site + "X-Amz-Algorithm=" + maps.get("X-Amz-Algorithm") + "&X-Amz-Credential=" + maps.get("X-Amz-Credential") + "&X-Amz-Date=" + maps.get("X-Amz-Date") + "&X-Amz-Expires=" + maps.get("X-Amz-Expires") + "&X-Amz-Signature=" + maps.get("X-Amz-Signature") + "&X-Amz-SignedHeaders=" + maps.get("X-Amz-SignedHeaders");

        //outputPath = "src/main/resources/newFile/";
        //filename = "ZipArchive.zip";
        System.out.println(urlToDownload);
        System.out.println(maps.get("X-Amz-Algorithm"));

        Response response = RestAssured.given().//получение ответа
                when().
                get(urlToDownload).
                andReturn();
        Assert.assertEquals(response.getStatusCode(), "200");
        if (response.getStatusCode() == 200) {

            if (outputFile.exists()) {
                outputFile.delete();
            }

            System.out.println("Downloaded an " + response.getHeader("Content-Type"));

            byte[] fileContents = response.getBody().asByteArray();

            OutputStream outStream = null;

            outStream = new FileOutputStream(outputFile);
            outStream.write(fileContents);
            if (outStream != null) {
                outStream.close();
            }
        }

    }

}
