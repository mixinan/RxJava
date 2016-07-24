package cc.guoxingnan.rxjavatest.entity;



import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mixinan on 2016/7/24.
 */
public class ImageResult {
    private boolean error;
    private @SerializedName("results")
    List<Image> images;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "ImageResult{" +
                "images.size()---" + images.size();
    }
}
