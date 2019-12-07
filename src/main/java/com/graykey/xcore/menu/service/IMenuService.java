package com.graykey.xcore.menu.service;

/**
 * 菜单配置	Service层接口
 *
 * @author xuezb
 * @date 2019-12-07
 */
public interface IMenuService {

    /**
     * 分页
     *
     * @param page
     * @param limit
     * @param menuVo
     * @return
     */
    //Pager queryEntityList(Integer page, Integer limit, MenuVo menuVo);

    /**
     * 保存or更新
     *
     * @param menuVo
     * @return
     */
    //Menu saveOrUpdate(MenuVo menuVo);

    /**
     * 删除
     *
     * @param ids
     */
    //void delete(String ids);

    /**
     * 分组统计
     *
     * @param menuVo  筛选条件
     * @param sysCode 子系统编码
     * @return
     */
    //List<List> queryBaseCount(MenuVo menuVo, String sysCode);

}