package com.zeus.hello.moiveapp.MyView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zeus.hello.moiveapp.R;

/**
 * Created by zhou on 2017/2/24.
 */

public abstract class MyBaseFragment extends Fragment {
    protected View view;
    protected String title;
    protected   int layout_id;
    protected Activity a;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view=inflater.inflate(layout_id,container,false);
        setComponets(view);
        return view;
    }
    public void setArgs(String title, int layout_id,Activity a){
        this.title=title;
        this.layout_id=layout_id;
        this.a=a;
    }

    public abstract void  setComponets(View rootview);



}
