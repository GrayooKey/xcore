package com.graykey.xcore.category.service;

import com.graykey.xcore.category.module.Category;
import com.graykey.xcore.category.module.CategoryAttribute;
import com.graykey.xcore.category.vo.CategoryAttributeVo;
import com.graykey.xcore.category.vo.CategoryVo;
import org.springframework.data.domain.Page;

import java.util.List;


/**
 * 数据字典	Service层接口
 *
 * @author xuezb
 * @date 2019-12-31
 */
public interface ICategoryService {

    /**
     * 分页   数据字典
     *
     * @param page
     * @param size
     * @param categoryVo
     * @return
     */
    Page queryEntityList(Integer page, Integer size, CategoryVo categoryVo);

    /**
     * 分页   字典属性
     *
     * @param page
     * @param size
     * @param categoryId 数据字典ID
     * @return
     */
    Page queryEntityList(Integer page, Integer size, String categoryId);

    /**
     * 保存或更新    数据字典
     *
     * @param categoryVo
     * @return
     */
    Category saveOrUpdate(CategoryVo categoryVo);

    /**
     * 保存或更新    字典属性
     *
     * @param categoryAttributeVo
     * @return
     */
    CategoryAttribute saveOrUpdate(CategoryAttributeVo categoryAttributeVo);

    /**
     * 删除   数据字典
     *
     * @param ids
     */
    void delete(String ids);

    /**
     * 删除   字典属性
     *
     * @param ids
     */
    void deleteAttr(String ids);

    /**
     * 根据ID查询实体对象   数据字典
     *
     * @param id
     * @return
     */
    Category getEntityById(String id);

    /**
     * 根据ID查询实体对象    字典属性
     *
     * @param id
     * @return
     */
    CategoryAttribute getCategoryAttributeById(String id);

    /**
     * 更新实体时设置基础值
     *
     * @param category 要更新的实体
     * @return
     */
    Category getBaseModuleValue(Category category);


    /**
     * 根据父ID获得下一级节点数量   [用于新增节点(数据字典)时, 排序值(sortNum)自动生成]
     *
     * @param pId 父ID
     * @return 节点数量
     */
    long queryEntityCount(String pId);

    /**
     * 根据字典编码获得数据字典
     *
     * @param code 字典编码
     * @return
     */
    Category getCategoryByCode(String code);

    /**
     * 根据查询条件获得数据字典集合
     *
     * @param categoryVo 查询条件
     * @return
     */
    List<Category> queryEntityList(CategoryVo categoryVo);

    /**
     * 根据数据字典ID获得字典属性集合
     *
     * @param categoryId 数据字典ID
     * @return
     */
    List<CategoryAttribute> queryListByCategoryId(String categoryId);


    /**
     * 系统启动时加载数据字典
     */
    void getDictByCode();
}