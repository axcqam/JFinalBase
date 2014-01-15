package manage.model;


import jfinal.core.model.CoreModel;

import java.util.Date;

import com.jfinal.ext.plugin.tablebind.TableBind;

/**
* 数据库表对应的Model类，继承了CoreModel
* @author qiutian 
* @date 2013-12-19
*/
@TableBind(tableName="jz_article",pkName="pk_article") 
public class JzArticle extends CoreModel<JzArticle>{

	public static final JzArticle dao = new JzArticle();
	private String pk_article; //主键
	private String article_name; //文章标题
	private String article_content; //文章内容
	private String comments; //备注
	private int times;     //浏览次数
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


	public static final String PK_ARTICLE = "pk_article";
	public static final String ARTICLE_NAME = "article_name";
	public static final String ARTICLE_CONTENT = "article_content";
	public static final String COMMENTS = "comments";
	public static final String TIMES = "times";
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
		return "jz_article";
	}


	/**主键字段名称*/
	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return "pk_article";
	}
	
	@Override
	public String getLikeColumn() {
		// TODO Auto-generated method stub
		return ARTICLE_NAME;
	}
	
}
