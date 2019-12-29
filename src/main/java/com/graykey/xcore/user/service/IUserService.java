package com.graykey.xcore.user.service;

import com.graykey.xcore.user.module.User;
import com.graykey.xcore.user.module.UserExtend;
import com.graykey.xcore.user.vo.UserExtendVo;
import com.graykey.xcore.user.vo.UserVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;


/**
 * 用户信息	Service层接口
 *
 * @author xuezb
 * @date 2019-12-26
 */
public interface IUserService {

    /**
     * 分页
     *
     * @param page
     * @param size
     * @param userVo
     * @return
     */
    Page queryEntityList(Integer page, Integer size, UserVo userVo);

    /**
     * 保存or更新
     *
     * @param userVo
     * @return
     */
    User saveOrUpdate(UserVo userVo);

    /**
     * 删除
     *
     * @param ids
     */
    void delete(String ids);

    /**
     * 分组统计
     *
     * @param userVo 筛选条件
     * @return
     */
    List<List> queryBaseCount(UserVo userVo);

    /**
     * 根据ID查询实体对象
     *
     * @param id
     * @return
     */
    User getEntityById(String id);

    /**
     * 更新实体时设置基础值
     *
     * @param user 要更新的实体
     * @return
     */
    User getBaseModuleValue(User user);



    /**
     * 用户名,手机号,电子邮箱 是否存在 返回对应User
     *
     * @param loginName
     * @return User
     */
    User isExistUser(String loginName);

    /**
     * 返回符合条件的user,结果只有一个
     * 适用于通过身份证获取用户 手机号获取用户等
     *
     * @param param
     * @return User
     */
    User getUserByParam(Map<String,Object> param);

    /**
     * 密码加密
     *
     * @param user
     * @return User
     */
    User encryptPwd(User user);

    /**
     * 登录验证
     *
     * @param loginName
     * @param password
     * @return User
     */
    User checkLogin(String loginName, String password);

    /**
     * 重置密码
     *
     * @param id
     * @return String 重置后的密码
     */
    String updateResetPwd(String id);

    /**
     * 根据条件 查询 用户集合
     *
     * @param userVo 查询条件
     * @return List<User>
     */
    List<User> queryUserList(UserVo userVo);

    /**
     * 根据条件 查询 用户集合
     *
     * @param userVo 查询条件
     * @param sqlStr 额外的sql查询
     * @return List<User>
     */
    List<User> queryUserList(UserVo userVo, String sqlStr);

    /**
     * 保存或更新用户与角色的关联
     *
     * @param userId  用户Id
     * @param roleIds 关联角色id (多个id之间用","隔开)
     */
    void saveOrUpdateRelation(String userId, String roleIds);

    /**
     * 通过用户所属组织id获得组织下级所有用户集合(包括所属组织的用户)
     *
     * @param userVo 查询条件
     * @return List<User>
     */
    List<User> queryUserChildList(UserVo userVo);

    /**
     * 根据 微信openId 查询用户
     *
     * @param openId 微信唯一标识
     * @return
     */
    User getUserByOpenId(String openId);

    /**
     * 微信登录
     *
     * @param encryptedData 明文,加密数据
     * @param iv            加密算法的初始向量
     * @param code          code值五分钟限制
     * @return 返回用户信息
     * @throws Exception 登录失败
     */
    User saveWeChatLogin(String encryptedData, String iv, String code) throws Exception;


    /*------------------------------  扩展表 start ------------------------------*/

    /**
     * 保存or更新 扩展表信息
     * @param userExtendVo 扩展表信息
     * @return
     */
    void saveOrUpdateExtend(UserExtendVo userExtendVo);

    /**
     * 根据 主表id 查询其扩展表数据
     * @param primaryTableId 主表id
     * @return
     */
    UserExtend getExtendByPrimaryTableId(String primaryTableId);

    /*------------------------------  扩展表 end  ------------------------------*/

}