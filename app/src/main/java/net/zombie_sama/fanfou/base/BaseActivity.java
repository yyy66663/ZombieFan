package net.zombie_sama.fanfou.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import butterknife.ButterKnife;

public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBefore();
        setContentView(getLayoutResource());
        ButterKnife.bind(this);
        initAfter();
    }

    protected BaseApplication getBaseApplication() {
        return (BaseApplication) getApplication();
    }

    protected abstract void initAfter();

    protected void initBefore() {

    }

    protected abstract int getLayoutResource();
}
