package net.zombie_sama.fanfou.activity;

import android.content.Intent;
import android.widget.TextView;

import net.zombie_sama.fanfou.R;
import net.zombie_sama.fanfou.base.BaseActivity;

import butterknife.Bind;

public class ResultActivity extends BaseActivity {

    @Bind(R.id.tv_result)
    TextView tv_result;

    @Override
    protected void initAfter() {
        Intent intent = getIntent();
        tv_result.setText(intent.getStringExtra("result"));
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.result_layout;
    }

}
