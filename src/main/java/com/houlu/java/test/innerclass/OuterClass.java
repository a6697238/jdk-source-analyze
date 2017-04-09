package com.houlu.java.test.innerclass;

import java.util.HashMap;
import java.util.Map;

/**
 * 类名称: OuterClass <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 17/3/27 下午10:25
 */
public class OuterClass {

    private String outerName;

    private String outerSpace;

    public void checkInnerClass(InnerClass innerClass){
        System.out.println(innerClass.innerName);
        System.out.println(innerClass.innerSpace);
    }

    private static HashMap<String,Object> objectMap = new HashMap();

    public static class InnerClass{

        private String innerName;

        private String innerSpace;

        public void setInnerName(String innerName){
            this.innerName=innerName;
            objectMap.put(innerName,this);
        }

        public void setInnerSpace(String innerSpace){
            this.innerSpace=innerSpace;
        }
    }


    public static void main(String[] args) {
        OuterClass.InnerClass innerClass= new OuterClass.InnerClass();
        innerClass.setInnerName("innerName");
        OuterClass.InnerClass innerClass2= new OuterClass.InnerClass();
        innerClass2.setInnerName("innerName2");
        for(Map.Entry entry : objectMap.entrySet()){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}
