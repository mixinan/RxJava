package cc.guoxingnan.rxjavatest.fragment;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.guoxingnan.rxjavatest.BaseFragment;
import cc.guoxingnan.rxjavatest.R;
import cc.guoxingnan.rxjavatest.adapter.AppAdapter;
import cc.guoxingnan.rxjavatest.entity.AppInfo;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by mixinan on 2016/7/24.
 */
public class FragmentApp extends BaseFragment implements AdapterView.OnItemClickListener {

    @BindView(R.id.lv_app)
    ListView lvApp;

    private AppAdapter adapter;
    private List<AppInfo> data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_4, container, false);
        ButterKnife.bind(this, view);

        getData();
        lvApp.setOnItemClickListener(this);
        return view;
    }

    private void getData() {
        subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<AppInfo>>() {
                    @Override
                    public void call(List<AppInfo> appInfos) {
                        data = appInfos;
                        adapter = new AppAdapter(getActivity(), appInfos);
                        lvApp.setAdapter(adapter);
                    }
                });
    }

    Observable<List<AppInfo>> observable = Observable.create(new Observable.OnSubscribe<List<AppInfo>>() {
        @Override
        public void call(Subscriber<? super List<AppInfo>> subscriber) {
            List<AppInfo> data = findApps();
            subscriber.onNext(data);
        }
    });


    /**
     * 需要在子线程做的操作：获取app 集合
     *
     * @return
     */
    private List<AppInfo> findApps() {
        List<AppInfo> data = new ArrayList<AppInfo>();
        PackageManager packageManager = getActivity().getPackageManager();
        List<PackageInfo> pInfos = packageManager.getInstalledPackages(0);
        for (int i = 0; i < pInfos.size(); i++) {
            AppInfo appInfo = new AppInfo();
            PackageInfo packageInfo = pInfos.get(i);

            appInfo.setName(getAppName(packageInfo.packageName, packageManager));
            appInfo.setTime(String.valueOf(packageInfo.lastUpdateTime));
            appInfo.setImage(packageInfo.applicationInfo.loadIcon(packageManager));

            File apkFile = new File(packageInfo.applicationInfo.sourceDir);
            appInfo.setFile(apkFile);

            appInfo.setSize(""+((float) apkFile.length() / 1024 / 1024));

            //如果非系统应用，则添加至data
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                data.add(appInfo);
            }
        }
        return data;
    }


    /**
     * 根据包名，获取应用名
     */
    protected String getAppName(String packageName, PackageManager packageManager) {
        String appName = "";
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, 0);
            appName = (String) packageManager.getApplicationLabel(applicationInfo);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appName;
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        //分享文件
        File apkFile = data.get(position).getFile();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(apkFile));
        startActivity(intent);
    }
}
