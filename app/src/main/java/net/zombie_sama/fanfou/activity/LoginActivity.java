package net.zombie_sama.fanfou.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.OnResponseListener;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.RequestQueue;
import com.yolanda.nohttp.Response;

import net.zombie_sama.fanfou.R;
import net.zombie_sama.fanfou.base.BaseActivity;
import net.zombie_sama.fanfou.base.BaseApplication;
import net.zombie_sama.fanfou.utils.Utils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

public class LoginActivity extends BaseActivity implements View.OnClickListener, OnResponseListener<String> {

    @Bind(R.id.tv_signin)
    TextView tv_signin;
    @Bind(R.id.tv_signup)
    TextView tv_signup;
    @Bind(R.id.et_username)
    EditText et_username;
    @Bind(R.id.et_password)
    EditText et_password;

    private String username;
    private String password;

    @Override
    public int getLayoutResource() {
        return R.layout.login_layout;
    }

    @Override
    protected void initAfter() {
        tv_signin.setOnClickListener(this);
        tv_signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_signin:

                username = et_username.getText().toString();
                password = et_password.getText().toString();
                Map<String, String> param = new HashMap<String, String>();
                param.put("x_auth_username", "username");
                param.put("x_auth_password", "password");
                param.put("x_auth_mode", "client_auth");
                Utils.getBaseString("http://api.fanfou.com/account/verify_credentials.xml",param);
                RequestQueue queue = ((BaseApplication) getApplication()).getRequestQueue();
                Request<String> request = NoHttp.createStringRequest(getString(R.string.base_url) + getString(R.string.access_token_url), RequestMethod.POST);
                request.add("x_auth_username", "username");
                // 添加到队列
                queue.add(0, request, this);
                break;
            case R.id.tv_signup:
                break;
        }
    }

    @Override
    public void onStart(int what) {

    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("result", response.get());
        startActivity(intent);
    }

    @Override
    public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
        Log.e("response", "code=" + responseCode, exception);
    }

    @Override
    public void onFinish(int what) {

    }
}
