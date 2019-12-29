package com.graykey.xcore.attach.dao;


import com.graykey.xcore.attach.module.Attach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * 附件信息	Dao层接口
 *
 * @author xuezb
 * @date 2019-12-24
 */
@Repository("iAttachDao")
public interface IAttachDao extends JpaRepository<Attach, String>, JpaSpecificationExecutor<Attach>, QuerydslPredicateExecutor<Attach> {
}