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
    <title>人才招聘管理>>新增</title>
</head>
<style>
    #box { margin: 50px auto; width: 740px; min-height: 150px; background: #eced24 }
</style>
<script type="text/javascript">
    $(function () {
        $.ajaxSetup({cache: false});
        $('#submit').click(function () {
            $('#addForm').submit();
        });
    });

</script>
<body>
<div class="container" >
    <div class="Main">
        <div class="mainRight">
            <div class="main-right-title">
                您当前的位置：人才招聘管理 >> 新增
            </div>
            <div class="main-right-con">
                <form:form modelAttribute="pageModel" id="addForm" action="${rootUrl}company/recruit/add" method="post">
                   <div class="main-right-table-con">
                       <table class="main-right-table-add">
                           <caption>职位简介</caption>
                           <tr>
                               <th>工作岗位</th>
                               <td><form:input type="text" maxlength="16" class="inputxt" path="job"  placeholder="必填"/> </td>
                               <th>职位职能</th>
                               <td><form:input type="text" class="inputxt" maxlength="16" path="jobFunction" placeholder="必填" /></td>
                           </tr>
                           <tr>
                               <th>工作地点</th>
                               <td><form:input type="text" class="inputxt" path="workPlace" maxlength="16" placeholder="必填" /></td>
                               <th>招聘人数</th>
                               <td><form:input type="text" maxlength="16" class="inputxt" path="hireNumber" placeholder="必填" /></td>
                           </tr>
                           <tr>
                               <th>工作年限</th>
                               <td><form:input type="text" maxlength="16" class="inputxt" path="workYear" placeholder="必填" /></td>
                               <th>语言要求</th>
                               <td><form:input type="text" maxlength="16" class="inputxt" path="language" placeholder="必填" /></td>
                           </tr>
                           <tr>
                               <th>学历</th>
                               <td><form:input type="text" maxlength="16" class="inputxt" path="record" placeholder="必填" /></td>
                               <th>薪水范围</th>
                               <td><form:input type="text" maxlength="16" class="inputxt" path="salary" placeholder="必填" /></td>
                           </tr>
                           <tr>
                               <th>邮件发送地址</th>
                               <td><form:input type="text" maxlength="16" class="inputxt" path="toEmail"
                                               placeholder="必填" /></td>
                               <th>邮件标题备注</th>
                               <td><form:input type="text" maxlength="16" class="inputxt" path="remark"
                               /></td>
                           </tr>
                       </table>
                       <table class="main-right-table-add">
                           <caption>职位简介</caption>
                           <tr>
                               <th>工作职责</th>
                               <td><form:textarea class="textxt" maxlength="1000" path="jobResponse"/></td>
                           </tr>
                       </table>
                       <table class="main-right-table-add">
                           <caption>任职条件</caption>
                           <tr>
                               <th>任职条件</th>
                               <td><form:textarea maxlength="1000" class="textxt" path="qualifications"/></td>
                           </tr>
                       </table>
                   </div>
                </form:form>
                <div class="main-footer-btn">
                    <button class="edit-btn" id="submit" >提交</button>
                    <a class="add-a" href="${rootUrl }company/recruit/manage" >返回</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var jsonmsg =${json};
    if (jsonmsg.message) alert(jsonmsg.message);
</script>
</html>
