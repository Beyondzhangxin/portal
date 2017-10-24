$(function(){
	$(".person_type_input").click(function(){
		$(".hidden").slideToggle(300);
		$(".hidden li").click(function(){
			var person_type_input=$(this).text();
			$(".person_type_input tt").text(person_type_input);
		});
	});
	var imgSrc=$(".upload_img>img");
	imgSrc.attr('src')==''?imgSrc.css('display','none'):undefined;
});
// 关闭
function closeModel(e){$(e).css('display','none');}
// 显示
function addModel(e){$(e).css('display','block');}
// 重置
function reset(e){$(e).val('');}
// 删除
var notFirstLi = $(".family_ul li").not( $(".family_ul_title")[0] );
$(notFirstLi).click(function(){
    $(this).toggleClass('family_ul_click');
})
$('.delect_submit').click(function(){
    if( $(notFirstLi).hasClass('family_ul_click')){
        $('.family_ul li').remove('.family_ul_click');
        $('.delect_model').hide();
    }else{
        $('.delect_model').hide();
    }
})
function viewImage(obj, imgPreviewId, divPreviewId) {
    var allowExtention = ".jpg,.bmp,.gif,.png,.jpeg"; 
    var extention = obj.value.substring(obj.value.lastIndexOf(".") + 1).toLowerCase();
    var browserVersion = window.navigator.userAgent.toUpperCase(); 
    if (allowExtention.indexOf(extention) > -1) { 
    	document.getElementById(imgPreviewId).style.display='block';
        if (browserVersion.indexOf("MSIE") > -1) {
            if (browserVersion.indexOf("MSIE 6.0") > -1) {
                document.getElementById(imgPreviewId).setAttribute("src", obj.value);
            } else {
                obj.select();
                var newPreview = document.getElementById(divPreviewId + "New");
                if (newPreview == null) {
                    newPreview = document.createElement("div");
                    newPreview.setAttribute("id", divPreviewId + "New");
                    newPreview.style.width = 160;
                    newPreview.style.height = 170;
                    newPreview.style.border = "solid 1px #d2e2e2";
                }
                newPreview.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src='" + document.selection.createRange().text + "')";
                var tempDivPreview = document.getElementById(divPreviewId);
                tempDivPreview.parentNode.insertBefore(newPreview, tempDivPreview);
                tempDivPreview.style.display = "none";
            }
        } else if (browserVersion.indexOf("FIREFOX") > -1) {
            var firefoxVersion = parseFloat(browserVersion.toLowerCase().match(/firefox\/([\d.]+)/)[1]);
            console.log('火狐下='+firefoxVersion);
            if (firefoxVersion < 7) {
                document.getElementById(imgPreviewId).setAttribute("src", obj.files[0].getAsDataURL());
            } else {
            	console.log(window.URL.createObjectURL);
            	console.log(obj.files[0]);
                document.getElementById(imgPreviewId).setAttribute("src", window.URL.createObjectURL(obj.files[0]));
            }
        } else if (obj.files) {                
            if (typeof FileReader !== "undefined") {
                var reader = new FileReader();
                reader.onload = function (e) {
                    document.getElementById(imgPreviewId).setAttribute("src", e.target.result);
                }
                reader.readAsDataURL(obj.files[0]);
            } else if (browserVersion.indexOf("SAFARI") > -1) {
                alert("暂时不支持Safari浏览器!");
            }
        } else {
            document.getElementById(divPreviewId).setAttribute("src", obj.value);
        }
    } else {
        alert("仅支持" + allowSuffix + "为后缀名的文件!");
        obj.value = "";
        if (browserVersion.indexOf("MSIE") > -1) {
            obj.select();
            document.selection.clear();
        }
        obj.outerHTML = obj.outerHTML;
    }
}