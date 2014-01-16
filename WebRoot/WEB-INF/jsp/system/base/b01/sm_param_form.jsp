<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2 class="contentTitle">系统参数详细信息</h2>

<div class="pageContent">
	
	<form method="post" action="/smParam/saveModel" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="110">
			<dl>
				<dt>参数编码：</dt>
				<dd>
					<input type="hidden" name="smParam.pk_param" maxlength="40" value="${smParam.pk_param}"/>
					<input type="text" name="smParam.vcode" maxlength="40" class="required" value="${smParam.vcode}"/>
				</dd>
			</dl>
			<dl>
				<dt>参数值：</dt>
				<dd>
					<textarea name="smParam.param_value" class="required" cols="80" rows="4" >${smParam.param_value}</textarea>
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
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

