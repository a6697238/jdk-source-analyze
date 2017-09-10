package com.houlu.java.test.compiler.memeory;


import com.houlu.java.test.compiler.JavaSourceFileObject;

import javax.tools.*;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Lu Hou
 * @date 2017/9/5
 * @time 上午10:55
 */
public class MemoryCompiler {

    public static byte[] compile(String code) throws Exception {

        // 1.获得JavaCompiler
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        // 2.创建一个DiagnosticCollector对象，用于获取编译输出信息
        DiagnosticCollector<JavaFileObject> diagnosticCollector = new DiagnosticCollector<JavaFileObject>();

        // 3.获得被修改后的JavaFileManager，用于管理需要编译的文件
        ClassFileManager fileManager = new ClassFileManager(compiler.getStandardFileManager(diagnosticCollector, null, null));

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

        // 6.获得编译任务
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnosticCollector, null, null, javaFileObjects);

        // 7.编译
        Boolean result =  task.call();


        /**
         * 输出结果
         */
        System.out.println(result);
        List list = diagnosticCollector.getDiagnostics();
        for (Object object : list) {
            Diagnostic d = (Diagnostic) object;
            System.out.println(d.getMessage(Locale.ENGLISH));
        }

        // 8.关闭JavaFileManager
        fileManager.close();

        return fileManager.getClassFileObject().getClassBytes();
    }
}
