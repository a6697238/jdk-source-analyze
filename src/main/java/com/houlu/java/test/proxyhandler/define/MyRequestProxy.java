package com.houlu.java.test.proxyhandler.define;

import java.lang.reflect.Method;

/**
 * 类名称: MyRequestProxy <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 17/3/18 上午11:20
 */
public class MyRequestProxy implements MyInvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object object) throws Throwable {
        doProxy();
        return null;
    }

    public void doProxy(){
        System.out.println(this.getClass()+"正在执行代理");
    }
}
