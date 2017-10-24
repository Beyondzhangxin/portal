<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/head.jsp" %>
    <title>人才招聘管理>>审核</title>
<script type="text/javascript">
function updStatus(url) {
	var url = url.getAttribute("data-href");
	$(function(){
		$.ajax({  
	        url : url,  
	        type : "POST",  
	        dataType: "text",
	        success : function(data) {  
	        	alert(data);
	        	window.location.href = "${rootUrl }company/recruit/audit/manage";
// 	        	window.location.reload();
	        },  
	        error : function(data) { 
	        	alert("网络连接失败，请联系管理员");
	        }  
	   });  
	});
};
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
                您当前的位置：人才招聘管理 >> 审核
            </div>
            <div class="main-right-con">
                <form:form modelAttribute="pageModel" id="addForm" action="" method="post">
                   <div class="main-right-table-con">
                       <table class="main-right-table-add">
                           <caption>职位简介</caption>
                           <tr>
                               <th>工作岗位</th>
                               <td><form:input type="text" class="inputxt" path="job" placeholder="必填"/> </td>
                               <th>职位职能</th>
                               <td><form:input type="text" class="inputxt" path="jobFunction" placeholder="必填" /></td>
                           </tr>
                           <tr>
                               <th>工作地点</th>
                               <td><form:input type="text" class="inputxt" path="workPlace" placeholder="必填" /></td>
                               <th>招聘人数</th>
                               <td><form:input type="text" class="inputxt" path="hireNumber" placeholder="必填" /></td>
                           </tr>
                           <tr>
                               <th>工作年限</th>
                               <td><form:input type="text" class="inputxt" path="workYear" placeholder="必填" /></td>
                               <th>语言要求</th>
                               <td><form:input type="text" class="inputxt" path="language" placeholder="必填" /></td>
                           </tr>
                           <tr>
                               <th>学历</th>
                               <td><form:input type="text" class="inputxt" path="record" placeholder="必填" /></td>
                               <th>薪水范围</th>
                               <td><form:input type="text" class="inputxt" path="salary" placeholder="必填" /></td>
                           </tr>
                       </table>
                       <table class="main-right-table-add">
                           <caption>职位简介</caption>
                           <tr>
                               <th>工作职责</th>
                               <td><form:textarea class="textxt" path="jobResponse"/></td>
                           </tr>
                       </table>
                       <table class="main-right-table-add">
                           <caption>任职条件</caption>
                           <tr>
                               <th>任职条件</th>
                               <td><form:textarea class="textxt" path="qualifications"/></td>
                           </tr>
                       </table>
                   </div>
                </form:form>
                <div class="main-footer-btn">
                    <div class="main-footer-btn">
						<a class="edit-a" data-href="${rootUrl }company/recruit/audit/updStatus/${pageModel.id}?status=1" onClick="javascript:updStatus(this)">通过</a>
						<a class="add-a" data-href="${rootUrl }company/recruit/audit/updStatus/${pageModel.id}?status=2" onClick="javascript:updStatus(this)">不通过</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>
