	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 
	<style>
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

<link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css" />
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>


<c:if test="${Role eq 'ROLE_LA'}">
<div class="content">
		<div class="container-fluid"style="margin-top: -100px; position: relative; z-index:99">
		<%@ include file="/WEB-INF/views/message.jsp"%>	
		<%@ include file="/WEB-INF/views/ajaxLoader.jsp"%>	
			<div class="row inner-cardbox">
				<h6>Reserved Book List</h6><hr>
					<table id="example" class="table table-bordered export-table" >
								<thead>
									<tr>
										<th>Sl no.</th>
										<th>Book </th>
										<th>Member Name</th>
										<th>Member Code</th>
										<th>Mobile</th>
										<th>Status </th>
										<c:if test="${Role eq 'ROLE_LA'}"><th style="width: 165px;"><spring:message code="site.common.action"></spring:message></th> </c:if>													
									</tr>
								</thead>
								<tbody>
							 	   <c:forEach items="${bookList}" var="booklst" varStatus="count"> 
										<tr>
											<td>${count.count }</td>
											<td>${booklst.book.bookUkNo}</td>
											<td>${booklst.libraryCard.member.memberName}</td>
											<td>${booklst.libraryCard.member.memberCode}</td>
											<td>${booklst.libraryCard.member.phoneNo}</td>
											<td>${booklst.status}</td>
											<c:if test="${Role eq 'ROLE_LA'}">
											<td class="d-flex justify-content-evenly"> 
												<button type="button" class="btn-style" onclick="showModal('${booklst.bookIssuedId}');"  title="Approve" ><span class="badge bg-success ">Approve</span></button><br>
												<button type="button" class="btn-style" onclick="Reject('REJECTED','${booklst.bookIssuedId}')" title="Rejected"><span class="badge bg-danger ">Reject</span></button>
												<input type="hidden" name="bookIssue" value="${booklst}" />
			            					</td>
			            					</c:if>
										</tr>
							     </c:forEach> 
								</tbody>
							</table>
		</div>
	</div>
</div>
</c:if>

<c:if test="${Role eq 'ROLE_MEMBER'}">
<div class="content">
		<div class="container-fluid" style="margin-top: -100px; position: relative; z-index:99">	
		<%@ include file="/WEB-INF/views/message.jsp"%>	
		<%@ include file="/WEB-INF/views/ajaxLoader.jsp"%>
			<div class="row inner-cardbox">
				<h6>Member's  Book List</h6><hr>
					<table  class="table table-bordered" >
								<thead>
									<tr>
										<th>Sl no.</th>
										<th>Book </th>
										<th>Library Card Id</th>
										<th>Issue Date</th>
										<th>Return Date</th>
										<th>Status </th>
										<th style="width: 165px;"><spring:message code="site.common.action"></spring:message></th>  												
									</tr>
								</thead>
								<tbody>
							 	   <c:forEach items="${bookList}" var="booklst" varStatus="count"> 
										<tr>
											<td>${count.count }</td>
											<td>${booklst.book.bookUkNo}</td>
											<td>${booklst.libraryCard.lib_cardId}</td>
											<td>${booklst.issuedDate}</td>
											<td>${booklst.returnDate}</td>
											<td>${booklst.status}</td>
											<c:if test="${booklst.status eq 'RESERVED'}">
											<td><button type="button" class="btn-style" onclick="Reject('REJECTED','${booklst.bookIssuedId}');" title="Rejected"><span class="badge bg-danger ">Cancel</span></button>
											<input type="hidden" name="bookIssue" value="${booklst}" /></td>
											</c:if>
										</tr>
							     </c:forEach> 
								</tbody>
							</table>
		</div>
	</div>
</div>

</c:if>

 <!-- Modal -->
<div class="modal fade int-modal" id="approveModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <form class="modal-content" action="${contextPath}/mst/save-ReservedBookStatus" id="memberForm" method="POST">
		 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
       	 <input type="hidden" name="bookIssueId" id ="bookIssueId">
		 <input type="hidden" name="status" id ="status" value="APPROVED">
      <div class="modal-header">
      <div class="d-block">
       <h6 class="modal-title" id="exampleModalLabel">Return Date: </h6>
        
		<p style="display: block; color: red; font-size: small;">Reserved Date Should Be within ${maxReserveDay.valueEn} days</p>
      </div>
       
       
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="hidden" name="mamberCode" id="mamberCode">
			<div class="col-md-10 row">
			<div class="col-md-5">
				<div class="form-group">
					<label class="col-md-12 smallInput required" for=""> Issue Date:</label>
<!-- 					<div class="datepicker-box"> -->
						<div class="datepicker_con_nextdisable" style="postion:relative;" >
						<input type="text" class="form-control form-control-sm datepicker_con_nextdisable" id="issueDate" name="issueDate" readonly="readonly">
<!-- 						<i class='bx bx-calendar'></i> -->
					</div>
				</div>
			 </div>
			 
			<div class="col-md-5">
				<div class="form-group">
					<label class="col-md-12 smallInput required" for=""> Return Date:</label>
<!-- 					<div class="datepicker-box"> -->
                        <div class="datepicker_con_disabled" style="postion:relative;" >
						<input type="text" class="form-control form-control-sm datepicker_con_disabled" id="returnDate" name="returnDate" readonly="readonly">
<!-- 						<i class='bx bx-calendar'></i> -->
					</div>
				</div>
			</div>
			</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary footer-div" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary footer-div" onclick="sendapprove();">Submit</button>
      </div>
    </form>
  </div>
</div>

<form action="${contextPath}/mst/save-ReservedBookStatus" method="POST" id="approveOrReject" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
  <input type="hidden" name="bookIssueId" id="bookIssueIdReject">
  <input type="hidden" name="status" id="statusReject">
</form>
 
<script>

function Reject(status, bookId) {
	debugger;
	  $("#bookIssueIdReject").val(bookId);
	  $("#statusReject").val(status);
	  var confirmed = confirm("Are you sure you want to reject?");
	  if (confirmed) {
	        document.getElementById('approveOrReject').submit();
	    }
	}

</script>

<script>
  function showModal(bookIssuedId) {
    debugger;
    var approveModal = new bootstrap.Modal(document.getElementById('approveModal'));
    document.getElementById('bookIssueId').value = bookIssuedId;
    approveModal.show();
  }

 /*  function sendapprove() {
	    debugger;
	    showAjaxLoader();
	    var issueDate = $("#issueDate").val();
	    var returnDate = $("#returnDate").val();
	    var maxReserveDay = "${maxReserveDay.valueEn}";

	    var parsedReturnDate = new Date(returnDate);
	    var parsedIssueDate = new Date(issueDate);
	    var daysDifference = (parsedReturnDate - parsedIssueDate) / (24 * 60 * 60 * 1000);

	    if (daysDifference <= maxReserveDay) {
	        document.getElementById('memberForm').submit();
	        hideAjaxLoader();
	    } else {
	    	hideAjaxLoader();
	        alert("Reserved Date Should Be within 15 days");
	    }
	} */

  function sendapprove() {
    debugger;
    async function fetchData() {
        try {
            var isReservationExceeded  = await checkReservedBookByMemberId();
            if (isReservationExceeded ) {
                alert("Reserve Book Count is exceed");
            } else {
                var issueDate = $("#issueDate").val();
                var returnDate = $("#returnDate").val();
                
                if(issueDate != '' && returnDate != ''){
	                var maxReserveDay = ${maxReserveDay.valueEn};
	                var parsedReturnDate = new Date(returnDate);
	                var parsedIssueDate = new Date(issueDate);
	                var daysDifference = (parsedReturnDate - parsedIssueDate) / (24 * 60 * 60 * 1000);
	
	                if (!isNaN(daysDifference)){
	                if (daysDifference <= maxReserveDay) {
	                    document.getElementById('memberForm').submit();
	                } else {
	                    alert("Reserved Date Should Be within "+${maxReserveDay.valueEn}+" days");
	                }
                }else{
                    document.getElementById('memberForm').submit();	
                }
                }else{
                	alert("Fill the Dates");
                }
                
            }
        } catch (error) {
            console.error(error);
            alert("Error checking reserved book count");
        }
    }
    fetchData();
}

function checkReservedBookByMemberId() {
    return new Promise(function (resolve, reject) {
        debugger;
        $("#bookIssueId").val();
        $.get(contextPath + "/core/membersBookAllocationCount", { identity: 'MAX_ALLOCATION_LIMIT', bookIssuedId:$("#bookIssueId").val()})
            .done(function (data) {
                if (data != '' || data != null) {
                   var maxReserveDay = ${maxAllocationLimit.valueEn};
                   if(maxReserveDay > data){
                	   resolve(false);
                   }else{
                	   resolve(true);
                   }
                } 
            })
            .fail(function () {
                // hideAjaxLoader(); // Consider hiding loader here if needed
                alert("Error fetching checkMembersBookCount information");
                reject(new Error("Error fetching checkMembersBookCount information"));
            });
    });
}


</script>
<script>
// $(document).ready(function() {
// 	debugger
//     $("#issueDate").datepicker({
//         minDate: new Date(), 
//         dateFormat: "yy-mm-dd" 
         
//     });
// });

$("#issueDate").datepicker({ changeYear: true, dateFormat: 'dd/mm/yy', showOn: 'none', showButtonPanel: true,  minDate:'0d' }); 

// $(document).ready(function() {
//     $('#example').dataTable();
// } );

$(document).ready(function() {
    $('.export-table').DataTable( {
        dom: 'Bfrtip',
        buttons: [
            'copy', 'csv', 'excel', 'pdf', 'print'
        ]
    } );
} );

</script>
