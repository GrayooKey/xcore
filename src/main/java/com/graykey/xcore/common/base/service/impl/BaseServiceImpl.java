package com.graykey.xcore.common.base.service.impl;

import com.graykey.xcore.common.base.service.IBaseService;
import com.graykey.xcore.demo.dao.IDemoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * 基础实体 Service层实现
 *
 * @author xuezb
 * @date 2019-12-05
 */
public class BaseServiceImpl implements IBaseService {

    @Autowired
    private IDemoDao iDemoDao;


    @Override
    public void save(Object entity) {

    }

    @Override
    public void update(Object entity) {

    }

    @Override
    public void saveOrUpdate(Object entity) {

    }

    @Override
    public void delete(Object entity) {

    }

    @Override
    public <T> void delete(Class<T> entityClass, String ids) {

    }

    @Override
    public <T> void getEntityById(Class<T> entityClass, String id) {

    }

    @Override
    public <T> List getAllEntity(Class<T> entityClass, Sort.Order order) {
        return null;
    }

    @Override
    public <T> T getBaseModuleValue(T entity, Class<T> entityClass, String id) {
        return null;
    }

}
