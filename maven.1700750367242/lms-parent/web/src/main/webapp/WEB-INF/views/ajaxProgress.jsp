<!-- 
	 * @author Tapan
	 * @version 1.0
	 * @since 22-05-2017
	 * @description The Process Loader for Ajax Responses

 -->

<style>
#backgroundFaderDiv {
	display: none;
	position:absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: #ababab;
	z-index: 1001;
	-moz-opacity: 0.8;
	opacity: .70;
	filter: alpha(opacity=80);
}

#progressModalDiv {
	display: none;
	position: absolute;
	top: 42%;
	left: 48%;
	width: 290px;
	height: 80px;
	padding: 5px 5px 0px;
	border: 3px solid #ababab;
	box-shadow: 1px 1px 10px #ababab;
	border-radius: 15px;
	background-color: white;
	z-index: 1002;
	text-align: center;
	overflow: auto;
}
#progressText {
	color: #2e89a7;
	margin-bottom: 5px;
	font-size: 12px;
}
#progressImage {
	/* height: 55px;
	width: 55px; */
}
</style>

<div id="backgroundFaderDiv"></div>
<div id="progressModalDiv">
	<div id="progressText">Please wait while the request is processed ...</div>
	<img id="progressImage" src='resources/images/ajaxLoader/ajax-loader.gif'/>
</div>

<script>
function showAjaxProcess()
{
	document.getElementById('progressModalDiv').style.display = 'block';
	document.getElementById('backgroundFaderDiv').style.display = 'block';
}

function hideAjaxProcess() 
{
	document.getElementById('progressModalDiv').style.display = 'none';
	document.getElementById('backgroundFaderDiv').style.display = 'none';
}
</script>

 

