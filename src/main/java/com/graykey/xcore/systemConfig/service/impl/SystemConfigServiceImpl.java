package com.graykey.xcore.systemConfig.service.impl;

import com.graykey.xcore.common.utils.cache.Cache;
import com.graykey.xcore.systemConfig.dao.ISystemConfigDao;
import com.graykey.xcore.systemConfig.module.SystemConfig;
import com.graykey.xcore.systemConfig.service.ISystemConfigService;
import com.graykey.xcore.systemConfig.vo.SystemConfigVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 系统配置	Service层实现
 *
 * @author xuezb
 * @date 2020-01-11
 */
@Service("systemConfigServiceImpl")
public class SystemConfigServiceImpl implements ISystemConfigService {

    @Autowired
    private ISystemConfigDao iSystemConfigDao;


    @Override
    public SystemConfig saveOrUpdate(SystemConfigVo systemConfigVo) {
        SystemConfig systemConfig = new SystemConfig();
        BeanUtils.copyProperties(systemConfigVo, systemConfig);
        if (StringUtils.isBlank(systemConfig.getId())) {
            this.iSystemConfigDao.save(systemConfig);
        } else {
            systemConfig = this.getBaseModuleValue(systemConfig);
            this.iSystemConfigDao.save(systemConfig);
        }
        return systemConfig;
    }

    @Override
    public SystemConfig getEntityById(String id) {
        return this.iSystemConfigDao.getOne(id);
    }

    @Override
    public SystemConfig getBaseModuleValue(SystemConfig systemConfig) {
        SystemConfig old = this.iSystemConfigDao.getOne(systemConfig.getId());
        systemConfig.setCreateTime(old.getCreateTime());
        systemConfig.setUpdateTime(new Date());
        return systemConfig;
    }

    @Override
    public SystemConfig getSystemConfig() {
        List<SystemConfig> systemConfigList = this.iSystemConfigDao.findAll();
        if (!systemConfigList.isEmpty()) {
            return systemConfigList.get(0);
        }
        return null;
    }

    @Override
    public void loadSystemConfig() {
        Cache.getSystemConfig = this.getSystemConfig();
    }

}