package com.houlu.java.test.proxyhandler.define;

import java.lang.reflect.Method;

/**
 * 类名称: MyInvocationHandler <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 17/3/18 上午11:17
 */
public interface MyInvocationHandler {

    Object invoke(Object proxy, Method method, Object object)
            throws Throwable;

}
