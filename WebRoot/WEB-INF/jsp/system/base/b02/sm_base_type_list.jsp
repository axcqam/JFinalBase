<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="jfinal.core.model.CoreModel"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add"    href="/smParam/addPage" target="dialog" ref="formadd"><span>添加</span></a></li>
			<li><a class="delete" href="/smParam/delModel?smParam.pk_param={pk_value}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit"   href="/smParam/editPage?smParam.pk_param={pk_value}" target="dialog" ref="formedit"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
</div>
<style type="text/css">
	ul.rightTools {float:right; display:block;}
	ul.rightTools li{float:left; display:block; margin-left:5px}
</style>

<div class="pageContent" style="padding:5px">
	<div class="panel" defH="40">
		<h1>病人基本信息</h1>
		<div>
			病人编号：<input type="text" name="patientNo" />
			<ul class="rightTools">
				<li><a class="button" target="dialog" href="demo/pagination/dialog1.html" mask="true"><span>创建病例</span></a></li>
				<li><div class="buttonDisabled"><div class="buttonContent"><button>病人治疗流程</button></div></div></li>
				<li><div class="buttonDisabled"><div class="buttonContent"><button>按病人编号检索病例</button></div></div></li>
				<li><div class="buttonDisabled"><div class="buttonContent"><button>从病人列表选取病例</button></div></div></li>
			</ul>
		</div>
	</div>
	<div class="divider"></div>
	<div class="tabs">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>实验室检测</span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent">
			<div>
	
				<div layoutH="146" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
				    ${tree}
				</div>
				
				<div id="jbsxBox" class="unitBox" style="margin-left:246px;">
					<!--#include virtual="list1.html" -->
				</div>
	
			</div>
			
			<div>病人处方</div>
			
			<div>病人服药情况</div>
			
			<div>基线调查</div>
			
			<div>随访</div>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
	
</div>


	

