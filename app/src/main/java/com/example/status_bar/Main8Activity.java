package com.example.status_bar;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class Main8Activity extends AppCompatActivity {

    SelectPicPopupWindow menuWindow;

    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        initPopupWindow();
    }

    private void initPopupWindow() {
        TextView tv = (TextView) this.findViewById(R.id.tv);
        //把文字控件添加监听，点击弹出自定义窗口
        tv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                View popupView = getLayoutInflater().inflate(R.layout.layout_pp_view, null);
                popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(false);
                popupWindow.showAtLocation(Main8Activity.this.findViewById(R.id.main), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置

            }
        });

    }

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener(){

        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                case R.id.btn_take_photo:
                    break;
                case R.id.btn_pick_photo:
                    break;
                default:
                    break;
            }


        }

    };


}
