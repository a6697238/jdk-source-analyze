package com.houlu.java.test.generic;

/**
 * 类名称: MethodHandler <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 17/4/9 下午10:44
 */
public class MethodHandler {

    public boolean addMethod(){
        System.out.println("增加方法执行");
        return true;
    }

    public boolean remoceMethod(){
        System.out.println("删除方法执行");
        return true;
    }

    public <T> T getResult(Class<T> T,Object data){
        return (T) data;
    }
}
