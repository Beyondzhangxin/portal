<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
<%@ include file="/WEB-INF/jsp/head.jsp" %>
<title>用户管理</title>
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
</script>
</head>
<body >
	<div class="container">
        <div class="Main">
            <div class="mainRight">
            	<div class="main-right-title">您当前的位置：用户管理</div>
                
                <div class="main-right-con">
                	<form:form modelAttribute="pageModel" id="listForm" name="listForm" action="${rootUrl }company/usermanage/list" method="post">	
                		<input type="hidden" id="pageNo" name="pageNo">
                    </form:form>
                    
                    <div class="main-right-form">
                    	<a class="add-a" href="${rootUrl }company/usermanage/addUserPage">新增</a>
                   </div>

                    <div class="main-right-table-con">
                    	<table class="main-right-table">
                        	<thead>
                            	<tr>
                                	<th style="width:5%;"><input type="checkbox" onClick="javascript:selectAll(this)"/></th>
                                    <th style="width:5%;">序号</th>
                                    <th style="width:15%;">姓名</th>
                                    <th style="width:10%;">性别</th>
                                    <th style="width:22%;">移动电话</th>
                                    <th style="width:23%;">电子邮件</th>
                                    <th style="width:20%;">操作</th>
                                </tr>
                            </thead>
                            <tbody>
	                            <c:forEach items="${pagn.result }" var="vo" varStatus="voStatus">
									<%@ include file="row.jsp"%>
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
