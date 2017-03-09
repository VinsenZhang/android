package com.vinsen.mylibrary;

import android.os.Looper;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by op on 17/3/8.
 */

public class ThreadManager {


    private static ThreadPoolExecutor executor;

    /**
     * @param corePoolSize    线程池维护线程的最少数量
     * @param maximumPoolSize 线程池维护线程的最大数量
     * @param keepAliveTime   线程池维护线程所允许的空闲时间
     */
    public static void init(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
        executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());
    }


    public static void doInBackGround(Runnable runnable) {
        if (executor == null) {
            throw new NullPointerException("have not init executor,please init first");
        }
        if (!isMainThread()) {
            executor.execute(runnable);
        } else {
            throw new RuntimeException("it is in main thread .");
        }
    }


    public static void doInMainThread(Runnable runnable) {
        if (executor == null) {
            throw new NullPointerException("have not init executor,please init first");
        }
        if (isMainThread()) {
            executor.execute(runnable);
        } else {
            throw new RuntimeException("it is not in main thread .");
        }
    }


    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

}
