package com.graykey.xcore.common.utils.cache;


import com.graykey.xcore.category.module.CategoryAttribute;
import com.graykey.xcore.systemConfig.module.SystemConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * 缓存
 *
 * @author xuezb
 * @date 2020-01-11
 */
public class Cache {

	/**
	 * 所有数据字典带默认值
	 */
	public static Map<String,Set<CategoryAttribute>> getDictByCode = new HashMap<>();
	
	/**
	 * 所有数据字典Map方便直接找到字典数据 进行翻译
	 */
	public static Map<String,Map<String,String>> getDictByCodeMap = new HashMap<>();

	/**
	 * 行政区划
	 */
	public static Map<String,String> getAreaByCode = new HashMap<>();
	
	/**
	 * 字典目录对翻译
	 */
	public static Map<String,String> getDict = new HashMap<>();
	
	/**
	 * 子系统列表
	 */
	public static Map<String,String> getSubsystem = new HashMap<>();

	/**
	 * 系统配置
	 **/
	public static SystemConfig getSystemConfig = new SystemConfig();

	/**
	 * 工作日配置
	 */
	public static Map<Integer,String> getWorkDayConfig = new HashMap<>();
	
	/**
	 * APIConfig 接口配置
	 * 1-IP,2-SMS,3-MAP,4-Weather,5-Video
	 */
	public static Map<Integer,Object> getApiConfig = new HashMap<Integer, Object>();
	
}
