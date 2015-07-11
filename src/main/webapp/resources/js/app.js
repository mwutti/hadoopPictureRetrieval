$(document).ready(function() {
	var t;
	var waitingMessages = ["Calculate infinity ", "Approximate pi "]
	$(".file-dropzone").on('dragover', handleDragEnter);
	$(".file-dropzone").on('dragleave', handleDragLeave);
	$(".file-dropzone").on('drop', handleDragLeave);

	function handleDragEnter(e) {
		this.classList.add('drag-over');
		console.log("dragEnter");
	}

	function handleDragLeave(e) {
		this.classList.remove('drag-over');
		console.log("dragLeave");
	}

	Dropzone.options.imageUpload = {
		url : "upload",
		autoProcessQueue : false,
		uploadMultiple : false,
		maxFilesize : 2, // MB
		addRemoveLinks : false,

		success : function(response, mostSimilar) {

			for(i = 0 ; i < mostSimilar.length; i++ ) {
				result = $('#most-similar-' + i);
				result.attr("src", "/getFile/?src=" + mostSimilar[i]);
			}

			$("#result").removeClass("hidden");
			$('#waiting-text').addClass("hidden");

			$('html, body').animate({
				scrollTop: $("#result").offset().top
			}, 800, function(){

				// when done, add hash to url
				// (default click behaviour)
				window.location.hash = "#result";
			});
			clearInterval(t);
		},

		previewsContainer : ".dropzone-previews",

		// The setting up of the dropzone
		init : function() {
			var myDropzone = this;
			processing = true;
			myDropzone.on("addedfile", function(file) {
				$("#pixsearch-image-upload").addClass("hidden");
				$(".dz-progress").remove();

			});

			$('#upload-button').on("click", function(e) {
				myDropzone.processQueue();
				$('#upload-button').addClass("hidden");

				setWaitText("Initialize the awesome ");

				t = setInterval(function() {
					randomNr = Math.floor(Math.random() * waitingMessages.length)
					setWaitText(waitingMessages[randomNr]);
				},5000);
			});

		}
	};
});

function setWaitText(text) {
	$("#waiting-text").Loadingdotdotdot({
		"speed": 600,
		"maxDots": 3,
		"word": text
	});
}


(function($) {

	$.Loadingdotdotdot = function(el, options) {

		var base = this;

		base.$el = $(el);

		base.$el.data("Loadingdotdotdot", base);

		base.dotItUp = function($element, maxDots) {
			if ($element.text().length == maxDots) {
				$element.text("");
			} else {
				$element.append(".");
			}
		};

		base.stopInterval = function() {
			clearInterval(base.theInterval);
		};

		base.init = function() {

			if ( typeof( speed ) === "undefined" || speed === null ) speed = 300;
			if ( typeof( maxDots ) === "undefined" || maxDots === null ) maxDots = 3;

			base.speed = speed;
			base.maxDots = maxDots;

			base.options = $.extend({},$.Loadingdotdotdot.defaultOptions, options);

			base.$el.html("<span>" + base.options.word + "<em></em></span>");

			base.$dots = base.$el.find("em");
			base.$loadingText = base.$el.find("span");

			base.$el.css("position", "relative");
			base.$loadingText.css({
				"position": "absolute",
				"top": (base.$el.outerHeight() / 2) - (base.$loadingText.outerHeight() / 2),
				"left": (base.$el.width() / 2) - (base.$loadingText.width() / 2)
			});

			base.theInterval = setInterval(base.dotItUp, base.options.speed, base.$dots, base.options.maxDots);

		};

		base.init();

	};

	$.Loadingdotdotdot.defaultOptions = {
		speed: 300,
		maxDots: 3,
		word: "Loading"
	};

	$.fn.Loadingdotdotdot = function(options) {

		if (typeof(options) == "string") {
			var safeGuard = $(this).data('Loadingdotdotdot');
			if (safeGuard) {
				safeGuard.stopInterval();
			}
		} else {
			return this.each(function(){
				(new $.Loadingdotdotdot(this, options));
			});
		}

	};

})(jQuery);