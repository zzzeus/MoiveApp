package com.zeus.hello.moiveapp;

import android.app.Application;
import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;

import com.bilibili.magicasakura.utils.ThemeUtils;

/**
 * Created by zhou on 2017/1/20.
 */

public class MyApplication extends Application implements ThemeUtils.switchColor {
    @Override
    public int replaceColorById(Context context, @ColorRes int colorId) {
        return 0;
    }

    @Override
    public int replaceColor(Context context, @ColorInt int color) {
        return 0;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //init
        ThemeUtils.setSwitchColor(this);
    }
}
