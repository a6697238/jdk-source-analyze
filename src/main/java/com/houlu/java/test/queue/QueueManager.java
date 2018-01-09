package com.houlu.java.test.queue;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Lu Hou
 * @date 2017/11/4
 * @time 上午12:33
 */
public class QueueManager {

    private static Queue<String> queue = new LinkedBlockingQueue<>();

    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(5));

    private static ReentrantLock poolLock = new ReentrantLock();

    private static Condition condition = poolLock.newCondition();


    public static void main(String[] args) throws InterruptedException {

        Thread consumThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    poolLock.lock();
                    try {
                        while (queue.size()==0){
                            condition.await();
                        }
                        String str = queue.poll();
                        threadPool.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    System.out.println(str);
                                    Thread.sleep(200);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        poolLock.unlock();
                    }
                }
            }
        });
        consumThread.start();
        int i =0;
        while (true){
            poolLock.lock();
            try {
                queue.offer("str : "+i);
                i++;
                System.out.println("线程池中活跃的线程数： " + threadPool.getPoolSize());
                System.out.println("阻塞任务： " + threadPool.getQueue().size());
                System.out.println("队列任务： " + queue.size());
                condition.signalAll();
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                poolLock.unlock();
            }
            if(i%100==0){
                Thread.sleep(1000);
            }
        }
    }

}
