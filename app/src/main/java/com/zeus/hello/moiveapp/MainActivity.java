package com.zeus.hello.moiveapp;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
//        Log.d(TAG, "onCreateOptionsMenu: .....menu find "+menu.findItem(R.id.search));
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        if(searchView!=null)
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(new ComponentName(getApplicationContext(), Main2Activity.class)));
        else {
//            Log.d(TAG, "onCreateOptionsMenu: .....searchview is null");
        }

        Log.d(TAG, "onCreateOptionsMenu: SearchInfo:"+searchManager.getSearchableInfo(getComponentName()));

//        MenuItem searchItem = menu.findItem(R.id.search);
//        SearchView searchView =
//                (SearchView) MenuItemCompat.getActionView(searchItem);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        Log.d(TAG, "onOptionsItemSelected: 111"+item.getItemId()+"...."+R.id.home);
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
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{
        private Context mContext;
        private List<MyMovie> movieList;
        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (mContext==null){
                mContext=parent.getContext();
            }
            View view= LayoutInflater.from(mContext).inflate(R.layout.recycler1_view,parent,false);
            final MyHolder myHolder= new  MyHolder(view);
            myHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=myHolder.getAdapterPosition();
                    Intent intent=new Intent(mContext,MovieActivity.class);
                    intent.putExtra(MovieActivity.TAG,position);
                    mContext.startActivity(intent);
                }
            });
            return myHolder;
        }
        public  MyAdapter(List<MyMovie> list){
            movieList=list;
        }
        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
                MyMovie myMovie=movieList.get(position);
            holder.title.setText(myMovie.title);
            holder.info.setText("Data:"+myMovie.date+"\n清晰度："+myMovie.info);
            Glide.with(MainActivity.this).load(myMovie.imageLink).into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyHolder extends RecyclerView.ViewHolder{
            CardView cardView;
            TextView info;
            TextView title;
            ImageView imageView;
            public MyHolder(View itemView) {
                super(itemView);
                cardView= (CardView) itemView;
                imageView= (ImageView) itemView.findViewById(R.id.recycler_image);
                info= (TextView) itemView.findViewById(R.id.recycler_info);
                title= (TextView) itemView.findViewById(R.id.recycler_text);
            }
        }
    }

//    private void send(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                try {
//                    OkHttpClient client=new OkHttpClient();
//                    Request request=new Request.Builder().url("http://gaoqing.la/").build();
//                    Response response=client.newCall(request).execute();
//                    String data=response.body().string();
//                    show(data);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }).start();
//    }

    private void getMoives(){
        new Thread(new Runnable() {
            @Override
            public void run() {
               list= MoiveList.getMoives();


                show();
//                Log.d(TAG, "run: 11111");
            }
        }).start();
    }
    private void show(){

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
    //                Log.d(TAG, "run: show111111");
                    if (list!=null)
                    {
                            recyclerView= (RecyclerView) findViewById(R.id.myrecycler);
                            GridLayoutManager layoutManager= new GridLayoutManager(MainActivity.this,2);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(new MyAdapter(list));
                    }

                    else
                    {
                        Log.d(TAG, "show: Failed................");
                        Toast.makeText(MainActivity.this,"Failed to get the content!",Toast.LENGTH_LONG).show();
                    }
                    swipeRefreshLayout.setRefreshing(false);
                }
            });

           
    }

    @Override
    protected void onDestroy() {
//        if (mediaPlayer!=null)
//        {
//            mediaPlayer.release();
//            mediaPlayer=null;
//        }
        super.onDestroy();
    }
}
