package com.houlu.java.test.lambda;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * CompletionStageDemo
 *
 * @author hl162981
 * @date 2018/8/5
 */
public class CompletionStageDemo {

  public static void main(String[] args) {
//    completableFutureTest();
//    thenApply();
    completionStageTest();
  }

  public static void completionStageTest(){
    CompletionStage<String> completionStage =CompletableFuture.completedFuture("word");

    completionStage.thenApply(CompletionStageDemo::firstPrint)
        .thenApply(val -> secondPrint(val))
        .thenAccept(val -> secondPrint(val));

  }

  public static String firstPrint(String val){
    System.out.println(val);
    return "firstPrint" + val;
  }

  public static String secondPrint(String val){
    System.out.println(val);
    return "secondPrint" + val;
  }

  public static void completableFutureTest(){
    CompletableFuture<Double> futurePrice = getPriceAsync();
    //do anything you want, 当前线程不被阻塞
    System.out.println(111);
    //线程任务完成的话，执行回调函数，不阻塞后续操作
    futurePrice.whenComplete((aDouble, throwable) -> {
      System.out.println(aDouble);
      //do something else
    });
    System.out.println(222);
  }

  public static CompletableFuture<Double> getPriceAsync() {
    CompletableFuture<Double> futurePrice = new CompletableFuture<>();
    new Thread(() -> {
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      futurePrice.complete(23.55);
    }).start();
    return futurePrice;
  }

  public static void thenApply() {
    String result = CompletableFuture.supplyAsync(() -> "hello").thenApply(s -> s + " world").join();
    System.out.println(result);
  }


}
