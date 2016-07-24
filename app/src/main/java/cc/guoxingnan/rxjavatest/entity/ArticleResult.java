package cc.guoxingnan.rxjavatest.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mixinan on 2016/7/24.
 */
public class ArticleResult {
    private boolean error;
    private @SerializedName("results")
    List<Article> articles;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "ArticleResult{" +
                "articles.size()---" + articles.size();
    }
}
