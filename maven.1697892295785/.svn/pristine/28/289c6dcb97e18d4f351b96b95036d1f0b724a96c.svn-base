
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div class="content">
<div class="container-fluied" style="margin-top: -100px; position: relative; z-index:99">
	
	<div class="page-inner mt-5 pb-0">
		<div class="row mt-2">
			<div class="col-md-12">
				<div class="card full-height">
					<div class="card-header">
						<!--<div class="panel-actions">
							<a href="#" class="fa fa-caret-down"></a>
						</div>-->
						<h6 class="card-title">Role Menu Mapping</h6>
					</div>
					<div class="card-body">
						<div class="row">
							<%@ include file="/WEB-INF/views/message.jsp"%>
							<div class="col-md-12">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								<div class="col-md-12">
									<div class="col-md-3">
										<div class="form-group">
											<label class="col-md-12 required" for="roleId">Role :</label>
											<div class="col-md-12">
												<select class="form-control form-control-sm" name="roleId"
													id="roleId">
													<option value="">--select--</option>
													<c:forEach items="${roleList}" var="roleList">
														<option value="${roleList.roleCode}">${roleList.displayName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<!-- Menu Table -->
									<div class="col-md-12" style="margin-top: 20px;">
										<div class="form-group">
											<div class="col-sm-12">
												<!-- Add a <table> element where the tree should appear: -->
												<table id="treegrid" class="table table-collapsed">
													<colgroup>
														<col width="30px"></col>
														<col width="*"></col>
														<col width="450px"></col>
														<%-- <col width="180px"></col> --%>
													</colgroup>
													<thead>
														<tr>
															<th>Select</th>
															<th>Menu Name</th>
															<th>Menu URL</th>
														</tr>
													</thead>
													<!-- Optionally define a row that serves as template, when new nodes are created: -->
													<tbody>
														<tr>
															<td></td>
															<td></td>
															<td></td>
															<!--   <td></td> -->
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>

									<div class="col-md-12 text-center">
										<button type="button" class="btn btn-success btn-sm"
											id="btnAssign">Assign</button>
										<a href="${contextPath}/" class="btn btn-warning btn-sm">Cancel</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
<link
	href="${contextPath}/assets/js/plugin/jquery-fancytree/skin-xp/ui.fancytree.css"
	rel="stylesheet" />

<!-- Fancy Tree needs this version of jquery-ui -->
<script
	src="${contextPath}/assets/js/plugin/jquery-fancytree/jquery-ui.min.js"></script>
<script
	src="${contextPath}/assets/js/plugin/jquery-fancytree/jquery.fancytree-all.min.js"></script>


<!-- end: page -->
<script type="text/javascript">
	
$().ready(function(){
	
	$("#treegrid").fancytree({
		  checkbox: true,
		  selectMode: 3,
		  partsel: true , 
		  source : [] ,
		  extensions: ["table"],
		  table: {
		        indentation: 20,      // indent 20px per node level
		        nodeColumnIdx: 1,     // render the node title into the 2nd column
		        checkboxColumnIdx: 0  // render the checkboxes into the 1st column
	      },
	      renderColumns: function(event, data) {
	          var node = data.node,
              $tdList = $(node.tr).find(">td");

	          // (index #0 is rendered by fancytree by adding the checkbox)
	          $tdList.eq(2).text(node.data.url);
        },
	 });
	
	$('#roleId').change(function(){
		
		var roleCode = $('#roleId option:selected').val();
		let root = $("#treegrid").fancytree('getTree').rootNode;
		root.removeChildren();
		
		$.ajax({
			url : "${contextPath}/admin/menu/" + roleCode,
			type: "GET",
	        dataType: "json",
	        success: function(data) {
	        	let tree = $("#treegrid").fancytree('getTree');
	        	tree.reload(data);
	        },
	        cache: false
		});

	});
	
	$('#btnAssign').click(function(){
		let nodes = $('#treegrid').fancytree('getTree').getSelectedNodes();
		
		let nodeIds = [];
		
		$.each(nodes, function(idx, elem){
			nodeIds.push(elem.data.id);
			nodeIds.push(elem.parent.data.id);
			if (elem.parent.parent !== null)
			{
				nodeIds.push(elem.parent.parent.data.id);
			}
			
		});
		
		//console.log(nodeIds);
		var roleCode = $('#roleId option:selected').val();

		if (roleCode == "")
		{
			bootbox.alert("Please select a Role");
			return false;
		}
		
		/* Remove nodes with undefined values */
		var distIds = nodeIds.filter(function(value, index, arr){

			return value !== undefined;
		});
		
		/* filter to unique elements */
		distIds = [... new Set(distIds)];
		
		//console.log(distIds);
		
		$.ajax({
			url : "${contextPath}/admin/menu/assign",
			type: "POST",
			data : {
				"_csrf" : '${_csrf.token}',
				"data" : distIds,
				"roleCode" : roleCode
			},
	        dataType: "json",
	        success: function(data) {
	        	bootbox.alert("Menu assigned succesfully.");
	        },
	        cache: false
		});
	});
	
});

	
</script>

