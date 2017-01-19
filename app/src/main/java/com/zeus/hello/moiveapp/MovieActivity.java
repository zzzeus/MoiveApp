package com.zeus.hello.moiveapp;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieActivity extends AppCompatActivity {

    public static final String TAG="Movie";
    MyMovie myMovie;
    @BindView(R.id.movie_tool)Toolbar toolbar;
    @BindView(R.id.collapsing)CollapsingToolbarLayout collapsing;
    @BindView(R.id.movie_barimage)ImageView barimage;
    @BindView(R.id.movie_text)TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
    ButterKnife.bind(this);
        Intent intent=getIntent();
        int position=intent.getIntExtra(TAG,0);
        myMovie=MoiveList.list.get(position);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsing.setTitle(myMovie.title);
        Glide.with(MovieActivity.this).load(myMovie.imageLink).into(barimage);

achieveContent();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void achieveContent(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                StringBuilder s=new StringBuilder();
                Document doc= null;
                try {
                    Log.d(TAG, "run:link ......"+myMovie.link);
                    doc = Jsoup.connect(myMovie.link).userAgent("Mozilla").get();


//                showContent(doc.text());
                Elements elements=doc.select("span");
//                Log.d(TAG, "run: ..11."+elements.html());

                for (Element e:elements){
                    if (e.attr("style").equals("color: #000000;")){
                        Log.d(TAG, "run: 22222"+e.text());
                        s.append(e.text());
                        s.append("\n");
                    }
                    Log.d(TAG, "run: ....."+e.attr("style"));

//                    Log.d(TAG, "onCreate: ..."+e.text()+"11111");
                }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                showContent(s.toString());
            }
        }).start();
    }
    private void showContent(final String content){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(content+"......");
            }
        });
    }
}
