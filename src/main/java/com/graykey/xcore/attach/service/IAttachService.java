package com.graykey.xcore.attach.service;

import com.graykey.xcore.attach.module.Attach;

import java.util.List;


/**
 * 附件信息	Service层接口
 *
 * @author xuezb
 * @date 2019-12-27
 */
public interface IAttachService {

    /**
     * 根据formId获得附件集合
     *
     * @param formId 实体表单ID
     * @return 附件集合
     */
    List<Attach> queryAttchListByFormId(String formId);

    /**
     * 根据附件ids获取附件集合
     *
     * @param ids 附件id(多个附件id用逗号隔开)
     * @return 附件集合
     */
    List<Attach> queryAttchListByIds(String ids);

    /**
     * 通过formId删除附件
     *
     * @param formId 实体表单ID
     */
    void deleteAttachByFormId(String formId);

    /**
     * 通过附件ids删除附件
     *
     * @param ids 附件id(多个附件id用逗号隔开)
     */
    void deleteAttachByIds(String ids);

    /**
     * 保存or更新
     *
     * @param attach
     * @return
     */
    Attach saveOrUpdate(Attach attach);

    /**
     * 根据ID查询实体对象
     *
     * @param id
     * @return
     */
    Attach getEntityById(String id);

    /**
     * 更新实体时设置基础值
     *
     * @param attach 要更新的实体
     * @return
     */
    Attach getBaseModuleValue(Attach attach);

}