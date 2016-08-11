package net.zombie_sama.fanfou.network;

import android.content.Context;

import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;

import net.zombie_sama.fanfou.utils.L;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NetworkController {
    public static final int WHAT_LOGIN = 1;

    private static final String CONSUMER_KEY = "2daab52989effcb74cd7350b3eef4b77";
    private static final String CONSUMER_SECRET = "480103db30ad8a6d937f46a5afb73367";
    private static final String MAC_NAME = "HmacSHA1";
    private static final String ENCODING = "UTF-8";
    private static final String BASE_URL = "http://fanfou.com/";
    private static final String ACCESS_TOKEN_URL = "oauth/access_token";
    private static final String X_AUTH_MODE = "client_auth";
    private static final String OAUTH_CONSUMER_KEY = "8d3523de7b46423170f4351427bb680f";
    private static final String OAUTH_SIGNATURE_METHOD = "HMAC-SHA1";
    private static RequestQueue queue;


    public static synchronized RequestQueue getRequestQueue() {
        if (null == queue)
            queue = NoHttp.newRequestQueue();
        return queue;
    }

    public static void login(Context context, String username, String password, OnResponseListener listener) {
        String signature;

        Map<String, String> param = new HashMap<String, String>();
        param.put("x_auth_username", username);
        param.put("x_auth_password", password);
        param.put("x_auth_mode", X_AUTH_MODE);
        param.put("oauth_consumer_key", OAUTH_CONSUMER_KEY);
        param.put("oauth_signature_method", OAUTH_SIGNATURE_METHOD);
        param.put("oauth_timestamp", "" + System.currentTimeMillis());
        param.put("oauth_nonce", UUID.randomUUID().toString());
        param.put("oauth_version", "1.0");

        try {
            signature = LoginHelper.getSignature(param, BASE_URL + ACCESS_TOKEN_URL, CONSUMER_SECRET);
            Request<String> request = NoHttp.createStringRequest(BASE_URL + ACCESS_TOKEN_URL, RequestMethod.POST);
            request.add(param);
            request.setCancelSign(WHAT_LOGIN);
            request.add("oauth_signature", signature);
            RequestQueue queue = getRequestQueue();
            L.i("param\n" + request.getParamKeyValues());
            for (String key : request.getParamKeyValues().keySet()) {
                L.i(key + " = " + request.getParamKeyValues().getValues(key));
            }
            queue.add(WHAT_LOGIN, request, listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
