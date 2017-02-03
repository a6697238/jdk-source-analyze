package com.houlu.java.test.reflect;

/**
 * 类名称: GetClass <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 16/11/12 下午2:28
 */
public class GetClass {

    //获取Class类的方法
    public static void main(String[] args) throws ClassNotFoundException {
        Boolean var1 = true;
        Class<?> classType2 = var1.getClass();
        System.out.println(classType2);


        Class<?> classType4 = Boolean.class;
        System.out.println(classType4);

        Class<?> classType5 = Class.forName("java.lang.Boolean");
        System.out.println(classType5);

    }
}
