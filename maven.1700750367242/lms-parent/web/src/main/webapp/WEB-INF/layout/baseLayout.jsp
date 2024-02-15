
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
        <link rel="stylesheet" href="${contextPath}/assets/js/plugin/jquery_autocomplete/jquery.autocomplete.css">
        <link rel="stylesheet" href="${contextPath}/assets/vendor/slick-1.8.1/slick/slick.css">
        <!-- Boxiocns CDN Link -->
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css'
            rel='stylesheet'>
        <link rel="stylesheet" href="${contextPath}/assets/css/style.css">

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
	<div id="loader" class="lds-dual-ring overlay"></div>
	

		
		<tiles:insertAttribute name="menu" />
			<section class="home-section">
				<tiles:insertAttribute name="header" />
				<tiles:insertAttribute name="body">
			</section>

			</tiles:insertAttribute>
			<tiles:insertAttribute name="footer" />
		</div>
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
<!--   <script src="https://crypto-js.googlecode.com/svn/tags/3.1.2/build/rollups/aes.js"></script> -->
  <script type="text/javascript" src="${contextPath}/assets/js/AES/aes.js"></script>
<script type="text/javascript" src="${contextPath}/assets/js/AES/AesUtil.js"></script>
<script type="text/javascript" src="${contextPath}/assets/js/AES/common.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
     

<script src="${contextPath}/assets/bootbox/bootbox.min.js"></script>
<script type="text/javascript" src="${contextPath}/assets/js/script.js"></script> 

   
<script>

$(document).ready(function() {
    $('.datepicker_con_add > input').datepicker({
        dateFormat: 'dd-mm-yy',
        maxDate: 0,  // Set maxDate to disable future dates, you can adjust this as needed
        showOnFocus: true,
        showTrigger: '<button type="button" class="trigger">' +
            '<i class="bx bx-calendar"></i></button>',
        onClose: function(selected) {
            var birthday = +new Date(selected);
            var age = ~~((Date.now() - birthday) / (31557600000));
            var da = age | 0;
            $('#farmerAge').val(da);
        }
    });
});

			$('.datepicker_con_disabled>input').datepick({onShow: $.datepick.monthOnly, dateFormat: 'dd/mm/yyyy',yearRange: 'c-100:c+5', showOnFocus: true,minDate:0,
				showTrigger: '<button type="button" class="trigger">' +
				'<i class="bx bx-calendar"></i></button>'});
			
			$('.datepicker_con_nextdisable>input').datepick({onShow: $.datepick.monthOnly, dateFormat: 'dd/mm/yyyy',maxDate:0,yearRange: 'c-100:c+5', showOnFocus: true,
				showTrigger: '<button type="button" class="trigger">' +
				'<i class="bx bx-calendar"></i></button>'});
			
			 $('.datepicker_con>input').datepick({onShow: $.datepick.monthOnly, dateFormat: 'dd/mm/yyyy',yearRange: 'c-100:c+5', showOnFocus: true,
				showTrigger: '<button type="button" class="trigger">' +
				'<i class="bx bx-calendar"></i></button>'});  
	 
	</script>


<script>

// document.onkeydown = function(e) {
// 		/* Block F12 */
// 		if(event.keyCode == 123) {
// 			return false;
// 		}
// 		/* CTRL + SHIFT + I */
// 		if(e.ctrlKey && e.shiftKey && e.keyCode == 'I'.charCodeAt(0)){
// 			return false;
// 		}
// 		/* CTRL + SHIFT + J */
// 		if(e.ctrlKey && e.shiftKey && e.keyCode == 'J'.charCodeAt(0)){
// 			return false;
// 		}
		/* CTRL + SHIFT + C 
		if(e.ctrlKey && e.shiftKey && e.keyCode == 'C'.charCodeAt(0)){
			return false;
		}*/
		/* CTRL + U */
// 		if(e.ctrlKey && e.keyCode == 'U'.charCodeAt(0)){
// 			return false;
// 		}
// 	}
	
$(document).ready(function(){
	   $(document).bind("contextmenu",function(e){
	      return false;
	   });
	   
	   $(document).ready(function(){
		   $(document).bind("contextmenu",function(e){
		      return false;
		   });
		   
		    $('.exportbtn').DataTable({
		       
			});
		    
		});
		
	$(document).ready(function() {
	 $('.export-table').DataTable( {
	     dom: 'Bfrtip',
	     buttons: [
	         'copy', 'csv', 'excel', 'pdf', 'print'
	     ]
	 } );
	} );

	    
	});

</script>


<script>
		function logout(){
			debugger
			$('#frmLogout').submit();
		}
		
		</script>
		<script>
		let currentImageIndex = null;

		function updateSlotVisibility() {
		  const imageCount = document.getElementById('image-container').getElementsByClassName('image-wrapper').length;
		  document.getElementById('image-slot').style.display = imageCount >= 8 ? 'none' : 'inline-flex';
		  
		  $(document).on("click", ".image-options", function() {
			  $(this).parents('.image-wrapper').remove();
			});
		}

		document.getElementById('image-container').addEventListener('click', function(event) {
		  const openMenus = document.querySelectorAll('.context-menu');
		  openMenus.forEach(menu => {
		      menu.style.display = 'none';
		  });

		  if (event.target.nodeName === 'IMG') {
		    const modal = document.getElementById('fullscreen-modal');
		    const fullImage = document.getElementById('fullscreen-image');
		    const images = Array.from(document.getElementById('image-container').getElementsByTagName('img'));
		    
		    currentImageIndex = images.indexOf(event.target);
		    
		    fullImage.src = event.target.src;
		    modal.style.display = 'flex';
		  } else if (event.target.classList.contains('image-options')) {
		    const menu = event.target.nextSibling;
		    menu.style.display = 'block';
		  } else if (event.target.innerText === 'Eliminar') {
		    const imgWrapper = event.target.closest('.image-wrapper');
		    imgWrapper.remove();
		    updateSlotVisibility();
		  }
		});

		document.getElementById('prev-image').addEventListener('click', function() {
		  const images = Array.from(document.getElementById('image-container').getElementsByTagName('img'));
		  
		  if (currentImageIndex > 0) {
		    currentImageIndex--;
		    document.getElementById('fullscreen-image').src = images[currentImageIndex].src;
		  }
		});

		document.getElementById('next-image').addEventListener('click', function() {
		  const images = Array.from(document.getElementById('image-container').getElementsByTagName('img'));
		  
		  if (currentImageIndex < images.length - 1) {
		    currentImageIndex++;
		    document.getElementById('fullscreen-image').src = images[currentImageIndex].src;
		  }
		});

		document.getElementById('close-modal').addEventListener('click', function() {
		  document.getElementById('fullscreen-modal').style.display = 'none';
		});

		document.addEventListener('keydown', function(event) {
		  if (document.getElementById('fullscreen-modal').style.display === 'flex') {
		    if (event.key === 'ArrowLeft') {
		      document.getElementById('prev-image').click();
		    } else if (event.key === 'ArrowRight') {
		      document.getElementById('next-image').click();
		    } else if (event.key === 'Escape') {
		      document.getElementById('close-modal').click();
		    }
		  }
		});

		document.getElementById('fullscreen-modal').addEventListener('click', function(event) {
		  if (event.target === document.getElementById('fullscreen-modal')) {
		    document.getElementById('close-modal').click();
		  }
		});

		document.getElementById('image-slot').addEventListener('click', function() {
		  document.getElementById('image-upload').click();
		});

		document.getElementById('image-upload').addEventListener('change', function(event) {
		  const files = event.target.files;
		  const currentImages = document.getElementById('image-container').getElementsByClassName('image-wrapper').length;
		  const allowedUploads = 8 - currentImages;

		  if (files.length > allowedUploads) {
		    const toast = document.getElementById('toast');
		    toast.style.display = 'block';
		    setTimeout(() => {
		      toast.style.display = 'none';
		    }, 3000);
		    return;
		  }

		  for (let i = 0; i < files.length; i++) {
		    const file = files[i];
		    const reader = new FileReader();
		    
		    reader.onload = function(e) {
		      const imgWrapper = document.createElement('div');
		      imgWrapper.classList.add('image-wrapper');

		      const img = document.createElement('img');
		      img.src = e.target.result;

		      const optionsIcon = document.createElement('div');
		      optionsIcon.classList.add('image-options');
		      optionsIcon.innerHTML = '...';

		      const menu = document.createElement('div');
		      menu.classList.add('context-menu');
		      const deleteButton = document.createElement('button');
		      deleteButton.innerText = 'Eliminar';
		      deleteButton.addEventListener('click', function() {
		        imgWrapper.remove();
		        updateSlotVisibility();
		        menu.style.display = 'none';
		      });
		      menu.appendChild(deleteButton);

		      imgWrapper.appendChild(img);
		      imgWrapper.appendChild(optionsIcon);
		      imgWrapper.appendChild(menu);

		      const slot = document.getElementById('image-slot');
		      document.getElementById('image-container').insertBefore(imgWrapper, slot);
		      
		      updateSlotVisibility();
		    }

		    reader.readAsDataURL(file);
		  }
		});

		const imageContainer = document.getElementById('image-container');
		const sortable = new Sortable(imageContainer, {
		    animation: 150,
		    handle: '.image-wrapper',
		    filter: '#image-slot',
		    ghostClass: 'sortable-ghost',
		    chosenClass: 'sortable-chosen'
		});
		
		/* $('.image-options').click(function({
			$(this).closest('.image-wrapper').remove();
		}); */
		
		$(document).ready(function() {
		    $('#ex').DataTable( {
		        dom: 'Bfrtip',
		        buttons: [
		            'copy', 'csv', 'excel', 'pdf', 'print'
		        ]
		    } );
		} );
		
	
		</script>
</html>