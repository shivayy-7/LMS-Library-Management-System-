<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<head>
   <meta charset="UTF-8">
    <title>Library Management System</title>
    <meta name="keywords" content="Library Management System" />
    <meta name="description" content="Library Management System">
    <meta name="author" content="Aashdit Technologies">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
  <%--   <meta http-equiv="Content-Security-Policy" content="<directive>;<directive>;<directive>; ..."> --%>
    <link rel="shortcut icon" href="${contextPath}/assetsLogin/images/logo1.png">
    <link rel="stylesheet" href="${contextPath}/assetsLogin/css/login.css">
    <link rel="stylesheet" href="${contextPath}/assetsLogin/css/bootstrap.min.css">
     <link href="https://fonts.googleapis.com/css2?family=Secular+One&display=swap" rel="stylesheet">
     <link rel="stylesheet" href="${contextPath}/assets/css/fonts.min.css">
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.1.0/css/v4-shims.min.css">
    
    
  <style>
	.carousel-inner>.item>img, .carousel-inner>.item>a>img {
	display: block;
	height: auto;
	max-width: 100%;
	line-height: 1;
	width: 100%; // Add this
	}
	.copyright-sec {
position: fixed;
    bottom: 0;
    left: 0;
    width: 100%;
    z-index: 9;
    background: #1f1d1d;
    padding: 8px 0;
    color: #fff;
    text-align: center;
	}
	.copyright-sec p {
	    margin-bottom: 0;
    font-size: 12px;
	}
	.copyright-sec p a {
		color: #f53400;
	}
	.copyright-sec p:hover a {
	    text-decoration: none;
	}
	.copyright-sec p {
	    display: flex;
	    flex-wrap: wrap;
	    justify-content: space-between;
	}
     </style>
     <style>
        .container-wa .floating-button {
            position: fixed;
            bottom: 10px;
            left: 12px;
            width: 60px;
            height: 60px;
            background-color: #fa592d;
            color: white;
            border-radius: 50px;
            text-align: center;
            font-size: 35px;
            cursor: pointer;
            box-shadow: 0px 2px 5px #666;
            z-index: 1000;
        }

        .container-wa .floating-button:hover {
            background-color: #1fad53;
        }

        .container-wa .floating-button .icon {
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            line-height: 60px;
            text-align: center;
            transition: all 0.3s;
        }

        .container-wa .floating-button .icon.wa {
            animation: wa-out 0.3s;
        }

        .container-wa .floating-button .icon.close {
            opacity: 0;
            margin: auto;
            transform: rotateZ(-70deg);
            animation: close-out 0.3s;
        }

        .container-wa .card {
            font-family: 'Quicksand', sans-serif;
            position: absolute;
            width: 280px;
            height: auto;
            background-color: white;
            border-radius: 5px;
            left: 40px;
            bottom: 88px;
            display: none;
            border: 1px solid rgba(0, 0, 0, 0.15);
            z-index: 10000;
        }

        .container-wa .card .banner-header {
            background-image: url("../../investio.com.br/images/banner/bg-chat-whatsapp.jpg");
            background-size: cover;
            color: white;
            padding: 20px;
            padding-bottom: 40px;
            background-color: #075e54;
        }

        .container-wa .card .banner-body {
            flex-grow: 1;
            background-color: #eaeaea;
            padding: 15px;
        }

        .container-wa .card .banner-body .card-atendente {
            margin-top: -50px;
            background-color: white;
            padding: 15px;
            display: flex;
            flex-direction: column;
            border-radius: 15px;
        }

        .container-wa .card .banner-body .card-atendente .dados-atendente {
            display: flex;
        }

        .container-wa .card .banner-body .card-atendente .dados-atendente .avatar {
            padding: 15px;
        }

        .container-wa .card .banner-body .card-atendente .dados-atendente .avatar img {
            border-radius: 50%;
        }

        .container-wa .card .banner-body .card-atendente .dados-atendente .informacoes {
            padding: 15px;
        }

        .container-wa .card .banner-body .card-atendente .botao-atendente {
            display: flex;
        }

        .container-wa .card .banner-body .card-atendente .botao-atendente .btn {
            color: white;
            background-color: #d3b924;
            border-radius: 15px;
            width: 100%;
            padding: 10px;
            text-align: center;
            text-decoration: none;
        }

        .container-wa.is-opened .icon.wa {
            animation: wa-in 0.15s linear;
            animation-fill-mode: forwards;
        }

        .container-wa.is-opened .icon.close {
            animation: close-in 0.2s;
            animation-delay: 0.1s;
            animation-fill-mode: forwards;
        }

        .container-wa.is-opened .card {
            display: flex;
            flex-direction: column;
            position: fixed;
        }

        .container-wa.is-opened .icon.wa {
            animation: wa-in 0.15s linear;
            animation-fill-mode: forwards;
        }

        .container-wa.is-opened .icon.close {
            animation: close-in 0.2s;
            animation-delay: 0.1s;
            animation-fill-mode: forwards;
        }

        @keyframes close-in {
            from {
                opacity: 0;
                transform: rotateZ(-70deg);
            }

            to {
                opacity: 1;
                transform: rotateZ(0deg);
            }
        }

        @keyframes close-out {
            from {
                opacity: 1;
                transform: rotateZ(0deg);
            }

            to {
                opacity: 0;
                transform: rotateZ(-70deg);
            }
        }

        @keyframes wa-in {
            from {
                opacity: 1;
                transform: rotateZ(0deg);
            }

            to {
                opacity: 0;
                transform: rotateZ(180deg);
            }
        }

        @keyframes wa-out {
            from {
                opacity: 0;
                transform: rotateZ(180deg);
            }

            to {
                opacity: 1;
                transform: rotateZ(0deg);
            }
        }

        .card ul {
            padding: 0 15px;
            margin: 0;
            text-align: left;
        }

        .card ul li {
            color: rgb(0, 0, 0);
            text-decoration: none;
            list-style: none;
            padding: 10px 0;
            border-bottom: 1px solid rgba(145, 143, 143, 0.336);
        }
        .card ul li:nth-child(1){
        	text-align: left;
        }
        
            

        .container-wa .card .card-head p {
            text-align: center;
            text-decoration: underline;
            font-size: 20px;
            font-weight: 600;
            color:#000;
        }
        .container-wa .card .card-head {
        display: flex;
    align-items: center;
    justify-content: center;
    }
        
       
    </style>
</head>
<body>
  <div id="demo" class="carousel slide" data-ride="carousel" data-interval="5000" style="position: fixed;">

  
  <!-- The slideshow -->
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="${contextPath}/assetsLogin/images/banner1.jpg" alt="LMS Image" class="img-fluid" >
    </div>
    <div class="carousel-item">
      <img src="${contextPath}/assetsLogin/images/banner2.jpg" alt="LMS Image" class="img-fluid" >
    </div>
    <div class="carousel-item">
      <img src="${contextPath}/assetsLogin/images/banner3.jpg" alt="LMS Image" class="img-fluid" >
    </div>
  </div>
  
</div>
    <div class="loginBox">
          <div class="login_con">
              <div class="upSec">
               <div class="upBox">
                  <img src="${contextPath}/assetsLogin/images/logo1.png" style="border-right: 1px solid #b9b9b9;">
                 <%--  <img src="${contextPath}/assetsLogin/images/bmc.png" style="margin-left: 5px;"> --%>
                </div> 
                <h2 style="color: #d4c915;">  Library Management System </h2>
              </div>
              <h3 class="login_lbl">  Login Here</h3>
              <div class="districtcon">
				<c:if test="${not empty success_msg}">
					<div class="alert alert-danger"
						style="width: 50%; margin-left: 17%; text-align: center;"
						id="errorMsg">${err_msg}</div>
				</c:if>
				<c:if test="${not empty err_msg}">
					<div class="alert alert-danger"
						style="width: 50%; margin-left: 17%; text-align: center;"
						id="errorMsg">${err_msg}</div>
				</c:if>
				<form class="form-horizontal" id="login-window" method="POST"
						action="${contextPath}/overwrite/umt/login" autocomplete="off"  onsubmit="submitUserForm()">
						<input type="hidden" name="" value="">
						<input type="hidden" name="loginType" id="loginType">
						<div class="fieldBox">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
								<input type="hidden" name="appCode" value="EUTP" />
							<input type="hidden" name="loginType" value="OTP" />
							<div class="form-group" id="unm">
								<label class="col-sm-12" for="email">Username:</label>
								<div class="col-sm-12">
									<input placeholder="Enter username" class="form-control plc"
										 id="username" type="text" autocomplete="false"
										required="required">
									<input type="hidden" id="textUserName" name="userName" value="">		
								</div>
							</div>
							<div class="form-group" id="pas">
								<label class="col-sm-12" for="pwd">Password:</label>
								<div class="col-sm-12">
									<input placeholder="********" class="form-control plc"
										 id="textPassword" type="password"
										autocomplete="false" required="required">
									<input type="hidden" id="password" name="password" value="">		
								</div>
							</div>
						</div>
						<div class="form-group captcha_con mt-3"
							style="width: 100%; display: inline-flex; margin-left: 0.5%;">
							<div class="col-sm-5 captchabox p-0" style="width: 45%">
								<input name="captcha" type="text" placeholder="Enter Captcha"
									required maxlength="5" id="captcha" class="form-control plc"
									autocomplete="off">
							</div>
							<div class="col-sm-2 captchaImg" style="width: 20%">
								<%-- <img id="Image1" onclick="refreshCaptcha();" src="${contextPath}/assetsLogin/images/refresh.png"> --%>
							</div>
							<!-- causesvalidation="false" -->
							<div class="col-sm-5 captcha" onmousedown="return false"
								style="width: 40%; height: 34px;">
								<img style="height: 38px;" src="${contextPath}/captcha/5" />
							</div>
						</div>
						
						<div class="form-footer col-sm-12">
							<button type="submit" class="btn btn-success " id="btn_submit">Login</button>
						</div>
						
						<div class="text-center">

							 <label style="float: left; padding-top: 15px; font-weight: 500;"
								id="">
<!-- 								<a href="#" style="color: #000;"> Terms & Conditions</a> -->
 								<a href="${contextPath}/public/termsConditions" style="color: #000;" target="_blank"> Terms & Conditions</a>								
								</label>
								
							  <label style="float: right; padding-top: 15px; font-weight: 500;"
								id=""><a href="${contextPath}/public/forgot/password" style="color: #000;" target="_blank"> Forgot Password</a></label> 
						</div>
                         <div class="btm-footer">

						<label
							style="display: block; text-align: center; font-weight: bold; padding-top: 15px;">
							<a href="${contextPath}/mst/add-member"
							style="color: #4248f5; text-decoration: none;" target="_blank">
								Apply For Member </a>
						</label>

						<!-- <label style="color:#fff">
                                 <a href="#"> Sign Up</a></label> -->
                              <!--   <label class="mt-1 col-md-12">
                                  <i style="color: #3971bf;font-size: 20px;" class="far fa-hand-point-right"></i> 
                                  <span><a href="#" data-toggle="modal" data-target="#myModal">Tenant Login</a></span></label> -->
                          </div>
					</form>
            </div>
          </div> 
        </div>
        <section class="copyright-sec">
	        <div class="container">
	        	<div class="row">
	        		<div class="col-lg-12">
	        			<p><span>Powered By <a href="https://www.axisbank.com/" target="_blank">AXIS Bank</a></span> 
	        			<span> <a href="${contextPath}/public/lodgeGrievence" target="_blank">LODGE GRIEVENCE</a></span> 
	        			 <span> Developed By <a href="https://www.aashdit.com/" target="_blank">AASHDIT Technologies</a></span>
	        			 </p>
	        		</div>
	        		
	        		<!-- POPUP  -->
	        		    <div class="container-wa">
        <div class="card">

            <div style="">
                <div class="card-head">
                    <p style="color:#000">Contact US</p>
                </div>
                <ul>
                    <li>
                       Shri Jagannath Sanskrit University, 
                       Shrivihar, Puri, Odisha, India, Pin-752003
                    </li>
                    <li>
                        <span>Est</span> - <b> 07 june 1981.</b>
                    </li>
                    <li>
                        <b>
                           <i class="fa fa-phone-square style="color:#3481f6"></i>
                            1800-3456-796
                        </b>
                    </li>

                </ul>
                <div style="text-align: center; font-size: 1.5em;letter-spacing: 10px;">
                    <a href="#"  rel="noopner">
                   		 <i style="color:#18d45c"  class="fa fa-whatsapp"></i>
                    </a>
                    <a href="#"  rel="noopner"><i style="color:#3481f6"
                            class="fa fa-facebook"></i></a>

                    <a href="#"  rel="noopner"><i style="color:red"
                            class="fa fa-envelope"></i></a>

                    <a href="#" rel="noopner"><i style="color:blue"
                            class="fa fa-instagram"></i></a>
                </div>



            </div>
        </div>
        <div class="floating-button">
            <i class="fa fa-comments icon wa"></i>
            <i style="color:#fff;" class="fa fa-times icon close"></i>

        </div>
    </div>
	        		<!--ENd POPUP  -->
	        	</div>
	        </div>
        </section>
	<section class="login_content"></section>
	<script src="${contextPath}/assetsLogin/js/jquery-3.5.1.min.js"></script>
	<script src="${contextPath}/assetsLogin/js/bootstrap.min.js"></script>
	<%-- <%@ include file="/WEB-INF/views/tenantLogin.jsp"%>   --%> 
	  <script src="${pageContext.request.contextPath}/assets/applicationSpecific/encrypt/AesUtil.js"></script>
    <script src="${pageContext.request.contextPath}/assets/applicationSpecific/encrypt/aes.js"></script>
    <script src="${pageContext.request.contextPath}/assets/applicationSpecific/encrypt/pbkdf2.js"></script>
    <script src="${pageContext.request.contextPath}/assets/applicationSpecific/encrypt/lbase.js"></script>    
      
     <script type="text/javascript">
     
      $(".floating-button").click(function () {
         $(this).closest('.container-wa').toggleClass('is-opened');
     }) 
 </script>
 
   
<script type="text/javascript">
 	var err = '${err_msg}';
 	if(err !=""){
 		setTimeout(function() {
 		    $('#errorMsg').fadeOut('slow');
 		}, 3000);
 	}
 	
 function verifyOTP(){
	 $("#pymntformId").submit();
 }
 
 function submitUserForm() { 
  	var username = $("#username").val();
  	var password = $("#textPassword").val();
  	var generateCaptcha=$("#captcha").val();
  	if (username == "") {
  		bootbox.alert("Username Can't Be Empty");
  		return false;
  	}
  	if (password == "") {
  		bootbox.alert("Password Can't Be Empty");
  		return false;
  	}
  	if (generateCaptcha == "") {
	    bootbox.alert("Please enter the CAPTCHA"); 
	    return false;
	}
  	/* var inputCaptcha=$("#txtCaptcha").val();
	    if (inputCaptcha != "") {
		    if(inputCaptcha.localeCompare(generateCaptcha)) {
		    	bootbox.alert("Captcha Did Not Match");
				$("#txtCaptcha").val(""); 
				refreshCaptcha(); 
				return false;
			}
	    }
	    else {
	    	event.preventDefault(); 
	    	bootbox.alert("Please Complete The reCAPTCHA Step");  
	    	return false; 
	    } */
	    /* if (inputCaptcha != "") {
  		$('#txtCaptcha').val(enc_inputCaptcha(inputCaptcha)); 
	    	$('#Captcha').val(enc_generatedCaptcha(generateCaptcha)); 
  	} */
  	
	  	if(username != "") {
	  		//$('#textUserName').val(enc_password(username)); 
	  		$('#textUserName').val(username); 
	  	}
	    if(password != "") {
  		     $('#password').val(enc_password(password)); 
  		return true;
  	}  
  };  
</script>
<script>

document.onkeydown = function(e) {
		/* Block F12 */
		if(event.keyCode == 123) {
			return false;
		}
		/* CTRL + SHIFT + I */
		if(e.ctrlKey && e.shiftKey && e.keyCode == 'I'.charCodeAt(0)){
			return false;
		}
		/* CTRL + SHIFT + J */
		if(e.ctrlKey && e.shiftKey && e.keyCode == 'J'.charCodeAt(0)){
			return false;
		}
		/* CTRL + SHIFT + C 
		if(e.ctrlKey && e.shiftKey && e.keyCode == 'C'.charCodeAt(0)){
			return false;
		}*/
		/* CTRL + U */
		if(e.ctrlKey && e.keyCode == 'U'.charCodeAt(0)){
			return false;
		}
	}
	
$(document).ready(function(){
	   $(document).bind("contextmenu",function(e){
	      return false;
	   });
	});
	


/* $('input').on("cut copy paste",function(e) {
    e.preventDefault();
 }); */
</script>

