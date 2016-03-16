$(document).ready(function() {
	$("#form-supplier").on("click", "[data-type='add']", function(e) {
		console.log("boton presionado");
		var supplier={"code":"J131111454", "name":"PRUEBAJSON4 C.A"};
		$.ajax({
			contentType: "application/json",
	        type: "POST",
	        url: "../supplier",
	        data: JSON.stringify(supplier),
	        success: function(data, textStatus, xhr) {
		        console.log(xhr.status);
		    },
	        error: function (result) {
	        	console.log("error");
	        }
		});
	});
	$("#list-supplier").on("click", "[data-type='delete']", function(e) {
		console.log($(this).data("id"));
		$.ajax({
		    url: '../supplier/'+$(this).data("id"),
		    type: 'DELETE',
		    success: function(data, textStatus, xhr) {
		        console.log(xhr.status);
		    }
		});
	});
});