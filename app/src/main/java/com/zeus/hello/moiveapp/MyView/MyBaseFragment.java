package com.zeus.hello.moiveapp.MyView;

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
    private View view;
    private String title;
    private  int layout_id;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view=inflater.inflate(layout_id,container,false);
        setComponets();
        return view;
    }
    public void setArgs(String title, int layout_id){
        this.title=title;
        this.layout_id=layout_id;
    }
//    public void setTitles(String s){
//        title=s;
//    }
//    public void setLayout_id(int i){
//        layout_id=i;
//    }
    public abstract void  setComponets();
//        switch (d){
//            case 1:
//                break;
//            case 2:
//                break;
//            case 3:
//                break;
//            case 4:
//                break;
//        }


}
