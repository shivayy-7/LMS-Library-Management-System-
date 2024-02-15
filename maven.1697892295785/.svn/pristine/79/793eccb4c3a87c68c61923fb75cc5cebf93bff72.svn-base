
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
	<%-- <div class="panel-header bg-primary-gradient">
		<div class="page-inner py-4">
			<div
				class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
				<div>
					<h2 class="text-blue pb-2 fw-bold">Add Library</h2>
				</div>
				<div class="ml-md-auto py-2 py-md-0">
					<a href="${contextPath}"
						class="btn btn-sm btn-border btn-blue btn-round mr-2"><i
						class="fa fa-home"></i></a> <a href="#"
						class="btn btn-sm btn-border btn-blue btn-round mr-2">Add Library</a> <a href="#"
						class="btn btn-sm btn-border btn-blue btn-round mr-2">/Add Library</a>
				</div>
			</div>
		</div>
	</div> --%>
	<div class="container-fluid" style="margin-top: -100px; position: relative; z-index:99">
		<div class="row inner-cardbox">
			<h6>Add Library</h6><hr>
<%@ include file="/WEB-INF/views/message.jsp"%>
			
					
					
						<form action="${contextPath}/mst/manage-lib" id="submitForm" method="POST" class="row" >
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						    <input type="hidden" name=libraryVO.libId value="${libDtls.libraryVO.libId}" />
						    <input type="hidden" name="libraryVO.libCode" value="${libDtls.libraryVO.libCode}" />
						    
							<div class="col-md-2">
									<label for="libName" class="required"> Library Name :</label>
									<input type="text" class="form-control form-control-sm AlphabetsOnly" id="libName" oninput="this.value = this.value.toUpperCase();" name="libraryVO.libName" value="${libDtls.libraryVO.libName}" maxlength="35">
							</div>
							
						


							<div class="col-md-2 ">
									<label for="mobile" class="required">Contact Number: </label>
									<input type="tel" class="form-control form-control-sm" oninput="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="10" onchange="validateMobileNumber(this.value, 'mobile');" id="mobile" name="libraryVO.mobile" value="${libDtls.libraryVO.mobile}">
							</div>
							<div class="col-md-2">
									<label for="email" class="required">Email ID :</label> 
									<input type="text" class="form-control form-control-sm" id="email" onchange="validateEmail(this)" name="libraryVO.email" value="${libDtls.libraryVO.email}" maxlength="25">
							</div>

                            <div class="col-md-2">
									<label for="openingHours" class="required">Opening Hour:</label> 
									<input type="text" class="form-control form-control-sm NumbersOnlybtn"  id="openingHours" name="libraryVO.openingHours" value="${libDtls.libraryVO.openingHours}" maxlength="2">
							</div>
							
							<div class="col-md-4">
							    <label class="smallInput required"> Address Of Library: </label>
							    <textarea class="form-control form-control-sm " id="libAddress"  oninput="this.value = this.value.toUpperCase();" name="libraryVO.libAddress" maxlength="150">${libDtls.libraryVO.libAddress}</textarea>
							</div>

							<div class="col-md-12 mt-3" style="text-align:center;">
							<input type="button" class="btn btn-success btn-sm" onclick="submitButton();" value="Submit" />
							<a href="${contextPath}/mst/add-library" class="btn btn-danger btn-sm">Cancel</a>
					  		</div>

						</form>
					
				
			
		</div>
	</div>
</div>


<script>
// function submitButton(){
// 	$("#submitForm").submit();
// }

function submitButton(){
	debugger;
	var labels = document.querySelectorAll('label[class="required"]');
	var falseFields=0;
	labels.forEach(function(label) {
		if(falseFields == 0){
	    var labelId = label.getAttribute('for');
	    var inputElement = document.getElementById(labelId);

	        var inputValue = inputElement.value;
	        if (inputValue === '' || inputValue === undefined || inputValue === '0') {
	            bootbox.alert("Please Enter "+label.textContent );
	            falseFields ++;
	            return false;
	        } 
		}
	});
	
if (falseFields == 0) {
	$("#submitForm").submit();
} 
}

$('.NumbersOnlybtn').on('keypress', function (event) {
    var regex = new RegExp("^[0-9]+$");
    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);

    // Check if the key is a number
    if (!regex.test(key)) {
        event.preventDefault();
        return false;
    }

    // Check if the entered value is between 0 and 24
    var enteredValue = parseInt($(this).val() + key, 10);
    if (enteredValue < 0 || enteredValue > 24) {
        event.preventDefault();
        return false;
    }
});


</script>
