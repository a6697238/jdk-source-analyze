package com.houlu.java.test.cocurrent.count.down.latch.bean;

import java.util.concurrent.CountDownLatch;

/**
 * 类名称: DatabaseHealthChecker <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 17/2/26 下午6:12
 */
public class DatabaseHealthChecker extends BaseHealthChecker {

    public DatabaseHealthChecker (CountDownLatch latch)  {
        super("Database Service", latch);
    }

    @Override
    public void verifyService()
    {
        System.out.println("Checking " + this.getServiceName());
        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is UP");
    }
}
