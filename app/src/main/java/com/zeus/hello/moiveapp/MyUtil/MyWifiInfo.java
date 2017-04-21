package com.zeus.hello.moiveapp.MyUtil;

import android.net.wifi.ScanResult;
import android.support.v7.util.DiffUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhou on 2017/4/16.
 */

public class MyWifiInfo extends DiffUtil.Callback {

    public final static List<ScanResult> oldlist=new ArrayList<ScanResult>();
    public final static List<ScanResult> list=new ArrayList<ScanResult>();

    @Override
    public int getOldListSize() {
        return oldlist.size();
    }

    @Override
    public int getNewListSize() {
        return list.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldlist.get(oldItemPosition).SSID.equals(list.get(newItemPosition).SSID);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldlist.get(oldItemPosition).BSSID.equals(list.get(newItemPosition).BSSID);
    }
}
