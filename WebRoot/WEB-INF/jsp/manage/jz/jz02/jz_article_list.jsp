<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 分页数据提交form表单 -->
	<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="pageNum" value="${page.pageNumber}" />
		<input type="hidden" name="numPerPage" value="${page.pageSize}" />
	</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="/jzArticle" method="post" rel="pagerForm">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					文章名称：<input type="text" name="jzArticle.article_name" value="${jzArticle.article_name}" />
				</td>
				<td>
					<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add"    href="/jzArticle/addPage" target="navTab" ref="formadd"><span>添加</span></a></li>
			<li><a class="delete" href="/jzArticle/delModel?jzArticle.pk_article={pk_value}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit"   href="/jzArticle/editPage?jzArticle.pk_article={pk_value}" target="navTab" ref="formedit"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="5%">序号</th>
				<th width="60%">文章名称</th>
				<th width="35%">浏览次数</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="jzArticle" varStatus="number">
				<tr target="pk_model" rel="${jzArticle.pk_article}">
					<td>${number.index+1}</td>
					<td>${jzArticle.article_name}</td>
					<td>${jzArticle.times}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>
			<span>条，共${page.totalRow}条</span>
		</div>
		
		<jsp:include page="/WEB-INF/jsp/pub/page_for_dwz.jsp?action=/jzArticle" />
	</div>
</div>
