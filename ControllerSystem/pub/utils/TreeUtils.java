package pub.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import jfinal.core.model.CoreModel;
import jfinal.core.utils.DataUtils;
import pub.itf.IPubParam;


/**
 * 用于生成树的各种类型数据
 * @author qiutian  @date 2014年1月13日
 *
 */
public class TreeUtils {

	/**
	 * 生成树形菜单的html，默认起始主键IPubParam.pk_parent_dafultValue
	 * @param mapData  Map<String, Collection<CoreModel>> <父主键，下级model集合>
	 * @return 生成树形菜单的html
	 * @throws Exception 
	 */
	public static String createTreeHtml(List listModel,String viewUrl) throws Exception{
		return createTreeHtml(listModel, IPubParam.pk_parent_dafultValue, viewUrl);
	}
	
	
	/**
	 * 生成树形菜单的html
	 * @param mapData  Map<String, Collection<CoreModel>> <父主键，下级model集合>
	 * @param rootPK 起始父主键，生成的为它和它的下级
	 * @return 生成树形菜单的html
	 * @throws Exception 
	 */
	public static String createTreeHtml(List listModel, String rootPK, String viewUrl) throws Exception{
		
		Map<String, Collection<CoreModel>> mapData = DataUtils.getMapParentToChilds(listModel);
		
		StringBuffer strTree = new StringBuffer();
		strTree.append("<ul class=\"tree treeFolder\"> \n");
		if(mapData.containsKey(rootPK)){
			createTreeDetails(mapData, rootPK, strTree, true, viewUrl);
		}
		strTree.append("</ul> \n");
		
		if(strTree.length()>0){
			return strTree.toString();	
		}else{
			return "";
		}
	}
	
	/**
	 * 生成树形菜单的html，递归方式
	 * @param mapData Map<String, Collection<CoreModel>> <父主键，下级model集合>
	 * @param pk_parent 父主键
	 * @param strTree 保存生成的html
	 * @param isFirstNode 是否第一个节点，以免第一级增加ul
	 * @throws Exception 
	 */
	private static void createTreeDetails(Map mapData,String pk_parent, StringBuffer strTree, boolean isFirstNode, String viewUrl) throws Exception{
		
		try {
			//获取子数据
			List<CoreModel> listRoot = (List<CoreModel>) mapData.get(pk_parent);
			
			if(!isFirstNode){
				strTree.append("<ul> \n");
			}
			//循环子数据
			for (int i=0;i<listRoot.size();i++) {
				
				CoreModel cmodel = listRoot.get(i);
				
				String pk_model = cmodel.getStr(cmodel.getPrimaryKey()); //获取主键
				int isleaf = cmodel.getInt("isleaf"); 					//是否末级
				String vcode = cmodel.getStr("vcode");	//编码
				String vname = cmodel.getStr("vname");  //名称
				
				String nodeName = vcode+" "+vname;   //节点显示名称
				
				if(isleaf==0){   //非末级
					strTree.append("<li><a href=\"javascript\">"+nodeName+"</a>\n");
					
					if(mapData.containsKey(pk_model)){
						createTreeDetails(mapData, pk_model, strTree, false, viewUrl);  //加载子数据 （递归）
					}
				}else{  //末级
					strTree.append("<li><a href=\""+viewUrl+"?"+cmodel.getPrimaryKey()+"="+pk_model+"\" target=\"ajax\" rel=\"formBox\">"+nodeName+"</a></li> \n");
				}
			}
			
			if(!isFirstNode){
				strTree.append("</ul> \n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("生成树形菜单错误");
		}
	}
	
}
