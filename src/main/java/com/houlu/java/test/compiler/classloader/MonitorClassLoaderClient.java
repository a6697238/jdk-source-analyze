package com.houlu.java.test.compiler.classloader;


import com.houlu.java.test.compiler.MonitorClassLoader;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * @author Lu Hou
 * @date 2017/9/4
 * @time 下午11:50
 */
public class MonitorClassLoaderClient {

    public static void main(String[] args)throws Exception {
        FileInputStream fileInput = new FileInputStream("/apps/software/IDEA_WORK_SPACE/meituan-spring-demo/com/meituan/monitor/service/HelloServiceImpl.class");
        byte[] byt = new byte[fileInput.available()];
        fileInput.read(byt);
        MonitorClassLoader monitorClassLoader = new MonitorClassLoader(byt, Thread.currentThread().getContextClassLoader());
        Class<?> testClass = monitorClassLoader.loadClass("com.meituan.monitor.service.HelloServiceImpl");

        // 12.获得测试类的方法
        Method testMethod = testClass.getDeclaredMethod("sayHello", String.class);

        // 13.创建一个测试类的实例
        Object testObj = testClass.newInstance();

        // 14.运行测试函数
        System.out.println(testMethod.invoke(testObj, "hi"));
    }
}
