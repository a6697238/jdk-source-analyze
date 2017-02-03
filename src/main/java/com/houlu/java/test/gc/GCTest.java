package com.houlu.java.test.gc;

import java.util.HashMap;
import java.util.Map;

/**
 * 类名称: GCTest <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 16/11/13 下午3:32
 */
public class GCTest {
    public static void main(String[] args) throws Exception {
        while (true) {
            Map map = new HashMap();
            byte[] testBytes = new byte[1024];
            for (int i = 0; i < testBytes.length; i++) {
                testBytes[i] = 1;
            }
            map.put("CESHI",testBytes);
            Thread.sleep(1);
        }
    }
}
