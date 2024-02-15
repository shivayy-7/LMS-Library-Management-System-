<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript"
	src="${contextPath}/assets/appJs/validation/common-utils.js"></script>
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
                <div class="row inner-cardbox">
                    <h6>${empty shelfData.shelfId ? 'Add ' : 'Update '} Shelf</h6>
                    <hr>
                     <div class="col-md-12">
            <form class="row" action="${contextPath}/mst/save-Shelf" method="post" id="shelfForm" >
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
            <input type="hidden" name="shelfId" value="${shelfData.shelfId}" />
                <div class="row">
                 <div class="col-md-3">
		                <div class="form-group">
		                  <label class="smallInput required" for=""> Rack Name :</label>
							<select class="form-control form-control-sm" id="rackName" name="rackId"  onchange="getShelf(this.value)" >
							<option value="0">Select</option>
								<c:forEach items="${rackList}" var="racklist" >
									<option value="${racklist.rackId}" ${racklist.rackId eq rackId ? 'selected' : '' }>${racklist.rackName}</option>
								</c:forEach>
							</select>
		                </div>
		              </div>
		              <div class="col-lg-9">
                     <div class="table-responsive">
				            <table class=" table table-striped table-bordered mt-3 " id="apndTable">
				              <thead style="height:30px">
				                <tr>
				                    <th style="width:95%">Shelf Name</th>
									<th style="width:5%">
										<button type="button" class="btn-primary addRow"title="Add"><i class='bx bx-plus'></i></button>
									</th>
				               </tr>
				              </thead>
				              <tbody id="">
				              <c:if test="${not empty shelfDtls.shelfVOList}">
				                <c:forEach items="${shelfDtls.shelfVOList}" var="shelf" varStatus="vs">
				                <tr class="gradeA" id="managementRow">
				                  <td>
				                      <input type="hidden" id="shelfId${vs.index}" name="shelfVOList[${vs.index}].shelfId" value="${shelf.shelfId}" />
						   			  <input type="hidden" id="shelfCode${vs.index}" name="shelfVOList[${vs.index}].shelfCode" value="${shelf.shelfCode}" />
									  <input type="text" class="form-control form-control-sm AlphaNumericOnly rowCount" id="shelfName${vs.index}" name="shelfVOList[${vs.index}].shelfName" oninput="this.value = this.value.toUpperCase();" value="${shelf.shelfName}" maxlength="35">
								  </td>
								  <td>&nbsp;</td>
							<%-- 	<td> 
			            			<button type="button" class="btn ${shelfDtls.isActive eq false ? 'btn-danger' : 'btn-success'} btn-xs" data-toggle="tooltip" onclick = "actShelfForm(${shelfDtls.shelfId},${shelfDtls.isActive})" title="${shelfDtls.isActive eq true ? 'Click To Deactivate' : 'Click To Activate'}">
			            			<i class="${shelfDtls.isActive eq false ? 'bx bx-lock' : 'bx bx-lock-open'}"></i></button>			            
			            		</td> --%>
				                </tr>
				                </c:forEach>
				                </c:if>
				              </tbody>
				            </table>
				          </div>
				           <div>
						<button type="button" class="btn btn-success btn-sm" onclick = "saveForm('${empty shelfData ? 'SAVE' : 'UPDATE'}')">${empty shelfData ? 'Save' : 'Update'}</button>
				  	</div>
				          </div>
		              
		            
                </div>
            </form>
            </div>
        </div>	
    </div>
   <input type="hidden" id="rowLength1" value="1"/> 
    
    
<form action="${contextPath}/mst/getShelfByRackId" method="GET" id="aginstRack" >
            <input type="hidden" name="rackId" id="rkId" value="${shelfData.shelfId}" />
</form>

<form action = "${contextPath}/mst/activeInactiveShelf" method="post" id="shelfFormAct">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<input type="hidden" name="shelfDtls.shelfId" id="shelfIdAct"/>
	<input type="hidden" name="status" id="status"/>
    </form> 

    
<script>
function getShelf(rackId){
	debugger;
	$("#rkId").val(rackId);
	$("#aginstRack").submit();
}

// $('#rackName').on('change', function() {
//     var selectedValue = $(this).val();
//     $("#rkId").val(rackId);
// 	$("#aginstRack").submit();
// });

// function saveForm(status){
// 	 if($("#rackName").val() == 0){
// 		  alert("Please Select Rack Name.");
// 		  return false;
// 	  }
// 	    var validCnt = document.getElementsByClassName("rowCount").length-1;
// 	    if($("#shelfName"+validCnt).val()==""){
// 	    	bootbox.alert("Please Enter Shelf Name");
// 	    }else{
// 		  $("#shelfForm").submit(); 
// 	    }
// }

function saveForm(status){
    if ($("#rackName").val() == 0) {
        alert("Please Select Rack Name.");
        return false;
    }

    var validCnt = document.getElementsByClassName("rowCount").length - 1;

    if (validCnt < 0 || $("#shelfName" + validCnt).val() == "") {
        bootbox.alert("Please Enter Shelf Name");
    } else {
        $("#shelfForm").submit();
    }
}



function actShelfForm(id,status){
	debugger;
	$("#shelfIdAct").val(id);
	if(status == true){
		$("#status").val(false);
	}else{
		$("#status").val(true);
	}
	
	$("#shelfFormAct").submit();
    }


let count = 1;
//Try to get tbody first with jquery children. works faster!
var tbody = $('#apndTable').children('tbody');
//Then if no tbody just select your table 
var table = tbody.length ? tbody : $('#apndTable');


$('.addRow').click(function(){
	var validCnt = document.getElementsByClassName("rowCount").length-1;
    if($("#shelfName"+validCnt).val()==""){
    	bootbox.alert("Please Enter Shelf Name");
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
									 ' <input type="text" class="form-control form-control-sm rowCount" oninput="this.value = this.value.toUpperCase();" id="shelfName' + countRow + '" name="shelfVOList[' + countRow + '].shelfName" maxlength="35">'+
								 ' </td>'+
				                 ' <td>'+
				                   ' <button type="button" class="btn-danger deleteRow" title="Delete" style="padding: 0px 5px;"><i class="bx bx-minus"></i></button>'+
				                 ' </td>'+
				                '</tr>');

    }	 
$("#apndTable").on('click', '.deleteRow', function() {
$(this).closest("tr").remove();
count--;          
});
});


</script>    