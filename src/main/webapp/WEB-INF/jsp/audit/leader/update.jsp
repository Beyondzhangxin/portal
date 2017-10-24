<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
<%@ include file="/WEB-INF/jsp/head.jsp" %>
<title>公司概况管理-公司领导</title>
<link rel="stylesheet" type="text/css" href="${rootUrl }css/message.css">
<script type="text/javascript" src="${rootUrl }js/pic/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${rootUrl }js/pic/message.js"></script>
<script type="text/javascript">
function updStatus(url) {
	var url = url.getAttribute("data-href");
	$(function(){
		$.ajax({  
	        url : url,  
	        type : "POST",  
	        dataType: "text",
	        success : function(data) {  
	        	alert(data);
	        	window.location.href = "${rootUrl }company/leader/audit/manage";
	        },  
	        error : function(data) { 
	        	alert("网络连接失败，请联系管理员");
	        }  
	   });  
	});
};
</script>
</head>

<body >
	<div class="container">
        <div class="Main">
                <div class="mainRight">
            	<div class="main-right-title">
                	您当前的位置：公司概况管理  >>  公司领导
                </div>
                <div class="main-right-con">
                    <form:form modelAttribute="pageModel" id="updateForm" action="" method="post">
	                    <form:hidden path="id" />
	                    <div class="main-right-table-con">
	                    	<table class="main-right-table-add">
	                          <tr>
	                            <th><b class="must">*</b>姓名</th>
	                            <td><form:input type="text" class="inputxt" path="name" placeholder="必填" disabled="true"/></td>
	                            <td rowspan="3" style="width:250px;height:250px;">
	                            	<div class="upload_img">
	                            		<img src="${rootUrl}${pathName }" id="imgView" style="max-height:200px;margin:0 auto 10px auto">
	                            	</div>
								</td>
	                          </tr>
	                          <tr>
	                            <th><b class="must">*</b>职务</th>
	                            <td><form:input type="text" class="inputxt" path="position" placeholder="必填" disabled="true"/></td>
	                          </tr>
	                          <tr>
	                            <th><b class="must">*</b>所属部门</th>
	                            <td><form:input type="text" class="inputxt" path="department" placeholder="必填" disabled="true"/></td>
	                          </tr>
	                          <tr>
	                            <th>简介</th>
	                            <td colspan="3"><form:textarea class="textxt" path="introduction" disabled="true"/></td>
	                          </tr>
	                        </table>
	                    </div>
                    </form:form>
					<div class="main-footer-btn">
						<a class="edit-a" data-href="${rootUrl }company/leader/audit/updStatus/${pageModel.id}?status=1" onClick="javascript:updStatus(this)">通过</a>
						<a class="add-a" data-href="${rootUrl }company/leader/audit/updStatus/${pageModel.id}?status=2" onClick="javascript:updStatus(this)">不通过</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
