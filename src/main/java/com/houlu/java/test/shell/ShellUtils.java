package com.houlu.java.test.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 类名称: ShellUtils <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 17/1/4 上午12:13
 */
public class ShellUtils {

    public static void execute(String command) throws IOException {
        try {
            Process p = Runtime.getRuntime().exec(command);
            JmeterStartThread jmeterStartThread = new JmeterStartThread(false,p);
            jmeterStartThread.start();
            p.waitFor(3000, TimeUnit.MILLISECONDS);
            jmeterStartThread.setStop(true);
            System.out.println(jmeterStartThread.getOut());
            if(!jmeterStartThread.getOut().contains("Created the tree successfully")){
                throw new RuntimeException();
            }
            p.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String startCommand = "/apps/apache-jmeter-3.0-1/bin/jmeter -n -t /apps/ypt/performance/type_jmx/test-random.jmx";
//        String startCommand = "/Users/yp-pc-m-7120/Software/Jmeter/apache-jmeter-3.0/bin/ypt-jmeter-start.sh /Users/yp-pc-m-7120/Software/Jmeter/apache-jmeter-3.0/bin/test-random.jmx ./test.log";
        System.out.println(startCommand);
        execute(startCommand);

//        String stopCommand = "ps -ef |grep jmeter |awk '{print $2}'|xargs kill -9";
//        String[] command = {"/bin/sh", "-c", stopCommand};

//        System.out.println("ps -ef |grep "+"jmeter" +" |awk '{print $2}'|xargs kill -9");
//        execute("ps -ef |grep "+"jmeter" +" |awk '{print $2}'|xargs kill -9");
//        System.out.println(stopCommand);
//        Process p = Runtime.getRuntime().exec(command);
//        Thread.sleep(50000);
        System.out.println("finish");
    }


}
