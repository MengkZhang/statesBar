package com.example.status_bar;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.LinearLayout;


/**
 * Created by Mengk on 2019/3/27
 * Describe :
 * ---------------------
 * 每次导航栏消失或者显示都会使view重新绘制 所以我在popwindow的contentview（linearlayout）上做了些改动，在这个contentview的Onlayout方法里面判断是否需要偏移，如果偏移就设置这个contentview.setPadding(0,0,0,getNavigationBarHeight());不偏移就正常显示contentview.setPadding(0,0,0,0);
 * ---------------------
 */
public class AutoHeightLinearLayout extends LinearLayout {
    private static final String TAG ="CustomShareBoard";
    private Context context;
    private boolean isFirstEnter = true;//是否是手动打开的导航栏
    private boolean isFirstEnd = false;//

    public AutoHeightLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context= context;

    }

    public AutoHeightLinearLayout(Context context) {
        super(context);
        this.context= context;
    }

    public AutoHeightLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context= context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(!isOffset()) { //没有导航栏
            if (this.getPaddingBottom()!=0) {
                this.setPadding(0, 0, 0, 0);
            }
        } else{           //有导航栏
            this.setPadding(0, 0, 0, getNavigationBarHeight());
            invalidate();
        }
        this.getViewTreeObserver().addOnGlobalLayoutListener(globalLayoutListener);
        super.onLayout(true, l, t, r, b);
    }

    private ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            if (isNavigationBarShow()) {
                AutoHeightLinearLayout.this.setPadding(0, 0, 0, getNavigationBarHeight());
            } else {
                AutoHeightLinearLayout.this.setPadding(0, 0, 0, -1);
            }
        }
    };

    private boolean isNavigationBarShow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            WindowManager windowManager = (WindowManager) MyApp.context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            Point size = new Point();
            Point realSize = new Point();
            display.getSize(size);
            display.getRealSize(realSize);
            return realSize.y != size.y;
        } else {
            boolean menu = ViewConfiguration.get(context).hasPermanentMenuKey();
            boolean back = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
            return !(menu || back);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private int getNavigationBarHeight() {
        Resources resources = this.context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }
    public boolean isOffset() {
        return getDecorViewHeight() > getScreenHeight();
    }


    public int getDecorViewHeight(){
        return ((Activity)this.context).getWindow().getDecorView().getHeight();
    }

    public int getScreenHeight(){
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity)this.context).getWindowManager().getDefaultDisplay().getMetrics(dm);//当前activity
        return dm.heightPixels;
    }
}
