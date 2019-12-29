package com.graykey.xcore.user.dao;


import com.graykey.xcore.user.module.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * 用户信息	Dao层接口
 *
 * @author xuezb
 * @date 2019-12-24
 */
@Repository("iUserDao")
public interface IUserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User>, QuerydslPredicateExecutor<User> {
}