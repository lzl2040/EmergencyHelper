package com.example.emergencyhelper.constant;

/**
 * author ： yxm521
 * time    ： 2022/3/28
 */
public enum TimeType {
    /**
     * 显示“年”“月”“日”
     */
    DEFAULT(new boolean[]{true, true, true, false, false, false}),
    /**
     * 显示“年”“月”“日”“时”“分”“秒”
     */
    ALL(new boolean[]{true, true, true, true, true, true}),
    /**
     * 显示“时”“分”“秒”
     */
    TIME(new boolean[]{false, false, false, true, true, true}),
    /**
     * 显示“年”“月”“日”“时”“分”
     */
    DATE(new boolean[]{true, true, true, true, true, false});


    private final boolean[] mType;

    TimeType(boolean[] type) {
        mType = type;
    }

    public boolean[] getType() {
        return mType;
    }
}
