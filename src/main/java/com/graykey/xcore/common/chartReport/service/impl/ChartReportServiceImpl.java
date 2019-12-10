package com.graykey.xcore.common.chartReport.service.impl;

import com.graykey.xcore.common.chartReport.service.IChartReportService;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * ECharts图表数据统计 和 列表页面头部分组统计 的工具类
 *
 * @author xuezb
 * @date 2019-07-16
 */
@Repository("chartReportServiceImpl")
public class ChartReportServiceImpl implements IChartReportService {

	@Override
	public Map<Object, Object> groupingStatisticsForECharts(String dictCode) {

		String sql = "select t.attrKey,t.attrValue from um_category uc,um_category_attribute t where t.categoryId = uc.id and uc.categoryCode = '" + dictCode + "'";

		//List<Object> keyList = this.baseDaoImpl.queryBySql(sql);
		List<Object> keyList = new ArrayList<>();

		Map<Object, Object> map = new TreeMap<>();
		// 遍历字典属性键值对(key,value)集合,并将key逐个存放到map中,并设其默认值为0
		for(int i = 0; i < keyList.size(); i++){
			Object[] obj = (Object[]) keyList.get(i);
			map.put(obj[0].toString(), 0);
		}

		return map;
	}


	@Override
	public List<Map<String, Object>> groupingStatisticsForList(String dictCode) {

		String sql = "select t.attrKey,t.attrValue from um_category uc,um_category_attribute t where t.categoryId = uc.id and uc.categoryCode = '" + dictCode + "'";
		//List<Object> keyList = this.baseDaoImpl.queryBySql(sql);
		List<Object> keyList = new ArrayList<>();

		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map1 = new TreeMap<>();
		Map<String, Object> map2 = new TreeMap<>();
		// 遍历字典属性键值对(key,value)集合,并将key逐个存放到map1中,并设其默认值为0,将(key,value)逐个存放到map2中
		for(int i = 0; i < keyList.size(); i++){
			Object[] obj = (Object[]) keyList.get(i);
			map1.put(obj[0].toString(), 0);
			map2.put(obj[0].toString(), obj[1].toString());
		}
		list.add(map1);
		list.add(map2);

		return list;
	}

}
