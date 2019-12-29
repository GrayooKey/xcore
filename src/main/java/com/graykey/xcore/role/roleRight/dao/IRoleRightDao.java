package com.graykey.xcore.role.roleRight.dao;


import com.graykey.xcore.role.roleRight.module.RoleRight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * 角色权限	Dao层接口
 *
 * @author xuezb
 * @date 2019-12-24
 */
@Repository("iRoleRightDao")
public interface IRoleRightDao extends JpaRepository<RoleRight, String>, JpaSpecificationExecutor<RoleRight>, QuerydslPredicateExecutor<RoleRight> {
}