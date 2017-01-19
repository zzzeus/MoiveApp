package com.zeus.hello.moiveapp.MyView;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import okhttp3.OkHttpClient;

public abstract class MyActivity extends AppCompatActivity {

    public final static OkHttpClient okHttpClient=new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
public Context getContext(){
    this.getLocalClassName();
    return this;
}

}
