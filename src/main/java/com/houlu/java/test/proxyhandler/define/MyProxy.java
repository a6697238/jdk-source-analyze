package com.houlu.java.test.proxyhandler.define;

import sun.reflect.Reflection;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 类名称: MyProxy <br>
 * 类描述: <br>
 *
 * 替换JDK生成代理对象
 *
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 17/3/18 上午11:15
 */
public class MyProxy {

    static String rt = "\r\t";

    protected MyInvocationHandler h;

    private MyProxy(){}

    protected MyProxy(MyInvocationHandler h){
        this.h = h;
    }

    public static Object createProxyInstance(ClassLoader classLoader,Class inter,MyInvocationHandler invocationHandler) throws Exception{

        System.out.println("=======构造一个自定义JAVA代理类对象");
        Method[] methods = inter.getMethods();
        String proxyClassString = "com.houlu.java.test.proxyhandler.define;"+rt
                +"import java.lang.reflect.Method;"+rt
                +"public class $MyProxy implements "+inter.getName()+"{"+rt
                +"MyInvocationHandler h;"+rt
                +"public $$MyProxy(MyInvocationHandler h){"+rt
                +"this.h=h;"+rt+"}"
                +getMethodString(methods,inter);
        //将我们的自定义代理类生成文件
        FileOutputStream fos = new FileOutputStream(new File(inter.getClass()+"$MyProxy"));
        fos.write(proxyClassString.getBytes());
        fos.close();
        //编译文件
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager =  compiler.getStandardFileManager(null,null,null);
        Iterable units = fileManager.getJavaFileObjects(inter.getClass()+"$MyProxy");
        JavaCompiler.CompilationTask compileTask = compiler.getTask(null,fileManager,null,null,null,units);
        compileTask.call();
        fileManager.close();
        return null;
    }

    private static String getMethodString(Method[] methods, Class inter) {
        String proxyMe="";
        for(Method method : methods){
            proxyMe +="public void "+method.getName()+"() throws Throwable {"+rt
                    +"Method md="+inter.getName()+".class.getMethod(\""
                    +method.getName()+"\",new Class[]{};"+rt
                    +" this.h.invoke(this,md,null);"+rt+"}"+rt;
        }
        System.out.println(proxyMe);
        return proxyMe;
    }


}
