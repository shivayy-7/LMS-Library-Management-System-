
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
<div class="container-fluied" style="margin-top: -100px; position: relative; z-index:99">

	<div class="page-inner mt-5 pb-0">
		<div class="row mt-2">
			<%@ include file="/WEB-INF/views/message.jsp"%>
			<div class="col-md-12">
				<div class="card full-height">
					<div class="card-header">
						<!--<div class="panel-actions">
							<a href="#" class="fa fa-caret-down"></a>
						</div>-->
						<h6>User Details</h6>
					</div>
					<div class="card-body">
						<div class="col-md-12">
								<form class="form-horizontal"
									action="${contextPath}/umt/user/add" method="POST"
									id="userForm">
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" /> <input type="hidden" name="userid"
										value="${userDetails.userId}" />
									<div class="row">
										
											<div class="col-md-3">
												<div class="form-group">
													<label class="col-md-12 required" for="complaintName">User
														Id</label>
													<div id="hideUserId">
														<div class="col-md-12">
															<input type="text" id="username" name="username"
																class="form-control form-control-sm" required
																onchange="userNameValidation();" maxlength="50" placeholder="User Id">
														</div>
													</div>
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label class="col-md-12 required" for="complaintName">First
														Name</label>
													<div class="col-md-12">
														<input type="text" id="firstname" name="firstname"
															class="form-control form-control-sm" required
															maxlength="100" placeholder="Enter First Name">
													</div>
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label class="col-md-12 required" for="complaintAge">Last
														Name</label>
													<div class="col-md-12">
														<input type="text" id="lastname" name="lastname"
															class="form-control form-control-sm" required
															maxlength="100" placeholder="Enter Last Name">
													</div>
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label class="col-md-12 required" for="mobile">Mobile</label>
													<div class="col-md-12">
														<input type="text" name="userMobile" id="userMobile"
															class="form-control form-control-sm NumbersOnly"
															maxlength="10" onchange="validateMobileNo(this);"
															required placeholder="Enter Phone Number">
													</div>
												</div>
											</div>
								
									
											<%-- <div class="col-md-3">
									<div class="form-group">
										<label class="col-md-12 required1" for="mobile">Date Of Birth</label>
										<div class="col-md-12">
											<input type="text" name="dateOfbirth" id="dateOfbirth" class="form-control form-control-sm" maxlength="10" value="<fmt:formatDate value="${userDetails.dateOfBirth}" pattern="dd-MM-yyyy" />" >
										</div>
									</div>
								</div> --%>
											<div class="col-md-3">
												<div class="form-group">
													<label class="col-md-12 required" for="Email">Email
														Id</label>
													<div class="col-md-12">
														<input type="email" name="userEmail" id="userEmail"
															class="form-control form-control-sm emailsOnly"
															onchange="validateEmail(this);" required maxlength="150" placeholder="Enter Email Id">
													</div>
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label class="col-md-12 required">Designation</label>
													<div class="col-md-12">
														<input type="text" name="designation" id="designation"
															class="form-control form-control-sm" required
															maxlength="150"  placeholder="Enter Designation">
													</div>
												</div>
											</div>
						
						
									<div class="" style="padding-top: 15px; margin-bottom: 15px;">
										<div class="no-more-tables">
											<table class="table table-bordered table-striped mb-none table-condensed cf" id="tableId1">
												<thead class="cf">
													<tr>
														<th><label><b>Sl No.</b></label></th>
														<th><label><b>Primary Role</b></label></th>
														<th><label><b>Role</b></label></th>
														<th><label><b>Status</b></label></th>
														<th width="5%" style="text-align: center;">
															<button type="button" onclick="createRow();"
																class="btn btn-primary btn-sm" title="Add New Row">
																<i class="fa fa-plus" aria-hidden="true"></i>
															</button>
														</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${userRoleHcMapList}"
														var="userRoleHcMapList" varStatus="index">
														<tr>
															<td data-title="Sl. No." align="center"><input type="hidden"
																name="userRoleHcMapId[]"
																value="${userRoleHcMapList.userRoleId}" />
																${index.count}</td>
															<td data-title="Primary Role"><select class="form-control form-control-sm"
																name="isPrimary[]" id="isPrimary${index.count}">
																	<option value="">--Select--</option>
																	<option value="1"
																		${userDetails.primaryRole.roleId eq userRoleHcMapList.roleId ?'selected':''}>YES</option>
																	<option value="0"
																		${userDetails.primaryRole.roleId ne userRoleHcMapList.roleId ?'selected':''}>No</option>
															</select></td>
	
															<td data-title="Role"><select class="form-control form-control-sm"
																name="roleName[]" id="roleName${index.count}"
																data-plugin-selectTwo
																onchange="checkDuplicate(${index.count},this.value)">
																	<option value="">--Select--</option>
																	<c:forEach items="${roleList}" var="roleList">
																		<c:choose>
																			<c:when
																				test="${userRoleHcMapList.roleId eq roleList.roleId}">
																				<option value="${roleList.roleId}"
																					selected="selected">${roleList.displayName}</option>
																			</c:when>
																			<c:otherwise>
																				<option value="${roleList.roleId}">${roleList.displayName}</option>
																			</c:otherwise>
																		</c:choose>
																	</c:forEach>
															</select></td>
															<td data-title="Status"><select class="form-control form-control-sm"
																name="status[]" id="status${index.count}">
																	<option value="1" ${1 eq 1 ?'selected':''}>Active</option>
																	<option value="0" ${1 eq 0 ?'selected':''}>Inactive</option>
															</select></td>
															<td data-title="+" style="text-align: center;">
																<button type="button" class="btn btn-danger btn-sm"
																	id='deleteMap${userRoleHcMapList.userRoleId}'
																	onclick='deleteRow(this);' disabled>
																	<i class='fa fa-minus' aria-hidden='true'
																		title='Remove Current Row'></i>
																</button>
															</td>
														</tr>
														<c:set var="countRow" value="${index.count+1}"></c:set>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
									<div class="col-md-12 mt-2 text-center">
										<input type="button" class="btn btn-success btn-sm"
											value="Submit" onclick="return validateForm()"> <a
											href="${contextPath}/" type="button"
											class="btn btn-warning btn-sm">Back</a>
										<!-- <input type="submit" value="submit"
						 class="btn btn-success"> -->

									</div>
								</form>
							</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<form id="editUserRoleHCMap" action="./edit-user-role.htm" method="post">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" /> <input type="hidden"
		name="editUserRoleFormId" id="editUserRoleFormId" />
</form>
<script>
function userNameValidation(){
	var userName = $("#username").val();  
	$.ajax({
		type : "GET",
		url : "${contextPath}/umt/user/validate-user-name",
		data : {
			"userName" : userName,
		}, 
		success : function(response) {
			var val = JSON.parse(response); 
			if (val.isDuplicate == true) {
				bootbox.alert("Duplicate user name not allowed"); 
				var html ='<div class="col-md-12"><input type="text" id="username" name="username" value="${userDetails.userName}" class="form-control form-control-sm" required="required" onchange="userNameValidation();"></div>'
				$("#hideUserId").empty().append(html);
			}  
		}, 
		error : function(error) {
			//bootbox.alert("Failure"); 
		}
	});
} 
	$(document).ready(function(){
		var check = "${sentUserRoleData}";
		if(check =='departmentalUser'){
			$('#departmentalUserDiv').show();
		}
		else{
			
		}
	});
	
	function addUpdateUserRole(userId){
		bootbox.confirm({
			title : labelConfirm,
			message : "Do you want to edit User Role Health Checkup Centres?",
			buttons : {
				confirm : {
					label : labelYes,
					className : "btn-success"
				},
				cancel : {
					label : labelNo,
					className : "btn-danger"
				}
			},
			callback : function(result) {
				if(result == true){
					$('#editUserRoleFormId').val(userId);
					$('#editUserRoleHCMap').submit();
				}
			}
		});
	}
	
	function createRow(){
		var table = document.getElementById("tableId1");
		/* var totalRows = $("#tableId1 tr").dataTable().length-1; */
		var totalRows = $('#tableId1 tr').length-1;
		
		//totalRows = getDataTableRowLengthExceptTHcells("tableId");
		/* alert(totalRows); */
		var rName = "";
		var hName = "";
		var table = document.getElementById("tableId1");
		var row = table.insertRow(totalRows + 1);
		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1);
		var cell3 = row.insertCell(2);
		var cell4 = row.insertCell(3);
		var cell5 = row.insertCell(4);
		
		cell5.className = "AddRemoveRowIconCell"; 
		
		<c:forEach items="${roleList}" var="roleList">
			<c:if test="${roleList.roleCode ne 'ROLE_UMITRA' && roleList.roleCode ne 'ROLE_CEO'}">
				rName = rName+"<option value='${roleList.roleId}'>${roleList.displayName}</option>";
			</c:if>
		</c:forEach> 
		
		
		
		totalRows += 1;
		
		var cellVal1 = '<select name="roleName[]" id="roleName' +totalRows+ '" class="form-control form-control-sm" onchange="checkDuplicate(' +totalRows+ ',this.value)" >';
		var cellVal2 = '<select name="isPrimary[]" id="isPrimary' +totalRows+ '" class="form-control form-control-sm multi-formcontrol" >';
		var cellVal3 = '<p align = "center"><input type="hidden" name="userRoleHcMapId[]" value="0" />'+totalRows+'</p>';
		var cellVal4 = '<select name="status[]" id="status' +totalRows+ '" class="form-control form-control-sm multi-formcontrol">';
		

		cell1.innerHTML = ""+cellVal3+"";
		
		cell2.innerHTML = ""+cellVal2+ "<option value=''>--Select--</option><option value ='1'>YES</option>"+"<option value ='0'>NO</option></select>";
		
		
		cell3.innerHTML = ""+cellVal1+ "<option value=''>--Select--</option>"+rName+"</select>";
		
		cell4.innerHTML = ""+cellVal4+ "<option value=''>--Select--</option><option value ='1' selected>Active</option>"+"<option value ='0'>Inactive</option></select>";
		
		cell5.innerHTML = "<button type='button' class='btn btn-danger btn-sm' id='deleteMap"+totalRows+"' onclick='deleteRow(this);' title='Remove Current Row'><i class='fa fa-minus' aria-hidden='true'></i></button>";

		renumberRows();
	}
	
	function deleteRow(row){
		//var rowno = document.getElementById("tableId1").rows.length;
		//rowno = getDataTableRowLengthExceptTHcells("tableId1");
		var rowno = $('#tableId1 tr').length-1;
		
		if(rowno == 1){
			bootbox.alert(errMsgOneRowMustInDelete);
		}
		else{
			var i = row.parentNode.parentNode.rowIndex;
			var rowIds = i - 1;

			document.getElementById('tableId1').deleteRow(i);
			var rowlength = document.getElementById("tableId1").rows.length;
		    var k = 0;

		    for(var j = 0; j <= rowno; j++){
		    	if(document.getElementById("roleName"+j)=='[object HTMLInputElement]'){
					document.getElementById("isPrimary"+j).id = "isPrimary"+k;
		    		document.getElementById("healthCheckup"+j).id = "healthCheckup"+k;
		    		document.getElementById("roleName"+j).id = "roleName"+k;
			        document.getElementById("deleteMap"+j).id = "deleteMap"+k;
			        document.getElementById("status"+j).id = "status"+k;
		  	        k = k + 1;
				}
		  	}
		}

		renumberRows();
	}

	function renumberRows()
	{
		var allRows = $('#tableId1 tr');
		$.each(allRows, function(idx, elem){
			if (idx > 0)
			{
				let fTd = $(elem).find('td')[0];
				let p = $(fTd).find('p')[0];
				let inp = $(fTd).find('input')[0];
				
				$("#"+$(elem).find('td')[1].childNodes[0].id).attr("id","isPrimary"+idx);
				$("#"+$(elem).find('td')[2].childNodes[0].id).attr("id","roleName"+idx);
				$("#"+$(elem).find('td')[3].childNodes[0].id).attr("id","status"+idx);
				
				$(fTd).html('');
				
				$(p).html('');
				$(p).append($(inp));
				$(inp).after(idx);
				$(fTd).append($(p));
			}
		})
	}
	
	function validateForm(){
		
		var uName = $('#username').val();
		var fName = $('#firstname').val();
		var lName = $('#lastname').val();
		var umob = $('#userMobile').val();
		var email = $('#userEmail').val();

		if (uName == "")
		{
			bootbox.alert("Please provide unqiue user id.");
			return false;
		}

		if (fName == "")
		{
			bootbox.alert("Please provide first name.");
			return false;
		}

		if (lName == "")
		{
			bootbox.alert("Please provide last name.");
			return false;
		}

		if (umob == "")
		{
			bootbox.alert("Please provide mobile number.");
			return false;
		}

		if (umob.length != 10)
		{
			bootbox.alert("Please provide valid mobile number.");
			return false;
		}

		if (email == "")
		{
			bootbox.alert("Please provide email id.");
			return false;
		}

		var validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

		if (!email.match(validRegex)) {
			bootbox.alert("Please provide valid email id.");
			return false;
		}
		
		if ($("#designation").val() == "")
		{
			bootbox.alert("Please give designations");
			return false;
		}
		
		var totalRows = $('#tableId1 tr').length-1;
		if (totalRows == 0)
		{
			bootbox.alert("Please assign a Primary Role to the user.");
			return false;
		}
		var authCount = 0;
		var i;
		for(i = 1; i < totalRows+1; i++){
			if($("#isPrimary"+i).val() == 1){
				authCount++;
			}
			if($("#isPrimary"+i).val() == ""){
				bootbox.alert("Please select primary role.");
				return false;
			}
			/* if($('#healthCheckup'+i).val() == ""){
				bootbox.alert("Please select health checkup center.");
				return false;
			} */
			if($('#roleName'+i).val() == ""){
				bootbox.alert("Please select role.");
				return false;
			}
			if($('#status'+i).val() == ""){
				bootbox.alert("Please select status.");
				return false;
			}
			if($('#roleName'+i).val() != ""){
				if($("option:selected", $('#roleName'+i)).text() == 'Guest'){
					bootbox.alert("Please assign role other than Guest.");
					return false;
				}
			}
		}

		if(authCount == 0){
			bootbox.alert("Please assign a Primary Role to the user.");
			return false;
		}
		
		if(authCount > 1){
			bootbox.alert("Only one role can be saved as primary role.");
			return false;
		}
			$('#userForm').submit();
	}
	
	function checkDuplicate(count, roleID){
			var rows =  $('#tableId1 tr').length-1 ; //$('#tableId1 tbody > tr').length;
			var dupc = 0;
			 for (i = 1; i <= rows; i++) {
				var count1=i
				var role = $('#roleName'+count1).val();
				if(roleID === role){
					dupc ++;
				}
			  } 
			if(dupc > 1){
				bootbox.alert("You have entered duplicate data.");
				$("#roleName"+count).val("");
				return false;
			}
			else{
				return true;
			}
		
	}

	$(document).ready(function() {
		$('#ssodepid').DataTable();
	})
</script>


