package com.graykey.xcore.systemConfig.service;

import com.graykey.xcore.systemConfig.module.SystemConfig;
import com.graykey.xcore.systemConfig.vo.SystemConfigVo;


/**
 * 系统配置	Service层接口
 *
 * @author xuezb
 * @date 2020-01-11
 */
public interface ISystemConfigService {

    /**
     * 保存or更新
     *
     * @param systemConfigVo
     * @return
     */
    SystemConfig saveOrUpdate(SystemConfigVo systemConfigVo);

    /**
     * 根据ID查询实体对象
     *
     * @param id
     * @return
     */
    SystemConfig getEntityById(String id);

    /**
     * 更新实体时设置基础值
     *
     * @param systemConfig 要更新的实体
     * @return
     */
    SystemConfig getBaseModuleValue(SystemConfig systemConfig);


    /**
     * 获取系统配置
     *
     * @return
     */
    SystemConfig getSystemConfig();

    /**
     * 加载系统配置信息 - 置放缓存
     */
    void loadSystemConfig();

}