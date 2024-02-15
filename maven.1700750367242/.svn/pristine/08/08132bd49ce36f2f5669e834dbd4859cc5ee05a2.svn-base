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
									Grievence List  
								</h2>
							</div>
							<div class="ml-md-auto">
								<a href="${contextPath}/" class="btn btn-sm btn-border btn-blue btn-round mr-2"><i class="fa fa-home"></i></a>
								<a href="#" class="btn btn-sm btn-border btn-blue btn-round mr-2">/ Grievence List</a>
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
									<h4 class="card-title">Grievence List </h4>
								</div>
								<div class="card-body" style="">
									<div class="col-md-12" id="print_sec">
										<table class="table table-bordered DataTable mt-4 mb-4" id="dynamicTable">
												<thead>
												<tr>
													<th style="width: 65px;">Sl No.</th>
													<th>Name</th>
													<th>Phone No</th>
													<th>Shop Id</th>
													<th>Email Id</th>
													<th class="exporttable">Action</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${grievenceList}" var="mstGriev" varStatus="count">
												<tr>
													<td>${count.count}</td>
													<td>${mstGriev.grievenceName}</td>
													<td>${mstGriev.phoneNo}</td>
													<td>${mstGriev.shopId}</td>
													<td>${not empty mstGriev.emailId ? mstGriev.emailId : 'N/A'}</td>
													<td class="exporttable">
<%-- 														<button type="button" class="btn btn-xs btn-primary" onclick="editshopById(${mstShop.shopId})" >Edit</button> --%>
														<button  type="button" class="btn btn-xs btn-info" onclick="viewGrievFormById(${mstGriev.grievenceId})"  >View</button>
														<input  type="button" class="btn btn-xs btn-info ${mstGriev.status eq true?'Resolved':'New'}"  ${mstGriev.status eq true?'disabled':''}onclick="changeStatus(${mstGriev.grievenceId})" value="${mstGriev.status eq true?'Resolved':'New'}" >
<%-- 														<button  type="button" class="btn btn-xs btn-info" onclick="viewGrievFormById(${mstGriev.grievenceId})"  >InActive</button> --%>
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
	
	 $('#dynamicTable').DataTable( {
		        dom: 'Bfrtip',
// 		         buttons: [
// 		            {
// 		              extend: 'pdf',
// 		              exportOptions: {
// 			              columns: [0, 1, 2, 3, 4,5 ]
// 		              },
// 		              title: 'Grievence Report'
		            
// 		            },
// 		            {
// 			              extend: 'excel',
// 			              exportOptions: {
// 			                columns: [0, 1, 2, 3, 4,5 ]
// 			              },
// 			              title: 'Grievence Report'
// 			            },
// 			            {
// 				              extend: 'print',
// 				              exportOptions: {
// 				                columns: [0, 1, 2, 3, 4,5 ]
// 				              },
// 				              title: 'Grievence Report'
// 				            } 
// 		          ] 
		    } );
	});
</script>		