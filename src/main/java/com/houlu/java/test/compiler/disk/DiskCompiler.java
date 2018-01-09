package com.houlu.java.test.compiler.disk;


import com.houlu.java.test.compiler.JavaSourceFileObject;

import javax.tools.*;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Lu Hou
 * @date 2017/9/4
 * @time 下午2:42
 */
public class DiskCompiler {

//    public static void main(String[] args) throws Exception {
//        String helloClassCode ="package com.meituan.monitor.service;\n" +
//                "\n" +
//                "/**\n" +
//                " * @author Lu Hou\n" +
//                " * @date 2017/9/4\n" +
//                " * @time 下午2:46\n" +
//                " */\n" +
//                "public class HelloServiceImpl implements HelloService {\n" +
//                "    @Override\n" +
//                "    public String sayHello(String content) {\n" +
//                "        return content + \" say hello\";\n" +
//                "    }\n" +
//                "}\n";
//        String thriftCode = "package com.meituan.thrift.demo.client;\n" +
//                "\n" +
//                "import com.meituan.payment.bankinfo.thrift.idl.BankInfoService;\n" +
//                "import com.meituan.payment.bankinfo.thrift.idl.BankResponse;\n" +
//                "import com.meituan.service.mobile.mtthrift.client.pool.MTThriftPoolConfig;\n" +
//                "import com.meituan.service.mobile.mtthrift.proxy.ThriftClientProxy;\n" +
//                "import org.springframework.context.ApplicationContext;\n" +
//                "import org.springframework.context.support.ClassPathXmlApplicationContext;\n" +
//                "\n" +
//                "/**\n" +
//                " * @author Lu Hou\n" +
//                " * @date 2017/9/4\n" +
//                " * @time 下午5:57\n" +
//                " */\n" +
//                "public class ThriftDirect {\n" +
//                "\n" +
//                "    public static void main(String[] args)throws Exception {\n" +
//                "        proxyThriftDemo();\n" +
//                "    }\n" +
//                "\n" +
//                "    public static void proxyThriftDemo()throws Exception {\n" +
//                "\n" +
//                "        ThriftClientProxy proxy = new ThriftClientProxy();\n" +
//                "        proxy.setAppKey(\"demo\");\n" +
//                "        proxy.setRemoteAppkey(\"com.sankuai.pay.fundstransfer.bankservice\");\n" +
//                "        proxy.setServiceInterface(Class.forName(\"com.meituan.payment.bankinfo.thrift.idl.BankInfoService\"));\n" +
//                "        proxy.setRemoteServerPort(9001);\n" +
//                "        proxy.afterPropertiesSet();  //初始化实例\n" +
//                "\n" +
//                "        BankInfoService.Iface bankInfoService = (BankInfoService.Iface) proxy.getObject(); //获取代理对象\n" +
//                "        BankResponse bankResponse = bankInfoService.getAllDisplayBankInfos();\n" +
//                "        System.out.println(bankResponse);\n" +
//                "\n" +
//                "        proxy.destroy();  //销毁实例\n" +
//                "\n" +
//                "    }\n" +
//                "\n" +
//                "}\n";
//        compileCode(helloClassCode);
//    }

    public static void compile(String code) throws Exception {

        // 1.获得JavaCompiler
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        // 2.创建一个DiagnosticCollector对象，用于获取编译输出信息
        DiagnosticCollector<JavaFileObject> diagnosticCollector = new DiagnosticCollector<JavaFileObject>();

        // 3.获得JavaFileManager对象，用于管理需要编译的文件
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnosticCollector, null, null);


        // 4.生成一个JavaFileObject对象，表示需要编译的源文件
        /**
         * 组装java源码并返回JavaFileObject
         */
        String javaFileContents = code;
        Pattern classPath = Pattern.compile("class\\s+([$_a-zA-Z][$_a-zA-Z0-9]*)\\s*");
        Matcher matcher = classPath.matcher(javaFileContents);
        String compileClassName;

        if (matcher.find()) {
            compileClassName = matcher.group(1);
        } else {
            throw new IllegalArgumentException("No such class name in " + javaFileContents);
        }
        JavaSourceFileObject javaSourceFileObject = new JavaSourceFileObject(compileClassName, javaFileContents.toString());



        // 5.组成一个JavaFileObject的遍历器
        Iterable<? extends JavaFileObject> javaFileObjects = Arrays.asList(javaSourceFileObject);

        // 6.编译后文件输出地址
        Iterable<String> options = Arrays.asList("-d", ".");

        // 7.获得编译任务
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnosticCollector, options, null, javaFileObjects);

        // 8.编译
        Boolean result = task.call();
        /**
         * 输出结果
         */
        System.out.println(result);
        List list = diagnosticCollector.getDiagnostics();
        for (Object object : list) {
            Diagnostic d = (Diagnostic) object;
            System.out.println(d.getMessage(Locale.ENGLISH));
        }

        // 9.关闭JavaFileManager
        fileManager.close();

    }


}
