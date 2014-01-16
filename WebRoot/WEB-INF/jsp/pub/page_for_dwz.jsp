<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	/*
	 使用分页时，在页面尾部加载词方法即可
	 <jsp:include page="/WEB-INF/jsp/pub/page_for_dwz.jsp?action=/b01" /> 
	 参数: action=/b01    为分页form表单的提交地址，对应下面的	${action} 
 
	*/
%>
	<!-- 分页数据提交form表单 -->
	<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="pageNum" value="${page.pageNumber}" />
		<input type="hidden" name="numPerPage" value="${page.pageSize}" />
	</form>
	
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
		
		<div class="pagination" targetType="navTab" totalCount="${page.totalRow}" numPerPage="${page.pageSize}" pageNumShown="${page.pageNumber+1}" currentPage="${page.pageNumber}"></div>
	</div>
