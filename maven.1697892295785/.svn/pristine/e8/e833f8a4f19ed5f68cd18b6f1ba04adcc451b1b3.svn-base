	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 

<script src="${contextPath}/assets/appJs/framework/data-table.js" type="text/javascript"></script>
<div class="content">
		<div class="container-fluid" style="margin-top: -100px; position: relative; z-index:99">	
		<%@ include file="/WEB-INF/views/message.jsp"%>	
			<div class="row inner-cardbox">
				<h6>Book Issue List</h6><hr>
					<table  id="example" class="table table-bordered" >
								<thead>
									<tr>
										<th>Sl no.</th>
										<th>Book </th>
										<th>Member Id </th>
										<th>Issue Date </th>
										<th>Return Date </th>
										<th>Status </th>											
									</tr>
								</thead>
						<tbody>
							<c:forEach items="${bookIssueList}" var="issueList" varStatus="count"> 
									<tr>
									    <td>${count.count }</td>
										<td>${issueList.book.bookUkNo}</td>
										<td>${issueList.libraryCard.approveNo}</td>
										 <td><fmt:formatDate value="${issueList.issuedDate}" pattern="dd/MM/yyyy" /></td>
                                         <td><fmt:formatDate value="${issueList.returnDate}" pattern="dd/MM/yyyy" /> </td>
										<td>${issueList.status}</td>
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