package com.zeus.hello.moiveapp.MyView;

import android.graphics.Movie;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zeus.hello.moiveapp.MainActivity;
import com.zeus.hello.moiveapp.MoiveList;
import com.zeus.hello.moiveapp.MyMovie;
import com.zeus.hello.moiveapp.MyUtil.MyAdapter;
import com.zeus.hello.moiveapp.R;

import org.reactivestreams.Publisher;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zhou on 2017/3/27.
 */

public class MoivelistFra extends MyBaseFragment {
    private static String TAG="moive_in_pages";
//    private final CompositeDisposable disposables = new CompositeDisposable();
    private RecyclerView recyclerView;



    @Override
    public void setComponets(final View rootview) {
//        disposables.add( Observable.just(MoiveList.getMoives())
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(new DisposableObserver<List>(){
//
//                    @Override
//                    public void onNext(List value) {
//                        Log.d(TAG, "onNext: start.......");
//                        show(rootview,value);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "onError: start.........");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "onComplete: start........");
//                    }
//                }));
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
                        show(rootview,value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        if (null== title)
        {
            Log.d(TAG, "setComponets: failed..........");
            return;
        }
        RecyclerView r= (RecyclerView) rootview.findViewById(R.id.movielistview_recycler);


    }
    private  void show(View rootview,List value){
        recyclerView= (RecyclerView) rootview.findViewById(R.id.myrecycler);
        GridLayoutManager layoutManager= new GridLayoutManager(a,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new MyAdapter(a));
    }
    public void dispose(){
//        disposables.clear();
    }
}
