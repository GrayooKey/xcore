package com.graykey.xcore.role.roleRight.service;

import com.graykey.xcore.menu.module.MenuAttribute;
import com.graykey.xcore.role.roleRight.module.RoleRight;
import com.graykey.xcore.role.roleRight.vo.RoleRightVo;

import java.util.List;


/**
 * 角色权限	Service层接口
 *
 * @author xuezb
 * @date 2020-01-03
 */
public interface IRoleRightService {

    /**
     * 根据查询条件获得角色权限集合
     *
     * @param roleRightVo
     * @return List<RoleRight>
     */
    List<RoleRight> queryEntityList(RoleRightVo roleRightVo);

    /**
     * 保存角色菜单权限
     *
     * @param menuIds 菜单ids(每个id之间用逗号隔开)
     * @param roleId 角色id
     */
    void saveOrUpdate(String menuIds, String roleId);

    /**
     * 清除角色菜单权限
     *
     * @param roleId 角色id
     */
    void saveClean(String roleId);


    /**
     * 根据 菜单id 获取菜单功能项集合
     *
     * @param menuId 菜单id
     * @return List<MenuAttribute>
     */
    List<MenuAttribute> queryMenuAttributeList(String menuId);

    /**
     * 保存角色菜单功能
     *
     * @param menuId 菜单id
     * @param menuAttributeIds 菜单功能ids(每个id之间用逗号隔开)
     * @param roleId 角色id
     */
    void saveOrUpdateMenuAttribute(String menuId, String menuAttributeIds, String roleId);

    /**
     * 清除角色菜单功能
     *
     * @param menuId 菜单id
     * @param roleId 角色id
     */
    void saveCleanMenuAttribute(String menuId, String roleId);


    /**
     * 根据ID查询实体对象
     *
     * @param id
     * @return
     */
    RoleRight getEntityById(String id);

}