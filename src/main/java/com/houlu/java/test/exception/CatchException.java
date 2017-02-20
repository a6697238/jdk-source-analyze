package com.houlu.java.test.exception;

import java.io.IOException;

/**
 * 类名称: CatchException <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 17/2/20 上午11:46
 */
public class CatchException {

    public static void main(String[] args) {
        try {
            System.out.println("抛出异常");
            Thread.sleep(1000);
            throw new RuntimeException("runtimeexception");
        }catch (RuntimeException r){
            System.out.println("RuntimeException catch");
        }catch (InterruptedException i){
            System.out.println("InterruptedException catch");
        }catch (Exception e){
            System.out.println("Exception catch");
        }

    }
}
