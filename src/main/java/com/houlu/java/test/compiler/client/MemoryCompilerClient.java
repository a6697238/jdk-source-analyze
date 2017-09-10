package com.houlu.java.test.compiler.client;


import com.houlu.java.test.compiler.MonitorClassLoader;
import com.houlu.java.test.compiler.memeory.MemoryCompiler;

import java.lang.reflect.Method;

/**
 * @author Lu Hou
 * @date 2017/9/5
 * @time 下午1:48
 */
public class MemoryCompilerClient {

    public static void main(String[] args) throws Exception {

        String code = "package com.meituan.monitor.service;\n" +
                "\n" +
                "/**\n" +
                " * @author Lu Hou\n" +
                " * @date 2017/9/4\n" +
                " * @time 下午2:46\n" +
                " */\n" +
                "public class HelloServiceImpl implements HelloService {\n" +
                "    @Override\n" +
                "    public String sayHello(String content) {\n" +
                "        return content + \" say hello\";\n" +
                "    }\n" +
                "}\n";
        String code2 = "package com.meituan.thrift.demo.client;\n" +
                "\n" +
                "import com.meituan.payment.bankinfo.thrift.idl.BankInfoService;\n" +
                "import com.meituan.payment.bankinfo.thrift.idl.BankResponse;\n" +
                "import com.meituan.service.mobile.mtthrift.client.pool.MTThriftPoolConfig;\n" +
                "import com.meituan.service.mobile.mtthrift.proxy.ThriftClientProxy;\n" +
                "import org.springframework.context.ApplicationContext;\n" +
                "import org.springframework.context.support.ClassPathXmlApplicationContext;\n" +
                "\n" +
                "/**\n" +
                " * @author Lu Hou\n" +
                " * @date 2017/9/4\n" +
                " * @time 下午5:57\n" +
                " */\n" +
                "public class ThriftDirect {\n" +
                "\n" +
                "    public static void main(String[] args)throws Exception {\n" +
                "        proxyThriftDemo();\n" +
                "    }\n" +
                "\n" +
                "    public static void proxyThriftDemo()throws Exception {\n" +
                "\n" +
                "        ThriftClientProxy proxy = new ThriftClientProxy();\n" +
                "        proxy.setAppKey(\"demo\");\n" +
                "        proxy.setRemoteAppkey(\"com.sankuai.pay.fundstransfer.bankservice\");\n" +
                "        proxy.setServiceInterface(Class.forName(\"com.meituan.payment.bankinfo.thrift.idl.BankInfoService\"));\n" +
                "        proxy.setRemoteServerPort(9001);\n" +
                "        proxy.afterPropertiesSet();  //初始化实例\n" +
                "\n" +
                "        BankInfoService.Iface bankInfoService = (BankInfoService.Iface) proxy.getObject(); //获取代理对象\n" +
                "        BankResponse bankResponse = bankInfoService.getAllDisplayBankInfos();\n" +
                "        System.out.println(bankResponse);\n" +
                "        proxy.destroy();  //销毁实例\n" +
                "    }\n" +
                "\n" +
                "}\n";
        byte[] classBytes = MemoryCompiler.compile(code);
        MonitorClassLoader monitorClassLoader = new MonitorClassLoader(classBytes, Thread.currentThread().getContextClassLoader());
        Class<?> testClass = monitorClassLoader.loadClass("com.meituan.thrift.demo.client.ThriftDirect");
        Method testMethod = testClass.getDeclaredMethod("proxyThriftDemo");
        Object testObj = testClass.newInstance();
        testMethod.invoke(testObj);
    }
}
