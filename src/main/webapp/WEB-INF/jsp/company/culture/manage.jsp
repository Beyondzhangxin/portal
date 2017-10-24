<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
<%@ include file="/WEB-INF/jsp/head.jsp" %>
<title>公司概况管理-企业文化</title>
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
            	<div class="main-right-title">
                	您当前的位置：公司概况管理  >>  企业文化
                </div>
                
                <div class="main-right-con">
                 	<form:form modelAttribute="pageModel" id="listForm" name="listForm" action="${rootUrl }company/culture/manage" method="get">	
                		<input type="hidden" id="pageNo" name="pageNo" value="1">
                    </form:form>
	                    <div class="main-right-form">
	                        <a class="add-a" href="${rootUrl }company/culture/add">新增</a>
	                    </div>
                    <div class="main-right-table-con">
                    	<table class="main-right-table">
                        	<thead>
                            	<tr>
                                	<th style="width:5%;"><input type="checkbox" onClick="javascript:selectAll(this)"/></th>
                                    <th style="width:5%;">序号</th>
                                    <th style="width:15%;">标题</th>
<!--                                     <th>内容</th> -->
                                    <th style="width:18%;">上传时间</th>
                                    <th style="width:6%;">状态</th>
                                    <th style="width:12%;">操作</th>
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
    <script type="text/javascript">
  	var jsonmsg=${json};
  	if(jsonmsg.message)	alert(jsonmsg.message);
 </script>
</body>
</html>
