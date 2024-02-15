<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<style>
.alert {
	box-shadow: none;
}

.alert-info {
	border-left: 6px solid #48ABF7
}
</style>

<style type="text/css">
	.l-hide-tab tfoot tr td:last-child {
		display: none;
	}

	.nav-toggle {
		display: none;
	}

	.sidebar.sidebar-style-2 {
		display: none;
	}

	label {
		font-weight: 600;
	}

	.main-panel>.content {
		padding: 0 px !important;
		min-height: calc(100% - 123 px);
		margin-top: 0;
		overflow: hidden;
	}
	
	 .hidden{
 display: none;
 }   
</style>

<div class="wrapper ">
	<div class="main-panel" style="width: 100%;">
		<div class="content">
			<div class="panel-header bg-primary-gradient">
				<div class="page-inner py-4">
					<div
						class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
						<div>
							<h2 class="text-blue pb-2 fw-bold">
								Forgot Password
							</h2>
						</div>
						<div class="ml-md-auto">
							<a href="${contextPath}/"
								class="btn btn-sm btn-border btn-blue btn-round mr-2"><i
									class="fa fa-home"></i></a>
							<a href="#" class="btn btn-sm btn-border btn-blue btn-round mr-2">/
								Forgot Password</a>
						</div>
					</div>
				</div>
			</div>
<div class="content" style="margin-top: 0;">
	<div class="page-inner">
		<%@ include file="/WEB-INF/views/message.jsp"%>
		<div class="row">
			<div class="col-md-12">
				<div class="card card-outer">
					<div class="card-header">
						<h4 class="card-title">Forgot Password</h4>
					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-md-12">
								<form class="form-horizontal" id="formId"
									action="${contextPath}/public/forgot/password/filter" method="POST" >
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									<div class="row">
										<div class="col-md-12"
											style="display: flex; align-items: center; justify-content: center;">
											<div class="col-md-3 card"
												style="padding: 1rem 1rem 2rem 1rem;">
												<div class="col-md-12">
													<div class="col-md-12">
														<!-- <div class="col-md-6"> -->
															<div class="form-group">
																<label class="col-md-12 required">User Name: </label>
																<div class="col-md-12">
																	<input type="text" class="form-control form-control-sm" name="userName"
																		id="userName" maxlength="50" required>
																</div>
															</div>
														<!-- </div> -->
														<div class="col-md-12">
													    <input type="radio" id="age1" value="email" name="messageType" checked><label >Email Id: </label>
													    <input type="radio" id="age1" value="mobile" name="messageType" value="30"><label >Mobile No: </label>
													    </div>
													</div> 
														<div class="col-md-12" id="email">
														<!-- <div class="col-md-6"> -->
															<div class="form-group">
																<label class="col-md-12 required">Email Id: </label>
																<div class="col-md-12">
																	<input type="text" class="form-control form-control-sm"
																		name="emailId" id="emailId" maxlength="50" onchange="validateEmailId(this)"
																		required="required">
																</div>
															</div>
														<!-- </div> -->
													</div>
													<div class="col-md-12 hidden" id="mobile">
														<!-- <div class="col-md-6"> -->
															<div class="form-group">
																<label class="col-md-12 required">Mobile No: </label>
																<div class="col-md-12">
																	<input type="text" class="form-control form-control-sm NumbersOnly"
																		name="mobileNo" id="mobileNo" maxlength="10" 
																		required="required">
																</div>
															</div>
														<!-- </div> -->
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-12 text-center mt-3">
											<input type="button" class="btn btn-success btn-sm" 
												value="Submit" onclick="saveFormSubmit()" /> <a href="${contextPath}/"
												type="button" class="btn btn-warning btn-sm">Back</a>
										</div>
									</div>
								</form>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	</div>
</div>
</div>
<script src="${contextPath}/assets/vendor/bootbox5/bootbox.js"></script>
<script src="${contextPath}/assets/vendor/bootbox/bootbox.min.js"></script>
<script>

function saveFormSubmit() {
	if($("#userName").val()==""){
		bootbox.alert("Please enter user name");
		return false;
	}else if($("#emailId").val()==""  && $('input[name="messageType"]:checked').val()=='email'){
		bootbox.alert("Please enter email Id");
		return false;
	}else if($("#mobileNo").val()=="" && $('input[name="messageType"]:checked').val()=='mobile'){
		bootbox.alert("Please enter mobile no");
		return false;	
	}else{
		var validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
		var emailValue = $("#emailId").val();
		if (!validRegex.test(emailValue)) {
		    bootbox.alert("Please provide a valid email id.");
		    // $("#" + fieldId).val(""); // You may clear the input field if needed.
		    $("#emailId").focus();
		    return false;
		} else {
		    $("#formId").submit();
		}
		
		
	}
	
}

function validateEmailId(value) {
	var fieldId = value.id;
	var validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

	if (!$("#" + fieldId).val().match(validRegex)) {
		bootbox.alert("Please provide valid email id.");
		//$("#"+fieldId).val("");
		$("#" + fieldId).focus();
		return false;
	}

}
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

$("input[name$='messageType']").click(function() {
    var value = $(this).val();
    if(value=="mobile"){
    	$("#mobile").removeClass("hidden");
    	$("#email").addClass("hidden");
    	$("#emailId").val("");
    }else{
    	$("#email").removeClass("hidden");
    	$("#mobile").addClass("hidden");
    	$("#mobileNo").val("");
    }
}); 
 </script>