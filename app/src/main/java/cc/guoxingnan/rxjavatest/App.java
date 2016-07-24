package cc.guoxingnan.rxjavatest;

import android.app.Application;

/**
 * Created by mixinan on 2016/7/23.
 */
public class App extends Application{
    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static App getApp(){
        return app;
    }
}
