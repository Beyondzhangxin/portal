<%--
  Created by IntelliJ IDEA.
  User: Zhangx
  Date: 2017/8/31
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/head.jsp" %>
    <title>航天科工智慧产业发展有限公司</title>
    <style>
        .icon-logout {
            background: url('../img/logout.png') no-repeat center center;
            left: 1250px;
            position: fixed;
            }
    </style>
</head>
<body>
   <div class="container">
       <%@ include file="/WEB-INF/jsp/header.jsp" %>
       <div class="main">
           <div class="main-left">
               <jsp:include page="/common/menuResource/${menu}"></jsp:include>
           </div>
           <div class="main-right">
               <iframe src="" frameborder="0" width="100%" height="100%">
               </iframe>
           </div>
       </div>
   </div>
</body>
</html>
