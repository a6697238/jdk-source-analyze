package com.houlu.java.test.compiler;

/**
 * @author Lu Hou
 * @date 2017/9/4
 * @time 下午11:17
 */
public class MonitorClassLoader extends ClassLoader {

    private byte[] classByte;

    public MonitorClassLoader() {
    }

    public MonitorClassLoader(byte[] classByte, ClassLoader parent) {
        super(parent);
        this.classByte = classByte;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return defineClass(null, classByte, 0, classByte.length);
    }
}
