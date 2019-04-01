package com.example.status_bar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Mengk on 2019/3/11
 * Describe :
 */
public class MyRV extends RecyclerView {
    public MyRV(Context context) {
        super(context);
    }

    public MyRV(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private int lastX;
    private int lastY;

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        //getRawX()是控件相对于父容器左上角的距离
//        //获取控件的x,y坐标
//        int eventX = (int) event.getRawX();
//        int eventY = (int) event.getRawY();
//
//
//        switch (event.getAction()) {
//
//            case MotionEvent.ACTION_DOWN:
//                /**记录控件的x,y坐标*/
//                lastX = eventX;
//                lastY = eventY;
//                Log.e("===z", "MyRV按下坐标x：" + lastX);
//                Log.e("===z", "MyRV按下坐标y：" + lastY);
//
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//                /**计算偏移量*/
//                int dx = eventX - lastX;
//                int dy = eventY - lastY;
//
//
//                /**再次记录控件的x,y坐标*/
//                lastX = eventX;
//                lastY = eventY;
//
//                break;
//            case MotionEvent.ACTION_UP:
//                Log.e("===z", "MyRV抬起坐标x：" + lastX);
//                Log.e("===z", "MyRV抬起坐标y：" + lastY);
//                break;
//
//            default:
//                break;
//        }
//        return true;
//    }
}
