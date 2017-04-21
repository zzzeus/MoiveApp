package com.zeus.hello.moiveapp.MyService;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.support.annotation.IntDef;

import com.zeus.hello.moiveapp.MyUtil.MyWifiInfo;

import java.util.List;

public class WifiService extends Service {
    WifiManager wm;
    WiFiBroadcastReceiver wiFiBroadcastReceiver;
    public WifiService() {
       wm= (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wm.startScan();
        mintentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        mintentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        wiFiBroadcastReceiver=new WiFiBroadcastReceiver();
        registerReceiver(wiFiBroadcastReceiver,mintentFilter);
    }
private final IntentFilter mintentFilter=new IntentFilter();
    @Override
    public void onCreate() {
        super.onCreate();



    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
    class WiFiBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action =intent.getAction();
            if(action.equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)){
                List<ScanResult> list=wm.getScanResults();
                MyWifiInfo.list.addAll(list);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(wiFiBroadcastReceiver);
    }
}
