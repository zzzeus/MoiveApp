package com.zeus.hello.moiveapp.MyUtil;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhou on 2017/3/15.
 */

public class DownloadTool {
    private static OkHttpClient okHttpClient;
    public static  String getText(String url){

        return "";
    }
    private static void check(){
        if (okHttpClient==null){
            okHttpClient=new OkHttpClient();
        }
    }
    public String reques(){
        String data=null;
        try {
            Request request=new Request.Builder().url("http://gaoqing.la/").build();
            Response response=okHttpClient.newCall(request).execute();
            data=response.body().string();
        }catch (Exception e){

        }
        return data;
    }
}
