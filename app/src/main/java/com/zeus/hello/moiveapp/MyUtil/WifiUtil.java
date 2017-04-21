package com.zeus.hello.moiveapp.MyUtil;

import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;

/**
 * Created by zhou on 2017/4/6.
 */

public class WifiUtil {
    private static final IntentFilter intentFilter = new IntentFilter();
    public static void listen(){
        //  Indicates a change in the Wi-Fi P2P status.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);

        // Indicates a change in the list of available peers.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);

        // Indicates the state of Wi-Fi P2P connectivity has changed.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);

        // Indicates this device's details have changed.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
    }
}
