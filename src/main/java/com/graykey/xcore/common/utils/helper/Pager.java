package com.graykey.xcore.common.utils.helper;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询返回结果对象
 *
 * @author xuezb
 * @date 2019-12-12
 */
public class Pager implements Serializable {

    private long totalCount;    //总条数
    private List pageList;      //数据列表

    public Pager(long totalCount, List pageList) {
        this.totalCount = totalCount;
        this.pageList = pageList;
    }


    public long getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
    public List getPageList() {
        return pageList;
    }
    public void setPageList(List pageList) {
        this.pageList = pageList;
    }
}
