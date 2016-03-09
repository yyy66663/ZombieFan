package net.zombie_sama.fanfou.base;

import android.app.Application;

import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestQueue;

public class BaseApplication extends Application{
    private RequestQueue queue;
    public RequestQueue getRequestQueue(){
        if (null == queue)
            queue = NoHttp.newRequestQueue();
        return queue;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        NoHttp.init(this);
    }
}
