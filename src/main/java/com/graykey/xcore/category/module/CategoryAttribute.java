package com.graykey.xcore.category.module;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 数据字典属性
 *
 * @author xuezb
 * @date 2019-12-07
 */
@Entity
@org.hibernate.annotations.Table(comment = "数据字典属性", appliesTo = "core_category_attribute")
@Table(name = "core_category_attribute")
public class CategoryAttribute {

    private String id;              // 主键       UUID
    private String attrKey;         // 属性键
    private String attrValue;       // 属性值
    private Integer isDefault;      // 是否默认    数据字典(comm_yesNot)
    private Integer sortNum;        // 排序
    private Category category;      // 主表


    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false, length = 36)
    public String getId() {
        return id;
    }

    @Column(length = 30)
    public String getAttrKey() {
        return attrKey;
    }
    @Column(length = 30)
    public String getAttrValue() {
        return attrValue;
    }
    public Integer getIsDefault() {
        return isDefault;
    }
    public Integer getSortNum() {
        return sortNum;
    }

    @ManyToOne(cascade={CascadeType.MERGE})
    @JoinColumn(name="categoryId")
    public Category getCategory() {
        return category;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAttrKey(String attrKey) {
        this.attrKey = attrKey;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
