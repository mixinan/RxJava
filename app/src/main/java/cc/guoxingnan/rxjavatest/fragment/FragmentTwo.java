package cc.guoxingnan.rxjavatest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cc.guoxingnan.rxjavatest.R;

/**
 * Created by mixinan on 2016/7/23.
 */
public class FragmentTwo extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2,null);

        return view;
    }

}
