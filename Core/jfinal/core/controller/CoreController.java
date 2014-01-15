package jfinal.core.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.jfinal.core.Controller;

/**
 * 核心控制类，开发请继承
 * 增加分页信息，J-UI返回信息，以及常用的参数控制
 * @author qiutian  2013年12月18日
 */
public class CoreController extends Controller {

	public int pageSize=20;    //每页显示多少行
	public int pageNumber=1;   //页号
	
	
//	/**
//	 * 删除数据
//	 */
//	public void delModel(){
//		
//		new Model<Model>() {
//			@Override
//			public boolean deleteById(Object id) {
//				// TODO Auto-generated method stub
//				return super.deleteById(id));
//			}
//		};
//	}
	
	/**
	 * 获取每页显示多少行
	 */
	public int getPageSize(){
		pageSize = getParaToInt("numPerPage")!=null?getParaToInt("numPerPage"):pageSize;
		//setAttr("numPerPage", numPerPage);
		return pageSize;
	}

	/**
	 * 获取页号
	 */
	public int getPageNumber(){
		pageNumber = getParaToInt("pageNum")!=null?getParaToInt("pageNum"):pageNumber;
		//setAttr("pageNumber", pageNumber);
		return pageNumber;
	}
	
	/**
	 * 调用日志工具
	 * @param cls 调用类的class路径
	 * @return 日志工具
	 */
	protected Logger logger(Class cls){
		return LogManager.getLogger(cls);
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
}
