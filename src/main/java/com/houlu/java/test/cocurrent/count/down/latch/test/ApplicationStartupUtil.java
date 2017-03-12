package com.houlu.java.test.cocurrent.count.down.latch.test;

import com.houlu.java.test.cocurrent.count.down.latch.bean.BaseHealthChecker;
import com.houlu.java.test.cocurrent.count.down.latch.bean.CacheHealthChecker;
import com.houlu.java.test.cocurrent.count.down.latch.bean.DatabaseHealthChecker;
import com.houlu.java.test.cocurrent.count.down.latch.bean.NetworkHealthChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 类名称: ApplicationStartupUtil <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 17/2/26 下午6:11
 */
public class ApplicationStartupUtil {
    //List of service checkers
    private static List<BaseHealthChecker> _services;

    //This latch will be used to wait on
    private static CountDownLatch _latch;

    private ApplicationStartupUtil() {
    }

    private final static ApplicationStartupUtil INSTANCE = new ApplicationStartupUtil();

    public static ApplicationStartupUtil getInstance() {
        return INSTANCE;
    }

    public static boolean checkExternalServices() throws Exception {
        //Initialize the latch with number of service checkers
        _latch = new CountDownLatch(3);

        //All add checker in lists
        _services = new ArrayList<BaseHealthChecker>();
        _services.add(new NetworkHealthChecker(_latch));
        _services.add(new CacheHealthChecker(_latch));
        _services.add(new DatabaseHealthChecker(_latch));

        //Start service checkers using executor framework
        Executor executor = Executors.newFixedThreadPool(_services.size());

        for (final BaseHealthChecker v : _services) {
            executor.execute(v);
        }

        //Now wait till all services are checked
        _latch.await();

        //Services are file and now proceed startup
        for (final BaseHealthChecker v : _services) {
            if (!v.isServiceUp()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args)
    {
        boolean result = false;
        try {
            result = ApplicationStartupUtil.checkExternalServices();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("External services validation completed !! Result was :: "+ result);
    }
}
