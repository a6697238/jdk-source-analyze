package com.houlu.java.test.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTaskDemo
 *
 * @author hl162981
 * @date 2018/8/5
 */
public class FutureTaskDemo {

  public static void main(String[] args) throws ExecutionException, InterruptedException {

    FutureTask<Double> task = new FutureTask<Double>(new Callable<Double>() {

      @Override
      public Double call() throws Exception {
        return new Double(123.3);
      }
    });
    task.run();
    System.out.println(task.get());

  }

}
