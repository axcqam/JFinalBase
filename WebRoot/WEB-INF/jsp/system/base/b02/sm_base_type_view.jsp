<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2 class="contentTitle">基础类型详细信息</h2>

<div class="pageContent">
	
	<form method="post" action="/smBaseType/saveModel" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="110">
			<dl>
				<dt>基础类型编码：</dt>
				<dd>
					<input type="hidden"  name="smBaseType.pk_base_type" maxlength="40" class="required" value="${smBaseType.pk_base_type}"/>
					<input type="text"  name="smBaseType.vcode" maxlength="40" class="required" value="${smBaseType.vcode}"/>
				</dd>
			</dl>
			<dl>
				<dt>基础类型名称：</dt>
				<dd>
					<input type="text" name="smBaseType.vname" maxlength="40" class="required" value="${smBaseType.vname}"/>
				</dd>
			</dl>
			<dl>
				<dt>备注：</dt>
				<dd>
					<textarea name="smBaseType.comments" class="required" cols="80" rows="4" >${smBaseType.comments}</textarea>
				</dd>
			</dl>
			<dl>
				<dt>父主键：</dt>
				<dd>
					<input type="text" disabled="disabled" name="smBaseType.pk_parent" maxlength="40" class="required" value="${smBaseType.pk_parent}"/>
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

