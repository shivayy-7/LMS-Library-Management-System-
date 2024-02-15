<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<sec:authentication var="principal" property="principal" />

<!-- <div class="wrapper "> -->
	
            <div class="heading-top">
			<img src="${contextPath}/assets/image/library-with-books.jpg"  class="topimg" style="width:100%">
                <div class="home-content">
	                <div class="col-md-8 plft10">
	                    <i class='bx bx-menu'><img src="${contextPath}/assets/img/menuicon.png"></i>
	                    <img src="${contextPath}/assets/img/logo.png" style="width: 290px;">
					</div>
                    <div class="col-md-4 mt-1 plft10 d-flex justify-content-end align-items-center">
                    <div class="time-box">
                        <h5 class="whiteheading">Hi, Welcome back!</h5>
                        <div class="datetimeholder"><div class="display-date">
                                <span id="day">day</span>,
                                <span id="daynum">00</span>
                                <span id="month">month</span>
                                <span id="year">0000</span>
                            </div>
                            <div class="display-time"></div>
                         </div>
                        </div>
                        
                        <ul class="d-flex list-unstyled mb-0">
<%--                         	<li><a href="${contextPath}/umt/user/profile" class="btn btn-sm btn-notification"><i class='bx bx-bell'></i></a></li> --%>
                        	<li><a href="${contextPath}/umt/user/profile" class="btn btn-sm btn-success"><i class='bx bx-user'></i></a></li>
                        	<li><button type="button" class="btn btn-sm btn-danger btnLogout" onclick="logout()"><i class='bx bx-power-off'></i></button></li>
                        </ul>
                    </div>
                </div>
            </div>
            
           

<!-- End Navbar -->
<form method="post" action="${contextPath}/umt/logout" id="frmLogout">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" /> <input type="submit" style="display: none" />
</form>

<script>
// 		/* 	$(function(){
// 				$('#btnLogout').click(function(){
	function logout(){
					debugger;
					$('#frmLogout').submit();
	}
// 				}); */
				
				$('#siteLangSelector').change(function(){
					const lang = $(this).val();
					switchLanguage(lang);
				});
// 			});
		</script>
		
		

