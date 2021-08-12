/** Order Obj*/
class Order {
	constructor(client, shipping, details) {
		this.client = client;
		//this.date = date;
		//this.total = total;
		this.shipping = shipping
		this.details = details;
	}
}
class OrderDetail {
	constructor(book, quantity) {
		this.book = book;
		this.quantity = quantity;
	}
}

function getAllOrders(callback) {
	
	$.ajax("api/orders", {
		type: "GET",
		contentType: "application/json",
		headers: {"Authorization": sessionStorage.getItem('token')},
		success: function(data) {
			var orders = JSON.parse(JSON.stringify(data));
			callback(orders);
		},
		error: function(request,error) {
            alert('An error occurred attempting to get all books');
            // console.log(request, error);
        }	
	});
}

function getOrder(id, callback) {
	
	var url = window.location.origin + "/api/orders/" + id ; 

	$.ajax(url, {
        type : 'GET',
        contentType: "application/json",
        headers: {"Authorization": sessionStorage.getItem('token')},
        success: function(data) { 
            var p = JSON.parse(JSON.stringify(data));
			callback(p);
        },
        error: function(err) {
			callback(err);
            //console.log("No se ha podido obtener la información");
        }
    });
}

function setOrder(order, callback) {
	
	$.ajax('api/orders', {
		type: "POST",
		contentType: "application/json",
		headers: {"Authorization": sessionStorage.getItem('token')},
		data: JSON.stringify(order),
		success: function(data) {
			var o = JSON.parse(JSON.stringify(data));
			//callback(o);
			callback(o);
		},
		error: function(request,error) {
            alert('An error occurred attempting to set a order');
            // console.log(request, error);
        }	
	})
}

function getParameterByName(name) {
	name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	results = regex.exec(location.search);
	return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

/** Testing */
$(function(){
	
})