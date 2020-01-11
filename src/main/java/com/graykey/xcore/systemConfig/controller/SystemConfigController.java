package com.graykey.xcore.systemConfig.controller;

import com.alibaba.fastjson.JSONObject;
import com.graykey.xcore.common.base.controller.BaseController;
import com.graykey.xcore.systemConfig.module.SystemConfig;
import com.graykey.xcore.systemConfig.service.ISystemConfigService;
import com.graykey.xcore.systemConfig.vo.SystemConfigVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 系统配置	Controller层
 *
 * @author xuezb
 * @date 2020-01-11
 */
@Controller
@RequestMapping("/xcore/systemConfig")
public class SystemConfigController extends BaseController {

    @Autowired
    private ISystemConfigService systemConfigServiceImpl;


    /**
     * 编辑页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/systemConfig_edit")
    public String edit(HttpServletRequest request) {
        SystemConfigVo systemConfigVo = new SystemConfigVo();
        SystemConfig systemConfig = this.systemConfigServiceImpl.getSystemConfig();
        if (StringUtils.isNotBlank(systemConfigVo.getId())) {
            BeanUtils.copyProperties(systemConfig, systemConfigVo);
        }
        request.setAttribute("systemConfigVo", systemConfigVo);
        return "/page/xcore/systemConfig/systemConfig_edit";
    }

    /**
     * 保存或更新
     *
     * @param response
     * @param systemConfigVo
     */
    @RequestMapping(value = "/systemConfig_saveOrUpdate")
    public void saveOrUpdate(HttpServletResponse response, SystemConfigVo systemConfigVo) {
        JSONObject json = new JSONObject();
        try {
            SystemConfig systemConfig = this.systemConfigServiceImpl.saveOrUpdate(systemConfigVo);
            json.put("result", true);
        } catch (Exception e) {
            json.put("result", false);
            logger.error(e.getMessage(), e);
        } finally {
            this.print(json.toString());
        }
    }

}