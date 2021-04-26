
import org.testng.annotations.Test;

import java.io.IOException;

import static API.ZipArchive.parseURL;
import static API.ZipArchive.zipArchiveDownload;


public class APITest {

    @Test

    public void shouldDownloadArchiveTest() throws IOException {

        //String urlToDownload = "https://storage.yandexcloud.net/lm-ved-bucket/24895667.zip?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=V4z29OOjnDiQHo3vtUFi%2F20210419%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210419T113233Z&X-Amz-Expires=43200&X-Amz-Signature=d0731c352f386f0db2533819cdb57de4e09e887857a3103f3c4da40225098af4&X-Amz-SignedHeaders=host";
        //parseURL(urlToDownload);
        zipArchiveDownload(parseURL("X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=V4z29OOjnDiQHo3vtUFi%2F20210426%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210426T124039Z&X-Amz-Expires=43200&X-Amz-Signature=303296a2179c8f6c95ad194eeb6f86038d937ce7379a5209ec618acde9fedfa5&X-Amz-SignedHeaders=host"));
        //проверка


    }

    @Test

    public void shouldParseUrlTest() throws IOException {

        parseURL("X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=V4z29OOjnDiQHo3vtUFi%2F20210426%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210426T124039Z&X-Amz-Expires=43200&X-Amz-Signature=303296a2179c8f6c95ad194eeb6f86038d937ce7379a5209ec618acde9fedfa5&X-Amz-SignedHeaders=host");


    }
}



