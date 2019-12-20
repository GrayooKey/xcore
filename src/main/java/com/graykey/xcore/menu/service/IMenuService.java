package com.graykey.xcore.menu.service;

import com.graykey.xcore.menu.module.Menu;
import com.graykey.xcore.menu.module.MenuAttribute;
import com.graykey.xcore.menu.vo.MenuAttributeVo;
import com.graykey.xcore.menu.vo.MenuVo;
import org.springframework.data.domain.Page;

import java.util.List;


/**
 * 菜单配置	Service层接口
 *
 * @author xuezb
 * @date 2019-12-20
 */
public interface IMenuService {

    /**
     * 分页   菜单
     *
     * @param page
     * @param size
     * @param menuVo
     * @return
     */
    Page queryEntityList(Integer page, Integer size, MenuVo menuVo);

    /**
     * 分页   菜单功能
     *
     * @param page
     * @param size
     * @param menuId 菜单ID
     * @return
     */
    Page queryEntityList(Integer page, Integer size, String menuId);

    /**
     * 保存或更新    菜单
     *
     * @param menuVo
     * @return
     */
    Menu saveOrUpdate(MenuVo menuVo);

    /**
     * 保存或更新    菜单功能
     *
     * @param menuAttributeVo
     * @return
     */
    MenuAttribute saveOrUpdate(MenuAttributeVo menuAttributeVo);

    /**
     * 删除   菜单
     *
     * @param ids
     */
    void delete(String ids);

    /**
     * 删除   菜单功能
     *
     * @param ids
     */
    void deleteAttr(String ids);


    /**
     * 获取菜单集合
     *
     * @param menuVo 筛选条件
     * @return
     */
    List<Menu> queryEntityList(MenuVo menuVo);

    /**
     * 获取菜单功能集合
     *
     * @param menuId 菜单ID
     * @param menuAttributeVo 筛选条件
     * @return
     */
    List<MenuAttribute> queryMenuAttributeList(String menuId, MenuAttributeVo menuAttributeVo);

    /**
     * 根据菜单编码获取菜单
     *
     * @param menuCode 菜单编码
     * @return
     */
    Menu getMenuByMenuCode(String menuCode);


    /**
     * 根据ID查询实体对象   菜单
     *
     * @param id
     * @return
     */
    Menu getEntityById(String id);

    /**
     * 根据ID查询实体对象   菜单功能
     *
     * @param id
     * @return
     */
    MenuAttribute getMenuAttributeById(String id);

    /**
     * 更新实体时设置基础值   菜单
     *
     * @param menu 要更新的实体
     * @return
     */
    Menu getBaseModuleValue(Menu menu);

    /**
     * 更新实体时设置基础值   菜单功能
     *
     * @param menuAttribute 要更新的实体
     * @return
     */
    MenuAttribute getBaseModuleValue(MenuAttribute menuAttribute);

}