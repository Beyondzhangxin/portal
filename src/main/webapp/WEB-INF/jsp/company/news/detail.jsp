<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Zhangx
  Date: 2017/8/29
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <%@ include file="/WEB-INF/jsp/head.jsp" %>
    <title>公司新闻管理>>修改</title>
    <link rel="stylesheet" type="text/css" href="${rootUrl }css/webuploader.css">
    <link rel="stylesheet" type="text/css" href="${rootUrl }css/style.css">
    <script src="${rootUrl}js/webuploader.js"></script>
    <script src="${rootUrl}js/upload.js"></script>
    <script type="text/javascript" src="${rootUrl }ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="${rootUrl }ueditor/ueditor.all.min.js"></script>
</head>
<style>

#myEditor img{
	max-width:100%;
}
</style>

<body>
    <!-- 实例化编辑器 -->
    <script type="text/javascript">
	    var ue = UE.getEditor('myEditor', {
	        autoHeightEnabled: false,
	        autoFloatEnabled: false
	    });
	    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
	    UE.Editor.prototype.getActionUrl = function(action) {
	    	var standardId=$(".standardId").val();
	    	if (action == 'uploadimage') { //图片上传
	             return '${rootUrl}pub/uploadImage.do';
	         }if(action=='catchimage'){
	        	console.log("catchimage");
	         }else {
	             return this._bkGetActionUrl.call(this, action);
	         }
	    }
    </script>
<div class="container" >
    <div class="Main">
        <div class="mainRight" >
            <div class="main-right-title">
                您当前的位置：公司新闻管理 >> 新增
            </div>
            <div class="main-right-con">
                <div class="main-right-table-con">
                    <form class="form">
                    <table class="culture-table-add">
                        <tr>
                            <th><b class="must">*</b>图片上传：</th>
                            <td colspan="3">
                                <%--图片上传container--%>
                                <div id="container">
                                    <%--头部，相册选择和格式选择--%>
                                    <div id="uploader">
                                        <div class="queueList">
                                            <div id="dndArea" class="placeholder">
                                                <div id="filePicker"></div>
                                                <p>或将照片拖到这里</p>
                                            </div>
                                        </div>
                                        <div class="statusBar" style="display:none;">
                                            <div class="progress">
                                                <span class="text">0%</span>
                                                <span class="percentage"></span>
                                            </div>
                                            <div class="info"></div>
                                            <div class="btns">
                                                <div id="filePicker2"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th><b class="must">*</b>类型：</th>
                            <td>
                                <select class="selectxt" name="type">
                                    <c:forEach items="${newsTypes}" var="type">
                                       <option value="${type.value}">${type.name}</option>
                                    </c:forEach>
                                </select>
                            </td> 
                            <th>封面新闻：</th>
                            <td>
                                <select class="selectxt" name="iscover">
                                     <option value="0">否</option>
                                     <option value="1">是</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th><b class="must">*</b>标题：</th>
                            <td colspan="3"><input type="text" maxlength="32" class="inputxt" name="title" id="title"/></td>
                        </tr>
                        <tr>
	                        <td   colspan="50">
	                        	<textarea id="myEditor" datatype="*" nullmsg="请输入" style="width:100%;" class="content" name="content" ></textarea>
	                        </td>
                        </tr>
                    </table>
                    </form>
                    <div class="main-footer-btn">
                        <button class="edit-btn">提交</button>
                        <a class="add-a" href="${rootUrl }company/news/manage">返回</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(img_upload($('.edit-btn'),'addPic','add',$('.form')));
</script>
</html>
