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
     * 时间戳转日期字符串
     * @param times 毫秒
     * @return
     */
    public static String timeNum2String(long times){
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(times);
        return dateStr;
    }

    /**
     * 日期字符串转为时间戳
     * @param timeStr
     * @return
     */
    public static long string2TimeNum(String timeStr){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(timeStr);
        long ts = date.getTime();
        return ts;
    }

    /**
     * 给定间隔的毫秒数,计算出时间
     * @param interval 间隔的时间戳
     * @return
     */
    public static String calInterval(long interval){
        long seconds = interval / 1000;                         //转换为秒
        int day = seconds / DateConstant.DAY_TIME_SECONDS;      //计算这是多少天
        int hour = (seconds % DateConstant.DAY_TIME_SECONDS) / DateConstant.HOUR_TIME_SECONDS;      //计算这是多少小时
        int minutes = (seconds % DateConstant.DAY_TIME_SECONDS) /% DateConstant.HOUR_TIME_SECONDS;  //计算这是多少分钟
        int second = seconds % 60;                              //计算这是多少秒

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
