package com.houlu.java.test.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * @author Lu Hou
 * @date 2017/10/22
 * @time 下午10:36
 */
public class ListDemo {

    public static void main(String[] args) {

        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        for (String feature : features) {
            System.out.print(feature);
        }
        System.out.println();
        // Java 8之后：
        features.forEach(n -> System.out.print(n));
        System.out.println();

// 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
// 看起来像C++的作用域解析运算符
        features.forEach(System.out::print);
        System.out.println();

    }
}
