
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="userDetails" value="${serviceOutcome.data}" />
<sec:authentication var="principal" property="principal" />
<script type="text/javascript"
	src="${contextPath}/assets/appJs/validation/common-utils.js"></script>
	
	<div class="content">
	<div class="container-fluid" style="margin-top: -100px; position: relative; z-index:99;" >
	<%@ include file="/WEB-INF/views/message.jsp"%>
                <div class="row inner-cardbox">
                    <h6> User Details</h6>
                    <hr>
                        <div class="col-md-6">
                            <div class="row">
                                <div class="form-group mb-2">
                                    <label>Name</label>
                                    <input type="text" class="form-control" id="memberName" name="memberName" value="${pntData.member.memberName}" read>
                                </div>
                            </div>

                            <div class="row">
                             <div class="form-group col-md-6 mb-2">
                                    <label>Contact Number</label>
                                    <input type="tel" class="form-control" oninput="this.value=this.value.replace(/[^0-9]/g,'');"
											maxlength="10" onchange="is_mobile_valid(this.value);"
											id="phoneNo" name="phoneNo" value="${pntData.member.phoneNo}">
                                </div>
                                
                              <div class="form-group col-md-6 mb-2">
                                    <label>Email</label>
                                    <input type="text" class="form-control" 
											onchange="mailVal(this.value)" id="emailId" name="emailId"
											value="${pntData.member.emailId}">
                                </div>
                                
                                <div class="form-group col-md-6 mb-2">
                                    <label>Gender</label>
                                    <input type="text" class="form-control" 
											onchange="mailVal(this.value)" id="emailId" name="emailId"
											value="${pntData.member.gender.genderNameEN}">
                                </div>

                                
                                <div class="form-group col-md-6 mb-2">
                                    <label>Date of Birth</label>
                                    <div class="datepicker_con_add">
                                    <input type="text" class="form-control"
											id="dob" name="dob" style="pointer-events: none;"
											value="<fmt:formatDate pattern="dd/MM/yyyy" value="${pntData.member.dob}" />">
											</div>
                                </div>
                                <div class="form-group col-md-6 mb-2">
                                    <label>Issue Date </label>
                                    <div class="datepicker_con_add">
                                    <input type="text" class="form-control"
											id="dob" name="dob" style="pointer-events: none;"
											value="<fmt:formatDate pattern="dd/MM/yyyy" value="${pntData.issueDate}" />">
											</div>
                                </div>
                                <div class="form-group col-md-6 mb-2">
                                    <label>Valid Date</label>
                                    <div class="datepicker_con_add">
                                    <input type="text" class="form-control"
											id="dob" name="dob" style="pointer-events: none;"
											value="<fmt:formatDate pattern="dd/MM/yyyy" value="${pntData.validDate}" />">
											</div>
                                </div>
                                
                            </div>

                            <div class="row">
                                <div class="col-md-12 mt-2">
                                    <label>Address Information</label>
                                    <textarea class="form-control" style="height:80px;"
											oninput="this.value = this.value.replace(/[^A-Za-z0-9\-_!@#$%^&*()]/g, '');"
											maxlength="10" id="address"
											name="address">${pntData.member.address}</textarea>
                                </div>
                            </div>

                            <div class="col-md-12 mt-3">
								<%-- <c:if test="${principal.dbUser.primaryRole.roleCode eq 'ROLE_MEMBER' } ">
				                 	 <a href="${contextPath}/" class="btn btn-warning btn-sm" type="button" >MEMBER_Back</a>
					            </c:if>
					            <c:if test="${principal.dbUser.primaryRole.roleCode ne 'ROLE_MEMBER' } ">
					                 <a href="${contextPath}/mst/list-member" class="btn btn-warning btn-sm" type="button" >LA_Back</a>
					            </c:if> --%>
					            <c:choose>
								    <c:when test="${principal.dbUser.primaryRole.roleCode ne 'ROLE_MEMBER'}">
								        <a href="${contextPath}/mst/list-member" class="btn btn-success btn-sm" type="button" style="padding:5px 12px">Back</a>
								    </c:when>
								    <c:otherwise>
								        <a href="${contextPath}/" class="btn btn-success btn-sm" type="button" style="padding:5px 12px; border:none;">Back</a>
								    </c:otherwise>
								</c:choose>
				            </div>
				            
                        </div>
                        
                        <div class="col-lg-6">
                        	<div class="id-card-tag"></div>
								<div class="id-card-tag-strip"></div>
								<div class="id-card-hook"></div>
								<div class="id-card-holder">
									<div class="id-card">
										<div class="header">
											<img src="../assets/image/logo.png">
										</div>
										<div class="photo">
                                   <img class="profile-pic" src="${contextPath}/image/viewimage?filePath=${pntData.member.imagePath}">
										</div>
										<h6>${pntData.member.memberName}</h6>
							      		<h3 style="font-weight:500">${pntData.approveNo}</h3>
										<p style="font-size: 10px;"><strong>Valid:</strong> <fmt:formatDate pattern="dd/MM/yyyy" value="${pntData.issueDate}" /> - <fmt:formatDate pattern="dd/MM/yyyy" value="${pntData.validDate}" /></p>
										<hr>
										<ul class="list-unstyled d-flex align-items-center justify-content-around">
											<li><img class="img-fluid" src="data:image/jpeg;base64,${base64Image}" alt="Image" /></li> 
											<li class="text-start">
												<p><strong>NIIT University</strong> Neemrana, NH-8 Delhi-Jaipur highway <p>
												<p>District Alwar, Rajasthan <strong>301705</strong></p>
												<p>Ph: 01494-660600, 7073222393</p>
											</li>
										</ul>
									</div>
								</div>
								
								  <div class="text-center mt-3"><button onclick="downloadAsImage()" class="btndownload">Download as Image</button></div>
                        </div>
                      

<!-- 		<div class="col-md-4"> -->
<!-- 			<h6 class="mb-2">barcode</h6> -->
<!-- 			<div class="circle upload-button"> -->
<%-- 			<img class="img-fluid" src="data:image/jpeg;base64,${base64Image}" alt="Image" /> --%>
<!-- 			</div> -->

<!-- 		</div> -->
		<%-- <div class="col-md-3">
            <h6 class="mb-2">Image</h6>
            <div class="circle upload-button">
               
                 <c:if test="${not empty pntData}">
                <img class="profile-pic" src="${contextPath}/image/viewimage?filePath=${pntData.member.imagePath}">
               </c:if>
               
            </div>
            
        </div> --%>
                        
                </div>

            </div>
            </div>
            
            
     
     <script>
     $(document).ready(function() {
    	    $('input').prop('readonly', true);
    	    $('textarea').prop('readonly', true);
    	});

     </script>       
<script type="text/javascript"
				src="${contextPath}/assets/cansvas/html2canvas.js"></script>
			
			<script>
			function downloadAsImage() {
    // Get the HTML element to capture
    var element = document.querySelector('.id-card');
			
    // Use html2canvas to capture the content as an image
    html2canvas(element).then(function(canvas) {
        // Convert the canvas to data URL
        var dataURL = canvas.toDataURL('image/png');
			
        // Create a download link
        var link = document.createElement('a');
        link.href = dataURL;
        link.download = 'id_card.png';
			
        // Trigger the download
        link.click();
    });
			}
			</script>       
            





 






