package com.frw.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fruwei on 2016/7/20.
 */
public class DateTimeHelper {

    public static  String getNowInStr() {
        Date now = new Date();
        DateFormat dateFormat = new SimpleDateFormat();
        return dateFormat.format(now);
    }
}
