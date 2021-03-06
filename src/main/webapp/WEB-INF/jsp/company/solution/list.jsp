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
    <style>
        .active { border-bottom: 5px solid #00a6a6;
            color: #00a6a6; }
    </style>
    <%@ include file="/WEB-INF/jsp/head.jsp" %>
    <title>解决方案管理</title>
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
        });
        function deleteAll() {
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
                if (confirm("你确认要删除这" + j + "项？")) {
                    $.ajax({
                        url: "${rootUrl}company/solution/deleteAll",
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
<div class="container" >
    <div class="Main">
        <div class="mainRight">
            <div class="main-right-title">
                您当前的位置：解决方案管理
            </div>
            <div class="main-right-con">
                <form:form modelAttribute="pageModel" id="listForm" name="listForm"
                           action="${rootUrl }company/solution/manage" method="get">
                    <input type="hidden" id="pageNo" name="pageNo" value="1">
                </form:form>
                <div class="main-right-form">
                    <ul class="main-right-form-ul">
                        <li <c:if test="${type==1}">class="active"</c:if>><a href="${rootUrl }company/solution/manage?type=1">总体解决方案</a></li>
                        <li <c:if test="${type==2}">class="active"</c:if>><a href="${rootUrl }company/solution/manage?type=2">专项解决方案</a></li>
                    </ul>
                    <button class="add-btn" onclick="javascript:window.open('${rootUrl}company/solution/add','_self')">新增</button>
                    <button class="add-btn" onclick="deleteAll()">删除</button>

                </div>
                <div class="main-right-table-con">
                    <table class="main-right-table">
                        <thead>
                        <tr>
                            <th style="width:5%;"><input type="checkbox"  onClick="javascript:selectAll(this)"/></th>
                            <th style="width:5%;">序号</th>
                            <th>方案标题</th>
                            <th>方案作者</th>
                            <th style="width: 18%">上传时间</th>
                            <th style="width:5%;">状态</th>
                            <th style="width: 18%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="index" value="0"></c:set>
                        <c:forEach var="product" items="${pagn.result}">
                            <c:set var="index" value="${index+1}"></c:set>
                            <tr>
                                <td><input type="checkbox" class="checkboxes" value="${product.id}" /></td>
                                <td>${index}</td>
                                <td>${product.title}</td>
                                <td>${product.creator}</td>
                                <td><fmt:formatDate value="${product.createTime.time}" pattern="yyyy-MM-dd hh:mm" /></td>
                                <td>
                                    <c:if test="${product.status == '0' }"><font>新建</font></c:if>
                                    <c:if test="${product.status == '1' }"><font color="blue">通过</font></c:if>
                                    <c:if test="${product.status == '2' }"><font color="red">未通过</font></c:if>
                                </td>
                                <td>
                                    <c:if test="${product.status=='1'}">
                                        <a class="edit-a" href="${rootUrl}company/solution/update/${product.id}">详情</a>
                                    </c:if>
                                    <c:if test="${product.status!='1'}">
                                        <a class="edit-a" href="${rootUrl}company/solution/update/${product.id}">修改</a>
                                    </c:if>
                                    <a class="edit-a" href="${rootUrl}company/solution/isTop/${product.id}">置顶</a>
                                </td>
                            </tr>
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
