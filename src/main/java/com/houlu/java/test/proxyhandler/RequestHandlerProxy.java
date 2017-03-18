package com.houlu.java.test.proxyhandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 类名称: RequestHandlerProxy <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 16/11/12 下午8:44
 */
public class RequestHandlerProxy implements InvocationHandler {

    private RequestHandler requestHandler;

    public RequestHandlerProxy(RequestHandler requestHandler){
        this.requestHandler = requestHandler;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        doProxy();
        return method.invoke(requestHandler, args);
    }

    public void doProxy(){
        System.out.println(this.getClass()+"正在代理");
    }
}
