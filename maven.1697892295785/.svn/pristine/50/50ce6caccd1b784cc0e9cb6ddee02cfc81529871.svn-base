	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 

<script src="${contextPath}/assets/appJs/framework/data-table.js" type="text/javascript"></script>

<style>
a#btnView {
    background: #6aa9c1;
    color: #fff !important;
}
</style>
<div class="content">
		<div class="container-fluid" style="margin-top: -100px; position: relative; z-index:99">		
			<div class="row inner-cardbox ">
			<%@ include file="/WEB-INF/views/message.jsp"%>
				<h6>Author List</h6><hr>
										<div class="table-responsive">
										<table id="example" class="table table-striped dataTable no-footer ">
											<thead>
												<tr>
													<th>Sl No.</th>
													<th>Author Name</th>
													<th>Gender</th>
													<th>Address</th>
													<th>Email</th>
													<th>Mobile</th>
													<th style="min-width:75px !important;">Action</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${authorDetailsList}" var="authorDetailsList"
													varStatus="status">
													
													<tr>
														<td>${status.count}</td>
														<td>${authorDetailsList.authorName}</td>
														<td>${authorDetailsList.gender.genderNameEN}</td>
														<td>${authorDetailsList.address}</td>
														<td>${authorDetailsList.mail}</td>
														<td>${authorDetailsList.mobileNumber}</td>
															<td><a class="btn btn-warning btn-sm" id="btnEdit"
																href="${contextPath}/mst/editAuthorDetails?authorId=${authorDetailsList.authorId}&status=EDIT">
																<i class='bx bx-pencil'></i>
															</a>
															 <a class="btn btn-info btn-sm" id="btnView"
           href="${contextPath}/mst/editAuthorDetails?authorId=${authorDetailsList.authorId}&status=VIEW"><i class='bx bx-show'></i>
        </a> <c:if test="${authorDetailsList.isActive eq true}">
										<button
											onclick="checkActiveAuthor('${authorDetailsList.authorId}')"
											class="btn btn-info btn-xs" aria-hidden="true"
											data-toggle="tooltip" title="Inactive Data">
											<i class="fa fa-lock"></i>
										</button>

									</c:if> <c:if test="${authorDetailsList.isActive eq false}">
										<button
											onclick="checkActiveAuthor('${authorDetailsList.authorId}')"
											class="btn btn-danger btn-xs" aria-hidden="true"
											data-toggle="tooltip" title="Active Data">
											<i class="fa fa-lock"></i>
										</button>
									</c:if></td>


														</tr>
												</c:forEach>												
											</tbody>
										</table>
										</div>
									</div>
								</div>
							</div>
<form action="./checkActivInactiveAuthorData" method="POST" id="formId">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />    
	<input type="hidden" name="authorId" id="authorActiveId" />
</form>	
<script>
$(document).ready(function() {
    $('#example').dataTable();
} );
</script>			
<script>
function checkActiveAuthor(authorId) {
    $("#authorActiveId").val(authorId);
    bootbox.confirm({
        message: "Do you want to Activated / Deactivated ?",
        callback: function (proceed) {
            if (proceed) {
                $("#formId").submit();
            }
        }
    });
}

	</script>
	   
	  
	
