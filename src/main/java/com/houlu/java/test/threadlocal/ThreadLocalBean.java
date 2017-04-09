package com.houlu.java.test.threadlocal;

/**
 * 类名称: ThreadLocalBean <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 17/3/26 上午12:05
 */
public class ThreadLocalBean {


    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
        public Integer initialValue() {
            return 0;
        }
    };


    public void addSeq(){
        seqNum.set(seqNum.get()+1);
        System.out.println("当前线程" + Thread.currentThread().getName() +" 的值为"+seqNum.get());
    }
}
