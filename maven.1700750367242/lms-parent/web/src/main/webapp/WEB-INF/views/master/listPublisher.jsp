	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 

<script src="${contextPath}/assets/appJs/framework/data-table.js" type="text/javascript"></script>

<div class="container-fluid" style="margin-top: -100px; position: relative; z-index:99">	
		  <div class="row inner-cardbox">			
				<h6>Publisher List</h6><hr>
				<div class="table-responsive">
						<table id="example" class="table table-striped dataTable no-footer">
								<thead>
									<tr>
										<th>Sl no.</th>
										<th>Publisher Name</th>
										<th>Contact</th>
										<th>Website</th>
										<th style="width: 165px;"><spring:message code="site.common.action"></spring:message></th> 													
									</tr>
								</thead>
								<tbody>
							 	   <c:forEach items="${publisherList}" var="publisher" varStatus="count"> 
										<tr>
											<td>${count.count }</td>
											<td>${publisher.publisherName}</td>
											<td>${publisher.contact}</td>
											<td>${publisher.website}</td>
											<td> 
												<a href="${contextPath}/mst/add-publisher?publisherCode=${publisher.publisherCode}" type="button" class="btn btn-warning"  title="Edit"><i class='bx bxs-pencil'></i></a>
			            					</td>
										</tr>
							     </c:forEach> 
								</tbody>
							</table>
						</div>

	</div>
</div>

	
<script>
$(document).ready(function() {
    $('#example').dataTable();
} );
</script>