package com.graykey.xcore.demo.service.impl;

import com.graykey.xcore.common.chartReport.service.IChartReportService;
import com.graykey.xcore.common.utils.helper.Pager;
import com.graykey.xcore.demo.dao.IDemoDao;
import com.graykey.xcore.demo.module.Demo;
import com.graykey.xcore.demo.service.IDemoService;
import com.graykey.xcore.demo.vo.DemoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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




    @Override
    public Pager queryEntityList(Integer page, Integer limit, DemoVo demoVo) {

        //new JPAQueryFactory()

        return null;
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
            iDemoDao.save(demo);
        } else {
            iDemoDao.save(demo);
        }
        return demo;
    }

    @Override
    public void delete(String ids) {
        String[] idz = ids.split(",");
        for (int i = 0; i < idz.length; i++) {
            Demo demo = iDemoDao.getOne(idz[i]);
            iDemoDao.delete(demo);
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

}