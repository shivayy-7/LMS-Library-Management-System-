
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
<%@ include file="/WEB-INF/views/message.jsp"%>
	<div class="container-fluid" style="margin-top: -100px; position: relative; z-index:99">
		<div class="row inner-cardbox">
			<h6>Add Librarian</h6><hr>
								<form class="form-horizontal" action="${contextPath}/mst/saveLibrarianDetails" method="POST"id="librarianSubmitPage">
									<input type="hidden" name="${_csrf.parameterName}"value="${_csrf.token}" />						
										<input type="hidden" name="librarianId"value="${editLibrarian.librarianId}" />											 
											<input type="hidden" id="viewStatus" value="${status}" >
									
									<div class="row">
																														
												<div class="col-md-2 " >
									<div class="form-group">
										<label class="col-md-12 required" for="mobile">Library</label>
										<div class="col-md-12">
											<select class="form-control" id="libId" name="libId" >  												
													<option value="0">-Select-</option>
														<c:forEach items="${libraryList}" var="libraryLst">
															<option value="${libraryLst.libId}"
																${libraryLst.libId eq editLibrarian.library.libId ? 'selected' : ''}>
																${libraryLst.libName}</option>
														</c:forEach>

													</select> 
										</div>
									</div>
								</div>
								<div class="col-md-2">
													<div class="form-group">
														<label class="col-md-12 required" for="complaintName">Librarian Name</label>
														<div class="col-md-12 ">
															<input type="text" id="librarianName" name="librarianName"  oninput="this.value = this.value.toUpperCase();" value="${editLibrarian.librarianName}" class="form-control AlphabetsOnly" maxlength="30"   >																
														</div>
													</div>
												</div>
												
											<div class="col-lg-2">
											<div class="form-group">
												<label class="col-sm-12 required">Gender</label>
												<div class="col-sm-12">
													<select class="form-control" id="genderId" 
														name="genderId">
														<option value="0">-Select-</option>
														<c:forEach items="${genderList}" var="genderLst">														
															<option value="${genderLst.genderId}"${genderLst.genderId eq editLibrarian.gender.genderId ? 'selected' :''}>${genderLst.genderNameEN}</option>																
																														
														</c:forEach>
													</select>
												</div>
											</div>
											</div>
											
										<div class="col-md-2 ">
													<div class="form-group">
														<label class="col-md-12 required" for="complaintName">Address</label>
														<div class="col-md-12 ">
															<textarea  id="librarianAddress" name="librarianAddress" class="form-control" maxlength="100" >${editLibrarian.librarianAddress}</textarea>																
														</div>
													</div>
												</div>
											
																	
											<div class="col-md-2">
												<div class="form-group">
													<label class="col-md-12 required" for="mobile">Mobile</label>
													<div class="col-md-12">
														<input type="text" name="mobile" id="mobile" value="${editLibrarian.mobile}"
															class="form-control form-control-sm NumbersOnly"
															maxlength="10" onchange="validateMobileNo(this);"
															required placeholder="Enter Phone Number">
													</div>
												</div>
											</div>
										
									
											<div class="col-md-2">
												<div class="form-group">
													<label class="col-md-12 required" for="Email">Email Id</label>														
													<div class="col-md-12">
														<input type="email" name="email" id="email" value="${editLibrarian.email}"
															class="form-control form-control-sm emailsOnly" onchange="validateEmail(this)"
															 required maxlength="30" placeholder="Enter Email Id">
													</div>
												</div>
											</div>
											
										
									</div>
									<div class="col-md-12 mt-4 text-center">
									
									    <input type="button" class="btn btn-success btn-sm " id="valicationForm"
									           value="${not empty editLibrarian.librarianId ? 'Update' : 'Submit'}" onclick="return validateForm()"> 
                                    
                                    <c:if test="${not empty editLibrarian.librarianId}">
										<a href="${contextPath}/mst/getLibrarianDetailsList" type="button" class="btn btn-warning btn-sm">Back</a>
                                    </c:if>
                                    <c:if test="${empty editLibrarian.librarianId}">
										<a href="${contextPath}/mst/addLibrarianDetails" class="btn btn-danger btn-sm">Cancel</a>
                                    </c:if>

									</div>
								</form>
							</div>
					</div>
				</div>
<script>
function validateForm() {
	 var libName = $('#libId').val();
    var lName = $('#librarianName').val();
     var gender = $('#genderId').val();
     var address = $('#librarianAddress').val();
     var email = $('#email').val();
     var mobile = $('#mobile').val();
     if (libName == "0") {
    	 bootbox.alert("Please select Library.");
         return false;
     }
    if (lName == "") {
    	bootbox.alert("Please enter Librarian name.");
        return false;
    }
    if (gender == "0") {
    	bootbox.alert("Please select Gender.");
        return false;
    }
    if (address == "") {
    	bootbox.alert("Please enter librarian Address.");
        return false;
    }

		if (mobile == "")
		{
			bootbox.alert("Please enter mobile number.");
			return false;
		}

		if (mobile.length != 10)
		{
			bootbox.alert("Please provide valid mobile number.");
			return false;
		}
		
    if (email == "") {
    	bootbox.alert("Please enter email id.");
        return false;
    }

    var validRegex =/^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$/;
    if (!email.match(validRegex)) {
    	bootbox.alert("Please enter a valid email id.");
        return false;
    }
    
    bootbox.confirm({
        message: "Do you want to Submit/Back!",
        callback: function (proceed) {
            if (proceed) {
                $('#librarianSubmitPage').submit();
            }
        }
    });
}

$(document).ready(function() {
	
	var status = $("#viewStatus").val();
   	if(status === 'VIEW'){
   		document.getElementById('libId').disabled = true;
   		document.getElementById('librarianName').readOnly = true;
   		document.getElementById('email').readOnly = true;
   		document.getElementById('genderId').disabled = true;
   		document.getElementById('mobile').readOnly = true;
   		document.getElementById('librarianAddress').readOnly = true;
   		$("#valicationForm").hide();
   	}	   	
});
		
</script>


