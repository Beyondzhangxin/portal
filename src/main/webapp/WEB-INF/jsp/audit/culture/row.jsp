<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<tr>
	<td><input type="checkbox" class="checkboxes" name="ids" value="${vo.id }"/></td>
	<td class="">${voStatus.count }</td>
	<td>${vo.title }</td>
<%-- 	<td>${vo.content }</td> --%>
	<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${vo.createTime.time}"></fmt:formatDate></td>
	<td>
		<a class="edit-a" href="${rootUrl}company/culture/audit/update/${vo.id}">审核</a>
	</td>
</tr>
