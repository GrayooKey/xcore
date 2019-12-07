package com.graykey.xcore.role.roleRight.module;

import com.graykey.xcore.common.base.module.BaseModule;
import com.graykey.xcore.menu.module.Menu;
import com.graykey.xcore.role.role.module.Role;

import javax.persistence.*;

/**
 * 角色权限
 *
 * @author xuezb
 * @date 2019-12-07
 */
@Entity
@org.hibernate.annotations.Table(comment = "角色权限", appliesTo = "core_role_right")
@Table(name = "core_role_right")
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region="urms_cache")
public class RoleRight extends BaseModule {

    private Menu menu;      // 菜单
    private Role role;      // 角色


    @ManyToOne(cascade={CascadeType.MERGE})
    @JoinColumn(name="menuId")
    //@Cache(usage= CacheConcurrencyStrategy.READ_WRITE,region="urms_cache")
    public Menu getMenu() {
        return menu;
    }

    @ManyToOne(cascade={CascadeType.MERGE})
    @JoinColumn(name="roleId")
    //@org.hibernate.annotations.Cache(usage= CacheConcurrencyStrategy.READ_WRITE,region="urms_cache")
    public Role getRole() {
        return role;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}