package net.zombie_sama.fanfou.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class StartActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVisible(false);
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }
}
