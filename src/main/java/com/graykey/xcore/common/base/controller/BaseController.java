package com.graykey.xcore.common.base.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Set;

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
     * 返回layui分页查询结果
     *
     * @param response
     * @param pager 分页对象
     * @param excludePropertyArr 需要过滤的属性
     */
    public void getPageResult(HttpServletResponse response, Page pager, @Nullable String[] excludePropertyArr) {
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        Set<String> set = filter.getExcludes();
        if (excludePropertyArr != null) {
            for (String excludeProperty : excludePropertyArr) {
                set.add(excludeProperty);
            }
        }
        JSONObject json = new JSONObject();
        json.put("code", 0);    //成功的状态码
        json.put("msg", "");    //提示文本
        json.put("count", pager.getTotalElements());//数据总数
        json.put("data", JSONArray.parseArray(JSON.toJSONString(pager.getContent(), filter, SerializerFeature.WriteMapNullValue))); //数据列表
        this.print(json);
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
