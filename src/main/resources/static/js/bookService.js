/** Search obj*/
class Search {
	constructor(title, isbn, author){
		this.title = title;
		this.isbn = isbn;
		this.author = author;	// nombre del autor
	}
}

function getAllBooks(callback) {
	
	$.ajax("api/books", {
		type: "GET",
		contentType: "application/json",
		headers: {"Authorization": sessionStorage.getItem('token')},
		success: function(data) {
			var books = JSON.parse(JSON.stringify(data));
			callback(books);
		},
		error: function(request,error) {
            alert('An error occurred attempting to get all books');
            // console.log(request, error);
        }	
	});
}

function getBook(id, callback) {
	
	$.ajax("api/books/"+id, {
		type: "GET",
		contentType: "application/json",
		headers: {"Authorization": sessionStorage.getItem('token')},
		success: function(data) {
			var book = JSON.parse(JSON.stringify(data));
			callback(book);
		},
		error: function(request,error) {
            alert('An error occurred attempting to get a book');
            // console.log(request, error);
        }	
	});
}

function searchBooks(search, callback) {
	
	// search debe tener 3 parametros Search Obj
	
	//$.get("", JSON.stringify(search)), 
	
	$.ajax("api/books/search", {
		type: "GET",
		//contentType: "application/json",
		headers: {"Authorization": sessionStorage.getItem('token')},
		data: search,//jQuery.param(JSON.stringify(search)),
		success: function(data) {
			var books = JSON.parse(JSON.stringify(data));
			callback(books);
		},
		error: function(request,error) {
            alert('An error occurred attempting to do a search');
            // console.log(request, error);
        }	
	});
}

/** Testing */
$(function(){
	
	/*getAllBooks((books) => {
		console.log(books);
	})
	
	var sea = new Search("In", "B", "");
	searchBooks(sea, (books) =>{
		console.log(books);
	})
	*/			
})