package com.graykey.xcore.menu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 菜单配置	Controller层
 *
 * @author xuezb
 * @date 2019-12-07
 */
@Controller
@RequestMapping("/xcore/menuController")
public class MenuControllerController {

//    @Autowired
//    private IMenuControllerService menuControllerServiceImpl;
//
//
//    /**
//     * 列表页面
//     *
//     * @param httpSession
//     * @param request
//     * @param menuCode
//     * @return
//     */
//    @RequestMapping(value = "/menuController_list")
//    public String list(HttpSession httpSession, HttpServletRequest request, String menuCode) {
//        request.setAttribute("menuCode", menuCode);
//        return "/page/xxx/menuController/menuController_list";
//    }
//
//    /**
//     * 列表数据加载
//     *
//     * @param httpSession
//     * @param response
//     * @param page
//     * @param limit
//     * @param menuControllerVo
//     */
//    @RequestMapping(value = "/menuController_load")
//    public void load(HttpSession httpSession, HttpServletResponse response, Integer page, Integer limit, MenuControllerVo menuControllerVo) {
//        Pager pager = this.menuControllerServiceImpl.queryEntityList(page, limit, menuControllerVo);
//        JSONObject json = new JSONObject();
//        json.put("count", pager.getRowCount()); //总记录数
//        json.put("code", 0); //状态码
//        JsonConfig config = new JsonConfig(); //自定义JsonConfig用于过滤Hibernate配置文件所产生的递归数据
//        json.put("data", JSONArray.fromObject(pager.getPageList(), config));
//        this.print(json);
//    }
//
//    /**
//     * 编辑页面
//     *
//     * @param httpSession
//     * @param request
//     * @param menuControllerVo
//     * @return
//     */
//    @RequestMapping(value = "/menuController_edit")
//    public String edit(HttpSession httpSession, HttpServletRequest request, MenuControllerVo menuControllerVo) {
//        if (StringUtils.isNotBlank(menuControllerVo.getId())) {
//            MenuController menuController = this.menuControllerServiceImpl.getEntityById(MenuController.class, menuControllerVo.getId());
//            BeanUtils.copyProperties(menuController, menuControllerVo);
//            request.setAttribute("menuControllerVo", menuControllerVo);
//        } else {
//            request.setAttribute("menuControllerVo", menuControllerVo);
//        }
//        return "/page/xxx/menuController/menuController_edit";
//    }
//
//    /**
//     * 保存or更新
//     *
//     * @param httpSession
//     * @param response
//     * @param menuControllerVo
//     */
//    @RequestMapping(value = "/menuController_saveOrUpdate")
//    public void saveOrUpdate(HttpSession httpSession, HttpServletResponse response, MenuControllerVo menuControllerVo) {
//        JsonObject json = new JsonObject();
//        try {
//            MenuController menuController = this.menuControllerServiceImpl.saveOrUpdate(menuControllerVo);
//            json.addProperty("result", true);
//        } catch (Exception e) {
//            json.addProperty("result", false);
//            logger.error(e.getMessage(), e);
//        } finally {
//            this.print(json.toString());
//        }
//    }
//
//    /**
//     * 详情页面
//     *
//     * @param request
//     * @param id
//     * @return
//     */
//    @RequestMapping(value = "/menuController_detail")
//    public String detail(HttpServletRequest request, String id) {
//        if (StringUtils.isNotBlank(id)) {
//            MenuController menuController = this.menuControllerServiceImpl.getEntityById(MenuController.class, id);
//            MenuControllerVo menuControllerVo = new MenuControllerVo();
//            BeanUtils.copyProperties(menuController, menuControllerVo);
//            request.setAttribute("menuControllerVo", menuControllerVo);
//        }
//        return "/page/xxx/menuController/menuController_detail";
//    }
//
//    /**
//     * 删除
//     *
//     * @param response
//     * @param ids
//     */
//    @RequestMapping(value = "/menuController_delete")
//    public void delete(HttpServletResponse response, String ids) {
//        JsonObject json = new JsonObject();
//        try {
//            if (StringUtils.isNotBlank(ids)) {
//                this.menuControllerServiceImpl.delete(ids);
//                json.addProperty("result", true);
//            }
//        } catch (Exception e) {
//            json.addProperty("result", false);
//            logger.error(e.getMessage(), e);
//        } finally {
//            this.print(json.toString());
//        }
//    }
//
//    /**
//     * 分组统计
//     *
//     * @param session
//     * @param response
//     * @param menuControllerVo 筛选条件
//     */
//    @RequestMapping(value = "/menuController_getBaseCount")
//    public void getBaseCount(HttpSession session, HttpServletResponse response, MenuControllerVo menuControllerVo) {
//        String sysCode = (String) session.getAttribute("sysCode");
//        List<List> mapList = this.menuControllerServiceImpl.queryBaseCount(menuControllerVo, sysCode);
//        JSONArray jsonArray = JSONArray.fromObject(mapList);
//        this.print(jsonArray.toString());
//    }

}