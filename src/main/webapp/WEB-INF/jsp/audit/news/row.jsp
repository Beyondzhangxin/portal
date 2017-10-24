<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<tr>
	<td><input type="checkbox" class="checkboxes" name="ids" value="${vo.id }"/></td>
	<td class="">${voStatus.count }</td>
	<td>
		<c:if test="${vo.type=='1' }">公司新闻</c:if>
		<c:if test="${vo.type=='2' }">媒体聚焦</c:if>
		<c:if test="${vo.type=='3' }">行业新闻</c:if>
	</td>
	<td>${vo.title }</td>
	<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${vo.createTime.time}"></fmt:formatDate></td>
	<td>
		<a class="edit-a" href="${rootUrl}company/news/audit/update/${vo.id}">审核</a>
	</td>
</tr>
