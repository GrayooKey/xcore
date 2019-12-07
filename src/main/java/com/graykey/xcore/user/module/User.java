package com.graykey.xcore.user.module;

import com.graykey.xcore.areaCode.module.AreaCode;
import com.graykey.xcore.common.base.module.BaseModule;
import com.graykey.xcore.role.role.module.Role;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 * 用户信息
 *
 * @author xuezb
 * @date 2019-12-07
 */
@Entity
@org.hibernate.annotations.Table(comment = "用户信息", appliesTo = "core_user")
@Table(name = "core_user")
public class User extends BaseModule {

    private String loginName;                     // 用户名    默认手机号码-必须唯一
    private String password;                      // 密码     MD5(UP+随即码)
    private String randomCode;                    // 随机码    32位长度随机数
    private String tokenCode;                     // token    MD5(随机数+时间戳)
    private String userName;                      // 真实姓名
    private String nickName;                      // 网咯昵称
    private String idCard;                        // 身份证号
    private String mobile;                        // 手机号码
    private String officePhone;                   // 固定电话
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;                        // 生日     yyyy-MM-dd
    private Integer sex;                          // 性别
    private Integer nation;                       // 民族
    private Integer education;                    // 文化程度/学历
    private String email;                         // 电子邮箱
    private String bornProvince;                  // 户籍地(省)
    private String bornCity;                      // 户籍地(市)
    private String bornDistrict;                  // 户籍地(区/县)
    private String bornStreet;                    // 户籍地(街道/镇)
    private String bornVillage;                   // 户籍地(社区/村)
    private String bornAddress;                   // 户籍详址
    private String liveProvince;                  // 居住地(省)
    private String liveCity;                      // 居住地(市)
    private String liveDistrict;                  // 居住地(区/县)
    private String liveStreet;                    // 居住地(街道/镇)
    private String liveVillage;                   // 居住地(社区/村)
    private String liveAddress;                   // 居住详址
    private Integer userType;                     // 用户类型  数据字典(user_type)   1平台超管 2系统超管 3系统普通用户(政务用户) 4个人(注册会员) 5企业/商家(注册会员) 6临时用户
    private Integer state;                        // 状态     数据字典(user_state)
    private String memo;                          // 备注

    private Set<Role> roles = new TreeSet<Role>();   // 角色集

    private AreaCode orgFrame;                    // 关联的组织机构 【用于设置用户数据权限】

    //private String qq;                            // QQ
    //private String openId;                        // 微信唯一标识
    //private String headimgurl;                    // 微信头像路径


    @Column(length = 30)
    public String getLoginName() {
        return loginName;
    }
    @Column(length = 32)
    public String getPassword() {
        return password;
    }
    @Column(length = 32)
    public String getRandomCode() {
        return randomCode;
    }
    @Column(length = 32)
    public String getTokenCode() {
        return tokenCode;
    }
    @Column(length = 30)
    public String getUserName() {
        return userName;
    }
    @Column(length = 30)
    public String getNickName() {
        return nickName;
    }
    @Column(length = 20)
    public String getIdCard() {
        return idCard;
    }
    @Column(length = 20)
    public String getMobile() {
        return mobile;
    }
    @Column(length = 20)
    public String getOfficePhone() {
        return officePhone;
    }
    public Date getBirthday() {
        return birthday;
    }
    public Integer getSex() {
        return sex;
    }
    public Integer getNation() {
        return nation;
    }
    public Integer getEducation() {
        return education;
    }
    @Column(length = 50)
    public String getEmail() {
        return email;
    }
    @Column(length = 20)
    public String getBornProvince() {
        return bornProvince;
    }
    @Column(length = 20)
    public String getBornCity() {
        return bornCity;
    }
    @Column(length = 20)
    public String getBornDistrict() {
        return bornDistrict;
    }
    @Column(length = 20)
    public String getBornStreet() {
        return bornStreet;
    }
    @Column(length = 20)
    public String getBornVillage() {
        return bornVillage;
    }
    @Column(length = 100)
    public String getBornAddress() {
        return bornAddress;
    }
    @Column(length = 20)
    public String getLiveProvince() {
        return liveProvince;
    }
    @Column(length = 20)
    public String getLiveCity() {
        return liveCity;
    }
    @Column(length = 20)
    public String getLiveDistrict() {
        return liveDistrict;
    }
    @Column(length = 20)
    public String getLiveStreet() {
        return liveStreet;
    }
    @Column(length = 20)
    public String getLiveVillage() {
        return liveVillage;
    }
    @Column(length = 100)
    public String getLiveAddress() {
        return liveAddress;
    }
    public Integer getUserType() {
        return userType;
    }
    public Integer getState() {
        return state;
    }
    @Column(length = 200)
    public String getMemo() {
        return memo;
    }

    @ManyToMany(fetch = FetchType.EAGER)    // 原先：lazy 事件管理：eager
    @JoinTable(name = "core_user_role", joinColumns = {@JoinColumn(name = "userId")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    //@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "urms_cache")
    public Set<Role> getRoles() {
        return roles;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="orgFrameId")
    //@org.hibernate.annotations.Cache(usage= CacheConcurrencyStrategy.READ_WRITE,region="urms_cache")
    public AreaCode getOrgFrame() {
        return orgFrame;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }

    public void setTokenCode(String tokenCode) {
        this.tokenCode = tokenCode;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setNation(Integer nation) {
        this.nation = nation;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBornProvince(String bornProvince) {
        this.bornProvince = bornProvince;
    }

    public void setBornCity(String bornCity) {
        this.bornCity = bornCity;
    }

    public void setBornDistrict(String bornDistrict) {
        this.bornDistrict = bornDistrict;
    }

    public void setBornStreet(String bornStreet) {
        this.bornStreet = bornStreet;
    }

    public void setBornVillage(String bornVillage) {
        this.bornVillage = bornVillage;
    }

    public void setBornAddress(String bornAddress) {
        this.bornAddress = bornAddress;
    }

    public void setLiveProvince(String liveProvince) {
        this.liveProvince = liveProvince;
    }

    public void setLiveCity(String liveCity) {
        this.liveCity = liveCity;
    }

    public void setLiveDistrict(String liveDistrict) {
        this.liveDistrict = liveDistrict;
    }

    public void setLiveStreet(String liveStreet) {
        this.liveStreet = liveStreet;
    }

    public void setLiveVillage(String liveVillage) {
        this.liveVillage = liveVillage;
    }

    public void setLiveAddress(String liveAddress) {
        this.liveAddress = liveAddress;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setOrgFrame(AreaCode orgFrame) {
        this.orgFrame = orgFrame;
    }
}