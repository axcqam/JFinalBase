package system.model;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jfinal.core.model.CoreModel;
import jfinal.core.utils.CacheUtils;
import pub.itf.ICacheParam;
import pub.itf.IPubParam;

import com.jfinal.ext.plugin.tablebind.TableBind;

/**
* 数据库表对应的Model类，继承了CoreModel
* @author qiutian 
* @date 2013-12-19
*/
@TableBind(tableName="sm_param",pkName="pk_param") 
public class SmParam extends CoreModel<SmParam>{

	public static final SmParam dao = new SmParam();
	
	
	private String pk_param; //主键
	private String vcode; //编码
	private String vname; //名称
	private String param_value; //参数值
	private String pk_parent; //父主键
	private String create_by; //制单人
	private Date create_time; //制单时间
	private Date ts; //时间戳
	private int dr; //删除标记
	private String column_1; //预留字段1
	private String column_2; //预留字段2
	private String column_3; //预留字段3
	private int column_4; //预留字段4
	private Date column_5; //预留字段5


	public static final String PK_PARAM = "pk_param";
	public static final String VCODE = "vcode";
	public static final String VNAME = "vname";
	public static final String PARAM_VALUE = "param_value";
	public static final String PK_PARENT = "pk_parent";
	public static final String CREATE_BY = "create_by";
	public static final String CREATE_TIME = "create_time";
	public static final String TS = "ts";
	public static final String DR = "dr";
	public static final String COLUMN_1 = "column_1";
	public static final String COLUMN_2 = "column_2";
	public static final String COLUMN_3 = "column_3";
	public static final String COLUMN_4 = "column_4";
	public static final String COLUMN_5 = "column_5";

	//private static Map<String,String> mapSmParam = null;
	
	/**数据库中的表名*/
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "sm_param";
	}


	/**主键字段名称*/
	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return "pk_param";
	}
	
	@Override
	public String getLikeColumn() {
		// TODO Auto-generated method stub
		return VCODE+","+PARAM_VALUE;
	}
	
	
	@Override
	public boolean save() {
		boolean isSuccess = super.save();
		//SmParam.getSmParam(true); //刷新缓存
		return isSuccess;
	}
	
	/**
	 * 首次调用初始化系统参数数据，存入缓存中
	 * @author qiutian  2013年12月18日
	 * @return 获取系统参数数据
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> getSmParam(){
		
//		if(mapSmParam==null || refresh){ //如果为空或者主动刷新则重新加载数据
//			mapSmParam = new HashMap<String, String>();
//			
//			//查询
//			List<SmParam> listTemp = dao.find("select * from sm_param where dr=0");
//			for (SmParam smParam : listTemp) {
//				String vcode = smParam.getStr("vcode");
//				String param_value = smParam.getStr("param_value");
//				mapSmParam.put(vcode,param_value);
//			}			
//		}else{
//			
//		}
		
		//获取缓存数据
		Object cacheValue = CacheUtils.getCache(ICacheParam.paramCacheName, SmParam.class.getName());
		
		if(cacheValue==null){
			//查询
			
			Map<String,String> mapSmParam = new HashMap<String,String>();
			
			List<SmParam> listTemp = dao.find("select * from sm_param where dr=0");
			for (SmParam smParam : listTemp) {
				String vcode = smParam.getStr("vcode");
				String param_value = smParam.getStr("param_value");
				mapSmParam.put(vcode,param_value);
			}	
			CacheUtils.setCache(ICacheParam.paramCacheName, SmParam.class.getName(),mapSmParam);
			return mapSmParam;
		}else{
			return ( Map<String,String> ) cacheValue;
		}
	}
	
	/**
	 * 获取系统参数值
	 * @param vcode 系统参数编码
	 * @return
	 */
	public static String getParamValue(String vcode){
		return getSmParam().get(vcode);
	}
}
