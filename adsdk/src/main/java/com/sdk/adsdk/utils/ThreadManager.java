package com.sdk.adsdk.utils;

import android.os.Looper;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程统一管理，避免乱开线程，造成浪费
 * Created by Vinsen on 17/3/8.
 */

public class ThreadManager {


    private static ThreadPoolExecutor executor;

    /**
     * corePoolSize    线程池维护线程的最少数量
     * maximumPoolSize 线程池维护线程的最大数量
     * keepAliveTime   线程池维护线程所允许的空闲时间
     */
    public static void init() {
        int corePoolSize = Runtime.getRuntime().availableProcessors() + 1;
        int maximumPoolSize = corePoolSize * 2 + 1;
        executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());
    }

    // 后台执行一个任务
    public static void doInBackGround(Runnable runnable) {
        if (executor == null) {
            throw new NullPointerException("have not init executor,please init first");
        }
        executor.execute(runnable);
    }


    // 执行一个callable  返回一个Future
    public static <T> Future<T> getCallableRes(Callable<T> callable) {
        if (executor == null) {
            throw new NullPointerException("have not init executor,please init first");
        }
        return executor.submit(callable);
    }


    // 判断线程池内是否还有线程在工作
    public static boolean isAllFinish() {
        if (executor == null) {
            throw new NullPointerException("have not init executor,please init first");
        }
        return executor.getActiveCount() == 0;
    }


    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

}
