package com.houlu.java.test.reflect;

import com.houlu.java.test.bean.StudentBean;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 类名称: GetInstance <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 16/11/12 下午8:12
 */
public class GetInstance {

    public static void main(String[] args) throws Exception{

        //无参的构造方法
        Class<?> classType = StudentBean.class;
        Object inst = classType.newInstance();
        System.out.println(inst);

        System.out.println();

        Constructor<?> constructor1 = classType.getConstructor();
        inst = constructor1.newInstance();
        System.out.println(inst);

        System.out.println();

        //有参的构造方法
        Constructor<?> constructor2 = classType.getDeclaredConstructor(String.class);
        inst = constructor2.newInstance("123");
        System.out.println(inst);
    }
}
