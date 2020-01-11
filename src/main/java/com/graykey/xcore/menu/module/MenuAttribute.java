package com.graykey.xcore.menu.module;


import com.graykey.xcore.role.role.module.Role;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * 菜单功能
 *
 * @author xuezb
 * @date 2019-12-07
 */
@Entity
@org.hibernate.annotations.Table(comment = "菜单功能", appliesTo = "core_menu_attribute")
@Table(name = "core_menu_attribute")
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "urms_cache")
public class MenuAttribute {

    private String id;                  // 主键           UUID
    private String attributeName;       // 功能属性名称
    private String attributeCode;       // 功能属性编码 对应layui监听事件 数据字典(menu_attributeCode) 支持自定义
    private String icon;                // 图标(按钮上的图标显示)           数据字典(menu_attributeIcon)    1.新增  2.编辑  3.明细  4.删除  5.预览
    private String color;               // 操作底色        #000000 - 色卡
    private Integer pageType;           // 页面类型        数据字典(menu_pageType)   1.list  2.edit  3.detail
    private Integer showMethod;         // 按钮显示方式    数据字典(menu_buttonShowMethod) 1 仅图标 2 仅文字 3 图标+文字  4 文字+图标
    private String attributeMethod;     // 功能方法 自定义js方法
    private Integer state;              // 状态           数据字典(menu_state)     1.正常  2.停用  3.维护中  4.研发中  5.后期功能
    private String memo;                // 备注
    private Integer position;           // 位置  数据字典(menu_attributePosition) 1.头工具栏 2.行工具栏
    private Integer sortNum;            // 排序           自动递增

    private Menu menu;
    private Set<Role> roles = new TreeSet<>();


    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(nullable = false, length = 36)
    public String getId() {
        return id;
    }
    @Column(length = 30)
    public String getAttributeName() {
        return attributeName;
    }
    @Column(length = 30)
    public String getAttributeCode() {
        return attributeCode;
    }
    @Column(length = 30)
    public String getIcon() {
        return icon;
    }
    @Column(length = 30)
    public String getColor() {
        return color;
    }
    public Integer getPageType() {
        return pageType;
    }
    public Integer getShowMethod() {
        return showMethod;
    }
    @Column(length = 30)
    public String getAttributeMethod() {
        return attributeMethod;
    }
    public Integer getState() {
        return state;
    }
    @Column(length = 200)
    public String getMemo() {
        return memo;
    }
    public Integer getPosition() {
        return position;
    }
    public Integer getSortNum() {
        return sortNum;
    }

    @ManyToOne(cascade={CascadeType.MERGE})
    @JoinColumn(name="menuId")
    public Menu getMenu() {
        return menu;
    }

    @ManyToMany(cascade={CascadeType.REFRESH})
    @JoinTable(name = "core_role_menu_attribute", joinColumns = { @JoinColumn(name = "menuAttributeId") }, inverseJoinColumns = { @JoinColumn(name = "roleId") })
    //@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region="urms_cache")
    public Set<Role> getRoles() {
        return roles;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public void setAttributeCode(String attributeCode) {
        this.attributeCode = attributeCode;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPageType(Integer pageType) {
        this.pageType = pageType;
    }

    public void setShowMethod(Integer showMethod) {
        this.showMethod = showMethod;
    }

    public void setAttributeMethod(String attributeMethod) {
        this.attributeMethod = attributeMethod;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}