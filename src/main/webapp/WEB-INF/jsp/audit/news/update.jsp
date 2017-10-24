<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
<%@ include file="/WEB-INF/jsp/head.jsp" %>
<title>公司新闻审核管理-公司新闻</title>
<link rel="stylesheet" type="text/css" href="${rootUrl }css/message.css">
<script type="text/javascript" src="${rootUrl }js/pic/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${rootUrl }js/pic/message.js"></script>
<script type="text/javascript" src="${rootUrl }ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${rootUrl }ueditor/ueditor.all.min.js"></script>
    <script type="text/javascript">
        function updStatus(url) {
            var url = url.getAttribute("data-href");
            $(function () {
                $.ajax({
                    url: url,
                    type: "POST",
                    dataType: "text",
                    success: function (data) {
                        alert(data);
                        window.location.href = "${rootUrl }company/news/audit/manage";
                    },
                    error: function (data) {
                        alert("网络连接失败，请联系管理员");
                    }
                });
            });
        }
        ;
    </script>
</head>

<body>
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
                您当前的位置：公司新闻审核管理 >> 审核
            <div class="main-right-con">
                <form:form modelAttribute="pageModel" id="updateForm" action="" method="post">
                    <form:hidden path="id" />
                <div class="main-right-table-con">
                    <table class="culture-table-add">
                        <tr>
                            <th>图片上传：</th>
                            <td colspan="3">
                                <c:forEach items="${picList}" var="pic">
	                                <div class="culture-add-img-con">
	                                     <b><img src="${rootUrl }${rootPath}${pic.realName}" /><br />${pic.name}</b>
	                                </div>
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <th>类型：</th>
                            <td>
                                <c:if test="${pageModel.type == '1' }">公司新闻</c:if>
                                <c:if test="${pageModel.type == '2' }">媒体聚焦</c:if>
                                <c:if test="${pageModel.type == '3' }">行业新闻</c:if>
                            </td>
                            <th>封面新闻：</th>
                          	<td>
                                 <c:if test="${pageModel.iscover == '1' }">是</c:if>
                                 <c:if test="${pageModel.iscover == '0' }">否</c:if>
                           	</td>
                        </tr>
                        <tr>
                            <th>标题：</th>
                            <td colspan="3"><form:input type="text" class="inputxt" path="title" placeholder="必填" disabled="true" /></td>
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
                        <a class="edit-a" data-href="${rootUrl }company/news/audit/updStatus/${pageModel.id}?status=1"
                           onClick="javascript:updStatus(this)">通过</a>
                        <a class="add-a" data-href="${rootUrl }company/news/audit/updStatus/${pageModel.id}?status=2"
                           onClick="javascript:updStatus(this)">不通过</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
