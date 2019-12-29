package com.graykey.xcore.user.dao;


import com.graykey.xcore.user.module.UserExtend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * 用户扩展表	Dao层接口
 *
 * @author xuezb
 * @date 2019-12-27
 */
@Repository("iUserExtendDao")
public interface IUserExtendDao extends JpaRepository<UserExtend, String>, JpaSpecificationExecutor<UserExtend>, QuerydslPredicateExecutor<UserExtend> {
}