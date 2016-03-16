function getInput(form)
{
	var object = {};
	form.find('[name]').each(function(index,value)
	{
		var attribute = $(this);
		var name = attribute.attr('name');
		var value = attribute.val();
		object[name] = value;
	});
	return object;
}
function saveRequest(object, request, uri)
{
	$.ajax({
		contentType: "application/json",
        type: request,
        url: uri,
        data: JSON.stringify(object),
        success: function(data, textStatus, xhr) {
	        console.log(xhr.status);
	    },
        error: function (result) {
        	console.log("error");
        }
	});
}
function deleteRequest(uri,id)
{
	$.ajax({
	    url: uri+"/"+id,
	    type: 'DELETE',
	    success: function(data, textStatus, xhr) {
	        console.log(xhr.status);
	    }
	});
}
function getUri(entity)
{
	uri="";
	switch(entity)
	{
		case "supplier": uri="../supplier";break;
		case "product": uri="../product";break;
	}
	return uri;
}
$(document).ready(function() {
	$("#content").on("click", "[data-type='input']", function(e) {//for buttons that input data into the database
		
		var entity = $('#form').data('entity');
		
		var requestType = $(this).data('request');
		
		var object = getInput($('#form'));
		
		var uri = getUri(entity);

		saveRequest(object,requestType,uri);
	});
	
	$("#content").on("click", "[data-type='delete']", function(e) {//for buttons that delete data from the database
		
		var entity = $('#list').data('entity');
		
		var id = $(this).data('id');
		
		var uri = getUri(entity);
		
		deleteRequest(uri,id);
	});
	
	$("#content").on("click", "[data-type='output']", function(e) {//for buttons that delete data from the database
		
		var entity = $('#list').data('entity');
		
		var id = $(this).data('id');
		
		var uri = getUri(entity);
		
		deleteRequest(uri,id);
	});
});
