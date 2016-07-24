package cc.guoxingnan.rxjavatest;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.guoxingnan.rxjavatest.fragment.FragmentApp;
import cc.guoxingnan.rxjavatest.fragment.FragmentOne;
import cc.guoxingnan.rxjavatest.fragment.FragmentThree;
import cc.guoxingnan.rxjavatest.fragment.FragmentTwo;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initData();

        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "美女图片";
                    case 1:
                        return "Android文章";
                    case 2:
                        return "ios文章";
                    case 3:
                        return "分享手机中的应用";
                    default:
                        return "美女图片";
                }
            }
        });

        tabLayout.setupWithViewPager(viewpager);
        viewpager.setOffscreenPageLimit(4);
    }

    private void initData() {
        fragments = new ArrayList<>();
        fragments.add(new FragmentOne());
        fragments.add(new FragmentTwo());
        fragments.add(new FragmentThree());
        fragments.add(new FragmentApp());
    }

}
