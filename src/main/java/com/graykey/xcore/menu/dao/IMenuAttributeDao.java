package com.graykey.xcore.menu.dao;

import com.graykey.xcore.menu.module.MenuAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;


/**
 * 菜单功能	Dao层接口
 *
 * @author xuezb
 * @date 2019-12-20
 */
@Repository("iMenuAttributeDao")
public interface IMenuAttributeDao extends JpaRepository<MenuAttribute, String>, JpaSpecificationExecutor<MenuAttribute>, QuerydslPredicateExecutor<MenuAttribute> {
}