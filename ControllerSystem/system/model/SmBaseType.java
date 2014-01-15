package system.model;


import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jfinal.core.model.CoreModel;
import jfinal.core.utils.DataUtils;

import com.google.common.collect.Multimap;
import com.jfinal.ext.plugin.tablebind.TableBind;

/**
* 数据库表对应的Model类，继承了CoreModel
* @author qiutian 
* @date 2013-12-19
*/
@TableBind(tableName="sm_base_type",pkName="pk_base_type") 
public class SmBaseType extends CoreModel<SmBaseType>{

	public static final SmBaseType dao = new SmBaseType();
	private String pk_base_type; //主键
	private String vcode; //类型编码
	private String vname; //名称
	private String comments; //备注
	private String pk_parent; //父主键
	private String create_by; //制单人
	private Date create_time; //制单时间
	private Date ts; //时间戳
	private int dr; //删除标记
	private int isleaf;//是否末级
	private String column_1; //预留字段1
	private String column_2; //预留字段2
	private String column_3; //预留字段3
	private int column_4; //预留字段4
	private Date column_5; //预留字段5


	public static final String PK_BASE_TYPE = "pk_base_type";
	public static final String VCODE = "vcode";
	public static final String VNAME = "vname";
	public static final String COMMENTS = "comments";
	public static final String PK_PARENT = "pk_parent";
	public static final String CREATE_BY = "create_by";
	public static final String CREATE_TIME = "create_time";
	public static final String TS = "ts";
	public static final String DR = "dr";
	public static final String ISLEAF = "isleaf";
	public static final String COLUMN_1 = "column_1";
	public static final String COLUMN_2 = "column_2";
	public static final String COLUMN_3 = "column_3";
	public static final String COLUMN_4 = "column_4";
	public static final String COLUMN_5 = "column_5";


	/**数据库中的表名*/
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "sm_base_type";
	}


	/**主键字段名称*/
	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return "pk_base_type";
	}
	
	
	public Map getSmBaseType(){
		
		Map<String,SmBaseType> mapTree = new HashMap<String,SmBaseType>();
		List<SmBaseType> listModel = getList();   //查询全部的数据
		return (Map) DataUtils.getMapParentToChilds(listModel);  	 
	}
	
}
