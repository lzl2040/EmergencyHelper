package com.example.emergencyhelper.util;

import android.content.Context;

/**
 * author ： yxm521
 * time    ： 2022/3/29
 */
public class CommonUtils {
    private static float density;

    public static int dipToPx(Context context, int dip) {
        if (density <= 0.0F) {
            density = context.getResources().getDisplayMetrics().density;
        }

        return (int)((float)dip * density + 0.5F);
    }
}
