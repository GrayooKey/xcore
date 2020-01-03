package com.graykey.xcore.role.roleRight.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.graykey.xcore.common.base.controller.BaseController;
import com.graykey.xcore.menu.module.Menu;
import com.graykey.xcore.menu.module.MenuAttribute;
import com.graykey.xcore.menu.service.IMenuService;
import com.graykey.xcore.menu.vo.MenuVo;
import com.graykey.xcore.role.role.module.Role;
import com.graykey.xcore.role.role.service.IRoleService;
import com.graykey.xcore.role.roleRight.module.RoleRight;
import com.graykey.xcore.role.roleRight.service.IRoleRightService;
import com.graykey.xcore.role.roleRight.vo.RoleRightVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 角色权限	Controller层
 *
 * @author xuezb
 * @date 2020-01-03
 */
@Controller
@RequestMapping("/xcore/roleRight")
public class RoleRightController extends BaseController {

    @Autowired
    private IRoleRightService roleRightServiceImpl;
    @Autowired
    private IMenuService menuServiceImpl;
    @Autowired
    private IRoleService roleService;


    /**
     * 列表页面
     *
     * @param request
     * @param menuCode
     * @return
     */
    @RequestMapping(value = "/roleRight_list")
    public String list(HttpServletRequest request, String menuCode) {
        request.setAttribute("menuCode", menuCode);
        return "/page/xcore/role/roleRight/roleRight_list";
    }

    /**
     * 加载菜单树
     *
     * @param response
     * @param
     * @param roleId 角色id
     */
    @RequestMapping(value = "/roleRight_menuTree")
    public void menuTree(HttpServletResponse response, String id, String roleId) {
        StringBuffer menuIds = new StringBuffer();
        if (StringUtils.isNotBlank(roleId)) {
            RoleRightVo roleRightVo = new RoleRightVo();
            Role role = new Role();
            role.setId(roleId);
            roleRightVo.setRole(role);
            //该角色 关联的 角色权限集合
            List<RoleRight> roleRightList = this.roleRightServiceImpl.queryEntityList(roleRightVo);
            for (RoleRight roleRight : roleRightList) {
                menuIds.append(roleRight.getMenu().getId() + ",");
            }
        }

        //菜单树
        StringBuffer tree = new StringBuffer();
        tree.append("[");
        if(StringUtils.isBlank(id)){
            id = "0";//初始化菜单 根节点为 0
            Menu m = this.menuServiceImpl.getEntityById(id);
            if(m != null){
                tree.append("{");
                tree.append("id:'"+m.getId()+"',");
                tree.append("pId:'',");
                tree.append("name:'"+m.getMenuName()+"',");
                if (menuIds.toString().indexOf(m.getId()) > -1) {
                    tree.append("checked:true,");
                }
                tree.append("open:true");
                tree.append("},");
            }
        }
        MenuVo menuVo = new MenuVo();
        menuVo.setpId(id);
        List<Menu> menuList = this.menuServiceImpl.queryEntityList(menuVo);
        if(menuList != null && menuList.size() > 0){
            for (Menu menu : menuList) {
                tree.append("{");
                tree.append("id:'"+menu.getId()+"',");
                tree.append("pId:'"+menu.getpId()+"',");
                tree.append("name:'"+menu.getMenuName()+"',");
                if (menuIds.toString().contains(menu.getId())) {
                    tree.append("checked:true,");
                }
                if(menu.getIsLeaf() != null && menu.getIsLeaf() == 0){
                    tree.append("isParent:true");
                }
                tree.append("},");
            }
        }
        tree.deleteCharAt(tree.toString().length()-1);
        tree.append("]");
        this.print(tree.toString());
    }


    /**
     * 保存角色菜单权限
     *
     * @param response
     * @param menuIds 菜单ids(每个id之间用逗号隔开)
     * @param roleId 角色id
     */
    @RequestMapping(value = "/roleRight_saveOrUpdate")
    public void saveOrUpdate(HttpServletResponse response, String menuIds, String roleId) {
        JSONObject json = new JSONObject();
        try {
            if (StringUtils.isNotBlank(roleId) && StringUtils.isNotBlank(menuIds)) {
                this.roleRightServiceImpl.saveOrUpdate(menuIds, roleId);
                json.put("result", true);
            } else {
                json.put("result", false);
            }
        } catch (Exception e) {
            json.put("result", false);
            logger.error(e.getMessage(), e);
        } finally {
            this.print(json.toString());
        }
    }

    /**
     * 清除角色菜单权限
     *
     * @param response
     * @param roleId  角色id
     */
    @RequestMapping(value = "/roleRight_clean")
    public void clean(HttpServletResponse response, String roleId) {
        JSONObject json = new JSONObject();
        try {
            if (StringUtils.isNotBlank(roleId)) {
                this.roleRightServiceImpl.saveClean(roleId);
                json.put("result", true);
            } else {
                json.put("result", false);
            }
        } catch (Exception e) {
            json.put("result", false);
            logger.error(e.getMessage(), e);
        } finally {
            this.print(json.toString());
        }
    }


    /**
     * 菜单功能项列表数据加载
     *
     * @param response
     * @param menuId 菜单id
     */
    @RequestMapping(value = "/roleRight_loadMenuAttribute")
    public void loadMenuAttribute(HttpServletResponse response, String menuId, String roleId) {
        Role role = this.roleService.getEntityById(roleId);
        String mIds = "";
        if (null != role.getMenuAttribute() && role.getMenuAttribute().size() > 0) {
            for (MenuAttribute m : role.getMenuAttribute()) {
                mIds += m.getId() + ",";
            }
        }

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        List<MenuAttribute> menuAttributeList = this.roleRightServiceImpl.queryMenuAttributeList(menuId);
        if (menuAttributeList != null && menuAttributeList .size() > 0) {
            for (MenuAttribute menuAttribute : menuAttributeList) {
                jsonObject.put("id", menuAttribute.getId());
                jsonObject.put("attributeName", menuAttribute.getAttributeName());
                if (mIds.indexOf(menuAttribute.getId()) > -1) {
                    jsonObject.put("LAY_CHECKED", true);
                } else {
                    jsonObject.put("LAY_CHECKED", false);
                }
                jsonArray.add(jsonObject);
            }
        }
        this.print(jsonArray.toString());
    }

    /**
     * 根据 角色id 获取角色权限集合中的菜单ids
     *
     * @param response
     * @param roleId   角色id
     */
    @RequestMapping(value = "/roleRight_queryRoleRightListByRoleId")
    public void queryRoleRightListByRoleId(HttpServletResponse response, String roleId) {
        JSONObject json = new JSONObject();
        if (StringUtils.isNotBlank(roleId)) {
            RoleRightVo roleRightVo = new RoleRightVo();
            Role role = new Role();
            role.setId(roleId);
            roleRightVo.setRole(role);
            List<RoleRight> roleRightList = this.roleRightServiceImpl.queryEntityList(roleRightVo);

            String menuIds = "";
            if (null != roleRightList && roleRightList.size() > 0) {
                for (RoleRight roleRight : roleRightList) {
                    menuIds += roleRight.getMenu().getId() + ",";
                }
                menuIds = menuIds.substring(0, menuIds.length() - 1);
            }
            json.put("menuIds", menuIds);
            json.put("result", true);
        } else {
            json.put("result", false);
        }
        this.print(json.toString());
    }

    /**
     * 保存角色菜单功能
     *
     * @param response
     * @param menuId 菜单id
     * @param menuAttributeIds 菜单功能ids(每个id之间用逗号隔开)
     * @param roleId 角色id
     */
    @RequestMapping(value = "/roleRight_saveOrUpdateMenuAttribute")
    public void saveOrUpdateMenuAttribute(HttpServletResponse response, String menuId, String menuAttributeIds, String roleId) {
        JSONObject json = new JSONObject();
        try {
            if (StringUtils.isNotBlank(roleId) && StringUtils.isNotBlank(menuId)) {
                this.roleRightServiceImpl.saveOrUpdateMenuAttribute(menuId, menuAttributeIds, roleId);
                json.put("result", true);
            } else {
                json.put("result", false);
            }
        } catch (Exception e) {
            json.put("result", false);
            logger.error(e.getMessage(), e);
        } finally {
            this.print(json.toString());
        }
    }

    /**
     * 清除角色菜单功能
     *
     * @param response
     * @param menuId  菜单id
     * @param roleId  角色id
     */
    @RequestMapping(value = "/roleRight_cleanMenuAttribute")
    public void cleanMenuAttribute(HttpServletResponse response, String menuId, String roleId) {
        JSONObject json = new JSONObject();
        try {
            if (StringUtils.isNotBlank(roleId) && StringUtils.isNotBlank(menuId)) {
                this.roleRightServiceImpl.saveCleanMenuAttribute(menuId, roleId);
                json.put("result", true);
            } else {
                json.put("result", false);
            }
        } catch (Exception e) {
            json.put("result", false);
            logger.error(e.getMessage(), e);
        } finally {
            this.print(json.toString());
        }
    }

}