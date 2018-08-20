package com.houlu.java.test.lambda;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

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
//    completionStageTest();
        parallHandle();
    }

    public static void completionStageTest() {
        CompletionStage<String> completionStage = CompletableFuture.completedFuture("word");

        completionStage.thenApply(CompletionStageDemo::firstPrint)
                .thenApply(val -> secondPrint(val))
                .thenAccept(val -> secondPrint(val));


    }

    public static String firstPrint(String val) {
        System.out.println(val);
        return "firstPrint" + val;
    }

    public static String secondPrint(String val) {
        System.out.println(val);
        return "secondPrint" + val;
    }

    public static void completableFutureTest() {
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
        String result = CompletableFuture.supplyAsync(() -> "hello").thenApply(s -> s + " world")
                .join();
        System.out.println(result);
    }

    public static void parallHandle() {
        Executor executor = Executors.newFixedThreadPool(100);
        Random random = new Random(100);

        List<Map<String, Integer>> todoList = Lists.newArrayList();
        for (int i = 0; i < 5000; i++) {
            Map map = Maps.newHashMap();
            map.put("prenum", i);
            todoList.add(map);
        }
        long start1 = System.currentTimeMillis();
        List<CompletableFuture<Map<String, Integer>>> priceFuture = todoList.stream()
                .map(todoMap -> CompletableFuture
                        .supplyAsync(() -> {
                            Integer val = todoMap.get("prenum");
                            todoMap.put("ordernum", (int) (Math.random()*1000));
                            return todoMap;
                        }, executor))
                .collect(Collectors.toList());
        List<Map<String, Integer>> resultList1 = priceFuture.stream().map(CompletableFuture::join)
                .sorted((s1, s2) -> {
                    Integer v1 = s1.get("ordernum");
                    Integer v2 = s2.get("ordernum");
                    return v1.compareTo(v2);

                })
                .collect(Collectors.toList());
        System.out.println("start 1 cost:"+ String.valueOf(System.currentTimeMillis()-start1));


        long start2 = System.currentTimeMillis();
        List<Map<String, Integer>> resultList2 = todoList.parallelStream().map(todoMap->{
            todoMap.put("ordernum", (int) (Math.random()*1000));
            return todoMap;
        }) .sorted((s1, s2) -> {
            Integer v1 = s1.get("ordernum");
            Integer v2 = s2.get("ordernum");
            return v1.compareTo(v2);

        }).collect(Collectors.toList());
        System.out.println("start 2 cost:"+ String.valueOf(System.currentTimeMillis()-start2));




//        resultList2.stream().forEach(todoMap ->{
//            System.out.println(todoMap.get("ordernum") + " : " + todoMap.get("prenum"));
//        });


    }


}
