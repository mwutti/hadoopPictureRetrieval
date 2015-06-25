$(document).ready(function() {

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
		addRemoveLinks : true,

		success : function(response, mostSimilar) {

			for(i = 0 ; i < mostSimilar.length; i++ ) {
				result = $('#most-similar-' + i);
				result.attr("src", "/getFile/?src=" + mostSimilar[i]);
			}

			$("#result").removeClass("hidden");

			$('html, body').animate({
				scrollTop: $("#result").offset().top
			}, 800, function(){

				// when done, add hash to url
				// (default click behaviour)
				window.location.hash = "#result";
			});
		},

		previewsContainer : ".dropzone-previews",

		// The setting up of the dropzone
		init : function() {
			var myDropzone = this;
			myDropzone.on("addedfile", function(file) {
				$("#pixsearch-image-upload").addClass("hidden");
			});

			// first set autoProcessQueue = false
			$('#upload-button').on("click", function(e) {
				myDropzone.processQueue();
			});

			// customizing the default progress bar
			this.on("uploadprogress", function(file, progress) {

				progress = parseFloat(progress).toFixed(0);

				var progressBar = file.previewElement.getElementsByClassName("dz-upload")[0];
				progressBar.innerHTML = progress + "%";
			});
		}
	};



});