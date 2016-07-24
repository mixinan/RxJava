package cc.guoxingnan.rxjavatest;

import android.support.v4.app.Fragment;

import rx.Subscription;

/**
 * Created by mixinan on 2016/7/24.
 */
public class BaseFragment extends Fragment {
    protected Subscription subscription;


    protected void unSubscribe(){
        if (subscription!=null && !subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }

    @Override
    public void onDestroyView() {
        unSubscribe();
        super.onDestroyView();
    }
}
