
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div class="content">
	<div class="panel-header bg-primary-gradient">
		<div class="page-inner py-4">
			<div
				class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
				<div>
					<h2 class="text-blue pb-2 fw-bold">
						Add Content
					</h2>
				</div>
				<div class="ml-md-auto py-2 py-md-0">
					<a href="${contextPath}"
						class="btn btn-xs btn-border btn-blue btn-round mr-2"><i
						class="fa fa-home"></i></a> <a href="#"
						class="btn btn-xs btn-border btn-blue btn-round mr-2"> Add Content</a>
				</div>
			</div>
		</div>
	</div>
	<div class="page-inner mt-5 pb-0">
		<div class="row mt-2">
			<div class="col-md-12">
				<div class="card full-height">
					<div class="card-header">
						<div class="panel-actions">
							<a href="#" class="fa fa-caret-down"></a>
						</div>
						<h4 class="card-title">
							Add Content
						</h4>
					</div>
					<div class="card-body">
						<div class="row">
							<%@ include file="/WEB-INF/views/message.jsp"%>
							<div class="col-md-12">
								<form class="form-horizontal"
									action="${contextPath}/admin/content/addNupdt" method="POST"  enctype="multipart/form-data">
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" /> <input type="hidden" name="contentId"
										value="${applicationContent.contentId}" />
										<div class="col-md-12">
											<div class="col-md-3">
												<div class="form-group">
													<label class="col-md-12 required" for="complaintName">Content Key</label>
													<div class="col-md-12">
														<input type="text" id="contentKey" name="contentKey"
															value="${applicationContent.contentKey}" onchange="checkDupContentKey(this.value)"
															class="form-control form-control-sm" maxlength="50"
															required="required"  placeholder="Content Key" ${not empty applicationContent.contentKey ? 'readonly' : ''}>
														<input type="hidden" id="hdnContentKey" value="${applicationContent.contentKey}">	
													</div>
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label class="col-md-12 required" for="complaintAge">Content Value</label>
													<div class="col-md-12">
														<input type="text" id="contentValue" name="contentValue"
															value="${applicationContent.contentValue}" maxlength="50"
															class="form-control form-control-sm" required="required" placeholder="Content Value">
													</div>
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label class="col-md-12" for="complaintAge">Upload Image</label>
													<div class="col-md-12">
														<input type="file" id="image" name="image" onchange="uploadImage(this.value);"
															class="form-control form-control-sm" placeholder="Content Value">
													</div>
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label class="col-md-12 required" for="complaintAge">Is Active</label>
													<div class="col-md-12">
													    <select name="isActive" id="isActive" class="form-control form-control-sm" required="required">
															 <option value="true" ${applicationContent.isActive eq true ? 'selected':''} >Active</option>
															 <option value="false" ${applicationContent.isActive eq false ? 'selected':''} >In Active</option>
														 </select>
													</div>
												</div>
											</div>
											
										</div>
									<div class="card-footer text-center mt-2">
										<input type="submit"
											value="<spring:message code="site.common.submit"></spring:message>"
											class="btn btn-success btn-xs"> <a
											href="${contextPath}/" class="btn btn-warning btn-xs">Cancel</a>
									</div>
								</form>
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
						<div class="panel-actions">
							<a href="#" class="fa fa-caret-down"></a>
						</div>
						<h4 class="card-title">
							Content List
						</h4>
					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-md-12">
								<div class="no-more-tables">
									<table id="basic-datatables" class="DataTable display table table-bordered table-hover table-condensed cf">
										<thead class="cf">
											<tr>
												<th>Sl no.</th>
												<th>Content Key</th>
												<th>Content Value</th>
												<th>Status</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody id="tbd">
											<c:forEach items="${applicationContentLst}" var="applicationContent"
												varStatus="roleCount">
												<tr>
													<td data-title="Sl. No.">${roleCount.count}</td>
													<td data-title="Content Key">${applicationContent.contentKey}</td>
													<td  data-title="Content Value">${applicationContent.contentValue}</td>
													<td>${applicationContent.isActive?'Active':'In Active'}</td>
													<td data-title="Action">
														<button class="btn btn-warning btn-xs"
															onclick="editApplicationById('${applicationContent.contentId}')"
															title="Edit Role">
															<i class="fa fa-edit"></i>
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

<script>
	function checkDupContentKey(contentKey){
		var hdnContentKey = $("#hdnContentKey").val();  
		if(hdnContentKey!=contentKey){
			$.ajax({
				type : "GET",
				url : "${contextPath}/admin/content/validate-content-key",
				data : {
					"contentKey" : contentKey,
				}, 
				success : function(response) {
					var val = JSON.parse(response); 
					if (val.isDuplicate == true) {
						bootbox.alert("Duplicate content key not allowed"); 
						$("#roleCode").val("");
					}  
				}, 
				error : function(error) {
					//bootbox.alert("Failure"); 
				}
			});
		}
			
		} 
	
	
		function editApplicationById(id){
				    $("#formId").attr('action','${contextPath}/admin/content/edit/'+ id +'');
				    $("#formId").submit();
		}

		function uploadImage(image){
			if(image!=""){
				$("#contentValue").val($("#contentKey").val());
			}else{
				$("#contentValue").val('');
			}
		}
	</script>

