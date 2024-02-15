
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="userDetails" value="${serviceOutcome.data}" />
<style>
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
<script type="text/javascript"
	src="${contextPath}/assets/appJs/validation/common-utils.js"></script>
	<link rel="stylesheet" href="${contextPath}/assets/js/plugin/jquery_autocomplete/jquery.autocomplete.css" />
		<div class="container-fluid" style="margin-top: -100px; position: relative; z-index:99">
	<%@ include file="/WEB-INF/views/message.jsp"%>
	<%@ include file="/WEB-INF/views/ajaxLoader.jsp"%>
                <div class="row inner-cardbox">
                    <h6>Book Issue</h6>
                    <hr>
                   <form action="${contextPath}/mst/save-member" class="row" id="submitForm" method="POST" enctype="multipart/form-data">
                   
					<div class="col-md-6">
               		<div class="row">
            
               		<div class="col-md-3">
                        <div class="">
                           
                            <div class="circle upload-button">
                           
                               <img class="profile-pic" src="../assets/image/avattar.png" style="width:100px; height:100px!important;">
                               </div>
                            </div>
                        </div>
                     <div class="col-md-9">
               				<div class="col-md-12">
                             <div class="form-group mb-1">
                                   <input type="text" class="form-control form-control-sm" oninput="this.value = this.value.toUpperCase();"   name="aaprovalNo" id="aaprovalNo" maxlength="14" placeholder="Member Id"   >
                                   
                             </div>                                                  
                              </div>
                              <div class="col-md-12">
                             <div class="form-group mb-1">
                                   <input type="text" class="form-control form-control-sm" id="memberName" maxlength="10" placeholder="Name"   readonly>
                                   
                             </div>                                                  
                              </div>
                                 <div class="col-md-12">
                             <div class="form-group mb-1">
                                   <input type="text" class="form-control form-control-sm" oninput="this.value = this.value.toUpperCase();" readonly   id="email" maxlength="10" placeholder="Email"   >
                                   
                             </div>                                                  
                              </div>
                                 <div class="col-md-12">
                             <div class="form-group mb-1">
                                   <input type="text" class="form-control form-control-sm" oninput="this.value = this.value.toUpperCase();" readonly id="address" maxlength="10" placeholder="Address"   >
                                   
                             </div>                                                  
                              </div>
                                     <div class="col-md-12">
                             <div class="form-group mb-1">
                                   <input type="text" class="form-control form-control-sm" oninput="this.value = this.value.toUpperCase();"  readonly id="phoeno" maxlength="10" placeholder="phone"   >
                                   
                             </div>                                                  
                              </div>
                              
                              <div class="row" style="border:#ddd 1px solid; padding:20px 0;   margin-left: 1px; margin-right: 1px;">
                        <div class="form-group col-md-6 ">
                                    <label class="required">Issue Date</label>
                                    <div class="datepicker_con_nextdisable" style="postion:relative;" >
                                    <input type="text" class="form-control"
											id="issueDateId" name="issueDate" style="pointer-events: none;"
											value="<fmt:formatDate pattern="dd/MM/yyyy" value="${mamberData.dob}" />" readonly="readonly">
											</div>
                                </div>
                      <div class="col-md-6">
						<div class="form-group">
						<label class="col-md-12 smallInput required" for="">
						Return Date:</label>
						<div class="col-md-12 datepicker_con_disabled"  style="position:relative;">
						<input type="text" class="form-control form-control-sm "
							id="valiDate" name="valiDate" style="pointer-events: none;" readonly="readonly">
					</div>
				</div>
			</div>
			
<!--                         <div class="form-group col-md-5"> -->
<!--                                     <label>Remark</label> -->
<!--                                     <div class="datepicker_con_add" style="postion:relative;" > -->
<!--                                     <textarea type="text" class="form-control"></textarea> -->
<!-- 											</div> -->
<!--                                 </div> -->
             
                               
			    	
						 
			<div class="col-md-12 mt-2">
                     <button type="button" class="btn btn-primary pp" onclick="saveIssuingDetails();" value="Submit" style="height: 30px;font-size:14px">Issue Book</button>
                       </div>
                               
			</div>
                   </div>
            </div>
        
            
        </div>
            
                              
                 <div class="col-md-6" style="margin-top:-7px;">
                 <div class="col-md-12 ">
                    	<div class="form-group col-md-12">
               
                 <input type="text" class="form-control form-control-sm" oninput="this.value = this.value.toUpperCase();"   name="books" id="books" maxlength="10" placeholder="Search Book"  >
                  </div>
				<div class="table-responsive mt-3">
				
					<table  id="bookTable" class="table table-bordered exportbtn" style="margin-top: 7px;">
						<thead>
							<tr>
								<th>Book ID</th>
								<th>Rack</th>
								<th>Shelf</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
				
			</div>
                     
                        </div>
                        	
              

			
				<!--<div class="col-lg-4">
						<div class="id-card-holder">
							<div class="id-card">
								<div class="photo">
									<img class="profile-pic"
										src="${contextPath}/image/viewimage?filePath=${manamnetData.member.imagePath}">
								</div>
								<h2 id="memberName"></h2>
								<h3 id="approveNo"></h3>
								<p style="font-size: 10px;">
									<strong>Valid:</strong> <span id="issueDate"></span> - <span
										id="validDate"></span>
								</p>
								<hr>
							</div>
						</div>
					</div>-->
				




				
                         
                         	
                          
				
			         
                       
                    </form>
                    
                </div>

            </div>
            
<%--      <form action = "${contextPath}/member-deatils" method="get" id="filterForm"> --%>
<!-- 	<input type="hidden" name="sentapprovalNo" id="sentapprovalNo" value=""> -->
<!-- </form>	 -->

            
<script>
$(document).ready(function() {    
      $("#aaprovalNo").autocomplete({
          serviceUrl : '${contextPath}/findbyapprovalno.htm',
	        paramName : "aaprovalNo",
		    delimiter : ",",
		    width : '195px',
		    async : false,
		    transformResult : function(response) {
		    	 if($.parseJSON(response).length == 0){
		    		 $("#hidden").removeClass("hidden");
		    		 resetValue();
		    		 }
				     return {				    	
						suggestions : $.map($.parseJSON(response),function(item) {
						return {	
							value : item.aaprovalNo
							};													
							})
		            };
			},
		onSelect : function(suggestion) {
// 						$("#sentapprovalNo").val(suggestion.value);
// 						$("#filterForm").submit();
			membeDetils(suggestion.value);
					}
	});

      $("#books").autocomplete({
    	    serviceUrl: '${contextPath}/findbybookname.htm',
    	    paramName: "books",
    	    delimiter: ",",
    	    width: '195px',
    	    async: false,
    	    transformResult: function(response) {
    	        if ($.parseJSON(response).length == 0) {
    	            $("#hidden").removeClass("hidden");
    	            resetValue();
    	        }
    	        return {
    	            suggestions: $.map($.parseJSON(response), function(item) {
    	                return {
    	                    value: item.books,
    	                    code: item.bookcode,
    	                    image: item.image
    	                };
    	            })
    	        };
    	    },
    	    onSelect: function(suggestion) {
    	    	rackshelf(suggestion.code);
    	    }
    	}).data("ui-autocomplete")._renderItem = function(ul, item) {
    	    return $("<li>")
    	        .append("<div><img src='data:image/jpeg;base64," + item.image + "' style='width: 50px; height: 50px;' /><span>" + item.value + "</span></div>")
    	        .appendTo(ul);
    	};



}); 



function membeDetils(approvalNo){
	
	$.ajax({
		type : "GET",
		url : '${contextPath}/member-deatils-data',
		dataType : "json",
		data : {
			"approvalNo" : approvalNo,
		},
		success : function(response) {
			var data = response;
			if (data != "" || data != null) {
				 // Assuming 'memberamnetData' contains the received data
                $(".profile-pic").attr("src", '${contextPath}/image/viewimage?filePath=' + data.imagePath);
                $("#memberName").val(data.meberName);
                $("#approveNo").text(data.approvalNo);
                var issueDateFormatted = formatDate(data.issueDate);
                var validDateFormatted = formatDate(data.validDate);

                // Update the HTML elements
                $("#issueDate").text(issueDateFormatted);
                $("#validDate").text(validDateFormatted);
                $("#email").val(data.email);

                $("#address").val(data.address);

                $("#phoeno").val(data.phoneno);

			}
		},
		error : function(error) {
			//bootbox.alert("No Branch Found");
		}
	});
	
	
}


function rackshelf(code){
	
	$.ajax({
		type : "GET",
		url : '${contextPath}/book-deatils',
		dataType : "json",
		data : {
			"code" : code,
		},
		success : function(response) {
			var data = response;
			var tableBody = document.getElementById('bookTable').getElementsByTagName('tbody')[0];
			 if (data.length > 0) {
			        for (var i = 0; i < data.length; i++) {
			            var bookName = data[i].bookcode; // Assuming bookcode is the book name
                        var racId = data[i].rakId;
                        var slfId = data[i].slfId;
                        var slfName = data[i].slfName;
                        var rackName = data[i].rackName;
                        const newRow = document.createElement('tr');
                        newRow.innerHTML = '<td><input type="text" name="bookVOList[' + i + '].bookUkNo" value="' + bookName.toUpperCase() + '" class="form-control" readonly="readonly"/></td>' +
                            '<td><input type="hidden" name="bookVOList[' + i + '].racId" value="' + racId + '" class="form-control"/><input type="text" name="bookVOList[' + i + '].rackName" value="' + rackName + '" class="form-control" readonly="readonly"/></td>' +
                            '<td><input type="hidden" name="bookVOList[' + i + '].slfId" value="' + slfId + '" class="form-control"/><input type="text" name="bookVOList[' + i + '].slfName" value="' + slfName + '" class="form-control" readonly="readonly"/></td>' +
                            '<td><input type="checkbox"  name="bookVOList[' + i + '].bookId" id="myCheckbox[' + i + ']" value="true" class="" onchange="checkAvalability(this.value,${count.count})"/></td>'; // Added checkbox here
                        tableBody.appendChild(newRow);
			        }
			    }
			 else{
				 bootbox.alert({
                     message:"BOOK IS NOT AVAILABLE",
                     callback: function () {
                     }
                 });
			 }
		},
		error : function(error) {
			//bootbox.alert("No Branch Found");
		}
	});
}

function formatDate(dateString) {
    var options = { year: 'numeric', month: 'long', day: 'numeric' };
    return new Date(dateString).toLocaleDateString(undefined, options);
}
function resetValue()
{
	debugger
$('#dynamic-form-fields' ).find( 'input','select' ).each(function(){
	id = $( this ).attr( 'id' );
    $("#"+id+"").val('');
});
	
$('#dynamic-form-fields' ).find('select').each(function(){
	$(this).find('option').prop('selected', false);
	$(this).trigger('change');
});
}

</script>

<script>

/*http://laravel.io/forum/02-08-2014-ajax-autocomplete-input*/
$(function() {
    $('.datepicker_con1 > input').datepick({
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
});


function checkAvalability(id, count) {
    if ($('#myCheckbox\\[' + count + '\\]').is(':checked')) {
        $("#pcProfileIdHdn" + count).val(id);
    } else {
        $("#pcProfileIdHdn" + count).val(""); // Make sure to use the correct ID here
    }
}


function saveIssuingDetails() {
	debugger;
    var approvalNo = $("#aaprovalNo").val();
    var issueDate = $("#issueDateId").val();
    var returnDate = $("#valiDate").val();
    
    if(approvalNo==''){
   	 bootbox.alert("Please select MemberId.");
   	 return false;
   }
    if(issueDate==''){
    	 bootbox.alert("Please select Issue Date.");
    	 return false;
    }
    if(returnDate==''){
   	 bootbox.alert("Please select Return Date.");
   	 return false;
   }
    
	    var maxReserveDay = "${maxReserveDay.valueEn}";

// 	    var parsedReturnDate = new Date(returnDate);
	    
	    var from = returnDate.split("/")
	    var parsedReturnDate = new Date(from[2], from[1] - 1, from[0])
	    
// 	    var parsedIssueDate = new Date(issueDate);
	    var from2 = issueDate.split("/")
	    var parsedIssueDate = new Date(from2[2], from2[1] - 1, from2[0])
	    var daysDifference = (parsedReturnDate - parsedIssueDate) / (24 * 60 * 60 * 1000);

	    if (daysDifference <= maxReserveDay) {
	        
	    	var bookDetails = [];

	        $("#bookTable tbody tr").each(function() {
	            var isChecked = $(this).find('input[type="checkbox"]').is(':checked');
	            if (isChecked) {
	                var bookId = $(this).find('input[name^="bookVOList"]').val();
	                var rackId = $(this).find('select[name^="bookVOList"]').val();
	                var shelfId = $(this).find('select[name^="bookVOList"]').val();

	                bookDetails.push({
	                    bookId: bookId,
	                    rackId: rackId,
	                    shelfId: shelfId
	                });
	            }
	        });

	        var data = {
	            approvalNo: approvalNo,
	            issueDate: issueDate,
	            returnDate: returnDate,
	            bookDetails: bookDetails
	        };

	        // Add CSRF token as a header
	        var csrfToken = '${_csrf.token}';
	        showAjaxLoader();
	        // Send data only if at least one checkbox is checked
	        if (bookDetails.length > 0) {
	            $.ajax({
	                type: "POST",
	                url: "${contextPath}/save-issuedetails",
	                contentType: "application/json; charset=utf-8",
	                dataType: "json",
	                headers: {
	                    '${_csrf.headerName}': csrfToken
	                },
	                data: JSON.stringify(data),
	                success: function(response) {
	                	 hideAjaxLoader();
	                    if (response.outcome == true) {
	                        bootbox.alert({
	                            message: response.message,
	                            callback: function () {
	                                // Handle success if needed
	                            }
	                        });
	                    } else {
	                        bootbox.alert({
	                            message: response.message,
	                            callback: function () {
	                                // Handle failure if needed
	                            }
	                        });
	                    }
	                	location.reload();
	                },
	                error: function(error) {
	                	 hideAjaxLoader();
	                    bootbox.alert({
	                        message: "An error occurred. Please try again.",
	                        callback: function () {
	                            // Handle error if needed
	                        }
	                    });
	                	location.reload();
	                }
	            });
	        } else {
	        	 hideAjaxLoader();
	            bootbox.alert("Please select at least one book to issue.");
	        }
	    	
	    	
	    } else {
	        alert("Reserved Date Should Be within 15 days");
	    }
    
    
}


</script>




 







