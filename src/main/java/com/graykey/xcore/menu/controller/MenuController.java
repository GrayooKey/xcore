package com.graykey.xcore.menu.controller;

import com.alibaba.fastjson.JSONObject;
import com.graykey.xcore.common.base.controller.BaseController;
import com.graykey.xcore.menu.module.Menu;
import com.graykey.xcore.menu.module.MenuAttribute;
import com.graykey.xcore.menu.service.IMenuService;
import com.graykey.xcore.menu.vo.MenuAttributeVo;
import com.graykey.xcore.menu.vo.MenuVo;
import com.graykey.xcore.user.module.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


/**
 * 菜单配置	Controller层
 *
 * @author xuezb
 * @date 2019-12-20
 */
@Controller
@RequestMapping("/xcore/menu")
public class
MenuController extends BaseController {

    @Autowired
    private IMenuService menuServiceImpl;


    /**
     * 左侧菜单
     *
     * @param session
     * @param request
     * @param menuId 菜单ID
     * @return
     */
    @RequestMapping(value = "/menu_menuIndex")
    public String menuIndex(HttpSession session, HttpServletRequest request, String menuId) {
        User user = this.getSessionUser();
        Menu menu = this.menuServiceImpl.getEntityById(menuId);
        if (menu != null) {
            request.setAttribute("menuId", menuId);
            request.setAttribute("winName", menu.getWinName());
            request.setAttribute("menuCode", menu.getMenuCode());
        }
        //获得用户菜单
        List<List<Menu>> list = this.getMenu(user);
        if (list.get(0) != null) {
            request.setAttribute("secondList", list.get(0));
        }
        if (list.get(1) != null) {
            request.setAttribute("thirdList", list.get(1));
        }
        if (list.get(2) != null) {
            request.setAttribute("fourList", list.get(2));
        }
        if (list.get(3) != null) {
            request.setAttribute("fiveList", list.get(3));
        }
        return "/page/xcore/menu/menuIndex";
    }

    /**
     * 获得菜单
     *
     * @param user 当前登录用户
     * @return
     */
    public List<List<Menu>> getMenu(User user){
        List<List<Menu>> list = new ArrayList<>();
        List<Menu> secondList = new ArrayList<>();
        List<Menu> thirdList = new ArrayList<>();
        List<Menu> fourList = new ArrayList<>();
        List<Menu> fiveList = new ArrayList<>();
        List<Menu> firstMapList = new ArrayList<>();
        //超级管理员
        if ("1".equals(user.getUserType())) {
            MenuVo menuVo = new MenuVo();
            menuVo.setMenuType(12);     //菜单类型  普通菜单和超管菜单
            menuVo.setState(1);         //可用状态
            menuVo.setMenuLevel(2);     //二级菜单
            secondList = menuServiceImpl.queryEntityList(menuVo);
            menuVo.setMenuLevel(3);//三级菜单
            thirdList = menuServiceImpl.queryEntityList(menuVo);
            menuVo.setMenuLevel(4);//四级菜单
            fourList = menuServiceImpl.queryEntityList(menuVo);
            menuVo.setMenuLevel(5);//五级菜单
            fiveList = menuServiceImpl.queryEntityList(menuVo);
            //地图菜单
            menuVo.setMenuType(3);//3：地图菜单编码
            menuVo.setMenuLevel(2);
            firstMapList = menuServiceImpl.queryEntityList(menuVo);
        } else {
            //普通用户
//            Set<Role> roles = roleService.queryUserRoleList(user.getId());
//            if(roles != null && roles.size() > 0){
//                for (Role role : roles) {//一用户多角色 合并起来
//                    for(RoleRight roleRights :role.getRoleRights()){
//                        Menu menu= roleRights.getMenu();
//                        if(menu.getState()==1){//可用状态
//                            if(menu.getMenuType()==2){//普通菜单
//                                if(menu.getMenuLevel()==2){//二级菜单
//                                    secondList.add(menu);
//                                }else if(menu.getMenuLevel()==3){//三级菜单
//                                    thirdList.add(menu);
//                                }else if(menu.getMenuLevel()==4){//四级菜单
//                                    fourList.add(menu);
//                                }else if(menu.getMenuLevel()==5){//五级菜单
//                                    fiveList.add(menu);
//                                }
//                            }else if(menu.getMenuType()==3){//地图菜单
//                                if(menu.getMenuLevel()==2){//二级菜单
//                                    firstMapList.add(menu);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            secondList = removeDuplicate(secondList);
//            thirdList = removeDuplicate(thirdList);
//            fourList = removeDuplicate(fourList);
//            firstMapList = removeDuplicate(firstMapList);
//            fiveList = removeDuplicate(fiveList);
        }
        ComparatorMenu comparator = new ComparatorMenu();
        secondList.sort(comparator);
        list.add(0, secondList);
        thirdList.sort(comparator);
        list.add(1, thirdList);
        fourList.sort(comparator);
        list.add(2, fourList);
        fiveList.sort(comparator);
        list.add(3, fiveList);
        firstMapList.sort(comparator);
        list.add(4, firstMapList);
        return list;
    }

    //菜单排序
    public class ComparatorMenu implements Comparator<Object> {
        @Override
        public int compare(Object arg0, Object arg1) {
            Menu menu0=(Menu)arg0;
            Menu menu1=(Menu)arg1;
            return menu0.getSortNum().compareTo(menu1.getSortNum());
        }
    }

    //排重
    private List removeDuplicate(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }


    /**
     * 加载菜单树
     *
     * @param response
     * @param id 菜单ID
     */
    @RequestMapping(value = "/menu_loadTree")
    public void loadTree(HttpServletResponse response, String id) {
        StringBuffer tree = new StringBuffer();
        tree.append("[");
        if (StringUtils.isBlank(id)) {
            id = "0";   //初始化菜单 根节点为 0
            Menu m = this.menuServiceImpl.getEntityById(id);
            if (m != null) {
                tree.append("{");
                tree.append("id:'" + m.getId() + "',");
                tree.append("pId:'',");
                tree.append("name:'" + m.getMenuName() + "',");
                tree.append("open:true");
                tree.append("},");
            }
        }
        MenuVo menuVo = new MenuVo();
        menuVo.setpId(id);
        List<Menu> menuList = this.menuServiceImpl.queryEntityList(menuVo);
        if (menuList != null && menuList.size() > 0) {
            for (Menu menu : menuList) {
                tree.append("{");
                tree.append("id:'" + menu.getId() + "',");
                tree.append("pId:'" + menu.getpId() + "',");
                tree.append("name:'" + menu.getMenuName() + "',");
                if (menu.getIsLeaf() != null && menu.getIsLeaf() == 0) {
                    tree.append("isParent:true");
                }
                tree.append("},");
            }
        }
        tree.deleteCharAt(tree.toString().length() - 1);
        tree.append("]");
        this.print(tree.toString());
    }

    /**
     * 判断菜单是否存在
     *
     * @param menuCode
     * @param id       菜单ID
     */
    @RequestMapping(value = "/menu_isExist")
    public void isExistMenu(String menuCode, String id) {
        JSONObject json = new JSONObject();
        if (StringUtils.isNotBlank(menuCode)) {
            Menu menu = this.menuServiceImpl.getMenuByMenuCode(menuCode);
            if (menu != null) {
                if (StringUtils.isNotBlank(id) && !id.equals(menu.getId())) {
                    json.put("result", true);
                } else {
                    json.put("result", false);
                }
            } else {
                json.put("result", false);
            }
        } else {
            json.put("result", false);
        }
        this.print(json);
    }

    /**
     * 根据菜单上级节点id 获得当前新增菜单排序
     *
     * @param pId 当前新增菜单上级节点id
     */
    @RequestMapping(value = "/menu_getSortNum")
    public void getSortNum(String pId){
        JSONObject json = new JSONObject();
        if (StringUtils.isNotBlank(pId)) {
            int sortNum = 1;
            MenuVo menuVo = new MenuVo();
            menuVo.setpId(pId);
            List<Menu> menuList = this.menuServiceImpl.queryEntityList(menuVo);
            if(menuList != null && menuList.size() > 0){
                sortNum += menuList.size();
            }
            json.put("result", true);
            json.put("sortNum", sortNum);
        } else {
            json.put("result", false);
        }
        this.print(json);
    }

    /**
     * 图标选择
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/menu_chooseIcon")
    public String chooseIcon(HttpServletRequest request, String icon) {
        request.setAttribute("iconName", icon);
        return "/page/xcore/menu/menu_icon_choose";
    }



    /**
     * 菜单 列表页面
     *
     * @param request
     * @param menuCode
     * @return
     */
    @RequestMapping(value = "/menu_list")
    public String list(HttpServletRequest request, String menuCode) {
        request.setAttribute("menuCode", menuCode);
        return "/page/xcore/menu/menu_list";
    }

    /**
     * 菜单 列表数据加载
     *
     * @param response
     * @param page
     * @param limit
     * @param menuVo
     */
    @RequestMapping(value = "/menu_load")
    public void load(HttpServletResponse response, Integer page, Integer limit, MenuVo menuVo) {
        Page pager = this.menuServiceImpl.queryEntityList(page, limit, menuVo);
        this.getPageResult(response, pager, new String[]{"roleRights", "menuAttributes"});
    }

    /**
     * 菜单 编辑页面
     *
     * @param request
     * @param menuVo
     * @return
     */
    @RequestMapping(value = "/menu_edit")
    public String edit(HttpServletRequest request, MenuVo menuVo) {
        if (StringUtils.isNotBlank(menuVo.getId())) {
            Menu menu = this.menuServiceImpl.getEntityById(menuVo.getId());
            BeanUtils.copyProperties(menu, menuVo);
            request.setAttribute("menuVo", menuVo);
        } else {
            request.setAttribute("menuVo", menuVo);
        }
        return "/page/xcore/menu/menu_edit";
    }

    /**
     * 菜单 保存或更新
     *
     * @param response
     * @param menuVo
     */
    @RequestMapping(value = "/menu_saveOrUpdate")
    public void saveOrUpdate(HttpServletResponse response, MenuVo menuVo) {
        JSONObject json = new JSONObject();
        try {
            Menu menu = this.menuServiceImpl.saveOrUpdate(menuVo);
            json.put("result", true);
            json.put("id", menu.getId());
        } catch (Exception e) {
            json.put("result", false);
            logger.error(e.getMessage(), e);
        } finally {
            this.print(json.toString());
        }
    }

    /**
     * 菜单 删除
     *
     * @param response
     * @param ids
     */
    @RequestMapping(value = "/menu_delete")
    public void delete(HttpServletResponse response, String ids) {
        JSONObject json = new JSONObject();
        try {
            if (StringUtils.isNotBlank(ids)) {
                this.menuServiceImpl.delete(ids);
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
     * 菜单功能 列表数据加载
     *
     * @param response
     * @param page
     * @param limit
     * @param menuId 菜单ID
     */
    @RequestMapping(value = "/menuAttribute_load")
    public void loadAttribute(HttpServletResponse response, Integer page, Integer limit, String menuId) {
        Page pager = this.menuServiceImpl.queryEntityList(page, limit, menuId);
        this.getPageResult(response, pager, new String[]{"menu", "roles"});
    }

    /**
     * 菜单功能 编辑页面
     *
     * @param request
     * @param menuAttributeVo
     * @return
     */
    @RequestMapping(value = "/menuAttribute_edit")
    public String editAttribute(HttpServletRequest request, MenuAttributeVo menuAttributeVo) {
//        Set<CategoryAttribute> set = Cache.getDictByCode.get("menu_attributeCode");
//        request.setAttribute("menuAttributeCodes", set);
        if (StringUtils.isNotBlank(menuAttributeVo.getId())) {
            MenuAttribute menuAttribute = this.menuServiceImpl.getMenuAttributeById(menuAttributeVo.getId());
            BeanUtils.copyProperties(menuAttribute, menuAttributeVo);
            request.setAttribute("menuAttributeVo", menuAttributeVo);
        } else {
            request.setAttribute("menuAttributeVo", menuAttributeVo);
        }
        return "/page/xcore/menu/menuAttribute_edit";
    }

    /**
     * 菜单功能 保存或更新
     *
     * @param response
     * @param menuAttributeVo
     */
    @RequestMapping(value = "/menuAttribute_saveOrUpdate")
    public void saveOrUpdateAttribute(HttpServletResponse response, MenuAttributeVo menuAttributeVo) {
        JSONObject json = new JSONObject();
        try {
            MenuAttribute menuAttribute = this.menuServiceImpl.saveOrUpdate(menuAttributeVo);
            json.put("result", true);
        } catch (Exception e) {
            json.put("result", false);
            logger.error(e.getMessage(), e);
        } finally {
            this.print(json.toString());
        }
    }

    /**
     * 菜单功能 删除
     *
     * @param response
     * @param ids
     */
    @RequestMapping(value = "/menuAttribute_delete")
    public void deleteAttribute(HttpServletResponse response, String ids) {
        JSONObject json = new JSONObject();
        try {
            if (StringUtils.isNotBlank(ids)) {
                this.menuServiceImpl.deleteAttr(ids);
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
     * 根据菜单id 获得当前新增菜单功能排序
     *
     * @param response
     * @param menuId 菜单id
     */
    @RequestMapping(value = "/menuAttribute_getSortNum")
    public void getmenuAttributeSortNum(HttpServletResponse response, String menuId){
        JSONObject json = new JSONObject();
        if (StringUtils.isNotBlank(menuId)) {
            int sortNum = 1;
            List<MenuAttribute> menuAttributeList = this.menuServiceImpl.queryMenuAttributeList(menuId, new MenuAttributeVo());
            if(menuAttributeList != null && menuAttributeList.size() > 0){
                sortNum += menuAttributeList.size();
            }
            json.put("result", true);
            json.put("sortNum", sortNum);
        } else {
            json.put("result", false);
        }
        this.print(json);
    }

    /**
     * 获取指定菜单下的功能属性编码集合
     *
     * @param response
     * @param menuId 菜单ID
     */
    @RequestMapping(value = "/menuAttribute_getAttribute")
    public void getAttribute(HttpServletResponse response, String menuId){
        JSONObject json = new JSONObject();
        if (StringUtils.isNotBlank(menuId)) {
            List<MenuAttribute> menuAttributes = this.menuServiceImpl.queryMenuAttributeList(menuId, new MenuAttributeVo());
            List<String> attributeCodeList = new ArrayList<>();
            if(!menuAttributes.isEmpty()){
                for (MenuAttribute menuAttribute : menuAttributes) {
                    attributeCodeList.add(menuAttribute.getAttributeCode());
                }
            }
            json.put("result", true);
            json.put("attributeCodeList", attributeCodeList);
        } else {
            json.put("result", false);
        }
        this.print(json);
    }

}