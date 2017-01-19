package com.zeus.hello.moiveapp.MyUtil;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zeus.hello.moiveapp.R;
import com.zeus.hello.moiveapp.ViewpagerActivity;

/**
 * Created by zhou on 2017/2/2.
 */

public class ImageDetailFragment extends Fragment {
    public static final String IMAGE_DATA_EXTRA="resID";
    private int mImageNum;
    private ImageView mImageView;
    public static ImageDetailFragment newInstance(int imageNum){
        final ImageDetailFragment f=new ImageDetailFragment();
        final Bundle args=new Bundle();
        args.putInt(IMAGE_DATA_EXTRA,imageNum);
        f.setArguments(args);
        return f;
    }
    public ImageDetailFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageNum=getArguments()!=null?getArguments().getInt(IMAGE_DATA_EXTRA):-1;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.item_imageitem,container,false);
        Bundle args=getArguments();
        ImageView i= (ImageView) rootView.findViewById(R.id.imageitem1);
        i.setImageResource(args.getInt(IMAGE_DATA_EXTRA));

        return rootView;
    }


}
