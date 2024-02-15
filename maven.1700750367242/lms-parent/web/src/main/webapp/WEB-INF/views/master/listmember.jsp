	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
 
    
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 
	   <script src="${contextPath}/assets/appJs/framework/data-table.js" type="text/javascript"></script>
 
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

<div class="content">
	<div class="container-fluid" style="margin-top: -100px; position: relative; z-index:99">
	<%@ include file="/WEB-INF/views/message.jsp"%>
                <div class="row inner-cardbox">
                    <h6>Member List</h6>
                    <hr>
						<div class="col-md-12">
						   <div class="table-responsive">
						<table class="table table-striped export-table"> 
								<thead>
									<tr>
										<th>Sl no.</th>
										<th>Mamber Name</th>
										<th>Contact Number</th>
										<th>Email ID </th>
<!-- 										<th>Address</th> -->
										<th>Status</th>
										<th><spring:message code="site.common.action"></spring:message></th> 													
									</tr>
								</thead>
								<tbody>
							 	   <c:forEach items="${memberList}" var="memberlist" varStatus="count"> 
										<tr>
											<td>${count.count }</td>
											<td>${memberlist.memberName}</td>
											<td>${memberlist.phoneNo}</td>
											<td>${memberlist.emailId}</td>
<%-- 											<td>${memberlist.address}</td> --%>
											<td>${memberlist.status}</td>
											<td> 
												<button type="button" class="btn btn-warning" onclick = "editMamberForm('${memberlist.memberCode}');" title="Member View"><i class='bx bx-show'></i></button>
												<c:if test="${memberlist.status eq 'APPROVED'}">
												<button type="button" class="btn btn-success btncolor1" onclick = "viewmember('${memberlist.memberCode}');" title="Card View"><i class='bx bx-credit-card-front'></i></button>
												</c:if>
												<c:if test="${memberlist.isValid eq true}">
									 <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#Re-approveModal" onclick="updateReapproval('${memberlist.memberCode}');" title="Re-issue" > R </button>
									</c:if>
			            			</td>
									</tr>
							     </c:forEach> 
								</tbody>
							</table>
							</div>
						</div>
						</div>
						</div>
						
		
		
		<div class="modal fade int-modal" id="Re-approveModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <form class="modal-content" action="${contextPath}/mst/send-reapprove-member" id="rememberForm" method="POST">
      <div class="modal-header">
        <h6 class="modal-title" id="exampleModalLabel">User Re-Valid</h6>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<input type="hidden" name="mamberCode" id="hdnmamberCode">
			
			<div class="col-md-3">
				<div class="form-group">
					<label class="col-md-12 smallInput required" for="">
						Re-Valid Date:</label>
					<div class="col-md-12 datepicker_con_disabled">
						<input type="text" class="form-control form-control-sm " id="revaliDate" name="revaliDate" style="pointer-events: none;" readonly="readonly">
					</div>
				</div>
			</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="sendreapprove();">Submit</button>
      </div>
    </form>
  </div>
</div>			

<form action = "${contextPath}/mst/edit-member" method="post" id="mamberForm">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<input type="hidden" name="mamberCode" id="mamberCode">
</form> 

<form action = "${contextPath}/mst/view-member" method="post" id="mamberviewForm">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<input type="hidden" name="mamberCode" id="mamberviewCode">
</form> 
<script>
// $(document).ready(function() {
//     $('#example').dataTable();
// } );
</script>

<script>
function editMamberForm(code){
	debugger;
			$("#mamberCode").val(code);
			$("#mamberForm").submit();	
}


function viewmember(code){
	debugger;
			$("#mamberviewCode").val(code);
			$("#mamberviewForm").submit();	
}

</script>
<script>
    $(document).ready(function() {
        $('#myTable').DataTable();
    });
    
    function updateReapproval(mebercode){
    	$("#hdnmamberCode").val(mebercode);
    }
    
    function sendreapprove(){
    	
    	if($("#revaliDate").val() == ""){
    		  bootbox.alert("Please Select  Valid Date Of Member");
    		  return false;
    	  }
    	$("#rememberForm").submit();	
    }
    
    $(function() {
        $('.datepicker_con1 > input').datepick({
            onShow: $.datepick.monthOnly,
            dateFormat: 'dd/mm/yyyy',
            showOnFocus: true,
            yearRange: (new Date().getFullYear() - 23) + ':' + new Date().getFullYear(),
            showTrigger: '<button type="button" class="trigger">' +
                '<i class="fa fa-calendar"></i></button>',
             minDate: 0,
            onSelect: function(d) {
            }
        });
    });
</script>
	<script>

// document.onkeydown = function(e) {
// 		/* Block F12 */
// 		if(event.keyCode == 123) {
// 			return false;
// 		}
// 		/* CTRL + SHIFT + I */
// 		if(e.ctrlKey && e.shiftKey && e.keyCode == 'I'.charCodeAt(0)){
// 			return false;
// 		}
// 		/* CTRL + SHIFT + J */
// 		if(e.ctrlKey && e.shiftKey && e.keyCode == 'J'.charCodeAt(0)){
// 			return false;
// 		}
		/* CTRL + SHIFT + C 
		if(e.ctrlKey && e.shiftKey && e.keyCode == 'C'.charCodeAt(0)){
			return false;
		}*/
		/* CTRL + U */
// 		if(e.ctrlKey && e.keyCode == 'U'.charCodeAt(0)){
// 			return false;
// 		}
// 	}
	


</script>
