package com.graykey.xcore.role.role.service.impl;

import com.graykey.xcore.role.role.dao.IRoleDao;
import com.graykey.xcore.role.role.module.Role;
import com.graykey.xcore.role.role.service.IRoleService;
import com.graykey.xcore.role.role.vo.RoleVo;
import com.graykey.xcore.user.dao.IUserDao;
import com.graykey.xcore.user.module.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.*;


/**
 * 角色信息	Service层实现
 *
 * @author xuezb
 * @date 2019-12-31
 */
@Service("roleServiceImpl")
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao iRoleDao;
    @Autowired
    private IUserDao iUserDao;


    @Override
    public Page queryEntityList(Integer page, Integer size, RoleVo roleVo) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "createTime");
        return this.iRoleDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (StringUtils.isNotBlank(roleVo.getRoleName())) {
                predicateList.add(criteriaBuilder.like(root.get("roleName"), "%" + roleVo.getRoleName().trim() + "%"));
            }
            if (StringUtils.isNotBlank(roleVo.getRoleCode())) {
                predicateList.add(criteriaBuilder.like(root.get("roleCode"), "%" + roleVo.getRoleCode().trim() + "%"));
            }
            if (null != roleVo.getSmsValidation()) {
                predicateList.add(criteriaBuilder.equal(root.get("smsValidation"), roleVo.getSmsValidation()));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }, pageable);
    }

    @Override
    public Role saveOrUpdate(RoleVo roleVo) {
        Role role = new Role();
        BeanUtils.copyProperties(roleVo, role);
        if (StringUtils.isBlank(role.getId())) {
            this.iRoleDao.save(role);
        } else {
            role = this.getBaseModuleValue(role);
            this.iRoleDao.save(role);
        }
        return role;
    }

    @Override
    public void delete(String ids) {
        String[] idz = ids.split(",");
        for (int i = 0; i < idz.length; i++) {
            this.iRoleDao.deleteById(idz[i]);
        }
    }

    @Override
    public Role getEntityById(String id) {
        return this.iRoleDao.getOne(id);
    }

    @Override
    public Role getBaseModuleValue(Role role) {
        Role old = this.iRoleDao.getOne(role.getId());
        role.setCreateTime(old.getCreateTime());
        role.setUpdateTime(new Date());
        role.setCreatorId(old.getCreatorId());
        role.setCreatorName(old.getCreatorName());
        // TODO 更新修改人id 得等到做完用户登录的时候从session中获取当前用户ID  -- role.getModifierId();
        role.setUsers(old.getUsers());
        role.setRoleRights(old.getRoleRights());
        role.setMenuAttribute(old.getMenuAttribute());
        return role;
    }



    @Override
    public List<Role> queryEntityList(RoleVo roleVo) {
        return this.iRoleDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (StringUtils.isNotBlank(roleVo.getId())) {
                predicateList.add(criteriaBuilder.equal(root.get("id"), roleVo.getId().trim()));
            }
            if (StringUtils.isNotBlank(roleVo.getRoleName())) {
                predicateList.add(criteriaBuilder.like(root.get("roleName"), "%" + roleVo.getRoleName().trim() + "%"));
            }
            if (StringUtils.isNotBlank(roleVo.getRoleCode())) {
                predicateList.add(criteriaBuilder.like(root.get("roleCode"), "%" + roleVo.getRoleCode().trim() + "%"));
            }
            if (null != roleVo.getSmsValidation()) {
                predicateList.add(criteriaBuilder.equal(root.get("smsValidation"), roleVo.getSmsValidation()));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }, Sort.by(Sort.Direction.DESC, "createTime"));
    }

    @Override
    public void saveOrUpdateRelation(String roleId, String userIds) {
        Role role = this.iRoleDao.getOne(roleId);
        Set<User> users = new HashSet<>();
        if (StringUtils.isNotBlank(userIds)) {
            String[] userIdArr = userIds.split(",");
            for (String userId : userIdArr) {
                users.add(this.iUserDao.getOne(userId));
            }
        }
        role.setUsers(users);
        this.iRoleDao.save(role);
    }

    @Override
    public Set<Role> queryUserRoleList(String userId) {
        return null;
    }

}