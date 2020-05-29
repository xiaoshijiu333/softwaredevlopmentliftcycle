package com.fei.softwaredevlopmentliftcycle.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/17
 * @Description: $value$
 */
public class TimeAddHours {

    private TimeAddHours() {
    }

    public static Date addDateMinut(Date date, int hour) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 24小时制
        cal.add(Calendar.HOUR, hour);
        date = cal.getTime();
        return date;
    }
}
