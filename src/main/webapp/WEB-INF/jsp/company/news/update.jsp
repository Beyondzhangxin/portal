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
    <title>公司新闻管理>>新增</title>
    <link rel="stylesheet" type="text/css" href="${rootUrl }css/webuploader.css">
    <link rel="stylesheet" type="text/css" href="${rootUrl }css/style.css">
    <script src="${rootUrl}js/webuploader.js"></script>
    <script src="${rootUrl}js/upload.js"></script>
    <script type="text/javascript" src="${rootUrl }ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="${rootUrl }ueditor/ueditor.all.min.js"></script>
</head>
<style>
</style>

<body>
<div class="container">
    <div class="Main">
        <div class="mainRight">
            <div class="main-right-title">
                您当前的位置：公司新闻管理 >> 新增
            </div>
            <div class="main-right-con">

                <div class="main-right-table-con">
                    <form class="form">
                        <c:forEach items="${picList}" var="pic">
                             <input name="pic" hidden type="image" dataName="${pic.name}" src="${rootUrl}${pic.realName}"/>
                        </c:forEach>
                        <input hidden name="id" value="${news.id}"/>
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
                                <td >
                                    <select class="selectxt" name="type">
                                        <c:forEach items="${newsTypes}" var="type">
                                            <c:choose>
                                                <c:when test="${type.value==news.type}">
                                                    <option value="${type.value}" selected="selected">${type.name}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${type.value}">${type.name}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </td>
                                <th>封面新闻：</th>
                           		 <td>
	                                <select class="selectxt" name="iscover">
	                                     <option value="0">否</option>
	                                     <option value="1" <c:if test="${news.iscover == '1' }">selected="selected"</c:if>>是</option>
	                                </select>
                            	</td> 
                            </tr>
                            <tr>
                                <th><b class="must">*</b>标题：</th>
                                <td colspan="3"><input type="text" maxlength="32" class="inputxt" name="title" id="title" value="${news.title}" /></td>
                            </tr>
                            <tr>
                                <td colspan="50">
                                    <textarea id="myEditor" datatype="*" nullmsg="请输入" style="width:100%;"
                                              class="content" name="content">${news.content}</textarea>
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

    $(img_upload($('.edit-btn'), '../addPic', '../update', $('.form')));
    var ue = UE.getEditor('myEditor', {
        autoHeightEnabled: false,
        autoFloatEnabled: false
    });
    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    UE.Editor.prototype.getActionUrl = function (action) {
        var standardId = $(".standardId").val();
        if (action == 'uploadimage') { //图片上传
            return '${rootUrl }pub/uploadImage.do';
        }
        if (action == 'catchimage') {
            console.log("catchimage");
        } else {
            return this._bkGetActionUrl.call(this, action);
        }
    }
</script>
</html>
