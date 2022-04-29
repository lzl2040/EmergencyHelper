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

    /**
     * 检查电话号码是否有效
     * @param phone
     * @return
     */
    public static boolean checkPhoneValid(String phone){
        if(phone == null || phone.length() != 11){
            return false;
        }
        return true;
    }

    /**
     * 检查密码是否有效
     * @param pwd
     * @return
     */
    public static boolean checkPwdValid(String pwd){
        if(pwd == null || pwd.length() < 8 || pwd.length() > 15){
            return false;
        }
        return true;
    }

    /**
     * 检查两次密码是否一致
     * @param pwd 第一次输入的密码
     * @param rpwd 第二次输入的密码
     * @return
     */
    public static boolean checkPwdIsSame(String pwd,String rpwd){
        if(pwd != null && rpwd != null && pwd.equals(rpwd) && pwd.length() > 0){
            return true;
        }
        return false;
    }
}
