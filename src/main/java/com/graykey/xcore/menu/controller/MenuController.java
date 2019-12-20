package com.graykey.xcore.menu.controller;

import com.alibaba.fastjson.JSONObject;
import com.graykey.xcore.common.base.controller.BaseController;
import com.graykey.xcore.menu.module.Menu;
import com.graykey.xcore.menu.service.IMenuService;
import com.graykey.xcore.menu.vo.MenuVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 菜单配置	Controller层
 *
 * @author xuezb
 * @date 2019-12-20
 */
@Controller
@RequestMapping("/xcore/menu")
public class MenuController extends BaseController {

    @Autowired
    private IMenuService menuServiceImpl;


    /**
     * 列表页面
     *
     * @param httpSession
     * @param request
     * @param menuCode
     * @return
     */
    @RequestMapping(value = "/menu_list")
    public String list(HttpSession httpSession, HttpServletRequest request, String menuCode) {
        request.setAttribute("menuCode", menuCode);
        return "/page/xcore/menu/menu_list";
    }

    /**
     * 列表数据加载
     *
     * @param httpSession
     * @param response
     * @param page
     * @param limit
     * @param menuVo
     */
    @RequestMapping(value = "/menu_load")
    public void load(HttpSession httpSession, HttpServletResponse response, Integer page, Integer limit, MenuVo menuVo) {
        Page pager = this.menuServiceImpl.queryEntityList(page, limit, menuVo);
        this.getPageResult(response, pager, null);
    }

    /**
     * 编辑页面
     *
     * @param httpSession
     * @param request
     * @param menuVo
     * @return
     */
    @RequestMapping(value = "/menu_edit")
    public String edit(HttpSession httpSession, HttpServletRequest request, MenuVo menuVo) {
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
     * 保存or更新
     *
     * @param httpSession
     * @param response
     * @param menuVo
     */
    @RequestMapping(value = "/menu_saveOrUpdate")
    public void saveOrUpdate(HttpSession httpSession, HttpServletResponse response, MenuVo menuVo) {
        JSONObject json = new JSONObject();
        try {
            Menu menu = this.menuServiceImpl.saveOrUpdate(menuVo);
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
    @RequestMapping(value = "/menu_detail")
    public String detail(HttpServletRequest request, String id) {
        if (StringUtils.isNotBlank(id)) {
            Menu menu = this.menuServiceImpl.getEntityById(id);
            MenuVo menuVo = new MenuVo();
            BeanUtils.copyProperties(menu, menuVo);
            request.setAttribute("menuVo", menuVo);
        }
        return "/page/xcore/menu/menu_detail";
    }

    /**
     * 删除
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

}