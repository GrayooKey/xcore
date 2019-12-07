package com.graykey.xcore.common.base.module;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * 基础实体
 *
 * @author xuezb
 * @date 2019-12-05
 */
@MappedSuperclass
public class BaseModule {

    private String id;                  // 主键        UUID
    private Date createTime;            // 创建时间     记录时间(yyyy-MM-dd HH:mm:ss)
    private Date updateTime;            // 最近更新时间  记录时间(yyyy-MM-dd HH:mm:ss)
    private String creatorId;           // 创建人id
    private String creatorName;         // 创建人姓名
    private String modifierId;          // 最近更新人id
    private Integer dataSource;         // 数据来源      数据字典(comm_dataSource)   1:PC端 2:移动APP 3:微信


    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false, length = 32)
    public String getId() {
        return id;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        return createTime;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdateTime() {
        return updateTime;
    }
    @Column(length = 32)
    public String getCreatorId() {
        return creatorId;
    }
    @Column(length = 30)
    public String getCreatorName() {
        return creatorName;
    }
    @Column(length = 32)
    public String getModifierId() {
        return modifierId;
    }
    public Integer getDataSource() {
        return dataSource;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    public void setDataSource(Integer dataSource) {
        this.dataSource = dataSource;
    }
}
