package com.houlu.java.test.compiler;

import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

/**
 * @author Lu Hou
 * @date 2017/9/5
 * @time 上午10:36
 */
public class JavaClassFileObject extends SimpleJavaFileObject {
    //用于存储class字节
    private ByteArrayOutputStream outputStream;

    public JavaClassFileObject(String className, Kind kind) {
        super(URI.create("string:///" + className.replace('.', '/') + kind.extension), kind);
        outputStream = new ByteArrayOutputStream();
    }

    @Override
    public OutputStream openOutputStream() throws IOException {
        return outputStream;
    }

    public byte[] getClassBytes() {
        return outputStream.toByteArray();
    }
}
