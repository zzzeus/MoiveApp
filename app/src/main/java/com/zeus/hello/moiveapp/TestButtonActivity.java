package com.zeus.hello.moiveapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class TestButtonActivity extends AppCompatActivity {
private final static String TAG="TESTButton";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_button);
    }
    public void  launchActivity(View view){
        Log.d(TAG, "launchActivity: "+view.getId()+"..........");
        switch (view.getId()){

            case R.id.launchnew:
                startActivity(new Intent(TestButtonActivity.this,NewActivity.class));
                break;
            case R.id.launchAnimation:
                startActivity(new Intent(TestButtonActivity.this,LaunchAnimationActivity.class));
                break;
            case R.id.launchViewPager:
                startActivity(new Intent(TestButtonActivity.this,ViewpagerActivity.class));
                break;
            case R.id.launch2048:
                startActivity(new Intent(TestButtonActivity.this,Game2048Activity.class));
                break;
            case R.id.launchpages:
                startActivity(new Intent(TestButtonActivity.this,Main2Activity.class));
                break;
        }
    }
}
