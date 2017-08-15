package com.houlu.java.test.security;

import java.io.*;
import java.security.Policy;

public class JavaPolicyTest {
    public static void main(String[] args) throws IOException {
//        System.setSecurityManager(new MySecurityManager(new SecurityManager()));
        Policy.setPolicy(new MySecurityPolicy());
        System.setSecurityManager(new SecurityManager());
        System.exit(0);
        File file = new File("/Users/yp-pc-m-7120/EclipseWorkSpace/JavaTest/jdk-source-analyze/src/main/resources/input.txt");
        File file2 = new File("/apps/netproxy.sql");

        file2.delete();
        Runtime.getRuntime().exec("ls /apps");

        try {
            read(file);
            System.out.println("file read ok");
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }

        try {
            write(file);
            System.out.println("file write ok");
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }

    private static void read(File file) throws Throwable {
        InputStream in = null;
        BufferedReader reader = null;
        try {
            in = new FileInputStream(file);
            reader = new BufferedReader(new InputStreamReader(in));
            String temp = null;
            while ((temp = reader.readLine()) != null) {
                System.out.println("read-->" + temp);
            }
        } catch (Throwable e) {
            throw e;
        } finally {
            if (in != null) {
                in.close();
            }
            if (reader != null) {
                reader.close();
            }
        }
    }

    private static void write(File file) throws Throwable {
        FileWriter fw = new FileWriter(file);
        for (int i = 0; i < 10; i++) {
            String temp = new java.util.Date() + " "
                    + new java.util.Random().nextLong();
            System.out.println("write-->" + temp);
            fw.write(temp + "\r\n");
        }
        fw.flush();
        fw.close();
    }
}