package vedportal.chronology;

import java.util.Date;


public class VedFile {

    String url;
    Date date;
    String name;



    public VedFile(String url, Date date, String name) {
        this.url = url;
        this.date = date;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
