package com.houlu.java.test.security;

import java.io.FilePermission;
import java.security.Permission;

public class MySecurityManager extends SecurityManager {

    private SecurityManager baseSecurityManager;

    private final static String EXITVM = "exitVM";
    private final static String DELET_FILE = "delete";
    private final static String EXECUTE_FILE = "execute";

    private final static String TEMP_DIR = "/apps/.*";


    public MySecurityManager(SecurityManager baseSecurityManager) {
        this.baseSecurityManager = baseSecurityManager;
    }

    @Override
    public void checkPermission(Permission permission) {
//        if (permission.getName().startsWith(EXITVM)) {
//            throw new SecurityException("System exit not allowed");
//        }
//
//        if (permission.getClass().isAssignableFrom(FilePermission.class)) {
//            if (DELET_FILE.equals(permission.getActions())) {
//                if (!permission.getName().matches(TEMP_DIR)) {
//                    throw new SecurityException("delete file not allowed");
//                }
//            }
//            if(EXECUTE_FILE.equals(permission.getActions())){
//                throw new SecurityException("execute file exit not allowed");
//            }
//        }
//        if (baseSecurityManager != null) {
//            baseSecurityManager.checkPermission(permission);
//        } else {
//            return;
//        }
    }


    public static void main(String[] args) {
        String path = "/apps/.*";
        System.out.println("".matches(path));
        System.out.println("/apps/ypt".matches(path));

    }
}