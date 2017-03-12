package com.houlu.java.test.cocurrent.count.down.latch.bean;

import java.util.concurrent.CountDownLatch;

/**
 * 类名称: BaseHealthChecker <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 17/2/26 下午6:08
 */
public abstract class BaseHealthChecker implements Runnable {

    private CountDownLatch _latch;
    private String _serviceName;
    private boolean _serviceUp;

    //Get test object in constructor so that after completing the task, thread can countDown() the test
    public BaseHealthChecker(String serviceName, CountDownLatch latch) {
        super();
        this._latch = latch;
        this._serviceName = serviceName;
        this._serviceUp = false;
    }

    @Override
    public void run() {
        try {
            verifyService();
            _serviceUp = true;
        } catch (Throwable t) {
            t.printStackTrace(System.err);
            _serviceUp = false;
        } finally {
            if (_latch != null) {
                _latch.countDown();
            }
        }
    }

    public String getServiceName() {
        return _serviceName;
    }

    public boolean isServiceUp() {
        return _serviceUp;
    }

    //This methos needs to be implemented by all specific service checker
    public abstract void verifyService();

}
