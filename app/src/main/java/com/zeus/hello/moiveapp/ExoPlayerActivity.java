package com.zeus.hello.moiveapp;

import android.content.res.AssetManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;


import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ExoPlayerActivity extends AppCompatActivity {

    private static final String TAG="ExoPlayerActivity";
    private SimpleExoPlayerView simpleExoPlayerView;
    SimpleExoPlayer player;
    MediaSource mediaSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_player);

        simpleExoPlayerView= (SimpleExoPlayerView) findViewById(R.id.exo_player1);
        createPlayer();
    }
    private void createPlayer(){
        // 1. Create a default TrackSelector
//        Handler mainHandler = new Handler();
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

// 2. Create a default LoadControl
        LoadControl loadControl = new DefaultLoadControl();

// 3. Create the player
         player =
                ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl);

        simpleExoPlayerView.setPlayer(player);

        DataSource.Factory dataFactory=new DefaultDataSourceFactory(this,getApplication().toString());

        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

//        Uri uri;
//        uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.climb);
//        uri=Uri.parse("Android.resource://"+getPackageName()+"/raw/climb.mp3");
//        uri=Uri.parse("android.resource://com.zeus.hello.moiveapp/raw/climb");
//        uri=Uri.parse("Android.resource://"+getPackageName()+"/" +R.raw.climb);
//        uri=Uri.parse("Android.resource://" + getPackageName() + "/"+R.raw.climb);
//        uri=Uri.parse("file:///android_asset/climb.mp3");
//        uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.climb);



//        Log.d(TAG, "createPlayer: ..."+uri.toString());
//        mediaSource=new ExtractorMediaSource(
//                uri,
//                dataFactory,extractorsFactory,null,null
//                );
//
//        player.setPlayWhenReady(true);
//        Observable.create(new ObservableOnSubscribe<Boolean>() {
//
//            @Override
//            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
//                e.onNext(prepareforPlayer());
//            }
//        }).subscribeOn(Schedulers.newThread())
//        .subscribe();


    }

    private  boolean prepareforPlayer(){
//        Log.d(TAG, "prepareforPlayer: ......."+Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.climb));

        if (player!=null){
            player.prepare(mediaSource);
        }

        return true;
    }
    @Override
    protected void onDestroy() {
if (player!=null){
    player.release();
}
        super.onDestroy();
    }
}
