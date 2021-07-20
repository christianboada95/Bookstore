
/**
 * Retorna todas las categorias de la base de datos
 * 
 * 
 * */
function getAllCategories(callback) {
	
	$.ajax("api/categories", {
		type: "GET",
		contentType: "application/json",
		headers: {"Authorization": sessionStorage.getItem('token')},
		success: function(data) {
			var books = JSON.parse(JSON.stringify(data));
			callback(books);
		},
		error: function(request,error) {
            alert('An error occurred attempting to get all categories');
            // console.log(request, error);
        }	
	});
}
/**
 * Retorna todas las categorias que tiene el usuario logueado
 * La categoría que tiene representa su especialización
 * 
 * */
function getUserCategories(callback) {
	currentUser(user => {
		callback(user.categories);
	})
}

/** Testing */
$(function(){
	
	/*getAllCategories((categories) => {
		console.log(categories);
	})*/
	
	/*getUserCategories(categories => {
		console.log(categories);
	})*/
	
})