var news = new Array();
var bestSellers = new Array();

$(function() {

    getAllBooks( (data) => {

    	news = data.filter((book) => {
            if(book.categories != null)
    		for(let i = 0; i<book.categories.length; i++){	 
    			if(book.categories[i].name == "New")
    				return true;
    		}
    		
    	});
    	
    	bestSellers = data.filter((book) => {
            if(book.categories != null)
    		for(let i = 0; i<book.categories.length; i++){	 
    			if(book.categories[i].name == "Best seller")
    				return true;
    		}
    	});
    	
    	//console.log(news);
    	//console.log(bestSellers);
    	
        for (const book of data) {
            addBook(book);
        }               
        //console.log(data[0]);
        addBestSellers();
    });   


    var id = urlParam('id');
    if(id) {
        //console.log(id);
        infobook(id);              
    }
  
    

});

function addBestSellers(){

    //console.log(bestSellers);

    var books = $("#betsSellers");

    for (const book of bestSellers) {     
        books.append(                                                         
            '<div class="col-md-3 mb-2 animated fadeInUp">' +                    
                '<div class="card-1 text-center">' +
                    '<img src="'+ book.cover +'" class="card-img-top" alt="Inventario">' +
                    '<div class="product-rating mt-1">' +
                        '<ul>' +
                            '<li><a href="#"><i class="fa fa-star"></i></a></li>' +
                            '<li><a href="#"><i class="fa fa-star"></i></a></li>' +
                            '<li><a href="#"><i class="fa fa-star"></i></a></li>' +
                            '<li><a href="#"><i class="fa fa-star"></i></a></li>' +
                            '<li><a href="#"><i class="fa fa-star"></i></a></li>' +
                        '</ul>' +
                    '</div>' +                        
                    '<h5 class="mt-0" >'+ book.title +'</h5>' +
                    '<p>$' + book.price + '</p>' +                
                    '<a class="a-book" href="http://localhost:8080/book?id='+ book.id +'" >Ver detalles</a>' +
                '</div>' +
            '</div>' 
        );
           
    }

    var books2 =  $("#news");
    for (const book of news) {     
        books2.append(                                                         
            '<div class="col-md-3 mb-2 animated fadeInUp">' +                    
                '<div class="card-1 text-center">' +
                    '<img src="'+ book.cover +'" class="card-img-top" alt="Inventario">' +
                    '<div class="product-rating mt-1">' +
                        '<ul>' +
                            '<li><a href="#"><i class="fa fa-star"></i></a></li>' +
                            '<li><a href="#"><i class="fa fa-star"></i></a></li>' +
                            '<li><a href="#"><i class="fa fa-star"></i></a></li>' +
                            '<li><a href="#"><i class="fa fa-star"></i></a></li>' +
                            '<li><a href="#"><i class="fa fa-star"></i></a></li>' +
                        '</ul>' +
                    '</div>' +                        
                    '<h5 class="mt-0" >'+ book.title +'</h5>' +
                    '<p>$' + book.price + '</p>' +                
                    '<a class="a-book" href="http://localhost:8080/book?id='+ book.id +'" >Ver detalles</a>' +
                '</div>' +
            '</div>' 
        );
           
    }
}

function urlParam(name) {
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    
    if(results != null)
    	return results[1] || 0;
}

function addBook(book) {

    var books = $("#books");

    books.append(                                                         
        '<div class="col-md-2 mb-2 animated fadeInUp">' +                    
            '<div class="card-1 text-center">' +
                '<img src="'+ book.cover +'" class="card-img-top" alt="Inventario">' +
                '<div class="product-rating mt-1">' +
                    '<ul>' +
                        '<li><a href="#"><i class="fa fa-star"></i></a></li>' +
                        '<li><a href="#"><i class="fa fa-star"></i></a></li>' +
                        '<li><a href="#"><i class="fa fa-star"></i></a></li>' +
                        '<li><a href="#"><i class="fa fa-star"></i></a></li>' +
                        '<li><a href="#"><i class="fa fa-star"></i></a></li>' +
                    '</ul>' +
                '</div>' +                        
                '<h5 class="mt-0" >'+ book.title +'</h5>' +
                '<p>$' + book.price + '</p>' +                
                '<a class="a-book" href="http://localhost:8080/book?id='+ book.id +'" >Ver detalles</a>' +
                '<hr/>' +
                '<button class="btn btn-primary btn-sm btn-block mt-3" onclick="addCardSimple('+ book.id +')" ><i class="fas fa-cart-plus"></i> Agregar al carrito</button > ' +
                
            '</div>' +
        '</div>' 
    );
}

function infobook(id){
    
    getBook(id, (book) => {
        console.log(book);
        $( "#cover" ).replaceWith( '<img id="cover" src="' + book.cover  + '" class="card-img-top" alt="Inventario">' );
        
        $( "#title" ).replaceWith( "<h2>" + book.title  + "</h2>" );        
        $( "#author" ).replaceWith( "<p class='m-0'><b>Autor:</b> " + book.author.name  + "</p>" );
        $( "#publisher" ).replaceWith( "<p class='m-0'><b>Editorial:</b> " + book.publisher.name  + "</p>" );

        $( "#description" ).replaceWith( "<p class='m-0 mt-1'><b>Descripción:</b> " + book.description  + "</p>" );   

        $( "#date" ).replaceWith( "<p class='m-0 mt-1'><b>Fecha de lanzamiento:</b> " + book.release_at  + "</p>" ); 
        
        if(book.categories != null){
            for (const category of book.categories) {
                $("#categories").append(
                    '<span class="badge badge-pill badge-secondary mr-2">' + category.name + '</span>'
                );
            } 
        }       
        
        
        $( "#pages" ).replaceWith( "<p class='m-0 mt-1'><b>N° de páginas:</b> " + book.pages  + "</p>" ); 
        $( "#isbn" ).replaceWith( "<p class='m-0 mt-1'><b>ISBN:</b> " + book.isbn  + "</p>" );      
        $( "#stock" ).replaceWith( "<p class='m-0 mt-1'><b>Stock:</b> " + book.stock  + "</p>" );      
        
        
        $( "#price" ).replaceWith( "<h5 class='m-0 mt-1'><b>Precio:</b> $" + book.price  + "</h5>" );

        
        

        for (let i = 0; i < book.qualification; i++) {            
            $("#qualification").append(
                '<li><a href="#"><i class="fa fa-star"></i></a></li>'
            );
        }

        

    });
        
}

function addCard(){
    
    var id = urlParam('id');

    getBook(id, (book) => {
        //let stock = parseInt(book.stock);
        
    	var pd = new OrderDetail(book, 1);
        
    	// Si el producto no esta en el carrito lo mete
        if(!carrito.isProduct(pd)){
        	carrito.addProductDetail(pd);
            carrito.save();	
            Toast.fire({
                type: 'success',
                title: 'Libro agregado'
              })
        }
        // Si ya esta muestra una notificacion de que ya está
        else {
            //alert("El producto ya esta en el carrito");
            Swal.fire({
                type: 'warning',
                title: 'El producto ya esta en el carrito',                    
              })
        }
     
    });       
}

const Toast = Swal.mixin({
    toast: true,
    position: 'top-end',
    showConfirmButton: false,
    timer: 4500
  });

function addCardSimple(id) {
    
    getBook(id, (book) => {
        var stock = book.stock;

        if( stock >= 1 ) 
        {
            console.log(book);    
            var pd = new OrderDetail(book, 1);            
            carrito.addProductDetail(pd);
            carrito.save();	
            
            stock -= 1;

            Toast.fire({
                type: 'success',
                title: 'Libro agregado'
              })


        }        

    });       
}

function searchBook() {

    titleBook = $("#titleBook").val();
    isbnBook = $("#isbnBook").val();
    authorBook = $("#authorBook").val();

    if( titleBook == "" && isbnBook == "" && authorBook == "" ) {
       // alert("Al menos un parametro de busqueda");  
        Swal.fire({
            type: 'warning',
            title: 'Ingresa al menos un parametro de busqueda',                    
          })         
    } else {
        var book = new Search(titleBook, isbnBook, authorBook);
        //console.log(book);

        Swal.fire({
            title: 'Buscando libros...',
            onBeforeOpen: () => {
                Swal.showLoading();

                searchBooks(book, (books) => {            
                    if(books.length >= 1) {   
                        var contenido = $("#books");
                    
                        contenido.replaceWith( 
                            '<div class="row" id="books">' +
                                '<div class="col-12">' +
                                    '<h4>Resultados de busqueda</h4>' +
                                    '<hr class="bets" >' +
                                '</div>     ' +                               
                            '</div>'
                        );
        
                        for (const item of books) {
                            addBook(item);
                            console.log(books);    
                            swal.close();            
                        }
                    } else {
                        Swal.fire({
                            type: 'warning',
                            title: 'No se encontraron libros con los criterios de busqueda',                    
                          })
                    }
        
                } );                      
            }            
        })
        
        
        
    }
}