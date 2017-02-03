package com.houlu.java.test.gc;

import com.houlu.java.test.bean.CGLibBean;

import java.util.HashMap;

/**
 * 类名称: PermGenGC <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 16/11/14 上午10:19
 */
public class PermGenGC {

    public static void main(String[] args) throws ClassNotFoundException, InterruptedException {


        HashMap propertyMap = new HashMap();

        propertyMap.put("id", Class.forName("java.lang.Integer"));

        propertyMap.put("name", Class.forName("java.lang.String"));

        propertyMap.put("address", Class.forName("java.lang.String"));

        while (true){
            int i=0;
            propertyMap.put("address"+i, Class.forName("java.lang.String"));
            i++;
            // 生成动态 Bean
            CGLibBean bean = new CGLibBean(propertyMap);

            System.out.println(bean);
            Thread.sleep(1);
        }

    }
}
