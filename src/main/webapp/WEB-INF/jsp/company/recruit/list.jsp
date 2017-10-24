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
<title>人才招聘管理</title>
<script type="text/javascript">
	$(function(){
		var total = ${pagn.totalCount};
		var pageSize = ${pagn.pageSize};
		var currentPage = ${pagn.currentPage};
		$('.M-box3').pagination({
			totalData:total,
			showData:pageSize,
			current:currentPage,
			jump:true,
			coping:true,
			homePage:'首页',
			endPage:'末页',
			prevContent:'上页',
			nextContent:'下页',
			callback:function(api){
				var pageNo = $("#pageNo").val(api.getCurrent());
				$('#listForm').submit();
			}
       	});	
	})
	
	function deleteAll() {
	var flag = false;
	var j = 0;
	var ids ="";
	var checkboxes = $(".checkboxes");
	for (i = 0; i < checkboxes.length; i++) {
	    if (j >0) {
			ids = ids+",";
		}
	    if (checkboxes[i].checked) {
	    	ids =ids+checkboxes[i].value;
	        flag = true;
	        j++;
	    }
	}
	if (flag) {
		if (confirm("你确认要删除所选的这"+j+"项？")) {
	     $.ajax({
	         url: "${rootUrl}company/recruit/deleteAll",
	         type: "POST",
	         data: {ids:ids},
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
	
	function isTop(url) {
		var url = url.getAttribute("data-href");
		$(function(){
			$.ajax({  
		        url : url,  
		        type : "POST",  
		        dataType: "text",
		        success : function(data) {  
		        	alert(data);
		        	$('#listForm').submit();
		        },  
		        error : function(data) { 
		        	alert("网络连接失败，请联系管理员");
		        }  
		   });  
		});
	};
</script>
</head>

<body>
<div class="container" >
    <div class="Main">
        <div class="mainRight">
            <div class="main-right-title">
                您当前的位置：人才招聘管理
            </div>
            <div class="main-right-con">
           		 <form:form modelAttribute="pageModel" id="listForm" name="listForm" action="${rootUrl }company/recruit/manage" method="get">	
                	<input type="hidden" id="pageNo" name="pageNo" value="1">
                 </form:form>
                <div class="main-right-form">
                    <a class="add-a" href="${rootUrl}company/recruit/add">新增</a>
                    <a class="add-a" href="javascript:deleteAll()">删除</a>
                </div>
                <div class="main-right-table-con">
                    <table class="main-right-table">
                        <thead>
                        <tr>
                            <th style="width:5%;"><input type="checkbox"  onClick="javascript:selectAll(this)"/></th>
                            <th style="width:5%;">序号</th>
                            <th style="width:15%;">工作岗位</th>
                            <th >职位职能</th>
                            <th >工作地点</th>
                            <th >招聘人数</th>
                            <th >工作年限</th>
                            <th >学历</th>
                            <th >状态</th>
                            <th style="width:18%;">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="index" value="0"/>
                        <c:forEach var="recruit" items="${pagn.result}">
                            <c:set var="index" value="${index+1}"></c:set>
                            <tr>
                                <td><input type="checkbox" class="checkboxes" name="ids" value="${recruit.id }"></td>
                                <td>${index}</td>
                                <td>${recruit.job}</td>
                                <td>${recruit.jobFunction}</td>
                                <td>${recruit.workPlace}</td>
                                <td>${recruit.hireNumber}</td>
                                <td>${recruit.workYear}</td>
                                <td>${recruit.record}</td>
                                <td>
                                	<c:if test="${recruit.status == '0'}">新建</c:if>
                                	<c:if test="${recruit.status == '1'}"><font color="blue">通过</font></c:if>
                                	<c:if test="${recruit.status == '2'}"><font color="red">未通过</font></c:if>
                                </td>
                                <td>
                                	<c:if test="${recruit.isTop =='0' }">
	                                	<a class="edit-a" data-href="${rootUrl}company/recruit/isTop/${recruit.id}?isTop=1" onClick="javascript:isTop(this)">置顶</a>
                                	</c:if>
                                	<c:if test="${recruit.isTop =='1' }">
	                                	<a class="edit-a" data-href="${rootUrl}company/recruit/isTop/${recruit.id}?isTop=0" onClick="javascript:isTop(this)">不置顶</a>
                                	</c:if>
                                	<c:if test="${recruit.status !='1' }">
                                		<a class="edit-a" href="${rootUrl}company/recruit/detail/${recruit.id}">修改</a>
                                	</c:if>
                                	<c:if test="${recruit.status =='1' }">
                                		<a class="edit-a" href="${rootUrl}company/recruit/detail/${recruit.id}">详情</a>
                                	</c:if>
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
<script type="text/javascript">
  	var jsonmsg=${json};
  	if(jsonmsg.message)	alert(jsonmsg.message);
</script>
</body>
</html>
