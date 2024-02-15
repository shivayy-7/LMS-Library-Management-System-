
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
						<!-- <div class="panel-actions">
							<a href="#" class="fa fa-caret-down"></a>
						</div>-->
						<h6 class="card-title">Access Level Masters</h6>
					</div>
					<div class="card-body">
						<div class="row">
							<%@ include file="/WEB-INF/views/message.jsp"%>
							<div class="col-md-12">
								<form class="form-horizontal" action="${contextPath}/"
									method="POST" id="userForm">
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" /> <input type="hidden"
										name="mstLevelId" />
									<div class="col-md-12" style="padding-top: 15px;">
										<div class="col-sm-12">
											<div>
												<table class="DataTable table table-bordered table-sm">
													<thead>
														<tr>
															<th>Sl #</th>
															<th>Display Name</th>
															<th>Level Code</th>
															<th>Source Entity</th>
															<th>View Entity</th>
															<th>Actions</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${lstAccessLevels}" var="acl"
															varStatus="cnt">
															<tr>
																<td>${cnt.count}</td>
																<td>${acl.displayName}</td>
																<td>${acl.levelCode}</td>
																<td>${acl.levelEntityName}</td>
																<td>${acl.displayViewName}</td>
																<td></td>
															</tr>
														</c:forEach>

													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class=" text-center mt-4">
										<input type="button" class="btn btn-success btn-sm"
											value="Submit" onclick="return validateForm()"> <a
											href="${contextPath}/admin/role/list.htm" type="button"
											class="btn btn-warning btn-sm">Back</a>
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
