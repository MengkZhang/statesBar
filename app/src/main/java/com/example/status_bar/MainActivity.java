package com.example.status_bar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.status_bar.notch.NotchProperty;
import com.example.status_bar.notch.NotchTools;
import com.example.status_bar.notch.OnNotchCallBack;

public class MainActivity extends AppCompatActivity implements OnNotchCallBack {

    //底部导航是否隐藏
    private boolean isBottomHidden = false;
    //延时毫秒数
    private static final long COLLAPSE_SB_PERIOD = 100;
    //id
    private static final int COLLAPSE_STATUS_BAR = 1000;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case COLLAPSE_STATUS_BAR:
                    collapse(MainActivity.this, true);
                    sendEmptyMessageDelayed(COLLAPSE_STATUS_BAR, COLLAPSE_SB_PERIOD);
                    break;
                default:
                    break;
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //根本没有导航栏的写法
        hiddenStatus();
        //隐藏底部菜单栏
        secondHide();
        setContentView(R.layout.activity_main);
        NotchTools.getFullScreenTools().fullScreenUseStatus(this, this);
    }

    private void hiddenStatus() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) && (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP)) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public void secondHide() {
        isBottomHidden = true;
        int flags = getWindow().getDecorView().getSystemUiVisibility();
        getWindow().getDecorView().setSystemUiVisibility(flags | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE);
        setBg();

        mHandler.sendEmptyMessageDelayed(COLLAPSE_STATUS_BAR, COLLAPSE_SB_PERIOD);

    }

    private void setBg() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Android 5.0 以上 全透明
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            // 状态栏（以上几行代码必须，参考setStatusBarColor|setNavigationBarColor方法源码）
            window.setStatusBarColor(Color.TRANSPARENT);
            // 虚拟导航键
            //window.setNavigationBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.BLACK);
        }
    }

    public static void collapse(Activity activity, boolean enable) {
        Window window = activity.getWindow();
        if (enable) {
            //这里需要判断api是否是21
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int flags = window.getDecorView().getSystemUiVisibility();
                // FIXME: 2019/2/13 要实现window扩展 又不能使底部导航栏透明
//                window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);//实现将window扩展至全
                window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);//避免重新布局
                window.getDecorView().setSystemUiVisibility(flags | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
            } else {
                WindowManager.LayoutParams attr = window.getAttributes();
                window.setAttributes(attr);
                window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
                int flags = window.getDecorView().getSystemUiVisibility();
                window.getDecorView().setSystemUiVisibility(flags | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

                attr.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            }
        } else {
            WindowManager.LayoutParams attr = window.getAttributes();
            attr.flags &= (WindowManager.LayoutParams.FLAG_FULLSCREEN);
            window.setAttributes(attr);
            window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }


    public void clickShow(View view) {

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //显示状态栏
        //如果没有显示  则显示 如果显示了  则隐藏
        if (isBottomHidden) {
            //显示了 需要隐藏
            //取消发送，显示底部菜单栏
            mHandler.removeMessages(COLLAPSE_STATUS_BAR);
            showBottomUIMenu();
        } else {
            //没有显示 需要显示
            //隐藏底部菜单栏
            secondHide();
        }

    }

    /**
     * 显示菜单栏
     * 如果底部的bar 隐藏就显示
     */
    protected void showBottomUIMenu() {
        isBottomHidden = false;
        int flags;
        int curApiVersion = android.os.Build.VERSION.SDK_INT;
        // This work only for android 4.4+
        if (curApiVersion >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            // Android 5.0 以上 全透明
            flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN//View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN：Activity全屏显示，但状态栏不会被隐藏覆盖，状态栏依然可见，Activity顶端布局部分会被状态遮住。
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;//效果同View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            // must be executed in main thread :)
            getWindow().getDecorView().setSystemUiVisibility(flags);
            setBg();
        } else if (curApiVersion >= Build.VERSION_CODES.KITKAT) {
            // This work only for android 4.4+
            // hide navigation bar permanently in android activity
            // touch the screen, the navigation bar will not show
            flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN//View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN：Activity全屏显示，但状态栏不会被隐藏覆盖，状态栏依然可见，Activity顶端布局部分会被状态遮住。
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;//效果同View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            // must be executed in main thread :)
            getWindow().getDecorView().setSystemUiVisibility(flags);

        } else {
            // touch the screen, the navigation bar will show
            flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            // must be executed in main thread :)
            getWindow().getDecorView().setSystemUiVisibility(flags);
        }

//        // must be executed in main thread :)
//        getWindow().getDecorView().setSystemUiVisibility(flags);

    }

    @Override
    public void onNotchPropertyCallback(NotchProperty notchProperty) {

    }
}
