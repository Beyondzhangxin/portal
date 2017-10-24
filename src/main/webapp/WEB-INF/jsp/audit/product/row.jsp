<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<tr>
    <td><input type="checkbox" class="checkboxes" name="ids" value="${vo.id }" /></td>
    <td class="">${voStatus.count }</td>
    <td>${vo.name}</td>
    <td><c:if test="${vo.type == '1' }">平台类</c:if>
        <c:if test="${vo.type == '2' }">行业类</c:if></td>
    <td>${vo.subType.value }</td>
    <td><fmt:formatDate value="${vo.createTime.time}" pattern="yyyy-MM-dd hh:mm" /></td>
    <td>
        <a class="edit-a" href="${rootUrl}company/product/audit/update/${vo.id}">审核</a>
    </td>
</tr>
