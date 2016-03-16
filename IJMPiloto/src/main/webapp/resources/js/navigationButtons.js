$(document).ready(function() {
	$("#content").on("click", "[data-type='navigation']", function(e) {//for buttons that input data into the database
		//$('#content').load($(this).data('uri'));
		windows.location.href = $(this).data('uri');
	});
});