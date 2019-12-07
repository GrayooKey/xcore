package com.graykey.xcore.category.module;

import com.graykey.xcore.common.base.module.BaseModule;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * 数据字典
 *
 * @author xuezb
 * @date 2019-12-07
 */
@Entity
@org.hibernate.annotations.Table(comment = "数据字典", appliesTo = "core_category")
@Table(name = "core_category")
public class Category extends BaseModule {

    private String pId;             // 父ID
    private String categoryName;    // 字典名称
    private String categoryCode;    // 字典编码
    private String pIds;            // 所有父ID
    private String pNames;          // 所有父Name
    private Integer isLeaf;         // 是否叶节点  没有数据字典(0:不是 1:是)
    private Integer categoryType;   // 字典类型   数据字典(dict_categoryType)
    private Integer sortNum;        // 排序
    private Set<CategoryAttribute> categoryAttributes = new TreeSet<CategoryAttribute>();


    @Column(length = 32)
    public String getpId() {
        return pId;
    }
    @Column(length = 30)
    public String getCategoryName() {
        return categoryName;
    }
    @Column(length = 30)
    public String getCategoryCode() {
        return categoryCode;
    }
    @Column(length = 500)
    public String getpIds() {
        return pIds;
    }
    @Column(length = 500)
    public String getpNames() {
        return pNames;
    }
    public Integer getIsLeaf() {
        return isLeaf;
    }
    public Integer getCategoryType() {
        return categoryType;
    }
    public Integer getSortNum() {
        return sortNum;
    }

    @OneToMany(cascade= CascadeType.MERGE,mappedBy="category",fetch=FetchType.LAZY)
    @OrderBy("sortNum asc")
    public Set<CategoryAttribute> getCategoryAttributes() {
        return categoryAttributes;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
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

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public void setCategoryAttributes(Set<CategoryAttribute> categoryAttributes) {
        this.categoryAttributes = categoryAttributes;
    }
}