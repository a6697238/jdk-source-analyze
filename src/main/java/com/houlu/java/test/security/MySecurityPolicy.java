package com.houlu.java.test.security;

import java.io.FilePermission;
import java.security.Permission;
import java.security.Policy;
import java.security.ProtectionDomain;

/**
 * 类名称: MySecurityPolicy <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 17/7/2 下午3:09
 */
public class MySecurityPolicy extends Policy {


    private final static String EXITVM = "exitVM";
    private final static String DELET_FILE = "delete";
    private final static String EXECUTE_FILE = "execute";

    private final static String TEMP_DIR = "/apps/.*";

    @Override
    public boolean implies(ProtectionDomain domain, Permission permission) {
        if (permission.getName().startsWith(EXITVM)) {
            throw new SecurityException("System exit not allowed");
        }

        if (permission instanceof  FilePermission ) {
            if (DELET_FILE.equals(permission.getActions())) {
                if (!permission.getName().matches(TEMP_DIR)) {
                    throw new SecurityException("delete file not allowed");
                }
            }
            if(EXECUTE_FILE.equals(permission.getActions())){
                throw new SecurityException("execute file exit not allowed");
            }
        }
        return true;
    }
}
