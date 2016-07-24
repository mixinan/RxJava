package cc.guoxingnan.rxjavatest.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.guoxingnan.rxjavatest.BaseFragment;
import cc.guoxingnan.rxjavatest.R;
import cc.guoxingnan.rxjavatest.adapter.ImageAdapter;
import cc.guoxingnan.rxjavatest.entity.Image;
import cc.guoxingnan.rxjavatest.entity.ImageResult;
import cc.guoxingnan.rxjavatest.http.HttpUtil;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by mixinan on 2016/7/23.
 */
public class FragmentOne extends BaseFragment {

    @BindView(R.id.rvImage)
    RecyclerView rvImage;

    private int page;
    private ImageAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    Observer<List<Image>> observer = new Observer<List<Image>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(List<Image> images) {
            adapter = new ImageAdapter(images);
            rvImage.setAdapter(adapter);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, null);
        ButterKnife.bind(this, view);

        initView();

        page = 1;
        getData(page);

        return view;
    }

    private void initView() {
        layoutManager = new GridLayoutManager(getActivity(), 2);
        rvImage.setLayoutManager(layoutManager);
    }


    private void getData(int page) {
        unSubscribe();
        subscription = HttpUtil.getImageApi().getImages(10, page)
                .map(new Func1<ImageResult, List<Image>>() {
                    @Override
                    public List<Image> call(ImageResult imageResult) {
                        List<Image> data = imageResult.getImages();
                        return data;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
