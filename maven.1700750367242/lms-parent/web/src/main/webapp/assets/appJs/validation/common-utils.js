$(function() {

	$('.AlphabetsOnly').keypress(function(e) {
		var regex = new RegExp(/^[a-zA-Z\s]+$/);
		var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
		if (regex.test(str)) {
			return true;
		} else {
			e.preventDefault();
			return false;
		}
	});

	$('.emailsOnly').keypress(function (e) {
	    var regex = new RegExp("/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/");
	    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	    if (regex.test(str)) {
	        return true;
	    }
	    else {
	        e.preventDefault();
	        return false;
	    }
	});

	$('.NumbersOnly').on(
			'keypress',
			function(event) {
				var regex = new RegExp("^[0-9]+$");
				var key = String.fromCharCode(!event.charCode ? event.which
						: event.charCode);
				if (!regex.test(key)) {
					event.preventDefault();
					return false;
				}
			});

	$('.AlphaNumericOnly').keypress(function(e) {
		var regex = new RegExp(/^[a-zA-Z0-9@#*%_\s]+$/);
		var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
		console.log(str);
		if (regex.test(str)) {
			return true;
		} else {
			e.preventDefault();
			return false;
		}
	});

	$('.NumbersOnlyForAmount').keypress(
		function(event) {
			var regex = new RegExp("^[0-9.]+$");
			var key = String.fromCharCode(!event.charCode ? event.which
					: event.charCode);
			if (!regex.test(key)) {
				event.preventDefault();
				return false;
			}
});
});

function validateEmail(emailField) {
	var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

	if (reg.test(emailField.value) == false) {
		bootbox.alert("Please Provide a Vaild Email Id");
		var result = emailField.value.replace(
				/[a-zA-Z!&#@^/#+()$~%&\\\|\.''"":;*?<>{}]/g, '');
		emailField.value = result;
		emailField.value = '';
		emailField.focus();
		return false;
	}
	return true;
}

function validateMobileNo(element) {
	/*alert("mobile vaildate function id---------->"+element);*/
	var re = /^[0-9]+$/;

	var str = element.toString();

	var str1 = element.value;

	element = (element) ? element : window.event;
	var charCode = (element.which) ? element.which : element.keyCode;
	if (charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
	}

	if (isNaN(str1) || str1.indexOf(" ") != -1) {
		bootbox.alert("Please Provide a Vaild Mobile Number");
		var result = element.value.replace(
				/[a-zA-Z!&#@^/#+()$~%&\\\|\.''"":;*?<>{}]/g, '');
		element.value = result;
		element.value='';
		this.value='';
		//document.getElementById('txtMobile').focus();
		return false;
	}

	if (str1.length > 10 || str1.length < 10) {
		bootbox.alert("Please Provide a Vaild Mobile Number");
		var result = element.value.replace(
				/[a-zA-Z!&#@^/#+()$~%&\\\|\.''"":;*?<>{}]/g, '');
		//element.value = result;
		element.value='';
		this.value='';
		//document.getElementById('txtMobile').value = '';
		return false;
	}

	/*$("input[type='text']").on('keypress', function (event) {
	    var regex = new RegExp("^[a-zA-Z0-9_ ]+$");
	    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	    if (!regex.test(key)) {
	       event.preventDefault();
	       return false;
	    }
	});
	
	$('textarea').on('keypress', function (event) {
	    var regex = new RegExp("^[a-zA-Z0-9. ]+$");
	    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	    if (!regex.test(key)) {
	       event.preventDefault();
	       return false;
	    }
	});*/
}

function imageCheck(that){
debugger;
	var ValidFileExtension = [ 'jpg','jpeg','pdf'];
	if ($.inArray($("#"+that.id).val().split('.').pop().toLowerCase(), ValidFileExtension) == -1) {
		$("#"+that.id).val("");
		bootbox.alert("Sorry! allowed format is jpg or jpeg or pdf only.");
		event.preventDefault();
		return false;
	}
	if ((that.files[0].size) > 2097152) {
		$(that).val("");
		bootbox.alert("File size exceeds maximum image size of 1 MB!");
		return false;
	}
	
}

function currencyConverter(that,fieldId){
	if(that!=""){
		var value = that.replaceAll(",","");
		if(value.includes(".")){
			var splitValues = value.split(".");
			if(splitValues.length>=2){
				if(splitValues[0]==""){
					splitValues[0]="0";
				}
				value=splitValues[0]+"."+splitValues[1];
				if(splitValues[1].length==1){
					value=splitValues[0]+"."+splitValues[1]+"0";		
				}
				if(splitValues[1].length>2 || splitValues[1]==""){
					value=splitValues[0]+".00";		
				}
			}else{
				value=splitValues[0];
			}
		}else{
			value=value+".00";
		}
		var hiddenFieldId = fieldId.split("Conv")[0];
		$("#"+hiddenFieldId).val(value);
		value = value.replace(/(\d)(?=(\d{2})+\d\.)/g, '$1,');
		$("#"+fieldId).val(value);
	}
}

$('.AlphaNumericForCode').keypress(function(e) {
	var regex = new RegExp(/^[a-zA-Z0-9]+$/);
	var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	if (regex.test(str)) {
		return true;
	} else {
		e.preventDefault();
		return false;
	}
});


function getMonths(monthId,financialYear){
	showAjaxLoader();
	var htm = '<option value="" >--Select--</option>';
	$.ajax({
	   	 	url: window.contextPath+'/pcMpr/getMonthList',
	   	 	type : 'GET',
	   	 	data : ({
	   	 	financialYear : financialYear
			}),
			cache : false,
			asynch : false,
			success : function(response) {
				hideAjaxLoader();
				if(response!=""){
					var obj = jQuery.parseJSON(response);
					$.each(obj, function(key,value) {
						htm = htm+"<option value="+value.monthId+">"+value.monthName+"</option>";
					});
				}else{
					bootbox.alert("Error while getting month by financial year.");
					return false;
				}
				$("#month").empty().append(htm);
				if(monthId!=0){
					$("#month").val(monthId);
				}
			}
	       ,
			error : function(error) {
				hideAjaxLoader();
				bootbox.alert("Something went wrong while month by financial year.");
				return false;
			}
	    
	      });
}

function aadharValidation(value, fieldId){
debugger;
  var cleanAadhar = value.replace(/[^0-9]/g, '');
   if (cleanAadhar.length === 12) {
   
  }else{
       $("#"+fieldId).val(" ");  
       alert("Aadhar no should be 12 digit");
  }
}

function validateMobileNumber(mobNumber, field) {
debugger;
      const cleanNumber = mobNumber.replace(/[^0-9]/g, '');
      
      if (/^\d{10}$/.test(cleanNumber)) {
        return true; 
    } else {
        alert('Invalid mobile number');
        $("#"+ field).val("");
        return false; 
    }
}

function validatePan(panNo, field){
debugger;
var regpan = /^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$/;

if(regpan.test(panNo)){
   return true; 
} else {
   alert('Invalid Pan number');
   $("#"+ field).val("");
   return false; 
}
}


  function checkFileTypeExtAndSize(that, ftype) {
  debugger;
    var fileInput = $("#" + that.id)[0];
    var file = fileInput.files[0];

    if (file) {
      // Check file size
      var maxSize = 1024 * 1024; // 1 MB
      if (file.size > maxSize) {
        bootbox.alert("File size exceeds 1Mb limit.");
        $("#" + that.id).val('');
        return false;
      }
    }

    // Get the file upload control file extension
    var extn = $('#' + that.id).val().split('.').pop().toLowerCase();

    // Create array with the files extensions to upload
    var fileListToUpload;
    if (parseInt(ftype) == 1) {
      fileListToUpload = new Array('pdf', 'gif', 'jpg', 'jpeg', 'png');
    } else if (parseInt(ftype) == 2) {
      fileListToUpload = new Array('png', 'jpg', 'jpeg');
    } else {
      fileListToUpload = new Array('pdf');
    }

    // Check the file extension is in the array
    var isValidFile = $.inArray(extn, fileListToUpload);

    // isValidFile gets the value -1 if the file extension is not in the list
    if (isValidFile == -1) {
      if (parseInt(ftype) == 1) {
        bootbox.alert("Please select a valid file of type pdf, gif, jpg, jpeg, png.");
      } else if (parseInt(ftype) == 2) {
        bootbox.alert("Please select a valid file of type png, jpg or jpeg.");
      } else {
        bootbox.alert("Please select a valid pdf file only.");
      }
      $('#' + that.id).val('');
    }

    return true;
  }

 