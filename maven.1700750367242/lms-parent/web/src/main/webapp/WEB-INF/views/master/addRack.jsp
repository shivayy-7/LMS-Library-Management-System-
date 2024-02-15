
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="userDetails" value="${serviceOutcome.data}" />
<script type="text/javascript"
	src="${contextPath}/assets/appJs/validation/common-utils.js"></script>
<div class="content">
		<div class="container-fluid"style="margin-top: -100px; position: relative; z-index:99">
	
	<div class="page-inner mt--5 pb-0">
		<div class="row mt-3">
			<div class="col-md-12">
				<div class="card full-height">
					<div class="card-header">
						
						<h6 >Add Rack</h6>
					<%@ include file="/WEB-INF/views/message.jsp"%>
					</div>
					<div class="card-body" style="">
						<form action="${contextPath}/mst/manage-rack" id="submitForm" method="POST" >
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						    
						<div class="table-responsive">
				            <table class="table table-striped table-bordered mt-3 " id="apndTable">
				              <thead>
				                <tr>
				                  <th>Rack Name</th>
									<th>
									<button type="button" class="btn-primary addRow"title="Add"><i class='bx bx-plus'></i></button>
									</th>
				               </tr>
				              </thead>
				              <tbody>
				
				                <%-- <c:if test="${empty rackList}">
				                <tr class="gradeA" id="managementRow">
				                  <td>
									  <input type="text" class="form-control form-control-sm" id="rackName1" name="rackVOList[0].rackName" >
								  </td>
				                  
				                </tr>
				                </c:if> --%>
				                
				                <c:if test="${not empty rackList}">
				                <c:forEach items="${rackList}" var="rack" varStatus="vs">
				                <tr class="gradeA" id="managementRow">
				                  <td>
				                      <input type="hidden" id="secId${vs.index}" name="rackVOList[${vs.index}].rackId" value="${rack.rackId}" />
						   			 <input type="hidden" id="secCode${vs.index}" name="rackVOList[${vs.index}].rackCode" value="${rack.rackCode}" />
									  <input type="text" class="form-control form-control-sm rowCount" id="secName${vs.index}" name="rackVOList[${vs.index}].rackName" value="${rack.rackName}" maxlength="20">
									  
								  </td>
								  
				                  <td class="btnholder">
									    <button type="button" class="btn-danger" style="padding: 0px 5px;" onclick="rackActiveInactivemanager('${rack.rackCode}', ${rack.isActive})">
									        <i class="fa ${rack.isActive ? 'fa-lock' : 'fa-lock-open'}"></i>
									    </button>
								  </td>

				                </tr>
				                </c:forEach>
				                </c:if>
				                
				              </tbody>
				            </table>
				          </div>

                          <div class="col-md-12 text-center mt-3">
							<input type="button" class="btn btn-success btn-sm" onclick="submitButton();" value="Submit" />
							<a href="${contextPath}/mst/add-rack" class="btn btn-danger btn-sm">Cancel</a>
						  </div>

						</form>
					</div>
				</div>
				</div>
			</div>
		</div>
	</div>
</div>
<input type="hidden" id="rowLength1" value="1"/> 

<form action="${contextPath}/mst/rackManager" id="rackmanager" method="GET" >
   <input type="hidden" id="rkCode" name="rackVO.rackCode" />
   <input type="hidden" id="rkStatus" name="rackVO.isActive" />
</form>

<script>

function rackActiveInactivemanager(rackCode, status){
	debugger;
	$("#rkCode").val(rackCode);
	$("#rkStatus").val(status);
	$("#rackmanager").submit();
}

function submitButton(){
	 var validCnt = document.getElementsByClassName("rowCount").length-1;
	    if($("#rackName"+validCnt).val()==""){
	    	bootbox.alert("Please Enter Rack Name");
	    }else{
		$("#submitForm").submit();
	    }
}


let count = 1;
//Try to get tbody first with jquery children. works faster!
var tbody = $('#apndTable').children('tbody');
//Then if no tbody just select your table 
var table = tbody.length ? tbody : $('#apndTable');


$('.addRow').click(function(){
    var validCnt = document.getElementsByClassName("rowCount").length-1;
    if($("#rackName"+validCnt).val()==""){
    	bootbox.alert("Please Enter Rack Name");
    }else{
    debugger;
                count++;
                var count11 = $("#rowLength1").val() ;
        		count11++;
        		var countArrLength = count11-1;
        		var countRow = document.getElementsByClassName("rowCount").length;

        		$("#rowLength1").val(count11);
                var currRowCount = $("#rowLength1").val() - 1;
        		
                table.append('<tr class="gradeA" id="deleteRowDetailsInfra'+count11+'"> '+
				                  '<td>'+
									 ' <input type="text" class="form-control form-control-sm rowCount" id="rackName' + countRow + '" name="rackVOList[' + countRow + '].rackName" >'+
								 ' </td>'+
				                 ' <td>'+
				                   ' <button type="button" class="btn-danger deleteRow" title="Delete" style="padding: 0px 5px;"><i class="bx bx-minus"></i></button>'+
				                 ' </td>'+
				                '</tr>');

	 $( document ).ready(function() {
         $('.datepicker_con').datepick({ dateFormat: 'dd/mm/yyyy'});
       });
     $('.rangePicker').datepick({ 
      rangeSelect: true, showTrigger: '#calImg'});
    }
});
 $("#apndTable").on('click', '.deleteRow', function() {
  $(this).closest("tr").remove();
  count--; 
});

</script>
