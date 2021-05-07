package com.demo.websocket.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static String getString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static Date getDate(String date) throws ParseException {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = null;
        parse = simpleDateFormat.parse(date);
        return parse;
    }

    /**
     * 给时间加上几个小时
     *
     * @param date 当前时间
     * @param hour 需要加的时间
     * @return Date
     */
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

    /**
     * 获取当前时间之前或之后几分钟 minute
     *
     * @param minute
     * @return String
     */
    public static String getTimeByMinute(int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minute);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
    }

    public static void main(String[] args) throws ParseException {
        String a = getTimeByMinute(-30);
        System.out.println(a);
    }


}
