<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
<%@ include file="/WEB-INF/jsp/head.jsp" %>
<title>人才招聘管理>>详情</title>
<script type="text/javascript">
$(function(){
	$.ajaxSetup ({cache: false});
	$('#submit').click(function() {
		$('#updateForm').submit();
	});
});
</script>
</head>
<style>
    #box { margin: 50px auto; width: 740px; min-height: 150px; background: #eced24 }
</style>

<body>
<div class="container" >
    <div class="Main">
        <div class="mainRight" >
            <div class="main-right-title">
                您当前的位置：人才招聘管理 >> 详情
            </div>
            <div class="main-right-con">
                <form:form modelAttribute="pageModel" id="updateForm" action="${rootUrl}company/recruit/update" method="post">
                   <form:hidden path="id"/>
                   <div class="main-right-table-con">
                       <table class="main-right-table-add">
                           <caption>职位简介</caption>
                           <tr>
                               <th>工作岗位</th>
                               <td><form:input type="text" maxlength="32" class="inputxt" path="job" placeholder="必填"/> </td>
                               <th>职位职能</th>
                               <td><form:input type="text" maxlength="32" class="inputxt" path="jobFunction" placeholder="必填" /></td>
                           </tr>
                           <tr>
                               <th>工作地点</th>
                               <td><form:input type="text" maxlength="16" class="inputxt" path="workPlace" placeholder="必填" /></td>
                               <th>招聘人数</th>
                               <td><form:input type="text" maxlength="16" class="inputxt" path="hireNumber" placeholder="必填" /></td>
                           </tr>
                           <tr>
                               <th>工作年限</th>
                               <td><form:input type="text" maxlength="16" class="inputxt" path="workYear" placeholder="必填" /></td>
                               <th>语言要求</th>
                               <td><form:input type="text" maxlength="16"  class="inputxt" path="language" placeholder="必填" /></td>
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
                               <td><form:textarea class="textxt" maxlength="1000"  path="qualifications"/></td>
                           </tr>
                       </table>
                   </div>
                </form:form>
                <div class="main-footer-btn">
                	<c:if test="${pageModel.status != '1' }">
                    	<button class="edit-btn" id="submit">提交</button>
                    </c:if>
                    <a class="add-a" href="${rootUrl }company/recruit/manage" >返回</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>
