package com.zeus.hello.moiveapp.MyService;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

import com.zeus.hello.moiveapp.R;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;

public class MyMusicService extends Service {
    private final static String TAG="MyMusicService";
    private static MediaPlayer mediaPlayer;
    private static  int m_id= R.raw.climb;
    public MyMusicService() {
    }

    private void init(){
        if (mediaPlayer!=null){
            mediaPlayer.release();

        }
//        mediaPlayer=MediaPlayer.create(getApplicationContext(),m_id);

         mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
                Log.d(TAG, "onPrepared: ....");
            }
        });
        try {
            mediaPlayer.setDataSource(this, Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.climb));
            Log.d(TAG, "init: .....init the source");

            mediaPlayer.prepare();
        } catch (IOException e) {

            Log.d(TAG, "init: "+e.getLocalizedMessage());
            Log.d(TAG, "init: failed to play !");
        }
//        Log.d(TAG, "init: start.....");
//        mediaPlayer.start();

    }
    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: MyMusicService is creating");
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ......");
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {

        Log.d(TAG, "onStartCommand: ......");
//        stopSelf();
//        init();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer = null;
        }

        Log.d(TAG, "onDestroy: ....");
        super.onDestroy();
    }
}
