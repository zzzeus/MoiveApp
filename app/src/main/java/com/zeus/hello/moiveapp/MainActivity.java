package com.zeus.hello.moiveapp;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zeus.hello.moiveapp.MyService.MyMusicService;
import com.zeus.hello.moiveapp.MyUtil.MyAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="MainActivity";
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private List<MyMovie> list;
    private SwipeRefreshLayout swipeRefreshLayout;

//    private final CompositeDisposable disposables = new CompositeDisposable();
private Activity a;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a=this;

        toolbar= (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        drawerLayout= (DrawerLayout) findViewById(R.id.activity_main);
        navigationView= (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.store);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()){
                    case R.id.mail:
                        MainActivity.this.startActivity(new Intent(MainActivity.this,TestButtonActivity.class));
                        break;
                    case R.id.setting:
                        MainActivity.this.startActivity(new Intent(MainActivity.this,StartActivity.class));
                        break;
                    case R.id.store:
                        break;

                }
                return true;
            }
        });
swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.main_swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMoives();

            }
        });
//        send();
//        Log.d(TAG, "onCreate: 1111111111");
            getMoives();
//        mediaPlayer=MediaPlayer.create(this,R.raw.climb);
//        mediaPlayer.start();
        startService(new Intent(this, MyMusicService.class));

    }

    private void createNotify(){
        android.support.v4.app.NotificationCompat.Builder builder= new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.setting)
                .setContentTitle("wifi info")
                .setContentText("the wifi")
                .setAutoCancel(true);
        Intent intent=new Intent(this,WifiActivity.class);
        TaskStackBuilder stackBuilder=TaskStackBuilder.create(this);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent=stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(10, builder.build());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
//        drawerLayout.closeDrawers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolitem,menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        if(searchView!=null)
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(new ComponentName(getApplicationContext(), Main2Activity.class)));
        else {

        }

        Log.d(TAG, "onCreateOptionsMenu: SearchInfo:"+searchManager.getSearchableInfo(getComponentName()));


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
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
            default:break;
        }
        return true;
    }

private void getMoives(){

    Observable.create(new ObservableOnSubscribe<List>() {
        @Override
        public void subscribe(ObservableEmitter<List> e) throws Exception {
            e.onNext(MoiveList.getMoives());
        }
    }).subscribeOn(Schedulers.newThread())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe(new Observer<List>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(List value) {
            swipeRefreshLayout.setRefreshing(false);
            show(value);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    });
}
    private  void show(List value){
//        Log.d(TAG, "show: ..................111"+list.get(1).getMovieName());
        if (value.isEmpty())
        {
            Toast.makeText(this, "Failed to get the info", Toast.LENGTH_SHORT).show();
        }
        recyclerView= (RecyclerView) findViewById(R.id.myrecycler);
        GridLayoutManager layoutManager= new GridLayoutManager(a,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new MyAdapter(a));
    }


//    private void show(){
//
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//
//                    if (list!=null)
//                    {
//                            recyclerView= (RecyclerView) findViewById(R.id.myrecycler);
//                            GridLayoutManager layoutManager= new GridLayoutManager(MainActivity.this,2);
//                            recyclerView.setLayoutManager(layoutManager);
//                            recyclerView.setAdapter(new MyAdapter(list,MainActivity.this));
//                    }
//
//                    else
//                    {
//                        Log.d(TAG, "show: Failed................");
//                        Toast.makeText(MainActivity.this,"Failed to get the content!",Toast.LENGTH_LONG).show();
//                    }
//                    swipeRefreshLayout.setRefreshing(false);
//                }
//            });
//
//
//    }

    @Override
    protected void onDestroy() {
//        if (mediaPlayer!=null)
//        {
//            mediaPlayer.release();
//            mediaPlayer=null;
//        }
//        disposables.clear();
        super.onDestroy();
    }

}

