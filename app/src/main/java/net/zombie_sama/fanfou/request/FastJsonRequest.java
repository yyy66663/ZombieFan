package net.zombie_sama.fanfou.request;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.RestRequest;
import com.yolanda.nohttp.StringRequest;


public class FastJsonRequest extends RestRequest<JSONObject> {

    public FastJsonRequest(String url) {
        super(url);
    }

    public FastJsonRequest(String url, RequestMethod requestMethod) {
        super(url, requestMethod);
    }

    @Override
    public JSONObject parseResponse(String url, Headers headers, byte[] responseBody) {
        String result = StringRequest.parseResponseString(url, headers, responseBody);
        JSONObject jsonObject = null;
        if (!TextUtils.isEmpty(result)) {
            jsonObject = JSON.parseObject(result);
        } else {
            // 这里默认的错误可以定义为自己的数据格式
            jsonObject = (JSONObject) JSON.toJSON("{}");
        }
        return jsonObject;
    }

    @Override
    public String getAccept() {
        // 告诉服务器你接受什么类型的数据, 会添加到请求头的Accept中
        return "application/json;q=1";
    }

}