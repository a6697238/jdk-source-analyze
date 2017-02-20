package com.houlu.java.test.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 类名称: ProcessBuilderTest <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 17/1/4 上午12:13
 */
public class ProcessBuilderTest {

    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder();
        try {
            Process p = pb.command("/apps/apache-jmeter-3.0-modify/bin/jmeter", "-n", "-t", "/apps/apache-jmeter-3.0-modify/bin/test-random.jmx","-j","/apps/apache-jmeter-3.0-modify/bin/testLog/01.log").start();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
            String s = "";
//            while ((s = br.readLine()) != null) {
//                System.out.println(s);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
