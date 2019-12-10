package com.graykey.xcore.demo.dao;

import com.graykey.xcore.demo.module.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * 示例	Dao层接口
 *
 * @author xuezb
 * @date 2019-12-09
 */
@Repository("iDemoDao")
public interface IDemoDao extends JpaRepository<Demo, String>, QuerydslPredicateExecutor<Demo> {
}