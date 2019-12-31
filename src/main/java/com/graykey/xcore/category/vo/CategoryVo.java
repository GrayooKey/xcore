package com.graykey.xcore.category.vo;

import com.graykey.xcore.category.module.Category;

/**
 * 数据字典	Vo
 *
 * @author xuezb
 * @date 2019-12-31
 */
public class CategoryVo extends Category {

    private Boolean labelMenu;      // 用于标注菜单的标识


    public Boolean getLabelMenu() {
        return labelMenu;
    }

    public void setLabelMenu(Boolean labelMenu) {
        this.labelMenu = labelMenu;
    }


    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Category) {
            Category category = (Category) obj;
            return (this.getId().equals(category.getId()));
        }
        return super.equals(obj);
    }
}