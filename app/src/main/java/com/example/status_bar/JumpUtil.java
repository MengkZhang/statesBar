package com.example.status_bar;

import android.content.Context;
import android.content.Intent;

public class JumpUtil {
    public static void jump(Context context,Class clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }
}
