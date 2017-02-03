package com.houlu.java.test.reflect;

import com.houlu.java.test.bean.StudentBean;

import java.lang.reflect.Method;

/**
 * 类名称: DoInvoke <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 16/11/12 下午8:18
 */
public class DoInvoke {
    public static void main(String[] args) throws Exception{
        Class<?> classType = StudentBean.class;
        Object inst = classType.newInstance();
        Method goSchoolMethod = classType.getDeclaredMethod("goSchool", String.class);
        goSchoolMethod.invoke(inst, "student A ");

        Method goSchoolMethodPrivate = classType.getDeclaredMethod("goSchoolprivate", String.class);
        goSchoolMethodPrivate.setAccessible(true);
        goSchoolMethodPrivate.invoke(inst, "student A ");
    }
}
