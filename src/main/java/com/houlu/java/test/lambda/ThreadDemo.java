package com.houlu.java.test.lambda;

/**
 * @author Lu Hou
 * @date 2017/10/22
 * @time 下午10:28
 */
public class ThreadDemo {

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8, too much code for too little to do");
            }
        }).start();
        //Java 8方式：
        new Thread( () -> System.out.println("In Java8, Lambda expression rocks !!") ).start();
    }
}
