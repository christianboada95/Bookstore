
$(function() {
	
	getAllOrders(data => {
		console.log(data);
		for (const order of data) {
            addOrder(order);
        }  
	});
  
});

function addOrder(order) {

    var orders = $("#orders");

    orders.append(                                        
        '<div class="col-12 mb-3 animated fadeInUp">' +                    
            '<div class="card">' +
                '<h5 class="card-header">Orden #' + order.code + '</h5>' +
                '<div class="card-body">' +
                    '<h5 class="card-title">Facturado a ' + order.client.name +' '+ order.client.surname + '</h5>' +
                    '<p class="card-text">Envio '+ order.shipping +' a ' + order.client.address + ' en '+ order.client.city+', '+ order.client.department +'</p>' +
                '</div>' +
            '</div>' +
        '</div>' 
    );
}
