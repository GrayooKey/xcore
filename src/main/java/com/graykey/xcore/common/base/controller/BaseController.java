package com.graykey.xcore.common.base.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 基础实体 Controller层
 *
 * @author xuezb
 * @date 2019-12-05
 */
public class BaseController {

    public static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;
    @Resource
    private HttpSession httpSession;


    /**
     * 向客户端输出内容
     * @param str 输出内容
     */
    public void print(Object str) {
        try {
            this.response.getWriter().print(str);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 获得当前session User对象
     * @return user对象
     */
//    public User getSessionUser(){
//        return (User)this.getHttpSession().getAttribute("user");
//    }

    public HttpServletRequest getRequest() {
        return request;
    }
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
    public HttpServletResponse getResponse() {
        return response;
    }
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
    public HttpSession getHttpSession() {
        return httpSession;
    }
    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }
}
