package system.model;


import jfinal.core.model.CoreModel;
import java.util.Date;
import com.jfinal.ext.plugin.tablebind.TableBind;

/**
* 数据库表对应的Model类，继承了CoreModel
* @author qiutian 
* @date 2013-12-19
*/
@TableBind(tableName="sm_user",pkName="pk_user") 
public class SmUser extends CoreModel<SmUser>{

	public static final SmUser dao = new SmUser();
	private String pk_user; //主键
	private String username; //用户名
	private String password; //密码
	private String pname; //姓名
	private String last_ip; //最后访问IP
	private String user_type; //用户类型
	private String status; //用户状态
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


	public static final String PK_USER = "pk_user";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String PNAME = "pname";
	public static final String LAST_IP = "last_ip";
	public static final String USER_TYPE = "user_type";
	public static final String STATUS = "status";
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


	/**数据库中的表名*/
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "sm_user";
	}


	/**主键字段名称*/
	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return "pk_user";
	}
}
