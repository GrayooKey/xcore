package com.graykey.xcore.demo.service;

import com.graykey.xcore.demo.module.Demo;
import com.graykey.xcore.demo.vo.DemoVo;

import java.util.List;

/**
 * 示例	Service层接口
 *
 * @author xuezb
 * @date 2019-12-09
 */
public interface IDemoService {

    /**
     * 分页
     *
     * @param page
     * @param limit
     * @param demoVo
     * @return
     */
    //Pager queryEntityList(Integer page, Integer limit, DemoVo demoVo);

    /**
     * 保存or更新
     *
     * @param demoVo
     * @return
     */
    Demo saveOrUpdate(DemoVo demoVo);

    /**
     * 删除
     *
     * @param ids
     */
    void delete(String ids);

    /**
     * 分组统计
     *
     * @param demoVo  筛选条件
     * @return
     */
    List<List> queryBaseCount(DemoVo demoVo);

}