package com.graykey.xcore.menu.service.impl;

import com.graykey.xcore.menu.dao.IMenuAttributeDao;
import com.graykey.xcore.menu.dao.IMenuDao;
import com.graykey.xcore.menu.module.Menu;
import com.graykey.xcore.menu.module.MenuAttribute;
import com.graykey.xcore.menu.service.IMenuService;
import com.graykey.xcore.menu.vo.MenuAttributeVo;
import com.graykey.xcore.menu.vo.MenuVo;
import com.graykey.xcore.role.role.module.Role;
import com.graykey.xcore.role.roleRight.module.RoleRight;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * 菜单配置	Service层实现
 *
 * @author xuezb
 * @date 2019-12-20
 */
@Service("menuServiceImpl")
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private IMenuDao iMenuDao;
    @Autowired
    private IMenuAttributeDao iMenuAttributeDao;


    @Override
    public Page queryEntityList(Integer page, Integer size, MenuVo menuVo) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "sortNum");
        Page<Menu> pager = this.iMenuDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            //列表页不显示顶级菜单
            predicateList.add(criteriaBuilder.notEqual(root.get("id"), "0"));
            //父ID
            if (StringUtils.isNotBlank(menuVo.getpId())) {
                predicateList.add(criteriaBuilder.equal(root.get("pId"), menuVo.getpId().trim()));
            }
            if (StringUtils.isNotBlank(menuVo.getMenuName())) {
                predicateList.add(criteriaBuilder.like(root.get("menuName"), "%" + menuVo.getMenuName().trim() + "%"));
            }
            if (StringUtils.isNotBlank(menuVo.getMenuCode())) {
                predicateList.add(criteriaBuilder.like(root.get("menuCode"), "%" + menuVo.getMenuCode().trim() + "%"));
            }
            if (null != menuVo.getState()) {
                predicateList.add(criteriaBuilder.equal(root.get("state"), menuVo.getState()));
            }

            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }, pageable);
        return pager;
    }

    @Override
    public Page queryEntityList(Integer page, Integer size, String menuId) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "sortNum");
        Page<MenuAttribute> pager = this.iMenuAttributeDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            //菜单ID
            predicateList.add(criteriaBuilder.equal(root.get("menu.id"), menuId));

            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }, pageable);
        return pager;
    }

    @Override
    public Menu saveOrUpdate(MenuVo menuVo) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuVo, menu);
        if (StringUtils.isBlank(menu.getId())) {
            //修改父节点isLeaf状态
            Menu pMenu = this.iMenuDao.getOne(menu.getpId());
            if (pMenu.getIsLeaf() == 1) {
                pMenu.setIsLeaf(0);
                this.iMenuDao.save(pMenu);
            }

            //新添加的叶子节点的一些默认值
            menu.setIsLeaf(1);
            menu.setMenuLevel(pMenu.getMenuLevel() + 1);
            if (StringUtils.isNotBlank(pMenu.getpIds())) {
                menu.setpIds(pMenu.getpIds() + "/" + pMenu.getId());
            } else {
                menu.setpIds(pMenu.getpId());
            }
            if (StringUtils.isNotBlank(pMenu.getpNames())) {
                menu.setpNames(pMenu.getpNames() + "/" + pMenu.getMenuName());
            } else {
                menu.setpNames(pMenu.getMenuName());
            }

            this.iMenuDao.save(menu);
        } else {
            menu = this.getBaseModuleValue(menu);
            this.iMenuDao.save(menu);
        }
        return menu;
    }

    @Override
    public MenuAttribute saveOrUpdate(MenuAttributeVo menuAttributeVo) {
        MenuAttribute menuAttribute = new MenuAttribute();
        BeanUtils.copyProperties(menuAttributeVo, menuAttribute);
        if (StringUtils.isBlank(menuAttribute.getId())) {
            this.iMenuAttributeDao.save(menuAttribute);
        } else {
            MenuAttribute newMenuAttribute = this.iMenuAttributeDao.getOne(menuAttribute.getId());
            Set<Role> roles = newMenuAttribute.getRoles();
            BeanUtils.copyProperties(menuAttribute, newMenuAttribute);
            newMenuAttribute.setRoles(roles);
            this.iMenuAttributeDao.save(newMenuAttribute);
        }
        return menuAttribute;
    }

    @Override
    public void delete(String ids) {
        String[] idz = ids.split(",");
        for (int i = 0; i < idz.length; i++) {
            //顶级菜单不能删除
            if(idz[i].equals("0")) {
                break;
            }

            Menu menu = this.iMenuDao.getOne(idz[i]);
            String pid = menu.getpId();
            //先删除菜单权限数据
            if(menu.getRoleRights() != null && menu.getRoleRights().size() > 0){
                for (RoleRight rr : menu.getRoleRights()) {
                    //this.menuDaoImpl.delete(rr);
                }
            }
            //删除菜单功能数据
            if(menu.getMenuAttributes() != null && menu.getMenuAttributes().size() > 0){
                for (MenuAttribute ma : menu.getMenuAttributes()) {
                    //this.menuDaoImpl.delete(ma);
                }
            }
            this.iMenuDao.delete(menu);

            //修改父节点
            MenuVo menuVo = new MenuVo();
            menuVo.setpId(pid);
            List<Menu> list = this.queryEntityList(menuVo);
            if(list.isEmpty()){
                Menu pmenu = this.getEntityById(pid);
                pmenu.setIsLeaf(1);
                this.iMenuDao.save(pmenu);
            }
        }
    }

    @Override
    public void deleteAttr(String ids) {

    }

    @Override
    public List<Menu> queryEntityList(MenuVo menuVo) {
        return null;
    }

    @Override
    public List<MenuAttribute> queryMenuAttributeList(String menuId, MenuAttributeVo menuAttributeVo) {
        return null;
    }

    @Override
    public Menu getMenuByMenuCode(String menuCode) {
        return null;
    }


    @Override
    public Menu getEntityById(String id) {
        return this.iMenuDao.getOne(id);
    }

    @Override
    public MenuAttribute getMenuAttributeById(String id) {
        return null;
    }

    @Override
    public Menu getBaseModuleValue(Menu menu) {
        Menu old = this.iMenuDao.getOne(menu.getId());
        menu.setCreateTime(old.getCreateTime());
        menu.setUpdateTime(new Date());
        menu.setCreatorId(old.getCreatorId());
        menu.setCreatorName(old.getCreatorName());
        // TODO 更新修改人id 得等到做完用户登录的时候从session中获取当前用户ID  -- menu.getModifierId();
        return menu;
    }

    @Override
    public MenuAttribute getBaseModuleValue(MenuAttribute menuAttribute) {
        return null;
    }

}