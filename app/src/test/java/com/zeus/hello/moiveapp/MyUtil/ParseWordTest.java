package com.zeus.hello.moiveapp.MyUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by zhou on 2017/2/28.
 */
public class ParseWordTest {
    static String num_result;
    static String text="<div class='hjd_jplang_title' >\t<span class=\"hjd_jp_ico\">\u65E5\u4E2D\u67E5\u8BE2\u7ED3\u679C\uFF1A</span> \t<span class=\"hjd_jp_resultcount\">\t1 \u4E2A\u7ED3\u679C<a href=\"###\" id=\"A2\" onclick=\"return HJ.fun.ShiftExplanPanels(1, this );\" class=\"hjd_expen1\"><b><div class='hjd_jp_expall_off'></div></b></a>  \t</span></div><div style=\"clear:both\">\t\t<span class=\"hjd_jp_title hjd_fl\" title=\"\u65E5\u8BED\u5355\u8BCD\"><span class=\"hjd_Green\">[<font color=red>\u5FC3\u6E29\u307E\u308B</font>]</span> <span title=\"\u5047\u540D\">[\u3053\u3053\u308D\u3042\u305F\u305F\u307E\u308B]</span> <span title=\"\u7F57\u9A6C\u97F3\" class=\"hjd_Orange\"><font color=red>[kokoroatatamaru]</font></span> <span title=\"\u58F0\u8C03\" class=\"hjd_Orange\">\u2466</span></span> <span title=\"\u65E5\u8BED\u53D1\u97F3\"> <span id=\"hjd_jp_pronounce_sound\" name=\"hjd_jp_pronounce_sound\" class=\"hjd_fl\">http://d1.g.hjfile.cn/voice/jpsound/J25834.mp3</span></span> <span id='hjd_amw_panel_0' class=\"hjd_add_myword\" ><a href='###' onclick='HJ.fun.AddJpWord(0);return false;'><img align='absmiddle' src='http://dict.hjenglish.com/images/btn_myword_add.gif' alt='\u5C06\u8FD9\u4E2A\u5355\u8BCD\u6DFB\u52A0\u5230\u6211\u7684\u751F\u8BCD\u672C' border='0'/></a></span>\t\t<input type=\"hidden\" value=\"\u5FC3\u6E29\u307E\u308B\" id=\"hjd_amw_word_0\"/>\t\t<input type=\"hidden\" value=\"\u3010\u3053\u3053\u308D\u3042\u305F\u305F\u307E\u308B\u3011<b>\u3010\u540D\u8BCD\u3011</b> <br/>\u6696\u4EBA\u5FC3\u6000\u3002\uFF08\u3042\u305F\u305F\u304B\u3044\u4EBA\u60C5\u3092\u611F\u3058\u3066\u306A\u3054\u3084\u304B\u306A\u5FC3\u306B\u306A\u308B\uFF09\u3002<br/><img src='http://dict.hjenglish.com/images/icon_star.gif' align='absmiddle' style='margin-left:10px;'/>\u5FC3\u6E29\u307E\u308B\u60C5\u666F\u3002/\u6696\u4EBA\u5FC3\u6000\u7684\u60C5\u666F\u3002<br/>\" id=\"hjd_amw_comment_0\"/>\t<span class=\"hjd_fr\">\t\t<a href=\"###\" id=\"click2expend_0\" onclick=\"return HJ.fun.ShiftVisible_jpcom('com_panel_0',this);\" class=\"expen\"><b><div class='hjd_jp_exp_off'></div></b></a>\t</span></div> <div id=\"com_panel_0\" style=\"display:block;\" class=\"hjd_jp_explain\">  \t<b>\u3010\u540D\u8BCD\u3011</b> <br/>\u6696\u4EBA\u5FC3\u6000\u3002\uFF08\u3042\u305F\u305F\u304B\u3044\u4EBA\u60C5\u3092\u611F\u3058\u3066\u306A\u3054\u3084\u304B\u306A\u5FC3\u306B\u306A\u308B\uFF09\u3002<br/><img src='http://dict.hjenglish.com/images/icon_star.gif' align='absmiddle' style='margin-left:10px;'/>\u5FC3\u6E29\u307E\u308B\u60C5\u666F\u3002/\u6696\u4EBA\u5FC3\u6000\u7684\u60C5\u666F\u3002<br/></div><div id=\"hjd_panel_feedback\">\t<a href=\"http://dict.hujiang.com/app/jp/w/%e5%bf%83%e6%b8%a9%e3%81%be%e3%82%8b&type=jc\" target=\"_blank\">\u66F4\u591A\u89E3\u91CA\u00BB</a></div>";
    @Test
    public void achieve() throws Exception {
        Document document= Jsoup.parse(text);
        Elements elements=document.getElementsByClass("hjd_jp_resultcount");
        for (Element e : elements){
            num_result= e.text();
        }
        assertThat(num_result,is("1 个结果"));
        Elements explain=document.getElementsByClass("hjd_jp_explain");
        assertEquals(1,explain.size());
        for (Element e: explain){
            assertEquals(e.text(),"");
        }

    }

}