
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="userDetails" value="${serviceOutcome.data}" />
<script type="text/javascript"
	src="${contextPath}/assets/appJs/validation/common-utils.js"></script>
<style>
label.required::after {
    content: '* ';
    color: red;
}
</style>
<div class="content">
		<div class="container-fluid" style="margin-top: -100px; position: relative; z-index:99">		
			<div class="row inner-cardbox">
			<h6>Library Section</h6><hr>
	<%@ include file="/WEB-INF/views/message.jsp"%>
			
						<form action="${contextPath}/mst/manage-lib-section" id="submitForm" method="POST" class="row"  >
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						    
						    <div class="col-md-3">
							    <label class="required">Choose Library:</label> 
							    <select class="form-control form-control-sm selectpicker" multiple data-live-search="true" id="libId" name="libId" multiple="multiple">
							        <option value="0">--Select--</option>
							
							        <c:forEach items="${libList}" var="lib">
							            <c:set var="selected" value="false" />
							            <c:forEach items="${libSectionDtls.librarySectionVOList}" var="libraryId">
							                <c:if test="${libraryId.library.libId eq lib.libId}">
							                    <c:set var="selected" value="true" />
							                </c:if>
							            </c:forEach>
							            <option value="${lib.libId}" ${selected ? 'selected' : ''}>${lib.libName}</option>
							        </c:forEach> 
							    </select>
							</div>

						    
						<div class="col-md-12 table-responsive">
				            <table class="table table-striped table-bordered mt-3 " id="apndTable">
				              <thead>
				                <tr>
				                  <th>Sl No</th>
				                  <th>Section Name</th>
				                  <th>Section Description</th>
				                  <th style="width:150px">Capacity</th>
				                  <th>Status</th>
									<th>
									<button type="button" class="btn-primary addRow"title="Add">+</button>
									</th>
				               </tr>
				              </thead>
				              <tbody>
				
				                <c:if test="${empty libSectionDtls.librarySectionVOList}">
				                <tr class="gradeA" id="managementRow">
				                  <td>1</td>
				                  <td>
									  <input type="text" class="form-control form-control-sm AlphaNumericOnly"  oninput="this.value = this.value.toUpperCase();" id="secName1" name="librarySectionVOList[0].secName" maxlength="20">
								  </td>
								  <td>
								      <textarea class="form-control form-control-sm AlphaNumericOnly" id="secDesc1" name="librarySectionVOList[0].secDesc" maxlength="50"></textarea>
								  </td>
								  <td>
									<input type="tel" class="form-control form-control-sm NumbersOnly" id="capacity1" name="librarySectionVOList[0].capacity" >
								 </td>
				                  <td>
									  <select class="form-control form-control-sm" id="status1" name="librarySectionVOList[0].status" >
										<option value="0">--Select--</option>
										<option value="OPEN">Open</option>
										<option value="CLOSE">Close</option>
									  </select>
								  </td>
				                  <td>
				                    <button type="button" class="btn-danger deleteRow"
				                      title="Delete" style="padding: 0px 5px;"><i
				                        class="bx bx-minus"></i></button>
				                  </td>
				                </tr>
				                </c:if>
				                
				                <c:if test="${not empty libSectionDtls.librarySectionVOList}">
				                <c:forEach items="${libSectionDtls.librarySectionVOList}" var="sectionDtls" varStatus="vs">
				                
				                <tr class="gradeA" id="managementRow">
				                  <td>${vs.count}</td>
				                  <td>
				                      <input type="hidden" id="secId" name="librarySectionVOList[${vs.count-1}].secId" value="${sectionDtls.secId}" />
						   			  <input type="hidden" id="secCode" name="librarySectionVOList[${vs.count-1}].secCode" value="${sectionDtls.secCode}" />
									  <input type="text" class="form-control form-control-sm " id="secName1" name="librarySectionVOList[${vs.count-1}].secName" value="${sectionDtls.secName}"  >
								  </td>
								  <td>
								      <textarea class="form-control form-control-sm" id="secDesc1" name="librarySectionVOList[${vs.count-1}].secDesc"> ${sectionDtls.secDesc}</textarea>
								  </td>
								  <td>
									<input type="tel" class="form-control form-control-sm NumbersOnly1" id="capacity1" name="librarySectionVOList[${vs.count-1}].capacity" value="${sectionDtls.capacity}" >
								 </td>
				                  <td>
									  <select class="form-control form-control-sm" id="status1" name="librarySectionVOList[${vs.count-1}].status" >
										<option value="0">--Select--</option>
										<option value="OPEN"  ${sectionDtls.status eq 'OPEN' ? 'selected' : ''} >OPEN</option>
										<option value="CLOSE"  ${sectionDtls.status eq 'CLOSE' ? 'selected' : ''} >CLOSE</option>
									  </select>
								  </td>
				                  <td>
				                    <button type="button" class="btn-danger deleteRow"
				                      title="Delete" style="padding: 0px 5px;"><i
				                        class="bx bx-minus"></i></button>
				                  </td>
				                </tr>
				                </c:forEach>
				                </c:if>
				                
				              </tbody>
				            </table>
				          </div>

                          <div class="col-md-12 text-center mt-3">
							<input type="button" class="btn btn-success btn-sm" onclick="submitButton();" value="Submit" />
						  </div>

						</form>
					</div>
				
			</div>
	
</div>
<input type="hidden" id="rowLength1" value="1"/> 

<script>
function submitButton(){
	debugger
	if($("#libId").val() == 0){
		alert("Choose Library");
	}else{
		if($("#secName1").val() == ''){
			alert("Fill the SecName");
			return false;
		}else if($("#secDesc1").val() == ''){
			alert("Fill the Section Desc");
			return false;
		}else if($("#capacity1").val() == ''){
			alert("Fill the Capacity");
			return false;
		}else if($("#status1").val() == '0'){
			alert("Fill the Status");
			return false;
		}else{
		$("#submitForm").submit();
		}
	}
}


let count = 1;
//Try to get tbody first with jquery children. works faster!
var tbody = $('#apndTable').children('tbody');
//Then if no tbody just select your table 
var table = tbody.length ? tbody : $('#apndTable');


$('.addRow').click(function(){
    //Add row
    debugger;
                count++;
                var count11 = $("#rowLength1").val() ;
        		count11++;
        		var countArrLength = count11-1;
        		
        		$("#rowLength1").val(count11);
                var currRowCount = $("#rowLength1").val() - 1;
                
                
                
                var secName = $("#secName"+currRowCount).val();
        		var secDesc = $("#secDesc"+currRowCount).val();
        		var capacity = $("#capacity"+currRowCount).val();
        		var status = $("#status"+currRowCount).val();
        		
        		
        		if(secName !="" && secDesc !="" && capacity !="0" && status !="0" ){
        		
                table.append('<tr class="gradeA" id="deleteRowDetailsInfra'+count11+'"> '+
				                  '<td>' + count11 + '</td>'+
				                  '<td>'+
									 ' <input type="text" class="form-control form-control-sm" id="secName' + count11 + '" name="librarySectionVOList[' + countArrLength + '].secName" >'+
								 ' </td>'+
								 ' <td>'+
								  '    <textarea class="form-control form-control-sm" id="secDesc' + count11 + '" name="librarySectionVOList[' + countArrLength + '].secDesc"></textarea>'+
								 ' </td>'+
								'  <td>'+
								'	<input type="tel" class="form-control form-control-sm NumbersOnly" id="capacity' + count11 + '" name="librarySectionVOList[' + countArrLength + '].capacity" >'+
								' </td>'+
				                '  <td>'+
									  '<select class="form-control form-control-sm" id="status' + count11 + '" name="librarySectionVOList[' + countArrLength + '].status" >'+
										'<option value="0">--Select--</option>'+
										'<option value="OPEN">OPEN</option>'+
										'<option value="CLOSE">CLOSE</option>'+
									 ' </select>'+
								 ' </td>'+
				                 ' <td>'+
				                   ' <button type="button" class="btn-danger deleteRow" title="Delete" style="padding: 0px 5px;"><i class="bx bx-minus"></i></button>'+
				                 ' </td>'+
				                '</tr>');
    
//     $("#rowLength2").val(count11);
//		var currRowCount = $("#rowLength2").val()-1;

        }else{
        	alert("Can't add empty row ");
        	count11 = currRowCount;
        	$("#rowLength1").val(currRowCount);
       }

	 $( document ).ready(function() {
         $('.datepicker_con').datepick({ dateFormat: 'dd/mm/yyyy'});
       });
     $('.rangePicker').datepick({ 
      rangeSelect: true, showTrigger: '#calImg'});
});
 $("#apndTable").on('click', '.deleteRow', function() {
  $(this).closest("tr").remove();
  count--;          
});

</script>
