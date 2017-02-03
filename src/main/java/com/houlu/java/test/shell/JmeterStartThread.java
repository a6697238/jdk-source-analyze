package com.houlu.java.test.shell;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 类名称: JmeterStartThread <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 17/1/6 上午12:14
 */
public class JmeterStartThread extends Thread {

    private String out;

    private boolean stop;

    private Process process;


    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public JmeterStartThread(boolean stop, Process process) {
        this.stop = stop;
        this.process = process;
    }

    @Override
    public void run() {
        try {
            String line;
            InputStream is = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            while ((line = reader.readLine()) != null) {
                if(stop){
                    break;
                }
                out = out + line + "\n\r";
            }
            reader.close();
            is.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
