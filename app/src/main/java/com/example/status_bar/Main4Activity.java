package com.example.status_bar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main4Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }

    @Override
    protected void setStatusBar() {
        super.setStatusBar();
        int mColor = getResources().getColor(R.color.colorWhite);
        StatusBarUtil.setColor(this, mColor, 0);
        StatusBarUtil.setWhiteLightMode(this);
    }
}
