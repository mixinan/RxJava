package cc.guoxingnan.rxjavatest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        first();

//        second();

//        third();

//        forth();

//        fifth();

//        sixth();

//        seventh();

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.i(TAG, "被调用：" + Thread.currentThread().getName());
                subscriber.onNext("被调用了！");
            }
        });

        Action1<String> action = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i(TAG, "call: " + Thread.currentThread().getName());
            }
        };

        observable.subscribe(action);



    }


    private void seventh() {
        Observable.just(0, 1).repeat(5).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.i(TAG, "call: " + integer);
            }
        });
    }


    private void sixth() {
        Observable.range(0, 12).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.i(TAG, "call: " + integer);
            }
        });
    }


    private void fifth() {
        Observable.just(0, 1, 2).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.i(TAG, "call: " + integer);
            }
        });
    }


    private void forth() {
        Observable<String> observable = Observable.just("forth");
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i(TAG, "call: " + s);
            }
        });
    }


    private void third() {
        Observable<String> observable = Observable.just("third");
        Action1<String> action1 = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i(TAG, "call: " + s);
            }
        };

        observable.subscribe(action1);
    }


    private void second() {
        Observable<String> observable = Observable.just("嘿嘿");
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "-----------onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext: " + s);
            }
        };

        observable.subscribe(subscriber);
    }


    private void first() {
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext: " + s);
            }
        };

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("heihei");
            }
        });

        observable.subscribe(subscriber);
    }
}
