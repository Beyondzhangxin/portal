<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<tr>
	<td><input type="checkbox" class="checkboxes" name="ids" value="${vo.id }"/></td>
	<td class="">${voStatus.count }</td>
	<td>${vo.title }</td>
	<td>${vo.creator }</td>
	<td><fmt:formatDate value="${vo.createTime.time}" pattern="yyyy-MM-dd hh:mm" /></td>
	<td>
		<a class="edit-a" href="${rootUrl}company/solution/audit/update/${vo.id}">审核</a>
	</td>
</tr>
