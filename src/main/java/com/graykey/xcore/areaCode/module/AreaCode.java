package com.graykey.xcore.areaCode.module;

import com.graykey.xcore.common.base.module.BaseModule;
import com.graykey.xcore.user.module.User;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * 行政区划
 *
 * @author xuezb
 * @date 2019-12-07
 */
@Entity
//@org.hibernate.annotations.Table(comment = "行政区划", appliesTo = "core_areaCode")
@Table(name = "core_areaCode")
public class AreaCode extends BaseModule {

    private String pId;          // 父ID
    private String pAreaCode;    // 父AreaCode
    private String areaName;     // 行政区划名字/机构名称
    private String areaCode;     // 行政区划编码/机构编码
    private String pIds;         // 所有父ID
    private String pNames;       // 所有父Name
    private Integer isLeaf;      // 是否叶子节点(行政区划)    0:不是 1:是
    private Integer level;       // 层数  [注: 1是国家,为第一层; 2是省(或直辖市); 3是普通市; 以此类推 ......]
    private Integer state;       // 状态                   数据字典(comm_state)   1:正常     2:停用
    private Integer aoType;      // 行政区划/网格/组织机构    数据字典(area_aoType)  1:行政区划  2:网格  3:组织机构
    private Integer sortNum;     // 排序

    private Integer oisLeaf;     // 是否叶子节点(组织机构/行政区划) (注:用于展示 行政区划与组织机构 一体的树)   0:不是 1:是
    private Integer ohasNextOrg; // 当前组织机构 是否有关联的 下级组织机构 (注:用于展示纯组织机构树)     0:有 1:没有
    private String opId;         // 当前组织机构 关联的 上级组织机构Id    (注:用于展示纯组织机构树)

    private Set<User> users = new TreeSet<User>();      // 关联用户集 【用于设置用户数据权限】


    @Column(length = 32)
    public String getpId() {
        return pId;
    }
    @Column(length = 30)
    public String getpAreaCode() {
        return pAreaCode;
    }
    @Column(length = 30)
    public String getAreaName() {
        return areaName;
    }
    @Column(length = 30)
    public String getAreaCode() {
        return areaCode;
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
    public Integer getLevel() {
        return level;
    }
    public Integer getState() {
        return state;
    }
    public Integer getAoType() {
        return aoType;
    }
    public Integer getSortNum() {
        return sortNum;
    }
    public Integer getOisLeaf() {
        return oisLeaf;
    }
    public Integer getOhasNextOrg() {
        return ohasNextOrg;
    }
    @Column(length = 32)
    public String getOpId() {
        return opId;
    }

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "orgFrame", fetch = FetchType.LAZY)
    //@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "urms_cache")
    public Set<User> getUsers() {
        return users;
    }


    public void setpId(String pId) {
        this.pId = pId;
    }

    public void setpAreaCode(String pAreaCode) {
        this.pAreaCode = pAreaCode;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
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

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setAoType(Integer aoType) {
        this.aoType = aoType;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public void setOisLeaf(Integer oisLeaf) {
        this.oisLeaf = oisLeaf;
    }

    public void setOhasNextOrg(Integer ohasNextOrg) {
        this.ohasNextOrg = ohasNextOrg;
    }

    public void setOpId(String opId) {
        this.opId = opId;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}