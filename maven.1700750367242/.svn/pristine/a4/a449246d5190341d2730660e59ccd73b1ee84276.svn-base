	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 

<script src="${contextPath}/assets/appJs/framework/data-table.js" type="text/javascript"></script>

<div class="content">
		<%-- <div class="panel-header bg-primary-gradient">
			<div class="page-inner">
				<div class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
					<div>
						<h2 class="text-white fw-bold m-0">Mamber List</h2>
					</div>
					<div class="ml-md-auto">
						<a href="${contextPath}" class="btn btn-white btn-border btn-round mr-2"><i class="fa fa-home"></i></a>
						<a href="javascript:void(0)" class="btn btn-secondary btn-round">/ Mamber List </a>
					</div>
				</div>
			</div>
		</div> --%>
		<div class="container-fluid" style="margin-top: -100px; position: relative; z-index:99">		
			<div class="row inner-cardbox">
				<h6>Library List</h6><hr>
					<div class="table-responsive">
					<table id="example" class="table table-striped dataTable no-footer">
								<thead>
									<tr>
										<th>Sl no.</th>
										<th>Library Name</th>
										<th>Library Email</th>
										<th>Phone</th>
										<th style="width: 165px;"><spring:message code="site.common.action"></spring:message></th> 													
									</tr>
								</thead>
								<tbody>
							 	   <c:forEach items="${libList}" var="lib" varStatus="count"> 
										<tr>
											<td>${count.count }</td>
											<td>${lib.libName}</td>
											<td>${lib.email}</td>
											<td>${lib.mobile}</td>
											<td> 
												<a href="${contextPath}/mst/add-library?libCode=${lib.libCode}" type="button" class="btn btn-warning"  title="Edit"><i class='bx bx-pencil'></i></a>
			            					</td>
										</tr>
							     </c:forEach> 
								</tbody>
							</table>
						</div>
					
				
			
		</div>
	</div>
</div>

<script>
function editMamberForm(code){
	debugger;
	bootbox.confirm("Do you  want to edit Mamber?", function(result) {
		if (result) {
			$("#mamberCode").val(code);
			$("#mamberForm").submit();	
		}
	});
}




</script>

<script>
$(document).ready(function() {
    $('#example').dataTable();
} );
</script>	
