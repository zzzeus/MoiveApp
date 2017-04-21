package com.zeus.hello.moiveapp.MyFragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zeus.hello.moiveapp.MyService.WifiService;
import com.zeus.hello.moiveapp.MyUtil.MyWifiInfo;
import com.zeus.hello.moiveapp.PagesActivity;
import com.zeus.hello.moiveapp.R;
import com.zeus.hello.moiveapp.MyFragment.dummy.DummyContent;
import com.zeus.hello.moiveapp.MyFragment.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class WifiItemFragment extends Fragment {

    private final static String TAG="WifiItemFragment";
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    public RecyclerView recyclerView;

    private View view;
    private OnListFragmentInteractionListener mListener;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public WifiItemFragment() {
        if(getActivity()==null){
            Log.d(TAG, "WifiItemFragment: wm is null");
        }
        PagesActivity.activity.isWifi=true;



    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static WifiItemFragment newInstance(int columnCount) {
        WifiItemFragment fragment = new WifiItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_item_list, container, false);

        setRecycler();

        return view;
    }

public void setRecycler(){
    // Set the adapter
    if (view instanceof RecyclerView) {
        Context context = view.getContext();
        recyclerView = (RecyclerView) view;
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        List<DummyItem> items=DummyContent.getItems();
        MyItemRecyclerViewAdapter m=new MyItemRecyclerViewAdapter(items, mListener);
        recyclerView.setAdapter(m);

    }
}
public void setAdapter(){
    MyItemRecyclerViewAdapter m=new MyItemRecyclerViewAdapter(DummyContent.ITEMS, mListener);
    recyclerView.setAdapter(m);
}
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }


}
