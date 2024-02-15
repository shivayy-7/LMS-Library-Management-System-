<script src="${contextPath}/assets/vendor/bootbox5/bootbox.js"></script>
<script src="${contextPath}/assets/vendor/bootbox/bootbox.min.js"></script>
 <%--  <div class="modal" id="myModal">
  <div class="modal-dialog signup_modal">
    <div class="modal-content">
      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title"> Tenant Login </h4>
      </div>
      <!-- Modal body -->
      <div class="modal-body">
      <div class="col-md-12">
        <div class="form-group" id="unm">
          <label class="col-sm-12" for="email">Mobile Number:</label>
          <div class="col-sm-12">
            <input placeholder="Enter Registered Mobile Number" class="form-control plc NumbersOnly" maxlength="10"
              name="userName" id="otpUserName" type="text" autocomplete="off">
          </div>
        </div>
        <div class="form-group">
        <div class="col-sm-12">
        <div class="text-center">
          <button type="button" class="btn btn-success " id="btn_otp" onclick="requesetForOTP('CREATE');">Requeset For OTP</button>
        </div>
        </div>
        </div>
      </div>
    <div class="col-md-12 pt-2" style="text-align:center;">
          <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal">Close</button>
        </div>
    </div>
        
    </div>
  </div>
</div>
<div class="modal" id="otpModal">
  <div class="modal-dialog">
    <div class="modal-content" style="margin-top: 18%;margin-left: -6%;">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Enter your six-digit OTP:</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
      	<form class="form-horizontal" method="POST" action="${contextPath}/public/umt/login" autocomplete="off" method="POST" id="otpForm">
      	   <input type="hidden" name="${_csrf.parameterName}"value="${_csrf.token}" /> 
	       <input type="hidden" name="appCode" value="EUTP" />
                             <div class="form-group text-center mb-2">
                               <div class="col-sm-12"> <span>A code has been sent to</span> <small>*******9897</small> </div>
                                <div class="col-sm-12" id="otp">
                                    <input class="digit text-center otpCheck" type="text" required id="firstdigit" size="1" maxlength="1" tabindex="0">
                                    <input  class="digit text-center otpCheck" type="text" required id="snddigit" size="1" maxlength="1" tabindex="1">
                                    <input class="digit text-center otpCheck" type="text" required id="thirddigit" size="1" maxlength="1"  tabindex="2" >
                                    <input  class="digit text-center otpCheck" type="text" required id="fourthdigit" size="1" maxlength="1" tabindex="3">
                                    <input  class="digit text-center otpCheck" type="text" required id="fifthdigit" size="1" maxlength="1" tabindex="4">
                                    <input  class="digit text-center otpCheck" type="text" required id="sixthdigit" size="1" maxlength="1" tabindex="5">
                                </div>
                                <div class="col-sm-12" id="timerDiv">Left Time : <span id="timer"></span></div>
                                <div class="col-sm-12 mt-1"><button type="button" class="btn btn-primary btn-xs" title="Resend" id="repeatBtn" onclick="requesetForOTP('CREATE');"><i class="fa fa-repeat"></i></button></div>
                            </div>
                             <input type="hidden" name="userName" id="hdnUserName">
                             <input type="hidden" name="password" id="hdnPassword">
                            <div class="form-group text-center">
                                <div class="col-sm-12 mt-2 text-center">
                                    <button type="button" onclick="verifyOTPWithMobileNo();" id="otpFunCall"  class="btn btn-success btn-sm digit" tabindex="3">Confirm</button>
                                     <button type="button" class="btn btn-danger btn-sm closeme" id="" data-dismiss="modal">Close</button>
                                </div>
                            </div>
           </form>
      </div>

    </div>
  </div>
</div>
<!-- <h4 class="footertext">Planning & Convergence Department, Govt. of Odisha</h4> -->
<form action="${contextPath}/public/getPayment/list" id="pymntformId" method="get">
</form>
<script type="text/javascript">
$('.otpCheck').on('input',function() {
    var allvals = $('.otpCheck').map(function() { 
        return this.value; 
    }).get().join('');
    $('#hdnPassword').val(allvals);
  });

  function verifyOTPWithMobileNo(){
	var mobileNo = $("#otpUserName").val();  
	var mobileOtp =  $('#hdnPassword').val();
	  if (mobileOtp.length == 6) {
			$.ajax({
				type : "GET",
				url : "${contextPath}/public/verifyOTPWithMobileNo",
				data : {
					"mobileNo" : mobileNo,
					"mobileOtp" : mobileOtp,
				},
			success : function(response) {
				var val = JSON.parse(response);
				if (val.verifyOTP == true) {
					$("#otpForm").submit();
					return false;
				} else {
					//bootbox.alert("");
					bootbox.alert("Failed to verify incoming OTP.", function(){
						$(".otpCheck").val("");
					})
					
					return true;
				}
			},
			error : function(error) {
				//bootbox.alert("Failure"); 
			}
		 });
		}else{
			bootbox.alert("Please enter six digits otp.");
			return true;
		}
  }
</script>

<script>

function requesetForOTP(otpType) {
	var mobileNo = $("#otpUserName").val();
	$("#hdnUserName").val(mobileNo)
		if (mobileNo != "") {
			$.ajax({
				type : "GET",
				url : "${contextPath}/public/requesetForOTP",
				data : {
					"mobileNo" : mobileNo,
					"otpType" : otpType,
				},
			success : function(response) {
				var val = JSON.parse(response);
				if (val.mobileNoIsAvail == true) {
					$("#myModal").modal("hide");
					$("#otpModal").modal("show");
					timer(180);
					$(".otpCheck").val("");
					return false;
				} else {
					bootbox.alert("Please enter your registered mobile no.");
					return true;
				}
			},
			error : function(error) {
				//bootbox.alert("Failure"); 
			}
		 });
		}else{
			bootbox.alert("Please enter your mobile no.");
			return true;
		}
	}
let timerOn = true;
function timer(remaining) {
  $("#repeatBtn").hide();
  $("#timerDiv").show();
  var m = Math.floor(remaining / 60);
  var s = remaining % 60;
  
  m = m < 10 ? '0' + m : m;
  s = s < 10 ? '0' + s : s;
  document.getElementById('timer').innerHTML = m + ':' + s;
  remaining -= 1;
  
  if(remaining >= 0 && timerOn) {
    setTimeout(function() {
        timer(remaining);
    }, 1000);
    return;
  }

  if(!timerOn) {
    // Do validate stuff here
    return;
  }
  removeRequesetOTP('REMOVE');
  
}
  function removeRequesetOTP(otpType){
	  var mobileNo = $("#otpUserName").val();
		if (mobileNo != "") {
			$.ajax({
				type : "GET",
				url : "${contextPath}/public/requesetForOTP",
				data : {
					"mobileNo" : mobileNo,
					"otpType" : otpType,
				},
			success : function(response) {
				var val = JSON.parse(response);
				if (val.mobileNoIsAvail == true) {
					 $("#repeatBtn").show();
					 $("#timerDiv").hide();
					return false;
				} else {
					bootbox.alert("Please enter your registered mobile no.");
					return true;
				}
			},
			error : function(error) {
				//bootbox.alert("Failure"); 
			}
		 });
		}else{
			bootbox.alert("Please enter your mobile no.");
			return true;
		}  
  }
</script>  */ --%>