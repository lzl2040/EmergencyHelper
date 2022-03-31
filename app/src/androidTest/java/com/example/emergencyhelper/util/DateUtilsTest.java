package com.example.emergencyhelper.util;

import junit.framework.TestCase;

/**
 * author ： yxm521
 * time    ： 2022/3/31
 */
public class DateUtilsTest extends TestCase {

    public void testCalInterval() {
        long interval = 7261;
        String str = DateUtils.calInterval(interval);
        System.out.println(str);
    }
}