package com.zeus.hello.moiveapp.MyFragment.dummy;

import android.util.Log;

import com.zeus.hello.moiveapp.MyUtil.MyWifiInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {
private final static String TAG="DummyContent";
    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 25;

    static {

        if (MyWifiInfo.list != null) {
            // Add some sample items.

                for (int i = 0; i < MyWifiInfo.list.size(); i++) {
                    addItem(createDummyItem(i));
                }


        }else {
            addItem(new DummyItem("no","not find",".."));
        }

    }

    public static  List<DummyItem> getItems(){
        ITEMS.clear();
        if (MyWifiInfo.list != null) {
            // Add some sample items.

                for (int i = 0; i < MyWifiInfo.list.size(); i++) {
                    addItem(createDummyItem(i));
                }


        }else {
            addItem(new DummyItem("no","not find",".."));
        }
        return ITEMS;
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(MyWifiInfo.list.get(position).SSID,MyWifiInfo.list.get(position).BSSID,MyWifiInfo.list.get(position).toString());
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
