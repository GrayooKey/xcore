package com.graykey.xcore.areaCode.service.impl;

import com.graykey.xcore.areaCode.dao.IAreaCodeDao;
import com.graykey.xcore.areaCode.module.AreaCode;
import com.graykey.xcore.areaCode.service.IAreaCodeService;
import com.graykey.xcore.areaCode.vo.AreaCodeVo;
import com.graykey.xcore.common.utils.LiteracyUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.criteria.Predicate;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 行政区划	Service层实现
 *
 * @author xuezb
 * @date 2019-12-31
 */
@Service("areaCodeServiceImpl")
public class AreaCodeServiceImpl implements IAreaCodeService {

    @Autowired
    private IAreaCodeDao iAreaCodeDao;


    @Override
    public Page queryEntityList(Integer page, Integer size, AreaCodeVo areaCodeVo) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.ASC, "sortNum");
        Page<AreaCode> pager = this.iAreaCodeDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            //列表页不显示顶级目录
            predicateList.add(criteriaBuilder.notEqual(root.get("pId"), "0"));
            if (StringUtils.isNotBlank(areaCodeVo.getpId())) {
                predicateList.add(criteriaBuilder.equal(root.get("pId"), areaCodeVo.getpId().trim()));
            }
            if (StringUtils.isNotBlank(areaCodeVo.getpAreaCode())) {
                predicateList.add(criteriaBuilder.equal(root.get("pAreaCode"), areaCodeVo.getpAreaCode().trim()));
            }
            if (StringUtils.isNotBlank(areaCodeVo.getAreaCode())) {
                predicateList.add(criteriaBuilder.like(root.get("areaCode"), "%" + areaCodeVo.getAreaCode().trim() + "%"));
            }
            if (StringUtils.isNotBlank(areaCodeVo.getAreaName())) {
                predicateList.add(criteriaBuilder.like(root.get("areaName"), "%" + areaCodeVo.getAreaName().trim() + "%"));
            }
            if (null != areaCodeVo.getLevel()) {
                predicateList.add(criteriaBuilder.equal(root.get("level"), areaCodeVo.getLevel()));
            }
            if (null != areaCodeVo.getState()) {
                predicateList.add(criteriaBuilder.equal(root.get("state"), areaCodeVo.getState()));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }, pageable);
        return pager;
    }

    @Override
    public AreaCode saveOrUpdate(AreaCodeVo areaCodeVo) {
        AreaCode areaCode = new AreaCode();
        BeanUtils.copyProperties(areaCodeVo, areaCode);
        if (StringUtils.isBlank(areaCode.getId())) {
            AreaCode pAreaCode = this.iAreaCodeDao.getOne(areaCode.getpId());
            //更新父节点
            if (pAreaCode.getIsLeaf() == 1) {
                //行政区划叶子节点
                pAreaCode.setIsLeaf(0);
                this.iAreaCodeDao.save(pAreaCode);
            }

            //父AreaCode
            areaCode.setpAreaCode(pAreaCode.getAreaCode());
            //所有父ID
            if (StringUtils.isNotBlank(pAreaCode.getpIds())) {
                areaCode.setpIds(pAreaCode.getpIds() + "/" + pAreaCode.getId());
            } else {
                areaCode.setpIds(pAreaCode.getId());
            }
            //所有父Name
            if (StringUtils.isNotBlank(pAreaCode.getpNames())) {
                areaCode.setpNames(pAreaCode.getpNames() + "/" + pAreaCode.getAreaName());
            } else {
                areaCode.setpNames(pAreaCode.getAreaName());
            }
            //是否叶子节点
            areaCode.setIsLeaf(1);
            //层数
            areaCode.setLevel(pAreaCode.getLevel() + 1);

            this.iAreaCodeDao.save(areaCode);
        } else {
            areaCode = this.getBaseModuleValue(areaCode);
            this.iAreaCodeDao.save(areaCode);
        }
        // TODO
        // Cache.getAreaByCode.put(areaCode.getAreaCode(), areaCode.getAreaName());
        return areaCode;
    }

    @Override
    public void delete(String ids) {
        String[] idz = ids.split(",");
        for (int i = 0; i < idz.length; i++) {
            AreaCode areaCode = this.iAreaCodeDao.getOne(idz[i]);
            if (areaCode != null) {
                if ("area".equals(areaCode.getAreaCode())) {
                    break;
                }

                //更新父节点
                long num = this.queryEntityCount(areaCode.getpId());
                if (num < 2) {
                    AreaCode pAreaCode = this.iAreaCodeDao.getOne(areaCode.getpId());
                    pAreaCode.setIsLeaf(1);
                    this.iAreaCodeDao.save(pAreaCode);
                }

                // TODO
                // Cache.getAreaByCode.remove(areaCode.getAreaCode());
                this.iAreaCodeDao.delete(areaCode);
            }
        }
    }

    @Override
    public AreaCode getEntityById(String id) {
        return this.iAreaCodeDao.getOne(id);
    }

    @Override
    public AreaCode getBaseModuleValue(AreaCode areaCode) {
        AreaCode old = this.iAreaCodeDao.getOne(areaCode.getId());
        areaCode.setCreateTime(old.getCreateTime());
        areaCode.setUpdateTime(new Date());
        areaCode.setCreatorId(old.getCreatorId());
        areaCode.setCreatorName(old.getCreatorName());
        // TODO 更新修改人id 得等到做完用户登录的时候从session中获取当前用户ID  -- areaCode.getModifierId();
        return areaCode;
    }

    @Override
    public long queryEntityCount(String pId) {
        return this.iAreaCodeDao.count(((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(criteriaBuilder.equal(root.get("pId"), pId));
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }));
    }

    @Override
    public AreaCode getAreaByCode(String code) {
        List<AreaCode> areaCodeList = this.iAreaCodeDao.findAll(((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(criteriaBuilder.equal(root.get("areaCode"), code));
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }));
        if (!areaCodeList.isEmpty()) {
            return areaCodeList.get(0);
        }
        return null;
    }

    @Override
    public List<AreaCode> queryEntityList(AreaCodeVo areaCodeVo) {
        return this.iAreaCodeDao.findAll(((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (StringUtils.isNotBlank(areaCodeVo.getpId())) {
                predicateList.add(criteriaBuilder.equal(root.get("pId"), areaCodeVo.getpId().trim()));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }), Sort.by(Sort.Direction.ASC, "sortNum"));
    }

    @Override
    public void getAreaCode() {
        //TODO
    }

    @Override
    public void getAreaCodeAndName() {
        //TODO
    }

    @Override
    public void createAreaCodeFile(int type) {
        List<AreaCode> areaCodes = this.iAreaCodeDao.findAll(((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(criteriaBuilder.equal(root.get("level"), type));
            predicateList.add(criteriaBuilder.equal(root.get("state"), 1));
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }));

        StringBuilder content = new StringBuilder();
        String path = "";
        if(areaCodes != null && areaCodes.size() > 0){
            for (AreaCode areaCode : areaCodes) {
                content.append("{\"id\":\"");
                content.append(areaCode.getAreaCode());
                content.append("\",\"name\":\"");
                content.append(areaCode.getAreaName());
                content.append("\",\"pid\":\"");
                content.append(areaCode.getpAreaCode());
                content.append("\"},");
            }
            //去掉最后一个逗号
            if(content.length() > 1){
                content.setLength(content.length()-1);
            }
            String result = "";
            switch (type){
                case 2:
                    path = "/common/plugins/chooseAdress/js/province.js";
                    result = "var province = [" + content + "];";
                    break;
                case 3:
                    path = "/common/plugins/chooseAdress/js/city.js";
                    result = "var city =[" + content + "];";
                    break;
                case 4:
                    path = "/common/plugins/chooseAdress/js/district.js";
                    result = "var district =[" + content + "];";
                    break;
                case 5:
                    path = "/common/plugins/chooseAdress/js/street.js";
                    result = "var street =[" + content + "];";
                    break;
                case 6:
                    path = "/common/plugins/chooseAdress/js/village.js";
                    result = "var village =[" + content + "];";
                    break;
                default:
                    break;
            }
            WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
            ServletContext servletContext = webApplicationContext.getServletContext();
            String filePath = servletContext.getRealPath(path);
            //清空 文件内容
            LiteracyUtil.clearInfoForFile(filePath);
            //追加 文件内容
            LiteracyUtil.fileAdditionalContent(filePath, result);
        }
    }

}