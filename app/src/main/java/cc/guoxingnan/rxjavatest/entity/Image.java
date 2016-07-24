package cc.guoxingnan.rxjavatest.entity;

/**
 * Created by mixinan on 2016/7/24.
 */
public class Image {
    private String url;
    private String createdAt;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Image{" +
                "url='" + url + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
