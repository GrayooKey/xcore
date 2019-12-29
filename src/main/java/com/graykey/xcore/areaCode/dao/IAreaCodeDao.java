package com.graykey.xcore.areaCode.dao;


import com.graykey.xcore.areaCode.module.AreaCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * 行政区划	Dao层接口
 *
 * @author xuezb
 * @date 2019-12-24
 */
@Repository("iAreaCodeDao")
public interface IAreaCodeDao extends JpaRepository<AreaCode, String>, JpaSpecificationExecutor<AreaCode>, QuerydslPredicateExecutor<AreaCode> {
}