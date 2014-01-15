package jfinal.core.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jfinal.core.model.CoreModel;

import com.google.common.base.Function;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

/**
 * 数据转换工具
 * @author qiutian
 * @dae 2013-12-12
 */
public class DataUtils {

	
	
	
	/**
	 * 整理list中的数据，返回格式为 <父主键，下级model集合>
	 * @author qiutian  
	 * @date 2014年1月8日
	 * @param listModel 需要整理数据
	 * @return <父主键，下级model集合> 
	 */
	public static Map<String, Collection<CoreModel>> getMapParentToChilds(List listModel){
		
		//HashMultimap: key 放在 HashMap，而 value 放在 HashSet，即一个 key 对应的 value 不可重复
//		HashMultimap<String, Object> multMap = HashMultimap.create(); 
		
//		if(listModel.size()>0){
//			for (Object object : listModel) {
//				CoreModel model  = (CoreModel) object;
//				String pk_parent =  toString(model.get(CoreModel.PK_PARENT));
//				multMap.put(pk_parent, model);
//			}
//		}
		Multimap<String, CoreModel> multMap = 
				Multimaps.index(listModel, new Function<CoreModel, String>() {
					public String apply(final CoreModel model) {
						return (String) model.get(CoreModel.PK_PARENT);
					}
				});
		return multMap.asMap();
	}
	
	/**
	 * 将已有model转换为  <key,model>的形式
	 * @author qiutian  @date 2014年1月8日
	 * @param listModel 实体数据集合
	 * @param keyName  实体中的字段名，这个对应的值将作为map的key
	 * @return
	 */
	public static Map<String, CoreModel> modelKeyToValue(List listModel, String keyName){
		
		Map<String,CoreModel> mapTemp = new HashMap<String,CoreModel>();
		
		if(listModel.size()>0){
			for (Object object : listModel) {
				CoreModel model  = (CoreModel) object;
				
				if( model.get(keyName)==null){
					System.out.println("转换model失败，keyName错误!"); //记得修改下，写到之日志里才正确
					return mapTemp;
				}else{
					String key =  (String) model.get(keyName);
					mapTemp.put(key, model);
				}
			}
		}
		return mapTemp;
	}
	
	/**
	 * 将Object装换为Long
	 * @param longData
	 * @return
	 */
	public static Long toLong(Object value){
		
		if(value==null){
			return 0L;
		}else{
			Long longNum = new Long(value.toString());
			return longNum;
		}
	}
	
	/**
	 * 将Object装换为String
	 * @param longData
	 * @return
	 */
	public static String toString(Object value){
		
		if(value==null){
			return "";
		}else{
			return value.toString();
		}
	}
	
	/**
	 * 将Object装换为String
	 * @param longData
	 * @return
	 */
	public static Integer toInteger(Object value){
		
		if(value==null){
			return 0;
		}else{
			return new Integer(value.toString());
		}
	}

	/**
	 * 将Object装换为String
	 * @param longData
	 * @return
	 */
	public static Double toDouble(Object value){
		
		if(value==null){
			return new Double(0);
		}else{
			return new Double(value.toString());
		}
	}
}
