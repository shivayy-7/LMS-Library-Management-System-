<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<style>
.alert {
	box-shadow: none;
}

.alert-info {
	border-left: 6px solid #48ABF7
}
</style>
<div class="content" style="margin-top: 0;">
	<div class="page-inner">
		<%@ include file="/WEB-INF/views/message.jsp"%>
		<div class="row" style="margin-top: 80px;">
			<div class="col-md-12">
				<div class="card card-outer">
					<div class="card-header">
						<h4 class="card-title">Change Password</h4>
					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-md-12">
								<%@ include file="/WEB-INF/views/message.jsp"%>
								<form class="form-horizontal"
									action="${contextPath}/umt/user/change/password" method="POST"
									onsubmit="return validateSubmit();">
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
									<div class="row">
										<div class="col-md-12"
											style="display: flex; align-items: center; justify-content: center;">
											<div class="col-md-6 card"
												style="padding: 1rem 1rem 2rem 1rem;">
												<div class="col-md-12">
													<div class="col-md-12">
														<div class="col-md-12">
															<div class="form-group">
																<label class="col-md-12">User Id: </label>
																<div class="col-md-12">
																	<input type="text" class="form-control form-control-sm" name="userId"
																		id="userId" value="${userDetails.userName}" readonly>
																</div>
															</div>
														</div>
														<div class="col-md-12">
															<div class="form-group">
																<label class="col-md-12">Current Password: </label>
																<div class="col-md-12">
																	<input type="password" id="currPass" class="form-control form-control-sm"
																		onchange="checkCurrentPassword(this.value);"
																		required="required">
																</div>
															</div>
														</div>
														<div class="col-md-12">
															<div class="form-group">
																<label class="col-md-12">New Password: </label>
																<div class="col-md-12">
																	<input type="password" class="form-control form-control-sm" disabled
																		name="txtPass" id="txtPass" maxlength="15"
																		onchange="ensureStrongPassword(this);"
																		required="required">
																</div>
															</div>
														</div>
														<div class="col-md-12">
															<div class="form-group">
																<label class="col-md-12">Confirm Password: </label>
																<div class="col-md-12">
																	<input type="password" class="form-control form-control-sm" disabled
																		name="txtRePass" id="txtRePass" maxlength="15"
																		onblur="clearData();"> <span id="consms"
																		style="font-size: 14px; font-style: italic;"></span>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-12 text-center mt-3">
											<input type="submit" class="btn btn-success btn-sm" id="sbmtBtn"
												value="Change Password" disabled/> <a href="${contextPath}/"
												type="button" class="btn btn-warning btn-sm">Back</a>
										</div>
									</div>

									<div class="row">
										<div class="col-md-12 pl-2 mt-3">
											<div class="alert alert-info" id="lblPass"
												style="text-align: left;">
												Password must be between 8 - 15 characters <br /> - Must
												have one lower case alphabet. <br /> - Must have one upper
												case alphabet. <br /> - Must have one number. <br /> -
												Must have one special sign (@/*/#)
											</div>
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
<script>
					var count = 0;
					$("#txtRePass").keyup(function () {

						var pass = $("#txtPass").val();
						var rePass = $("#txtRePass").val();
						if ((rePass == null || rePass == "")) {
							$("#consms").text(" ");
							$("#consms").css("color", "black");

						} else {

							if (pass == rePass) {
								$("#consms").text(" ");
								$("#consms").css("color", "green");
								count = 1;
							} else {
								$("#consms").text("Password not match..!!!");
								$("#consms").css("color", "red");
								count = 0;
							}
						}

					});

					$('#txtPass').keyup(function () {

						var pass = ensureStrongPassword(this);
						/* 	console.log (pass); */
						if (pass !== true) {
							$('#lblPass').removeClass('alert-info');
							$('#lblPass').addClass('alert-danger');
						} else {
							$('#lblPass').removeClass('alert-danger');
							$('#lblPass').addClass('alert-info');
						}
					});

					function clearData() {

						if (count == 0) {
							$("#txtRePass").val("");
						}
					}
					/*function ensureStrongPassword(element) {
						 var re = /^([a-zA-Z0-9@*#]{8,15})$/;
						var sc = /[-!$%^&*()_+|~=`{}[:;<>?,.@#\]]/g;

						var letters = element.value;
						var uperCaseFlag = false;
						var lowerCaseFlag = false;
						var numberFlag = false;
						var spCharFlag = false;
						var lengthFlag = false;

						for (var i = 0; i < letters.length; i++) {
							if (letters[i] === letters[i].toUpperCase()) {
								uperCaseFlag = true;
							}
							if (letters[i] === letters[i].toLowerCase()) {
								lowerCaseFlag = true;
							}
							var matches = letters[i].match(/\d+/g);
							if (matches != null) {
								numberFlag = true;
							}
							if (letters[i].match(sc)) {
								spCharFlag = true;
							}
						}
						if (letters.match(re)) {
							lengthFlag = true;
						}
						
						if (uperCaseFlag == true && lowerCaseFlag == true && numberFlag == true
							&& spCharFlag == true && lengthFlag == true) {
							return true;
						} else {
							return false;
						} 

					}*/
					
					function ensureStrongPassword(element) {
					    var re = /^([a-zA-Z0-9@*#]{8,15})$/;

					    // Define regular expressions for checking special characters and numbers
					    var sc = /[-!$%^&*()_+|~=`{}[:;<>?,.@#\]]/;
					    var num = /\d/;

					    var password = element.value;
					    var upperCaseFlag = false;
					    var lowerCaseFlag = false;
					    var numberFlag = false;
					    var spCharFlag = false;
					    var lengthFlag = false;

					    for (var i = 0; i < password.length; i++) {
					        if (password[i] === password[i].toUpperCase()) {
					            upperCaseFlag = true;
					        }
					        if (password[i] === password[i].toLowerCase()) {
					            lowerCaseFlag = true;
					        }
					        if (num.test(password[i])) {
					            numberFlag = true;
					        }
					        if (sc.test(password[i])) {
					            spCharFlag = true;
					        }
					    }
					    if (re.test(password)) {
					        lengthFlag = true;
					    }
					    return upperCaseFlag && lowerCaseFlag && numberFlag && spCharFlag && lengthFlag;
					}

					function validateSubmit() {
						let txtP = $('#txtPass').val();
						if (txtP != '') {
							let txtRP = $("#txtRePass").val();
							if (txtP !== txtRP) {
								bootbox.alert('Passwords do not match');
								return false;
							}
							var elem = document.getElementById('txtPass');
							var ok = ensureStrongPassword(elem);
							if (!ok) {
								bootbox.alert('Password does not match Password Criteria.');
							}
							return ok;
						}
					}
					
					function checkCurrentPassword(pass){
						if (pass != "") {
							$.ajax({
								type : "GET",
								url : "${contextPath}/umt/user/checkCurrentPassword",
								data : {
									"pass" : pass,
								},
							success : function(response) {
								var val = JSON.parse(response);
								if (val.validated == true) {
									 $('#sbmtBtn').prop("disabled", false);
									 $('#txtPass').prop("disabled", false);
									 $('#txtRePass').prop("disabled", false);
									return true;
								} else {
									bootbox.alert("Invalid password");
									$("#currPass").val("");
									return false;
								}
							},
							error : function(error) {
								//bootbox.alert("Failure"); 
							}
						 });
						}
				  }
				</script>