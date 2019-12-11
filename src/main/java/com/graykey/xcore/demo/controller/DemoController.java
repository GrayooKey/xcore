package com.graykey.xcore.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.graykey.xcore.common.base.controller.BaseController;
import com.graykey.xcore.demo.dao.IDemoDao;
import com.graykey.xcore.demo.module.Demo;
import com.graykey.xcore.demo.module.QDemo;
import com.graykey.xcore.demo.service.IDemoService;
import com.graykey.xcore.demo.vo.DemoVo;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Visitor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;


/**
 * 示例	Controller层
 *
 * @author xuezb
 * @date 2019-12-09
 */
@Controller
@RequestMapping("/xcore/demo")
public class DemoController extends BaseController {

    @Autowired
    private IDemoService demoServiceImpl;
    @Autowired
    private IDemoDao iDemoDao;


    /**
     * 列表页面
     *
     * @param httpSession
     * @param request
     * @param menuCode
     * @return
     */
    @RequestMapping(value = "/demo_list")
    public String list(HttpSession httpSession, HttpServletRequest request, String menuCode) {
        request.setAttribute("menuCode", menuCode);
        return "/page/xcore/demo/demo_list";
    }

    /**
     * 列表数据加载
     *
     * @param httpSession
     * @param response
     * @param page
     * @param limit
     * @param demoVo
     */
    @RequestMapping(value = "/demo_load")
    public void load(HttpSession httpSession, HttpServletResponse response, Integer page, Integer limit, DemoVo demoVo) {
        System.out.println("測試開始");

        //这里的分页对象可以是单表的entity, 也可以是多表查询得到的Vo

        Predicate predicate = new Predicate() {
            @Override
            public Predicate not() {
                return null;
            }

            @Nullable
            @Override
            public <R, C> R accept(Visitor<R, C> visitor, @Nullable C c) {
                return null;
            }

            @Override
            public Class<? extends Boolean> getType() {
                return null;
            }
        };
        Pageable pageable = new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 0;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
        iDemoDao.findAll(predicate, pageable);

        getPageResult(response, null);

//        Pager pager = this.demoServiceImpl.queryEntityList(page, limit, demoVo);
//        JSONObject json = new JSONObject();
//        json.put("count", pager.getRowCount()); //总记录数
//        json.put("code", 0); //状态码
//        JsonConfig config = new JsonConfig(); //自定义JsonConfig用于过滤Hibernate配置文件所产生的递归数据
//        json.put("data", JSONArray.fromObject(pager.getPageList(), config));
//        this.print(json);
    }

    /**
     * 返回layui分页查询结果
     *
     * @param response
     * @param
     * @param excludePropertyArr 需要过滤的属性
     */
    public void getPageResult(HttpServletResponse response, @Nullable String[] excludePropertyArr) {
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        Set<String> set = filter.getExcludes();
        for (String excludeProperty : excludePropertyArr) {
            set.add(excludeProperty);
        }

        JSONObject json = new JSONObject();
        json.put("code", 0);    //成功的状态码
        json.put("msg", "");    //提示文本
        json.put("count", null);//数据总数
        json.put("data", JSON.toJSONString(null, filter)); //数据列表
        //json.put("data", JSONArray.parseArray(JSON.toJSONString(null, filter))); //数据列表
        this.print(json);
    }

    /**
     * 编辑页面
     *
     * @param httpSession
     * @param request
     * @param demoVo
     * @return
     */
    @RequestMapping(value = "/demo_edit")
    public String edit(HttpSession httpSession, HttpServletRequest request, DemoVo demoVo) {
        if (StringUtils.isNotBlank(demoVo.getId())) {
            //Demo demo = this.demoServiceImpl.getEntityById(Demo.class, demoVo.getId());
            Demo demo = this.iDemoDao.getOne(demoVo.getId());
            BeanUtils.copyProperties(demo, demoVo);
            request.setAttribute("demoVo", demoVo);
        } else {
            request.setAttribute("demoVo", demoVo);
        }
        return "/page/xcore/demo/demo_edit";
    }

    /**
     * 保存or更新
     *
     * @param httpSession
     * @param response
     * @param demoVo
     */
    @RequestMapping(value = "/demo_saveOrUpdate")
    public void saveOrUpdate(HttpSession httpSession, HttpServletResponse response, DemoVo demoVo) {
        JSONObject json = new JSONObject();
        try {
            Demo demo = this.demoServiceImpl.saveOrUpdate(demoVo);
            json.put("result", true);
        } catch (Exception e) {
            json.put("result", false);
            logger.error(e.getMessage(), e);
        } finally {
            this.print(json.toString());
        }
    }

    /**
     * 详情页面
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/demo_detail")
    public String detail(HttpServletRequest request, String id) {
        if (StringUtils.isNotBlank(id)) {
            //Demo demo = this.demoServiceImpl.getEntityById(Demo.class, id);
            Demo demo = this.iDemoDao.getOne(id);
            DemoVo demoVo = new DemoVo();
            BeanUtils.copyProperties(demo, demoVo);
            request.setAttribute("demoVo", demoVo);
        }
        return "/page/xcore/demo/demo_detail";
    }

    /**
     * 删除
     *
     * @param response
     * @param ids
     */
    @RequestMapping(value = "/demo_delete")
    public void delete(HttpServletResponse response, String ids) {
        JSONObject json = new JSONObject();
        try {
            if (StringUtils.isNotBlank(ids)) {
                this.demoServiceImpl.delete(ids);
                json.put("result", true);
            }
        } catch (Exception e) {
            json.put("result", false);
            logger.error(e.getMessage(), e);
        } finally {
            this.print(json.toString());
        }
    }

    /**
     * 分组统计
     *
     * @param session
     * @param response
     * @param demoVo   筛选条件
     */
    @RequestMapping(value = "/demo_getBaseCount")
    public void getBaseCount(HttpSession session, HttpServletResponse response, DemoVo demoVo) {
        List<List> mapList = this.demoServiceImpl.queryBaseCount(demoVo);
        this.print(JSONArray.toJSONString(mapList));
    }

}