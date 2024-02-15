<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript" src="${contextPath}/assets/appJs/validation/common-utils.js"></script>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<style>
.but-al{
margin-top:27px !important;
}

label.required::after {
    content: '* ';
    color: red;
}
</style>

<div class="container-fluid" style="margin-top: -100px; position: relative; z-index:99">
<%@ include file="/WEB-INF/views/message.jsp"%>
<%@ include file="/WEB-INF/views/ajaxLoader.jsp"%>
                <div class="row inner-cardbox">
                    <h6> Reserved Book</h6><hr>
								<form class="form-horizontal" action="${contextPath}/mst/save-reservedBook" method="POST" id="reservedSubmitPage">																		
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									<input type="hidden" name="bookCatalogVO.bookCatalogId" value="${bookDtls.bookCatalogVO.bookCatalogId}" />
                                    <input type="hidden" name="bookCatalogVO.bookCatalogCode" value="${bookDtls.bookCatalogVO.bookCatalogCode}" />										
										
									
									<div class="row">
										
										<div class="col-md-4">
							                <div class="form-group">
							                  <label class="required" for="publisherName">Book Title :</label>
							                 	 <input type="text" class="form-control form-control-sm AlphabetsOnly" id="bookTitle" value="${bookDtls.bookCatalogVO.bookTitles}" name="bookCatalogVO.bookTitles" oninput="this.value = this.value.toUpperCase()" readonly="readonly">
							                </div>
							              </div>
										
										 <div class="col-md-4">
							                <div class="form-group">
							                  <label class="required" for="website">Book Subject :</label>
							                  	<input type="text" class="form-control form-control-sm AlphabetsOnly" id="website" value="${bookDtls.bookCatalogVO.bookSubject}" name="bookCatalogVO.bookSubject" readonly="readonly">
							                </div>
							              </div>
							              
										<div class="col-md-4">
										  <div class="form-group">
											  <label class="required" for="contact">Total Book :</label>
											  <input type="text" class="form-control form-control-sm NumbersOnly" id="contact" value="${bookDtls.bookCatalogVO.totalBook}" name="bookCatalogVO.totalBook" readonly="readonly">
										  </div>
										</div>
										
										<div class="col-md-4">
										  <div class="form-group">
											  <label class="required" for="contact">Available Book :</label>
											  <input type="text" class="form-control form-control-sm NumbersOnly" id="contact" value="${bookDtls.bookCatalogVO.availableBook}" name="bookCatalogVO.totalBook" readonly="readonly">
										  </div>
										</div>
										
										<div class="col-md-4">
							                <div class="form-group">
							                  <label class="required" for="desc">Book price :</label>
							                  	<input type="text" class="form-control form-control-sm NumbersOnly" id="desc" value="${bookDtls.bookCatalogVO.bookPrice}" name="bookCatalogVO.bookPrice" readonly="readonly">
							                </div>
						                </div>
						                
						              <%--  <div class="row inner-cardbox">
    <h6>Book Catalog List</h6>
    <hr>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Sl no.-></th>
                <th>Book Title</th>
                <th style="width: 165px;"><spring:message code="site.common.action"></spring:message></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${bookList.bookVOList}" var="list" varStatus="count">
                <tr>
                    <td>${count.count}</td>
                    <td>${list.bookUkNo}</td>
                    <td>
                        <c:choose>
                            <c:when test="${compareTwoList.contains(list.bookId)}">
                            
                                <button type="button" class="badge bg-secondary" disabled>Reserved</button>
                            </c:when>
                            <c:otherwise>
                                <a href="${contextPath}/mst/save-reservedBook?bookId=${list.bookId}" type="button" class="badge bg-success">Reserve</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div> --%>
				</div>
							<div class="col-md-12 mt-3">		
									<c:choose>
                            <c:when test="${allBooksAvailable}">
                            
                                 
                                <button type="button" class="btn btn-danger btn-sm" disabled>Not Available</button>
									
                            </c:when>
                            <c:otherwise>
<%--                                 <a href="${contextPath}/mst/save-reservedBook?bookId=${list.bookId}" type="button" class="badge bg-success">Reserve</a> --%>
 								
									<c:if test="${Role eq 'ROLE_MEMBER'}">
										<input type="button" class="btn btn-success btn-sm"
											value="Reserve" onclick="saveCategoryForm()"> 
										</c:if>	
									
                            </c:otherwise>
                        </c:choose>
                                       
								<a href="${contextPath}/" type="button" class="btn btn-warning btn-sm">Back</a>
                        
									
									
								</form>
							</div>
					</div>
					</div>

<script>

function saveCategoryForm(status){
	debugger;
	showAjaxLoader();
	async function fetchData() {
		var value = await checkReservedBookByMemberId();
		if(value){
			hideAjaxLoader();
			alert("Reserve Book Count is exceed");
		}else{
			 $("#reservedSubmitPage").submit(); 
			 hideAjaxLoader();
		}
	}
		  fetchData();
}

function checkReservedBookByMemberId(){
	return new Promise(function (resolve, reject) {
		debugger;
		$.get(contextPath + "/core/checkMembersBookCount", {identity:'MAX_RESERVE_LIMIT'})
	    .done(function (data) {
	        if(data){
	        	resolve(true);
	        }else{
	        	resolve(false);
	        }
	    })
	    .fail(function () {
	        alert("Error fetching checkMembersBookCount information");
	        reject();
	    });
	});
}


</script>
				
				