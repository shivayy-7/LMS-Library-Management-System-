
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="userDetails" value="${serviceOutcome.data}" />
<script type="text/javascript"
	src="${contextPath}/assets/appJs/validation/common-utils.js"></script>
	<script type="text/javascript"
	src="${contextPath}/assets/appJs/validation/AadharVaidator.js"></script>
	
	<style>
	.backbtn {
    height: 23px;
    transition: all linear 0.5s;
    line-height: 17px;
    /* background: #ddd; */
    border: #ddd 1px solid;
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
	
	<div class="content">
	<div class="container-fluid" style="margin-top: -100px; position: relative; z-index:99; width:98%">
	<%@ include file="/WEB-INF/views/message.jsp"%>
                <div class="inner-cardbox" style="padding:0">
                  
                   <form action="${contextPath}/mst/save-member" class="row" id="submitForm" method="POST" enctype="multipart/form-data">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						  <input type="hidden" name="memberId" value="${mamberData.memberId}" />
						  
                        <div class="col-md-9" style="padding:29px">
                       <h6>${empty mamberData.memberId ? 'Add Member' : 'Member Details'} </h6>
                    <hr>
                  <div class="row">
                         <div class="col-md-4">
                             <div class="form-group mb-1">
                                    <label>Name<span class="text-danger">*</span></label>
                                    <input type="text" class="form-control AlphaNumericOnly" id="memberName" maxlength="50" name="memberName" value="${mamberData.memberName}">
                                </div>
                                
                                   <div class="form-group mb-1">
                                    <label>Contact Number<span class="text-danger">*</span></label>
                                    <input type="tel" class="form-control" oninput="this.value=this.value.replace(/[^0-9]/g,'');"
											maxlength="10" onchange="is_mobile_valid(this.value);"
											id="phoneNo" name="phoneNo" value="${mamberData.phoneNo}">
                                </div>
                                
                                
                                <div class="form-group mb-1">
                                    <label>Email<span class="text-danger">*</span></label>
                                    <input type="text" class="form-control" 
											onchange="mailVal(this.value);emailDuplicate(this)" id="emailId" name="emailId" maxlength="50"
											value="${mamberData.emailId}">
                                </div>
                            </div>                                                           

                             
                             
                             
                               <div class="col-md-4">
                                <div class="form-group mb-1">
                                    <label>Gender<span class="text-danger">*</span></label>
                                    <select
									class="form-control" id="gender"
									name="gender.genderId">
									<option value="0">--Select--</option>
									<c:forEach items="${genderList}" var="gl">
										<option value="${gl.genderId}"
											${mamberData.gender.genderId eq gl.genderId ? 'selected' : '' }>${gl.genderNameEN}</option>
									</c:forEach>
								</select>
                                </div>
                                
                                   <div class="form-group  mb-1">
                                    <label>Type<span class="text-danger">*</span></label>
                                   <select class="form-control"
											id="memberTypeId" name="memberTypeId">
											<option value="">--Select--</option>
											<c:forEach items="${typeListData}" var="types">
												<option value="${types.valueId}"
													${types.valueId eq mamberData.memberTypeId ? 'selected':''}>${types.valueCode}</option>
											</c:forEach>
										</select>
                                </div>
                                
                                 <div class="form-group mb-1">
                                    <label>Date of Birth<span class="text-danger">*</span></label>
                                    <div class="datepicker_con_nextdisable" style="postion:relative;" >
                                    <input type="text" class="form-control"
											id="dob" name="dob" style="pointer-events: none;"
											value="<fmt:formatDate pattern="dd/MM/yyyy" value="${mamberData.dob}" />" readOnly>
											</div>
                                </div>                               
                              </div>  
                              
                              
                              
                              <div class="col-md-4">
                                   <div class="form-group mb-1">
                                    <label>Aadhar Number<span class="text-danger">*</span></label>
                                <input type="text" class="form-control form-control-sm NumbersOnly" onchange="aadharDupliCate(this); ValidAadhaarNoCheck(this);"  id="aadharNo"  maxlength="12" name="aadharNo" 
                                value="${mamberData.aadharNo}" >
                                </div>
                                
                                   <div class="form-group mb-1">
                                    <label>Address Information<span class="text-danger">*</span></label>
                                    <textarea class="form-control AlphaNumericOnly" style="height: 96px;"maxlength="200" id="address" name="address">${mamberData.address}</textarea>
                                </div>
                              </div>
                            </div>

                         

                            <div class="col-md-12 text-center mt-2">
                             <c:if test="${empty mamberData }">
                                <button type="button"  class="btn btn-primary btn-sm" onclick="submitButton();" value="Submit">Submit</button>
                                <a href="${contextPath}/" class="btn btn-warning btn-sm" type="button" >Back</a>
                                </c:if>
                             	<c:if test="${mamberData.status eq 'PENDING'}">
									<c:if test="${roleCode eq 'ROLE_LA'}">
									 <a class="btn btn-sm btn-success" title="Approve" data-bs-toggle="modal" data-bs-target="#approveModal" onclick="updateIntrest('${mamberData.memberCode}');" >APPROVE</a>
									 <a class="btn btn-sm btn-danger" title="REJECT" data-bs-toggle="modal" data-bs-target="#rejectModal" onclick="rejectMember('${mamberData.memberCode}');" >Reject</a>
									 <a	href="${contextPath}/mst/list-member" class="btn btn-warning btn-sm" type="button" >Back</a>
									</c:if>
								</c:if>
								
								<c:if test="${not empty cardData }">
									<c:if test="${roleCode eq 'ROLE_LA'}">
									 <a class="btn btn-xs int-btn btn-success" title="Re-Valid" data-bs-toggle="modal" data-bs-target="#Re-approveModal" onclick="updateReapproval('${mamberData.memberCode}');" >Re-Issue</a>
									 <a href="${contextPath}/mst/list-member" class="btn btn-warning btn-sm" type="button" >Back</a>
									</c:if>
								</c:if>
								
								 
                            </div>
                        </div>
                        
                        
                        

                        <div class="col-md-3">
                        <div class="profileright">
                            <h6 class="mb-2">Upload Image<span class="text-danger">*</span></h6>
                            <div class="circle upload-button">
                           <c:if test="${empty mamberData}">
                               <img class="profile-pic" src="../assets/image/avattar.png">
                               </c:if>
                               
                                 <c:if test="${not empty mamberData}">
                                <img class="profile-pic" src="${contextPath}/image/viewimage?filePath=${mamberData.imagePath}">
                               </c:if>
                               
                            </div>

                            <input class="file-upload" type="file" accept="image/*" name="addAttachment" id="addAttachment"
												onchange="checkFileTypeExtAndSize(this,'2');" value="${mamberData.imagePath}">
                            
 </div>
                        </div>
                    </form>
                </div>

            </div>
            </div>
 
<form action = "${contextPath}/image/viewDocuments" method="get" id="viewmamberDoc" enctype="multipart/form-data" target="_blank">
	<input type="hidden" name="moduleName" id="moduleName"/>
	<input type="hidden" name="filePath" id="filePath"/>
</form> 
<script>

$(document).ready(function() {
   if('${mamberData}'!=''){
	   $('#submitForm input').prop('disabled', true);
	    $('#submitForm textarea').prop('disabled', true);
	    $('#submitForm select').prop('disabled', true);
   }
});

function submitButton(){
	 if($("#memberName").val() == ""){
		  bootbox.alert("Please Provide  Name");
		  return false;
	  }if($("#gender").val() == "0"){
		  bootbox.alert("Please Provide  Gender");
		  return false;
	  }if($("#aadharNo").val() == ""){
		  bootbox.alert("Please Provide Aadhar Number");
		  return false;
	  }if($("#phoneNo").val() == ""){
		  bootbox.alert("Please Provide  Contact Number");
		  return false;
	  }if($("#memberTypeId").val() == ""){
		  bootbox.alert("Please Provide  Type ");
		  return false;
	  }if($("#emailId").val() == ""){
		  bootbox.alert("Please Provide  E-mail");
		  return false;
	  }if($("#dob").val() == ""){
		  bootbox.alert("Please Provide Dob");
		  return false;
	  }if($("#address").val() == ""){
		  bootbox.alert("Please Provide  Address");
		  return false;
	  }
	  var imagepath = '${mamberData.imagePath}';
	  if(imagepath ==""){
		  if($("#addAttachment").val() == ""){
			  bootbox.alert("Please Upload  Image");
			  return false;
		  } 
	  }
	$("#submitForm").submit();
}


function viewDocument(path){
	$("#filePath").val(path);
	var modulname = '${mambermodule}';
	$("#moduleName").val(modulname);
	$("#viewmamberDoc").submit();
}

function checkFileTypeExtAndSize(that ,ftype) {
	  var fileInput = $("#"+ that.id)[0];
	  var file = fileInput.files[0];
	  if (file) {
	            // Check file size (in bytes)
	             var maxSize =  1024 * 1024;
	            if (file.size > maxSize) {
	            bootbox.alert("File size exceeds 1Mb limit..");
	            $("#"+that.id).val('');
	return false;
	            }
	        }
	// Get the file upload control file extension
	var extn = $('#' + that.id).val().split('.').pop().toLowerCase();
	if (extn != '') {
	//debugger;       
	   // Create array with the files extensions to upload
	   var fileListToUpload; 
	   if (parseInt(ftype) == 1){
	       fileListToUpload = new Array('pdf', 'gif', 'jpg', 'jpeg', 'png');
	   }
	   else if (parseInt(ftype) == 2){
	       fileListToUpload = new Array('png', 'jpg', 'jpeg');
	   }
	   else{
	       fileListToUpload = new Array('pdf');
	   }
	   //Check the file extension is in the array.              
	   var isValidFile = $.inArray(extn, fileListToUpload);        
	   // isValidFile gets the value -1 if the file extension is not in the list. 
	   if (isValidFile == -1) {        
	       if (parseInt(ftype) == 1){
	          bootbox.alert("Please select a valid file of type pdf, gif, jpg, jpeg, png.");
	       }
	       else if (parseInt(ftype) == 2){
	          bootbox.alert("Please select a valid file of type png, jpg or jpeg.");
	       }
	       else{
	          bootbox.alert("Please select a valid pdf file only.");
	       }
	       $('#' + that.id).replaceWith($('#' + that.id).val('').clone(true));
	   }
	}
	else {
	   return true;
	}
	}
	
$(function() {
    $('.datepicker_con_add > input').datepick({
        onShow: $.datepick.monthOnly,
        dateFormat: 'dd/mm/yyyy',
        showOnFocus: true,
        yearRange: (new Date().getFullYear() - 23) + ':' + new Date().getFullYear(),
        showTrigger: '<button type="button" class="trigger">' +
            '<i class="bx bx-calendar"></i></button>',
         minDate: 0,
        onSelect: function(d) {
        }
    });
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



function updateIntrest(mebercode){
	$("#mamberCode").val(mebercode);
	
}

function rejectMember(mebercode){
	$("#hidemamberCode").val(mebercode);
}


function updateReapproval(mebercode){
	$("#hdnmamberCode").val(mebercode);
}

function sendapprove(){
	
	if($("#valiDate").val() == ""){
		  bootbox.alert("Please Select  Valid Date Of Member");
		  return false;
	  }
	$("#memberForm").submit();	
}

function sendreapprove(){
	
	if($("#revaliDate").val() == ""){
		  bootbox.alert("Please Select  Valid Date Of Member");
		  return false;
	  }
	$("#rememberForm").submit();	
}



function mailVal(){
	debugger;
	var email = $("#emailId").val();
	var pattern =/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if(!pattern.test(email)){
	bootbox.alert("Not valid email address");
	$("#emailId").val('');
	return false;
	}
	}
	
function is_mobile_valid(mobNum){
	  //  var filter = /^\d*(?:\.\d{1,2})?$/;
	    var filter = /^[6789][0-9]{9}$/;
	      if (filter.test(mobNum)) {
	        if(mobNum.length==10){
	         } else {
	        bootbox.alert('Please enter 10 digit mobile number');
	            return false;
	          }
	        }
	        else {
	        bootbox.alert('Not a valid mobile number');
	          $("#phoneNo").val("");
	          return false;
	       }
	}
	

function aadharDupliCate(that){
	var aadharNO = $('#' + that.id).val();
	$.ajax({
		type : "GET",
		url : "${contextPath}/mst/duplicate_aadhar_check",
		data : {
			"aadharNO" : aadharNO,
		}, 
		success : function(response) {
			var val = JSON.parse(response); 
			if (val.isDuplicate == true) {
				if(aadharNO == val.aadharno){
				bootbox.alert("Duplicate Aadhar Number entered.");
				  $("#"+that.id).val(''); 
				}
			} 
		}, 
		error : function(error) {
			bootbox.alert('Something went wrong');
		}
	});
}

 function emailDuplicate(that) {
        debugger;
        var email = $('#' + that.id).val().trim();
        $.get('${contextPath}' + "/mst/emailCheck", { email: email })
            .done(function (data) {
                if (data === true) {
                	bootbox.alert("Duplicate Email entered.");
    				$("#"+that.id).val(''); 
                } 
            })
            .fail(function () {
                alert("Something went wrong");
            });
} 

function ValidAadhaarNoCheck(that){
	var valueNm = $('#' + that.id).val();
	var filter = /^\d*(?:\.\d{1,2})?$/;
	  if (filter.test(valueNm)) {
	    if(valueNm.length==12){
	     } else {
	    	 bootbox.alert('Invalid Aadhaar No');
	   	  $("#"+that.id).val(''); 
	        return false;
	      }
	    }
	    else {
	    	bootbox.alert('Invalid Aadhaar No');
	  	  $("#"+that.id).val(''); 
	      return false;
	   }	
			if (!ValidAadhaarNo(valueNm)) {
		        bootbox.alert("Invalid Aadhaar No");
		        $("#"+that.id).val(''); 
		        return false;
		    }
			
	}
	
function rejectedData(){
	
	if($("#remark").val() == ""){
		  bootbox.alert("Please Input Remark");
		  return false;
	  }
	$("#memberFormReject").submit();	
	
}
</script>

  
  
  <!-- Modal -->
<div class="modal fade int-modal" id="approveModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <form class="modal-content" action="${contextPath}/mst/send-approve-member" id="memberForm" method="POST">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Approval Modal </h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<input type="hidden" name="mamberCode" id="mamberCode">
			
			<div class="col-md-3">
				<div class="form-group">
					<label class="col-md-12 smallInput required" for="">
						Valid Upto:</label>
					<div class="col-md-12 datepicker_con1"  style="position:relative;">
						<input type="text" class="form-control form-control-sm "
							id="valiDate" name="valiDate" style="pointer-events: none;">
					</div>
				</div>
			</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="sendapprove();">Submit</button>
      </div>
    </form>
  </div>
</div>


<div class="modal fade int-modal" id="Re-approveModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <form class="modal-content" action="${contextPath}/mst/send-reapprove-member" id="rememberForm" method="POST">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">User Re-Valid</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<input type="hidden" name="mamberCode" id="hdnmamberCode">
			
			<div class="col-md-3">
				<div class="form-group">
					<label class="col-md-12 smallInput required" for="">
						Re-Valid Date:</label>
					<div class="col-md-12 datepicker_con1">
						<input type="text" class="form-control form-control-sm "
							id="revaliDate" name="revaliDate" style="pointer-events: none;">
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



<div class="modal fade int-modal" id="rejectModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <form class="modal-content" action="${contextPath}/mst/reject-member" id="memberFormReject" method="POST">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Reject Modal </h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<input type="hidden" name="mamberCode" id="hidemamberCode">
			
			<div class="col-md-3">
				<div class="form-group">
					<label class="col-md-12 smallInput required" for="">
						Remark:</label>
					<div class="col-md-12">
						<input type="text" class="form-control form-control-sm "
							id="remark" name="remark">
					</div>
				</div>
			</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="rejectedData();">Submit</button>
      </div>
    </form>
  </div>
</div>





