package com.graykey.xcore.category.dao;


import com.graykey.xcore.category.module.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * 数据字典	Dao层接口
 *
 * @author xuezb
 * @date 2019-12-24
 */
@Repository("iCategoryDao")
public interface ICategoryDao extends JpaRepository<Category, String>, JpaSpecificationExecutor<Category>, QuerydslPredicateExecutor<Category> {
}