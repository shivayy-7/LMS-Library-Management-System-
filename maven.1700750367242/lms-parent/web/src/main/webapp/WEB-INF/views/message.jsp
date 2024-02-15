<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>

.alert .close{
	top: 0 !important;
	 background:none;
	  width: 0px;
	  height: 0px;
}

</style>

<c:if test="${not empty success_msg}">
	<div class="alert alert-success alert-dismissible fade show text-center" id="messageDiv"
		role="alert" style="background:#dff0d8;">
		<strong>${success_msg}</strong> 
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
</c:if>

<c:if test="${not empty error_msg}">
	<div class="alert alert-danger alert-dismissible fade show text-center" id="messageDiv"
		role="alert" style="background: rgb(255 245 245);    display: block;
    position: absolute;   top: 0;   z-index: 99;   left: 0;   right: 0;    height: 48px;    line-height: 17px;">
		<strong>${error_msg}</strong> 
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
</c:if>

<script>

function hide()
{
	$("#messageDiv").hide();
}
setTimeout(function() {
    $('#messageDiv').fadeOut('slow');
}, 10000);

</script>