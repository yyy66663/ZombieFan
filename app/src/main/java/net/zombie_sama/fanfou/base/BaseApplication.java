package net.zombie_sama.fanfou.base;

import android.app.Application;
import android.content.SharedPreferences;

import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestQueue;

public class BaseApplication extends Application{

    private SharedPreferences sp;


    @Override
    public void onCreate() {
        super.onCreate();
        NoHttp.init(this);
        sp =getSharedPreferences("setting",MODE_PRIVATE);
    }
}
