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
    <title>公司新闻管理</title>
    <style>
        .active { border-bottom: 5px solid #00a6a6;
            color: #00a6a6; }
    </style>
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
                        url: "${rootUrl}company/news/deleteAll",
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
                您当前的位置：公司新闻管理
            </div>
            <div class="main-right-con">
                <div class="main-right-form">
                    <ul class="main-right-form-ul">
<%-- <<<<<<< .mine
                         <li class="active"><a href="${rootUrl }company/news/manage?type=1">公司新闻</a></li>
                        <li ><a href="javascript:;${rootUrl }company/news/manage?type=2" >媒体聚焦</a></li>
                        <li ><a href="javascript:; ${rootUrl }company/news/manage?type=3" >行业新闻</a></li>
======= --%>
                        <li <c:if test="${type==1}">class="active"</c:if>> <a
                                href="${rootUrl }company/news/manage?type=1">公司新闻</a></li>
                        <li <c:if test="${type==2}">class="active"</c:if>><a
                                href="${rootUrl }company/news/manage?type=2">媒体聚焦</a></li>
                        <li <c:if test="${type==3}">class="active"</c:if>><a
                                href="${rootUrl }company/news/manage?type=3">行业新闻</a></li>

                    </ul>
                    <button class="add-btn" onclick="javascript:window.open('${rootUrl}company/news/detail','_self')">
                        新增
                    </button>
                    <button class="add-btn" onclick="deleteAll()">删除</button>
                </div>
                <div class="main-right-table-con">
                    <form:form modelAttribute="pageModel" id="listForm" name="listForm"
                               action="${rootUrl }company/news/manage" method="get">
                        <input type="hidden" id="pageNo" name="pageNo" value="1">
                    </form:form>
                    <table class="main-right-table">
                        <thead>
                        <tr>
                            <th style="width:5%;"><input type="checkbox" onClick="javascript:selectAll(this)" /></th>
                            <th style="width:5%;">序号</th>
                            <th style="width:10%;">新闻类型</th>
                            <th>新闻标题</th>
                            <th style="width: 18%">上传时间</th>
                            <th style="width:8%;">状态</th>
                            <th style="width: 18%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="index" value="0"></c:set>
                        <c:forEach var="news" items="${pagn.result}">
                            <c:set var="index" value="${index+1}"></c:set>
                            <tr>
                                <td><input type="checkbox" class="checkboxes" name="ids" value="${news.id}" /></td>
                                <td>${index}</td>
                                <td>
                                    <c:if test="${news.type == '1' }">公司新闻</c:if>
                                    <c:if test="${news.type == '2' }">媒体聚焦</c:if>
                                    <c:if test="${news.type == '3' }">行业新闻</c:if>
                                </td>
                                <td>${news.title}</td>
                                <td><fmt:formatDate value="${news.createTime.time}" pattern="yyyy-MM-dd HH:mm" /></td>
                                <td>
                                    <c:if test="${news.status == '0' }"><font>新建</font></c:if>
                                    <c:if test="${news.status == '1' }"><font color="blue">通过</font></c:if>
                                    <c:if test="${news.status == '2' }"><font color="red">未通过</font></c:if>
                                </td>
                                <td>
                                    <c:if test="${news.status=='1'}">
                                        <a class="edit-a" href="${rootUrl}company/news/update/${news.id}">详情</a>
                                    </c:if>
                                    <c:if test="${news.status != '1' }">
                                        <a class="edit-a" href="${rootUrl}company/news/update/${news.id}">修改</a>
                                    </c:if>
                                    <a class="edit-a" href="${rootUrl}company/news/isTop/${news.id}">置顶</a>
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
<script>
    $(".main-right-form-ul li").click(function () {
        $(this).addClass("active").siblings().removeClass("active")
    })
</script>
</body>
</html>
