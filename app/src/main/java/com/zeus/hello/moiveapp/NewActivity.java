package com.zeus.hello.moiveapp;


import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.bumptech.glide.load.engine.Resource;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class NewActivity extends AppCompatActivity {
    private final static String TAG="NewActivity";
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        Uri uri;

        uri=Uri.parse("android.resource://"+getPackageName()+"/raw/a");
//        File f=new File("android.resource://"+getPackageName()+"/raw/climb");
//        if(f.exists())
//            Log.d(TAG, "onCreate: ...........success111");
//        else
//            Log.d(TAG, "onCreate: ..........failed1111");
        Log.d(TAG, "onCreate: ..111uri:"+uri.toString());

        Log.d(TAG, "onCreate: uripath:"+uri.getEncodedPath());
        textView= (TextView) findViewById(R.id.new_text);
        InputStream inputStream=null;
        try {
            inputStream= getResources().openRawResource(R.raw.a);
            InputStreamReader isr=new InputStreamReader(inputStream,"UTF-8" );
            BufferedReader bw=new BufferedReader(isr);
            String s=bw.readLine();
            Log.d(TAG, "onCreate: ...str:"+s);
            textView.setText(s);

            bw.close();
            Log.d(TAG, "onCreate: ...........sucess2222");
        }catch (Exception e) {
            Log.d(TAG, "onCreate: .........failed to open");
        }


    }
}
