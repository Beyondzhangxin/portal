<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<tr>
	<td><input type="checkbox" class="checkboxes" name="" value=""/></td>
	<td class="">${voStatus.count }</td>
	<td>${vo.name }</td>
	<td>${vo.department }</td>
	<td>${vo.position }</td>
	<td>${vo.introduction }</td>
	<td>
		<c:if test="${vo.status == '0' }"><font>新建</font></c:if>
		<c:if test="${vo.status == '1' }"><font color="blue">通过</font></c:if>
		<c:if test="${vo.status == '2' }"><font color="red">未通过</font></c:if>
	</td>
	<td>
		<c:if test="${vo.status != '1' }">
			<a class="edit-a" href="${rootUrl}company/leader/update/${vo.id}">修改</a>
		</c:if>
		<c:if test="${vo.status == '1' }">
			<a class="edit-a" href="${rootUrl}company/leader/update/${vo.id}">详情</a>
		</c:if>
		<a class="add-a"  data-href="${rootUrl}company/leader/delete/${vo.id}" onClick="javascript:aiidc_del(this)">删除</a>
	</td>
</tr>
