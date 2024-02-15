<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script src="${contextPath}/assets/appJs/validation/common-utils.js"></script>
<script src="${contextPath}/assets/appJs/validation/AadharVaidator.js"></script> 
<style>
.Resolved{
background: green !important;
}
.New{
background: red !important;
}
</style>
           <div class="content">
				<div class="panel-header bg-primary-gradient">
					<div class="page-inner py-4">
						<div class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
							<div>
								<h2 class="text-blue pb-2 fw-bold">
									Grievence View  
								</h2>
							</div>
							<div class="ml-md-auto">
								<a href="${contextPath}/" class="btn btn-sm btn-border btn-blue btn-round mr-2"><i class="fa fa-home"></i></a>
								<a href="#" class="btn btn-sm btn-border btn-blue btn-round mr-2">/ Grievence View</a>
							</div>
						</div>
					</div>

				</div>
				<div class="page-inner">
					<div class="row mt-4">
						<%@ include file="/WEB-INF/views/message.jsp"%>
						<div class="col-md-12">
							<div class="card full-height">
								<div class="card-header">
									<div class="panel-actions">
										<a href="#" class="fa fa-caret-down"></a>
									</div>
									<h4 class="card-title">Grievence View </h4>
								</div>
								<div class="card-body" style="">
									<div class="row">
										<div class="col-md-12">
										<form id="viewForm">
												<div class="col-md-12">
													<div class="col-md-3">
															<div class="form-group">
																<label class="col-md-12"> Name: </label>
																<div class="col-md-12">
																	<input type="text" class="form-control form-control-sm" name="grievenceName"
																	readonly	id="grievenceName" maxlength="50" value="${grievenceView.grievenceName}">
																</div>
															</div>
													</div>
													<div class="col-md-3">
														<!-- <div class="col-md-6"> -->
															<div class="form-group">
																<label class="col-md-12">Phone Number: </label>
																<div class="col-md-12">
																	<input type="text" class="form-control form-control-sm" name="phoneNo"
																	readonly	id="phoneNo" maxlength="10" value="${grievenceView.phoneNo}">
																</div>
															</div>
														<!-- </div> -->
													</div>
													
													<div class="col-md-3">
														<!-- <div class="col-md-6"> -->
															<div class="form-group">
																<label class="col-md-12">Shop Id: </label>
																<div class="col-md-12">
																	<input type="text" class="form-control form-control-sm" name="shopId"
																		readonly id="shopId" maxlength="11" value="${grievenceView.shopId}">
																</div>
															</div>
														<!-- </div> -->
													</div>
													<div class="col-md-3">
														<!-- <div class="col-md-6"> -->
															<div class="form-group">
																<label class="col-md-12">Description: </label>
																<div class="col-md-12">
																	<textarea class="form-control form-control-sm" name="description"
																	readonly	id="description" maxlength="50" >${grievenceView.description}</textarea>
																</div>
															</div>
														<!-- </div> -->
													</div>
														<div class="col-md-3">
														<!-- <div class="col-md-6"> -->
															<div class="form-group">
																<label class="col-md-12">Email Id: </label>
																<div class="col-md-12">
																	<input type="text" class="form-control form-control-sm"
																	readonly	name="emailId" id="emailId" maxlength="30" 
																		value="${grievenceView.emailId}">
																</div>
															</div>
														<!-- </div> -->
													</div>
												</div>
												</form>
											</div>
										</div>
										<div class="col-md-12 text-center" >
<!-- 										<button class="btn btn-sm btn-primary" type="button" onclick="submitFormData()">Save</button> -->
										
														<a href="${contextPath}/grievence/list" type="button" class="btn btn-warning btn-sm ml-1">Cancel</a>	
														</div>	
										<div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
<form method="GET" id="grievForm">
</form>

<script type="text/javascript" src="${contextPath}/assets/js/plugin/html2excel/jquery.table2excel.min.js"></script>
<script>
	
	function viewGrievFormById(id) {
		$("#grievForm").attr('action','${contextPath}/grievence/view/' + id + '');
		$("#grievForm").submit();
	}
	
	function changeStatus(id){
		$("#grievForm").attr('action','${contextPath}/grievence/changeStatus/' + id + '');
		$("#grievForm").submit();	
	}
</script>	


<script>
$(document).ready(function() {
	$("#viewForm input").prop("disabled", true);
	$('#viewForm select').prop('disabled', true);
	$('#viewForm textarea').prop('disabled', true);
	$(".datepick-trigger").prop('disabled', true);
});
</script>		