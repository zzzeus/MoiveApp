package com.zeus.hello.moiveapp;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhou on 2017/1/17.
 */

public class MoiveList {
    private static final String url="http://gaoqing.la/";
    private static final String TAG="MovieLists";
    public final static List<MyMovie> list=new ArrayList<MyMovie>();
    public final static List<MyMovie> oldlist=new ArrayList<MyMovie>();

    public static List<MyMovie> getMoives(){
        Log.d(TAG, "getMoives: start.....");
        String title;
        String link;
        String imageLink;
        String date;
        String info;
        oldlist.clear();
        oldlist.addAll(list);
        list.clear();
        try {
            Document doc = Jsoup.connect(url).userAgent("Mozilla").timeout(3000).get();

            Elements elements=doc.getElementsByClass("post_hover");
            Log.d(TAG, "getMoives: element:"+elements.isEmpty());
            for (Element big:elements) {
                Element a=big.getElementsByTag("a").first();
                title=a.attr("title");
                Log.d(TAG, "getMoives: 111111111111"+title);
                link=a.attr("href");
                imageLink=a.select("img").first().attr("src");
                Element element=big.getElementsByClass("info").first();
                date=  element.child(0).text();
                info=element.child(1).text();
                if (!title.isEmpty()){
list.add(new MyMovie(link,title,imageLink,date,info));
                }
            }
        } catch (IOException e) {

            Log.d(TAG, "getMoives: error:"+e.getLocalizedMessage());
        }finally {
            return list;
        }


    }
}
