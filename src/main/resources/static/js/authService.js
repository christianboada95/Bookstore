
function login(usr, pwd, callback) {

	$.ajax("http://localhost:8080/login", {
		type: "POST",
		contentType: "application/json",
		data: JSON.stringify({
			username: usr,
			password: pwd
		}),
		success: function(data,status,jqXHR) {
			var token = jqXHR.getResponseHeader("Authorization")
        	sessionStorage.setItem("token", token);

			callback(token);
			
		},
		error: function(request,error) {
            alert('An error occurred attempting to login');
            console.log(request, error);
        }	
	});
}

function register(user, callback) {

	$.ajax("http://localhost:8080/api/auth/register", {
		type: "POST",
		contentType: "application/json",
		data: JSON.stringify(user),
		success: function(data,status,jqXHR) {
			callback(data);
		},
		error: function(request,error) {
            alert('An error occurred attempting to register');
            console.log(request, error);
        }	
	});
}

function logout() {
	
	
	/*
	$.ajax("http://localhost:8080/logout", {
		type: "POST",
		contentType: "application/json",
		headers: {"Authorization": sessionStorage.getItem('token')},
		success: function(data,status,jqXHR) {
			
			localStorage.removeItem("token");
			localStorage.removeItem("car");
			window.location.href = "auth/login";
		},
		error: function(request,error) {
            alert('An error occurred attempting to logout');
            console.log(request, error);
        }	
	});
	*/

	sessionStorage.removeItem("token");
	sessionStorage.removeItem("car");
	window.location.href = "auth/login";
	
	
}

function currentUser(token, callback) {
	
	$.ajax("http://localhost:8080/api/auth/user", {
		type: "GET",
		headers: {"Authorization": token},
		success: function(data,status,jqXHR) {
        	
			callback(data);
		},
		error: function(request,error) {
            alert('An error occurred attempting to curent user');
            console.log(request, error);
        }	
	});
}

function currentUser(callback) {
	
	$.ajax("http://localhost:8080/api/auth/user", {
		type: "GET",
		headers: {"Authorization": sessionStorage.getItem('token')},
		success: function(data,status,jqXHR) {
        	
			callback(data);
		},
		error: function(request,error) {
            alert('An error occurred attempting to curent user');
            console.log(request, error);
        }	
	});
}

/** Testing */
$(function(){
	var emailRegex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/
	var usernameRegex = /^[a-zA-Z0-9](_(?!(\.|_))|\.(?!(_|\.))|[a-zA-Z0-9]){6,18}[a-zA-Z0-9]$/;
	var passRegex = /^(?=\w*\d)(?=\w*[A-Z])(?=\w*[a-z])\S{8,16}$/;
	var registerForm = $("#registerForm");
	registerForm.validate();
	
	// Login form
	$("#loginForm button").click((e) => {
		e.preventDefault();
		var usr = $("#username").val();
		var pwd = $("#password").val();
		login(usr, pwd, (token) => {
			console.log("Redirect to store");
			window.location.href = "/store";
		})
	});
	
	// Register form
	$("#registerForm button").click((e) => {
		e.preventDefault();
		
		var creditCard ={
				cvv: $("#cvv").val(),
				number:$("#numberCard").val(),
				type: $("#tipeCard").val(),
				expires_at: new Date( $("#yearCard").val(), $("#dateExpire").val() )
		}
		
		var usr = {
				username: $("#username").val(),
				email: $("#email").val(),
				password: $("#password").val(),
				name: $("#name").val(),
				surname: $("#surname").val(),
				address: $("#address").val(),
				postcode: $("#postal").val(),
				department: $("#department").val(),
				city: $("#city").val(),
				
				//card: creditCard
		}
		
		if (!emailRegex.test(usr.email)) {
			alert("Email no válido");
			return;
		}
		
		if (!usernameRegex.test(usr.username)) {
			alert("Username no válido");
			return;
		}
		
		if (!passRegex.test(usr.password)) {
			alert("Password no válido");
			return;
		}
		
		if (registerForm.valid()) {
			register(usr, (data) => {
				alert("Usuario registrado");
				console.log("Usuario registrado", data);
				window.location.href = "/auth/login";
			})			
		} else {
			alert("Datos no válidos");
		}
		
	});
	
	//logout();
	
})