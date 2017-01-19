package com.zeus.hello.moiveapp;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v4.util.TimeUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AnimationSet;
import android.widget.ImageView;


import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.subscribers.LambdaSubscriber;
import io.reactivex.schedulers.Schedulers;


public class LaunchAnimationActivity extends AppCompatActivity {

    @BindView(R.id.start_image)
    ImageView imageView;
    private static final int ANIMATION_TIME=2000;
    private static final float END_ANIMATION=1.2f;
    private Disposable flowable;
    public static final  String TAG="Launch Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_animation);
        ButterKnife.bind(this);

        flowable=  Flowable.timer(2000,TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) {
                        runAnimation();
                    }
                });

    }
    public void  runAnimation(){
        ObjectAnimator objectAnimatorX=ObjectAnimator.ofFloat(imageView,"scaleX",1f,END_ANIMATION);
        ObjectAnimator objectAnimatorY=ObjectAnimator.ofFloat(imageView,"scaleY",1f,END_ANIMATION);

        AnimatorSet set=new AnimatorSet();
        set.setDuration(ANIMATION_TIME).play(objectAnimatorX).with(objectAnimatorY);
        set.start();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                LaunchAnimationActivity.this.finish();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }
}
