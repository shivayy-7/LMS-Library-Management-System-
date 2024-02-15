<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
.hidden {
    display: none;
}

/* date picker algn */
.datepick-popup {
    width: 250px !important;
}
.datepick {
    width: 250px !important;
}
.datepick-month-header {
    width: 250px !important;
}
.datepick-month table {
    width: 249px !important;
}
/* date picker algn */
</style>

<div class="container-fluid" style="margin-top: -100px; position: relative; z-index:99">
<%@ include file="/WEB-INF/views/message.jsp"%>
<%@ include file="/WEB-INF/views/ajaxLoader.jsp"%>
                <div class="row inner-cardbox">
                    <h6> Book Reissue</h6>
                    <hr>
                    
            <form action="${contextPath}/mst/manage-reIssueBook" method="post" id="manageReturnBookForm" >
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
            <input type="hidden" name="bookIssueVO.bookIssuedId" value="${bookissueDtls.bookIssueVO.bookIssuedId}" />
                <div class="row">
                 
                     <div class="col-md-4">
		                <div class="form-group">
		                  <label  for="publisherName">Book Name :</label>
		                 	 <input type="text" class="form-control form-control-sm NumbersOnly" id="isbnNo" value="${bookissueDtls.bookIssueVO.book.bookUkNo}" readonly="readonly">
		                </div>
		              </div>
                 
                      <!-- <div class="col-md-4">
		                <div class="form-group">
		                  <label class="required" for="publisherName">Library Card :</label>
		                 	 <input type="text" class="form-control form-control-sm AlphabetsOnly" id="bookTitle" oninput="this.value = this.value.toUpperCase()" >
		                </div>
		              </div> -->
		              
		               <div class="col-md-4">
		                <div class="form-group">
		                  <label  for="address">Issued Date :</label>
<%-- 							    <input type="text" class="form-control form-control-sm AlphabetsOnly" id="author"  value="${bookissueDtls.bookIssueVO.issuedDate}" readonly="readonly"> --%>
							    <c:set var="dateString" value="${bookissueDtls.bookIssueVO.issuedDate}" />
				    <fmt:parseDate var="issueDate" value="${dateString}" pattern="dd/MM/yyyy" /> 
				
				<input type="text" class="form-control form-control-sm" id="issuedDate" value='<fmt:formatDate value="${issueDate}" pattern="dd/MM/yyyy" />' readonly="readonly">
		                </div>
		              </div>
		              
		              <div class="col-md-4">
						 <div class="form-group">
						   <label  for="contact">Return date :</label>
						   <c:set var="dateStringReturn" value="${bookissueDtls.bookIssueVO.returnDate}" />
				<fmt:parseDate var="returnDate" value="${dateStringReturn}" pattern="dd/MM/yyyy" />
				
				<input type="text" class="form-control form-control-sm" id="returnDate" value='<fmt:formatDate value="${returnDate}" pattern="dd/MM/yyyy" />' readonly="readonly">
<%--                     <input type="text" class="form-control form-control-sm" id="returnDate" value="${bookissueDtls.bookIssueVO.returnDate}" readonly="readonly"> --%>
						 </div>
					 </div>
					 
					 <div class="col-md-3">
						 <div class="form-group">
						   <label  for="contact">Re-Issue date :</label>
						   <div class="datepicker-box">
								<input type="text" class="form-control form-control-sm datepicker_con" name="bookIssueVO.reIssuedate" id="reIssueDate" readonly="readonly">
						   <i class='bx bx-calendar'></i>
		                  	</div>
						 </div>
					 </div>
		              
		              <%-- <div class="col-md-4 " >
		                <div class="form-group">
		                  <label class="required" for="isreturn">Is Book return late :</label>
		                  	<select class="form-control form-control-sm " id="isreturn"  onchange="getHiddenFineAmount(this.value)" >
							        <option value="0">--Select--</option>
							        <option value="true">Yes</option>
							        <option value="false">No</option>
							</select>
		                </div>
		              </div>
		              
		              <div class="col-md-4 hidden" id="hiddenFineAmount">
						 <div class="form-group">
						   <label class="required" for="fineAmount">Fine Amount :</label>
						   <input type="text" class="form-control form-control-sm NumbersOnly" id="fineAmount" name="bookIssueVO.fineAmount" value="${bookissueDtls.fineAmount}">
						 </div>
					 </div>
					 
					 <div class="col-md-4 " >
						 <div class="form-group">
						   <label class="required" for="bookCondition">Book Condition :</label>
						   <select class="form-control form-control-sm " id="bookCondition" name="bookIssueVO.bookCondition"  >
							        <option value="0">--Select--</option>
							        <option value="GOOD">Good</option>
							        <option value="DAMAGED">Damaged</option>
							        <option value="LOST">Lost</option>
							</select>
						 </div>
					 </div> --%>
		              
		             <div class="col-md-12 text-center mt-2">
						<button type="button" class="btn btn-success btn-sm" onclick = "saveForm()">Submit</button>
						<a href="${contextPath}/mst/issued-bookList" type="button" class="btn btn-warning btn-sm">Back</a>
				  	</div>
           	</div>
           	
           	
            </form>
            </div>
        </div>	
        
<script>

function saveForm(){
	debugger;
	showAjaxLoader();
	var booleanVal = validateDates();
	
	if($("#reIssueDate").val() === ''){
		alert("Fill the Reissue Date");
		hideAjaxLoader();
        return false;
	}
	if(booleanVal){
		$("#manageReturnBookForm").submit();
	}
		hideAjaxLoader();
}

function getHiddenFineAmount(value) {
    var hiddenField = document.getElementById("hiddenFineAmount");

    if (value === "true") {
        hiddenField.classList.remove("hidden");
    } else {
        hiddenField.classList.add("hidden");
    }
}

</script>    

<script>
function validateDates() {
	debugger;
    var returnDate = parseDate($('#returnDate').val());
    var reIssueDate = new Date($('#reIssueDate').val());

    if (reIssueDate <= returnDate) {
        alert('Re-Issue Date must be after Return Date');
//         $('#reIssueDate').val('');
        return false;
    }else{
    	return true;
    }
}

function parseDate(dateString) {
	debugger;
    var parts = dateString.split('/');
    if (parts.length === 3) {
        return new Date(parts[2], parts[1] - 1, parts[0]);
    }
    return null;
}


$( document ).ready(function() {
               $('.datepicker_con').datepick();
               
               $('.datepicker_con').datepicker({
                   
                   dateFormat: 'dd/mm/yyyy' 
               });
             });
</script>

