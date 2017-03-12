package com.houlu.java.test.cocurrent.count.down.latch.bean;

import java.util.concurrent.CountDownLatch;

/**
 * 类名称: NetworkHealthChecker <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 17/2/26 下午6:09
 */
public class NetworkHealthChecker extends BaseHealthChecker {

    public NetworkHealthChecker (CountDownLatch latch)  {
        super("Network Service", latch);
    }

    @Override
    public void verifyService()
    {
        System.out.println("Checking " + this.getServiceName());
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is UP");
    }

}
