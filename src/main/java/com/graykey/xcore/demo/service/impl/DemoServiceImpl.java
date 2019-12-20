package com.graykey.xcore.demo.service.impl;

import com.graykey.xcore.common.chartReport.service.IChartReportService;
import com.graykey.xcore.demo.dao.IDemoDao;
import com.graykey.xcore.demo.module.Demo;
import com.graykey.xcore.demo.service.IDemoService;
import com.graykey.xcore.demo.vo.DemoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 示例	Service层实现
 *
 * @author xuezb
 * @date 2019-12-09
 */
@Service("demoServiceImpl")
public class DemoServiceImpl implements IDemoService {

    @Autowired
    private IDemoDao iDemoDao;
    @Autowired
    private IChartReportService chartReportServiceImpl;


    @PersistenceContext
    private EntityManager em;


    @Override
    public Page queryEntityList(Integer page, Integer size, DemoVo demoVo) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "createTime");

        // 方法一: JPA 分页, 持久层接口需继承 JpaRepository<T, ID> 和 JpaSpecificationExecutor<T> 接口
        Page<Demo> pager = this.iDemoDao.findAll(new Specification<Demo>() {
            @Override
            public Predicate toPredicate(Root<Demo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                ArrayList<Predicate> predicateList = new ArrayList<>();
                if (StringUtils.isNotBlank(demoVo.getName())) {
                    predicateList.add(criteriaBuilder.like(root.get("name").as(String.class), demoVo.getName().trim()));
                }
                if (demoVo.getNumber() != null) {
                    predicateList.add(criteriaBuilder.equal(root.get("number").as(Integer.class), demoVo.getNumber()));
                }
                Predicate[] p = new Predicate[predicateList.size()];
                return criteriaBuilder.and(predicateList.toArray(p));
            }
        }, PageRequest.of(page - 1, size, Sort.Direction.DESC, "createTime"));


        //用lambda表达式简化代码
        Page<Demo> pager1 = this.iDemoDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            //模糊查询
            if (StringUtils.isNotBlank(demoVo.getName())) {
                predicateList.add(criteriaBuilder.like(root.get("name"), "%" + demoVo.getName().trim() + "%"));
            }
            //精确查询
            if (null != demoVo.getNumber()) {
                predicateList.add(criteriaBuilder.equal(root.get("number"), demoVo.getNumber()));
            }

            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }, pageable);

        return pager1;

/*
        // 方法二: JPA + queryDSL 分页, 持久层接口需继承 JpaRepository<T, ID> 和 QuerydslPredicateExecutor<T> 接口
        QDemo qDemo = QDemo.demo;
        List<com.querydsl.core.types.Predicate> predicateList = new ArrayList<>();
        com.querydsl.core.types.Predicate predicate = qDemo.name.eq(demoVo.getName().trim());
        if (StringUtils.isNotBlank(demoVo.getName())) {
            qDemo.name.like(demoVo.getName().trim());
        }
        if (demoVo.getNumber() != null) {
            qDemo.number.eq(demoVo.getNumber());
        }
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.Direction.DESC, "createTime");
        Page<Demo> all = this.iDemoDao.findAll(predicate, pageRequest);

        // 方法三: JPA + queryDSL 分页, 持久层接口需继承 JpaRepository<T, ID> 和 QuerydslPredicateExecutor<T> 接口, 还需要注入 EntityManager
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        JPAQuery<Demo> jpaQuery = queryFactory.select(QDemo.demo).from(QDemo.demo).where(predicate).offset(pageRequest.getOffset()).limit(pageRequest.getPageSize());
        QueryResults<Demo> result = jpaQuery.fetchResults();
        result.getTotal();
        List<Demo> list = result.getResults();
*/
    }


//    public void queryEntityList(Integer page, Integer limit, DemoVo demoVo) {
//
//        PageRequest pageRequest = PageRequest.of(page, limit, Sort.Direction.DESC, "createTime");
//
//        QDemo qDemo = QDemo.demo;
//        Predicate predicate = QDemo.demo.name.like("hehe");
//
//
//        QMenu qMenu = QMenu.menu;
//        qMenu.id.eq("123");
//        Predicate predicate = qMenu.menuName.like("hehe");
//
//        PageRequest pageRequest = PageRequest.of(page, limit, Sort.Direction.DESC, "createTime");
//
//        Page<Menu> menuPage = iMenuDao.findAll(predicate, pageRequest);
//    }

    @Override
    public Demo saveOrUpdate(DemoVo demoVo) {
        Demo demo = new Demo();
        BeanUtils.copyProperties(demoVo, demo);
        if (StringUtils.isBlank(demo.getId())) {
            this.iDemoDao.save(demo);
        } else {
            demo = this.getBaseModuleValue(demo);
            this.iDemoDao.save(demo);
        }
        return demo;
    }

    @Override
    public void delete(String ids) {
        String[] idz = ids.split(",");
        for (int i = 0; i < idz.length; i++) {
            this.iDemoDao.deleteById(idz[i]);
        }
    }

    @Override
    public List<List> queryBaseCount(DemoVo demoVo) {
//        StringBuffer sql = new StringBuffer();
//        sql.append("select xx_field,count(id) from xx_table where xx_field != '' and xx_field is not null ");
//        // 筛选条件
//        if (demoVo != null) {
//            //...
//        }
//        sql.append(" group by xx_field");
//        List<Object> list = this.baseDaoImpl.queryBySql(sql.toString());
//        iDemoDao.
//
//        List<List> arrayList = new ArrayList<>();
//        List<Map<String, Object>> mapList0 = this.chartReportServiceImpl.groupingStatisticsForList("xx_categoryCode");
//        Map<String, Object> map1 = mapList0.get(0);  //map1:(key:数量)
//        Map<String, Object> map2 = mapList0.get(1);  //map2:(key:value)
//
//        if (map1 != null && map1.size() > 0) {
//            for (int i = 0; i < list.size(); i++) {
//                Object[] obj = (Object[]) list.get(i);
//
//                map1.put(obj[0].toString(), (BigInteger) obj[1]);
//            }
//            for (String key : map1.keySet()) { //将map2的值(数据字典翻译值)作为map的键,将map1的值(统计数量)作为map的值
//                List<String> l = new ArrayList<>();
//                l.add(map2.get(key).toString());
//                l.add(map1.get(key).toString());
//                arrayList.add(l);
//            }
//        }
//
//        return arrayList;

        return new ArrayList<>();
    }


    @Override
    public Demo getEntityById(String id) {
        return this.iDemoDao.getOne(id);
    }

    @Override
    public Demo getBaseModuleValue(Demo demo) {
        Demo old = this.iDemoDao.getOne(demo.getId());
        demo.setCreateTime(old.getCreateTime());
        demo.setUpdateTime(new Date());
        demo.setCreatorId(old.getCreatorId());
        demo.setCreatorName(old.getCreatorName());
        // TODO 更新修改人id 得等到做完用户登录的时候从session中获取当前用户ID  -- demo.getModifierId();
        return demo;
    }

}