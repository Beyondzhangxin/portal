<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
<%@ include file="/WEB-INF/jsp/head.jsp" %>
<title>公司概况管理-企业荣誉与资质</title>
<link rel="stylesheet" type="text/css" href="${rootUrl }css/message.css">
<script type="text/javascript" src="${rootUrl }js/pic/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${rootUrl }js/pic/message.js"></script>
<script type="text/javascript" src="${rootUrl }ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${rootUrl }ueditor/ueditor.all.min.js"></script>
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
	        	window.location.href = "${rootUrl }company/honor/audit/manage";
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
    <script type="text/javascript">
	    var ue = UE.getEditor('myEditor', {
	        autoHeightEnabled: false,
	        autoFloatEnabled: false
	    });
	    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
	    UE.Editor.prototype.getActionUrl = function(action) {
	    	var standardId=$(".standardId").val();
	    	if (action == 'uploadimage') { //图片上传
	             return '${rootUrl }/pub/uploadImage.do';
	         }if(action=='catchimage'){
	        	console.log("catchimage");
	         }else {
	             return this._bkGetActionUrl.call(this, action);
	         }
	    }
    </script>
	<div class="container">
        <div class="Main">
                <div class="mainRight">
            	<div class="main-right-title">
                	您当前的位置：公司概况管理  >>  企业荣誉与资质
                </div>
                <div class="main-right-con">
                    <form:form modelAttribute="pageModel" id="addForm"  enctype="multipart/form-data" method="post">
	                    <form:hidden path="type" value="2"/>
	                    <form:hidden path="id" />
	                    <form:hidden path="picId" />
	                    <form:hidden path="picName" />
	                    <div class="main-right-table-con">
							<table class="culture-table-add">
	                          <tr>
	                            <th><b class="must">*</b>封面图片：</th>
	                            <td >
	                            	<div class="upload_img">
	                            		<img src="${rootUrl}${pathName }" id="imgView" style="max-height:200px;max-width:240px;margin:0 auto 10px auto">
	                            	</div>
	                            </td>
	                          </tr>
	                          <tr>
	                            <th><b class="must">*</b>标题：</th>
	                            <td><form:input type="text" class="inputxt" path="title"/></td>
	                          </tr>
	                          <tr>
	                          	<td colspan="50">
	                        		<form:textarea path="content" id="myEditor" style="width:100%;" class="content"/>
	                          	</td>
                        	  </tr>
                        	</table>
	                    </div>
                    </form:form>
					<div class="main-footer-btn">
						<a class="edit-a" data-href="${rootUrl }company/honor/audit/updStatus/${pageModel.id}?status=1" onClick="javascript:updStatus(this)">通过</a>
						<a class="add-a" data-href="${rootUrl }company/honor/audit/updStatus/${pageModel.id}?status=2" onClick="javascript:updStatus(this)">不通过</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
  	var jsonmsg=${json};
  	if(jsonmsg.message)	alert(jsonmsg.message);
</script> 
</html>
