package com.graykey.xcore.category.dao;


import com.graykey.xcore.category.module.CategoryAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * 数据字典属性	Dao层接口
 *
 * @author xuezb
 * @date 2019-12-24
 */
@Repository("iCategoryAttributeDao")
public interface ICategoryAttributeDao extends JpaRepository<CategoryAttribute, String>, JpaSpecificationExecutor<CategoryAttribute>, QuerydslPredicateExecutor<CategoryAttribute> {
}