package cc.guoxingnan.rxjavatest.http;

import cc.guoxingnan.rxjavatest.entity.ArticleResult;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by mixinan on 2016/7/24.
 */
public interface ApiAndroid {
    @GET("Android/{number}/{page}")
    Observable<ArticleResult> getAndroids(@Path("number")int number, @Path("page")int page);
}
