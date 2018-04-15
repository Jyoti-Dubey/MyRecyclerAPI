package recyclerapi.jyoti.com.myrecyclerapi.model;

/**
 * Created by jyoti on 15-04-2018.
 */

public class MainActivityModel {
    private String imgURL;
    private String title;
    private String desc;
    private String publish;
    private String urlReadMore;

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getUrlReadMore() {
        return urlReadMore;
    }

    public void setUrlReadMore(String urlReadMore) {
        this.urlReadMore = urlReadMore;
    }
}
