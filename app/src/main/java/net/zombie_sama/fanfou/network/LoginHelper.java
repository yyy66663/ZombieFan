package net.zombie_sama.fanfou.network;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by yyy66 on 2016/3/13.
 */
public class LoginHelper {
    public static String getSignature(Map<String, String> param, String url, String secret) throws Exception {

        String base_string = getBaseString("http://fanfou.com/oauth/access_token", param);
        String encode_string = encodeString(base_string);
        return HmacSHA1(encode_string, secret);
    }

    private static String getBaseString(String url, Map<String, String> param) {

        //参数转数组并排序
        String[] keyArray = param.keySet().toArray(new String[0]);
        Arrays.sort(keyArray);

        //拼接字符串
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("POST" + "&" + url + "&");
        for (int i = 0; i < keyArray.length; i++) {
            stringBuilder.append(keyArray[i] + "=").append(param.get(keyArray[i]));
            if (i < keyArray.length - 1)
                stringBuilder.append("&");
        }
        String base_string = stringBuilder.toString();
        Log.d("base string", base_string);
        return base_string;
    }
    private static String encodeString(String base_string) throws UnsupportedEncodingException {
        String encode_string = URLEncoder.encode(base_string);
        Log.d("encode base string", encode_string);
        return encode_string;
    }
    private static String HmacSHA1(String str, String secret) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(new SecretKeySpec((secret + '&').getBytes(), "HmacSHA1"));
        byte[] result = mac.doFinal(str.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : result) {
            sb.append(byteToHexString(b));
        }
        Log.d("HmacSHA1", sb.toString());
        return sb.toString();
    }
    private static String byteToHexString(byte ib) {
        char[] Digit = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
        };
        char[] ob = new char[2];
        ob[0] = Digit[(ib >>> 4) & 0X0f];
        ob[1] = Digit[ib & 0X0F];
        String s = new String(ob);
        return s;
    }
}
