package com.houlu.java.test.threadlocal;

/**
 * 类名称: ThreadLocalTestDemo <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 17/3/26 上午12:11
 */
public class ThreadLocalTestDemo {

    public static void main(String[] args) {
        ThreadLocalBean threadLocalBean = new ThreadLocalBean();

        ThreadCount t1 =  new ThreadCount(threadLocalBean);
        ThreadCount t2 =  new ThreadCount(threadLocalBean);
        ThreadCount t3 =  new ThreadCount(threadLocalBean);

        t1.start();
        t2.start();
        t3.start();


    }

    private static class ThreadCount extends Thread{
        private ThreadLocalBean threadLocalBean;

        public ThreadCount(ThreadLocalBean threadLocalBean){
            this.threadLocalBean=threadLocalBean;
        }

        @Override
        public void run() {
            for(int i=0;i<3;i++){
                threadLocalBean.addSeq();
            }
        }
    }
}
