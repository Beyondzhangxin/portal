<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
<%@ include file="/WEB-INF/jsp/head.jsp" %>
<title>客户留言管理-回复</title>
<script type="text/javascript">

$(function(){
	$.ajaxSetup ({cache: false});
	$('#submit').click(function() {
		$('#updateForm').submit();
	});
});
</script>
</head>

<body >
	<div class="container">
        <div class="Main">
                <div class="mainRight">
            	<div class="main-right-title">
                	您当前的位置：客户留言管理  >>  回复
                </div>
                <div class="main-right-con">
                    <form:form modelAttribute="pageModel" id="updateForm" action="${rootUrl}company/message/update" method="post">
	                    <form:hidden path="id" />
	                    <form:hidden path="name" />
	                    <form:hidden path="content" />
	                    <div class="main-right-table-con">
	                    	<table class="culture-table-add">
	                          <tr>
	                            <td>
	                            	<p class="paragraph"><span>留言时间</span>  <strong>
	                            	<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${pageModel.createTime.time}"></fmt:formatDate></strong></p>
									<p class="paragraph"><span>联系方式</span>  <strong>${pageModel.phoneNumber }</strong></p>
									<p class="paragraph"><span>留言内容</span>  
	                                <strong>${pageModel.content }</strong>
	                				</p>
	                            </td>
	                          </tr>
	                          <tr>
	                          	<td>回复留言</td>
	                          </tr>
	                          <tr>
	                            <td><form:textarea path="reply" maxlength="500" class="textxt"/></td>
	                          </tr>
	                        </table>
	                    </div>
                    </form:form>
					<div class="main-footer-btn">
                       	<button class="edit-btn" id="submit">提交</button>
                        <a class="add-a" href="${rootUrl }company/message/manage">返回</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
<script type="text/javascript">
  	var jsonmsg=${json};
  	if(jsonmsg.message)	alert(jsonmsg.message);
 </script>
</body>
</html>
