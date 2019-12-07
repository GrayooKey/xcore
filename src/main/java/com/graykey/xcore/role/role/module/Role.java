package com.graykey.xcore.role.role.module;

import com.graykey.xcore.common.base.module.BaseModule;
import com.graykey.xcore.menu.module.MenuAttribute;
import com.graykey.xcore.role.roleRight.module.RoleRight;
import com.graykey.xcore.user.module.User;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * 角色信息
 *
 * @author xuezb
 * @date 2019-12-07
 */
@Entity
@org.hibernate.annotations.Table(comment = "角色信息", appliesTo = "core_role")
@Table(name = "core_role")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "urms_cache")
public class Role extends BaseModule {

    private String roleName;                                               // 角色名称
    private String roleCode;                                               // 角色编码
    private Set<User> users;                                               // 用户集
    private Set<RoleRight> roleRights = new TreeSet<RoleRight>();                  // 角色权限
    private Set<MenuAttribute> menuAttribute = new TreeSet<MenuAttribute>();           // 菜单功能
    private Integer smsValidation;                                         // 登录是否需要短信验证


    @Column(length = 30)
    public String getRoleName() {
        return roleName;
    }

    @Column(length = 30)
    public String getRoleCode() {
        return roleCode;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "core_user_role", joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "userId")})
    //@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "urms_cache")
    public Set<User> getUsers() {
        return users;
    }

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "role", fetch = FetchType.LAZY)
    public Set<RoleRight> getRoleRights() {
        return roleRights;
    }

    @ManyToMany(cascade = {CascadeType.REFRESH})
    @JoinTable(name = "core_role_menu_attribute", joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "menuAttributeId")})
    //@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "urms_cache")
    public Set<MenuAttribute> getMenuAttribute() {
        return menuAttribute;
    }

    public Integer getSmsValidation() {
        return smsValidation;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void setRoleRights(Set<RoleRight> roleRights) {
        this.roleRights = roleRights;
    }

    public void setMenuAttribute(Set<MenuAttribute> menuAttribute) {
        this.menuAttribute = menuAttribute;
    }

    public void setSmsValidation(Integer smsValidation) {
        this.smsValidation = smsValidation;
    }
}