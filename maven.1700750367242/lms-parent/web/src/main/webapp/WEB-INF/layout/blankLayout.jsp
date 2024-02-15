
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!-- ========================================
  Author Name : Prasanta Kumar Singh
  LinkeDIn: https://www.linkedin.com/in/akash-pradhan/
  Version : 1.1
========================================== -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<!-- <meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no' name='viewport' /> -->
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>LMS</title>
<meta name="description"
	content="Department of Industries -  Govt. of Uttar Pradesh">
<meta name="author" content="Aashdit Technologies">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="cache-control" content="no-store" />
<meta http-equiv="cache-control" content="pre-check=0" />
<meta http-equiv="cache-control" content="post-check=0" />
<meta http-equiv="cache-control" content="must-revalidate" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="pragma" content="no-cache" />
<!-- CSS Files -->
<link rel="stylesheet" href="${contextPath}/assets/css/fonts.min.css" />
<link rel="stylesheet"
	href="${contextPath}/assets/vendor/bootstrap-5.0.2/css/bootstrap.min.css">
	
<!-- 	<link rel="stylesheet" -->
<%--             href="${contextPath}/assets/vendor/bootstrap-5.0.2/css/bootstrap.min.css"> --%>
        <link rel="stylesheet"
            href="${contextPath}/assets/js/jquery_datepicker/jquery.datepick.css">
        <link rel="stylesheet" href="${contextPath}/assets/css/datatables.min.css">
        <link rel="stylesheet" href="${contextPath}/assets/vendor/slick-1.8.1/slick/slick.css">
        <link rel="stylesheet" href="${contextPath}/assets/css/style.css">
        <!-- Boxiocns CDN Link -->
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css'
            rel='stylesheet'>

<script type="text/javascript" src="${contextPath}/assets/js/jquery.min.js"></script>

<!-- Fonts and icons -->
<script type="text/javascript">
 	window.contextPath = '${contextPath}';
 	window.ctxPath ='${contextPath}';
	$(function() 
	{
	       var url = window.location.href;
	       var newurl = url.split('?');
	       var finala=newurl[0];
	       $(".nav-primary a").each(function() 
	       {
	              if (finala == this.href)
	              {
	                     $(this).parent().addClass("active");
	                     $(this).parent().parent().parent().addClass("show");
	          		           $(this).parent().parent().parent().parent().addClass("active");
	                     $(this).parent().parent().parent().parent().parent().parent().addClass("show");
	              }
	       });
	       
// 	       $('input').on("cut copy paste",function(e) {
// 	    	      e.preventDefault();
// 	    	   });
	});
</script>

</head>

<body>
	<section class="body">
		<div class="inner-wrapper">
			<section role="main" class="content-body">
			    <tiles:insertAttribute name="header" />
				<tiles:insertAttribute name="body" />
			</section>
		</div>
	</section>
</body>

<!-- Page Specific Scripts -->
<%-- <tiles:insertAttribute name="pageScripts" /> --%>

<!-- jQuery UI -->




  <script type="text/javascript" src="${contextPath}/assets/js/jquery.min.js"></script>
  <script src="${contextPath}/assets/js/plugin/jquery_autocomplete/jquery.autocomplete.min.js"></script>
  <script src="${contextPath}/assets/vendor/bootstrap-5.0.2/js/bootstrap.bundle.js"></script>
  <script src="${contextPath}/assets/js/datatables.min.js"></script>
  <script src="${contextPath}/assets/js/jquery_datepicker/jquery.plugin.js" type="text/javascript"></script>
  <script src="${contextPath}/assets/js/jquery_datepicker/jquery.datepick.js" type="text/javascript"></script>
  <script src="${contextPath}/assets/vendor/slick-1.8.1/slick/slick.min.js" type="text/javascript"></script>
     

<script src="${contextPath}/assets/bootbox/bootbox.min.js"></script>
<script type="text/javascript" src="${contextPath}/assets/js/script.js"></script> 

   
<script>

		$(document).ready(function() {

			$('.datepicker_con_add>input').datepick({
				 dateFormat: 'dd/mm/yyyy',maxDate: new Date(), showOnFocus: true,
				 showTrigger: '<button type="button" class="trigger">' +
				 '<i class="fa fa-calendar"></i></button>',
				 onClose: function(selected) {
				 	var birthday = +new Date(selected);
						var age = ~~((Date.now() - birthday) / (31557600000));
						var da = age | 0;
						$('#farmerAge').val(da);
						
				 }
			});
			$('.datepicker_con_disabled>input').datepick({onShow: $.datepick.monthOnly, dateFormat: 'dd/mm/yyyy',yearRange: 'c-100:c+5', showOnFocus: true,minDate:1,
				showTrigger: '<button type="button" class="trigger">' +
				'<i class="fa fa-calendar"></i></button>'});
			
			$('.datepicker_con_nextdisable>input').datepick({onShow: $.datepick.monthOnly, dateFormat: 'dd/mm/yyyy',maxDate:0,yearRange: 'c-100:c+5', showOnFocus: true,
				showTrigger: '<button type="button" class="trigger">' +
				'<i class="fa fa-calendar"></i></button>'});
			
			 $('.datepicker_con>input').datepick({onShow: $.datepick.monthOnly, dateFormat: 'dd/mm/yyyy',yearRange: 'c-100:c+5', showOnFocus: true,
				showTrigger: '<button type="button" class="trigger">' +
				'<i class="fa fa-calendar"></i></button>'});  
		});
	 
	</script>




