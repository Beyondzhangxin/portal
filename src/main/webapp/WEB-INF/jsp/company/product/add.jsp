<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
<%@ include file="/WEB-INF/jsp/head.jsp" %>
<title>公司产品管理</title>
<link rel="stylesheet" type="text/css" href="${rootUrl }css/message.css">
<script type="text/javascript" src="${rootUrl }js/pic/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${rootUrl }js/pic/message.js"></script>
<script type="text/javascript" src="${rootUrl }ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${rootUrl }ueditor/ueditor.all.min.js"></script>
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
    <script type="text/javascript">
	    var ue = UE.getEditor('myEditor', {
	        autoHeightEnabled: false,
	        autoFloatEnabled: false
	    });
	    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
	    UE.Editor.prototype.getActionUrl = function(action) {
	    	var standardId=$(".standardId").val();
	    	if (action == 'uploadimage') { //图片上传
	             return '${rootUrl }pub/uploadImage.do';
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
                	您当前的位置：公司产品管理  >>  添加
                </div>
                <div class="main-right-con">
                    <form:form modelAttribute="pageModel" id="addForm" action="${rootUrl}company/product/add" enctype="multipart/form-data" method="post">
	                    <div class="main-right-table-con">
							<table class="culture-table-add">
	                          <tr>
	                            <th><b class="must">*</b>封面图片：</th>
	                            <td colspan="3">
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
	                            <th><b class="must">*</b>名称：</th>
	                            <td colspan="3"><form:input maxlength="16" type="text" class="inputxt" path="name"/></td>
	                          </tr>
	                          <tr>
                                <th><b class="must">*</b>类型：</th>
                                <td>
                                    <select class="selectxt" name="type">
                                        <c:forEach items="${types }" var="vo">
                                             <option value="${vo.key}">${vo.value}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <th><b class="must">*</b>分类：</th>
                                <td>
                                    <select class="selectxt" name="subType">
                                        <c:forEach items="${subTypes }" var="vo">
                                             <option value="${vo}">${vo.value}</option>
                                        </c:forEach>
                                    </select>
                                </td>
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
                       	<button class="edit-btn" id="submit">提交</button>
                        <a class="add-a" href="${rootUrl }company/product/manage">返回</a>
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
