package jfinal.core.utils;

import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.activerecord.cache.ICache;

/**
 * 操作缓存工具类
 * @author qiutian  
 * @date 2013年12月28日
 */
public class CacheUtils {

	
	/**
	 * 获取缓存中的数据
	 * @param cacheName 缓存名称
	 * @param key 根据key获取value
	 * @return value
	 */
	public static Object getCache(String cacheName, String key){
		
		ICache cache = DbKit.getCache();
		return cache.get(cacheName, key);
	}
	
	/**
	 * 设置缓存中的数据
	 * @param cacheName 缓存名称
	 * @param key 
	 * @return value
	 */
	public static void setCache(String cacheName, String key, Object value ){
		DbKit.getCache().put(cacheName, key, value);
	}
}
