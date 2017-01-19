package com.zeus.hello.moiveapp;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.zeus.hello.moiveapp.MyView.MyActivity;
import com.zeus.hello.moiveapp.MyView.MyDictoryFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends MyActivity {
    public static final String TAG ="Main2Activity";
    private  ArrayList<String> pages_Array;
    public  Fragment fragment;
    String query;
    @BindView(R.id.main2_viewpager)
    public ViewPager viewPager;
    @BindView(R.id.main2_toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        Log.d(TAG, "onCreate: .....threadId"+Thread.currentThread().getId());
        View decorView = getWindow().getDecorView();
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pages_Array=new ArrayList<String>();
        pages_Array.add("Dictory");
//        pages_Array.add("MEIZI");
//        pages_Array.add("Japanese");
        SlidingTabLayout tabLayout_1 = (SlidingTabLayout) findViewById( R.id.main2_sliding);
        viewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager()));
        tabLayout_1.setViewPager(viewPager);
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent){
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
             query = intent.getStringExtra(SearchManager.QUERY);
//            Log.d(TAG, "onNewIntent: .....search query:"+query);
            if(viewPager.getCurrentItem()!=0){
                viewPager.setCurrentItem(0);
                Log.d(TAG, "onNewIntent:....query:"+query);
            }
            if (fragment!=null){
                ((MyDictoryFragment)fragment).get_info(query);
            }else {
                Log.d(TAG, "handleIntent: framgent is null!");
            }
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class MainViewPagerAdapter extends FragmentStatePagerAdapter{

        public MainViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            fragment=new MyDictoryFragment();
            Log.d(TAG, "getItem: new Framgent!!!");
            ((MyDictoryFragment)fragment).setActivity(Main2Activity.this);
            if(query!=null){
                Bundle b=new Bundle();
                b.putString(TAG,query);
                fragment.setArguments(b);

            }

            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return pages_Array.get(position);
        }

        @Override
        public int getCount() {
            return pages_Array.size();
        }
    }
}
