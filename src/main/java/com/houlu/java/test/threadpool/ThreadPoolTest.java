package com.houlu.java.test.threadpool;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Lu Hou
 * @date 2017/11/4
 * @time 上午12:35
 */
public class ThreadPoolTest implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
