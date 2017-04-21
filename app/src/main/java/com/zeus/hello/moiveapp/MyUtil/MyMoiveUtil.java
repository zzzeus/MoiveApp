package com.zeus.hello.moiveapp.MyUtil;

import android.support.v7.util.DiffUtil;

import com.zeus.hello.moiveapp.MoiveList;

/**
 * Created by zhou on 2017/4/19.
 */

public class MyMoiveUtil extends DiffUtil.Callback {

    @Override
    public int getOldListSize() {
        return MoiveList.oldlist.size();
    }

    @Override
    public int getNewListSize() {
        return MoiveList.list.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        String s=MoiveList.list.get(newItemPosition).title;
        String s1=MoiveList.oldlist.get(oldItemPosition).title;

        return s.equals(s1);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return true;
    }
}
