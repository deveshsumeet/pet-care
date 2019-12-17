/* JS file to handle all UI validation and ajax call to backend pet care rest api’s 
 * 
 * @version: 1.0
 * @author:  Devesh Sumeet
 * 
 */
$(document).ready(function() {
	$("body").on("click", "nav ul li a", function() {
		var title = $(this).data('title');
		var pageName = $(this).data("name") + ".html";
		$(".title").children("h2").html(title);
		$(".main").load(pageName);
		$("#output").css("display", "none");
	});
});

$(document).on('change', '#petNameDropDown', function() {
	$('#aptDateTime').empty();
});

/**
 * Method to ajax call get all pet present in system
*/
function getPetList() {
	$.ajax({
		type : "GET",
		url : "/getPetAnimal",
		success : function(data) {
			var petOutput = '<option value="-1">Please Select a Pet</option>';
			for (var i = 0; i < data.length; i++) {
				petOutput += '<option value="' + data[i].petUniqueId + '">'
						+ data[i].petName + '</option>';
			}
			$("#petNameDropDown").html(petOutput);
		}
	});
}

/**
 * Method to ajax call get all vet present in system
*/
function getVetList() {
	$
			.ajax({
				type : "GET",
				url : "/getVeterinarian",
				success : function(data) {
					var output = '<option value="-1">Please Select a Veterinarian</option>';
					for (var i = 0; i < data.length; i++) {
						output += '<option value="' + data[i].vetUniqueId
								+ '">' + data[i].firstName + ' '
								+ data[i].lastName + '</option>';
					}
					$("#vetNameDropDown").html(output);
					$("#aptDateTime").empty();
				}
			});
}

/**
 * Method to ajax call get all appointment for a pet
*/
function searchAppointmentList() {
	if ((!$("#petNameDropDown option").length > 0)
			|| ($("#petNameDropDown").val() == "-1")) {
		alert("Please Select A Pet From Dropdown");
	} else {
		var apptData;
		$.ajax({
			type : "GET",
			url : "/getPetBooking/" + $('#petNameDropDown').val(),
			success : function(apptData) {
				var searchOutput = "List of All Appointments : <br><br>";
				for (var i = 0; i < apptData.length; i++) {
					searchOutput += "Booking No: " + apptData[i][0]
							+ " <br>With Veterinarian : Dr. " + apptData[i][1]
							+ ", At Time : " + apptData[i][2] + "<br><br>";
				}
				$("#output").css("display", "block");
				$("#output").empty().append(searchOutput);
			}
		});
	}
}

/**
 * Method to ajax call get all appointment for a pet 
*/
function getAppointmentList() {
	var apptData;
	$
			.ajax({
				type : "GET",
				url : "/getPetBooking/" + $('#petNameDropDown').val(),
				success : function(apptData) {
					var output = '<option value="-1">Please Select Appointment To Cancel</option>';
					for (var i = 0; i < apptData.length; i++) {
						output += '<option value="' + apptData[i][0]
								+ '"> With Veterinarian : Dr. '
								+ apptData[i][1] + ' At Time: '
								+ apptData[i][2] + '</option>';
					}
					$("#aptDateTime").empty().html(output);
				}
			});
}

/**
 * Method to ajax call add veterinarian and validate the empty textboxes 
*/
function addVet() {
	if (($('input[name=firstName]').val() == '')
			|| ($('input[name=lastName]').val() == '')) {
		alert("Please Enter First and Last Name");
	} else {
		var formVetData = {
			'firstName' : $('input[name=firstName]').val(),
			'lastName' : $('input[name=lastName]').val()
		};
		$.ajax({
			url : '/createVeterinarian',
			type : 'POST',
			dataType : 'json',
			contentType : 'application/json',
			success : function(formVetData) {
				document.getElementById("vetFormDetail").reset();
				$("#output").css("display", "block");
				$("#output").empty().append(
						"Veterinarian Detail Added Successfully !");
				$("#output").fadeOut(3000);
			},
			data : JSON.stringify(formVetData)
		});
	}
}

/**
 * Method to ajax call add appointment and validate the date format 
*/
function addPet() {
	if (($('input[name=petName]').val() == '')
			|| ($('input[name=petKind]').val() == '')) {
		alert("Please Enter Pet Name and Kind");
	} else {
		var formPetData = {
			'petName' : $('input[name=petName]').val(),
			'petKind' : $('input[name=petKind]').val()
		};
		$.ajax({
			url : '/createPetAnimal',
			type : 'POST',
			dataType : 'json',
			contentType : 'application/json',
			success : function(formPetData) {
				document.getElementById("petFormDetail").reset();
				$("#output").css("display", "block");
				$("#output").empty().append(
						"Pet Animal Detail Added Successfully ! ");
				$("#output").fadeOut(3000);
			},
			data : JSON.stringify(formPetData)
		});
	}
}

/**
 * Method to ajax call book appointment and validate the date format 
*/
function makeAppointment() {
	var pattern=/^([0]{0,1}[1-9]|1[012])\/([1-9]|([012][0-9])|(3[01]))\/\d\d\d\d [012]{0,1}[0-9]:[0-6][0-9]/;
	if ((!$("#petNameDropDown option").length > 0)
			|| ($("#petNameDropDown").val() == "-1")
			|| (!$("#vetNameDropDown option").length > 0)
			|| ($("#vetNameDropDown").val() == "-1")
			|| ($('input[name=aptDateTime]').val() == '')) {
		alert("Please Enter All Detail");
	} else if (!pattern.test($('input[name=aptDateTime]').val())) {
		alert("Please Follow Correct Date and Time Format");
	} else {
		var bookingTime = new Date($('input[name=aptDateTime]').val());
		var formAptData = {
			'petUniqueId' : $('#petNameDropDown').val(),
			'vetUniqueId' : $('#vetNameDropDown').val(),
			'bookingTime' : bookingTime.getTime()
		};
		$.ajax({
			url : '/setAppointment',
			type : 'POST',
			dataType : 'json',
			contentType : 'application/json',
			data : JSON.stringify(formAptData),
			success : function(data) {
				document.getElementById("apptFormDetail").reset();
				$("#output").css("display", "block");
				$("#output").empty().append(
						"Appointment Booked Successfully ! ");
				$("#output").fadeOut(3000);
			},
			error : function(err) {
				var errorResponse = err.responseText;
				var jsonErrorResponse = JSON.parse(errorResponse);
				$("#output").css("display", "block");
				$("#output").empty().append(
						"Failed to Book Appointment ! "
								+ jsonErrorResponse["message"]);
			}
		});
	}
}

/**
 * Method to ajax call cancel appointment  
*/
function cancelAppointment() {
	if ((!$("#petNameDropDown option").length > 0)
			|| (!$("#aptDateTime option").length > 0)
			|| ($("#petNameDropDown").val() == "-1")
			|| ($("#aptDateTime").val() == "-1")) {
		alert("Please Select All Dropdowns");
	} else {
		$.ajax({
			url : "/cancelAppointment/" + $('#aptDateTime').val(),
			type : 'POST',
			contentType : 'application/json',
			success : function(formAptData) {
				document.getElementById("cancelFormDetail").reset();
				$("#output").css("display", "block");
				$("#output").empty().append(
						"Appointment Cancelled Successfully ! ");
				$("#output").fadeOut(3000);
			},
			error : function(err) {
				var errorResponse = err.responseText;
				var jsonErrorResponse = JSON.parse(errorResponse);
				$("#output").css("display", "block");
				$("#output").empty().append(
						"Failed to Cancel Appointment ! "
								+ jsonErrorResponse["message"]);
			}
		});
	}
}