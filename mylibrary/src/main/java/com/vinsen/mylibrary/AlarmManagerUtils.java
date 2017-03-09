package com.vinsen.mylibrary;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * 长时间定时任务的操作，一般小时级别的才用这个
 * Created by Vinsen on 17/3/8.
 */

public class AlarmManagerUtils {

    private static AlarmManager manager;

    // 1小时
    public static final long HOUR = 60 * 60 * 1000;

    public static void init(Context context) {
        manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }


    /**
     * @param triggerAtMillis 延时多久执行
     * @param intervalMillis  多久重复一次
     * @param operation       执行的 PendingIntent
     */
    public static void setRepeatToDo(long triggerAtMillis, long intervalMillis, PendingIntent operation) {
        if (manager == null) {
            throw new NullPointerException("have not init manager .");
        }
        manager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtMillis, intervalMillis, operation);
    }


    /**
     * @param triggerAtMillis 延时多久执行
     * @param operation       执行的 PendingIntent
     */
    public static void setToDo(long triggerAtMillis, PendingIntent operation) {
        if (manager == null) {
            throw new NullPointerException("have not init manager .");
        }
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtMillis, operation);
    }


    /**
     * 这里只是延时执行某个广播
     *
     * @param context         上下文
     * @param requestCode     请求的验证code
     * @param intent          启动什么广播
     * @param triggerAtMillis 延时多久执行
     */
    public static void setToDo(Context context, int requestCode, Intent intent, long triggerAtMillis) {
        if (manager == null) {
            throw new NullPointerException("have not init manager .");
        }
        PendingIntent operation = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_ONE_SHOT);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtMillis, operation);
    }

}
