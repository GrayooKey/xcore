package com.graykey.xcore.role.role.dao;


import com.graykey.xcore.role.role.module.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * 角色信息	Dao层接口
 *
 * @author xuezb
 * @date 2019-12-24
 */
@Repository("iRoleDao")
public interface IRoleDao extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role>, QuerydslPredicateExecutor<Role> {
}