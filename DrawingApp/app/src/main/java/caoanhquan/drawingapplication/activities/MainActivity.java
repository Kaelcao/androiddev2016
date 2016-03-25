package caoanhquan.drawingapplication.activities;

import android.app.ActionBar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import caoanhquan.drawingapplication.R;
import caoanhquan.drawingapplication.customviews.DrawingView;
import caoanhquan.drawingapplication.fragments.DrawFragment;
import caoanhquan.drawingapplication.fragments.OnlineDrawFragment;
import caoanhquan.drawingapplication.fragments.SavedDrawFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(pager);
    }
}

class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;

    final String[] titles = {"Saved Draws", "Online Draws"};

    public HomeFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
//        DrawFragment drawFragment = new DrawFragment();
        OnlineDrawFragment onlineDrawFragment = new OnlineDrawFragment();
        SavedDrawFragment savedDrawFragment = new SavedDrawFragment();

        switch (position) {
            case 0:
                return onlineDrawFragment;
            case 1:
                return savedDrawFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}