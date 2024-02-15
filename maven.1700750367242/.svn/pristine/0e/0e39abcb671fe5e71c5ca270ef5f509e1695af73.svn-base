<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
		<script src="${contextPath}/assets/vendor/bootbox5/bootbox.js"></script>
		<script src="${contextPath}/assets/vendor/bootbox5/bootbox.min.js"></script>
<script type="text/javascript" src="${contextPath}/assets/appJs/validation/common-utils.js"></script>
		
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
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
                    <h6>${empty categoryData.categoryId ? 'Add ' : 'Update '} Category</h6>
                    <hr>
                     <div class="col-md-12">
            <form class="row align-items-end mb-4" action="${contextPath}/mst/save-Category" method="post" id="categoryForm" enctype="multipart/form-data">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<input type="hidden" name="categoryId" value="${categoryData.categoryId}" />
                
                      <div class="col-md-2">
		                <div class="form-group">
		                  <label class="required" >Category Name :</label>
		                 	 <input type="text" class="form-control form-control-sm AlphabetsOnly" oninput="this.value = this.value.toUpperCase();" value="${categoryData.categoryName}" id="categoryName" name="categoryName" maxlength="35">
		                </div>
		              </div>
		              
		               <div class="col-md-2">
		                <div class="form-group">
		                  <label >Category Description :</label>
		                  	<textarea type="text" class="form-control form-control-sm AlphaNumericOnly"  id="categoryDescription" name="categoryDescription" maxlength="200">${categoryData.categoryDescription}</textarea>
		                </div>
		              </div>
		              
		               <div class="col-md-4">
		                <div class="form-group dwnl-btn">
		                  <label class="smallInput required" for="">Category Image :</label>
		                  	<input type="file" class="form-control form-control-sm" id="categoryImageId" name="categoryImage" onchange="checkFileTypeExtAndSize(this,'2')" value="${categoryData.categoryImage}">
		                	<c:if test="${categoryData.categoryImagePath != null}">
		                  		<button type="button" title="View Image" onclick = "viewImage()"><i class='fa fa-eye'></i></button>
		                	</c:if>
		                </div>
		              </div> 
		              
		             <div class="text-start col-md-2">
						<button type="button" class="btn btn-success btn-sm" onclick = "saveCategoryForm('${empty categoryData ? 'SAVE' : 'UPDATE'}')">${empty categoryData ? 'Save' : 'Update'}</button>
						<a href="${contextPath}/mst/add-Category" class="btn btn-warning btn-sm">Cancel</a>
				  	</div>

            </form>
            </div>
        </div>
    </div>
    <div class="container-fluid mt-3">
		<div class="row inner-cardbox">
			<div class="col-md-12">
			<h6> Category List</h6>
			<hr>
			<div class="table-responsive">
				<table  class="table table-striped exportbtn">
					<thead>
						<tr>
							<th>Sl no:</th>
							<th>Category Name:</th>
							<th>Category Code:</th>
							<th >Category Descriptions:</th>
							<th >Action:</th>										
						</tr>
					</thead>
					<tbody>
				 	   <c:forEach items="${categoryList}" var="catlist" varStatus="count"> 
							<tr>
								<td>${count.count }</td>
								<td>${catlist.categoryName}</td>
								<td>${catlist.categoryCode}</td>
								<td>${catlist.categoryDescription}</td>
								<td> 
									<button type="button" class="btn btn-warning btn-sm"    onclick = "editCategoryForm('${catlist.categoryId}')"  title="Edit"><i class='bx bx-pencil'></i></button>
            						<button type="button" class="btn btn-sm ${catlist.isActive eq false ? 'btn-danger' : 'btn-success'} btn-xs" data-toggle="tooltip" onclick = "actCategoryForm(${catlist.categoryId},${catlist.isActive})" title="${catlist.isActive eq true ? 'Click To Deactivate' : 'Click To Activate'}">
            						<i class="${catlist.isActive eq false ? 'fa fa-lock' : 'fa fa-unlock'}"></i></button>			            
            					</td>
							</tr>
				     </c:forEach> 
					</tbody>
				</table>
				</div>
			</div>
		</div>
</div>	
			
	<form action = "${contextPath}/mst/edit-Category" method="GET" id="categoryFormList">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<input type="hidden" name="categoryId" id="category1Id">
	</form>
	
	<form action = "${contextPath}/mst/activeInactiveCategory" method="post" id="categoryFormAct">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<input type="hidden" name="categoryId" id="categoryIdAct"/>
	<input type="hidden" name="status" id="status"/>
    </form> 
    
    <form action = "${contextPath}/image/viewDocuments" method="get" id="CatFormView" enctype="multipart/form-data" target="_blank">
	<input type="hidden" name="moduleName" id="moduleName"/>
	<input type="hidden" name="filePath" id="filePath"/>
      </form>

<script>
function saveCategoryForm(status){
	debugger
	  if($("#categoryName").val() == ""){
		  bootbox.alert("Please Enter Category Name.");
		  return false;
	  }
	  /* if($("#categoryDescription").val() == ""){
		  bootbox.alert("Please Enter Category Description");
		  return false;
	  }  */
	   if($("#categoryImageId").val() == ""){
		  bootbox.alert("Please Upload Category Image.");
		  return false;
	  }
	  else{
		 /*  var msg = status === 'SAVE' ? 'save' : 'update';
		  bootbox.confirm("Do you want to "+msg+" Category ?",function(result){
			  if(result){
				  $("#categoryForm").submit(); 
			  }
		  }); */
		  $("#categoryForm").submit(); 
	  }
}

function editCategoryForm(id){
	debugger;
	$("#category1Id").val(id);
	$("#categoryFormList").submit();
}

function actCategoryForm(id,status){
	debugger;
	$("#categoryIdAct").val(id);
	if(status == true){
		$("#status").val(false);
	}else{
		$("#status").val(true);
	}
	$("#categoryFormAct").submit();
}

function viewImage(){
	  $('#moduleName').val('CATEGORY');
	  $('#filePath').val('${categoryData.categoryImagePath}');
	  $('#CatFormView').submit();
}

</script>    
<script>
$(document).ready(function() {
    $('#example').dataTable();
} );
</script>
