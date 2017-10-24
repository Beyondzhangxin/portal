<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
	<meta charset="utf-8"> 
	<title>登录</title>
	<link rel="stylesheet" href="${rootUrl }css/bootstrap.min.css">  
	<script src="${rootUrl }js/jquery.min.js"></script>
	<script src="${rootUrl }js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function login() {
			formData = new FormData($("#frmSearch")[0]);
			$.ajax({
				url : "${rootUrl }common/login",  
		        type : "POST",  
		        data :formData,
		        processData: false,
		        contentType: false,
		        dataType: "json",
		        success : function(data){
		        	if(data.success){
		        		window.location.href = "${rootUrl }common/welcome";
		        	}else{
		        		alert(data.message);
		        	}
		        }
			});
		}	
	</script>
	<style type="text/css">
	.login{
		margin:20px auto;
		width:30%;
		    	 text-align:center;
	}
        .loginName,.passwd{
        	background: url(../images/login_img.png) 6px 8px no-repeat;
		    padding-left: 40px;
		    border: 1px solid #E5E3E0;
		    height: 40px;
		    font-size: 16px;
		    width: 300px;
        }
        .login_bg input{
        	border-radius:5px;
        }
        .login_bg{
        	 background-color:#fff;
        	 border:2px solid  #2FA2B3;
        	 border-radius: 10px;
        }
        .passwd{
        	background: url(../images/psd_img.png) 6px 8px no-repeat;}
        .login_bg p{
        	  font-size: 18px;
 			   font-weight: bold;}
        .login_bg div{
        		padding-top:20px;
        	}
        	.login_btn{
        		    width: 300px;
				    height: 40px;
				    font-size: 16px;
				    background-color: #00C3C1;
				    border: #E5E3E0;
				    color: #fff;
				    margin-bottom: 20px
        	}
	</style>
</head>
<body style="background-color:#30BBD0">
	<div class="login">
		<img src="../images/logo2.png" style="width:200px">
			<div class="login_bg">
		<form action="${rootUrl }common/login" method="post" id="frmSearch">
	
			<div >
					 <p>航天科工智慧产业发展有限公司</p>
			</div>
			<div >
				<input type="text" placeholder="用户名" class="loginName" name="loginName"/>
			</div>
				
				<div>
					<input type="password" placeholder="密码" class="passwd"  name="passwd"/>
				</div>

		</form>
		<div><input type="button" value="登录"  onclick="login()" class="login_btn"></div>
				</div>
	</div>
</body>
</html>