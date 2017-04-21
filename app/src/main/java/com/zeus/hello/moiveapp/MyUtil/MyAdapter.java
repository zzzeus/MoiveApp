package com.zeus.hello.moiveapp.MyUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zeus.hello.moiveapp.MainActivity;
import com.zeus.hello.moiveapp.MoiveList;
import com.zeus.hello.moiveapp.MovieActivity;
import com.zeus.hello.moiveapp.MyMovie;
import com.zeus.hello.moiveapp.R;

import java.util.List;

/**
 * Created by zhou on 2017/3/27.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{

    Context a;

    private Context mContext;

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.recycler1_view,parent,false);
        final MyHolder myHolder= new  MyHolder(view);
        myHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=myHolder.getAdapterPosition();
                Intent intent=new Intent(mContext,MovieActivity.class);
                intent.putExtra(MovieActivity.TAG,position);
                mContext.startActivity(intent);
            }
        });
        return myHolder;
    }
    public  MyAdapter(Context a){

        this.a=a;
    }
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        MyMovie myMovie= MoiveList.list.get(position);
        holder.title.setText(myMovie.title);
        holder.info.setText("Data:"+myMovie.date+"\n清晰度："+myMovie.info);
        Glide.with(a).load(myMovie.imageLink).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return MoiveList.list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView info;
        TextView title;
        ImageView imageView;
        public MyHolder(View itemView) {
            super(itemView);
            cardView= (CardView) itemView;
            imageView= (ImageView) itemView.findViewById(R.id.recycler_image);
            info= (TextView) itemView.findViewById(R.id.recycler_info);
            title= (TextView) itemView.findViewById(R.id.recycler_text);
        }
    }
}
