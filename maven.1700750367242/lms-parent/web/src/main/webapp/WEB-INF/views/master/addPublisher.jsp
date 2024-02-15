<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${contextPath}/assets/appJs/validation/common-utils.js"></script>
<!-- <script src="https://crypto-js.googlecode.com/svn/tags/3.1.2/build/rollups/aes.js"></script> -->
<script type="text/javascript" src="${contextPath}/assets/js/AES/aes.js"></script>
<script type="text/javascript" src="${contextPath}/assets/js/AES/AesUtil.js"></script>
<script type="text/javascript" src="${contextPath}/assets/js/AES/common.js"></script>
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
                    <h6> Add Publisher</h6>
                    <hr>
                    
            <form action="${contextPath}/mst/manage-publisher" method="get" id="managePublisherForm" >
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
            <input type="hidden" name="publisherVO.publisherId" value="${publisherData.publisherVO.publisherId}" />
            <input type="hidden" name="publisherVO.publisherCode" value="${publisherData.publisherVO.publisherCode}" />
            <input type="hidden" id="encryptedDataa" name="encData" />
                <div class="row">
                 
                      <div class="col-md-2">
		                <div class="form-group">
		                  <label class="required" for="publisherName">Publisher Name :</label>
		                 	 <input type="text" class="form-control form-control-sm AlphabetsOnly" id="publisherName"  oninput="this.value = this.value.toUpperCase();" value="${publisherData.publisherVO.publisherName}" name="publisherVO.publisherName" maxlength="30">
		                </div>
		              </div>
		              
		               <div class="col-md-2">
		                <div class="form-group">
		                  <label class="" for="address">Address :</label>
		                  	<textarea class="form-control form-control-sm AlphaNumericOnly"  oninput="this.value = this.value.toUpperCase();" id="address" name="publisherVO.address" maxlength="60">${publisherData.publisherVO.address}</textarea>
		                </div>
		              </div>
		              
		              <div class="col-md-2">
		                <div class="form-group">
		                  <label class="" for="contact">Contact :</label>
		                  	<input type="text" class="form-control form-control-sm NumbersOnly" id="contact" value="${publisherData.publisherVO.contact}" name="publisherVO.contact" maxlength="10" onchange="validateMobileNumber(this.value,'contact')">
		                </div>
		              </div>
		              
		              <div class="col-md-2">
		                <div class="form-group">
		                  <label class="" for="website">Website :</label>
		                  	<input type="text" class="form-control form-control-sm " id="website" value="${publisherData.publisherVO.website}" maxlength="40" name="publisherVO.website" >
		                </div>
		              </div>
		              
		              <div class="col-md-2">
		                <div class="form-group">
		                  <label class="" for="desc">Description :</label>
		                  	<textarea class="form-control form-control-sm AlphaNumericOnly" id="desc" name="publisherVO.description" maxlength="100"> ${publisherData.publisherVO.description} </textarea>
		                  	
		                </div>
		              </div>
				  	
				  	<div class="col-md-12 mt-3" style="text-align:center;">
						<button type="button" class="btn btn-success btn-sm" onclick = "saveForm()">${empty publisherData.publisherVO.publisherId ? 'Save' : 'Update'} </button>
						<c:if test="${empty publisherData.publisherVO.publisherId}">
							<a href="${contextPath}/mst/add-publisher" class="btn btn-danger btn-sm">Cancel</a>
						</c:if>
						<c:if test="${not empty publisherData.publisherVO.publisherId}">
						     <a href="${contextPath}/mst/list-publisher" class="btn btn-danger btn-sm">Back</a>
						</c:if>
					</div>
           	</div>
            </form>
            </div>
        </div>	

    
<script>
// function saveForm(){
// 	debugger;
// 	$("#managePublisherForm").submit();
// }

function saveForm(){
	debugger;
	var labels = document.querySelectorAll('label[class="required"]');
	var falseFields=0;
	labels.forEach(function(label) {
		if(falseFields == 0){
	    var labelId = label.getAttribute('for');
	    var inputElement = document.getElementById(labelId);

	        var inputValue = inputElement.value;
	        if (inputValue === '' || inputValue === undefined || inputValue === '0') {
	            bootbox.alert("Please Enter "+label.textContent);
	            falseFields ++;
	            return false;
	        } 
		}
	});
	
if (falseFields == 0) {
	var formData = $('#managePublisherForm').serialize();
	var encryptionKey = "voF2jc4S5FnTNHDTKsQCtg";
// 	var encryptedData = encryptData(formData, encryptionKey);
// 	// URL encode the encrypted data
//     var encodedEncryptedData = encodeURIComponent(encryptedData);


        // Convert the form data object to a JSON string
        var jsonData = JSON.stringify(formData);

        // Encrypt the JSON data (use your encryption logic)
        var encryptedData = encryptData(jsonData, encryptionKey);

        // Set the encrypted data in the hidden input field
        $('#encryptedData').val(encryptedData);

        // Submit the form
        $('#managePublisherForm').submit();
    }

    // Your existing CryptoJS encryption function
    function encryptData(data, key) {
        var encrypted = CryptoJS.AES.encrypt(data, key);
        return encrypted.toString();
    }
</script>


    $('#encryptedDataa').val(encodedEncryptedData);
	$("#managePublisherForm").submit();
} 
}

function encryptData(data, key) {
	debugger;
    var encrypted = CryptoJS.AES.encrypt(data, key);
    return encrypted.toString();
}

function decryptData(encryptedData, key) {
	debugger;
    var decrypted = CryptoJS.AES.decrypt(encryptedData, key);
    return decrypted.toString(CryptoJS.enc.Utf8);
}

</script>    

