package com.houlu.java.test.compiler.classloader;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author Lu Hou
 * @date 2017/9/5
 * @time 下午12:19
 */
public class URLClassLoaderClient {

    public static void main(String[] args)throws Exception {


        // 1.创建ClassLoader，并设置目录为编译时的输出目录
        File file = new File("/apps/software/IDEA_WORK_SPACE/meituan-spring-demo/com/meituan/monitor/service/HelloServiceImpl.class");

        URL path = new URL( "file:///apps/software/IDEA_WORK_SPACE/meituan-spring-demo/" );
        URLClassLoader classLoader = new URLClassLoader(new URL[]{path});
        // 2.构建测试类的Class对象
        Class<?> testClass = classLoader.loadClass("com.meituan.monitor.service.HelloServiceImpl");

        // 3.获得测试类的方法
        Method testMethod = testClass.getDeclaredMethod("sayHello", String.class);

        // 4.创建一个测试类的实例
        Object testObj = testClass.newInstance();

        // 5.运行测试函数
        System.out.println(testMethod.invoke(testObj, args));

        classLoader.close();
    }
}
