package com.houlu.java.test.compiler.memeory;


import com.houlu.java.test.compiler.JavaClassFileObject;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import java.io.IOException;

/**
 * @author Lu Hou
 * @date 2017/9/5
 * @time 上午10:37
 */
public class ClassFileManager extends ForwardingJavaFileManager {

    private JavaClassFileObject classFileObject;


    public JavaClassFileObject getClassFileObject() {
        return classFileObject;
    }

    /**
     * Creates a new instance of ForwardingJavaFileManager.
     *
     * @param fileManager delegate to this file manager
     */
    public ClassFileManager(JavaFileManager fileManager) {
        super(fileManager);
    }

    /**
     * Gets a JavaFileObject file object for output
     * representing the specified class of the specified kind in the given location.
     */
    @Override
    public JavaClassFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind,
                                                    FileObject sibling) throws IOException {
        classFileObject = new JavaClassFileObject(className, kind);
        return classFileObject;
    }

}
