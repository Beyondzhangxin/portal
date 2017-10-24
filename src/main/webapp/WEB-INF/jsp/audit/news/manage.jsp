<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
    <style>
        .active { border-bottom: 5px solid #00a6a6;
            color: #00a6a6; }
    </style>
    <%@ include file="/WEB-INF/jsp/head.jsp" %>
    <title>公司新闻审核管理-公司新闻</title>
    <script type="text/javascript">
        $(function () {
            var total = ${pagn.totalCount};
            var pageSize = ${pagn.pageSize};
            var currentPage = ${pagn.currentPage};
            $('.M-box3').pagination({
                totalData: total,
                showData: pageSize,
                current: currentPage,
                jump: true,
                coping: true,
                homePage: '首页',
                endPage: '末页',
                prevContent: '上页',
                nextContent: '下页',
                callback: function (api) {
                    var pageNo = $("#pageNo").val(api.getCurrent());
                    $('#listForm').submit();
                }
            });
        })

        function updStatusAll() {
            var flag = false;
            var j = 0;
            var ids = "";
            var checkboxes = $(".checkboxes");
            for (i = 0; i < checkboxes.length; i++) {
                if (j > 0) {
                    ids = ids + ",";
                }
                if (checkboxes[i].checked) {
                    ids = ids + checkboxes[i].value;
                    flag = true;
                    j++;
                }
            }
            if (flag) {
                if (confirm("你确认要一键通过这" + j + "项？")) {
                    $.ajax({
                        url: "${rootUrl}company/news/audit/updStatusAll",
                        type: "POST",
                        data: {ids: ids},
                        dataType: "text",
                        success: function (data) {
                            alert(data);
                            $('#listForm').submit();
                        },
                        error: function (data) {
                            alert("网络连接失败，请联系管理员");
                        }
                    });
                }
            }
            else
                alert('请至少选择一个');

        }
    </script>
</head>

<body>
<div class="container">
    <div class="Main">
        <div class="mainRight">
            <div class="main-right-title">
                您当前的位置：公司新闻审核管理 >> 公司新闻
            </div>

            <div class="main-right-con">
                <form:form modelAttribute="pageModel" id="listForm" name="listForm"
                           action="${rootUrl }company/news/audit/manage" method="get">
                    <input type="hidden" id="pageNo" name="pageNo" value="1">
                </form:form>
                <div class="main-right-form">
                    <ul class="main-right-form-ul">
                        <li <c:if test="${type==1}">class="active"</c:if>><a href="${rootUrl }company/news/audit/manage?type=1">公司新闻</a></li>
                        <li <c:if test="${type==2}">class="active"</c:if>><a href="${rootUrl }company/news/audit/manage?type=2">媒体聚焦</a></li>
                        <li <c:if test="${type==3}">class="active"</c:if>><a href="${rootUrl }company/news/audit/manage?type=3">行业新闻</a></li>
                    </ul>
                    <a class="edit-a" href="javascript:updStatusAll()">一键通过</a>

                </div>
                <div class="main-right-table-con">
                    <table class="main-right-table">
                        <thead>
                        <tr>
                            <th style="width:5%;"><input type="checkbox" onClick="javascript:selectAll(this)"/></th>
                            <th style="width:5%;">序号</th>
                            <th>新闻类型</th>
                            <th>新闻标题</th>
                            <th >上传时间</th>
                            <th style="width:9%;">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pagn.result }" var="vo" varStatus="voStatus">
                            <%@ include file="row.jsp" %>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="M-box3"></div>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>
