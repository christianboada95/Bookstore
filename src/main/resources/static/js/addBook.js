function loadingAjax(div_id) {
    var divIdHtml = $("#" + div_id).html();
  
    token = sessionStorage.getItem("token");
    if (token != null) {
        $("#loading-image").show();
        $("#loading").hide();
        $(".chekcToken").hide();
    } else {
      window.location.href = "auth/login";
    }
}

$("#btn-add").click(function(){
    let title = $("#title").val();
    let author = $("#author").val();
    let publihser = $("#publisher").val();
    let isbn = $("#isbn").val();
    let npages = $("#npages").val();
    let stock = $("#stock").val();
    let price = $("#price").val();
    let description = $("#description").val();
    
    let newBook = {
        title: title,
        author: {name: author},
        publihser: {name: publihser},
        isbn: isbn,
        pages: npages,
        stock: stock,
        price: price,
        description: description
    }
    console.log(newBook)

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/books/save",
        //headers: { Authorization: token },
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newBook),
        cache: false,
        processData: false,
        success: function(response){
            console.log(response);
        },
        error: function(response){
            console.log(response);
        }
    });
});