package com.graykey.xcore.category.service.impl;

import com.graykey.xcore.category.dao.ICategoryAttributeDao;
import com.graykey.xcore.category.dao.ICategoryDao;
import com.graykey.xcore.category.module.Category;
import com.graykey.xcore.category.module.CategoryAttribute;
import com.graykey.xcore.category.service.ICategoryService;
import com.graykey.xcore.category.vo.CategoryAttributeVo;
import com.graykey.xcore.category.vo.CategoryVo;
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
 * 数据字典	Service层实现
 *
 * @author xuezb
 * @date 2019-12-31
 */
@Service("categoryServiceImpl")
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryDao iCategoryDao;
    @Autowired
    private ICategoryAttributeDao iCategoryAttributeDao;


    @Override
    public Page queryEntityList(Integer page, Integer size, CategoryVo categoryVo) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "sortNum");
        return this.iCategoryDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            //列表页不显示顶级目录
            predicateList.add(criteriaBuilder.notEqual(root.get("id"), "0"));
            if (StringUtils.isNotBlank(categoryVo.getpId())) {
                predicateList.add(criteriaBuilder.equal(root.get("pId"), categoryVo.getpId().trim()));
            }
            if (StringUtils.isNotBlank(categoryVo.getCategoryCode())) {
                predicateList.add(criteriaBuilder.like(root.get("categoryType"), "%" + categoryVo.getCategoryCode().trim() + "%"));
            }
            if (StringUtils.isNotBlank(categoryVo.getCategoryName())) {
                predicateList.add(criteriaBuilder.like(root.get("categoryName"), "%" + categoryVo.getCategoryName().trim() + "%"));
            }
            if (null != categoryVo.getCategoryType()) {
                predicateList.add(criteriaBuilder.equal(root.get("categoryType"), categoryVo.getCategoryType()));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }, pageable);
    }

    @Override
    public Page queryEntityList(Integer page, Integer size, String categoryId) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "sortNum");
        return this.iCategoryAttributeDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(criteriaBuilder.equal(root.get("category.id"), categoryId));
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }, pageable);
    }

    @Override
    public Category saveOrUpdate(CategoryVo categoryVo) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryVo, category);
        if (StringUtils.isBlank(category.getId())) {
            Category pCategory = this.iCategoryDao.getOne(category.getpId());
            if (pCategory.getIsLeaf() == 1) {
                pCategory.setIsLeaf(0);
                this.iCategoryDao.save(pCategory);
            }

            //所有父ID
            if(StringUtils.isNotBlank(pCategory.getpIds())){
                category.setpIds(pCategory.getpIds() + "/" + pCategory.getId());
            }else{
                category.setpIds(pCategory.getId());
            }
            //所有父Name
            if (StringUtils.isNotBlank(pCategory.getpNames())) {
                category.setpNames(pCategory.getpNames() + "/" + pCategory.getCategoryName());
            }else{
                category.setpNames(pCategory.getCategoryName());
            }
            //是否叶节点
            category.setIsLeaf(1);

            this.iCategoryDao.save(category);
        } else {
            category = this.getBaseModuleValue(category);
            this.iCategoryDao.save(category);
        }
        this.refreshCategory(category);
        return category;
    }

    @Override
    public CategoryAttribute saveOrUpdate(CategoryAttributeVo categoryAttributeVo) {
        CategoryAttribute categoryAttribute = new CategoryAttribute();
        BeanUtils.copyProperties(categoryAttributeVo, categoryAttribute);
        if (StringUtils.isBlank(categoryAttribute.getId())) {
            this.iCategoryAttributeDao.save(categoryAttribute);
        } else {
            this.iCategoryAttributeDao.save(categoryAttribute);
        }
        this.refreshCategory(categoryAttribute);
        return categoryAttribute;
    }

    /**
     * 添加修改缓存 数据字典
     *
     * @param category
     */
    private void refreshCategory(Category category){
        if(category != null){
            //Map<String,String> dictMap = Cache.getDictByCodeMap.get(category.getCategoryCode());
            //if(dictMap != null && dictMap.size() > 0){ //没有修改编码 无需刷新
            //    return;
            //}
            Map<String,String> map = new LinkedHashMap<>();
            if(category.getCategoryAttributes() != null && category.getCategoryAttributes().size() > 0){
                for (CategoryAttribute ca : category.getCategoryAttributes()) {//使用了延时加载 故不能删掉
                    map.put(ca.getAttrKey(), ca.getAttrValue());
                }
            }
            //Cache.getDictByCode.put(category.getCategoryCode(), category.getCategoryAttributes());//带默认值
            //Cache.getDictByCodeMap.put(category.getCategoryCode(), map);
        }
    }

    /**
     * 添加修改缓存 字典属性
     *
     * @param categoryAttribute 字典属性
     */
    private void refreshCategory(CategoryAttribute categoryAttribute){
        Category category = this.iCategoryDao.getOne(categoryAttribute.getCategory().getId());
        if(category != null){
            Map<String, String> map = new LinkedHashMap<>();
            if(category.getCategoryAttributes() != null){
                if(categoryAttribute != null){
                    category.getCategoryAttributes().add(categoryAttribute);
                }
                for (CategoryAttribute ca : category.getCategoryAttributes()) { //使用了延时加载 故不能删掉
                    map.put(ca.getAttrKey(), ca.getAttrValue());
                }
            }
            //TODO
            //Cache.getDictByCodeMap.put(category.getCategoryCode(), map);
            //Cache.getDictByCode.put(category.getCategoryCode(), category.getCategoryAttributes());//带默认值
        }
    }


    @Override
    public void delete(String ids) {
        String[] idz = ids.split(",");
        for (int i = 0; i < idz.length; i++) {
            if (idz[i].equals("0")) {
                break;
            }

            Category category = this.iCategoryDao.getOne(idz[i]);
            if(category != null){
                //Cache.getDictByCodeMap.get(category.getCategoryCode()).clear();
                //Cache.getDictByCode.get(category.getCategoryCode()).clear();

                //更新父节点
                String pId = category.getpId();
                long num = this.queryEntityCount(pId);
                if (num < 2) {
                    Category pCategory = this.iCategoryDao.getOne(pId);
                    pCategory.setIsLeaf(1);
                    this.iCategoryDao.save(pCategory);
                }

                //删除字典属性
                if (category.getCategoryAttributes() != null) {
                    for (CategoryAttribute attribute : category.getCategoryAttributes()) {
                        this.iCategoryAttributeDao.delete(attribute);
                    }
                }

                this.iCategoryDao.delete(category);
            }
        }
    }

    @Override
    public void deleteAttr(String ids) {
        String[] idz = ids.split(",");
        if(idz != null && idz.length>0){
            CategoryAttribute categoryAttribute = this.iCategoryAttributeDao.getOne(idz[0]);
            if(categoryAttribute != null){
                Category category = categoryAttribute.getCategory();
                if(category != null){
                    //Map<String,String> map = Cache.getDictByCodeMap.get(category.getCategoryCode());
                    Set<CategoryAttribute> categoryAttributeSet = category.getCategoryAttributes();
                    for (int i = 0; i < idz.length; i++) {
                        CategoryAttribute categoryAttr = this.iCategoryAttributeDao.getOne(idz[i]);
                        //map.remove(categoryAttr.getAttrKey());
                        categoryAttributeSet.remove(categoryAttr);
                        this.iCategoryAttributeDao.delete(categoryAttr);
                    }
                    //Cache.getDictByCodeMap.put(category.getCategoryCode(), map);
                    //Cache.getDictByCode.put(category.getCategoryCode(), categoryAttributeSet);//带默认值
                }
            }
        }
    }

    @Override
    public Category getEntityById(String id) {
        return this.iCategoryDao.getOne(id);
    }

    @Override
    public CategoryAttribute getCategoryAttributeById(String id) {
        return this.iCategoryAttributeDao.getOne(id);
    }

    @Override
    public Category getBaseModuleValue(Category category) {
        Category old = this.iCategoryDao.getOne(category.getId());
        category.setCreateTime(old.getCreateTime());
        category.setUpdateTime(new Date());
        category.setCreatorId(old.getCreatorId());
        category.setCreatorName(old.getCreatorName());
        // TODO 更新修改人id 得等到做完用户登录的时候从session中获取当前用户ID  -- category.getModifierId();
        category.setCategoryAttributes(old.getCategoryAttributes());
        return category;
    }

    @Override
    public long queryEntityCount(String pId) {
        return this.iCategoryDao.count(((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(criteriaBuilder.equal(root.get("pId"), pId));
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }));
    }

    @Override
    public Category getCategoryByCode(String code) {
        List<Category> list = this.iCategoryDao.findAll(((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(criteriaBuilder.equal(root.get("categoryCode"), code));
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }));
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Category> queryEntityList(CategoryVo categoryVo) {
        return this.iCategoryDao.findAll(((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (StringUtils.isNotBlank(categoryVo.getpId())) {
                predicateList.add(criteriaBuilder.equal(root.get("pId"), categoryVo.getpId()));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }), Sort.by(Sort.Direction.ASC, "sortNum"));
    }

    @Override
    public List<CategoryAttribute> queryListByCategoryId(String categoryId) {
        return this.iCategoryAttributeDao.findAll(((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(criteriaBuilder.equal(root.get("category.id"), categoryId));
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }), Sort.by(Sort.Direction.ASC, "sortNum"));
    }

    @Override
    public void getDictByCode() {
//        Cache.getDictByCode.clear();//先清除
//        Cache.getDictByCodeMap.clear();//先清除
//        List<Criterion> criterionList = new ArrayList<>();
//        List<Category> categoryList = this.categoryDaoImpl.queryList(criterionList, null ,Category.class);
//        if(categoryList != null && categoryList.size() > 0){
//            for (int i = 0; i < categoryList.size(); i++) {
//                Category category = categoryList.get(i);
//                Map<String,String> map = new LinkedHashMap<String,String>();
//                if(category.getCategoryAttributes() != null && category.getCategoryAttributes().size() > 0){
//                    for (CategoryAttribute ca : category.getCategoryAttributes()) {  //使用了延时加载 故不能删掉
//                        map.put(ca.getAttrKey(), ca.getAttrValue());
//                    }
//                }
//                Cache.getDictByCode.put(category.getCategoryCode(), category.getCategoryAttributes());//为了带上默认值
//                Cache.getDictByCodeMap.put(category.getCategoryCode(), map);
//            }
//        }
//
//        //字典目录
//        Cache.getDict.clear();//先清除
//        List<Criterion> criterion = new ArrayList<Criterion>();
//        criterion.add(Restrictions.eq("categoryType", 1));//字典目录
//        List<Category> categoryL = this.categoryDaoImpl.queryList(criterion, null ,Category.class);
//        if(categoryL != null && categoryL.size() > 0){
//            for (int i = 0; i < categoryL.size(); i++) {
//                Category category = categoryL.get(i);
//                Cache.getDict.put(category.getCategoryCode(), category.getCategoryName());
//            }
//        }
    }

}