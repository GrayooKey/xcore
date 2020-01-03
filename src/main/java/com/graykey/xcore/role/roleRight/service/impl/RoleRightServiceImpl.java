package com.graykey.xcore.role.roleRight.service.impl;

import com.graykey.xcore.menu.dao.IMenuAttributeDao;
import com.graykey.xcore.menu.module.Menu;
import com.graykey.xcore.menu.module.MenuAttribute;
import com.graykey.xcore.role.role.dao.IRoleDao;
import com.graykey.xcore.role.role.module.Role;
import com.graykey.xcore.role.roleRight.dao.IRoleRightDao;
import com.graykey.xcore.role.roleRight.module.RoleRight;
import com.graykey.xcore.role.roleRight.service.IRoleRightService;
import com.graykey.xcore.role.roleRight.vo.RoleRightVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.*;


/**
 * 角色权限	Service层实现
 *
 * @author xuezb
 * @date 2020-01-03
 */
@Service("roleRightServiceImpl")
public class RoleRightServiceImpl implements IRoleRightService {

    @Autowired
    private IRoleRightDao iRoleRightDao;
    @Autowired
    private IRoleDao iRoleDao;
    @Autowired
    private IMenuAttributeDao iMenuAttributeDao;


    @Override
    public List<RoleRight> queryEntityList(RoleRightVo roleRightVo) {
        return this.iRoleRightDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (StringUtils.isNotBlank(roleRightVo.getId())) {
                predicateList.add(criteriaBuilder.equal(root.get("id"), roleRightVo.getId()));
            }
            if (null != roleRightVo.getMenu()) {
                if (StringUtils.isNotBlank(roleRightVo.getMenu().getId())) {
                    predicateList.add(criteriaBuilder.equal(root.get("menu.id"), roleRightVo.getMenu().getId()));
                }
            }
            if (null != roleRightVo.getRole()) {
                if (StringUtils.isNotBlank(roleRightVo.getRole().getId())) {
                    predicateList.add(criteriaBuilder.equal(root.get("role.id"), roleRightVo.getRole().getId()));
                }
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        });
    }

    @Override
    public void saveOrUpdate(String menuIds, String roleId) {
        //角色原来的菜单功能点
        Role r = this.iRoleDao.getOne(roleId);
        Set<MenuAttribute> oldMenuAttributeSet = new TreeSet<>();
        oldMenuAttributeSet.addAll(r.getMenuAttribute());
        //新的菜单功能点
        Set<MenuAttribute> newMenuAttributeSet = new TreeSet<>();

        String[] menuIdArr = menuIds.split(",");
        for (int i = 0; i < menuIdArr.length; i++) {
            // 将 保留的下来的 旧的菜单的 原来的菜单功能点 添加到 新的菜单功能点 当中
            String oldMenuId = "";
            if (!oldMenuAttributeSet.isEmpty()) {
                for (MenuAttribute oldMenuAttribute : oldMenuAttributeSet) {
                    //判断 当前遍历的旧的菜单功能点 所属的菜单是否存在于新的菜单之中, 若存在, 则将其保存在 新的菜单功能点 当中
                    oldMenuId = oldMenuAttribute.getMenu().getId();
                    if (oldMenuId.equals(menuIdArr[i])) {
                        newMenuAttributeSet.add(oldMenuAttribute);
                    }
                }
            }

            Role role = new Role();
            role.setId(roleId);

            //删除原来的角色权限集合 只需要在保存第一个新的角色权限 之前 删除一次即可
            if (i == 0) {
                //根据条件查询原来的角色权限集合
                RoleRightVo roleRightVo = new RoleRightVo();
                roleRightVo.setRole(role);
                List<RoleRight> oldRoleRightList = this.queryEntityList(roleRightVo);
                //删除原来的角色权限集合
                if (!oldRoleRightList.isEmpty()) {
                    for (RoleRight oldRoleRight : oldRoleRightList) {
                        this.iRoleRightDao.delete(oldRoleRight);
                    }
                }
            }

            //保存新的角色权限
            RoleRight roleRight = new RoleRight();
            roleRight.setRole(role);
            Menu menu = new Menu();
            menu.setId(menuIdArr[i]);
            roleRight.setMenu(menu);
            this.iRoleRightDao.save(roleRight);
        }

        //更新角色的菜单功能点
        r.setMenuAttribute(newMenuAttributeSet);
        this.iRoleDao.save(r);
    }

    @Override
    public void saveClean(String roleId) {
        Role role = this.iRoleDao.getOne(roleId);
        //清除角色所有的菜单功能点
        role.setMenuAttribute(null);
        this.iRoleDao.save(role);

        //清除角色 关联的 所有角色权限
        RoleRightVo roleRightVo = new RoleRightVo();
        Role r = new Role();
        r.setId(roleId);
        roleRightVo.setRole(r);
        List<RoleRight> roleRightList = this.queryEntityList(roleRightVo);
        for (RoleRight roleRight : roleRightList) {
            this.iRoleRightDao.save(roleRight);
        }
    }

    @Override
    public List<MenuAttribute> queryMenuAttributeList(String menuId) {
        return this.iMenuAttributeDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(criteriaBuilder.equal(root.get("menu.id"), menuId));
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }, Sort.by(Sort.Direction.ASC, "sortNum"));
    }

    @Override
    public void saveOrUpdateMenuAttribute(String menuId, String menuAttributeIds, String roleId) {
        Role role = this.iRoleDao.getOne(roleId);
        Set<MenuAttribute> oldMenuAttribute = role.getMenuAttribute();
        Set<MenuAttribute> newMenuAttribute = new TreeSet<>();
        //用新的集合存放去除选中菜单id关联的菜单功能权限
        for (MenuAttribute m : oldMenuAttribute) {
            if (!m.getMenu().getId().equals(menuId)) {
                newMenuAttribute.add(m);
            }
        }
        //将该菜单id关联的新的菜单功能权限添加到新的集合中去
        if (StringUtils.isNotBlank(menuAttributeIds)) {
            String[] menuAttributeIdArr = menuAttributeIds.split(",");
            for (String menuAttributeId : menuAttributeIdArr) {
                newMenuAttribute.add(this.iMenuAttributeDao.getOne(menuAttributeId));
            }
            role.setMenuAttribute(newMenuAttribute);
        }
        this.iRoleDao.save(role);
    }

    @Override
    public void saveCleanMenuAttribute(String menuId, String roleId) {
        Role role = this.iRoleDao.getOne(roleId);
        Set<MenuAttribute> oldMenuAttribute = role.getMenuAttribute();
        Set<MenuAttribute> newMenuAttribute = new TreeSet<>();
        //用新的集合存放去除选中菜单id关联的菜单功能权限
        for (MenuAttribute m : oldMenuAttribute) {
            if (!m.getMenu().getId().equals(menuId)) {
                newMenuAttribute.add(m);
            }
        }
        role.setMenuAttribute(newMenuAttribute);
        this.iRoleDao.save(role);
    }

    @Override
    public RoleRight getEntityById(String id) {
        return this.iRoleRightDao.getOne(id);
    }

}