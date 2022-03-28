package com.example.emergencyhelper.util;

import android.annotation.SuppressLint;

import com.example.emergencyhelper.constant.DateConstant;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * author ： yxm521
 * time    ： 2022/3/28
 */
public class DateUtils {

    /**
     * 日期字符串转时间
     * @param time
     * @param format
     * @return
     */
    public static Date string2Date(final String time, final DateFormat format) {
        try {
            if (format != null) {
                return format.parse(time);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 日期转字符
     * @param date
     * @param format
     * @return
     */
    public static String date2String(final Date date, final DateFormat format) {
        if (format != null) {
            return format.format(date);
        } else {
            return null;
        }
    }

    /**
     * 时间戳转字符串
     * @param times 毫秒
     * @return
     */
    public static String timeNum2String(long times){
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(times);
        return dateStr;
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final ThreadLocal<DateFormat> yyyyMMddHHmmss = new ThreadLocal<DateFormat>() {
        @SuppressLint("SimpleDateFormat")
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(DateConstant.yyyyMMddHHmmss);
        }
    };

    /**
     * yyyy-MM-dd HH:mm
     */
    public static final ThreadLocal<DateFormat> yyyyMMddHHmm = new ThreadLocal<DateFormat>() {
        @SuppressLint("SimpleDateFormat")
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(DateConstant.yyyyMMddHHmm);
        }
    };
}
