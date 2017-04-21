package com.zeus.hello.moiveapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.zeus.hello.moiveapp.MyUtil.ImageDetailFragment;

public class ViewpagerActivity extends AppCompatActivity {

    public final static Integer[] imageResIds=new Integer[]{
            R.drawable.backup,R.drawable.exit,R.drawable.location1,
            R.drawable.mail,R.drawable.star,R.drawable.setting
    };
    private ImagePagerAdapter mAdapter;
    private ViewPager mPager;
    private Toolbar toolbar;
    public PagerTitleStrip pagerTitleStrip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        mAdapter=new ImagePagerAdapter(getSupportFragmentManager(),imageResIds.length);
        mPager= (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        toolbar= (Toolbar) findViewById(R.id.viewpager_bar);
        setSupportActionBar(toolbar);
        pagerTitleStrip= (PagerTitleStrip) findViewById(R.id.pager_title);
//        ActionBar actionBar=getSupportActionBar();
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//        ActionBar.TabListener tabListener=new ActionBar.TabListener(){
//
//            @Override
//            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
//
//            }
//
//            @Override
//            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
//
//            }
//
//            @Override
//            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
//
//            }
//        };
    }
    class  ImagePagerAdapter extends FragmentStatePagerAdapter{
        private final int mSize;
        public ImagePagerAdapter(FragmentManager fm,int size) {
            super(fm);
            mSize=size;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment=new ImageDetailFragment();
            Bundle args=new Bundle();
            args.putInt(ImageDetailFragment.IMAGE_DATA_EXTRA,ViewpagerActivity.imageResIds[position]);
            fragment.setArguments(args);

            Log.d("aa", "getItem: "+getPageTitle(position)+".");
            return fragment;
        }

        @Override
        public int getCount() {
            return mSize;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return String.valueOf(position);
        }
    }


}

