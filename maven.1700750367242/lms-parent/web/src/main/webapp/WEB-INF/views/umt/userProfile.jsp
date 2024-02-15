<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link
	href="${contextPath}/assets/vendor/jquery_datepicker/jquery.datepick.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${contextPath}/assets/appJs/validation/common-utils.js"></script>
<style>
.alert-danger {
	border-color: #f3acbb !important;
	color: #730503;
}
/*Profile Pic Start*/
.picture-container {
	position: relative;
	cursor: pointer;
	text-align: center;
}

.picture {
	width: 106px;
	height: 106px;
	background-color: #999999;
	border: 4px solid #CCCCCC;
	color: #FFFFFF;
	border-radius: 0px;
	margin: 10px auto;
	overflow: hidden;
	transition: all 0.2s;
	-webkit-transition: all 0.2s;
}

.picture:hover {
	border-color: #2ca8ff;
}

.content.ct-wizard-green .picture:hover {
	border-color: #05ae0e;
}

.content.ct-wizard-blue .picture:hover {
	border-color: #3472f7;
}

.content.ct-wizard-orange .picture:hover {
	border-color: #ff9500;
}

.content.ct-wizard-red .picture:hover {
	border-color: #ff3b30;
}

.picture input[type="file"] {
	cursor: pointer;
	display: block;
	height: 100%;
	left: 0;
	opacity: 0 !important;
	position: absolute;
	top: 0;
	width: 100%;
}

.picture img {
	width: 100%;
	height: 106px;
}

.picture-src {
	width: 100%;
}
</style>
<div class="content">
	<div class="container-fluid" style="margin-top: -100px; position: relative; z-index:99">
	<!--<div class="panel-header bg-primary-gradient">
		<div class="page-inner py-4">
			<div
				class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
				<div>
					<h2 class="text-blue pb-2 fw-bold">User Profile</h2>
				</div>
				<div class="ml-md-auto mb-4 py-2 py-md-0">
					<a href="${contextPath}"
						class="btn btn-sm btn-border btn-blue btn-round mr-2"><i
						class="fa fa-home"></i></a> <a href="#"
						class="btn btn-sm btn-border btn-blue btn-round mr-2">/ User
						Profile</a>
				</div>
			</div>
		</div>
	</div>-->
	<div class="page-inner mt-5 pb-0">
		<div class="row mt-2">
			<%@ include file="/WEB-INF/views/message.jsp"%>
			<div class="col-md-12">
				<div class="card full-height">
					<div class="card-header">
						<!-- <div class="panel-actions">
							<a href="#" class="fa fa-caret-down"></a>
						</div>-->
						<h6 >User Profile</h6>
					</div>
					<div class="card-body">
						<div class="col-md-12">
							<form class="form-horizontal"
								action="${contextPath}/umt/user/profile/update" method="POST"
								enctype="multipart/form-data">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								<div class="row">
									<div class="col-md-10">
											<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="col-md-12 required" for="complaintName"><spring:message
															code="site.admin.user.first.name"></spring:message></label>
													<div class="col-md-12">
														<input type="text" id="firstName" name="firstName"
															value="${userDetails.firstName}" maxlength="150"
															class="form-control form-control-sm" required="required">
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="col-md-12 required" for="complaintAge"><spring:message
															code="site.admin.user.last.name"></spring:message></label>
													<div class="col-md-12">
														<input type="text" id="lastName" name="lastName"
															value="${userDetails.lastName}" maxlength="150"
															class="form-control form-control-sm" required="required">
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="col-md-12 required" for="mobile"><spring:message
															code="site.common.mobile"></spring:message></label>
													<div class="col-md-12">
														<input type="text" name="mobile" id="mobile"
															class="form-control form-control-sm NumbersOnly"
															maxlength="10" value="${userDetails.mobile}"
															onchange="validateMobileNo(this);" required="required">
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="col-md-12 required" for="Email"><spring:message
															code="site.common.email"></spring:message></label>
													<div class="col-md-12">
														<input type="text" name="email" id="email"
															class="form-control form-control-sm emailsOnly"
															maxlength="150" onchange="validateEmail(this);"
															value="${userDetails.email}" required="required">
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="col-md-12 required" for="complaintName"><spring:message
															code="site.admin.user.name"></spring:message></label>
													<div id="hideUserId">
														<div class="col-md-12">
															<input type="text" id="userName" name="userName"
																value="${userDetails.userName}" maxlength="150"
																class="form-control form-control-sm" required="required"
																onchange="userNameValidation();">
														</div>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="col-md-12 required" for="Email">Designation</label>
													<div class="col-md-12">
														<input type="text" name="designation" id="designation"
															class="form-control form-control-sm" required
															value="${userDetails.designation}" maxlength="200">
													</div>
												</div>
											</div>
											<%-- <div class="col-md-4">
												<div class="form-group">
													<label class="col-md-12 required" for="applicantDob"><spring:message code="site.common.dob"></spring:message></label>
													<div class="col-md-12 datepicker_con">
																<input type="text" name="dateOfBirth" id="dateOfBirth" class="form-control form-control-sm" style="pointer-events: none;" placeholder="dd/mm/yyyy"
																	value="<fmt:formatDate pattern="dd/MM/yyyy" value="${userDetails.dateOfBirth}"/>"
																	required="required">
															</div> 
												</div>
											</div> --%>
											<%-- <div class="col-md-3">
												<div class="form-group">
													<label class="col-md-12 required" for="Email">Designation</label>
													<div class="col-md-12">
														<input type="text" name="designation" id="designation" class="form-control form-control-sm" value="${userDetails.designation}" required>   
													</div>
												</div>
											</div> --%>
										</div>
										</div>
										<div class="col-md-2">
											<div class="row">
												<div class="col-md-12">
													<div class="container">
														<div class="picture-container">
															<div class="picture">
																<c:choose>
																	<c:when test="${not empty userDetails.profilePhoto}">
																		<img id="uph" src="${contextPath}/assets/avattar.png"
																			aria-hidden="true" alt="User Icon" />
																	</c:when>
																	<c:otherwise>
																		<img id="uph"
																			src="${contextPath}/assets/avattar.png"
																			aria-hidden="true" alt="User Icon">
																		
																		<span class="ph-text">Photo</span>
																	</c:otherwise>
																</c:choose>
																<input type="file"
																	onchange="document.getElementById('uph').src = window.URL.createObjectURL(this.files[0]);validateImage(this);"
																	name="userProfileImage" id="userProfileImage">
															</div>
														</div>
													</div>

												</div>
											</div>

										</div>
										<div class="col-md-12 text-center mt-4">
											<button type="submit" class="btn btn-sm btn-success"
												id="submitBtn">
												<spring:message code="site.common.submit"></spring:message>
											</button>
											<a class="btn btn-sm btn-warning" href='${contextPath}/'>
												<spring:message code="site.common.back"></spring:message>
											</a>
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
<script> 	
function userNameValidation(){
	var userName = $("#userName").val();  
	$.ajax({
		type : "GET",
		url : "${contextPath}/umt/user/validate-user-name",
		data : {
			"userName" : userName,
		}, 
		success : function(response) {
			var val = JSON.parse(response); 
			if (val.isDuplicate == true) {
				bootbox.alert("Duplicate user name not allowed"); 
				var html ='<div class="col-md-12"><input type="text" id="userName" name="userName" value="${userDetails.userName}" class="form-control form-control-sm" required="required" onchange="userNameValidation()"></div>'
				$("#hideUserId").empty().append(html);
			}  
		}, 
		error : function(error) {
			//bootbox.alert("Failure"); 
		}
	});
}
$(function() {
	$('.datepicker_con>input').datepick(
			{
				onShow : $.datepick.monthOnly,
				dateFormat : 'dd/mm/yyyy',
				yearRange : 'c-100:c+5',
				showOnFocus : true,
				showTrigger : '<button type="button" class="trigger">'
						+ '<i class="fa fa-calendar"></i></button>'
			});
   }); 
   
function validateImage(that){
	var ValidFileExtension = [ 'jpg','jpeg'];
	if ($.inArray($("#"+that.id).val().split('.').pop().toLowerCase(), ValidFileExtension) == -1) {
		$("#"+that.id).val("");
		bootbox.alert("Sorry! allowed format is jpg or jpeg only.");
		event.preventDefault();
		return false;
	}
	if ((that.files[0].size) > 2097152) {
		$(that).val("");
		bootbox.alert("File size exceeds maximum image size of 1 MB!");
		return false;
	}
	
}
</script>