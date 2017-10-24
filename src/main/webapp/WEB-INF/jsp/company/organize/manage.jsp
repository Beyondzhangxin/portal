<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
<%@ include file="/WEB-INF/jsp/head.jsp" %>
<title>公司概况管理-企业文化</title>
<script type="text/javascript">
function uploadFile(obj){
	//检验是否为图像文件  
    var file = document.getElementById("file_id").files[0];  
    if(!/image\/\w+/.test(file.type)){  
        alert("请选择图片！");  
        return false;  
    }  
    var reader = new FileReader();  
    //将文件以Data URL形式读入页面  
    reader.readAsDataURL(file);  
    reader.onload=function(e){  
    	var result=document.getElementById("result");  
    	result.innerHTML='<img style="max-width:100%" src="' + this.result +'" alt="" />';  
    }  
}
</script>
</head>

<body >
	<div class="container">
        <div class="Main">
            <div class="mainRight">
            	<div class="main-right-title">
                	您当前的位置：公司概况管理  >>  组织机构
                </div>
                 <div class="main-right-con">
                    <div class="main-right-table-con">
                    	<form action="${rootUrl }company/organize/uploadImage" enctype="multipart/form-data" method="post" >
	                    	<table class="culture-table-add">
	                          <tr>
	                            <th>上传图片：</th>
	                            <td style="position:relative">
	<!--                             	<input type="text" class="inputxt-2"/> -->
	<!-- 								<button class="edit-btn">浏览</button> -->
							<a href="javascript:;" class="fileBtn">选择文件
									<input type="file" name="file" id="file_id" onchange ="uploadFile(this)"/> 
							</a>
							<div class="main-footer-btn" style="display:inline-block">
								<input type="submit" class="edit-btn" value="提交"/>
	                        </div>
	                            </td>
	                          </tr>
	                          
	                          <tr>
	                            <td colspan="2">
									<div id="result"  style="text-align:center"><img style="max-width:100%" src="${rootUrl }${pathname}"/></div>
	                            </td>
	                          </tr>
	                        </table>
							<!-- <div class="main-footer-btn">
								<input type="submit" class="edit-btn" value="提交"/>
	                        </div> -->
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
  <script type="text/javascript">
  	var jsonmsg=${msgJson};
  	if(jsonmsg.message)	alert(jsonmsg.message);
  </script>
</body>
</html>
