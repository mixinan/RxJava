package cc.guoxingnan.rxjavatest.http;

import cc.guoxingnan.rxjavatest.entity.ImageResult;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by mixinan on 2016/7/24.
 */
public interface ApiImage {
    @GET("福利/{number}/{page}")
    Observable<ImageResult> getImages(@Path("number") int number, @Path("page") int page);
}
