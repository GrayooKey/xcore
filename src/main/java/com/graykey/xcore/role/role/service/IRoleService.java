package com.graykey.xcore.role.role.service;

import com.graykey.xcore.role.role.module.Role;
import com.graykey.xcore.role.role.vo.RoleVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;


/**
 * 角色信息	Service层接口
 *
 * @author xuezb
 * @date 2019-12-31
 */
public interface IRoleService {

    /**
     * 分页
     *
     * @param page
     * @param size
     * @param roleVo
     * @return
     */
    Page queryEntityList(Integer page, Integer size, RoleVo roleVo);

    /**
     * 保存或更新
     *
     * @param roleVo
     * @return
     */
    Role saveOrUpdate(RoleVo roleVo);

    /**
     * 删除
     *
     * @param ids
     */
    void delete(String ids);

    /**
     * 根据ID查询实体对象
     *
     * @param id
     * @return
     */
    Role getEntityById(String id);

    /**
     * 更新实体时设置基础值
     *
     * @param role 要更新的实体
     * @return
     */
    Role getBaseModuleValue(Role role);


    /**
     * 根据查询条件获得角色集合
     *
     * @param roleVo 查询条件
     * @return List<Role>
     */
    List<Role> queryEntityList(RoleVo roleVo);


    /**
     * 保存或更新角色与用户的关联
     *
     * @param roleId  角色Id
     * @param userIds 关联用户id (多个id之间用","隔开)
     */
    void saveOrUpdateRelation(String roleId, String userIds);

    /**
     * 根据userId获取用户角色集合
     *
     * @param userId 用户ID
     * @return
     */
    Set<Role> queryUserRoleList(String userId);

}