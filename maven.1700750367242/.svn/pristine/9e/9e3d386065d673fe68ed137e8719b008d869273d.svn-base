	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 
	
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css" />
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>

<div class="content">
		<div class="container-fluid" style="margin-top: -100px; position: relative; z-index:99">		
			<div class="row inner-cardbox">
				<h6>Book Catalog List</h6><hr>
					<table id="example"  class="table table-bordered" >
								<thead>
									<tr>
										<th>Sl no.</th>
										<th style="width:500px">Book Title</th>
										<th style="width:1000px">Book Subject</th>
										<th style="width:1000px">Sub-Category</th>
										<th style="width:1000px">No. of Pages</th>
										<th style="width:1000px">Author</th>
										<th style="width:1000px">Publisher</th>
										<th style="width:140px">Total Book</th>
										<th style="width:140px">Price Per Unit</th>
										<th style="width: 165px;"><spring:message code="site.common.action"></spring:message></th> 													
									</tr>
								</thead>
								<tbody>
							 	   <c:forEach items="${bookCatagoryList}" var="bookCatalog" varStatus="count"> 
										<tr>
											<td>${count.count}</td>
											<td>${bookCatalog.bookTitles}</td>
											<td>${bookCatalog.bookSubject}</td>
											<td>${bookCatalog.subCategory.subCategoryName}</td>
											<td>${bookCatalog.noOfPage}</td>
											<td>${bookCatalog.author.authorName}</td>
											<td>${bookCatalog.publisher.publisherName}</td>
											<td>${bookCatalog.totalBook}</td>
											<td>${bookCatalog.bookPrice}</td>
											<td> 
												<a href="${contextPath}/mst/add-book?catalogCode=${bookCatalog.bookCatalogCode}" type="button" class="btn btn-warning"  title="Edit"><i class='bx bx-pencil'></i></a>
												<a href="${contextPath}/mst/add-book?catalogCode=${bookCatalog.bookCatalogCode}&status=VIEW" type="button"  class="btn btn-primary" title="Edit"><i class='bx bx-show'></i> </a>

												<c:if test="${Role eq 'ROLE_MEMBER'}">
												<a href="${contextPath}/mst/reservedBook?catalogCode=${bookCatalog.bookCatalogCode}" type="button" class="badge bg-success" >Reserve</a>
												</c:if>
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