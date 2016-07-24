package cc.guoxingnan.rxjavatest.http;

import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mixinan on 2016/7/24.
 */
public class HttpUtil {
    private static ApiImage imageApi;
    private static ApiAndroid androidApi;
    private static ApiIOS iosApi;

    private static OkHttpClient client = new OkHttpClient();
    private static Retrofit retrofit = getRetrofit();

    public static ApiImage getImageApi() {
        if (imageApi == null) {
            imageApi = retrofit.create(ApiImage.class);
        }
        return imageApi;
    }


    public static ApiAndroid getAndroidApi() {
        if (androidApi == null) {
            androidApi = retrofit.create(ApiAndroid.class);
        }
        return androidApi;
    }


    public static ApiIOS getIosApi(){
        if (iosApi==null){
            iosApi = retrofit.create(ApiIOS.class);
        }
        return iosApi;
    }

    @NonNull
    private static Retrofit getRetrofit() {
        return new Retrofit.Builder().client(client).baseUrl(Api.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


}
