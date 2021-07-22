/** Cart obj */
class shoppingCart {
	constructor() {
		// New car
		this.total = 0;
		this.details = new Array();
	}
	
	isProduct(pd) {
		// validar si el producto ya está en la lista
		var detail = this.details.find( item => item.book.id == pd.book.id);
		
		if(detail != null) {
			return true;
		} else {
			return false;
		}
	}
	
	addProductDetail(pd) {
		
		// validar si el producto ya está en la lista
		var detail = this.details.find( item => item.book.id == pd.book.id);
		// Si está, buscarlo y sumarle quantity no más
		if(detail != null) {
			//detail.quantity += pd.quantity;
			detail.quantity = pd.quantity;
		// Sino, añadirlo a la lista
		} else {
			this.details.push(pd);
		}
		
		this.updateTotal();
	}
	
	removeProductDetail(pd) {
		
		var detail = this.details.find( item => item.book.id == pd.book.id);
		var index = this.details.indexOf(detail);
		if(detail != null) {
			this.details.splice(index, 1);
		} else {
			console.log("Producto no esta en el carrito");
		}
	}
	
	updateTotal() {
		if(this.details.length > 0) {
			this.details.forEach(d => {
				this.total += d.book.price * d.quantity;
			})
		} else {
			this.total = 0;
		}
		
		// Actualizar el DOM
		$('#total').html('<strong>Total: $'+ this.total +'</strong>');
	}
	
	buyOrder(client, shipping) {
		var order = new Order(
				client,
				shipping,
				this.details
		);
		console.log(order);
		setOrder(order, (res) => {
			console.log(res);
			alert("Compra realizada con éxito");
			this.remove();
		})
	}
	
	load() {
		
		var carData = JSON.parse(localStorage.getItem("car"));
		
		if (carData === null)
			console.log("car does not exist");
		else {
			this.total = carData.total;
			this.details = carData.details;
		}
	}
	
	save() {
		localStorage.setItem("car", JSON.stringify(this));
	}
	
	remove() {
		if (localStorage.getItem("car") === null)
			console.log("car does not exist");
		else
			localStorage.removeItem("car");
		
		this.total = null;
		this.details = null;
	}
}

function addProductsSales(product, q) {
    $( '.table-products-sales' ).prepend( 
    '<tr class="item animated fadeIn delay-1s" id="item-'+ product.id +'">' +
        '<td width="20%" class="nombres">'+ product.title +'</td>' +
        '<td width="45%">'+ product.description +' </td>' +
        '<td class="text-center">'+ product.stock +'</td>' +
        '<td >' +
            '<input type="number" class="form-control" id="item-cantidad-'+ product.id +'" value="'+q+'" >' +
        '</td>' +
        '<td width="10%" class=""><strong>$'+ product.price +'</strong></td>' +
        '<td class="text-center">' +
            '<button type="submit" class="btn btn-primary btn-block" onClick="addOrder(' + product.id + ')" >Agregar</button>' +
        '</td>' +
    '</tr>'
    );
}

function showCart() {
	carrito.details.forEach(detail => {
		addProductsSales(detail.book, detail.quantity);
	})
	
	carrito.updateTotal();
}

function addOrder(id) {
	//alert(id);
	var cant = $("#item-cantidad-"+ id).val();
	console.log(cant);
	getBook(id, (book) => {
		

		if (cant > book.stock) {
			//alert("no se puede agregar más de este libro");
			Swal.fire({
				type: 'warning',
				title: 'No se puede agregar más de este libro',                    
			  })
		} else {
			
			var pd = new OrderDetail(book, cant);
			carrito.addProductDetail(pd);
			carrito.save();
			//alert("agregado")
			Swal.fire({
				type: 'warning',
				title: 'Libro agregado correctamente',                    
			})
		}
		
	})
}

function getOrder() {
	
	var ship = $("#ship").val();
	currentUser(user => {
		carrito.buyOrder(user, ship);
	})
}

/** Testing */
var carrito = new shoppingCart();					// genero un carrito nuevo

$(function(){
	
	// Carga el carrito guardado en el local
	carrito.load();
	
	/*var libreria = new Array();		// arreglo de libros
	getAllBooks((books) => {
		//console.log(books);
		libreria = books;
		
		var pd = new OrderDetail(libreria[0], 2);	// genero un OrderDetail
		var pd2 = new OrderDetail(libreria[3], 3);
		var pd3 = new OrderDetail(libreria[5], 1);
		
		carrito.addProductDetail(pd);				// lo agreo al carrito
		carrito.addProductDetail(pd2);
		carrito.addProductDetail(pd3);
		carrito.removeProductDetail(pd2);
		carrito.save();								// guardo el carrito en local storage
		
		//var ship = "courier";
		//currentUser(user => {
		//	carrito.buyOrder(user, ship);
		//})
		
		
	})*/
	
	/*getAllOrders((orders) => {
		console.log(orders);
	})*/
	
})