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
    static List<MyMovie> list;

//    public static   String getUrlContent(){
//        new  Thread(new Runnable() {
//            @Override
//            public void run() {
//                String data=null;
//                try {
//                    OkHttpClient okHttpClient=new OkHttpClient();
//                    Request request=new Request.Builder().url("http://www.baidu.com").build();
//                    Response response=okHttpClient.newCall(request).execute();
//                    data=response.body().string();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//        return data;
//    }
//    public static List<MyMovie> getMioves(String data){
//        try {
//            XmlPullParserFactory xmlPullParserFactory=XmlPullParserFactory.newInstance();
//            XmlPullParser xmlPullParser=xmlPullParserFactory.newPullParser();
//            xmlPullParser.setInput(new StringReader(data));
//            int eventType=xmlPullParser.getEventType();
//            String id="";
//            String  name="";
//            String version="";
//            while (eventType!=XmlPullParser.END_DOCUMENT){
//                String nodename=xmlPullParser.getName();
//
//                if (nodename=="div"&&xmlPullParser.getAttributeValue(0)=="post_hover"){
//                    xmlPullParser.getAttributeValue(0)
//
//                }
//            }
//        } catch (XmlPullParserException e) {
//            e.printStackTrace();
//        }
//
//        return
//    }
    public static List<MyMovie> getMoives(){
        String title;
        String link;
        String imageLink;
        String date;
        String info;
        list=new  ArrayList<MyMovie>();
        try {
            Document doc = Jsoup.connect(url).userAgent("Mozilla").timeout(3000).get();

            Elements elements=doc.getElementsByClass("post_hover");
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
            e.printStackTrace();
            list=null;
        }finally {
            return list;
        }


    }
}
