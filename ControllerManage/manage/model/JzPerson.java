package manage.model;


import jfinal.core.model.CoreModel;
import java.util.Date;
import com.jfinal.ext.plugin.tablebind.TableBind;

/**
* 数据库表对应的Model类，继承了CoreModel
* @author qiutian 
* @date 2013-12-19
*/
@TableBind(tableName="jz_person",pkName="pk_person") 
public class JzPerson extends CoreModel<JzPerson>{

	public static final JzPerson dao = new JzPerson();
	private String pk_person; //主键
	private String vcode; //编码
	private String vname; //名称
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
	private int sex; //性别
	private Date birthday; //出生日期
	private int age; //年龄
	private String zodiac; //属相
	private String idcard; //身份证号
	private String hometown; //籍贯
	private String degree; //学历
	private String marry_type; //婚姻状况
	private String ethnic; //民族
	private String religion; //宗教
	private String service_item; //服务项目
	private int ishome; //是否住家
	private String province; //服务区域(省)
	private String city; //服务区域(市)
	private int restday; //休息天数/月
	private String work_status; //工作状态
	private int word_time; //工作时长
	private double money; //期望工资
	private int phone; //手机号
	private String certificate; //证书
	private String comments; //自我介绍
	private String pohto_head; //头像照片
	private String photo_person; //个人照片


	public static final String PK_PERSON = "pk_person";
	public static final String VCODE = "vcode";
	public static final String VNAME = "vname";
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
	public static final String SEX = "sex";
	public static final String BIRTHDAY = "birthday";
	public static final String AGE = "age";
	public static final String ZODIAC = "zodiac";
	public static final String IDCARD = "idcard";
	public static final String HOMETOWN = "hometown";
	public static final String DEGREE = "degree";
	public static final String MARRY_TYPE = "marry_type";
	public static final String ETHNIC = "ethnic";
	public static final String RELIGION = "religion";
	public static final String SERVICE_ITEM = "service_item";
	public static final String ISHOME = "ishome";
	public static final String PROVINCE = "province";
	public static final String CITY = "city";
	public static final String RESTDAY = "restday";
	public static final String WORK_STATUS = "work_status";
	public static final String WORD_TIME = "word_time";
	public static final String MONEY = "money";
	public static final String PHONE = "phone";
	public static final String CERTIFICATE = "certificate";
	public static final String COMMENTS = "comments";
	public static final String POHTO_HEAD = "pohto_head";
	public static final String PHOTO_PERSON = "photo_person";


	/**数据库中的表名*/
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "jz_person";
	}


	/**主键字段名称*/
	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return "pk_person";
	}
}
