package com.example.status_bar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setStatusBar();
    }

    private void setStatusBar() {
        int mColor = getResources().getColor(R.color.colorPrimary);
        StatusBarUtil.setColor(this, mColor, 0);
    }

    public void clickAlpha(View view) {
        JumpUtil.jump(this,Main3Activity.class);
    }

    public void clickWhite(View view) {
        JumpUtil.jump(this,Main4Activity.class);
    }

    public void clickStatusNoPosition(View view) {
        JumpUtil.jump(this,Main5Activity.class);
    }

    public void clickStatusNoPositionBlack(View view) {
        JumpUtil.jump(this,Main6Activity.class);
    }

    public void clickSlideBack(View view) {
        JumpUtil.jump(this,SwipeBackActivity.class);
    }

    public void clickFragment(View view) {//UseInFragmentActivity
        JumpUtil.jump(this,UseInFragmentActivity.class);
    }

    public void clickNoStatus(View view) {
        JumpUtil.jump(this,MainActivity.class);
    }

    public void clickNoAllStatus(View view) {
        JumpUtil.jump(this,Main7Activity.class);
    }

    public void clickBug(View view) {
        JumpUtil.jump(this,Main8Activity.class);
    }

    public void clickAllScreen(View view) {
        JumpUtil.jump(this,Main9Activity.class);
    }

    public void clickGuideView(View view) {
        JumpUtil.jump(this,GuideActivity.class);
    }
}
