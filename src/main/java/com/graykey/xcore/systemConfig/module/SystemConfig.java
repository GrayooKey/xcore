package com.graykey.xcore.systemConfig.module;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 系统配置
 *
 * @author xuezb
 * @date 2020-01-11
 */
@Entity
@org.hibernate.annotations.Table(comment = "系统配置", appliesTo = "core_system_config")
@Table(name = "core_system_config")
public class SystemConfig {


    private String id;                  // 主键        UUID
    private Date createTime;            // 创建时间     记录时间(yyyy-MM-dd HH:mm:ss)
    private Date updateTime;            // 最近更新时间  记录时间(yyyy-MM-dd HH:mm:ss)
    private String systemName;          // 系统名称
    private String gisServerUrl;        // 地图服务地址
    private String mapCode;             // 初始化行政区域/地图编码
    private String uploadPath;          // 上传文件路径
    private String virtualPath;         // 上传文件虚拟路径/目录
    private Integer tokenValidTime;     // token有效时长 单位天
    private Integer singleLogin;        // 是否单一登陆 1:同时登录一个     2:一个账户多浏览器登录
    private int codeLength;             // 短信验证码长度
    private Integer codeValidTime;      // 短信验证码有效期 单位分钟
    private Integer sendInterval;       // 短信验证码发送间隔 单位秒
    private String copyright;           // 版权信息


    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(nullable = false, length = 36)
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
    @Column(length = 64)
    public String getSystemName() {
        return systemName;
    }
    @Column(length = 128)
    public String getGisServerUrl() {
        return gisServerUrl;
    }
    @Column(length = 32)
    public String getMapCode() {
        return mapCode;
    }
    @Column(length = 128)
    public String getUploadPath() {
        return uploadPath;
    }
    @Column(length = 128)
    public String getVirtualPath() {
        return virtualPath;
    }

    public Integer getTokenValidTime() {
        return tokenValidTime;
    }

    public Integer getSingleLogin() {
        return singleLogin;
    }

    public int getCodeLength() {
        return codeLength;
    }

    public Integer getCodeValidTime() {
        return codeValidTime;
    }

    public Integer getSendInterval() {
        return sendInterval;
    }
    @Column(length = 512)
    public String getCopyright() {
        return copyright;
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

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public void setGisServerUrl(String gisServerUrl) {
        this.gisServerUrl = gisServerUrl;
    }

    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public void setVirtualPath(String virtualPath) {
        this.virtualPath = virtualPath;
    }

    public void setTokenValidTime(Integer tokenValidTime) {
        this.tokenValidTime = tokenValidTime;
    }

    public void setSingleLogin(Integer singleLogin) {
        this.singleLogin = singleLogin;
    }

    public void setCodeLength(int codeLength) {
        this.codeLength = codeLength;
    }

    public void setCodeValidTime(Integer codeValidTime) {
        this.codeValidTime = codeValidTime;
    }

    public void setSendInterval(Integer sendInterval) {
        this.sendInterval = sendInterval;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}