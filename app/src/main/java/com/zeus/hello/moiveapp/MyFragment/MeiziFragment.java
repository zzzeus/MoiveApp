package com.zeus.hello.moiveapp.MyFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zeus.hello.moiveapp.MyUtil.Meizi;
import com.zeus.hello.moiveapp.MyUtil.MeiziList;
import com.zeus.hello.moiveapp.R;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MeiziFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MeiziFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeiziFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Context mContext;
    private OnFragmentInteractionListener mListener;
private View rootView;
    private RecyclerView recyclerView;
    private final static  String TAG="MeiziFragment";
    public MeiziFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MeiziFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MeiziFragment newInstance(String param1, String param2) {
        MeiziFragment fragment = new MeiziFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mContext==null){
            mContext=getContext();
        }
        rootView=inflater.inflate(R.layout.fragment_meizi, container, false);
        recyclerView= (RecyclerView) rootView.findViewById(R.id.fra_meizi_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
        recyclerView.setAdapter(new MyMeiziAdpter());
        Log.d(TAG, "onCreateView: To refresh");
        refresh();
        return rootView;
    }
private void  refresh(){
    Log.d(TAG, "refresh: start refresh....");
    Observable.create(new ObservableOnSubscribe<Boolean>() {
        @Override
        public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
            MeiziList.getPageInfo();
            e.onNext(true);
            Log.d(TAG, "subscribe: onnext....");
            
        }
    }).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe(new Observer<Boolean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Boolean value) {
                    recyclerView.getAdapter().notifyDataSetChanged();
                    Log.d(TAG, "onNext: end to refresh.....");
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
}
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    class MyMeiziAdpter extends  RecyclerView.Adapter
    {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (mContext==null){
                mContext=parent.getContext();
            }
            View view= LayoutInflater.from(mContext).inflate(R.layout.recycler1_view,parent,false);
            final RecyclerView.ViewHolder myHolder= new  ViewHolder(view);

            return myHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ViewHolder viewHolder= (ViewHolder) holder;
            Meizi meizi=MeiziList.list.get(position);
            Glide.with(mContext).load(meizi.imageUrl).into(viewHolder.imageView);
            viewHolder.time.setText(meizi.time);
            viewHolder.text.setText(meizi.tag);
        }

        @Override
        public int getItemCount() {
            return MeiziList.list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView text;
            TextView time;
            public ViewHolder(View itemView) {
                super(itemView);
                imageView= (ImageView) itemView.findViewById(R.id.recycler_image);
                text= (TextView) itemView.findViewById(R.id.recycler_info);
                time= (TextView) itemView.findViewById(R.id.recycler_text);
            }
        }
    }
}
