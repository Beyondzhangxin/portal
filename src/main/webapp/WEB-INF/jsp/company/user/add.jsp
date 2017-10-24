<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
<%@ include file="/WEB-INF/jsp/head.jsp" %>
<title>用户管理-添加用户</title>
<link rel="stylesheet" type="text/css" href="${rootUrl }css/message.css">
<link rel="stylesheet" href="${rootUrl }css/bootstrap.min.css">  
<script src="${rootUrl }js/bootstrap.min.js"></script>
<script type="text/javascript" src="${rootUrl }js/pic/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${rootUrl }js/pic/message.js"></script>
<script type="text/javascript">
	function addUser() {
		formData = new FormData($("#formSearch")[0]);
		$.ajax({
			url:"${rootUrl }company/usermanage/addUser",
			type : "POST",  
			data :formData,
			processData: false,
			contentType: false,
			dataType: "json",
			success : function(data){
				if(data.success){
					alert(data.message);
					window.location.href="${rootUrl }company/usermanage/list";
// 					window.location.reload();
				}else{
					alert(data.message);
				}
			}
		});
	}
	
	function showImage(obj) {
		//检验是否为图像文件  
	    var file = document.getElementById("person_photo").files[0];  
	    if(!/image\/\w+/.test(file.type)){  
	        alert("请选择图片！");  
	        return false;  
	    }  
	    var reader = new FileReader();  
	    //将文件以Data URL形式读入页面  
	    reader.readAsDataURL(file);  
	    reader.onload=function(e){  
	    	var result=document.getElementById("show_image_div");  
	    	result.innerHTML='<img style="max-width:140px;max-height:140px;border-radius:50%;" src="' + this.result +'" alt="" />';  
	    }  
	}
	
	
</script>
</head>
<body >
	<div class="container">
        <div class="Main">
                <div class="mainRight">
            	<div class="main-right-title">
                	您当前的位置：用户管理  >>  添加用户
                </div>
                <div class="main-right-con">
                    <form enctype="multipart/form-data" method="post" id="formSearch" action="${rootUrl }company/usermanage/addUser">
	                  <div class="main-right-table-con">
	                  		<table class="culture-table-add">
	                    		<tr>
		                            <th><span style="color: red">*</span>姓名</th>
		                            <td>
		                            	<input class="inputxt" type="hidden" name="id" id="edit_id" value="${editUserInfo.id }">
		                            	<input class="inputxt" maxlength="100" type="text" name="name" value="${editUserInfo.name }" placeholder="必填">
		                            </td>
		                            
		                            <th><span style="color: red">*</span>登录名</th>
	                          		<td><input class="inputxt" maxlength="100" type="text" name="loginName" value="${editUserInfo.loginName }" placeholder="必填"></td>
	                          	</tr>
								<tr>
									<th><span style="color: red">*</span>密码</th>
									<td>
										<input class="inputxt" type="password" maxlength="100" type="text" name="passwd"
											   value="${editUserInfo.passwd}" placeholder="必填">
									</td>

									<th><span style="color: red">*</span>密码确认</th>
									<td><input class="inputxt" type="password" maxlength="100" type="text"
											   name="passwdR"
											   value="${editUserInfo.passwd}" placeholder="必填" onkeyup="validate()">
									</td>
								</tr>
	                          	<tr>
	                          		<th><span style="color: red">*</span>性别</th>
		                            <td>
			                            <c:if test="${editUserInfo.male==1 or editUserInfo.male==2}" var="if_male">
			                          		<label class="control-label col-sm-6">男<input type="radio" name="male" value="1" <c:if test="${editUserInfo.male==1 }">checked="checked"</c:if>></label>
			                          		<label class="control-label col-sm-6">女<input type="radio" name="male" value="2" <c:if test="${editUserInfo.male==2 }">checked="checked"</c:if>></label>
			                            </c:if>
			                            <c:if test="${!if_male }">
			                            	<label class="control-label col-sm-6">男<input type="radio" name="male" value="1"  checked="checked"></label>
			                          		<label class="control-label col-sm-6">女<input type="radio" name="male" value="2"></label>
			                            </c:if>
		                            </td>
	                          	
	                          		<th><span style="color: red">*</span>角色</th>
	                          		<td>
	                          			<select class="selectxt" name="roleId" id="addUser_role">
		                          			<c:forEach items="${roleList }" var="roleinfo" >
		                          				<option value="${roleinfo.id }">${roleinfo.name }</option>
		                          			</c:forEach>
	                          			</select>
	                          		</td>
	                          	</tr>
	                          	<tr>
	                          		<th><span style="color: red">*</span>移动电话</th>
	                          		<td><input class="inputxt" maxlength="25" type="text" name="mobile" value="${editUserInfo.mobile }" placeholder="必填"></td>
	                          		
	                          		<th><span style="color: red">*</span>电子邮件</th>
	                          		<td><input class="inputxt" maxlength="100" type="text" name="email" value="${editUserInfo.email }" placeholder="必填"></td>
	                          	</tr>
	                          	<tr>
	                          		<th>头像</th>
	                          		<td colspan="2">
										<div id="show_image_div" ><img src="${rootUrl }${personImagePath}" style="max-width:140px;max-height:140px;border-radius:50%;" /></div>
	                           		</td>
	                           		
	                          		<td>
	                          			<input type="file" id="person_photo" name="personImage" onchange="showImage(this)">
	                          		</td>
	                          	</tr>
	                  		</table>
	                  </div>
                    </form>
					<div class="main-footer-btn">
						<input class="edit-btn" type="button" onclick="addUser()" value="提交">
                        <a class="add-a" href="${rootUrl }company/usermanage/list">返回</a> 
                    </div>
                </div>
            </div>
        </div>
    </div>
 <script type="text/javascript">
 	$("#addUser_role").find("option[value=${editUserInfo.role.id}]").prop("selected",true); 
 </script>
</body>
</html>
