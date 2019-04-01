package com.example.status_bar.guideview;

import android.graphics.RectF;
import android.util.Log;

/**
 * Created by caizepeng on 16/8/20.
 */
public  class OnBottomPosCallback extends OnBaseCallback{
    public OnBottomPosCallback() {
    }

    public OnBottomPosCallback(float offset) {
        super(offset);
    }

    @Override
    public void getPosition(float rightMargin, float bottomMargin, RectF rectF, HighLight.MarginInfo marginInfo) {
        marginInfo.rightMargin = rightMargin;
        Log.e("===z", "rightMargin = " + rightMargin);
        marginInfo.topMargin = rectF.top + rectF.height()+offset;
    }

}
