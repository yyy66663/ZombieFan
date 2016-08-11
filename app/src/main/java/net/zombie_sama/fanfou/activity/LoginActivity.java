package net.zombie_sama.fanfou.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Response;

import net.zombie_sama.fanfou.R;
import net.zombie_sama.fanfou.base.BaseActivity;
import net.zombie_sama.fanfou.network.NetworkController;
import net.zombie_sama.fanfou.utils.L;

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
                //获取用户名和密码
                username = et_username.getText().toString();
                password = et_password.getText().toString();
                NetworkController.login(this, username, password, this);
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
        L.i("onSucceed\nresponse = " + response.get() + "\ncode = " + response.responseCode() + "\nmessage = " + response.responseMesage());
    }

    @Override
    public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
        L.e("onFailed\nurl = " + url + " tag = " + tag + " code = " + responseCode + " networkMillis = " + networkMillis, exception);
    }

    @Override
    public void onFinish(int what) {

    }
}
