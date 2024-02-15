	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 
			<div class="content">
				<div class="panel-header bg-primary-gradient">
					<div class="page-inner py-4">
						<div class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
							<div>
								<h2 class="text-blue pb-2 fw-bold">Login History</h2>
							</div>
							<div class="ml-md-auto mb-4 py-2 py-md-0">
								<a href="${contextPath}/" class="btn btn-sm btn-border btn-blue btn-round mr-2"><i class="fa fa-home"></i></a>
								<a href="#" class="btn btn-sm btn-border btn-blue btn-round mr-2">/ Login History</a>
							</div>
						</div>
					</div>
				</div>
				<div class="page-inner">
					<div class="row mt-2">
					 <%@ include file="/WEB-INF/views/message.jsp"%>
						<div class="col-md-12">
							<div class="card full-height">
								<div class="card-header">
									<h4 class="card-title">Login History</h4>
								</div>
								<div class="card-body">
									<div class="table-responsive">
										<table id="basic-datatables" class="display table table-bordered table-hover DataTable" >
											<thead>
												<tr>
												    <th>Sl.No</th>
												    <th>User Name</th>
													<th>Email Id</th>
													<th>Login Type</th>
													<th>IP Address</th>
													<th>Operating System</th>
													<th>Browses Details</th>
													<th>Login Date</th>
													<th>Logout Date</th>    													
												</tr>
											</thead>
											<tbody id="tbd">
										 	   <c:forEach items="${userHistoryList}" var="userHistory" varStatus="count"> 
													<tr>
													    <td> ${count.count} </td>
													    <td>${userHistory.userName}</td>
														<td>${userHistory.email}</td>
														<td>${userHistory.loginType}</td>
														<td>${userHistory.ipAddress}</td>
													    <td>${userHistory.osDetails}</td>
														<td>${userHistory.browserDetails}</td>
														<td>${userHistory.loggedInDate}</td>
														<td>${userHistory.loggedOutDate}</td>
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
		

		  <!-- Modal End -->
<form  method="GET" id="formId">
</form>
	<!-- Atlantis JS -->
	<script src="../assets/js/atlantis.min.js"></script>
	<script >
		
	function editBlockById(id){
	    $("#formId").attr('action','${contextPath}/admin/block/edit/'+ id +'');
	    $("#formId").submit();
   }
	
	</script>
	
