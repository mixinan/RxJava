package cc.guoxingnan.rxjavatest.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.guoxingnan.rxjavatest.BaseFragment;
import cc.guoxingnan.rxjavatest.R;
import cc.guoxingnan.rxjavatest.adapter.ArticleAdapter;
import cc.guoxingnan.rxjavatest.entity.Article;
import cc.guoxingnan.rxjavatest.entity.ArticleResult;
import cc.guoxingnan.rxjavatest.http.HttpUtil;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by mixinan on 2016/7/23.
 */
public class FragmentTwo extends BaseFragment {


    @BindView(R.id.lv_android)
    ListView lvAndroid;

    private ArticleAdapter adapter;
    private int page;

    Observer<List<Article>> observer = new Observer<List<Article>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Log.i("Test", "onError: "+e.toString());
        }

        @Override
        public void onNext(List<Article> articles) {
            adapter = new ArticleAdapter(getActivity(), articles);
            lvAndroid.setAdapter(adapter);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, null);
        ButterKnife.bind(this, view);
        page = 1;
        getData(page);

        return view;
    }

    private void getData(int page) {
        unSubscribe();
        subscription = HttpUtil.getAndroidApi().getAndroids(20, page)
                .map(new Func1<ArticleResult, List<Article>>() {
                    @Override
                    public List<Article> call(ArticleResult articleResult) {
                        List<Article> data = articleResult.getArticles();
                        return data;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
