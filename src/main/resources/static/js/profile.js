let username = "";

function loadingAjax(div_id) {
  var divIdHtml = $("#" + div_id).html();

  token = sessionStorage.getItem("token");
  if (token != null) {
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/api/auth/user",
      headers: { Authorization: token },
      beforeSend: function () {
        $("#loading-image").show();
      },
      success: (data) => {
        $("#loading").hide();
        $(".chekcToken").hide();

        $("#Names").val(data.name);
        $("#LastNames").val(data.surname);
        $("#Email").val(data.email);
        $("#Address").val(data.address);
        $("#postal").val(data.postcode);
        $("#city").val(data.city);
        $("#department").val(data.department);
        username = data.username;
      },
      error: (err) => {
        sessionStorage.removeItem("token");

        window.location.href = "/auth/login";
      },
      complete: (xhr, status) => {
        console.log("loading complete", status);
      },
    });
  } else {
    window.location.href = "auth/login";
  }
}

function inputsDisable(bool) {
  $("#Names").prop("disabled", bool);
  $("#LastNames").prop("disabled", bool);
  $("#Email").prop("disabled", bool);
  $("#Address").prop("disabled", bool);
  $("#postal").prop("disabled", bool);
  $("#city").prop("disabled", bool);
  $("#department").prop("disabled", bool);
}

$("#btn-edit").click(function () {
  $(this).hide();
  $("#btn-update").show();
  $("#btn-cancel").show();

  inputsDisable(false);
});

$("#btn-cancel").click(function () {
  $(this).hide();
  $("#btn-update").hide();
  $("#btn-edit").show();

  inputsDisable(true);
});

$("#userForm").submit(function (e) {
  e.preventDefault();
  token = sessionStorage.getItem("token");
  if (token != null) {
    let userdata = {
      name: $("#Names").val(),
      surname: $("#LastNames").val(),
      email: $("#Email").val(),
      address: $("#Address").val(),
      postcode: $("#postal").val(),
      city: $("#city").val(),
      department: $("#department").val(),
    };

    $.ajax({
      type: "PUT",
      contentType: "application/json",
      url: "http://localhost:8080/api/customers/" + username,
      headers: { Authorization: token },
      data: JSON.stringify(userdata),
      beforeSend: function () {},
      success: (data) => {},
      error: (err) => {
        sessionStorage.removeItem("token");
        window.location.href = "/auth/login";
      },
      complete: (xhr, status) => {
        console.log("loading complete", status);
      },
    });
  } else {
    window.location.href = "auth/login";
  }
  inputsDisable(true);
  $("#btn-cancel").hide();
  $("#btn-update").hide();
  $("#btn-edit").show();
});
