package com.zeus.hello.moiveapp.MyUtil;

import okhttp3.OkHttpClient;

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
}
