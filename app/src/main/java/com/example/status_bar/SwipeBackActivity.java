package com.example.status_bar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.r0adkll.slidr.Slidr;

public class SwipeBackActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置右滑动返回
        Slidr.attach(this);
        setContentView(R.layout.activity_swipe_back);
    }

    @Override
    protected void setStatusBar() {
        super.setStatusBar();
        int mColor = getResources().getColor(R.color.colorPrimary);
        StatusBarUtil.setColorForSwipeBack(this, mColor, 38);
    }
}
