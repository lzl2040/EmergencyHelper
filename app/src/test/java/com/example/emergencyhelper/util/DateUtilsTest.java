package com.example.emergencyhelper.util;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * author ： yxm521
 * time    ： 2022/3/31
 */
public class DateUtilsTest extends TestCase {

    @Test
    public void testCalInterval() {
        long time = 117861000;
        String str = DateUtils.calInterval(time);
        System.out.println(str);
    }
}