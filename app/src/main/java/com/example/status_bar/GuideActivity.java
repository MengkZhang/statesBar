package com.example.status_bar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.status_bar.guideview.CircleLightShape;
import com.example.status_bar.guideview.HighLight;
import com.example.status_bar.guideview.HighLightInterface;
import com.example.status_bar.guideview.HightLightView;
import com.example.status_bar.guideview.OnBottomPosCallback;
import com.example.status_bar.guideview.OnTopPosCallback;
import com.example.status_bar.guideview.RectLightShape;

public class GuideActivity extends AppCompatActivity {

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private HighLight mHighLight;
    private boolean isShowGuideView = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//修改了它
        setContentView(R.layout.activity_guide);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GuideActivity.this, "ksk", Toast.LENGTH_SHORT).show();
//                guideView();
            }
        });
        guideView();

    }

    private void guideView() {
        mHighLight = new HighLight(this)//
                .autoRemove(false)//设置背景点击高亮布局自动移除为false 默认为true
//                .intercept(false)//设置拦截属性为false 高亮布局不影响后面布局的滑动效果
                .intercept(true)//拦截属性默认为true 使下方ClickCallback生效
                .enableNext()//开启next模式并通过show方法显示 然后通过调用next()方法切换到下一个提示布局，直到移除自身
                .anchor(findViewById(R.id.id_container))//如果是Activity上增加引导层，不需要设置anchor
                .setOnLayoutCallback(new HighLightInterface.OnLayoutCallback() {
                    @Override
                    public void onLayouted() {
                        if (isShowGuideView) {
                            return;
                        }
                        mHighLight.addHighLight(R.id.btn1, R.layout.custom_guide_view_right_top, new OnBottomPosCallback(20), new CircleLightShape())//矩形去除圆角
                                .addHighLight(R.id.btn2, R.layout.custom_guide_view_right_top, new OnBottomPosCallback(50), new CircleLightShape())//矩形去除圆角
                                /*.addHighLight(R.id.btn2, R.layout.custom_guide_view_right_top, new OnRightPosCallback(5), new BaseLightShape(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()), TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()), 0) {
                                    @Override
                                    protected void resetRectF4Shape(RectF viewPosInfoRectF, float dx, float dy) {
                                        //缩小高亮控件范围
                                        viewPosInfoRectF.inset(dx, dy);
                                    }

                                    @Override
                                    protected void drawShape(Bitmap bitmap, HighLight.ViewPosInfo viewPosInfo) {
                                        //custom your hight light shape 自定义高亮形状
                                        Canvas canvas = new Canvas(bitmap);
                                        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                                        paint.setDither(true);
                                        paint.setAntiAlias(true);
                                        //blurRadius必须大于0
                                        if (blurRadius > 0) {
                                            paint.setMaskFilter(new BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.SOLID));
                                        }
                                        RectF rectF = viewPosInfo.rectF;
                                        canvas.drawOval(rectF, paint);
                                    }
                                })*/
                                .addHighLight(R.id.btn3, R.layout.custom_guide_view_right_top, new OnTopPosCallback(10), new RectLightShape(0, 0, 15, 0, 0))
                                .setOnRemoveCallback(new HighLightInterface.OnRemoveCallback() {//监听移除回调
                                    @Override
                                    public void onRemove() {

                                    }
                                })
                                .setOnShowCallback(new HighLightInterface.OnShowCallback() {//监听显示回调
                                    @Override
                                    public void onShow(HightLightView hightLightView) {
                                    }
                                }).setOnNextCallback(new HighLightInterface.OnNextCallback() {
                            @Override
                            public void onNext(HightLightView hightLightView, View targetView, View tipView) {
                            }
                        });
                        mHighLight.show();
                        isShowGuideView = true;
                    }
                });

    }

    /**
     * 响应所有R.id.iv_known的控件的点击事件
     * <p>
     * 移除高亮布局
     * </p>
     *
     * @param view
     */
    public void clickKnown(View view) {
        if (mHighLight.isShowing() && mHighLight.isNext())//如果开启next模式
        {
            mHighLight.next();
        } else {
            remove(null);
        }
    }

    public void remove(View view) {
        mHighLight.remove();
    }
}
