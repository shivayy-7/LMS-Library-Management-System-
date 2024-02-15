<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script type="text/javascript" src="${contextPath}/assets/appJs/validation/common-utils.js"></script>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<body>

<div class="content">
	<div class="container-fluid" style="margin-top: -100px; position: relative; z-index:99;">
		<div class="inner-cardbox">
			<h6 class="d-flex justify-content-between align-items-center">Trending Books 
				<ul class="slick-arrow list-unstyled mx-0 my-0 py-0 px-0">
					<li class="book-f-prev me-1"><i class='bx bx-left-arrow-alt' ></i></li>
					<li class="book-f-next"><i class='bx bx-right-arrow-alt' ></i></li>
				</ul>
			</h6>
			
			<div class="row">
				<div class="col-lg-12">
				 <c:if test="${not empty subcatogoriesList}">
					<div class="book-f-slider">
					<c:forEach items="${subcatogoriesList}" var="subcatagoriesList">
						<div class="book-box">
							<div class="book-cell">
						    <div class="book-img">
						     	<img src="${contextPath}/image/catagories?filePath=${subcatagoriesList.subCategoryImagePath}" alt="" class="img-fluid book-photo">
						    </div>
						    <div class="book-content">
							     <div class="book-title">${subcatagoriesList.subCategoryName}</div>
							     <div class="book-author">By ${subcatagoriesList.subCategoryName}</div>
							     <div class="rate">
							      <ul class="rating yellow">
							       <li><i class="bx bxs-star"></i></li>
							       <li><i class="bx bxs-star"></i></li>
							       <li><i class="bx bxs-star"></i></li>
							       <li><i class="bx bxs-star-half"></i></li>
							       <li><i class="bx bx-star"></i></li>
							      </ul>
							      <span class="book-voters">1.987k Readers</span>
							     </div>
							     <div class="book-sum">${subcatagoriesList.subCategoryName}</div>
							     <div class="book-see book-yellow"><a href="#" onclick="viewimageProducttotal(${subcatagoriesList.subCategoryId})" type="button" class="badge bg-success">View All</a></div>
						    </div>
						  </div>
						</div>
						  </c:forEach>
					</div>
					</c:if>
				</div>
			</div>
			
			<hr/>
			<c:if test="${not empty bookcatalogList}">  
		    <h6>Book List</h6>
			<div class="row">
				<c:forEach items="${bookcatalogList}" var="bookcatalogList">
				<div class="col-lg-2">
					<div class="book">
					  <div class="book-cover cover1">
					    <img src="${contextPath}/image/bookcat?bookfilePath=${bookcatalogList.imgPath}">
					    <div class="effect"></div>
					    <div class="light"></div>
					  </div>
					  <div class="book-inside"></div>
					</div>
					<ul class="book-dec">
						<li class="bookName">${bookcatalogList.bookTitles}</li>
						<li><a href="${contextPath}/mst/reservedBook?catalogCode=${bookcatalogList.bookCatalogCode}" type="button" class="badge bg-success">Reserve</a></li>
									 <a	href="${contextPath}/home" class="btn btn-warning btn-sm backbtn" type="button" >Back</a>
					</ul>
				</div>
				 </c:forEach>
			</div>
			</c:if>
		</div>
	</div>
	</div>	
	
	
<form action="${contextPath}/mst/fillterbook" class="row" id="fromsubmit" method="POST">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<input type="hidden" name="categoryId" id="hdnsubcatId" />
<input type="hidden" name="subcategoryId" id="hdncatId" />
<input type="hidden" name="type" id="hdTypeId" />
</form>		
	
<script>

function viewimageProducttotal(subcatagoriesId){
	console.log(subcatagoriesId);
	var catlogId = $("#categoryName").val();
	$("#hdncatId").val(subcatagoriesId);
	$("#hdTypeId").val("book");
 $("#fromsubmit").submit(); 
	
}
</script>