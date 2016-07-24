package cc.guoxingnan.rxjavatest.entity;

/**
 * Created by mixinan on 2016/7/24.
 */
public class Article {
    private String desc;
    private String url;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Article{" +
                "desc='" + desc + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
