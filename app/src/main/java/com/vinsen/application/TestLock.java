package com.vinsen.application;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by op on 17/3/20.
 */

public class TestLock {

    private String[] array = new String[50];

    private int currentIndex;


    private Lock lock;

    private Condition isFull;
    private Condition isEmpty;

    public TestLock() {
        lock = new ReentrantLock();
        isFull = lock.newCondition();
        isEmpty = lock.newCondition();
    }

    public void add(String str) {
        lock.lock();

        try {
            if (currentIndex == array.length - 1) {
                isFull.await();
            }
            array[currentIndex] = str;
            currentIndex++;

            isEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }


    public String get() {
        String str = null;
        lock.lock();

        try {
            if (array[currentIndex] == null) {
                isEmpty.await();
            }
            str = array[currentIndex];
            array[currentIndex] = null;
            currentIndex--;

            isFull.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return str;
    }

}
