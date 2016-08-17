/*
 *  获取浏览器内核 
 **/
var browser={
    versions:function(){
    var u = window.navigator.userAgent;
    return {
        trident: u.indexOf('Trident') > -1, //IE内核
        presto: u.indexOf('Trident') > -1, //opera内核
        webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
        gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
        mobile: !!u.match(/AppleWebKit.*Mobile.*/)||!!u.match(/AppleWebKit/), //是否为移动终端
        ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
        android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
        iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者安卓QQ浏览器
        iPad: u.indexOf('iPad') > -1, //是否为iPad
        webApp: u.indexOf('Safari') == -1 ,//是否为web应用程序，没有头部与底部
        weixin: u.indexOf('MicroMessenger') > -1, //是否为微信浏览器
        alipay: u.indexOf('AlipayClient') > -1
        };
    }()
};

$(function () {
	'use strict';
	$(document).on("pageInit", "#page-ptr-tabs", function(e, id, page) {
	    $(page).find(".pull-to-refresh-content").on('refresh', function(e) {
	      // 2s timeout
	      var $this = $(this);
	      setTimeout(function() {

	        $this.find('.content-block').prepend("<p>New Content......</p>");
	        // Done
	        $.pullToRefreshDone($this);
	      }, 2000);
	    });
	    $(page).find(".infinite-scroll").on('infinite', function(e) {
	      // 2s timeout
	      var $this = $(this);
	      if($this.data("loading")) return;
	      $this.data("loading", 1);
	      setTimeout(function() {
	        $this.find('.content-block').append("<p>New Content......</p><p>New Content......</p><p>New Content......</p>");
	        $this.data("loading", 0);
	      }, 2000);
	    });
	  });
	$.init();
});

(function() {
	apms = {};
	apms.ajaxGet = function(url, data, success, error) {
        $.ajax({
            url: url,
            method: "GET",
//            processData: false,
            dataType : 'json',
            contentType : "application/json;charset=utf-8",
            data: data,
            success: function(resp) {
                try{
                    var res = resp.code;
                    if (res == 401) {
                    	$.alert("无权限访问！", "请求错误");
                    } else if (res == 400) {
                    	$.alert("请求参数有误！", "请求错误");
                    } else {
                        success(resp);
                    }
                }catch(e){
                    success(resp);
                }
                console.log(resp);
            },
            error: function(resp) {
                error(resp);
            }
        });
    };
	apms.ajaxSubmit = function(url, data, success, error) {
        $.ajax({
            url: url,
            method: "POST",
//            processData: false,
            dataType : 'json',
            contentType : "application/json;charset=utf-8",
            data: data,
            success: function(resp) {
                try{
                    var res = resp.code;
                    if (res == 401) {
                    	$.alert("无权限访问！", "请求错误");
                    } else if (res == 400) {
                    	$.alert("请求参数有误！", "请求错误");
                    } else {
                        success(resp);
                    }
                }catch(e){
                    success(resp);
                }
                console.log(resp);
            },
            error: function(resp) {
                error(resp);
            }
        });
    };
})();