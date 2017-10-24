<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<tr>
	<td><input type="checkbox" class="checkboxes" name="" value=""/></td>
	<td class="">${voStatus.count }</td>
	<td>${vo.name }</td>
	<td>${vo.phoneNumber }</td>
	<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${vo.createTime.time}"></fmt:formatDate></td>
	<td>${vo.content }</td>
	<td>
		<a class="edit-a" href="${rootUrl}company/message/update/${vo.id}">回复</a>
		<c:if test="${vo.isVisible == '0'}">
			<a class="edit-a" data-href="${rootUrl}company/message/updStatus/${vo.id}?isVisible=0" onClick="javascript:updStatus(this)">显示</a>
		</c:if>
		<c:if test="${vo.isVisible == '1'}">
			<a class="add-a" data-href="${rootUrl}company/message/updStatus/${vo.id}?isVisible=1" onClick="javascript:updStatus(this)">隐藏</a>
		</c:if>
		<a class="add-a"  data-href="${rootUrl}company/message/delete/${vo.id}" onClick="javascript:aiidc_del(this)">删除</a>
	</td>
</tr>
