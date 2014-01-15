package manage.jz.jz02;

import jfinal.core.controller.CoreController;
import manage.model.JzArticle;
import system.model.SmParam;

import com.jfinal.ext.render.DwzRender;

/**
 * 服务人员管理
 * @author qiutan
 */
public class JzArticleController extends CoreController {

	/**
	 * 默认方法查询数据
	 */
	public void index(){
		
		JzArticle jzArticle = getModel(JzArticle.class);
		setAttr("jzArticle", jzArticle);
		setAttr("list", jzArticle.query());
		//setAttr("page", JzArticle.queryPaginate(getPageNumber(), getPageSize()));
		renderJsp("/WEB-INF/jsp/manage/jz/jz02/jz_article_list.jsp");
	}

	/**
	 * 保存数据
	 */
	public void saveModel(){
		if(getModel(JzArticle.class).save()){
			
			DwzRender dwz = new DwzRender();
			dwz.message("操作成功");
			dwz.callbackType("closeCurrent");
			dwz.navTabId("jz_article_list");
			render(dwz);
		}else{
			render(DwzRender.error());
		}
	}
	
	/**
	 * 转入新增页面
	 */
	public void addPage(){
		renderJsp("/WEB-INF/jsp/manage/jz/jz02/jz_article_form.jsp");
	}
	
	/**
	 * 转入修改页面
	 */
	public void editPage(){
		setAttr("JzArticle", getModel(SmParam.class).queryById());
		renderJsp("/WEB-INF/jsp/manage/jz/jz02/jz_article_form.jsp");
	}
	
	/**
	 * 删除数据
	 */
	public void delModel(){
		getModel(JzArticle.class).delete(true);
		render(DwzRender.success());
	}
}
