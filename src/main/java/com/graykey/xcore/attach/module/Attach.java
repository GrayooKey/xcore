package com.graykey.xcore.attach.module;

import com.graykey.xcore.common.base.module.BaseModule;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 附件信息
 *
 * @author xuezb
 * @date 2019-12-07
 */
@Entity
@org.hibernate.annotations.Table(comment = "附件信息", appliesTo = "core_attach")
@Table(name = "core_attach")
public class Attach extends BaseModule {

    private String formId;                   // 实体表单ID      对应功能模块的实体ID
    private String attachName;               // 附件名称
    private String attachUploadName;         // 附件上传的名字
    private String localPath;                // 存储具体路径
    private String uploadPath;               // 存储虚拟路径
    private Long attachSize;                 // 附件大小
    private String attachSuffix;             // 附件后缀


    @Column(length = 32)
    public String getFormId() {
        return formId;
    }
    @Column(length = 100)
    public String getAttachName() {
        return attachName;
    }
    @Column(length = 100)
    public String getAttachUploadName() {
        return attachUploadName;
    }
    @Column(length = 200)
    public String getLocalPath() {
        return localPath;
    }
    @Column(length = 200)
    public String getUploadPath() {
        return uploadPath;
    }

    public Long getAttachSize() {
        return attachSize;
    }
    @Column(length = 10)
    public String getAttachSuffix() {
        return attachSuffix;
    }


    public void setFormId(String formId) {
        this.formId = formId;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName;
    }

    public void setAttachUploadName(String attachUploadName) {
        this.attachUploadName = attachUploadName;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public void setAttachSize(Long attachSize) {
        this.attachSize = attachSize;
    }

    public void setAttachSuffix(String attachSuffix) {
        this.attachSuffix = attachSuffix;
    }
}