package com.zeus.hello.moiveapp.MyFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zeus.hello.moiveapp.MoiveList;
import com.zeus.hello.moiveapp.MyUtil.MyAdapter;
import com.zeus.hello.moiveapp.MyUtil.MyMoiveUtil;
import com.zeus.hello.moiveapp.PagesActivity;
import com.zeus.hello.moiveapp.R;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zhou on 2017/4/11.
 */

public class MyMoiveFragment extends Fragment {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipe;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate( R.layout.recycler_fragment,container,false);
        swipe= (SwipeRefreshLayout) rootView.findViewById(R.id.universial_swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                    refresh();
            }
        });

        recyclerView= (RecyclerView) rootView.findViewById(R.id.universal_recycler);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(new MyAdapter(getContext()));
        refresh();
        return rootView;
    }
    private void refresh(){
        Observable.create(new ObservableOnSubscribe<List>() {
            @Override
            public void subscribe(ObservableEmitter<List> e) throws Exception {
                e.onNext(MoiveList.getMoives());
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List value) {
                        swipe.setRefreshing(false);
                        if(value.size()==0){
                            Toast.makeText(PagesActivity.activity, "Failed to get the moivelist", Toast.LENGTH_SHORT).show();
                            return;
                        }
//                                recyclerView.getAdapter().notifyDataSetChanged();
                        DiffUtil.DiffResult result=DiffUtil.calculateDiff(new MyMoiveUtil(),true);
                        result.dispatchUpdatesTo(recyclerView.getAdapter());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
