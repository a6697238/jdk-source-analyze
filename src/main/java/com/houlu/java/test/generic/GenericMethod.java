package com.houlu.java.test.generic;

/**
 * 类名称: GenericMethod <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 17/4/9 下午11:00
 */
public class GenericMethod<T> {

    public <T> T getGenericMethod(Class<T> c,Object data) throws IllegalAccessException, InstantiationException {
        T t = c.newInstance();
        return t;
    }

    public <T> T getGenericMethod(Object data){
        return (T) data;
    }

    public T getGenericClassMethod(Class<T> T,Object data){
        System.out.print(T);
        return (T) data;
    }
}
