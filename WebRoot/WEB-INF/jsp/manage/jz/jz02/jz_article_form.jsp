<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2 class="contentTitle">系统参数详细信息</h2>

<div class="pageContent">
	
	<form method="post" action="/jzArticle/saveModel" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="110">
			<dl>
				<dt>文章名称：</dt>
				<dd>
					<input type="hidden" name="jzArticle.pk_article" maxlength="40" value="${jzArticle.pk_article}"/>
					<input type="text" name="jzArticle.article_name" maxlength="40" class="required" value="${jzArticle.article_name}"/>
				</dd>
			</dl>
			<dl>
				<dt>文章内容：</dt>
				<dd>
					<textarea name="jzArticle.article_content" class="editor"  cols="150" rows="30" >${jzArticle.article_content}</textarea>
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>


<script type="text/javascript">
function customvalidXxx(element){
	if ($(element).val() == "xxx") return false;
	return true;
}
</script>

