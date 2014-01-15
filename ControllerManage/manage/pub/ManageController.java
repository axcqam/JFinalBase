package manage.pub;

import jfinal.core.controller.CoreController;

/**
 * 后台管理公用controller
 * @author qiutian
 * @date 2013年12月17日
 */
public class ManageController extends CoreController {

	public void index(){
		renderJsp("/WEB-INF/jsp/index.html");
	}
}
