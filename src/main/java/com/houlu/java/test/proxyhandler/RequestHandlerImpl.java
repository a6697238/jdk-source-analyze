package com.houlu.java.test.proxyhandler;

/**
 * 类名称: RequestHandlerImpl <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 16/11/12 下午8:44
 */
public class RequestHandlerImpl implements RequestHandler {

    public RequestHandlerImpl(String constructParam){
        System.out.println("RequestHandlerImpl :"+constructParam);
    }

    public String doRequest(String content) {
        System.out.println("RequestHandlerImpl do requset, content is " + content);
        return (content +"requset ok");
    }
}
