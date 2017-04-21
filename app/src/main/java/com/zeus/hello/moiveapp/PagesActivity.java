package com.zeus.hello.moiveapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.flyco.tablayout.SlidingTabLayout;
import com.zeus.hello.moiveapp.MyFragment.BrowserFragment;
import com.zeus.hello.moiveapp.MyFragment.MyMoiveFragment;
import com.zeus.hello.moiveapp.MyFragment.WifiItemFragment;
import com.zeus.hello.moiveapp.MyFragment.MeiziFragment;
import com.zeus.hello.moiveapp.MyUtil.MyWifiInfo;
import com.zeus.hello.moiveapp.MyView.MoivelistFra;
import com.zeus.hello.moiveapp.MyView.MyBaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PagesActivity extends AppCompatActivity {

    public static PagesActivity activity;
    private final static String TAG="PagesActivity";
    @BindView(R.id.pages_toobar)
    Toolbar toolbar;
    @BindView(R.id.pages_pager)
    ViewPager viewPager;
    @BindView(R.id.pages_pager_title)
    SlidingTabLayout pagerTitleStrip;

    WifiManager wm;
    ArrayList<String> arrayList;
    private final IntentFilter mintentFilter=new IntentFilter();
    WiFiBroadcastReceiver wiFiBroadcastReceiver;
    public static boolean isWifi=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pages);

        activity=this;

        ButterKnife.bind(this);
        arrayList=new ArrayList<String>();
        arrayList.add("MoiveList");
        arrayList.add("Meizi");
        arrayList.add("Wifi");
        arrayList.add("WebBrowser");

        viewPager.setAdapter(new MyPagesAdpter(getSupportFragmentManager()));
        pagerTitleStrip.setViewPager(viewPager);


        toolbar= (Toolbar) findViewById(R.id.pages_toobar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setTitle("我的app");
        }

//        arrayList.add("japanese");
//        arrayList.add("video");
//        arrayList.add("web");
        Log.d(TAG, "onCreate: size"+arrayList.size());
        mintentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        mintentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        wiFiBroadcastReceiver=new WiFiBroadcastReceiver();
        wm= (WifiManager) PagesActivity.activity.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        wm.startScan();
        registerReceiver(wiFiBroadcastReceiver,mintentFilter);
    }
    class MyPagesAdpter extends FragmentStatePagerAdapter{

        public MyPagesAdpter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
//            MyBaseFragment myBaseFragment=getFragment(position);
            Fragment f=null;
            if (position==0)
                f=new MyMoiveFragment();
            if (position==1)
                f=MeiziFragment.newInstance("a","b");
            if(position==2)
                f= WifiItemFragment.newInstance(1);
            if(position==3){
                f= BrowserFragment.newInstance("web","view");
            }
            return f;
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Log.d(TAG, "getPageTitle: "+arrayList.get(position));
            return arrayList.get(position);
        }
    }
    private MyBaseFragment getFragment(int position){
        String s=arrayList.get(position);
        MyBaseFragment myBaseFragment=null;
        switch (s){
            case "meizi":
                myBaseFragment=new MoivelistFra();
                myBaseFragment.setArgs("s",R.layout.movielistview,this);
                break;
            case "japanese":
                myBaseFragment=new MoivelistFra();
                myBaseFragment.setArgs("s",R.layout.movielistview,this);
                break;
            case "moivelist":
                myBaseFragment=new MoivelistFra();
                myBaseFragment.setArgs("s",R.layout.movielistview,this);
                break;
            case "video":
                myBaseFragment=new MoivelistFra();
                myBaseFragment.setArgs("s",R.layout.movielistview,this);
                break;
            case "web":
                myBaseFragment=new MoivelistFra();
                myBaseFragment.setArgs("s",R.layout.movielistview,this);
                break;

        }
        return myBaseFragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolitem,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.backup:
                Toast.makeText(this,"You clicked the Backup!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit:
                finish();
                break;
        }
        return true;
    }

    class WiFiBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action =intent.getAction();
            if(action.equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)){
                List<ScanResult> list=wm.getScanResults();
                MyWifiInfo.oldlist.clear();
                MyWifiInfo.oldlist.addAll(MyWifiInfo.list);
                MyWifiInfo.list.clear();
                MyWifiInfo.list.addAll(list);
                WifiItemFragment wifiItemFragment= (WifiItemFragment) viewPager.getAdapter().instantiateItem(viewPager,2);

                if(isWifi){
                    if(wifiItemFragment.recyclerView==null)
                        Log.d(TAG, "onReceive: ..wifiItemFragment.recyclerView==null");
                    else{
                        DiffUtil.DiffResult result=DiffUtil.calculateDiff(new MyWifiInfo(),true);
                        result.dispatchUpdatesTo(wifiItemFragment.recyclerView.getAdapter());
                    }
//                        wifiItemFragment.recyclerView.getAdapter().notifyDataSetChanged();
                }




            }
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(wiFiBroadcastReceiver);
        super.onDestroy();
    }
}
