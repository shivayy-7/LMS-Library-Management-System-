<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
.hidden {
    display: none;
}
.img-close {
	position: absolute;
    top: 2px;
    right: 15px;
    height: 26px;
    width: 26px;
    background: red;
    text-align: center;
    line-height: 26px;
    color: #fff;
    border-radius: 15px;
    cursor: pointer;
    z-index:999;
   }
   
   
.upload__inputfile {
  width: 0.1px;
  height: 0.1px;
  opacity: 0;
  overflow: hidden;
  position: absolute;
  z-index: -1;
}
.upload__btn {
  display: inline-block;
  font-weight: 600;
  color: #fff;
  text-align: center;
  min-width: 116px;
  padding: 5px;
  transition: all 0.3s ease;
  cursor: pointer;
  border: 2px solid;
  background-color: #4045ba;
  border-color: #4045ba;
  border-radius: 10px;
  line-height: 26px;
  font-size: 14px;
}
.upload__btn:hover {
  background-color: unset;
  color: #4045ba;
  transition: all 0.3s ease;
}
.upload__btn-box {
  margin-bottom: 10px;
}
.upload__img-wrap {
  display: flex;
  flex-wrap: wrap;
  margin: 0 -10px;
}
.upload__img-box {
  width: 180px;
  padding: 0 10px;
  margin-bottom: 12px;
}
.upload__img-close {
  width: 24px;
  height: 24px;
  border-radius: 50%;
  background-color: rgba(0, 0, 0, 0.5);
  position: absolute;
  top: 10px;
  right: 10px;
  text-align: center;
  line-height: 24px;
  z-index: 1;
  cursor: pointer;
}
.upload__img-close:after {
  content: "\ebe9";
  font-family:'boxicons';
  font-size: 14px;
  color: white;
}

.img-bg {
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  position: relative;
  padding-bottom: 100%;
}
.upload__btn {
   display: inline-block;
    font-weight: 600;
    color: #fff;
    text-align: center;
    min-width: 116px;
    padding: 5px;
    transition: all 0.3s ease;
    cursor: pointer;
    border: 2px solid;
    background-color: #3a3b44;
    border-color: snow;
    border-radius: 6px;
    line-height: 22px;
    font-size: 14px;
    height: 33px;
}

.custom-blue-btn {
        background-color: #2196F3; /* Blue color */
        color: white; /* Text color */
        /* Add any additional styles as needed */
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

<div class="container-fluid" style="margin-top: -100px; position: relative; z-index:99">
<%@ include file="/WEB-INF/views/message.jsp"%>
<%@ include file="/WEB-INF/views/ajaxLoader.jsp"%>
                <div class="row inner-cardbox">
                <c:if test="${status ne 'VIEW'}">
                    <h6>${empty bookDtls.bookCatalogVO.bookCatalogId ? 'Add Book' : 'Update Book'}</h6>
                </c:if>
                <c:if test="${status eq 'VIEW'}">
                    <h6>View Book</h6>
                </c:if>
                    <hr>
                    
            <form action="${contextPath}/mst/manage-book" method="post" id="manageBookForm" enctype="multipart/form-data" >
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
            <input type="hidden" name="bookCatalogVO.bookCatalogId" value="${bookDtls.bookCatalogVO.bookCatalogId}" />
            <input type="hidden" name="bookCatalogVO.bookCatalogCode" value="${bookDtls.bookCatalogVO.bookCatalogCode}" />
            <input type="hidden" id="imgPath" name="bookCatalogVO.imgPath" value="${bookDtls.bookCatalogVO.imgPath}" />
                <div class="row file_img">
                 
                     <div class="col-md-3">
		                <div class="form-group">
		                  <label class="required" for="isbnNo">ISBN NO :</label>
<%-- 		                 	 <input type="text" class="form-control form-control-sm NumbersOnly" id="isbnNo" value="${bookDtls.bookCatalogVO.isbnNo}" name="bookCatalogVO.isbnNo" onchange="bookDataByISBN(this.value)" maxlength="13"> --%>
                                 <input type="text" class="form-control form-control-sm NumbersOnly" id="isbnNo" value="${bookDtls.bookCatalogVO.isbnNo}" name="bookCatalogVO.isbnNo" oninput="this.value = this.value.replace(/[^0-9]/g, '').substring(0, 20)" onchange="bookDataByISBN(this.value)" maxlength="20">
                                 
		                </div>
		              </div>
                 
                      <div class="col-md-3">
		                <div class="form-group">
		                  <label class="required" for="bookTitle">Book Title :</label>
		                 	 <input type="text" class="form-control form-control-sm AlphabetsOnly" id="bookTitle" value="${bookDtls.bookCatalogVO.bookTitles}" name="bookCatalogVO.bookTitles" oninput="this.value = this.value.toUpperCase()" maxlength="30">
		                </div>
		              </div>
		              
		               <div class="col-md-3">
		                <div class="form-group">
		                  <label class="required" for="author" id="authorNameFromISBN">Author :</label>
		                  	 <select class="form-control form-control-sm " id="author" name="bookCatalogVO.author.authorId" >
							        <option value="0">--Select--</option>
							        <c:forEach items="${authorList}" var="author">
							            <option value="${author.authorId}" ${bookDtls.bookCatalogVO.author.authorId eq author.authorId ? 'selected' : ''} >${author.authorName}</option>
							        </c:forEach> 
							    </select>
<%-- 							    <input type="text" class="form-control form-control-sm AlphabetsOnly" id="author" value="${bookDtls.bookCatalogVO.authorId}" name="bookCatalogVO.authorId" > --%>
		                </div>
		              </div>
		              
		              <div class="col-md-3">
						 <div class="form-group">
						   <label class="required" for="totalBook">Total Book :</label>
						   <input type="text" class="form-control form-control-sm NumbersOnly" id="totalBook" value="${bookDtls.bookCatalogVO.totalBook}" name="bookCatalogVO.totalBook" maxlength="2" onchange="createBookRows(this.value, ${bookDtls.bookCatalogVO.totalBook});" ${bookDtls.bookCatalogVO.bookCatalogId != null ? 'readonly' : ''}>
						 </div>
					 </div>
		              
		              <div class="col-md-3">
		                <div class="form-group">
		                  <label class="required" for="website">Book Subject :</label>
		                  	<input type="text" class="form-control form-control-sm AlphabetsOnly" id="website" value="${bookDtls.bookCatalogVO.bookSubject}" name="bookCatalogVO.bookSubject" maxlength="30">
		                </div>
		              </div>
		              
		                <div class="col-md-3">
		                <div class="form-group">
		                  <label class="required" for="subCat">Sub Category :</label>
		                  	<select class="form-control form-control-sm " id="subCat" name="bookCatalogVO.subCategory.subCategoryId" >
							        <option value="0">--Select--</option>
							        <c:forEach items="${subCatList}" var="subCat">
							            <option value="${subCat.subCategoryId}" ${bookDtls.bookCatalogVO.subCategory.subCategoryId eq subCat.subCategoryId ? 'selected' : '' }>${subCat.subCategoryName}</option>
							        </c:forEach> 
							    </select>
		                </div>
		              </div>  
		              
		              <div class="col-md-3">
		                <div class="form-group">
		                  <label class="required" for="desc">Description :</label>
		                  	<textarea class="form-control form-control-sm" id="desc" name="bookCatalogVO.description" maxlength="100">${bookDtls.bookCatalogVO.description}</textarea>
		                </div>
		              </div>
		              
		              <%-- <div class="col-md-3">
		                <div class="form-group">
		                  <label class="required" for="desc">Language:</label>
		                  	<input type="text" class="form-control form-control-sm AlphabetsOnly" id="desc" value="${bookDtls.bookCatalogVO.language}" name="bookCatalogVO.language" >
		                </div>
		              </div> --%>
		              
		              <div class="col-md-3">
		                <div class="form-group">
		                  <label class="required" for="language">Language :</label>
		                  	<select class="form-control form-control-sm " id="language" name="bookCatalogVO.language.valueId" >
							        <option value="0">--Select--</option>
							        <c:forEach items="${languageList}" var="language">
							            <option value="${language.valueId}" ${bookDtls.bookCatalogVO.language.valueId eq language.valueId ? 'selected' : '' }>${language.valueCode}</option>
							        </c:forEach> 
							    </select>
		                </div>
		              </div>  
		              
		              <div class="col-md-3">
		                <div class="form-group">
		                  <label class="required" for="noOfPage">No of Page:</label>
		                  	<input type="text" class="form-control form-control-sm NumbersOnly" id="noOfPage" value="${bookDtls.bookCatalogVO.noOfPage}" name="bookCatalogVO.noOfPage" maxlength="5">
		                </div>
		              </div>
		              
		              <%-- <div class="col-md-3">
		                <div class="form-group">
		                  <label class="required" for="desc">Img Path :</label>
		                  	<input type="text" class="form-control form-control-sm " id="desc" value="${publisherData.publisherVO.description}" name="publisherVO.description" >
		                </div>
		              </div> --%>
		              
		              <div class="col-md-3">
		                <div class="form-group">
		                  <label class="required" for="bookPrice">Book price :</label>
		                  	<input type="text" class="form-control form-control-sm NumbersOnly" id="bookPrice" value="${bookDtls.bookCatalogVO.bookPrice}" name="bookCatalogVO.bookPrice" maxlength="10  ">
		                </div>
		              </div>
		              
		              <div class="col-md-3">
		                <div class="form-group">
		                  <label class="required" for="purchaseDate">Purchase Date :</label>
<!-- 			               <div class="datepicker-box"> -->
			               <div class="datepicker_con_nextdisable" style="postion:relative;" >
	                       <input type="text" class="form-control form-control-sm datepicker_con_nextdisable" id="purchaseDate" value="${bookDtls.bookCatalogVO.purchaseDate}" name="bookCatalogVO.purchaseDate" readonly="readonly">
<!-- 	                       <i class='bx bx-calendar'></i> -->
	                       </div>
		                </div>
		              </div>
		              
		              <div class="col-md-3">
		                <div class="form-group">
		                  <label class="required" for="publishDate" id="publishDateFromISBN">Publish Date :</label>
<!-- 		                    <div class="datepicker-box"> -->
                            <div class="datepicker_con_nextdisable" style="postion:relative;" >
		                  	<input type="text" class="form-control form-control-sm datepicker_con_nextdisable" id="publishDate" value="${bookDtls.bookCatalogVO.publishDate}" name="bookCatalogVO.publishDate" readonly="readonly" >
<!-- 		                  	<i class='bx bx-calendar'></i> -->
		                  	</div>
		                </div>
		              </div>
		              
		               <div class="col-md-3">
		                <div class="form-group">
		                  <label class="required" for="library">Library :</label>
		                  <select class="form-control form-control-sm " id="library" name="bookCatalogVO.lib.libId">
							        <option value="0">--Select--</option>
							        <c:forEach items="${libraryList}" var="library">
							            <option value="${library.libId}" ${bookDtls.bookCatalogVO.lib.libId eq library.libId ? 'selected' : '' }>${library.libName}</option>
							        </c:forEach> 
							    </select>
		                </div>
		              </div> 
		              
		               <div class="col-md-3">
		                <div class="form-group">
		                  <label class="required" for="publisher" id="publisherNameFromISBN" >Publisher :</label>
		                     <select class="form-control form-control-sm " id="publisher" name="bookCatalogVO.publisher.publisherId" >
							        <option value="0">--Select--</option>
							        <c:forEach items="${publisherList}" var="publisher">
							            <option value="${publisher.publisherId}" ${bookDtls.bookCatalogVO.publisher.publisherId eq publisher.publisherId ? 'selected' : '' }>${publisher.publisherName}</option>
							        </c:forEach> 
							    </select>
<%-- 							    <input type="text" class="form-control form-control-sm AlphabetsOnly" id="publisher" value="${bookDtls.bookCatalogVO.publisherId}" name="bookCatalogVO.publisherId" > --%>
		                </div>
		              </div> 
		              
		                 <%-- <c:if test="${status ne 'VIEW' }">
	 <div class="col-md-3" style="margin-top:26px;">
    <div class="upload__box">
  <div class="upload__btn-box">
    <label class="upload__btn">
      <p>Upload images</p>
       
      <input type="file" multiple data-max_length="20" class="upload__inputfile" id="bookCatalogImage" name="bookCatalogVO.bookCatogoryImages">
      
    </label>
  </div>
  <div class="upload__img-wrap"></div>
</div>
</div>
</c:if> --%>
		              
		              
		              <div class="col-md-3 hidden" id="selectedImage">
						    <div class="form-group">
						        <label for="image">Selected Image:</label>
<!-- <!-- 						        <img id="imagePreview" src="#" alt="Selected Image" style="max-width: 100px; max-height: 100px;"> -->
<!-- 						        <a href="#" type="button" id="viewimage" target="_blank" >view</a> -->
<%-- 						        <a href="data:image/png;base64,${image.encodeImagePath}" id="viewimage"><img class="img-fluid thumbnail" id="imgPath1" src="data:image/png;base64, ${image.encodeImagePath}" /></a> --%>
                                    <img class="img-fluid thumbnail" id="viewimage" src="https://covers.openlibrary.org/b/id/5546156-M.jpg" />
						    </div>
						</div>
						
						
						<%-- <c:if test="${not empty bookDtls.bookCatalogVO.imgPath && bookDtls.bookCatalogVO.imgPath != '#' && status ne 'VIEW'}">
						   <div class="col-md-3 " id="selectedImage">
						    <div class="form-group">
						        <label for="image">Selected Image:</label>
								<a href="${contextPath}/mst/viewImage?catalogCode=${bookDtls.bookCatalogVO.bookCatalogCode}&identity='BOOKCATALOG'" target="_blank" type="button">View</a>
						        
						        <a href="${contextPath}/mst/viewImage?image=${bookDtls.bookCatalogVO.imgPath}" target="_blank" type="button">View</a>
						        <img id="imagePreview" src="${bookDtls.bookCatalogVO.imgPath}" alt="Selected Image" style="max-width: 1S00px; max-height: 100px;">
						    </div>
						</div>
						</c:if> --%>
						
						<c:if test="${not empty bookDtls.bookCatalogImageVO}">

						        <c:forEach var="image" items="${bookDtls.bookCatalogImageVO}">
<%--                                         <a href="${contextPath}/mst/viewImage?catalogCode=${image.bookCatalogId}&identity='BOOKCATALOGIMAGE'" target="_blank" type="button">View</a> --%>
                                     <div class="col-lg-2 position-relative">
	                                     <div class="popup-gallery" id="close-gallery">
	                                     <c:if test="${status ne 'VIEW' }">
	                                      <i class="fa fa-times img-close" onclick="deletePic(${image.bookCatalogId})"></i>
	                                      </c:if>
	                                      <a href="data:image/png;base64,${image.encodeImagePath}"><img class="img-fluid thumbnail" src="data:image/png;base64, ${image.encodeImagePath}" /></a>
							        	 </div>  
									</div>
						        </c:forEach>
						</c:if>
						
						
						
						 <!-- div id="image-container">
				        <div id="image-slot">
				            Upload Images
				            <input type="file" id="image-upload" multiple style="display: none;" name="bookCatalogVO.bookCatogoryImages" >
				        </div>
				    	</div>

    <div id="fullscreen-modal">
        <img id="fullscreen-image" src="" alt="">
        <button id="prev-image">&#10094;</button>
        <button id="next-image">&#10095;</button>
        <button id="close-modal">X</button>
    </div>  -->
     <c:if test="${status ne 'VIEW' }">
   
   
    <div class="col-lg-3">
	    <div class="upload__box mt-2">
	    	<div class="upload__btn-box" style="margin-top:28px">
			    <label class="upload__btn">
			      <p>Upload images</p>
			       
			      <input type="file" multiple data-max_length="20" class="upload__inputfile" id="bookCatalogImage" name="bookCatalogVO.bookCatogoryImages">
			      
			    </label>
			 </div>
		</div>
	</div>
	
	<div class="col-lg-12 file_img_wrap" style="display: flex;   margin-bottom: 15px;">
		<div class="upload__img-wrap"></div>
	</div>
	
</c:if>
    
   <div class="col-md-12" >
<div class="table-responsive">
						<table id="bookTable"class="table table-striped">
						  <thead>
						    <tr>
						      <th>Book ID</th>
						      <th>Rack</th>
						      <th>Shelf</th>
<%-- 						      <c:if test="${not empty bookDtls.bookVOList}"> --%>
						      <th>Action</th>
<%-- 						      </c:if> --%>
						    </tr>
						  </thead>
						  <tbody>
						    <c:if test="${not empty bookDtls.bookVOList}">
						      <c:forEach var="book" items="${bookDtls.bookVOList}" varStatus="count">
						        <tr>
						          <td>
						            <input type="hidden" name="bookVOList[${count.index}].bookId" value="${book.bookId}" class="form-control" />
						            <input type="hidden" name="bookVOList[${count.index}].bookCode" value="${book.bookCode}" class="form-control" />
						            <input type="text" name="bookVOList[${count.index}].bookUkNo" value="${book.bookUkNo}" class="form-control" readonly="readonly"/>
						          </td>
						          <td>
						            <select name="bookVOList[${count.index}].rack.rackId" class="form-control" onchange="getShelfByRackId(this.value, 'shelf[${count.index}]')"> 
						              <option value="0">-select rack-</option>
						              <c:forEach var="rack" items="${rackList}"> 
						                <option value="${rack.rackId}" ${rack.rackId eq book.rack.rackId ? 'selected' : ''}>${rack.rackName}</option>
						              </c:forEach> 
						            </select> 
						          </td>
						          <td>
						            <select name="bookVOList[${count.index}].shelf.shelfId" class="form-control" id="shelf[${count.index}]">
						            </select>
						          </td>
						          <td>
						            <input type="hidden" id="myCheckbox[${count.index}]" value="false" class="form-control" />
						            <button type="button" id="deleteButton[${count.index}]" onclick="deleteRow(this)" >Delete</button>
						          </td>
						        </tr>
						      </c:forEach>
						    </c:if>
						  </tbody>
						</table>

		               <div class="col-md-12" style="text-align:center; align-items:center;">
						    <c:if test="${status ne 'VIEW'}">
						        <button type="button" class="btn btn-success btn-sm" onclick="saveForm()">
						            ${empty bookDtls.bookCatalogVO.bookCatalogId ? 'Save' : 'Update'}
						        </button>
						    </c:if>
						    <a href="${contextPath}/mst/add-book" class="btn btn-danger btn-sm">Cancel</a>
						</div>
						</div>
						</div>

           	</div>
           	
            </form>
            </div>
        </div>	
        
<script>

$(document).ready(function(){
    debugger;
    
    var status = "${status}";
    if (status === "VIEW") {
        $('#manageBookForm :input').prop('disabled', true);
    }
    
    var bookData = [
        <c:forEach items="${bookDtls.bookVOList}" var="item" varStatus="status">
            {
                'rackId': '${item.rack.rackId}',
                'shelfId': '${item.shelf.shelfId}'
            }
            <c:if test="${!status.last}">,</c:if>
        </c:forEach>
    ];

    for (let i = 0; i < bookData.length; i++) {
        getShelfByRackId(bookData[i].rackId, 'shelf['+i+']', bookData[i].shelfId);
    }
});

</script>

    
<script>

document.getElementById('bookCatalogImage').addEventListener('change', function() {
	debugger;
	var extn = $('#bookCatalogImage').val().split('.').pop().toLowerCase(); 
fileListToUpload = new Array('png', 'jpg', 'jpeg');
var isValidFile = $.inArray(extn, fileListToUpload);	
if(isValidFile == -1){
bootbox.alert("Please select a valid file of type png, jpg or jpeg."); 
$('#bookCatalogImage').val('')
return false;
}
	
const files = this.files;
/* if (files.length <= 1) {
bootbox.alert('Please select minimum 2 photos.');
    this.value = ''; // Clear the file input field
return false;
} */

if (files.length > 5) {
bootbox.alert('You can upload a maximum of 5 Photo.');
    this.value = ''; 
return false;
}


var fileInput = $("#"+ this.id)[0];
var file = fileInput.files[0];
if (file) {
     var maxSize = 1024*1024;
     if (file.size > maxSize) {
     bootbox.alert("File size should not exceed 1MB limit in Product Image 2 ");
     this.value = ''; 
return false;
     } 
 } 
var regr = /\s/;
if (regr.test(file.name)) {
bootbox.alert("Spaces are not allowed in the filename.");
this.value = ''; 
			return false;
		}
});

function deletePic(bookcatImgId) {
    debugger;
    $.get(contextPath + "/core/deleteBookCatalogImage", { id: bookcatImgId })
        .done(function (data) {
            var message = data.data;
        })
        .fail(function () {
            $('#loader').addClass('hidden');
            alert("error");
        });
}

function checkTableContent() {
    var table = document.getElementById('bookTable');
    if (table) {
        var rowCount = table.rows.length;

        for (var i = 0; i < rowCount; i++) {
            var row = table.rows[i];
            var cellCount = row.cells.length;

            for (var j = 0; j < cellCount; j++) {
                var cell = row.cells[j];
                var inputElement = cell.querySelector('input, select');
                if (inputElement) {
                    var elementType = inputElement.tagName.toLowerCase();
                    var elementValue = elementType === 'input' ? inputElement.value : inputElement.options[inputElement.selectedIndex].value;
                    if (elementValue.trim() === '' || elementValue.trim() === '0') {
//                         alert('Cell in row ' + (i + 1) + ', cell ' + (j + 1) + ' (' + elementType + ') is empty.');
                        return false;
                    }
                }
            }
        }
        return true;
    } else {
        console.error('Table with id "bookTable" not found.');
        return false;
    }
}

function saveForm(){
	debugger;
	 var tableData = checkTableContent();
     if(tableData){
			var labels = document.querySelectorAll('label[class="required"]');
			var falseFields=0;
			labels.forEach(function(label) {
				if(falseFields == 0){
			    var labelId = label.getAttribute('for');
			    var inputElement = document.getElementById(labelId);
		
			        var inputValue = inputElement.value;
			        if (inputValue === '' || inputValue === undefined || inputValue === '0') {
			            alert(label.textContent +"is required");
			            falseFields ++;
			            return false;
			        } 
				}
			});
			
		if (falseFields == 0) {
			/* var viewImageHref = $("#viewimage").attr("href");
			var urlWithoutHttps = viewImageHref.replace("https://", "");
			$("#imgPath").val(urlWithoutHttps); */
			
			var purchaseDate = $("#purchaseDate").val();
			var publishDate = $("#publishDate").val();
		
			var purchaseDateObj = parseDate(purchaseDate);
			var publishDateObj = parseDate(publishDate);
		
			if (publishDateObj >= purchaseDateObj) {
			  alert("Publish date should be before purchase date!");
			} else {
			  $("#manageBookForm").submit();
			}
		  }
     }else{
    	 alert("Please Fill Rack/Shelf");
     }
}

function parseDate(dateString) {
	  var parts = dateString.split("/");
	  // Note: JavaScript months are 0-based, so we subtract 1
	  return new Date(parts[2], parts[1] - 1, parts[0]);
	}

function deleteRow(button) {
    var row = button.parentNode.parentNode;
    row.parentNode.removeChild(row);
    var totalBook = $("#totalBook").val();
    $("#totalBook").val(totalBook-1);
}

function getShelfByRackId(rackId, shelfDropdownId, selecteditem, rackDropdownId) {
    debugger;
    $.get(contextPath + "/core/getData", { id: rackId, identity: 'SHELFBYRACK' })
        .done(function (data) {
            var blk = data.data;

            var shelfDropdown = document.getElementById(shelfDropdownId);
            shelfDropdown.innerHTML = '<option value="0">-select shelf-</option>';

            for (var i = 0; i < blk.length; i++) {
                var option = document.createElement('option');
                option.value = blk[i].shelfId;
                option.text = blk[i].shelfName;
                
                if(selecteditem != null){
	                if (blk[i].shelfId == selecteditem) {
	                    option.selected = true;
	                }
                }
                shelfDropdown.appendChild(option);
            }
            
        })
        .fail(function () {
            $('#loader').addClass('hidden');
            alert("error");
        });
}

function bookDataByISBN(isbnNo) {
    debugger;
    showAjaxLoader();
    // Use async/await to handle the asynchronous nature of isUniqueISBN
    async function fetchData() {
        var value = await isUniqueISBN(isbnNo.trim());

        if (value === true) {
        	hideAjaxLoader();
            alert("Duplicate ISBN No.")
        } else {
            isbnNo = isbnNo.trim();
            if (isbnNo !== "") {
                var url = 'https://openlibrary.org/api/books?bibkeys=ISBN:' + isbnNo + '&jscmd=data&format=json';

                $.get(url)
                    .done(function (data) {
                        var bookData = data['ISBN:' + isbnNo];
                        console.log(bookData);
                        if (bookData != null) {
                            
                        	$("#bookTitle").val(bookData.title);

                            var authors = bookData.authors[0].name;
                            var numberOfPages = bookData.number_of_pages;
                            var publishDate = bookData.publish_date;
                            var publishers = bookData.publishers[0].name;
                            var viewImage = bookData.cover.large;

                            var authorLabel = document.getElementById('authorNameFromISBN');
                            var authorSpan = document.createElement('span');
                            authorSpan.textContent = authors;
                            authorLabel.appendChild(authorSpan);
                            authorSpan.style.color = 'blue';

                            var publisherDateLabel = document.getElementById('publishDateFromISBN');
                            var publisherDateSpan = document.createElement('span');
                            publisherDateSpan.textContent = publishDate;
                            publisherDateLabel.appendChild(publisherDateSpan);
                            publisherDateSpan.style.color = 'blue';

                            var publisherNameLabel = document.getElementById('publisherNameFromISBN');
                            var publisherNameSpan = document.createElement('span');
                            publisherNameSpan.textContent = publishers;
                            publisherNameLabel.appendChild(publisherNameSpan);
                            publisherNameSpan.style.color = 'blue';

                            $("#noOfPage").val(numberOfPages);

                            var selectedImageDiv = document.getElementById("selectedImage");
                            selectedImageDiv.classList.remove("hidden");

//                             var viewImageLink = document.getElementById("viewimage");
//                             viewImageLink.href = viewImage;
                            $("#viewimage").attr("src", viewImage);

                        } else {
                            alert("Book data not present in this ISBNNO: " + isbnNo);
                            hideAjaxLoader();
                        }
                        hideAjaxLoader();
                    })
                    .fail(function () {
                        $('#loader').addClass('hidden');
                        alert('Error fetching book information');
                        hideAjaxLoader();
                    });
            }
        }
    }
    fetchData();
}

function isUniqueISBN(isbnNo) {
    return new Promise(function (resolve, reject) {
        debugger;
        $.get(contextPath + "/core/getInformationByISBN", { id: isbnNo })
            .done(function (data) {
                if (data === true) {
                    resolve(true);
                } else {
                    resolve(false);
                }
            })
            .fail(function () {
                // $('#loader').addClass('hidden');
                alert("Error fetching ISBN information");
                reject();
            });
    });
}



	$(document).on("click", ".img-close", function() {
	  $(this).closest('.popup-gallery').find('.col-lg-2').remove();
	});
	
	
	/* function isUniqueISBN(isbnNo){
		debugger;
		var booleanValue;
		
		$.get(contextPath + "/core/getInformationByISBN", { id: isbnNo})
        .done(function (data) {
            if(data=== true){
            	booleanValue = true;
            }else{
            	booleanValue = false;
            }
            
        })
        .fail(function () {
//             $('#loader').addClass('hidden');
            alert("Error fetching ISBN information");
        });
		
	} */
 
</script>    

<script>

function createBookRows(totalBooks, previousValue) {
	debugger;
    var tableBody = document.getElementById('bookTable').getElementsByTagName('tbody')[0];

    if (!tableBody) {
        console.error("Table body not found!");
        return;
    }

    var bookName = $("#bookTitle").val() || '';
    var newTotalBooks = totalBooks - previousValue;

    if (previousValue !== null && previousValue !== undefined) {
        if (newTotalBooks > 0) {
            for (let i = previousValue + 1; i <= previousValue + newTotalBooks; i++) {
                const newRow = document.createElement('tr');
                newRow.innerHTML = '<td><input type="text" name="bookVOList[' + i + '].bookUkNo" value="' + bookName.toUpperCase() + '-' + i + '" class="form-control" readonly="readonly"/></td><td><select name="bookVOList[' + i + '].rack.rackId" class="form-control" id="rack[' + i + ']" onchange="getShelfByRackId(this.value, \'shelf[' + i + ']\',null, \'rack[' + i + ']\')"><option value="0">-select rack-</option><c:forEach var="rack" items="${rackList}"><option value="${rack.rackId}">${rack.rackName}</option></c:forEach></select></td><td><select name="bookVOList[' + i + '].shelf.shelfId" class="form-control" id="shelf[' + i + ']"><option value="0">-select shelf-</option></select></td><td><button type="button" id="deleteButton[' + i + ']" onclick="deleteRow(this)" >Delete</button></td>';

                tableBody.appendChild(newRow);
            }
        } else if (newTotalBooks < 0 && newTotalBooks < previousValue) {
            for (let i = 0; i <= previousValue; i++) {
                var deleteButton = document.getElementById('deleteButton[' + i + ']');
                if (deleteButton) {
                    deleteButton.removeAttribute('disabled');
                }
            }
        }
    } else {
            while (tableBody.firstChild) {
                tableBody.removeChild(tableBody.firstChild);
            }
        for (let i = 1; i <= totalBooks; i++) {
            const newRow = document.createElement('tr');
            newRow.innerHTML = '<td><input type="text" name="bookVOList[' + i + '].bookUkNo" value="' + bookName.toUpperCase() + '-' + i + '" class="form-control" readonly="readonly"/></td><td><select name="bookVOList[' + i + '].rack.rackId" class="form-control" id="rack[' + i + ']" onchange="getShelfByRackId(this.value, \'shelf[' + i + ']\',null, \'rack[' + i + ']\')"><option value="0">-select rack-</option><c:forEach var="rack" items="${rackList}"><option value="${rack.rackId}">${rack.rackName}</option></c:forEach></select></td><td><select name="bookVOList[' + i + '].shelf.shelfId" class="form-control" id="shelf[' + i + ']"><option value="0">-select shelf-</option></select></td><td><button type="button" id="deleteButton[' + i + ']" onclick="deleteRow(this)" >Delete</button></td>';

            tableBody.appendChild(newRow);

        }
    }
}


</script>

<script>

$(document).ready(function() {
	$('.popup-gallery').magnificPopup({
		delegate: 'a',
		type: 'image',
		tLoading: 'Loading image #%curr%...',
		mainClass: 'mfp-img-mobile',
		gallery: {
			enabled: true,
			navigateByImgClick: true,
			preload: [0,1] // Will preload 0 - before current, and 1 after the current image
		},
		image: {
			tError: '<a href="%url%">The image #%curr%</a> could not be loaded.',
			titleSrc: function(item) {
				return item.el.attr('title');
			}
		}
	});

	
});

jQuery(document).ready(function () {
	  ImgUpload();
	});

	function ImgUpload() {
	  var imgWrap = "";
	  var imgArray = [];

	  $('.upload__inputfile').each(function () {
	    $(this).on('change', function (e) {
	      imgWrap = $(this).closest('.file_img').find('.file_img_wrap');
	      var maxLength = $(this).attr('data-max_length');

	      var files = e.target.files;
	      var filesArr = Array.prototype.slice.call(files);
	      var iterator = 0;
	      filesArr.forEach(function (f, index) {

	        if (!f.type.match('image.*')) {
	          return;
	        }

	        if (imgArray.length > maxLength) {
	          return false
	        } else {
	          var len = 0;
	          for (var i = 0; i < imgArray.length; i++) {
	            if (imgArray[i] !== undefined) {
	              len++;
	            }
	          }
	          if (len > maxLength) {
	            return false;
	          } else {
	            imgArray.push(f);

	            var reader = new FileReader();
	            reader.onload = function (e) {
	              var html = "<div class='upload__img-box'><div style='background-image: url(" + e.target.result + ")' data-number='" + $(".upload__img-close").length + "' data-file='" + f.name + "' class='img-bg'><div class='upload__img-close'></div></div></div>";
	              imgWrap.append(html);
	              iterator++;
	            }
	            reader.readAsDataURL(f);
	          }
	        }
	      });
	    });
	  });

	  $('body').on('click', ".upload__img-close", function (e) {
	    var file = $(this).parent().data("file");
	    for (var i = 0; i < imgArray.length; i++) {
	      if (imgArray[i].name === file) {
	        imgArray.splice(i, 1);
	        break;
	      }
	    }
	    $(this).parent().parent().remove();
	  });
	}
	
</script>

