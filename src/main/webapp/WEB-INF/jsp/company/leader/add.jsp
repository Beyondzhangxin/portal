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
$(function(){
	$.ajaxSetup ({cache: false});
	$('#submit').click(function() {
		$('#addForm').submit();
	});
});

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
                    <form:form modelAttribute="pageModel" id="addForm" action="${rootUrl}company/leader/add" enctype="multipart/form-data" method="post">
						<form:hidden path="id" />
	                    <div class="main-right-table-con">
	                    	<table class="main-right-table-add">
	                          <tr>
	                            <th><b class="must">*</b>姓名</th>
	                            <td><form:input type="text" class="inputxt" path="name" maxlength="4" placeholder="必填"/></td>
<!-- 	                            <th rowspan="3"><b class="must">*</b>上传照片</th> -->
	                            <td rowspan="3" style="width:250px;height:250px;">
	                            	<div class="upload_img">
	                            		<img src id="imgView" style="max-height:200px;max-width:240px;margin:0 auto 10px auto">
	                            	</div>
									<div class="file_box">
										<input type="button" class="btn" value="*上传图片"> <input
											type="file" class="file" name="file" onchange="viewImage(this,'imgView','pic_preview')">
									</div>
								 </td>
	                          </tr>
	                          <tr>
	                            <th><b class="must">*</b>职务</th>
	                            <td><form:input type="text" maxlength="16" class="inputxt" path="position" placeholder="必填"/></td>
	                          </tr>
	                          <tr>
	                            <th><b class="must">*</b>所属部门</th>
	                            <td><form:input type="text" class="inputxt" maxlength="16" path="department" placeholder="必填"/></td>
	                          </tr>
	                          <tr>
	                            <th>简介</th>
	                            <td colspan="3"><form:textarea class="textxt" maxlength="500" path="introduction"/></td>
	                          </tr>
	                        </table>
	                    </div>
                    </form:form>
					<div class="main-footer-btn">
                       	<button class="edit-btn" id="submit">提交</button>
                        <a class="add-a" href="${rootUrl }company/leader/manage">返回</a>
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
