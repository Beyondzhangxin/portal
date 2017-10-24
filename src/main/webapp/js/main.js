// JavaScript Document
$(function () {
    $(".main-menu>li>a").click(function () {
        var $this = $(this);
        if ($this.siblings("ol").length > 0) {
            $this.parent("li").siblings("li").children("ol").slideUp("normal");
            $this.siblings("ol").slideToggle();
        }
    });
});

function selectAll(e) {
    if ($(e).prop("checked")) {
        $('tbody').find('input').prop("checked", true);
    } else {
        $('tbody').find('input').prop("checked", false);
    }
};

function getUrl(url) {
    var url = url.getAttribute("data-href");
    document.getElementsByTagName('iframe')[0].setAttribute("src", url);
};

/*
 * $form 表示表单的jquery对象
 * url 要保存的url地址
 * type 为保存成功后的动作，1标识弹出提示框并清空本页，2表示返回上一页，可以扩展*/
function add($form, url, type) {
    fmData = new FormData($form[0]);
    $.ajax({
        url: url,
        type: 'post',
        data: fmData,
        processData: false,
        contentType: false,
        dataType: 'json',
        success: function (data) {
            if (type = 1) {
                if (data.status == "0000000") {
                    alert("保存成功");
                    $form.find("input").val("");
                } else alert(data.message);
            }
            if (type = 2) {
                window.history.back();
            }
        },
        error: function (data) {
        }
    });

}

function openUrl(url, target, parameter) {
    $.ajax({
        url: url,
        type: "POST",
        success: function (data) {
            $('.' + target).html(data);
            /*公司新闻*/
            if (parameter == "news2") {
                $("#myTab li.messages_ac").addClass("active").siblings().removeClass("active");
                $("#ios").addClass("active").removeClass("fade").siblings().removeClass("active");
                eadio();
            }
            if (parameter == "news3") {
                $("#myTab li.dropdown").addClass("active").siblings().removeClass("active");
                $("#industry").addClass("active").removeClass("fade").siblings().removeClass("active");
                eadio();
            }
            $('.subType').val(parameter);
        },
        error: function (data) {
            if (data.status == 0) {
                var $iframe = $('<iframe/>', {
                    src: url,
                    style: 'display: none;'
                });
                $iframe.appendTo('body');
                $iframe.load(function () {
                    location.reload();
                });
            } else {
                alert("服务器内部错误，请与系统管理员联系。");
            }
        }
    });

};


function aiidc_del(url) {
    var url = url.getAttribute("data-href");
    $(function () {
        if (confirm("你确定要删除该记录吗？")) {
            $.ajax({
                url: url,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    alert("删除成功");
                    window.location.reload();
                },
                error: function (data) {
                    alert("网络连接失败，请联系管理员");
                }
            });
        }
    });
};

function cut(e, num) {
    $(e).each(function () {
        //var sub=$(this).attr("data-cut");
        var s = $(this).text().length;
        var str = $(this).html();
        if (s > num) {
            var strN = str.substring(0, num);
            strN += "...";
            $(this).html(strN);
        }
    });
}
function cutTime(e, num) {
    $(e).each(function () {
        //var sub=$(this).attr("data-cut");
        var s = $(this).text().length;
        var str = $(this).html();
        if (s > num) {
            var strN = str.substring(0, num);
            $(this).html(strN);
        }
    });
}

function delHtmlTag(str) {
    return str.replace(/<[^>]+>/g, "");//去掉所有的html标记
}