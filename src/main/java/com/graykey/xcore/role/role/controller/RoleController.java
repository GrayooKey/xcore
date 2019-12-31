package com.graykey.xcore.role.role.controller;

import com.alibaba.fastjson.JSONObject;
import com.graykey.xcore.common.base.controller.BaseController;
import com.graykey.xcore.role.role.module.Role;
import com.graykey.xcore.role.role.service.IRoleService;
import com.graykey.xcore.role.role.vo.RoleVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 角色信息	Controller层
 *
 * @author xuezb
 * @date 2019-12-31
 */
@Controller
@RequestMapping("/xcore/role")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleServiceImpl;


    /**
     * 列表页面
     *
     * @param request
     * @param menuCode
     * @return
     */
    @RequestMapping(value = "/role_list")
    public String list(HttpServletRequest request, String menuCode) {
        request.setAttribute("menuCode", menuCode);
        return "/page/xcore/role/role_list";
    }

    /**
     * 列表数据加载
     *
     * @param response
     * @param page
     * @param limit
     * @param roleVo
     */
    @RequestMapping(value = "/role_load")
    public void load(HttpServletResponse response, Integer page, Integer limit, RoleVo roleVo) {
        Page pager = this.roleServiceImpl.queryEntityList(page, limit, roleVo);
        this.getPageResult(response, pager, null);
    }

    /**
     * 编辑页面
     *
     * @param request
     * @param roleVo
     * @return
     */
    @RequestMapping(value = "/role_edit")
    public String edit(HttpServletRequest request, RoleVo roleVo) {
        if (StringUtils.isNotBlank(roleVo.getId())) {
            Role role = this.roleServiceImpl.getEntityById(roleVo.getId());
            BeanUtils.copyProperties(role, roleVo);
            request.setAttribute("roleVo", roleVo);
        } else {
            request.setAttribute("roleVo", roleVo);
        }
        return "/page/xcore/role/role_edit";
    }

    /**
     * 保存or更新
     *
     * @param response
     * @param roleVo
     */
    @RequestMapping(value = "/role_saveOrUpdate")
    public void saveOrUpdate(HttpServletResponse response, RoleVo roleVo) {
        JSONObject json = new JSONObject();
        try {
            Role role = this.roleServiceImpl.saveOrUpdate(roleVo);
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
    @RequestMapping(value = "/role_detail")
    public String detail(HttpServletRequest request, String id) {
        if (StringUtils.isNotBlank(id)) {
            Role role = this.roleServiceImpl.getEntityById(id);
            RoleVo roleVo = new RoleVo();
            BeanUtils.copyProperties(role, roleVo);
            request.setAttribute("roleVo", roleVo);
        }
        return "/page/xcore/role/role_detail";
    }

    /**
     * 删除
     *
     * @param response
     * @param ids
     */
    @RequestMapping(value = "/role_delete")
    public void delete(HttpServletResponse response, String ids) {
        JSONObject json = new JSONObject();
        try {
            if (StringUtils.isNotBlank(ids)) {
                this.roleServiceImpl.delete(ids);
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