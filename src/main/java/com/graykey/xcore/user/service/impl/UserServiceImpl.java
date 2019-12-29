package com.graykey.xcore.user.service.impl;

import com.graykey.xcore.common.chartReport.service.IChartReportService;
import com.graykey.xcore.common.utils.helper.SecretKeyUtil;
import com.graykey.xcore.role.role.dao.IRoleDao;
import com.graykey.xcore.role.role.module.Role;
import com.graykey.xcore.user.dao.IUserDao;
import com.graykey.xcore.user.dao.IUserExtendDao;
import com.graykey.xcore.user.module.User;
import com.graykey.xcore.user.module.UserExtend;
import com.graykey.xcore.user.service.IUserService;
import com.graykey.xcore.user.vo.UserExtendVo;
import com.graykey.xcore.user.vo.UserVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.*;


/**
 * 用户信息	Service层实现
 *
 * @author xuezb
 * @date 2019-12-26
 */
@Service("userServiceImpl")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao iUserDao;
    @Autowired
    private IUserExtendDao iUserExtendDao;
    @Autowired
    private IRoleDao iRoleDao;
    @Autowired
    private IChartReportService chartReportServiceImpl;


    @Override
    public Page queryEntityList(Integer page, Integer size, UserVo userVo) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "createTime");
        return this.iUserDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (StringUtils.isNotBlank(userVo.getLoginName())) {
                predicateList.add(criteriaBuilder.like(root.get("loginName"), "%" + userVo.getLoginName().trim() + "%"));
            }
            if (StringUtils.isNotBlank(userVo.getUserName())) {
                predicateList.add(criteriaBuilder.like(root.get("userName"), "%" + userVo.getUserName().trim() + "%"));
            }
            if (StringUtils.isNotBlank(userVo.getNickName())) {
                predicateList.add(criteriaBuilder.like(root.get("nickName"), "%" + userVo.getNickName().trim() + "%"));
            }
            if (StringUtils.isNotBlank(userVo.getMobile())) {
                predicateList.add(criteriaBuilder.like(root.get("mobile"), "%" + userVo.getMobile().trim() + "%"));
            }
            if (StringUtils.isNotBlank(userVo.getIdCard())) {
                predicateList.add(criteriaBuilder.like(root.get("idCard"), "%" + userVo.getIdCard().trim() + "%"));
            }
            if (StringUtils.isNotBlank(userVo.getEmail())) {
                predicateList.add(criteriaBuilder.like(root.get("email"), "%" + userVo.getEmail().trim() + "%"));
            }
            if (null != userVo.getSex()) {
                predicateList.add(criteriaBuilder.equal(root.get("sex"), userVo.getSex()));
            }
            if (null != userVo.getNation()) {
                predicateList.add(criteriaBuilder.equal(root.get("nation"), userVo.getNation()));
            }
            if (null != userVo.getEducation()) {
                predicateList.add(criteriaBuilder.equal(root.get("education"), userVo.getEducation()));
            }
            if (null != userVo.getUserType()) {
                predicateList.add(criteriaBuilder.equal(root.get("userType"), userVo.getUserType()));
            }
            if (null != userVo.getState()) {
                predicateList.add(criteriaBuilder.equal(root.get("state"), userVo.getState()));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }, pageable);
    }

    @Override
    public User saveOrUpdate(UserVo userVo) {
        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        if (StringUtils.isBlank(user.getId())) {
            this.iUserDao.save(user);
        } else {
            User old = this.getEntityById(user.getId());
            user.setCreateTime(old.getCreateTime());
            user.setUpdateTime(new Date());
            user.setCreatorId(old.getCreatorId());
            user.setCreatorName(old.getCreatorName());

            user.setPassword(old.getPassword());
            user.setRandomCode(old.getRandomCode());
            user.setTokenCode(old.getTokenCode());
            user.setLoginName(old.getLoginName());  //用户名不可修改
            user.setRoles(old.getRoles());
            this.iUserDao.save(user);
        }
        return user;
    }

    @Override
    public void delete(String ids) {
        String[] idz = ids.split(",");
        for (int i = 0; i < idz.length; i++) {
            User user = this.iUserDao.getOne(idz[i]);

            //删除关联的附件信息
//            List<Attach> attachList = this.attachServiceImpl.queryAttchListByFormId(idz[i]);    //获取附件信息
//            for (Attach attach : attachList) {
//                this.delete(attach);
//            }

            //删除关联的扩展表信息
//            if (user.getUserType() == 6) {
//                UserExtend userExtend = this.getExtendByPrimaryTableId(idz[i]);
//                if (userExtend != null) {
//                    this.delete(userExtend);
//                }
//            }

            this.iUserDao.delete(user);
        }
    }

    @Override
    public List<List> queryBaseCount(UserVo userVo) {
        return null;
    }


    @Override
    public User getEntityById(String id) {
        return this.iUserDao.getOne(id);
    }

    @Override
    public User getBaseModuleValue(User user) {
        User old = this.iUserDao.getOne(user.getId());
        user.setCreateTime(old.getCreateTime());
        user.setUpdateTime(new Date());
        user.setCreatorId(old.getCreatorId());
        user.setCreatorName(old.getCreatorName());
        // TODO 更新修改人id 得等到做完用户登录的时候从session中获取当前用户ID  -- demo.getModifierId();
        return user;
    }



    @Override
    public User isExistUser(String loginName) {
        List<User> userList = this.iUserDao.findAll(((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(criteriaBuilder.or(
                    criteriaBuilder.equal(root.get("loginName"), "%" + loginName.trim() + "%"),
                    criteriaBuilder.equal(root.get("mobile"), "%" + loginName.trim() + "%"),
                    criteriaBuilder.equal(root.get("email"), "%" + loginName.trim() + "%")
            ));
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }));
        if (!userList.isEmpty()) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public User getUserByParam(Map<String, Object> param) {
        List<User> userList = this.iUserDao.findAll(((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            for (Map.Entry<String, Object> entry : param.entrySet()) {
                predicateList.add(criteriaBuilder.like(root.get(entry.getKey()), "%" + entry.getValue()+ "%"));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }));
        if (!userList.isEmpty()) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public User encryptPwd(User user) {
        if(user != null && StringUtils.isNotBlank(user.getPassword())){
            String ec_salt = user.getRandomCode();
            if(StringUtils.isBlank(ec_salt)){
                String random = SecretKeyUtil.createSecretKey(6);
                ec_salt = DigestUtils.md5Hex(random);
                user.setRandomCode(ec_salt);
            }
            String encodePsw = DigestUtils.md5Hex(user.getPassword()+ec_salt);
            user.setPassword(encodePsw);
        }
        return user;
    }

    @Override
    public User checkLogin(String loginName, String password) {
        if(StringUtils.isNotBlank(loginName) && StringUtils.isNotBlank(password)){
            User user = this.isExistUser(loginName);
            if(user != null){
                String ec_salt = user.getRandomCode();
                String encodePsw = DigestUtils.md5Hex(password+ec_salt);
                if(user.getPassword().equals(encodePsw)){
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public String updateResetPwd(String id) {
        User user = this.getEntityById(id);
        if (user != null && StringUtils.isNotBlank(user.getLoginName()) && StringUtils.isNotBlank(user.getRandomCode())) {
            String pwd = SecretKeyUtil.createSecretKey(6);
            String rpwd = DigestUtils.md5Hex(user.getLoginName() + pwd);
            String password = DigestUtils.md5Hex(rpwd + user.getRandomCode());
            user.setPassword(password);
            this.iUserDao.save(user);
            return pwd;
        }
        return null;
    }

    @Override
    public List<User> queryUserList(UserVo userVo) {
        return this.iUserDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (StringUtils.isNotBlank(userVo.getId())) {
                predicateList.add(criteriaBuilder.equal(root.get("id"), userVo.getId().trim()));
            }
            if (StringUtils.isNotBlank(userVo.getLoginName())) {
                predicateList.add(criteriaBuilder.like(root.get("loginName"), "%" + userVo.getLoginName().trim() + "%"));
            }
            if (StringUtils.isNotBlank(userVo.getUserName())) {
                predicateList.add(criteriaBuilder.like(root.get("userName"), "%" + userVo.getUserName().trim() + "%"));
            }
            if (StringUtils.isNotBlank(userVo.getNickName())) {
                predicateList.add(criteriaBuilder.like(root.get("nickName"), "%" + userVo.getNickName().trim() + "%"));
            }
            if (StringUtils.isNotBlank(userVo.getMobile())) {
                predicateList.add(criteriaBuilder.like(root.get("mobile"), "%" + userVo.getMobile().trim() + "%"));
            }
            if (StringUtils.isNotBlank(userVo.getIdCard())) {
                predicateList.add(criteriaBuilder.like(root.get("idCard"), "%" + userVo.getIdCard().trim() + "%"));
            }
            if (StringUtils.isNotBlank(userVo.getEmail())) {
                predicateList.add(criteriaBuilder.like(root.get("email"), "%" + userVo.getEmail().trim() + "%"));
            }
            if (null != userVo.getSex()) {
                predicateList.add(criteriaBuilder.equal(root.get("sex"), userVo.getSex()));
            }
            if (null != userVo.getNation()) {
                predicateList.add(criteriaBuilder.equal(root.get("nation"), userVo.getNation()));
            }
            if (null != userVo.getEducation()) {
                predicateList.add(criteriaBuilder.equal(root.get("education"), userVo.getEducation()));
            }
            if (null != userVo.getUserType()) {
                predicateList.add(criteriaBuilder.equal(root.get("userType"), userVo.getUserType()));
            }
            if (null != userVo.getState()) {
                predicateList.add(criteriaBuilder.equal(root.get("state"), userVo.getState()));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }, Sort.by(Sort.Direction.DESC, "createTime"));
    }

    @Override
    public List<User> queryUserList(UserVo userVo, String sqlStr) {
        return null;
    }

    @Override
    public void saveOrUpdateRelation(String userId, String roleIds) {
        if (StringUtils.isNotBlank(userId)) {
            User user = this.iUserDao.getOne(userId);

            Set<Role> roles = new HashSet<>();
            if (StringUtils.isNotBlank(roleIds)) {
                String[] roleIdArr = roleIds.split(",");
                for (String roleId : roleIdArr) {
                    roles.add(this.iRoleDao.getOne(roleId));
                }
            }

            user.setRoles(roles);
            this.iUserDao.save(user);
        }
    }

    @Override
    public List<User> queryUserChildList(UserVo userVo) {
        return null;
    }

    @Override
    public User getUserByOpenId(String openId) {
        return null;
    }

    @Override
    public User saveWeChatLogin(String encryptedData, String iv, String code) throws Exception {
//        Map<String, String> param = new HashMap<>(4);
//        param.put("appid", Common.MINIPROGRAM_APPID);
//        param.put("secret", Common.MINIPROGRAM_SECRET);
//        param.put("js_code", code);
//        param.put("grant_type", Common.MINIPROGRAM_GRANTTYPE);
//
//        //获取session_id
//        String sr = HttpClientUtil.doGet(Common.MINIPROGRAM_URL, param);
//        // 判断获取的是否有值
//        if (StringUtils.isBlank(sr)) {
//            throw new Exception("获取微信数据失败");
//        }
//        //解析相应内容（转换成json对象）
//        JSONObject json = JSONObject.parseObject(sr);
//        String openId = json.getString("openId");
//        User user = getUserByOpenId(openId);
//        if (user != null) {
//            return user;
//        }
//        //获取会话密钥（session_key）
//        String sessionKey = json.get("session_key").toString();
//        //2、对encryptedData加密数据进行AES解密
//        String result = AesCbcUtil.decrypt(encryptedData, sessionKey, iv, "UTF-8");
//
//        if (StringUtils.isBlank(result)) {
//            throw new Exception("解析微信数据失败");
//        }
//
//        JSONObject userInfoJSON = JSONObject.parseObject(result);
//        user = new User();
//        user.setUserType(Common.USER_TYPE_WECHAT);
//        user.setOpenId(userInfoJSON.getString("openId"));
//        user.setNickName(userInfoJSON.getString("nickName"));
//        user.setSex((userInfoJSON.getInteger("gender") != null && userInfoJSON.getInteger("gender") == 1) ? 1 : 2);
//        user.setHeadimgurl(userInfoJSON.getString("avatarUrl"));
//        this.userDaoImpl.save(user);
//        return user;
        return null;
    }

    @Override
    public void saveOrUpdateExtend(UserExtendVo userExtendVo) {
        UserExtend userExtend = new UserExtend();
        BeanUtils.copyProperties(userExtendVo, userExtend);
        this.iUserExtendDao.save(userExtend);
    }

    @Override
    public UserExtend getExtendByPrimaryTableId(String primaryTableId) {
        List<UserExtend> extendList = this.iUserExtendDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(criteriaBuilder.equal(root.get("userId"), primaryTableId));
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        });
        if (!extendList.isEmpty()) {
            return extendList.get(0);
        }
        return null;
    }

}