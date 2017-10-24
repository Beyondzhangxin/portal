<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<tr>
	<td><input type="checkbox" class="checkboxes" name="ids" value="${vo.id }"/></td>
	<td class="">${voStatus.count }</td>
	<td>${vo.name }</td>
	<td>${vo.department }</td>
	<td>${vo.position }</td>
	<td>${vo.introduction }</td>
	<td>
		<a class="edit-a" href="${rootUrl}company/leader/audit/update/${vo.id}">审核</a>
	</td>
</tr>
