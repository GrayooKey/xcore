package com.graykey.xcore.areaCode.service;

import com.graykey.xcore.areaCode.module.AreaCode;
import com.graykey.xcore.areaCode.vo.AreaCodeVo;
import org.springframework.data.domain.Page;

import java.util.List;


/**
 * 行政区划	Service层接口
 *
 * @author xuezb
 * @date 2019-12-31
 */
public interface IAreaCodeService {

    /**
     * 分页
     *
     * @param page
     * @param size
     * @param areaCodeVo
     * @return
     */
    Page queryEntityList(Integer page, Integer size, AreaCodeVo areaCodeVo);

    /**
     * 保存或更新
     *
     * @param areaCodeVo
     * @return
     */
    AreaCode saveOrUpdate(AreaCodeVo areaCodeVo);

    /**
     * 删除
     *
     * @param ids
     */
    void delete(String ids);

    /**
     * 根据ID查询实体对象
     *
     * @param id
     * @return
     */
    AreaCode getEntityById(String id);

    /**
     * 更新实体时设置基础值
     *
     * @param areaCode 要更新的实体
     * @return
     */
    AreaCode getBaseModuleValue(AreaCode areaCode);


    /**
     * 根据父ID获得下一级节点数量   [用于新增节点(行政区划)时, 排序值(sortNum)自动生成]
     *
     * @param pId 父ID
     * @return 节点数量
     */
    long queryEntityCount(String pId);

    /**
     * 根据编码获得行政区划
     *
     * @param code
     * @return
     */
    AreaCode getAreaByCode(String code);

    /**
     * 根据查询条件获得行政区划集合
     *
     * @param areaCodeVo 查询条件
     * @return List<AreaCode>
     */
    List<AreaCode> queryEntityList(AreaCodeVo areaCodeVo);


    /**
     * 系统启动时加载行政区划
     */
    void getAreaCode();

    /**
     * 行政区划多级联动所需数据
     */
    void getAreaCodeAndName();

    /**
     * 生成对应级别的行政区划json文件
     * @param type 级别 2=省市 3=区县 4=街道/镇 5=村/社区
     */
    void createAreaCodeFile(int type);

}