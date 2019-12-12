package com.graykey.xcore.common.utils.tld;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Layui风格-下拉选择标签
 *
 * @author xuezb
 * @date 2019-12-11
 */
public class SelectLayui extends SimpleTagSupport {


    private String id;
    private String name;
    private String value;//选中的值
    private Boolean isDefSelect;//是否请选择
    private String disabled;//是否置灰

    private Boolean laySearch;//开启搜索匹配功能
    private String layFilter;//layui事件过滤器
    private String layVerify;//layui必填验证
    private String layVerType;//layui必填验证提示样式
    private String dictKey;//字典编码


    @Override
    public void doTag() throws JspException, IOException {
        //getJspContext()方法来获取当前的JspContext对象
        JspWriter out = getJspContext().getOut();

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<select id=\""+id+"\" name=\""+name+"\" lay-filter=\""+layFilter+"\" lay-verify=\""+layVerify+"\" lay-verType=\""+layVerType+"\" ");
        //dictKey value

        if (StringUtils.isNotBlank(disabled)) {
            stringBuffer.append(" disabled=\"disabled\"");
        }
        if (laySearch != null) {
            stringBuffer.append(" lay-search=\""+laySearch+"\"");
        }
        if (StringUtils.isNotBlank(layFilter)) {
            stringBuffer.append(" lay-filter=\""+layFilter+"\"");
        }



        stringBuffer.append("</select>");
        out.println(stringBuffer.toString());
    }





    public void setDictKey(String dictKey) {
        this.dictKey = dictKey;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDefSelect(Boolean defSelect) {
        isDefSelect = defSelect;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public void setLaySearch(Boolean laySearch) {
        this.laySearch = laySearch;
    }

    public void setLayFilter(String layFilter) {
        this.layFilter = layFilter;
    }

    public void setLayVerify(String layVerify) {
        this.layVerify = layVerify;
    }

    public void setLayVerType(String layVerType) {
        this.layVerType = layVerType;
    }
}
