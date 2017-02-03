package com.houlu.java.test.reflect;

import com.houlu.java.test.bean.StudentBean;
import com.houlu.java.test.bean.UserBean;

import java.lang.reflect.Field;

/**
 * 类名称: GetFields <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 16/11/12 下午2:34
 */
public class GetFields {

    public static void main(String[] args) {

        Class<?> classType = StudentBean.class;

//        getFields返回的是申明为public的属性，包括父类中定义，
//        getDeclaredFields返回的是指定类定义的所有定义的属性，不包括父类的。

// 使用getFields获取属性
        Field[] fields = classType.getFields();
        for (Field f : fields) {
            System.out.println(f);
        }

        System.out.println();

// 使用getDeclaredFields获取属性
        fields = classType.getDeclaredFields();
        for (Field f : fields) {
            System.out.println(f);
        }
    }
}
