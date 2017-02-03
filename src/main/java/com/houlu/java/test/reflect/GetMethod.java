package com.houlu.java.test.reflect;

import com.houlu.java.test.bean.StudentBean;

import java.lang.reflect.Method;

/**
 * 类名称: GetMethod <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 16/11/12 下午2:43
 */
public class GetMethod {

    public static void main(String[] args) throws NoSuchMethodException {

        Class<?> classType = StudentBean.class;
        Method[] methods = classType.getMethods();
        for (Method m : methods) {
            System.out.println(m);
        }

        System.out.println();

// 使用getDeclaredMethods获取函数
        methods = classType.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(m);
        }

    }
}
