
import org.testng.annotations.Test;

import java.io.IOException;

import static API.ZipArchive.*;


public class APITest {

    @Test

    public void shouldDownloadArchiveTest() throws IOException {

        //String urlToDownload = "https://storage.yandexcloud.net/lm-ved-bucket/24895667.zip?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=V4z29OOjnDiQHo3vtUFi%2F20210419%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210419T113233Z&X-Amz-Expires=43200&X-Amz-Signature=d0731c352f386f0db2533819cdb57de4e09e887857a3103f3c4da40225098af4&X-Amz-SignedHeaders=host";
        //parseURL(urlToDownload);
        String paramOfUrl = "X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=V4z29OOjnDiQHo3vtUFi%2F20210427%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210427T073856Z&X-Amz-Expires=43200&X-Amz-Signature=a6c0664747e384872a020fd3db94f49f9abc81d256da271e1d04389a04a39b0b&X-Amz-SignedHeaders=host";
        zipArchiveDownload(parseURL(paramOfUrl), "24982262");
        //проверка


    }

    @Test

    public void shouldParseUrlTest() throws IOException {

        parseURL("X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=V4z29OOjnDiQHo3vtUFi%2F20210427%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210427T073856Z&X-Amz-Expires=43200&X-Amz-Signature=a6c0664747e384872a020fd3db94f49f9abc81d256da271e1d04389a04a39b0b&X-Amz-SignedHeaders=host");


    }
}



