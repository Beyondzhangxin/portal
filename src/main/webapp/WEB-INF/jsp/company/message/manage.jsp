<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
<%@ include file="/WEB-INF/jsp/head.jsp" %>
<title>客户留言管理</title>
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
	
function updStatus(url) {
	var url = url.getAttribute("data-href");
	$(function(){
		if(confirm("你确定要进行此操作吗？")){
			$.ajax({  
		        url : url,  
		        type : "POST",  
		        dataType: "json",
		        success : function(data) {  
		        	alert("操作成功");
		        	window.location.reload();
		        },  
		        error : function(data) {  
		        	alert("网络连接失败，请联系管理员");
		        }  
		   });  
		}
	});
};
</script>
</head>

<body >
	<div class="container">
        <div class="Main">
            <div class="mainRight">
            	<div class="main-right-title">
                	您当前的位置：客户留言管理
                </div>
                
                <div class="main-right-con">
					<form:form modelAttribute="pageModel" id="listForm" name="listForm" action="${rootUrl }company/message/manage" method="get">	
                		<input type="hidden" id="pageNo" name="pageNo">
                    </form:form>
                    <div class="main-right-table-con">
                    	<table class="main-right-table">
                        	<thead>
                            	<tr>
                                	<th style="width:3%;"><input type="checkbox" onClick="javascript:selectAll(this)"/></th>
                                    <th style="width:5%;">序号</th>
                                    <th style="width:10%;">留言账号</th>
                                    <th style="width:15%;">联系方式</th>
                                    <th style="width:15%;">留言时间</th>
                                    <th>留言内容</th>
                                    <th style="width:26%;">操作</th>
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
