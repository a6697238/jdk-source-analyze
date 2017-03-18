package com.houlu.java.test.reflect;

import com.houlu.java.test.bean.StudentBean;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

/**
 * 类名称: GetConstruct <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 16/11/12 下午2:52
 */
public class GetConstruct {

    public static void main(String[] args) throws NoSuchMethodException {

        Class<StudentBean> classType = StudentBean.class;

        // 使用getConstructors获取构造器
        Constructor<?>[] constructors = classType.getConstructors();
        for (Constructor<?> m : constructors) {
            System.out.println(m);
        }
        System.out.println();


        Constructor<StudentBean> constructor= classType.getConstructor(new Class<?>[]{String.class,String.class});
        System.out.println(constructor);
        System.out.println();

        // 使用getDeclaredConstructors获取构造器
        constructors = classType.getDeclaredConstructors();
        for (Constructor<?> m : constructors) {
            System.out.println(m);
        }

    }
}
