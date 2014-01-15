package system.pub;

import jfinal.core.controller.CoreController;

/**
 * 后台管理主页
 * @author qiutian
 * @date 2013年12月17日
 */
public class SystemController extends CoreController {

	public void index(){
		renderJsp("/WEB-INF/jsp/index.html");
	}
	
	public void test(){
		renderJsp("/WEB-INF/jsp/test.jsp");
	}
}
