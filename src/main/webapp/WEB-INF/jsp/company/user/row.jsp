<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<body>
<script type="text/javascript">
	function deleteUser(data) {
		if(confirm("你确定要删除该记录吗？")){
			$.ajax({
				url:"${rootUrl }company/usermanage/deleteUser",
				type:"post",
				data:{id:data},
				dataType:"json",
				success:function(result){
					if(result.success){
						alert(result.message);
						window.location.reload();
					}else{
						alert(result.message);
					}
				}
			});
		}
	}
</script>
<tr>
	<td><input type="checkbox" class="checkboxes" name="" value=""/></td>
	<td class="">${voStatus.count }</td>
	<td>${vo.name }</td>
	<td>
		<c:if test="${vo.male==1 }">男</c:if>
		<c:if test="${vo.male==2 }">女</c:if>
	</td>
	<td>${vo.mobile }</td>
	<td>${vo.email }</td>
	<td>
		<a class="edit-a" href="${rootUrl }company/usermanage/addUserPage?id=${vo.id}">修改</a>
<%-- 		<input type="button" class="add-a" onclick="deleteUser('${vo.id}')" value="删除"> --%>
	</td>
</tr>
</body>
