package com.zeus.hello.moiveapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import butterknife.ButterKnife;

public class WifiActivity extends AppCompatActivity {
    public final static String TAG="WifiActivity";
    private final IntentFilter mIntentFilter = new IntentFilter();
    WifiP2pManager mManager;
    WifiP2pManager.Channel mChannel;
    private List<WifiP2pDevice> peers = new ArrayList<WifiP2pDevice>();
    private TextView textView;
    private Button button;
    private WiFiDirectBroadcastReceiver mReceiver;
    WifiManager wm;
    private WifiP2pManager.PeerListListener peerListListener = new WifiP2pManager.PeerListListener() {
        @Override
        public void onPeersAvailable(WifiP2pDeviceList peerList) {
            Log.d(TAG, "onPeersAvailable: "+peerList.getDeviceList().isEmpty());
            Collection<WifiP2pDevice> refreshedPeers =  peerList.getDeviceList();
            Toast.makeText(WifiActivity.this, "start:isEmpty:"+refreshedPeers.isEmpty(), Toast.LENGTH_SHORT).show();
            if (!refreshedPeers.equals(peers)) {
                peers.clear();
                peers.addAll(refreshedPeers);

                // If an AdapterView is backed by this data, notify it
                // of the change.  For instance, if you have a ListView of
                // available peers, trigger an update.
//                ((WiFiPeerListAdapter) getListAdapter()).notifyDataSetChanged();

                // Perform any other updates needed based on the new list of
                // peers connected to the Wi-Fi P2P network.
            }

            if (peers.size() == 0) {
                Log.d(TAG, "No devices found..");
                Toast.makeText(WifiActivity.this, "No devices found..", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);

        textView= (TextView) findViewById(R.id.wifi_info);
        button= (Button) findViewById(R.id.wifi_button);
        //  Indicates a change in the Wi-Fi P2P status.
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);

        // Indicates a change in the list of available peers.
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);

        // Indicates the state of Wi-Fi P2P connectivity has changed.
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);

        // Indicates this device's details have changed.
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        mIntentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel = mManager.initialize(this, getMainLooper(), null);
        mReceiver = new WiFiDirectBroadcastReceiver(mManager, mChannel, this);
        mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {

            @Override
            public void onSuccess() {
                // Code for when the discovery initiation is successful goes here.
                // No services have actually been discovered yet, so this method
                // can often be left blank.  Code for peer discovery goes in the
                // onReceive method, detailed below.
                Log.d(TAG, "onSuccess: discover peer");
            }

            @Override
            public void onFailure(int reasonCode) {
                // Code for when the discovery initiation fails goes here.
                // Alert the user that something went wrong.
                Log.d(TAG, "onFailure: discover peer");
            }
        });

        wm= (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wm.startScan();
    }
    class WiFiDirectBroadcastReceiver extends BroadcastReceiver {

        private WifiP2pManager mManager;
        private WifiP2pManager.Channel mChannel;
        private WifiActivity mActivity;

        public WiFiDirectBroadcastReceiver(WifiP2pManager manager, WifiP2pManager.Channel channel,
                                           WifiActivity activity) {
            super();
            this.mManager = manager;
            this.mChannel = channel;
            this.mActivity = activity;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
//            doit();
            wm.getScanResults();
            String action = intent.getAction();
            Log.d(TAG, "onReceive: availble :receive some thing:"+action);
            if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
                // Check to see if Wi-Fi is enabled and notify appropriate activity
                int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
                if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                    // Wifi P2P is enabled
                    Log.d(TAG, "onReceive: wifi is up");
                } else {
                    // Wi-Fi P2P is not enabled
                    Log.d(TAG, "onReceive: wifi is closed");
                }
            } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
                // Call WifiP2pManager.requestPeers() to get a list of current peers
//                doit();
            } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
                // Respond to new connection or disconnections
            } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
                // Respond to this device's wifi state changing
            }else if(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(action)){
                List<ScanResult> results=wm.getScanResults();
                textView.setText("results: num is\n"+results.size());
                for (ScanResult s : results){
                    textView.setText(textView.getText()+"BSSID:  "+s.BSSID+"\n");
                    textView.setText(textView.getText()+"SSID:  "+s.SSID+"\n");
                    textView.setText(textView.getText()+"describeContents: "+s.describeContents()+"\n");
                    textView.setText(textView.getText()+"\n");
                    textView.setMovementMethod(new ScrollingMovementMethod());


                }

            }
        }
    }

    public void doit(){
        Log.d(TAG, "doit: it is doing!");
        if (mManager != null) {
            mManager.requestPeers(mChannel, new WifiP2pManager.PeerListListener() {
                @Override
                public void onPeersAvailable(WifiP2pDeviceList peers) {
                    Log.d(TAG, "onPeersAvailable: finished.isEmpty"+peers.getDeviceList().size());
//                    Log.d(TAG, "onPeersAvailable: ");
                    for (WifiP2pDevice w :peers.getDeviceList()){
                        Log.d(TAG, "onPeersAvailable: "+w.deviceName);
                    }
                }
            });
        }
    }
public void achieveinfo(View view){

}
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mIntentFilter);
//        wbr = new WifiBroadcastReceiver(mManager, mChannel, this);
//        registerReceiver(wbr, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        unregisterReceiver(wbr);
        unregisterReceiver(mReceiver);
    }
}
