package com.example.emergencyhelper.util;

/**
 * 检查一些输入是否合法
 * author ： yxm521
 * time    ： 2022/3/31
 */
public class CheckUtil {

    /**
     * 检查字符串是否为空
     * @param str
     * @return
     */
    public static boolean checkStringNull(String str){
        if(str == null || str.equals("")){
            return true;
        }
        return false;
    }
}
