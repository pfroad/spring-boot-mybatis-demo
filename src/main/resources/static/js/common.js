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