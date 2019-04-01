package com.example.status_bar;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class Main7Activity extends AppCompatActivity {

    private PopupWindow mOptionPanelBottomPpw;
    private boolean isShow = false;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        linearLayout = (LinearLayout) findViewById(R.id.rl_root_view);
        initPPW();
        showOptionPanelBottomPpw();
    }

    /**
     * 显示底部控制面板
     */
    private void showOptionPanelBottomPpw() {
        mOptionPanelBottomPpw.showAtLocation(linearLayout, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
    }

    /**
     * 隐藏底部控制面板
     *
     */
    private void displayOptionPanelBottomPpw() {
        if (mOptionPanelBottomPpw != null) {
            mOptionPanelBottomPpw.dismiss();
        }
    }

    private void initPPW() {
        View mOptionPanelBottomPpv = getLayoutInflater().inflate(R.layout.layout_pp_view, null);
        this.mOptionPanelBottomPpw = new PopupWindow(mOptionPanelBottomPpv, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        this.mOptionPanelBottomPpw.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        this.mOptionPanelBottomPpw.setFocusable(false);
        this.mOptionPanelBottomPpw.setOutsideTouchable(false);

    }

    private void hiddenNav() {
        int flags = getWindow().getDecorView().getSystemUiVisibility();
        getWindow().getDecorView().setSystemUiVisibility(
                flags | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE);


    }

    private void showNav() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Android 5.0 以上 全透明
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            // 虚拟导航键
            window.setNavigationBarColor(Color.BLACK);
        }
    }

    public void clickDT(View view) {
        if (isShow) {
            //显示 需隐藏
            hiddenNav();
            displayOptionPanelBottomPpw();
            isShow = false;
        } else {
            //隐藏 需显示
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            showNav();
            showOptionPanelBottomPpw();
            isShow = true;
        }
    }
}
