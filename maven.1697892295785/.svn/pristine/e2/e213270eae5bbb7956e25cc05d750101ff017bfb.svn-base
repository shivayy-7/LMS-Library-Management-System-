<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${contextPath}/assets/appJs/validation/common-utils.js"></script>
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
                    <h6> Add Author</h6><hr>
								<form class="form-horizontal" action="${contextPath}/mst/saveAuthorDetails" method="POST" id="authorSubmitPage">																		
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />										
										<input type="hidden" name="authorId" value="${editAuthor.authorId}" /> 										
											<input type="hidden" id="viewStatus" value="${status}" >
									
									<div class="row">
										
											<div class="col">
													<div class="form-group">
														<label class="col-md-12 required" for="complaintName">Author Name</label>
														<div class="col-md-12 ">
															<input type="text" id="authorName" name="authorName" value="${editAuthor.authorName}"class="form-control AlphabetsOnly"  oninput="this.value = this.value.toUpperCase();"  maxlength="30"   >																
														</div>
													</div>
												</div>
											<div class="col">
											<div class="form-group">
												<label class="col-sm-12 required">Gender</label>
												<div class="col-sm-12">
													<select class="form-control" id="genderId" 
														name="genderId" >
														<option value="0">Select</option>
														<c:forEach items="${genderList}" var="genderLst">														
															<option value="${genderLst.genderId}"${genderLst.genderId eq editAuthor.gender.genderId ? 'selected' :''}>${genderLst.genderNameEN}</option>																
																														
														</c:forEach>
													</select>
												</div>
											</div>
											</div>
											
										<div class="col">
													<div class="form-group">
														<label class="col-md-12 " for="complaintName">Address</label>
														<div class="col-md-12 ">
															<textarea type="text" id="address" name="address"class="form-control" oninput="this.value = this.value.toUpperCase();" maxlength="50"   ></textarea>																
														</div>
													</div>
												</div>
											
																	
											<div class="col">
												<div class="form-group">
													<label class="col-md-12" for="mobile">Mobile</label>
													<div class="col-md-12">
														<input type="text" name="mobileNumber" id="mobileNumber"
															class="form-control form-control-sm NumbersOnly"
															maxlength="10" onchange="validateMobileNo(this);"
															required placeholder="Enter Phone Number">
													</div>
												</div>
											</div>
										
									
											
											<div class="col">
												<div class="form-group">
													<label class="col-md-12" for="Email">Email
														Id</label>
													<div class="col-md-12">
														<input type="text" name="mail" id="mail" value="${editAuthor.mail}" class="form-control form-control-sm" onchange="validateEmail(this)"
															 required maxlength="150" placeholder="Enter Email Id">
													</div>
												</div>
											</div>
											
										
								
									</div>
									<div class="col mt-4 text-center">
									 <c:if test="${empty editAuthor.authorId}">
									 
										<input type="button" class="btn btn-success btn-sm"
											value="Submit" onclick="return validateForm()">
											
										<a href="${contextPath}/mst/addAuthorDetails" type="button"
											class="btn btn-warning btn-sm">Cancel</a>
									 </c:if>
									 <c:if test="${not empty editAuthor.authorId}">
									 
										<input type="button" class="btn btn-success btn-sm"
											value="Update" onclick="return validateForm()">
										<a href="${contextPath}/mst/getAuthorDetailsList" type="button"
											class="btn btn-warning btn-sm">Back</a>
									 </c:if>
											

									</div>
								</form>
							</div>
					</div>
				</div>
<script>
function validateForm() {
    var aName = $('#authorName').val();
    var gender = $('#genderId').val();
    var email = $('#mail').val();
   

    if (aName == "") {
   	bootbox.alert("Please enter Author name.");
        return false;
    }
    if (gender == "0") {
    	bootbox.alert("Please select Gender.");
        return false;
    }
    /* if (email == "") {
    	bootbox.alert("Please enter email id.");
        return false;
    }

    var validRegex =/^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$/;
    if (!email.match(validRegex)) {
    	bootbox.alert("Please enter a valid email id.");
        return false;
    }
   */
    bootbox.confirm({
        message: "Do you want to Submit/Back!",
        callback: function (proceed) {
            if (proceed) {
                $('#authorSubmitPage').submit();
            }
        }
    });
}


$(document).ready(function() {
	
	var status = $("#viewStatus").val();
   	if(status === 'VIEW'){
   		document.getElementById('authorName').readOnly = true;
   		document.getElementById('mail').readOnly = true;
   		document.getElementById('genderId').disabled = true; 
   		document.getElementById('mobileNumber').readOnly = true;
   		document.getElementById('address').readOnly = true;
   	}	   	
});
			
</script>