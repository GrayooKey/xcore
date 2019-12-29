package com.graykey.xcore.user.module;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户扩展表
 *
 * @author xuezb
 * @date 2019-10-15
 */
@Entity
@org.hibernate.annotations.Table(comment = "用户扩展表", appliesTo = "um_user_extend")
@Table(name = "um_user_extend")
public class UserExtend {

    private String id;                  // 主键id
    private String userId;              // 用户表主键

    private Integer loginNum;           // 已登录次数
    private Integer maxLoginNum;        // 最多登录次数
    private String ipAddress;           // 限制登录IP (只有1个)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date validityDate;          // 登录有限期限


    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(nullable = false, length = 36)
    public String getId() {
        return id;
    }

    @Column(length = 32)
    public String getUserId() {
        return userId;
    }

    public Integer getLoginNum() {
        return loginNum;
    }

    public Integer getMaxLoginNum() {
        return maxLoginNum;
    }

    @Column(length = 16)
    public String getIpAddress() {
        return ipAddress;
    }

    public Date getValidityDate() {
        return validityDate;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setLoginNum(Integer loginNum) {
        this.loginNum = loginNum;
    }

    public void setMaxLoginNum(Integer maxLoginNum) {
        this.maxLoginNum = maxLoginNum;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }
}
