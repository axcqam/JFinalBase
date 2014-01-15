package manage.jz.jz01;

import jfinal.core.controller.CoreController;

/**
 * 服务人员管理
 * @author qiutan
 */
public class JzPersonController extends CoreController {

	/**
	 * 服务人员查询
	 */
	public void index(){
		
		//查询数据
		//List<Users> s = Users.dao.find("select * from users");
		renderJsp("/WEB-INF/jsp/manage/jiazheng/person/person_list.jsp");
	}
	
	/**
	 * 删除服务人员
	 * @author qiutian
	 */
	public void delPerson(){
		String id = getPara("id");
		renderJsp("/WEB-INF/jsp/manage/person/person_list.jsp");
	}
}
