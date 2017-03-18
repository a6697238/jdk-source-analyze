package com.houlu.java.test.proxyhandler.define;

import com.houlu.java.test.proxyhandler.RequestHandler;
import com.houlu.java.test.proxyhandler.RequestHandlerImpl;
import com.houlu.java.test.proxyhandler.RequestHandlerProxy;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
public class ProxyManual {
    public static void main(String[] args) throws Exception {
        RequestHandler requestHandler = new RequestHandlerImpl("测试构造器参数");
        InvocationHandler handler = new RequestHandlerProxy(requestHandler);
        Class<?> classType = requestHandler.getClass();
        RequestHandler requestHandlerProxy = (RequestHandler) Proxy.newProxyInstance(classType.getClassLoader(),
                requestHandler.getClass().getInterfaces(), handler);
        System.out.println(requestHandlerProxy.getClass());
        System.out.println(requestHandler.doRequest("代理发送请求"));
        createProxyClassFile();
    }

    //代理类存入项目路径
    public static void createProxyClassFile() throws Exception {
       byte[] classFile = ProxyGenerator.generateProxyClass("$myProxy01",new Class[]{RequestHandler.class});
        //通过IO写入入项目文件
        FileOutputStream fileOutputStream = new FileOutputStream(new File("$myProxy01.class"));
        fileOutputStream.write(classFile);
        fileOutputStream.close();
    }
}
