
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div class="content">
	<div class="container-fluied" style="margin-top: -100px; position: relative; z-index:99">
	<div class="page-inner mt-5 pb-0">
		<div class="row mt-2">
			<div class="col-md-12">
				<div class="card full-height">
					<div class="card-header">
						<!--<div class="panel-actions">
							<a href="#" class="fa fa-caret-down"></a>
						</div>-->
						<h6 class="card-title">
							<spring:message code="site.admin.mst.add.role"></spring:message>
						</h6>
					</div>
					<div class="card-body">
						<div class="row">
							<%@ include file="/WEB-INF/views/message.jsp"%>
							<div class="col-md-12">
								<form class="form-horizontal"
									action="${contextPath}/admin/role/addNupdate" method="POST">
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" /> <input type="hidden" name="roleId"
										value="${userDetails.roleId}" />
											<div class="row">
											<%-- <div class="col-md-3">
												<div class="form-group">
													<label class="col-md-12 required" for="complaintName"><spring:message code="site.admin.mst.role.name"></spring:message></label>
													<div class="col-md-12">
														<input type="text" id="roleName" name="roleName" value="${userDetails.displayName}" class="form-control form-control-sm" required="required">
													</div>
												</div>
											</div> --%>
											<div class="col-md-3">
												<div class="form-group">
													<label class="col-md-12 required" for="complaintName"><spring:message
															code="site.admin.mst.role.code"></spring:message></label>
													<div class="col-md-12">
														<input type="text" id="roleCode" name="roleCode"
															value="${userDetails.roleCode}"
															class="form-control form-control-sm" maxlength="30"
															required="required" onchange="roleCodeValidation();" placeholder="Role Code">
													</div>
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label class="col-md-12 required" for="complaintAge"><spring:message
															code="site.admin.mst.role.display.name"></spring:message></label>
													<div class="col-md-12">
														<input type="text" id="displayName" name="displayName"
															value="${userDetails.displayName}" maxlength="30"
															class="form-control form-control-sm" required="required" placeholder="Display Name">
													</div>
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label class="col-md-12 required" for="Email"><spring:message
															code="site.admin.mst.role.description"></spring:message></label>
													<div class="col-md-12">
														<input type="text" name="description" id="description"
															class="form-control form-control-sm" maxlength="30"
															value="${userDetails.description}" required="required" placeholder="Description">
													</div>
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label class="col-md-12 required" for="maxAssignments"><spring:message
															code="site.admin.mst.role.maxassignments"></spring:message></label>
													<div class="col-md-12">
														<input type="number" min="-1" name="maxAssignments"
															id="maxAssignments" class="form-control form-control-sm"
															maxlength="30" value="${userDetails.maxAssignments}"
															required="required" placeholder="Maximum Assignments">
													</div>
												</div>
											</div>
										</div>
									<div class="text-center mt-4">
										<input type="button"
											value="<spring:message code="site.common.submit"></spring:message>"
											class="btn btn-success btn-xs">
											 <a href="${contextPath}/" class="btn btn-warning btn-xs">Cancel</a>
								</form>
							</div>
						</div>
					</div>
				</div>
				</div>
			
		</div>
		</div>
		<div class="row mt-2">
			<div class="col-md-12">
				<div class="card full-height">
					<div class="card-header">
						<!--<div class="panel-actions">
							<a href="#" class="fa fa-caret-down"></a>
						</div>-->
						<h6>
							<spring:message code="site.admin.mst.role.list"></spring:message>
						</h6>
					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-md-12">
								<div class="no-more-tables">
									<table id="basic-datatables" class="DataTable display table table-bordered table-hover table-condensed cf">
										<thead class="cf">
											<tr>
												<th>Sl no.</th>
												<th><spring:message code="site.admin.mst.role.code"></spring:message></th>
												<%-- <th><spring:message code="site.admin.mst.role.name"></spring:message></th> --%>
												<th><spring:message
														code="site.admin.mst.role.display.name"></spring:message></th>
												<th><spring:message
														code="site.admin.mst.role.description"></spring:message></th>
												<th style="width: 150px;"><spring:message
														code="site.common.action"></spring:message></th>
											</tr>
										</thead>
										<tbody id="tbd">
											<c:forEach items="${roleList}" var="role"
												varStatus="roleCount">
												<tr>
													<td data-title="Sl. No.">${roleCount.count}</td>
													<td data-title="Role Code">${role.roleCode}</td>
													<%-- 														<td>${role.displayName}</td> --%>
													<td  data-title="Display Name">${role.displayName}</td>
													<td data-title="Description">${role.description}</td>
													<td data-title="Action">
														<button class="btn btn-warning btn-xs"
															onclick="editApplicationById('${role.roleId}')"
															title="Edit Role">
															<i class='bx bx-edit-alt'></i>
														</button>
														<button class="btn btn-primary btn-xs color"
															onclick="viewApplicationById('${role.roleId}')"
															title="View Role">
													<i class='bx bx-low-vision'></i>
														</button> <c:if test="${role.isActive eq true}">
															<button class="btn btn-danger btn-xs"
																onclick="lockUser('${role.roleId}')" title="Lock Role">
															<i class='bx bx-lock-open-alt' ></i>
															</button>
														</c:if> <c:if test="${role.isActive eq false}">
															<button class="btn btn-success btn-xs"
																onclick="unlockUser('${role.roleId}')"
																title="Unlock Role">
																<i class="fa fa-unlock-alt" aria-hidden="true"></i>
															</button>
														</c:if>
														<button class="btn btn-info btn-xs color"
															onclick="mapRoleLevel('${role.roleId}')"
															title="Link Role To Level">
														<i class='bx bx-link'></i>
														</button>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<form method="GET" id="formId"></form>
<form action="${contextPath}/admin/role/isActive" method="POST"
	id="lockNunlockForm">
	<input type="hidden" name="roleId" id="roleId" /> <input type="hidden"
		name="isActive" id="isActive" /> <input type="hidden"
		name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<script>
	function roleCodeValidation(){
		var roleCode = $("#roleCode").val();  
		$.ajax({
			type : "GET",
			url : "${contextPath}/admin/role/validate-role-code",
			data : {
				"roleCode" : roleCode,
			}, 
			success : function(response) {
				var val = JSON.parse(response); 
				if (val.isDuplicate == true) {
					bootbox.alert("Duplicate role code not allowed"); 
					$("#roleCode").val("");
				}  
			}, 
			error : function(error) {
				//bootbox.alert("Failure"); 
			}
		});
	} 
		function editApplicationById(id){
				    $("#formId").attr('action','${contextPath}/admin/role/edit/'+ id +'');
				    $("#formId").submit();
		}

		function viewApplicationById(id){
			         $("#formId").attr('action','${contextPath}/admin/role/view/'+ id +'');
					    $("#formId").submit();
		 }

		function unlockUser(roleId){
			 $("#isActive").val(true);
			 $("#roleId").val(roleId);
			    bootbox.confirm("Do you want to unlock this role?",
			            function(result) {
			                    if (result == true) {
			                            $("#lockNunlockForm").submit();
			                    }
			            });
				 }
		function lockUser(roleId){
			 $("#isActive").val(false);
			 $("#roleId").val(roleId);
			    bootbox.confirm("Do you want to lock this role?",
			            function(result) {
			                    if (result == true) {
			                            $("#lockNunlockForm").submit();
			                    }
			            });
				 }
		
		function mapRoleLevel(roleId){
			window.location = "${contextPath}/admin/role/roleLevelMap/" + roleId;
		}
	</script>

