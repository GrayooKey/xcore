package com.graykey.xcore.systemConfig.dao;


import com.graykey.xcore.systemConfig.module.SystemConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * 系统配置	Dao层接口
 *
 * @author xuezb
 * @date 2020-01-11
 */
@Repository("iSystemConfigDao")
public interface ISystemConfigDao extends JpaRepository<SystemConfig, String>, JpaSpecificationExecutor<SystemConfig>, QuerydslPredicateExecutor<SystemConfig> {
}