$(document).ready(function() {

	$(".file-dropzone").on('dragover', handleDragEnter);
	$(".file-dropzone").on('dragleave', handleDragLeave);
	$(".file-dropzone").on('drop', handleDragLeave);

	function handleDragEnter(e) {

		this.classList.add('drag-over');
	}

	function handleDragLeave(e) {

		this.classList.remove('drag-over');
	}

	// "dropzoneForm" is the camel-case version of the form id "dropzone-form"
	Dropzone.options.imageUpload = {

		url : "upload",
		autoProcessQueue : false,
		uploadMultiple : false,
		maxFilesize : 256, // MB
		addRemoveLinks : true,

		success : function(response, mostSimilar) {
			var result=$('#result');

			for(i = 0 ; i < mostSimilar.length; i++ ) {
				result.append("<img id=\"img"+ i +"\">");
				$('#img' + i).attr("src", "/getFile/?src=" + mostSimilar[i]);
			}


			//mostSimilar.forEach(function(status) {
			//	console.log(status);
			//});
		},

		previewsContainer : ".dropzone-previews",

		// The setting up of the dropzone
		init : function() {
			var myDropzone = this;

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