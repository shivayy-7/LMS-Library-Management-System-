	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 

<script src="${contextPath}/assets/appJs/framework/data-table.js" type="text/javascript"></script>
<div class="content">
		<div class="container-fluid" style="margin-top: -100px; position: relative; z-index:99">		
			<div class="row inner-cardbox">
			<%@ include file="/WEB-INF/views/message.jsp"%>
				<h6>Librarian List</h6><hr>
				<div class="table-responsive">
				<table id="example" class="table table-striped dataTable no-footer">
											<thead>
												<tr>
													<th>Sl No.</th>
													<th>Librarian Name</th>
													<th>Gender</th>
													<th>Library</th>
													<th>Address</th>
													<th>Email</th>
													<th>Mobile</th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${librarianDetailsList}" var="list"
													varStatus="status">
													
													<tr>
														<td>${status.count}</td>
														<td>${list.librarianName}</td>
														<td>${list.gender.genderNameEN}</td>
														<td>${list.library.libName}</td>
														<td>${list.librarianAddress}</td>
														<td>${list.email}</td>
														<td>${list.mobile}</td>
															<td><a class="btn btn-warning btn-sm" id="btnEdit"
																href="${contextPath}/mst/editLibrarianDetails?librarianId=${list.librarianId}&status=EDIT">Edit
															</a>
															 <a class="btn btn-info btn-sm" id="btnView"
           href="${contextPath}/mst/editLibrarianDetails?librarianId=${list.librarianId}&status=VIEW">View
        </a>
        
        <c:if test="${list.isActive eq true}">
																	<button
																		onclick="checkActiveLibrarian('${list.librarianId}')"
																		class="btn btn-info btn-xs" aria-hidden="true"
																		data-toggle="tooltip" title="Inactive Data"><i
																		class="fa fa-lock"></i></button>
																		
																</c:if>
                                                           <c:if test="${list.isActive eq false}">
																	<button
																		onclick="checkActiveLibrarian('${list.librarianId}')"
																		class="btn btn-danger btn-xs" aria-hidden="true"
																		data-toggle="tooltip" title="Active Data"><i
																		class="fa fa-lock"></i></button>
																</c:if>
													
        
															</td>


														</tr>
												</c:forEach>												
											</tbody>
										</table>
										</div>
									</div>
								</div>
							</div>
<form action="./checkActivInactiveData" method="POST" id="formId">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />    
	<input type="hidden" name="librarianId" id="librarianActiveId" />
</form>
				
<script>


function checkActiveLibrarian(librarianId) {
    $("#librarianActiveId").val(librarianId);
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
	   
	 <script>
$(document).ready(function() {
    $('#example').dataTable();
} );
</script> 
	
