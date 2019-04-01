package com.example.status_bar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main6Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
    }

    @Override
    protected void setStatusBar() {
        super.setStatusBar();
//        StatusBarUtil.setTranslucent(this, 255);
        int mColor = getResources().getColor(R.color.colorPrimary);
        StatusBarUtil.setColor(this, mColor, 255);
    }
}
