<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script type="text/javascript" src="${contextPath}/assets/appJs/validation/common-utils.js"></script>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<style>
.but-al{
margin-top:27px !important;
}

label.required::after {
    content: '* ';
    color: red;
}
.hidden {
    display: none;
}
</style>

<div class="container-fluid" style="margin-top: -100px; position: relative; z-index:99">
<%@ include file="/WEB-INF/views/message.jsp"%>
<%@ include file="/WEB-INF/views/ajaxLoader.jsp"%>
                <div class="row inner-cardbox">
                    <h6> Book Return</h6>
                    <hr>
                    
            <form action="${contextPath}/mst/manage-returnBook" method="post" id="manageReturnBookForm" enctype="multipart/form-data">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
            <input type="hidden" name="bookIssueVO.bookIssuedId" value="${bookissueDtls.bookIssueVO.bookIssuedId}" />
                <div class="row">
                 
                     <div class="col-md-4">
		                <div class="form-group">
		                  <label  for="publisherName">Book Name :</label>
		                 	 <input type="text" class="form-control form-control-sm NumbersOnly" id="isbnNo" value="${bookissueDtls.bookIssueVO.book.bookUkNo}" readonly="readonly">
		                </div>
		              </div>
                 
                      <!-- <div class="col-md-4">
		                <div class="form-group">
		                  <label class="required" for="publisherName">Library Card :</label>
		                 	 <input type="text" class="form-control form-control-sm AlphabetsOnly" id="bookTitle" oninput="this.value = this.value.toUpperCase()" >
		                </div>
		              </div> -->
		              
		               <div class="col-md-4">
		                <div class="form-group">
		                  <label  for="address">Issued Date :</label>
							    <input type="text" class="form-control form-control-sm AlphabetsOnly" id="author"  value="${bookissueDtls.bookIssueVO.issuedDate}" readonly="readonly">
		                </div>
		              </div>
		              
		              <div class="col-md-4">
						 <div class="form-group">
						   <label  for="contact">Return date :</label>
						   <input type="text" class="form-control form-control-sm NumbersOnly" id="contact"   value="${bookissueDtls.bookIssueVO.returnDate}" readonly="readonly">
						 </div>
					 </div>
		              
		              <div class="col-md-4 " >
		                <div class="form-group">
		                  <label class="required" for="isreturn">Is Book return late :</label>
		                  	<select class="form-control form-control-sm " id="isreturn"  onchange="getHiddenFineAmount(this.value, 'ISRETURNLATE')" name="bookIssueVO.isBookReturnLate" >
							        <option value="0">--Select--</option>
							        <option value="true">Yes</option>
							        <option value="false">No</option>
							</select>
		                </div>
		              </div>
		              
		              <div class="col-md-4 hidden" id="hiddenFineAmount">
						 <div class="form-group">
						   <label class="required" for="fineAmount">Fine Amount :</label>
						   <input type="text" class="form-control form-control-sm NumbersOnly" id="fineAmount" name="bookIssueVO.fineAmount" value="${bookissueDtls.fineAmount}">
						 </div>
					 </div>
					 
					 <div class="col-md-4 " >
						 <div class="form-group">
						   <label class="required" for="bookCondition">Book Condition :</label>
						   <select class="form-control form-control-sm " id="bookCondition" name="bookIssueVO.bookCondition" onchange="getHiddenFineAmount(this.value, 'DAMAGE/LOST')" >
							        <option value="0">--Select--</option>
							        <option value="GOOD">Good</option>
							        <option value="DAMAGED">Damaged</option>
							        <option value="LOST">Lost</option>
							</select>
						 </div>
					 </div>
					 
					 <div class="col-md-4 hidden" id="hiddenDamageOrLostFine" >
						 <div class="form-group">
						   <label class="required" for="fineAmount">Damage/Lost Fine :</label>
						   <input type="text" class="form-control form-control-sm NumbersOnly" id="damageOrLostFine" name="bookIssueVO.damageOrLostFine" maxlength="4">
						 </div>
					 </div>
					 
					 <div class="col-md-4 hidden" id="hiddenDamageOrLostImg" >
						 <div class="form-group">
						   <label class="required" for="fineAmount">Damage Book Image :</label>
						   <input type="file" class="form-control form-control-sm" id="damageOrLostImg" name="bookIssueVO.damageOrLostImg" onchange="checkFileTypeExtAndSize(this,'2')" >
						 </div>
					 </div>
		              
		             <div class="col-md-12 text-center mt-2">
						<button type="button" class="btn btn-success btn-sm" onclick = "saveForm()">Submit</button>
						<a href="${contextPath}/mst/issued-bookList" type="button" class="btn btn-warning btn-sm">Back</a>
				  	</div>
           	</div>
           	
           	
            </form>
            </div>
        </div>	
<script src="${contextPath}/assets/appJs/v1/checkout.js"></script>        
<script>

/* function saveForm() {
    debugger;
    if ($("#isreturn").val() == '0') {
        alert("Please select Is Book Return Late");
        return false;
    } else if ($("#bookCondition").val() == '0') {
        alert("Please select Book Condition");
        return false;
    } else {
        if ($("#isreturn").val() === 'true') {
            if ($("#fineAmount").val() === '' || $("#fineAmount").val() === null) {
                alert("Please Enter Fine Amount");
                return false;
            }else if ($("#bookCondition").val() !== 'GOOD') {
                if ($("#damageOrLostImg").val() === '') {
                    alert("Please choose Image");
                    return false;
            }else {
                $("#manageReturnBookForm").submit();
            }
        } else {
            $("#manageReturnBookForm").submit();
        }
    }else{
    	if ($("#bookCondition").val() !== 'GOOD') {
            if ($("#damageOrLostImg").val() === '') {
                alert("Please choose Image");
                return false;
        }else {
            $("#manageReturnBookForm").submit();
        }
    } else {
        $("#manageReturnBookForm").submit();
    	}
    }
}
} */

function saveForm() {
    debugger;
    
    if ($("#isreturn").val() === '0') {
        alert("Please select Is Book Return Late");
        return false;
    }

    if ($("#bookCondition").val() === '0') {
        alert("Please select Book Condition");
        return false;
    }

    if ($("#isreturn").val() === 'true' && ($("#fineAmount").val() === '' || $("#fineAmount").val() === null)) {
        alert("Please Enter Fine Amount");
        return false;
    }

    if ($("#bookCondition").val() === 'DAMAGED' && ($("#damageOrLostImg").val() === '' || $("#damageOrLostFine").val() === '')) {
        alert("Please choose Image / fill Fine Amount");
        return false;
    }
    
    if ($("#bookCondition").val() === 'LOST' && $("#damageOrLostFine").val() === '') {
        alert("Please fill Fine Amount");
        return false;
    }

    $("#manageReturnBookForm").submit();
}


function getHiddenFineAmount(value, field) {
	debugger;
    var hiddenFineAmount = document.getElementById("hiddenFineAmount");
    var hiddenDamageOrLost = document.getElementById("hiddenDamageOrLostImg");
    var hiddenDamageOrLostFine = document.getElementById("hiddenDamageOrLostFine");
    switch (field){
	    case 'ISRETURNLATE':
	    	if (value === "true") {
	    		hiddenFineAmount.classList.remove("hidden");
	        } else {
	        	hiddenFineAmount.classList.add("hidden");
	        }
	    	break;
		case 'DAMAGE/LOST':
			if (value === "GOOD") {
				hiddenDamageOrLost.classList.add("hidden");
				hiddenDamageOrLostFine.classList.add("hidden");
				$("#damageOrLostFine").val('');
	        } else if(value === "DAMAGED") {
	        	hiddenDamageOrLost.classList.remove("hidden");
	        	hiddenDamageOrLostFine.classList.remove("hidden");
	        	$("#damageOrLostFine").val(${bookissueDtls.bookIssueVO.book.bookCatalog.bookPrice});
	        }else{
// 	        	hiddenDamageOrLost.classList.add("hidden");
	        	hiddenDamageOrLostFine.classList.remove("hidden");
	        	hiddenDamageOrLost.classList.add("hidden");
				$("#damageOrLostFine").val(${bookissueDtls.bookIssueVO.book.bookCatalog.bookPrice});
	        }
	    	break;
	    default:
    }
}

function createPayment(rentMonthId, count) {
	var amount = $("#fineAmount").val();
	if (amount == "") {
		bootbox.alert("Please Enter Amount.");
		return false;
	} else {
		$.ajax({
			type: "GET",
			url: "${contextPath}/createPayment",
			data: {
				"rentMonthId": rentMonthId,
				"amount": amount,
			},
			success: function (response1) {
				var val = JSON.parse(response1);
				var options = {
					"key": val.test_key, // Enter the Key ID generated from the Dashboard
					"amount": val.amount, // Amount is in currency subunits. Default currency is INR. Hence, 50000 refers to 50000 paise
					"currency": "INR",
					"name": "Library Management System",
					"description": "Library Management System",
					"image": "https://www.sjsv.nic.in/images/logo.png",
					"order_id": val.order_id, //This is a sample Order ID. Pass the `id` obtained in the response of Step 1
					"handler": function (response) {
						$.ajax({
							type: "GET",
							url: "${contextPath}/afterThePayment",
							data: {
								"payment_id": response.razorpay_payment_id,
								"order_id": response.razorpay_order_id,
								"signature": response.razorpay_signature,
								"status": "SUCCESS",
								"preference": preference,
								"rentMonthId": rentMonthId,
							},
							success: function (response2) {
								var value = JSON.parse(response2);
								if (value.transactionId != "") {
									//$("#transactionId").val(value.transactionId);
									$("#manageReturnBookForm").submit();
								} else {
									bootbox.alert("Something went Wrong !!");
								}
							},
							error: function (error) {
								bootbox.alert("Something went Wrong !!");
							}
						});
					},
					"prefill": {
						"name": val.name,
						"email": "abc@example.com",
						"contact": "1111111111"
					},
					"notes": {
						"address": "Razorpay Corporate Office"
					},
					"theme": {
						"color": "#3399cc"
					}
				};
				var rzp1 = new Razorpay(options);
				rzp1.on('payment.failed', function (response) {
					$.ajax({
						type: "GET",
						url: "${contextPath}/afterThePayment",
						data: {
							"payment_id": response.error.metadata.payment_id,
							"order_id": response.error.metadata.payment_id,
							"reason": response.error.reason,
							"status": "FAILED",
							"rentMonthId": rentMonthId,
						},
						success: function (response1) {
							var value = JSON.parse(response2);
							if (value.transactionId != "") {
								//$("#transactionId").val(value.transactionId);
								$("#manageReturnBookForm").submit();
							} else {
								bootbox.alert("Something went Wrong !!");
							}
						},
						error: function (error) {
							bootbox.alert("Something went Wrong !!")
						}
					});
				});
				rzp1.open();
			},
			error: function (error) {
				//bootbox.alert("Failure"); 
			}
		});
	}
}


</script>    

