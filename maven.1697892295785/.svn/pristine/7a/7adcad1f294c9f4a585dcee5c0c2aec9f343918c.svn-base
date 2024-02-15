<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<link
	href="${contextPath}/assets/js/plugin/jquery-fancytree/skin-xp/ui.fancytree.css"
	rel="stylesheet" />

<!-- Fancy Tree needs this version of jquery-ui -->
<script
	src="${contextPath}/assets/js/plugin/jquery-fancytree/jquery-ui.min.js"></script>
<script
	src="${contextPath}/assets/js/plugin/jquery-fancytree/jquery.fancytree-all.min.js"></script>
<script
	src="${contextPath}/assets/js/plugin/jquery-fancytree/modules/jquery.fancytree.dnd5.js"></script>
<script
	src="${contextPath}/assets/js/plugin/jquery-fancytree/jquery-migrate-3.0.0.min.js"></script>

<script
	src="${contextPath}/assets/appJs/admin/menu/reorderMenuController.js"></script>
<div class="content">
<div class="container-fluied" style="margin-top: -100px; position: relative; z-index:99">

	<div class="page-inner mt-5 pb-0">
		<div class="row mt-2">
			<div class="col-md-12">
				<div class="card full-height">
					<div class="card-header">
						<!-- ><div class="panel-actions">
							<a href="#" class="fa fa-caret-down"></a>
						</div>-->
						<h6>Reorder Menu</h6>
					</div>
					<div class="card-body">
						<div class="row">
							<%@ include file="/WEB-INF/views/message.jsp"%>
							<div class="col-md-12">
								<table class="table table-bordered table-striped table-sm"
									id="treegrid">
									<thead>
										<tr>
											<th>Title</th>
											<th>URL</th>
										</tr>
									</thead>
									<!-- Optionally define a row that serves as template, when new nodes are created: -->
									<tbody>
										<tr>
											<td></td>
											<td></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
</div>
<form id="frmMoveMenu" action="" method="post">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" /> <input type="hidden" name="menuId"
		id="menuId" /> <input type="hidden" name="parentMenuId"
		id="parentMenuId" /> <input type="hidden" name="order" id="order" />
</form>