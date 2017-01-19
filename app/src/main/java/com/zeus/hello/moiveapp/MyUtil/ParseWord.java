package com.zeus.hello.moiveapp.MyUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by zhou on 2017/2/28.
 */

public class ParseWord {
    static String num_result;
    public static String achieve(String text){
        Document document=Jsoup.parse(text);
Elements elements=document.getElementsByClass("hjd_jp_resultcount");
        for (Element e : elements){
            num_result= e.text();
        }
        return num_result;
    }
}
