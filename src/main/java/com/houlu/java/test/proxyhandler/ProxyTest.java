package com.houlu.java.test.proxyhandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 类名称: ProxyTest <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 16/11/12 下午8:49
 */
public class ProxyTest {
    public static void main(String[] args) {
        RequestHandler requestHandler = new RequestHandlerImpl();
        InvocationHandler handler = new RequestHandlerProxy(requestHandler);
        Class<?> classType = requestHandler.getClass();
        RequestHandler requestHandlerProxy = (RequestHandler) Proxy.newProxyInstance(classType.getClassLoader(),
                requestHandler.getClass().getInterfaces(), handler);
        System.out.println(requestHandlerProxy.getClass());


        System.out.println(requestHandlerProxy.doRequest("请求节点"));

    }
}
