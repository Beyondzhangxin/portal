<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<style>
.active_ol{background:#00a6a6}
::-webkit-scrollbar { width: 12px;}
::-webkit-scrollbar-track {-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);border-radius: 10px;}
::-webkit-scrollbar-thumb { border-radius: 10px;  background: rgba(0,0,0,0.7);-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.5);}
	</style>
	<div class="main-left-top">
    	<a data-href="../company/usermanage/loginUser" onclick="javascript:getUrl(this)"><img src="${rootUrl }${picUrl }" style="max-width:140px;max-height:140px;border-radius:50%;" /></a>
        <div class="main-left-top-user">
        	<P><span>${loginUser.name }</span></P>
            <p>欢迎登录!</p>
        </div>
    </div>
    <div style="width: 363px;height: 400px;overflow: hidden;">
    <div style="width: 373px;height: 400px;overflow-y: auto;">
    <ul class="main-menu" id="main-menu" style="height:410px">
    	<c:forEach items="${AclResource }" var="vo" varStatus="var">
    		<li id="cu" class="parent_li">
    			<a class="currentUrl" <c:if test="${vo.childResource eq null }"> data-href="${rootUrl }${vo.url }" onClick="javascript:getUrl(this)" </c:if> >
    			<img src="${rootUrl }img/${vo.logoPic }"/>${vo.name }</a>
    			<c:if test="${vo.childResource != null  }">
     			<ol class="main-menu-child">
     			<c:forEach items="${vo.childResource }" var="child">
     				<li id="cu" class="ol_li">
     				<a class="currentUrl" data-href="${rootUrl }${child.url }"onClick="javascript:getUrl(this)">${child.name }</a>
     				</li>
     			</c:forEach>
     			</ol>
    			</c:if>
    		</li>
    	</c:forEach>
    </ul>
    </div>
    </div>
<script>
	$(".parent_li").eq(0).addClass("active").siblings().removeClass("active");
	$(".parent_li").click(function(){
		if($(this).index()==0|| $(this).index() == 2|| $(this).index() == 3){
			$(".parent_li").removeClass("active")
		}else {
			$(this).addClass("active").siblings().removeClass("active");
			$('.main-menu-child').css("display","none")
		}
		$(".ol_li").removeClass("active_ol");
	})
	$(".ol_li").click(function(){
		$(this).addClass("active").siblings().removeClass("active");
		
	})
</script>