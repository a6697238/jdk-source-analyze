package com.houlu.java.test.generic;

import java.util.Objects;

/**
 * 类名称: GenericTestClient <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 17/4/9 下午10:47
 */
public class GenericTestClient {

    MethodHandler methodHandler = new MethodHandler();

    public <T> T callTemplate(MethodCall<T> methodCall) {
        return methodCall.run(methodHandler);
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        GenericMethod<String> genericMethod = new GenericMethod();
        String tt = genericMethod.getGenericMethod(String.class, (Object) "ceshi");
//        Integer tt = genericMethod.getGenericMethod("ceshi");
    }
}
