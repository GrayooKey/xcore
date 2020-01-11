package com.graykey.xcore.areaCode.controller;

import com.alibaba.fastjson.JSONObject;
import com.graykey.xcore.areaCode.module.AreaCode;
import com.graykey.xcore.areaCode.service.IAreaCodeService;
import com.graykey.xcore.areaCode.vo.AreaCodeVo;
import com.graykey.xcore.common.base.controller.BaseController;
import com.graykey.xcore.user.module.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * 行政区划	Controller层
 *
 * @author xuezb
 * @date 2019-12-31
 */
@Controller
@RequestMapping("/xcore/areaCode")
public class AreaCodeController extends BaseController {

    @Autowired
    private IAreaCodeService areaCodeServiceImpl;


    /**
     * 列表页面
     *
     * @param request
     * @param menuCode
     * @return
     */
    @RequestMapping(value = "/areaCode_list")
    public String list(HttpServletRequest request, String menuCode) {
        request.setAttribute("menuCode", menuCode);
        return "/page/xcore/areaCode/areaCode_list";
    }

    /**
     * 列表数据加载
     *
     * @param response
     * @param page
     * @param limit
     * @param areaCodeVo
     */
    @RequestMapping(value = "/areaCode_load")
    public void load(HttpServletResponse response, Integer page, Integer limit, AreaCodeVo areaCodeVo) {
        Page pager = this.areaCodeServiceImpl.queryEntityList(page, limit, areaCodeVo);
        this.getPageResult(response, pager, new String[]{"users"});
    }

    /**
     * 编辑页面
     *
     * @param request
     * @param areaCodeVo
     * @return
     */
    @RequestMapping(value = "/areaCode_edit")
    public String edit(HttpServletRequest request, AreaCodeVo areaCodeVo) {
        if (StringUtils.isNotBlank(areaCodeVo.getId())) {
            AreaCode areaCode = this.areaCodeServiceImpl.getEntityById(areaCodeVo.getId());
            BeanUtils.copyProperties(areaCode, areaCodeVo);
            request.setAttribute("areaCodeVo", areaCodeVo);
        } else {
            request.setAttribute("areaCodeVo", areaCodeVo);
        }
        return "/page/xcore/areaCode/areaCode_edit";
    }

    /**
     * 保存or更新
     *
     * @param response
     * @param areaCodeVo
     */
    @RequestMapping(value = "/areaCode_saveOrUpdate")
    public void saveOrUpdate(HttpServletResponse response, AreaCodeVo areaCodeVo) {
        JSONObject json = new JSONObject();
        try {
            AreaCode areaCode = this.areaCodeServiceImpl.saveOrUpdate(areaCodeVo);
            json.put("result", true);
            json.put("id", areaCode.getId());
        } catch (Exception e) {
            json.put("result", false);
            logger.error(e.getMessage(), e);
        } finally {
            this.print(json.toString());
        }
    }

    /**
     * 删除
     *
     * @param response
     * @param ids
     */
    @RequestMapping(value = "/areaCode_delete")
    public void delete(HttpServletResponse response, String ids) {
        JSONObject json = new JSONObject();
        try {
            if (StringUtils.isNotBlank(ids)) {
                this.areaCodeServiceImpl.delete(ids);
                json.put("result", true);
            }
        } catch (Exception e) {
            json.put("result", false);
            logger.error(e.getMessage(), e);
        } finally {
            this.print(json.toString());
        }
    }


    /**
     * 加载行政区划树
     *
     * @param session
     * @param response
     * @param id
     * @param type     树类型 一般加载系统配置的MapCode下的树,行政区划管理加载所有
     */
    @RequestMapping(value = "/areaCode_areaTree")
    public void areaTree(HttpSession session, HttpServletResponse response, String id, String type) {
        StringBuffer tree = new StringBuffer();
        tree.append("[");
        if (StringUtils.isBlank(id)) {
            String mapCode;
            User user = this.getSessionUser();
            if(user.getUserType() == 1){
                mapCode = "area"; //Cache.getSystemConfig.getMapCode();
            }else {
                mapCode = (String)session.getAttribute("areaCode");
            }
            if (StringUtils.isNotBlank(type) && type.equals("manager")) {
                mapCode = "area";
            }
            AreaCode areaCode = this.areaCodeServiceImpl.getAreaByCode(mapCode);
            if (areaCode != null) {
                id = areaCode.getId();
                tree.append("{");
                tree.append("id:'" + areaCode.getId() + "',");
                tree.append("pId:'',");
                tree.append("name:'" + areaCode.getAreaName() + "',");
                tree.append("areaCode:'" + areaCode.getAreaCode() + "',");
                tree.append("open:true");
                tree.append("},");
            }
        }
        AreaCodeVo areaCodeVo = new AreaCodeVo();
        areaCodeVo.setpId(id);
        List<AreaCode> areaCodeList = this.areaCodeServiceImpl.queryEntityList(areaCodeVo);
        if (areaCodeList != null && areaCodeList.size() > 0) {
            for (AreaCode areaCode : areaCodeList) {
                tree.append("{");
                tree.append("id:'" + areaCode.getId() + "',");
                tree.append("pId:'" + areaCode.getpId() + "',");
                tree.append("name:'" + areaCode.getAreaName() + "',");
                tree.append("areaCode:'" + areaCode.getAreaCode() + "'");
                if (areaCode.getIsLeaf() == 0) {
                    tree.append(",isParent:true");
                }
                tree.append("},");
            }
        }

        tree.deleteCharAt(tree.toString().length() - 1);
        tree.append("]");
        this.print(tree.toString());
    }

    /**
     * 检查 行政区划编码 唯一性
     *
     * @param response
     * @param code 行政区划编码
     */
    @RequestMapping(value = "/areaCode_isExist")
    public void isExistAreaCode(HttpServletResponse response, String code) {
        JSONObject json = new JSONObject();
        if (StringUtils.isNotBlank(code)) {
            AreaCode areaCode = this.areaCodeServiceImpl.getAreaByCode(code);
            if (areaCode != null) {
                json.put("result", false);  //不唯一
            } else {
                json.put("result", true);   //唯一
            }
        } else {
            json.put("result", false);
        }
        this.print(json.toString());
    }

    /**
     * 获取 行政区划 排序值
     *
     * @param response
     * @param pId 父ID
     */
    @RequestMapping(value = "/areaCode_getSortNum")
    public void getSortNum(HttpServletResponse response, String pId) {
        JSONObject json = new JSONObject();
        if (StringUtils.isNotBlank(pId)) {
            int sortNum = Math.toIntExact(this.areaCodeServiceImpl.queryEntityCount(pId) + 1);
            json.put("result", true);
            json.put("sortNum", sortNum);
        } else {
            json.put("result", false);
        }
        this.print(json.toString());
    }


    /**
     * 根据行政区划编码获取对应的行政区划名称
     *
     * @param response
     * @param areaCode
     */
    @RequestMapping(value = "/areaCode_changeAreaNameByAreaCode")
    public void changeAreaNameByAreaCode(HttpServletResponse response, String areaCode) {
        //this.print(DictUtils.getAreaValue(areaCode));
    }


    /**
     * 更新五级行政区划插件json文件
     *
     * @param request
     * @param response
     * @param type 级别 2=省市 3=区县 4=街道/镇 5=村/社区
     */
    @RequestMapping(value = "/areaCode_refreshAreaFile")
    public void refreshAreaFile(HttpServletRequest request, HttpServletResponse response, int type) {
        this.areaCodeServiceImpl.createAreaCodeFile(type);
        JSONObject json = new JSONObject();
        json.put("result", true);
        this.print(json.toString());
    }
}