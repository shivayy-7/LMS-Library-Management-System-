
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="userDetails" value="${serviceOutcome.data}" />
<script type="text/javascript"
	src="${contextPath}/assets/appJs/validation/common-utils.js"></script>

		<div class="container-fluid" style="margin-top: -100px; position: relative; z-index:99">	
	       <div class="row inner-cardbox">
	
<!-- 			<div class="col-md-12"> -->
<!-- 				<div class="card full-height"> -->
<!-- 					<div class="card-header"> -->
						
<!-- 						<h6 >Categories Wise Report</h6> -->
<%-- 					<%@ include file="/WEB-INF/views/message.jsp"%> --%>
<!-- 					</div> -->
<!-- 					<div class="card-body" style=""> -->
<!-- 					 <div class="row"> -->
<!--                          <div class="col-md-12"> -->
<!--                            <div class="form-group col-md-4"> -->
<!--                                     <label>From Date<span class="text-danger">*</span></label> -->
<!--                                     <div class="datepicker_con_add" style="postion:relative;" > -->
<!--                                     <input type="text" class="form-control" -->
<!-- 											id="fromDate" name="fromdate" style="pointer-events: none;" -->
<%-- 											value="<fmt:formatDate pattern="dd/MM/yyyy" />" readOnly> --%>
<!-- 											</div> -->
<!--                                 </div>  -->
                       
                         
<!--                          <div class="form-group col-md-4"> -->
<!--                                     <label>To Date<span class="text-danger">*</span></label> -->
<!--                                     <div class="datepicker_con_add" style="postion:relative;" > -->
<!--                                     <input type="text" class="form-control" -->
<!-- 											id="toDate" name="todate" style="pointer-events: none;" -->
<%-- 											value="<fmt:formatDate pattern="dd/MM/yyyy"  />" readOnly> --%>
<!-- 											</div> -->
<!--                                 </div>  -->
                                
                                
<!--                                     <div class="form-group col-md-4"> -->
<!-- 									<button type="button" id="filter" onclick="filterData()" -->
<!-- 										class="btn btn-sm btn-outline-info btn-center">Filter -->
<!-- 									</button> -->
<!-- 								</div> -->
<!--                                   </div> -->
                                  
                                 
<!--                          </div> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				</div> -->
<!-- 			</div> -->

			
			
					<h6 >Category-SubCategory Wise Report</h6>
					<hr>
				<%@ include file="/WEB-INF/views/message.jsp"%>
			
				
						 <div class="table-responsive ">
                                     <table class="table table-striped exportbtn">
								<thead>
									<tr>
										<th >Sl no.</th>
										<c:choose>
											<c:when test="${categoriesFillterDto.entitylevel eq 'CATEGORY'}">
												<th >CATEGORY</th>
											</c:when>
											<c:when test="${categoriesFillterDto.entitylevel eq 'SUBCATEGORY'}">
												<th>SUBCATEGORY</th>
											</c:when>
										</c:choose>
										<th>total book</th>
										<th>issue book</th>
										<th>Available book</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${catlist}" var="late" varStatus="count">
										<tr>
											<td>${count.count}</td>
											<c:choose>
											<c:when test="${not empty late.itemname}">
												<c:if test="${late.itemname eq 'CATEGORY'}">
												<td><a href="javascript:getData('SUBCATEGORY',${late.entityid})"><strong>${late.entityName}</strong></a></td>
												</c:if>
												<c:if test="${late.itemname eq 'SUBCATEGORY'}">
												<td>${late.entityName}</td>
												</c:if>
											</c:when>
											<c:otherwise>
												<td>${late.entityName}
											</c:otherwise>
										</c:choose>
											<td>${late.totalbook}</td>
											<td>${late.totalissuebook}</td>
											<td>${late.totalbook - late.totalissuebook}</td>
										</tr>

									</c:forEach>

									
								</tbody>
<%-- 								<c:if test="${empty catlist}"> --%>
<!-- 										<tr> -->
<!-- 											<td colspan="5" style="text-align: center;">No data -->
<!-- 												available</td> -->
<!-- 										</tr> -->
<%-- 									</c:if> --%>
							</table>

						</div>
				
		

	</div>
</div>



<form class="form-horizontal"
	action="${contextPath}/report/filltercategoriesdata" id="catagoriesdata" method="get">
<%-- 	 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> --%>
	<input type="hidden" name="fromdate" id="fromdate" />
	<input type="hidden" name="todate" id="todate" />
		<input type="hidden" name="entitylevel" id="hdnentitylevel" />
			<input type="hidden" name="levelid" id="hdnlevelid" />
		
</form>
<script>



function filterData() {
		$("#hdnentitylevel").val('CATEGORY');
		$("#hdnlevelid").val('0');
		$("#catagoriesdata").submit(); 
		
	}
	
function getData(status,id){
	
	if($("#fromDate").val()==""){
		bootbox.alert("Please provide the from date.")
		return false;
	}
	else if($("#toDate").val()==""){
		bootbox.alert("Please provide the to date.")
		return false;
	}else{
		$("#hdnentitylevel").val('SUBCATEGORY');
		$("#hdnlevelid").val(id);
		$("#catagoriesdata").submit();  
	}

}


	</script>

