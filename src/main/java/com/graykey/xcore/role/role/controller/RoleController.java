package com.graykey.xcore.role.role.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.graykey.xcore.common.base.controller.BaseController;
import com.graykey.xcore.role.role.module.Role;
import com.graykey.xcore.role.role.service.IRoleService;
import com.graykey.xcore.role.role.vo.RoleVo;
import com.graykey.xcore.user.module.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;


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
        this.getPageResult(response, pager, new String[]{"users", "roleRights", "menuAttribute"});
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
            roleVo.setSmsValidation(2);
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


    /**
     * 检查角色编码是否唯一
     *
     * @param response
     * @param roleCode 角色编码
     */
    @RequestMapping(value = "/role_checkRoleCode")
    public void checkRoleCode(HttpServletResponse response, String roleCode) {
        JSONObject json = new JSONObject();
        if (StringUtils.isNotBlank(roleCode)) {
            RoleVo roleVo = new RoleVo();
            roleVo.setRoleCode(roleCode);
            List<Role> roleList = this.roleServiceImpl.queryEntityList(roleVo);
            if (roleList.isEmpty()) {
                json.put("result", true);   //唯一
            } else {
                json.put("result", false);  //不唯一
            }
        } else {
            json.put("result", false);
        }
        this.print(json.toString());
    }

    /**
     * 根据条件 查询 角色集合
     *
     * @param response
     * @param roleVo
     */
    @RequestMapping(value = "/role_queryRoleList")
    public void queryRoleList(HttpServletResponse response, RoleVo roleVo) {
        List<Role> roleList = this.roleServiceImpl.queryEntityList(roleVo);

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        Set<String> set = filter.getExcludes();
        set.add("users");
        set.add("roleRights");
        set.add("menuAttribute");
        JSONObject json = new JSONObject();
        json.put("result", true);
        json.put("roleList", JSONArray.parseArray(JSON.toJSONString(roleList, filter, SerializerFeature.WriteMapNullValue))); //数据列表
        this.print(json.toString());
    }

    /**
     * 角色关联用户
     *
     * @param id 角色id
     * @return
     */
    @RequestMapping(value = "/role_relationUser")
    public String relationUser(HttpServletRequest request, String id) {
        if (StringUtils.isNotBlank(id)) {
            Role role = this.roleServiceImpl.getEntityById(id);
            String userIds = "";
            if (role != null && !role.getUsers().isEmpty()) {
                for (User user : role.getUsers()) {
                    userIds += user.getId() + ",";
                }
                userIds = userIds.substring(0, userIds.length() - 1);
            }
            request.setAttribute("userIds", userIds);
        }
        return "/page/xcore/role/role_relationUser";
    }

    /**
     * 保存或更新角色与用户的关联
     *
     * @param response
     * @param roleId  角色Id
     * @param userIds 关联用户id (多个id之间用","隔开)
     */
    @RequestMapping(value = "/role_saveOrUpdateRelation")
    public void saveOrUpdateRelation(HttpServletResponse response, String roleId, String userIds) {
        JSONObject json = new JSONObject();
        try {
            if (StringUtils.isNotBlank(roleId)) {
                this.roleServiceImpl.saveOrUpdateRelation(roleId, userIds);
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