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
								Lodge Grievence
							</h2>
						</div>
						<div class="ml-md-auto">
							<a href="${contextPath}/home"
								class="btn btn-sm btn-border btn-blue btn-round mr-2"><i
									class="fa fa-home"></i></a>
							<a href="#" class="btn btn-sm btn-border btn-blue btn-round mr-2">/
								Lodge Grievence</a>
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
						<h4 class="card-title">Lodge Grievence</h4>
					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-md-12">
								<form class="form-horizontal" id="addGrievenceFrm"
									action="${contextPath}/public/lodgeGrievence"  method="POST" >
									
									<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" /> <input type="hidden" id="grievenceId"
											name="grievenceId" value="${grievenceData.grievenceId }" />
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
																<label class="col-md-12 required"> Name: </label>
																<div class="col-md-12">
																	<input type="text" class="form-control form-control-sm" name="grievenceName"
																		id="grievenceName" maxlength="50" required>
																</div>
															</div>
														<!-- </div> -->
													</div>
													<div class="col-md-12">
														<!-- <div class="col-md-6"> -->
															<div class="form-group">
																<label class="col-md-12 required">Phone Number: </label>
																<div class="col-md-12">
																	<input type="text" class="form-control form-control-sm" name="phoneNo"
																		id="phoneNo" maxlength="10" required>
																</div>
															</div>
														<!-- </div> -->
													</div>
													
													<div class="col-md-12">
														<!-- <div class="col-md-6"> -->
															<div class="form-group">
																<label class="col-md-12 required">Shop Code: </label>
																<div class="col-md-12">
																	<input type="text" class="form-control form-control-sm" name="shopId"
																		id="shopId" maxlength="11" required>
																</div>
															</div>
														<!-- </div> -->
													</div>
													<div class="col-md-12">
														<!-- <div class="col-md-6"> -->
															<div class="form-group">
																<label class="col-md-12 required">Description: </label>
																<div class="col-md-12">
																	<textarea  class="form-control form-control-sm" name="description"
																		id="description" maxlength="150" required></textarea>
																</div>
															</div>
														<!-- </div> -->
													</div>
														<div class="col-md-12">
														<!-- <div class="col-md-6"> -->
															<div class="form-group">
																<label class="col-md-12">Email Id: </label>
																<div class="col-md-12">
																	<input type="text" class="form-control form-control-sm"
																		name="emailId" id="emailId" maxlength="30" onblur="checkDuplicateEmailId(this);"
																		required="required">
																</div>
															</div>
														<!-- </div> -->
													</div>
												</div>
											</div>
										</div>
										<div>
										<div class="col-md-12 text-center" >
										<button class="btn btn-sm btn-primary" type="button" onclick="submitFormData()">Save</button>
										
														<a href="${contextPath}/home" type="button" class="btn btn-warning btn-sm ml-1">Cancel</a>	
														</div>		
										</div>
										<%-- <div>
												<div class="col-md-2">
													<div class="text-center" style="margin-top: 20px;">
													<c:choose>
													<c:when test = "${empty grievenceData}" >
													   <button class="btn btn-sm btn-primary" type="button" onclick="submitFormData()">Save</button>
										
														<a href="${contextPath}/home" type="button" class="btn btn-warning btn-sm ml-1">Cancel</a>			
													</c:when>
													<c:when test = "${not empty grievenceData}" >
													  
														<c:choose>
														<c:when test = "${view eq 'view'}" >
														   <button class="btn btn-sm btn-primary" type="button" onclick="submitFormData()" disabled >Update</button>
														   <a href="${contextPath}/master/getVendorPage" type="button" class="btn btn-warning btn-sm ml-1">Cancel</a>	
														</c:when>
														<c:otherwise>
															<button class="btn btn-sm btn-primary"  type="button" onclick="submitFormData()">Update</button>
														<a href="${contextPath}/master/getVendorPage" type="button" class="btn btn-warning btn-sm ml-1">Cancel</a>	
      												   </c:otherwise>
														</c:choose>
													</c:when>
															</c:choose>
													</div>
												</div>
											</div> --%>
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
<script>

function submitFormData() {
	var grievenceName = $("#grievenceName").val();  
	if ($("#grievenceName").val() == "") {
				bootbox.alert("Please Enter Name.");
				return false;
		}
	if($("#phoneNo").val()==""){
		bootbox.alert("Enter Phone Number");
		return false;
	}
	if($("#shopId").val()==""){
		bootbox.alert("Enter Shop Code");
		return false;
	}
	if($("#description").val()==""){
		bootbox.alert("Enter  Description");
		return false;
	}
	else{
		$("#addGrievenceFrm").submit();
	}
	
}

function checkDuplicateEmailId(that) {
	var emailId = $(that).val();
	var hdnEmailId = $("#hdnEmailId").val();
	if (emailId != "" && emailId != hdnEmailId) {
		$.ajax({
			type : 'GET',
			url : '${contextPath}/webapi/checkDuplicateEmailId',
			data : {
				"emailId" : emailId
			},
			success : function(response) {
				var val = JSON.parse(response);
				if (val.duplicate == true) {
					bootbox.alert("Duplicate Email Id Not Allowed");
					$("#" + that.id).val("");
					return false;
				} else {
					return true;
				}
			},
			error : function(error) {
				//bootbox.alert("Failure"); 
			}
		});

	}
}

 </script>