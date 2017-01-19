package com.zeus.hello.moiveapp.MyView;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zeus.hello.moiveapp.Main2Activity;
import com.zeus.hello.moiveapp.MyUtil.ParseWord;
import com.zeus.hello.moiveapp.MyUtil.StringUtil;
import com.zeus.hello.moiveapp.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by zhou on 2017/2/24.
 */

public class MyDictoryFragment extends Fragment {
    private final  String TAG="DictoryFragment";
    private final  String baseURL="http://dict.hjenglish.com/services/huaci/jp_web_ajax.ashx?type=jc";
    private TextView textView;
    private EditText editText;
    private Button button;
private Bundle b;

    public void setActivity(MyActivity activity) {
        this.activity = activity;
    }

    private static MyActivity activity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.dic_main,container,false);
        textView= (TextView) rootView.findViewById(R.id.dic_text);
        editText= (EditText) rootView.findViewById(R.id.dic_edit);
        button= (Button) rootView.findViewById(R.id.dic_put);
        Log.d(TAG, "onCreateView: .....threadId"+Thread.currentThread().getId());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String text=editText.getText().toString();
                get_info(text);
                    }


            });
        b=getArguments();
if (b!=null)
    get_info(b.getString(Main2Activity.TAG));
        return rootView;
    }

public void get_info(String t){

final String text=t;
    OkHttpClient client=activity.okHttpClient;

    try {
        HttpUrl httpUrl=HttpUrl.parse(baseURL).newBuilder().addQueryParameter("w",text).build();
//                            String user_agent="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";
        Request request=new Request.Builder().url(httpUrl)
//                                    .addHeader("Accept","*/*")
//                                    .addHeader("Content-Type","application/x-javascript; charset=utf-8")
//                                    .addHeader("charset","utf-8")
//                                    .addHeader("ccept-Language","zh-CN,zh;q=0.8")
                .build();
//                            Content-Type:application/x-javascript; charset=utf-8

        Log.d(TAG, "onClick: ....url:"+request.url().url().toString());
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response)  {
//                                    textView.setText(response.body().string());
//                                    Log.d(TAG, "onResponse: ....threadId"+Thread.currentThread().getId());
                String set=response.header("Content-Type");
                Log.d(TAG, "onResponse: charset:"+set);
                try (ResponseBody responseBody = response.body()){
                    if (responseBody!=null) {
                        String body= StringUtil.decode(responseBody.string());

//                                            body=new String(body.getBytes(),"utf-8");

                        final String sign = text + "\n" +body.substring( body.indexOf("<"),body.lastIndexOf(">")+1);
                        if(MyDictoryFragment.activity!=null)
                            MyDictoryFragment.activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
//                                                textView.setText(Html.fromHtml(sign));
                                    textView.setText(sign);
                                }

                            });
                        else
                        {
                            Log.d(TAG, "onResponse: ...activity is null");
                        }
                    }else {
                        Log.d(TAG, "onResponse: "+"nullpoint");
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        });
//        Log.d(TAG, "onClick: "+text+"...."+httpUrl);
//                            http://dict.hjenglish.com/services/huaci/jp_web_ajax.ashx?type=jc&w=%E5%BF%83%E6%B8%A9%E3%81%BE%E3%82%8B

    }catch (Exception e){
        Log.e(TAG, "onClick: ......Error in url");
    }

}
    private void dealwith(String text){
        String s=ParseWord.achieve(text);
        Log.d(TAG, "dealwith: num of result:"+s);
    }
}
