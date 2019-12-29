package com.graykey.xcore.attach.service.impl;

import com.graykey.xcore.attach.dao.IAttachDao;
import com.graykey.xcore.attach.module.Attach;
import com.graykey.xcore.attach.service.IAttachService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 附件信息	Service层实现
 *
 * @author xuezb
 * @date 2019-12-27
 */
@Service("attachServiceImpl")
public class AttachServiceImpl implements IAttachService {

    @Autowired
    private IAttachDao iAttachDao;


    @Override
    public List<Attach> queryAttchListByFormId(String formId) {
        return this.iAttachDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(criteriaBuilder.equal(root.get("formId"), formId));
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        });
    }

    @Override
    public List<Attach> queryAttchListByIds(String ids) {
        List<Attach> attachList = new ArrayList<>();
        String[] idArr = ids.split(",");
        for (int i = 0; i < idArr.length; i++) {
            Attach attach = this.iAttachDao.getOne(idArr[i]);
            if (attach != null) {
                attachList.add(attach);
            }
        }
        return attachList;
    }

    @Override
    public void deleteAttachByFormId(String formId) {
        List<Attach> list = this.queryAttchListByFormId(formId);
        if (!list.isEmpty()) {
            for (Attach attach : list) {
                this.iAttachDao.delete(attach);
            }
        }
    }

    @Override
    public void deleteAttachByIds(String ids) {
        String[] idArr = ids.split(",");
        for (int i = 0; i < idArr.length; i++) {
            this.iAttachDao.deleteById(idArr[i]);
        }
    }

    @Override
    public Attach saveOrUpdate(Attach attach) {
        if (StringUtils.isBlank(attach.getId())) {
            this.iAttachDao.save(attach);
        } else {
            attach = this.getBaseModuleValue(attach);
            this.iAttachDao.save(attach);
        }
        return attach;
    }

    @Override
    public Attach getEntityById(String id) {
        return this.iAttachDao.getOne(id);
    }

    @Override
    public Attach getBaseModuleValue(Attach attach) {
        Attach old = this.iAttachDao.getOne(attach.getId());
        attach.setCreateTime(old.getCreateTime());
        attach.setUpdateTime(new Date());
        attach.setCreatorId(old.getCreatorId());
        attach.setCreatorName(old.getCreatorName());
        // TODO 更新修改人id 得等到做完用户登录的时候从session中获取当前用户ID  -- demo.getModifierId();
        return attach;
    }

}