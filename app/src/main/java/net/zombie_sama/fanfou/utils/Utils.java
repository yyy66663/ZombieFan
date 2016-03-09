package net.zombie_sama.fanfou.utils;

import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by yyy66 on 2016/3/10.
 */
public class Utils {
    public static String getBaseString(String url, Map<String,String> param) throws UnsupportedEncodingException {

        param.put("format", "json");

        String[] keyArray = param.keySet().toArray(new String[0]);
        Arrays.sort(keyArray);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("POST"+"&"+ URLEncoder.encode(url,"UTF-8")+"&");
        for (String key : keyArray)
        {
            stringBuilder.append(key).append(param.get(key)+"&");
        }

        String base_string = stringBuilder.toString();
        Log.d("base_string",base_string);
        return base_string;
    }

    private static String generateSignature(String baseString,
                                            String consumerKeySecret, String tokenSecret) {

        byte[] byteHMAC = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            SecretKeySpec spec;
            String oauthSignature = encode(consumerKeySecret) + "&"
                    + ((tokenSecret != null) ? encode(tokenSecret) : "");
            spec = new SecretKeySpec(oauthSignature.getBytes(), "HmacSHA1");
            mac.init(spec);
            byteHMAC = mac.doFinal(baseString.getBytes());
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException ignore) {
            // should never happen
        }
        return new String(Base64.encode(byteHMAC,Base64.DEFAULT));
    }
}
