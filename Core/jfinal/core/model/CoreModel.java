package jfinal.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jfinal.kit.StringKit;
import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.cache.ICache;

/**
 * model父类，公共方法写这里
 * 缓存查询，cacheName必须为 "query"+表名称
 * @author qiutian  2013年12月17日
 * @param <C>
 */
public abstract class CoreModel<C extends CoreModel> extends Model<C>{
	
	public static final String CREATE_BY = "create_by";       //创建人
	public static final String CREATE_TIME = "create_time"; 	//创建时间
	public static final String DR = "dr"; 					//删除标记
	public static final String TS = "ts"; 					//时间戳
	public static final String PK_PARENT = "pk_parent"; 		//父主键

	private String likeColumn;   //查询时，如需like查询，则将字段名称复制到此变量。
	
	/**
	 * 返回表的主键
	 * @return
	 */
	abstract public String getPrimaryKey();
	
	/**
	 * 数据库中的表名
	 * @return
	 */
	abstract public String getTableName();

	/**
	 * 需要用like进行查询的字段
	 */
	public String getLikeColumn(){
		return likeColumn;
	}
	
	/**
	 * 设置like查询的字段
	 * @param likeColumn
	 */
	public void setLikeColumn(String likeColumn){
		this.likeColumn = likeColumn;
	}
	
	/**
	 * 新增保存时，自动给主键赋值
	 */
	@Override
	public boolean save() {
		
		if(get(getPrimaryKey())!=null){  //主键不为空，走修改方法
			set(TS,new Date());   //刷新时间戳
			return super.update();
		}else{ //新增
			set(getPrimaryKey(), java.util.UUID.randomUUID().toString().replace("-", "")); //设置UUID为主键
			set(CREATE_BY, "admin");      //增加制单人
			set(CREATE_TIME,new Date()); //增加制单时间
			set(DR,0);
			set(TS,new Date());   //刷新时间戳
			return super.save();
		}
		
	}
	
	/**
	 * 如果为null，取""
	 * @author qiutian  @date 2014年1月10日
	 */
	@Override
	public String getStr(String attr) {
		// TODO Auto-generated method stub
		return super.get(attr, "");
	}
	
	/**
	 * 如果为null，取0
	 * @author qiutian  @date 2014年1月10日
	 */
	@Override
	public Integer getInt(String attr) {
		// TODO Auto-generated method stub
		return super.get(attr, 0);
	}
	
	/**
	 * 如果为null，取0.00
	 * @author qiutian  @date 2014年1月10日
	 */
	@Override
	public Double getDouble(String attr) {
		// TODO Auto-generated method stub
		return super.get(attr, 0.00);
	}
	
	/**
	 * 用id删除数据(将DR改为1)
	 */
	@Override
	public boolean deleteById(Object id) {
		set("dr", 1);
		set(getPrimaryKey(), id);
		return update();
	}
	
	/**
	 * 用id删除数据(将DR改为1)
	 * @param isPhysicalDelete 是否物理删除
	 */
	public boolean deleteById(Object id,boolean isPhysicalDelete) { 
		
		if(isPhysicalDelete){
			return super.deleteById(id); //物理删除数据
		}else{
			set("dr", 1);
			set(getPrimaryKey(), id);
			return update();
		}
	}
	
	/**
	 * Model用自身主键删除数据(将DR改为1)
	 */
	@Override
	public boolean delete() {
		set("dr", 1);
		return update();
	}
	
	/**
	 * Model用自身主键删除数据(将DR改为1)
	 * @param isPhysicalDelete 是否物理删除
	 */
	public boolean delete(boolean isPhysicalDelete) {
		if(isPhysicalDelete){
			return super.delete(); //物理删除数据
		}else{
			set("dr", 1);
			return update();
		}
	}
	
	
	/**
	 * 查询全部未删除的数据
	 * @return
	 */
	public List<C> getList(){
		return find(" select * from "+getTableName()+" where dr=0 ");
	}
	
	
	/**
	 * 单个条件查询
	 * @param key 数据库字段
	 * @param value 查询值
	 * @return Model
	 */
	public C queryFirst(String key, Object value) {
		List<C> mList =  query(key, value, "");
		return mList!=null && mList.size()>0 ? mList.get(0):null;
	}

	/**
	 * 单个条件查询
	 * @param key 数据库字段
	 * @param value 查询值
	 * @return List<Model>
	 */
	public List<C> query(String key, Object value) {
		return query(key, value, "");
	}

	/**
	 * 单个条件查询
	 * @param key 数据库字段
	 * @param value 查询值
	 * @param orderBy 排序字段
	 * @return List<Model>
	 */
	public List<C> query(String key, Object value, String orderBy) {
		checkTableName();
		String sql = "";
		if(getLikeColumn().contains(key)){
			sql = "select * from " + getTableName() + " where "+ key +" like '%"+value+"%' " + orderBy;
			
		}else{
			sql = "select * from " + getTableName() + " where "+ key +"=? " + orderBy;
		}
		
		return find(sql, value);
	}

	/**
	 * map条件查询单个数据
	 * @param maps 查询条件
	 * @return Model
	 */
	public C queryFirst(Map<String, Object> maps) {
		List<C> mList =  query(maps, "");
		return mList!=null && mList.size()>0 ? mList.get(0):null;
	}

	/**
	 * map条件查询
	 * @param maps 查询条件
	 * @return List<Model>
	 */
	public List<C> query(Map<String, Object> maps) {
		return query(maps, "");
	}

	/**
	 * map条件查询
	 * @param maps 查询条件
	 * @param orderBy 排序条件
	 * @return List<Model>
	 */
	public List<C> query(Map<String, Object> maps, String orderBy) {
		StringBuilder sb = new StringBuilder();
		List<Object> values = new ArrayList<Object>();
		createSqlAndParam(maps, values, sb, orderBy, false);
		return find(sb.toString(), values.toArray());
	}
	
	
	/**
	 * Model自身包含条件查询单个数据
	 * @return Model
	 */
	public C queryFirst() {
		Map<String, Object> maps = getAttrs();
		List<C> mList =  query(maps, "");
		return mList!=null && mList.size()>0 ? mList.get(0):null;
	}

	/**
	 * Model自身包含条件条件查询
	 * @return List<Model>
	 */
	public List<C> query() {
		Map<String, Object> maps = getAttrs();
		return query(maps, "");
	}
	
	/**
	 * Model自身包含主键查询
	 * @return List<Model>
	 */
	public C queryById() {
		return findById(get(getPrimaryKey()));
	}

	/**
	 * Model自身包含条件条件查询
	 * @param orderBy 排序条件
	 * @return List<Model>
	 */
	public List<C> query(String orderBy) {
		Map<String, Object> maps = getAttrs();
		StringBuilder sb = new StringBuilder();
		List<Object> values = new ArrayList<Object>();
		createSqlAndParam(maps, values, sb, orderBy, false);
		return find(sb.toString(), values.toArray());
	}

	/**
	 * map条件查询缓存
	 * @param cacheName the cache name
	 * @param key the key used to get date from cache
	 * @param maps 查询条件
	 * @return List<Model>
	 */
	public List<C> queryByCache(String cacheName, Object key, Map<String, Object> maps){
		return this.queryByCache(cacheName, key, maps,"");
	}

	/**
	 * map条件查询缓存
	 * @param cacheName the cache name
	 * @param key the key used to get date from cache
	 * @param maps 查询条件
	 * @param orderBy 排序条件
	 * @return List<Model>
	 */
	public List<C> queryByCache(String cacheName, Object key, Map<String, Object> maps, String orderBy) {
		checkTableName();
		ICache cache = DbKit.getCache();
		List<C> result = cache.get(cacheName, key);
		if (result == null) {
			result = query(maps);
			cache.put(cacheName, key, result);
		}
		return result;
	}

	/**
	 * Model自身条件查询缓存
	 * @param cacheName the cache name
	 * @param key the key used to get date from cache
	 * @param maps 查询条件
	 * @return List<Model>
	 */
	public List<C> queryByCache(String cacheName, Object key){
		return this.queryByCache(cacheName, key,"");
	}

	/**
	 * Model自身条件查询缓存
	 * @param cacheName the cache name
	 * @param key the key used to get date from cache
	 * @param orderBy 排序条件
	 * @return List<Model>
	 */
	public List<C> queryByCache(String cacheName, Object key, String orderBy) {
		checkTableName();
		ICache cache = DbKit.getCache();
		List<C> result = cache.get(cacheName, key);
		if (result == null) {
			result = query(getAttrs());
			cache.put(cacheName, key, result);
		}
		return result;
	}
	/**
	 * map条件查询分页
	 * @param cacheName the cache name
	 * @param key the key used to get date from cache
	 * @param maps 查询条件
	 * @param orderBy 排序条件
	 * @return List<Model>
	 */
	public Page<C> queryPaginate(int pageNumber, int pageSize, Map<String, Object> maps){
		return this.queryPaginate(pageNumber, pageSize, maps, "");
	}

	/**
	 * map条件查询分页
	 * @param pageNumber 页号
	 * @param pageSize 每页显示的数量
	 * @param maps 查询条件
	 * @return List<Model>
	 */
	public Page<C> queryPaginate(int pageNumber, int pageSize, Map<String, Object> maps, String orderBy) {
		List<Object> values = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder();
		
		createSqlAndParam(maps, values, sb,orderBy,true);
		return paginate(pageNumber, pageSize, "select *", sb.toString(),values.toArray());
	}

	/**
	 * 根据条件生成sql和param
	 * @param maps 查询条件
	 * @param values param
	 * @param sb sql
	 */
	private void createSqlAndParam(Map<String, Object> maps,
			List<Object> values, StringBuilder sb,String orderBy, boolean isPage) {
		
		checkTableName();
		
		if(isPage){
			sb.append(" from ").append(getTableName()).append(" where dr=0");
		}else{
			sb.append(" select * from ").append(getTableName()).append(" where dr=0");
		}
		for(Entry<String,Object> entry:maps.entrySet()){
			if(entry.getValue() != null){
				String entryKey = entry.getKey();
				if(getLikeColumn().contains(entryKey)){
					sb.append(" and ").append(entry.getKey()).append(" like '%"+entry.getValue()+"%' ");
				}else{
					sb.append(" and ").append(entry.getKey()).append(" =? ");
					values.add(entry.getValue());
				}
				
			}
		}
		sb.append(" ").append(orderBy);
	}
	
	/**
	 * map条件查询分页
	 * @param cacheName the cache name
	 * @param key the key used to get date from cache
	 * @param pageNumber 页号
	 * @param pageSize 每页显示的数量
	 * @param maps 查询条件
	 * @return List<Model>
	 */
	public Page<C> queryPaginateByCache(String cacheName, Object key, int pageNumber, int pageSize, Map<String, Object> maps) {
		return this.queryPaginateByCache(cacheName, key, pageNumber, pageSize, maps, "");
	}

	/**
	 * map条件查询分页
	 * @param cacheName the cache name
	 * @param key the key used to get date from cache
	 * @param pageNumber 页号
	 * @param pageSize 每页显示的数量
	 * @param maps 查询条件
	 * @param orderBy 排序条件
	 * @return List<Model>
	 */
	public Page<C> queryPaginateByCache(String cacheName, Object key, int pageNumber, int pageSize, Map<String, Object> maps, String orderBy) {
		checkTableName();
		ICache cache = DbKit.getCache();
		Page<C> result = cache.get(cacheName, key);
		if (result == null) {
			result = queryPaginate(pageNumber, pageSize, maps, orderBy);
			cache.put(cacheName, key, result);
		}
		return result;
	}

	
	/**
	 * Model自身条件查询分页
	 * @param cacheName the cache name
	 * @param key the key used to get date from cache
	 * @param maps 查询条件
	 * @param orderBy 排序条件
	 * @return List<Model>
	 */
	public Page<C> queryPaginate(int pageNumber, int pageSize){
		return this.queryPaginate(pageNumber, pageSize, "");
	}

	/**
	 * Model自身条件查询分页
	 * @param pageNumber 页号
	 * @param pageSize 每页显示的数量
	 * @return List<Model>
	 */
	public Page<C> queryPaginate(int pageNumber, int pageSize, String orderBy) {
		checkTableName();
		StringBuilder sb = new StringBuilder();
		List<Object> values = new ArrayList<Object>();
		createSqlAndParam(getAttrs(), values, sb, orderBy, true);
		
		return paginate(pageNumber, pageSize, "select *", sb.toString(),values.toArray());
	}

	/**
	 * Model自身条件缓存查询分页
	 * @param cacheName the cache name
	 * @param key the key used to get date from cache
	 * @param pageNumber 页号
	 * @param pageSize 每页显示的数量
	 * @return List<Model>
	 */
	public Page<C> queryPaginateByCache(String cacheName, Object key, int pageNumber, int pageSize) {
		return this.queryPaginateByCache(cacheName, key, pageNumber, pageSize, "");
	}

	/**
	 * Model自身条件缓存查询分页
	 * @param cacheName the cache name
	 * @param key the key used to get date from cache
	 * @param pageNumber 页号
	 * @param pageSize 每页显示的数量
	 * @param orderBy 排序条件
	 * @return List<Model>
	 */
	public Page<C> queryPaginateByCache(String cacheName, Object key, int pageNumber, int pageSize, String orderBy) {
		checkTableName();
		ICache cache = DbKit.getCache();
		Page<C> result = cache.get(cacheName, key);
		if (result == null) {
			result = queryPaginate(pageNumber, pageSize, getAttrs(), orderBy);
			cache.put(cacheName, key, result);
		}
		return result;
	}
	
	
	/**
	 * 校验表名不能为空
	 */
	private void checkTableName(){
		if (StringKit.isBlank(getTableName()))
			throw new IllegalArgumentException("tableName can not be blank,please setgetTableName()(tableName)");
	}
	
}
