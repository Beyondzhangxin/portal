<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
<%@ include file="/WEB-INF/jsp/head.jsp" %>
<title>解决方案管理</title>
<link rel="stylesheet" type="text/css" href="${rootUrl }css/message.css">
<script type="text/javascript" src="${rootUrl }js/pic/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${rootUrl }js/pic/message.js"></script>
<script type="text/javascript">
$(function(){
	$.ajaxSetup ({cache: false});
	$('#submit').click(function() {
		$('#addForm').submit();
	});
});
</script>
</head>
<body >
	<div class="container">
        <div class="Main">
            <div class="mainRight">
            	<div class="main-right-title">
                	您当前的位置：公司解决方案管理  >>  简述
                </div>
                <div class="main-right-con">
                    <form:form modelAttribute="pageModel" id="addForm" action="${rootUrl}company/solution/overview" method="post">
	                    <form:hidden path="id"/>

	                    <div class="main-right-table-con">
	                    	<table class="main-right-table-add">
	                          <tr>
	                          	<th colspan="3"><b class="must">*</b>方案简述</th>
	                          </tr>
	                          <tr>
	                          	<td colspan="50">
	                        		<form:textarea path="content" id="myEditor" style="width:100%;" class="content"/>
	                          	</td>
                        	  </tr>
<!-- 	                          <tr> -->
<!-- 	                            <th>概况</th> -->
<%-- 	                            <td colspan="3"><form:textarea class="textxt" path="content"/></td> --%>
<!-- 	                          </tr> -->
	                        </table>
	                    </div>
                    </form:form>
					<div class="main-footer-btn">
                       	<button class="edit-btn" id="submit">提交</button>
                    </div>
               	 </div>
<!--                 <div class="main-right-con"> -->
<!--                     <div class="main-right-table-con detail-con"> -->
<!--                     	<h1 class="detail-title">公司概况</h1> -->
<!--                         <span class="detail-titme">上传时间:2017-07-31</span> -->
<%--                         <img src="${rootUrl }img/pic-4.jpg"/> --%>
<!-- 						<p class="detail-content"> -->
<!-- 							过圣诞节和的首付款广化寺合伙人供货商分开了打工皇帝发了个客户身份戴开来规划时风格三贷后管理快速搭建花果山的交付给好可怜时间规划过圣诞节和的首付款广化寺合伙人供货商分开了打工皇帝发了个客户身份戴开来规划时风格三贷后管理快速搭建花果山的交付给好可怜时间规划过圣诞节和的首付款广化寺合伙人供货商分开了打工皇帝发了个客户身份戴开来规划时风格三贷后管理快速搭建花果山的交付给好可怜时间规划过圣诞节和的首付款广化寺合伙人供货商分开了打工皇帝发了个客户身份戴开来规划时风格三贷后管理快速搭建花果山的交付给好可怜时间规划过圣诞节和的首付款广化寺合伙人供货商分开了打工皇帝发了个客户身份戴开来规划时风格三贷后管理快速搭建花果山的交付给好可怜时间规划过圣诞节和的首付款广化寺合伙人供货商分开了打工皇帝发了个客户身份戴开来规划时风格三贷后管理快速搭建花果山的交付给好可怜时间规划过圣诞节和的首付款广化寺合伙人供货商分开了打工皇帝发了个客户身份戴开来规划时风格三贷后管理快速搭建花果山的交付给好可怜时间规划过圣诞节和的首付款广化寺合伙人供货商分开了打工皇帝发了个客户身份戴开来规划时风格三贷后管理快速搭建花果山的交付给好可怜时间规划广化寺合伙人供货商分开了打工皇帝发了个客户身份戴开来规划时风格三贷后管理快速搭建花果山的交付给好可怜时间规划过圣诞节和的首付款广化寺合伙人供货商分开了打工皇帝发了个客户身份戴开来规划时风格三贷后管理快速搭建花果山的交付给好可怜时间规划过圣诞节和的首付款广化寺合伙人供货商分开了打工皇帝发了个客户身份戴开来规划时风格三贷后管理快速搭建花果山的交付给好可怜时间规划过圣诞节和的首付款广化寺合伙人供货商分开了打工皇帝发了个客户身份戴开来规划时风格三贷后管理快速搭建花果山的交付给好可怜时间规划过圣诞节和的首付款广化寺合伙人供货商分开了打工皇帝发了个客户身份戴开来规划时风格三贷后管理快速搭建花果山的交付给好可怜时间规划。 -->
<!-- 						</p> -->
<!--                     </div> -->
<!--                 </div> -->
            </div>
        </div>
    </div>
<script type="text/javascript">
	var jsonmsg=${json};
	if(jsonmsg.message)	alert(jsonmsg.message);
</script>
</body>
</html>
