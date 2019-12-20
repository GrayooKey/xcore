package com.graykey.xcore.menu.module;


import com.graykey.xcore.common.base.module.BaseModule;
import com.graykey.xcore.role.roleRight.module.RoleRight;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * 菜单配置
 *
 * @author xuezb
 * @date 2019-12-04
 */
@Entity
@org.hibernate.annotations.Table(comment = "菜单配置", appliesTo = "core_menu")
@Table(name = "core_menu")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "urms_cache")
public class Menu extends BaseModule {

    private String pId;                 // 父ID          如果是第一次则为0,不能操作
    private Integer menuType;           // 菜单类型       数据字典(menu_type)
    private String menuName;            // 菜单名字
    private String winName;             // 窗口名字       layUI的弹窗上面的标题
    private String menuCode;            // 菜单编码
    private String menuUrl;             // 访问URL
    private Integer iconType;           // 图标来源       数据字典(menu_iconType)  1.iconfont  2.上传图标(UI设计的)
    private String menuIcon;            // 图标Icon
    private String picUrl;              // 图片URL       上传图标后的路径地址
    private String pIds;                // 所有父ID
    private String pNames;              // 所有父Name
    private Integer isLeaf;             // 是否叶节点
    private String iconColor;           // 图标颜色       #000000 - 仅手机菜单/部分PC使用
    private String fontColor;           // 字体颜色       #000000 - 仅手机菜单/部分PC使用
    private Integer menuLevel;          // 菜单级别
    private Integer menuOpenType;       // 打开方式       数据字典(menu_openType)
    private Integer state;              // 状态          数据字典(menu_state)  1.正常    2.停用    3.维护中   4.研发中   5.后期功能
    private Integer sortNum;            // 排序          自动递增

    private Set<RoleRight> roleRights = new TreeSet<>();
    private Set<MenuAttribute> menuAttributes = new TreeSet<>();


    @Column(length = 32)
    public String getpId() {
        return pId;
    }
    public Integer getMenuType() {
        return menuType;
    }
    @Column(length = 30)
    public String getMenuName() {
        return menuName;
    }
    @Column(length = 30)
    public String getWinName() {
        return winName;
    }
    @Column(length = 30)
    public String getMenuCode() {
        return menuCode;
    }
    @Column(length = 200)
    public String getMenuUrl() {
        return menuUrl;
    }
    public Integer getIconType() {
        return iconType;
    }
    @Column(length = 30)
    public String getMenuIcon() {
        return menuIcon;
    }
    @Column(length = 100)
    public String getPicUrl() {
        return picUrl;
    }
    @Column(length = 200)
    public String getpIds() {
        return pIds;
    }
    @Column(length = 200)
    public String getpNames() {
        return pNames;
    }
    public Integer getIsLeaf() {
        return isLeaf;
    }
    @Column(length = 10)
    public String getIconColor() {
        return iconColor;
    }
    @Column(length = 10)
    public String getFontColor() {
        return fontColor;
    }
    public Integer getMenuLevel() {
        return menuLevel;
    }
    public Integer getMenuOpenType() {
        return menuOpenType;
    }
    public Integer getState() {
        return state;
    }
    public Integer getSortNum() {
        return sortNum;
    }

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "menu", fetch = FetchType.LAZY)
    //@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "urms_cache")
    public Set<RoleRight> getRoleRights() {
        return roleRights;
    }

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "menu", fetch = FetchType.LAZY)
    //@OrderBy("sortNum asc")
    //@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "urms_cache")
    public Set<MenuAttribute> getMenuAttributes() {
        return menuAttributes;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setWinName(String winName) {
        this.winName = winName;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public void setIconType(Integer iconType) {
        this.iconType = iconType;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public void setpIds(String pIds) {
        this.pIds = pIds;
    }

    public void setpNames(String pNames) {
        this.pNames = pNames;
    }

    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    public void setIconColor(String iconColor) {
        this.iconColor = iconColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public void setMenuOpenType(Integer menuOpenType) {
        this.menuOpenType = menuOpenType;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public void setRoleRights(Set<RoleRight> roleRights) {
        this.roleRights = roleRights;
    }

    public void setMenuAttributes(Set<MenuAttribute> menuAttributes) {
        this.menuAttributes = menuAttributes;
    }
}