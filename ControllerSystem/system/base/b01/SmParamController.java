package system.base.b01;

import com.jfinal.ext.render.DwzRender;

import jfinal.core.controller.CoreController;
import system.model.SmParam;

/**
 * 系统参数设置
 * @author qiutian  2013年12月18日
 */
public class SmParamController extends CoreController {

	/**
	 * 默认方法查询数据
	 */
	public void index(){
		
		SmParam smParam = getModel(SmParam.class);
		setAttr("smParam", smParam);

		setAttr("page", smParam.queryPaginate(getPageNumber(), getPageSize())); //分页查询
		renderJsp("/WEB-INF/jsp/system/base/b01/sm_param_list.jsp");
	}

	/**
	 * 保存数据
	 */
	public void saveModel(){
		if(getModel(SmParam.class).save()){
			
			DwzRender dwz = new DwzRender();
			dwz.message("操作成功");
			dwz.callbackType("closeCurrent");
			dwz.navTabId("sm_param_list");
			render(dwz);
		}else{
			render(DwzRender.error());
		}
		
	}
	
	/**
	 * 转入新增页面
	 */
	public void addPage(){
		renderJsp("/WEB-INF/jsp/system/base/b01/sm_param_form.jsp");
	}
	
	/**
	 * 转入修改页面
	 */
	public void editPage(){
		setAttr("smParam", getModel(SmParam.class).queryById());
		renderJsp("/WEB-INF/jsp/system/base/b01/sm_param_form.jsp");
	}
	
	/**
	 * 删除数据
	 */
	public void delModel(){
		getModel(SmParam.class).delete(true);
		render(DwzRender.success());
	}
}
