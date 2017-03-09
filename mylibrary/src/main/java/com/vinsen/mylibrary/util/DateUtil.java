package com.vinsen.mylibrary.util;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


public class DateUtil {

    private static final String DEFUALT_FORMAT = "yyyy-MM-dd";
    private static final String DETAILED_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String LSH_FORMAT = "yyyyMMdd";
    private static final String DETAILED_LSH_FORMAT = "yyyyMMddHHmmss";
    private static final String SIMPLE_FORMAT = "yyMMdd";
    private static final String NS_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
    private static final SimpleDateFormat defualtFormat = new SimpleDateFormat(
            DEFUALT_FORMAT, Locale.CHINA);
    private static final SimpleDateFormat detaledFormat = new SimpleDateFormat(
            DETAILED_FORMAT, Locale.CHINA);
    private static final SimpleDateFormat lshFormat = new SimpleDateFormat(
            LSH_FORMAT, Locale.CHINA);
    private static final SimpleDateFormat detaledLshFormat = new SimpleDateFormat(
            DETAILED_LSH_FORMAT, Locale.CHINA);
    private static final SimpleDateFormat simpleFormat = new SimpleDateFormat(
            SIMPLE_FORMAT, Locale.CHINA);
    private static final SimpleDateFormat nsFormat = new SimpleDateFormat(
            NS_FORMAT, Locale.CHINA);

    public static String defualtFormat(Date date) {
        if (date == null) {
            return "";
        } else {
            return defualtFormat.format(date);
        }
    }

    public static String detaledFormat(Date date) {
        if (date == null) {
            return "";
        } else {
            return detaledFormat.format(date);
        }
    }

    public static String lshFormat(Date date) {
        if (date == null) {
            return "";
        } else {
            return lshFormat.format(date);
        }
    }

    public static String simpleFormat(Date date) {
        if (date == null) {
            return "";
        } else {
            return simpleFormat.format(date);
        }
    }

    public static String detaledLshFormat(Date date) {
        if (date == null) {
            return "";
        } else {
            return detaledLshFormat.format(date);
        }
    }

    public static String nsFormat(Date date) {
        if (date == null) {
            return "";
        } else {
            return nsFormat.format(date);
        }
    }

    public static Date addYear(Date date, int year) {
        if (date == null) {
            return null;
        }
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }

    public static Date addDay(Date date, int day) {
        if (date == null) {
            return null;
        }
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    public static Date addMonth(Date date, int month) {
        if (date == null) {
            return null;
        }
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    public static Date addHour(Date date, int hour) {
        if (date == null) {
            return null;
        }
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hour);
        return calendar.getTime();
    }

    public static Date addMinute(Date date, int minute) {
        if (date == null) {
            return null;
        }
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    public static Date addSecond(Date date, int second) {
        if (date == null) {
            return null;
        }
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }

    public static Date setHour(Date date, int hour) {
        if (hour < 0) {
            hour = 0;
        } else if (hour > 23) {
            hour = 23;
        }
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        return calendar.getTime();
    }

    public static Date setMinute(Date date, int minute) {
        if (minute < 0) {
            minute = 0;
        } else if (minute > 59) {
            minute = 59;
        }
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    public static Date setSecond(Date date, int second) {
        if (second < 0) {
            second = 0;
        } else if (second > 59) {
            second = 59;
        }
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    public static Date parseDate(String str) {
        if (StringUtil.isNull(str)) {
            return null;
        }
        try {
            return detaledFormat.parse(str);
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Date nsDate(String str) {
        if (StringUtil.isNull(str)) {
            return null;
        }
        try {
            return nsFormat.parse(str);
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public static Date formatDate(String dateStr, String formatStr) {
        if (StringUtil.isNull(dateStr)) {
            return null;
        }
        if (StringUtil.isNull(formatStr)) {
            return null;
        }
        try {
            return new SimpleDateFormat(formatStr, Locale.CHINA).parse(dateStr);
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static boolean dateIsNight(String dateStr) {
        boolean flag = true;
        try {
            Date date = parseDate(dateStr);
            if (date.getHours() < 19 && date.getHours() > 6) {
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}