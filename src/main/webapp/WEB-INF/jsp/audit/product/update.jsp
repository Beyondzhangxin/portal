<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/head.jsp" %>
    <title>解决方案审核</title>
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
                        window.location.href = "${rootUrl }company/product/audit/manage";
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
    UE.Editor.prototype.getActionUrl = function (action) {
        var standardId = $(".standardId").val();
        if (action == 'uploadimage') { //图片上传
            return '${rootUrl }/pub/uploadImage.do';
        }
        if (action == 'catchimage') {
            console.log("catchimage");
        } else {
            return this._bkGetActionUrl.call(this, action);
        }
    }
</script>
<div class="container">
    <div class="Main">
        <div class="mainRight">
            <div class="main-right-title">
                您当前的位置：公司产品审核 >> 审核
            </div>
            <div class="main-right-con">
                <form:form modelAttribute="pageModel" id="updateForm" action="" method="post">
                <form:hidden path="id" />
                <div class="main-right-table-con">
                    <table class="culture-table-add">
                        <tr>
                            <th>图片上传：</th>
                            <td>
                                <div class="upload_img">
                                    <img src="${rootUrl}${rootPath}" id="imgView"
                                         style="max-height:200px;max-width:240px;margin:0 auto 10px auto">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th><b class="must">*</b>名称：</th>
                            <td colspan="3"><form:input maxlength="64" type="text" class="inputxt" path="name" disabled="true"/></td>
                        </tr>
                        <tr>
                            <th><b class="must">*</b>类型：</th>
                            <td>
                                <c:forEach items="${types}" var="vo">
                                    <c:if test="${vo.key ==pageModel.type }">${vo.value}</c:if>
                                </c:forEach>     
                            </td>
                            <th><b class="must">*</b>分类：</th>
                            <td>
                                <c:forEach items="${subTypes}" var="vo">
                                    <c:if test="${vo ==pageModel.subType}">${vo.value}</c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="50">
                                <form:textarea path="content" id="myEditor" style="width:100%;" class="content" />
                            </td>
                        </tr>
                    </table>
                    </form:form>
                    <div class="main-footer-btn">
                        <a class="edit-a"
                           data-href="${rootUrl }company/product/audit/updStatus/${pageModel.id}?status=1"
                           onClick="javascript:updStatus(this)">通过</a>
                        <a class="add-a"
                           data-href="${rootUrl }company/product/audit/updStatus/${pageModel.id}?status=2"
                           onClick="javascript:updStatus(this)">不通过</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
