package com.graykey.xcore.category.controller;

import com.alibaba.fastjson.JSONObject;
import com.graykey.xcore.category.module.Category;
import com.graykey.xcore.category.module.CategoryAttribute;
import com.graykey.xcore.category.service.ICategoryService;
import com.graykey.xcore.category.vo.CategoryAttributeVo;
import com.graykey.xcore.category.vo.CategoryVo;
import com.graykey.xcore.common.base.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 数据字典	Controller层
 *
 * @author xuezb
 * @date 2019-12-31
 */
@Controller
@RequestMapping("/xcore/category")
public class CategoryController extends BaseController {

    @Autowired
    private ICategoryService categoryServiceImpl;


    /**
     * 加载树
     *
     * @param response
     * @param id
     */
    @RequestMapping(value="/category_loadTree")
    public void loadTree(HttpServletResponse response,String id) {
        StringBuffer tree = new StringBuffer();
        tree.append("[");
        if(StringUtils.isBlank(id)){
            //初始化菜单 根节点为 0
            id = "0";
            Category c = this.categoryServiceImpl.getEntityById(id);
            if(c != null){
                tree.append("{");
                tree.append("id:'"+c.getId()+"',");
                tree.append("pId:'',");
                tree.append("name:'"+c.getCategoryName()+"',");
                tree.append("categoryType:'"+c.getCategoryType()+"',");
                tree.append("open:true");
                tree.append("},");
            }
        }
        CategoryVo categoryVo = new CategoryVo();
        categoryVo.setpId(id);
        List<Category> categoryList = this.categoryServiceImpl.queryEntityList(categoryVo);
        if(categoryList != null && categoryList.size() > 0){
            for (Category category : categoryList) {
                tree.append("{");
                tree.append("id:'"+category.getId()+"',");
                tree.append("pId:'"+category.getpId()+"',");
                tree.append("name:'"+category.getCategoryName()+"',");
                tree.append("categoryType:'"+category.getCategoryType()+"'");
                if (category.getIsLeaf() != null && category.getIsLeaf() == 0) {
                    tree.append(",isParent:true");
                }
                tree.append("},");
            }
        }
        tree.deleteCharAt(tree.toString().length()-1);
        tree.append("]");
        this.print(tree.toString());
    }


    /**
     * 列表页面     数据字典
     *
     * @param request
     * @param menuCode
     * @return
     */
    @RequestMapping(value = "/category_list")
    public String list(HttpServletRequest request, String menuCode) {
        request.setAttribute("menuCode", menuCode);
        return "/page/xcore/category/category_list";
    }

    /**
     * 列表数据加载   数据字典
     *
     * @param response
     * @param page
     * @param limit
     * @param categoryVo
     */
    @RequestMapping(value = "/category_load")
    public void load(HttpServletResponse response, Integer page, Integer limit, CategoryVo categoryVo) {
        Page pager = this.categoryServiceImpl.queryEntityList(page, limit, categoryVo);
        this.getPageResult(response, pager, new String[]{"categoryAttributes"});
    }

    /**
     * 编辑页面     数据字典
     *
     * @param request
     * @param categoryVo
     * @return
     */
    @RequestMapping(value = "/category_edit")
    public String edit(HttpServletRequest request, CategoryVo categoryVo) {
        if (StringUtils.isNotBlank(categoryVo.getId())) {
            Category category = this.categoryServiceImpl.getEntityById(categoryVo.getId());
            BeanUtils.copyProperties(category, categoryVo);
            request.setAttribute("categoryVo", categoryVo);
        } else {
            request.setAttribute("categoryVo", categoryVo);
        }
        return "/page/xcore/category/category_edit";
    }

    /**
     * 保存or更新   数据字典
     *
     * @param response
     * @param categoryVo
     */
    @RequestMapping(value = "/category_saveOrUpdate")
    public void saveOrUpdate(HttpServletResponse response, CategoryVo categoryVo) {
        JSONObject json = new JSONObject();
        try {
            Category category = this.categoryServiceImpl.saveOrUpdate(categoryVo);
            json.put("result", true);
            json.put("id",category.getId());
        } catch (Exception e) {
            json.put("result", false);
            logger.error(e.getMessage(), e);
        } finally {
            this.print(json.toString());
        }
    }

    /**
     * 删除   数据字典
     *
     * @param response
     * @param ids
     */
    @RequestMapping(value = "/category_delete")
    public void delete(HttpServletResponse response, String ids) {
        JSONObject json = new JSONObject();
        try {
            if (StringUtils.isNotBlank(ids)) {
                this.categoryServiceImpl.delete(ids);
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
     * 检查 数据字典编码 唯一性
     *
     * @param response
     * @param categoryCode 数据字典编码
     * @param id 数据字典ID
     */
    @RequestMapping(value = "/category_isExistCategory")
    public void isExistCategory(HttpServletResponse response, String categoryCode, String id) {
        JSONObject json = new JSONObject();
        if (StringUtils.isNotBlank(categoryCode)) {
            Category category = this.categoryServiceImpl.getCategoryByCode(categoryCode);
            if (category != null) {
                if (StringUtils.isNotBlank(id) && id.equals(category.getId())) {
                    json.put("result", true);   //唯一
                } else {
                    json.put("result", false);  //不唯一
                }
            } else {
                json.put("result", true);  //唯一
            }
        } else {
            json.put("result", false);
        }
        this.print(json);
    }

    /**
     * 排序   数据字典
     *
     * @param response
     * @param pId 数据字典父ID
     */
    @RequestMapping(value = "/category_getSortNum")
    public void getSortNum(HttpServletResponse response, String pId){
        JSONObject json = new JSONObject();
        if (StringUtils.isNotBlank(pId)) {
            int sortNum = Math.toIntExact(this.categoryServiceImpl.queryEntityCount(pId) + 1);
            json.put("result", true);
            json.put("sortNum", sortNum);
        } else {
            json.put("result", false);
        }
        this.print(json.toString());
    }

    /**
     * 获得数据字典编码前缀
     *
     * @param response
     * @param pId 数据字典父ID
     */
    @RequestMapping(value = "/category_getCategoryCode")
    public void getCategoryCode(HttpServletResponse response, String pId){
        JSONObject json = new JSONObject();
        if (StringUtils.isNotBlank(pId)) {
            String codePrefix = "";

            CategoryVo categoryVo = new CategoryVo();
            categoryVo.setpId(pId);
            List<Category> categoryList = this.categoryServiceImpl.queryEntityList(categoryVo);
            if (!categoryList.isEmpty()) {
                String code = categoryList.get(0).getCategoryCode();
                if (StringUtils.isNotBlank(code)) {
                    codePrefix = code.substring(0, code.indexOf("_") + 1);
                }
            }

            json.put("result", true);
            json.put("codePrefix", codePrefix);
        } else {
            json.put("result", false);
        }
        this.print(json.toString());
    }



    /**
     * 列表数据加载   字典属性
     *
     * @param response
     * @param page
     * @param limit
     * @param categoryId 数据字典ID
     */
    @RequestMapping(value = "/categoryAttribute_load")
    public void loadAttr(HttpServletResponse response, Integer page, Integer limit, String categoryId) {
        Page pager = this.categoryServiceImpl.queryEntityList(page, limit, categoryId);
        this.getPageResult(response, pager, new String[]{"category"});
    }

    /**
     * 编辑页面     字典属性
     *
     * @param request
     * @param categoryAttributeVo
     * @param categoryId 数据字典ID
     * @return
     */
    @RequestMapping(value = "/categoryAttribute_edit")
    public String editAttr(HttpServletRequest request, CategoryAttributeVo categoryAttributeVo, String categoryId) {
        if (StringUtils.isNotBlank(categoryAttributeVo.getId())) {
            CategoryAttribute categoryAttribute = this.categoryServiceImpl.getCategoryAttributeById(categoryAttributeVo.getId());
            BeanUtils.copyProperties(categoryAttribute, categoryAttributeVo);
            request.setAttribute("categoryAttributeVo", categoryAttributeVo);
        } else {
            Category category = new Category();
            category.setId(categoryId);
            categoryAttributeVo.setCategory(category);
            categoryAttributeVo.setIsDefault(2);
            request.setAttribute("categoryAttributeVo", categoryAttributeVo);
        }
        return "/page/xcore/category/categoryAttribute_edit";
    }

    /**
     * 保存or更新   字典属性
     *
     * @param response
     * @param categoryAttributeVo
     */
    @RequestMapping(value = "/categoryAttribute_saveOrUpdate")
    public void saveOrUpdateAttr(HttpServletResponse response, CategoryAttributeVo categoryAttributeVo) {
        JSONObject json = new JSONObject();
        try {
            CategoryAttribute categoryAttribute = this.categoryServiceImpl.saveOrUpdate(categoryAttributeVo);
            json.put("result", true);
        } catch (Exception e) {
            json.put("result", false);
            logger.error(e.getMessage(), e);
        } finally {
            this.print(json.toString());
        }
    }

    /**
     * 删除   字典属性
     *
     * @param response
     * @param ids
     */
    @RequestMapping(value = "/categoryAttribute_delete")
    public void deleteAttr(HttpServletResponse response, String ids) {
        JSONObject json = new JSONObject();
        try {
            if (StringUtils.isNotBlank(ids)) {
                this.categoryServiceImpl.deleteAttr(ids);
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
     * 检查 字典属性键 唯一性
     *
     * @param response
     * @param categoryId 数据字典ID
     * @param attrKey 字典属性键
     * @param id 字典属性ID
     **/
    @RequestMapping(value = "/categoryAttribute_isExist")
    public void isExistCategoryAttr(HttpServletResponse response, String categoryId, String attrKey, String id) {
        JSONObject json = new JSONObject();
        if (StringUtils.isNotBlank(categoryId) && StringUtils.isNotBlank(attrKey)) {
            List<CategoryAttribute> categoryAttributes = this.categoryServiceImpl.queryListByCategoryId(categoryId);
            if (!categoryAttributes.isEmpty()) {
                for (CategoryAttribute categoryAttribute : categoryAttributes) {
                    if (categoryAttribute.getAttrKey().equals(attrKey)) {
                        if (StringUtils.isNotBlank(id) && id.equals(categoryAttribute.getId())) {
                            json.put("result", true);   //唯一
                        } else {
                            json.put("result", false);  //不唯一
                        }
                    } else {
                        json.put("result", true);   //唯一
                    }
                }
            }
        } else {
            json.put("result", false);
        }
        this.print(json);
    }

    /**
     * 排序   字典属性
     *
     * @param response
     * @param categoryId 数据字典ID
     */
    @RequestMapping(value = "/categoryAttribute_getSortNum")
    public void getAttrSortNum(HttpServletResponse response, String categoryId){
        JSONObject json = new JSONObject();
        if (StringUtils.isNotBlank(categoryId)) {
            int sortNum = 1;
            List<CategoryAttribute> categoryAttributes = this.categoryServiceImpl.queryListByCategoryId(categoryId);
            if(!categoryAttributes.isEmpty()){
                sortNum += categoryAttributes.size();
            }
            json.put("result", true);
            json.put("sortNum", sortNum);
        } else {
            json.put("result", false);
        }
        this.print(json.toString());
    }





    /**
     * 获得字典属性值
     *
     * @param response
     * @param dict
     */
    @RequestMapping(value = "/categoryAttribute_getDataDict")
    public void getDataDict(HttpServletResponse response, String dict) {
        StringBuilder keySb = new StringBuilder();
        StringBuilder valueSb = new StringBuilder();
        //TODO
        //for (CategoryAttribute ca : Cache.getDictByCode.get(dict)) {
        //    keySb.append(ca.getAttrKey()+",");
        //    valueSb.append(ca.getAttrValue()+",");
        //}
        JSONObject json = new JSONObject();
        json.put("keys", keySb.deleteCharAt(keySb.length() - 1).toString());
        json.put("values", valueSb.deleteCharAt(valueSb.length() - 1).toString());
        this.print(json.toString());
    }

    /**
     * 数据字典翻译
     *
     * @param response
     * @param dict
     */
    @RequestMapping(value = "/category_getDict")
    public void getDict(HttpServletResponse response, String dict) {
        JSONObject json = new JSONObject();
        //TODO
        //json.put("dict", Cache.getDict.get(dict));
        this.print(json.toString());
    }

    /**
     * 字典翻译 通过字典名称he字典key获得 value
     *
     * @param response
     * @param dict
     * @param key
     */
    @RequestMapping(value = "/category_changeDataDictByKey")
    public void changeDataDictByKey(HttpServletResponse response, String dict, String key) {
        //TODO
        //this.print(DictUtils.getDictValue(dict,key));
    }

    /**
     * 字典翻译 通过字典名称he字典key获得 对应的多值, 用","隔开
     *
     * @param response
     * @param dict
     * @param key
     */
    @RequestMapping(value = "/category_changeDataManyDictByKey")
    public void changeDataManyDictByKey(HttpServletResponse response, String dict, String key) {
        //TODO
        //this.print(DictUtils.getManyDictValue(dict, key));
    }

}