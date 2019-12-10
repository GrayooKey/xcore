package com.graykey.xcore.common.chartReport.service;

import java.util.List;
import java.util.Map;

/**
 * ECharts图表数据统计 和 列表页面头部分组统计 的工具类接口
 *
 * @author xuezb
 * @date 2019-07-16
 */
public interface IChartReportService {

	/**
	 * 根据字典code查询字典属性键值对(key,value)集合，用于ECharts图表数据统计
	 *
	 * @param dictCode
	 * @return	map<字典属性key,默认值0>
	 * @author xuezb
	 * @date 2019-07-16
	 */
	Map<Object, Object> groupingStatisticsForECharts(String dictCode);

	/**
	 * 根据字典code查询字典属性键值对(key,value)集合，用于列表页面头部分组统计
	 *
	 * @param dictCode
	 * @return	List<Map<Object,Object>>
	 * @author xuezb
	 * @date 2019-07-16
	 */
	List<Map<String, Object>> groupingStatisticsForList(String dictCode);

}
