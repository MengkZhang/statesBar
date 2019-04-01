package com.example.status_bar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.status_bar.notch.NotchProperty;
import com.example.status_bar.notch.NotchTools;
import com.example.status_bar.notch.OnNotchCallBack;
import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.OnKeyboardListener;

public class Main9Activity extends AppCompatActivity implements OnNotchCallBack, View.OnTouchListener {


    private MyRV myRV;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        NotchTools.getFullScreenTools().fullScreenUseStatus(this, this);
        initView();
    }

    private void initView() {
        myRV = (MyRV) findViewById(R.id.myrv);
        myRV.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this);
        myRV.setAdapter(adapter);
        myRV.setOnTouchListener(this);

    }

    @Override
    public void onNotchPropertyCallback(NotchProperty notchProperty) {

    }

    private int lastX;
    private int lastY;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //getRawX()是控件相对于父容器左上角的距离
        //获取控件的x,y坐标
        int eventX = (int) event.getRawX();
        int eventY = (int) event.getRawY();


        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                /**记录控件的x,y坐标*/
                lastX = eventX;
                lastY = eventY;
                Log.e("===z", "按下坐标x：" + lastX);
                Log.e("===z", "按下坐标y：" + lastY);

                break;

            case MotionEvent.ACTION_MOVE:
                /**计算偏移量*/
                int dx = eventX - lastX;
                int dy = eventY - lastY;


                /**再次记录控件的x,y坐标*/
                lastX = eventX;
                lastY = eventY;

                break;
            case MotionEvent.ACTION_UP:
                Log.e("===z", "抬起坐标x：" + lastX);
                Log.e("===z", "抬起坐标y：" + lastY);
                break;

            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
