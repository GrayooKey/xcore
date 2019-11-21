package com.graykey.xcore.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
public class IndexController {

    @RequestMapping(value = "/test")
    @ResponseBody
    public String test(HttpServletResponse response) {
        return "呵呵哒";
    }


    @RequestMapping(value = "/")
    public String test(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("time", new Date());
        return "page/index/index";
    }

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        return "page/index";
    }

}
