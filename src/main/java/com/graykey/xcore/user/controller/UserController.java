package com.graykey.xcore.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.graykey.xcore.common.base.controller.BaseController;
import com.graykey.xcore.role.role.module.Role;
import com.graykey.xcore.user.module.User;
import com.graykey.xcore.user.module.UserExtend;
import com.graykey.xcore.user.service.IUserService;
import com.graykey.xcore.user.vo.UserExtendVo;
import com.graykey.xcore.user.vo.UserVo;
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
 * 用户信息	Controller层
 *
 * @author xuezb
 * @date 2019-12-26
 */
@Controller
@RequestMapping("/xcore/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userServiceImpl;


    /**
     * 列表页面
     *
     * @param request
     * @param menuCode
     * @param userType 用户类型
     * @return
     */
    @RequestMapping(value = "/user_list")
    public String list(HttpServletRequest request, String menuCode, Integer userType) {
        request.setAttribute("menuCode", menuCode);
        request.setAttribute("userType", userType);
        return "/page/xcore/user/user_list";
    }

    /**
     * 列表数据加载
     *
     * @param response
     * @param page
     * @param limit
     * @param userVo
     */
    @RequestMapping(value = "/user_load")
    public void load(HttpServletResponse response, Integer page, Integer limit, UserVo userVo) {
        Page pager = this.userServiceImpl.queryEntityList(page, limit, userVo);
        this.getPageResult(response, pager, new String[]{"roles", "orgFrame"});
    }

    /**
     * 编辑页面
     *
     * @param request
     * @param userVo
     * @return
     */
    @RequestMapping(value = "/user_edit")
    public String edit(HttpServletRequest request, UserVo userVo) {
        if (StringUtils.isNotBlank(userVo.getId())) {
            User user = this.userServiceImpl.getEntityById(userVo.getId());
            BeanUtils.copyProperties(user, userVo);
            request.setAttribute("userVo", userVo);
            //获取扩展表信息
            if (userVo.getUserType() == 6) {
                UserExtend userExtend = this.userServiceImpl.getExtendByPrimaryTableId(userVo.getId());
                if (userExtend != null) {
                    request.setAttribute("userExtendVo", userExtend);
                }
            }
            return "/page/xcore/user/user_edit";
        } else {
            request.setAttribute("userVo", userVo);
            return "/page/xcore/user/user_add";
        }
    }

    /**
     * 保存或更新
     *
     * @param response
     * @param userVo
     */
    @RequestMapping(value = "/user_saveOrUpdate")
    public void saveOrUpdate(HttpServletResponse response, UserVo userVo) {
        JSONObject json = new JSONObject();
        try {
            User user = this.userServiceImpl.saveOrUpdate(userVo);
            json.put("result", true);
            json.put("id", user.getId());
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
    @RequestMapping(value = "/user_detail")
    public String detail(HttpServletRequest request, String id) {
        if (StringUtils.isNotBlank(id)) {
            User user = this.userServiceImpl.getEntityById(id);
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user, userVo);
            request.setAttribute("userVo", userVo);

            //获取扩展表信息
            if (userVo.getUserType() == 6) {
                UserExtend userExtend = this.userServiceImpl.getExtendByPrimaryTableId(id);
                if (userExtend != null) {
                    request.setAttribute("userExtendVo", userExtend);
                }
            }

            //获取附件信息
//            List<Attach> attachList = this.attachServiceImpl.queryAttchListByFormId(id);
//            request.setAttribute("attachList", attachList);
        }
        return "/page/xcore/user/user_detail";
    }

    /**
     * 删除
     *
     * @param response
     * @param ids
     */
    @RequestMapping(value = "/user_delete")
    public void delete(HttpServletResponse response, String ids) {
        JSONObject json = new JSONObject();
        try {
            if (StringUtils.isNotBlank(ids)) {
                this.userServiceImpl.delete(ids);
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
     * 分组统计
     *
     * @param response
     * @param userVo   筛选条件
     */
    @RequestMapping(value = "/user_getBaseCount")
    public void getBaseCount(HttpServletResponse response, UserVo userVo) {
        List<List> mapList = this.userServiceImpl.queryBaseCount(userVo);
        this.print(JSONArray.toJSONString(mapList));
    }


    /**
     * 重置密码
     *
     * @param response
     * @param id
     */
    @RequestMapping(value = "/user_resetPwd")
    public void resetPwd(HttpServletResponse response, String id) {
        JSONObject json = new JSONObject();
        try {
            if (StringUtils.isNotBlank(id)) {
                String pwd = this.userServiceImpl.updateResetPwd(id);
                if (StringUtils.isNotBlank(pwd)) {
                    // TODO 调用短信接口给用户发送重置后的密码
                    System.out.println(pwd);
                    json.put("result", true);
                } else {
                    json.put("result", false);
                }
            }
        } catch (Exception e) {
            json.put("result", false);
            logger.error(e.getMessage(), e);
        } finally {
            this.print(json.toString());
        }
    }

    /**
     * 根据条件 查询 用户集合 (角色列表中操作项 关联用户 里的用户集合)
     *
     * @param response
     * @param userVo
     */
    @RequestMapping(value = "/user_queryUserList")
    public void queryUserList(HttpServletResponse response, UserVo userVo) {
        List<User> userList = this.userServiceImpl.queryUserList(userVo);

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        Set<String> set = filter.getExcludes();
        set.add("roles");
        set.add("orgFrame");
        this.print(JSONArray.parseArray(JSON.toJSONString(userList, filter, SerializerFeature.WriteMapNullValue)));
    }

    /**
     * 用户关联角色
     *
     * @param request
     * @param id 用户id
     * @return
     */
    @RequestMapping(value = "/user_relationRole")
    public String relationRole(HttpServletRequest request, String id) {
        if (StringUtils.isNotBlank(id)) {
            User user = this.userServiceImpl.getEntityById(id);
            String roleIds = "";
            if (user != null && user.getRoles().size() > 0) {
                for (Role role : user.getRoles()) {
                    roleIds += role.getId() + ",";
                }
                roleIds = roleIds.substring(0, roleIds.length() - 1);
            }
            request.setAttribute("roleIds", roleIds);
        }
        return "/page/xcore/user/user_relationRole";
    }

    /**
     * 保存或更新用户与角色的关联
     *
     * @param response
     * @param userId  用户Id
     * @param roleIds 关联角色id (多个id之间用","隔开)
     */
    @RequestMapping(value = "/user_saveOrUpdateRelation")
    public void saveOrUpdateRelation(HttpServletResponse response, String userId, String roleIds) {
        JSONObject json = new JSONObject();
        try {
            if (StringUtils.isNotBlank(userId)) {
                this.userServiceImpl.saveOrUpdateRelation(userId, roleIds);
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


    /*------------------------------  扩展表 start ------------------------------*/
    /**
     * 保存or更新 扩展表信息
     *
     * @param response
     * @param userExtendVo    扩展表信息
     */
    @RequestMapping(value = "/user_saveOrUpdate_extend")
    public void saveOrUpdateExtend(HttpServletResponse response, UserExtendVo userExtendVo) {
        JSONObject json = new JSONObject();
        try {
            this.userServiceImpl.saveOrUpdateExtend(userExtendVo);
            json.put("result", true);
        } catch (Exception e) {
            json.put("result", false);
            logger.error(e.getMessage(), e);
        } finally {
            this.print(json.toString());
        }
    }
    /*------------------------------  扩展表   end ------------------------------*/

}