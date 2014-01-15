package system.base.b02;

import jfinal.core.controller.CoreController;
import pub.utils.TreeUtils;
import system.model.SmBaseType;
import system.model.SmBaseType;

import com.jfinal.ext.render.DwzRender;

/**
 * 数据类型参数设置
 * @author qiutian  2013年12月18日
 */
public class SmBaseTypeController extends CoreController {

	/**
	 * 默认方法查询数据
	 */
	public void index(){
		
		//生成全部数据的树形菜单
		try {
			setAttr("tree",TreeUtils.createTreeHtml(new SmBaseType().getList(),"/smBaseType/viewPage"));
			renderJsp("/WEB-INF/jsp/system/base/b02/sm_base_type_list.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			render(DwzRender.error(e.getMessage()));
		}
		
	}

	/**
	 * 保存数据
	 */
	public void saveModel(){
		if(getModel(SmBaseType.class).save()){
			
			DwzRender dwz = new DwzRender();
			dwz.message("操作成功");
			dwz.callbackType("closeCurrent");
			dwz.navTabId("sm_basetype_list");
			render(dwz);
		}else{
			render(DwzRender.error());
		}
	}
	
	/**
	 * 转入新增页面
	 */
	public void addPage(){
		renderJsp("/WEB-INF/jsp/system/base/b02/sm_base_type_form.jsp");
	}
	
	/**
	 * 转入修改页面
	 */
	public void editPage(){
		setAttr("smBaseType", getModel(SmBaseType.class).queryById());
		renderJsp("/WEB-INF/jsp/system/base/b02/sm_base_type_form.jsp");
	}
	
	/**
	 * 转入查看页面
	 */
	public void viewPage(){
		setAttr("smBaseType", new SmBaseType().set(SmBaseType.PK_BASE_TYPE, getPara(SmBaseType.PK_BASE_TYPE)).queryById());
		renderJsp("/WEB-INF/jsp/system/base/b02/sm_base_type_form.jsp");
	}
	
	/**
	 * 删除数据
	 */
	public void delModel(){
		getModel(SmBaseType.class).delete(true);
		render(DwzRender.success());
	}
}
