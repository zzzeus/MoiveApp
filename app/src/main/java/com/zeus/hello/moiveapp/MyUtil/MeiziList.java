package com.zeus.hello.moiveapp.MyUtil;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhou on 2017/4/19.
 */

public class MeiziList {
    private final static String pageUrl="http://www.mzitu.com/zipai/";
    private final static  String TAG="MeiziList";
    private static String previousUrl="0";
    private static int num=0;
    public final static List<Meizi> list=new ArrayList<Meizi>();
    public static void getPageInfo(){
        Log.d(TAG, "getPageInfo: start get,num:"+num);
        num++;
        if (num>4)
            return;
        String url;
        if (previousUrl.equals("0")){
            list.clear();
            url=pageUrl;
        }else {
            url=previousUrl;
        }

        try {
            Document doc = Jsoup.connect(url).userAgent("Mozilla").timeout(3000).get();
            Element div=doc.getElementById("comments");

            Elements lus=div.getElementsByTag("ul");
            Log.d(TAG, "getPageInfo: ...num:"+lus.size());
            for (Element ul:lus) {
                for (Element li:ul.getElementsByTag("li")) {
//                    Log.d(TAG, "getPageInfo: li num:");
                    Element img=li.getElementsByTag("img").first();
                    Element time=li.getElementsByTag("a").first();
                    Meizi m=new Meizi("自拍",img.attr("src"),time.ownText(),"自拍");
//                    Log.d(TAG, "getPageInfo: img:"+time.ownText());
                    list.add(m);
                }
            }
            Element nav=div.getElementsByClass("pagenavi-cm").get(0);
            Element previous=nav.getElementsByTag("a").last();
            if (previous.ownText().equals("下一页 »")){
                previousUrl=previous.attr("href");
                Log.d(TAG, "getPageInfo: going to next...");
                getPageInfo();
            }else {
                previousUrl="0";
            }
            
        } catch (IOException e) {
//            Log.d(TAG, "getPageInfo:  A exception happened");
            Log.d(TAG, "getPageInfo: exception:"+e.getLocalizedMessage());
        }
    }
}
