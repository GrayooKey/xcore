package com.graykey.xcore.common.base.service;

import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * 基础实体 Service层接口
 *
 * @author xuezb
 * @date 2019-12-05
 */
public interface IBaseService {

    /**
     * 保存实体
     *
     * @param entity
     */
    void save(Object entity);

    /**
     * 更新实体
     *
     * @param entity
     */
    void update(Object entity);

    /**
     * 保存或更新
     *
     * @param entity
     */
    void saveOrUpdate(Object entity);


    /**
     * 删除实体
     *
     * @param entity
     */
    void delete(Object entity);

    /**
     * 删除实体
     *
     * @param entityClass 实体对象.class
     * @param ids 实体ID,多个ID用英文逗号","隔开
     */
    <T> void delete(Class<T> entityClass, String ids);


    /**
     * 根据ID查询实体对象
     *
     * @param entityClass 实体对象.class
     * @param id 实体id
     */
    <T> void getEntityById(Class<T> entityClass, String id);

    /**
     * 获得所有实体
     *
     * @param entityClass 实体对象.class
     * @param order       排序
     * @return 实体List集合
     */
    <T> List getAllEntity(Class<T> entityClass, Sort.Order order);


    /**
     * 更新实体时设置基础值
     *
     * @param entity 要更新的实体
     * @param entityClass 实体对象.class
     * @param id 要更新的实体id
     * @return
     */
    <T> T getBaseModuleValue(T entity, Class<T> entityClass, String id);

}
