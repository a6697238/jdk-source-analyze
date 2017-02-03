package com.houlu.java.test.serialize;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 类名称: SerializeUtils <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 16/11/12 下午9:00
 */
public class SerializeUtils {
    public static byte[] serialize(Object obj) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        oos = new ObjectOutputStream(outputStream);
        oos.writeObject(obj);
        return outputStream.toByteArray();
    }


    public static Object deserialize(byte[] bytes) throws Exception{
        ObjectInputStream ois = null;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        ois = new ObjectInputStream(inputStream);
        return ois.readObject();
    }


    public static void main(String[] args) throws Exception {
        StudenDTO studenDTO = new StudenDTO();
        studenDTO.setJob("student");
        studenDTO.setSchool("high school");
        studenDTO.setStudentId("100");

        byte dtoByte[] = serialize(studenDTO);
        StudenDTO studenDTO1 = (StudenDTO) deserialize(dtoByte);

        Field[] filds = StudenDTO.class.getDeclaredFields();
        for(Field field:filds){
            StringBuffer sb = new StringBuffer();
            sb.append("get");
            sb.append(field.getName().substring(0, 1).toUpperCase());
            sb.append(field.getName().substring(1));
            Method method =  StudenDTO.class.getMethod(sb.toString());
            System.out.println(method.invoke(studenDTO1));
        }

    }


}