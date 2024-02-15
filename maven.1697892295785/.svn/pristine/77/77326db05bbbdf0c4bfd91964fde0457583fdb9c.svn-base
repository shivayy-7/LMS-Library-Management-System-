	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 

<script src="${contextPath}/assets/appJs/framework/data-table.js" type="text/javascript"></script>

<div class="content">
		<div class="container-fluid" style="margin-top: -100px; position: relative; z-index:99">		
			<div class="row inner-cardbox">
				<h6>Issued Book List</h6><hr>
						<table class="table table-striped export-table">
								<thead>
									<tr>
										<th>Sl no.</th>
										<th>Book Name</th>
										<th>Member Name</th>
										<th>Phone No.</th>
										<th>Email</th>
										<th>Issued date</th>
										<th>Return date</th>
										<th style="width: 165px;"><spring:message code="site.common.action"></spring:message></th> 													
									</tr>
								</thead>
								<tbody>
							 	   <c:forEach items="${issuedBookList}" var="issueBook" varStatus="count"> 
										<tr>
											<td>${count.count }</td>
											<td>${issueBook.book.bookUkNo}</td>
											<td>${issueBook.libraryCard.member.memberName}</td>
											<td>${issueBook.libraryCard.member.phoneNo}</td>
											<td>${issueBook.libraryCard.member.emailId}</td>
											<td><fmt:formatDate value="${issueBook.issuedDate}" pattern="dd/MM/yyyy" /></td>
											<td><fmt:formatDate value="${issueBook.returnDate}" pattern="dd/MM/yyyy" /></td>
											<td class="tblebtns"> 
												<a href="${contextPath}/mst/return-book?bookIssuedId=${issueBook.bookIssuedId}&type=RETURNBOOK" type="button" class="returnbtn"> Return </a>
												<a href="${contextPath}/mst/return-book?bookIssuedId=${issueBook.bookIssuedId}&type=REISSUE" type="button" class="reissuebtn"> Re-Issue </a>
			            					</td>
										</tr>
							     </c:forEach> 
								</tbody>
							</table>
		</div>
	</div>
</div>

