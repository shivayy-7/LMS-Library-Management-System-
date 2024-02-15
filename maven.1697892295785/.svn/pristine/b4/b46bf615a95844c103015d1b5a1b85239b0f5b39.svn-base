<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<sec:authentication var="principal" property="principal" />
<!-- Sidebar -->

	<div class="sidebar">
	<img src="${contextPath}/assets/image/book.png" alt="" class="img-fluid" style="position: absolute;
    bottom: 0;   width: 92%;    opacity: 0.2;">
<%-- 	<img src="${contextPath}/assets/image/library-with-books.jpg" alt="" class="img-fluid" style=" position: absolute; --%>
<!--     height: 100%; width: 100%;   object-fit: cover;  opacity: 0.1;"> -->
      <%-- <ul class="profile-box">
        <li><img src="${contextPath}/assets/image/avtarr.png" alt="" class="img-fluid"></li>
        <li>
          <h5 class="user-name">Soumya Ranjan</h5>
          <h6 class="role">Admin</h6>
        </li>
      </ul> --%>
      <ul class="nav-links">
        <li class="active">
          <a href="${contextPath}/home">
            <i class='bx bx-grid-alt'></i>
            <span class="link_name">Dashboard</span>
          </a>
        </li>
        <sec:authorize access="isAuthenticated()">
			<c:forEach items="${sessionScope.USER_MENUS}" var="node">
				<c:if test="${node.isDisplay eq true}">
			        <li>
				        <c:choose>
							<c:when test="${node.isParent eq true}">
					          <div class="iocn-link">
					            <a href="javascript:void(0)">
					              <i class='${node.menuIcon}'></i>
					              <span class="link_name">${node.menuText}</span>
					            </a>
					            <i class='bx bxs-chevron-down arrow'></i>
					          </div>
					          
					          
					          <ul class="sub-menu">
					          	<c:forEach items="${node.children}" var="menu">
									<c:set var="node" value="${menu}" scope="request" />
									<jsp:include page="node.jsp" />
								</c:forEach>
					          </ul>
			         	</c:when>
					  <c:when test="${node.isParent eq false}">
					 	<li>
						 	<a href="${contextPath}${node.menuURL}">
						 		<i class="${node.menuIcon}"></i>
								<span class="link_name">${node.menuText}</span>
							</a>
						</li>
					   </c:when>
					</c:choose>
				</li>
			</c:if>
		</c:forEach>
	</sec:authorize>
  </ul>
</div>
    
    
    

<%-- <div class="sidebar">
    <ul class="nav-links">
        <li>
            <a href="#">
                <i class='bx bx-grid-alt'></i>
                <span class="link_name">Dashboard</span>
            </a>
        </li>
        <sec:authorize access="isAuthenticated()">
			<c:forEach items="${sessionScope.USER_MENUS}" var="node">
				<c:if test="${node.isDisplay eq true}">
			        <li>
			        	<c:choose>
							<c:when test="${node.isParent eq true}">
					            <div class="iocn-link">
					                <a href="#">
					                    <i class='bx bx-pointer'></i>
					                    <span class="link_name">Categories &amp; Books</span>
					                </a>
					            </div>
					    
			        </li>
		
        <li>
            <div class="iocn-link">
                <a href="#">
                    <i class='bx bx-user-plus'></i>
                    <span class="link_name">Manage Students</span>
                </a>
            </div>
        </li>
        <li>
            <div class="iocn-link">
                <a href="#">
                    <i class='bx bx-book-open'></i>
                    <span class="link_name">Issue Books</span>
                </a>
            </div>
        </li>
        <li>
            <div class="iocn-link">
                <a href="#">
                    <i class='bx bx-history'></i>
                    <span class="link_name">Isssued History</span>
                </a>
            </div>
        </li>
        <li>
            <div class="iocn-link">
                <a href="#">
                    <i class='bx bx-book'></i>
                    <span class="link_name">Return Books</span>
                </a>
            </div>
        </li>
        <li>
            <div class="iocn-link">
                <a href="#">
                    <i class='bx bx-crop'></i>
                    <span class="link_name">Returned History</span>
                </a>
            </div>
        </li>
        <li>
            <div class="iocn-link">
                <a href="#">
                    <i class='bx bx-message-square-dots'></i>
                    <span class="link_name">View Records</span>
                </a>
            </div>
        </li>
        <li>
            <div class="iocn-link">
                <a href="#">
                    <i class='bx bx-copy'></i>
                    <span class="link_name">Upload Data(CSV)</span>
                </a>
            </div>
        </li>
    </ul>
</div> --%>
        
        
        
        
        <!-- OLD Menu -->
<%-- <div class="sidebar sidebar-style-2">
	<div class="sidebar-wrapper scrollbar scrollbar-inner">
		<div class="sidebar-content">
			<div class="side-img">
				<img alt="" src="${contextPath}/assets/img/bemc-img.jpg" class="img-fluid">
				<ul class="navbar-nav topbar-nav ml-md-auto align-items-center">
						
						<li class="nav-item dropdown hidden-caret">
							<a class="nav-link dropdown-toggle" href="#" id="notifDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<i class="fa fa-bell"></i>
								<span class="notification">3</span>
							</a>
							<ul class="dropdown-menu notif-box animated fadeIn" aria-labelledby="notifDropdown">
								<li>
									<div class="dropdown-title">You have 3 new notifications</div>
								</li>
								<li>
									<div class="notif-scroll scrollbar-outer">
										<div class="notif-center">
											<a href="#">
												<div class="notif-icon notif-primary"> <i class="fa fa-user-plus"></i> </div>
												<div class="notif-content">
													<span class="block">
														New user registered
													</span>
													<span class="time">5 minutes ago</span> 
												</div>
											</a>
											<a href="#">
												<div class="notif-icon notif-success"> <i class="fa fa-comment"></i> </div>
												<div class="notif-content">
													<span class="block">
														Rahmad commented on Admin
													</span>
													<span class="time">12 minutes ago</span> 
												</div>
											</a>
											<a href="#">
												<div class="notif-img"> 
													<img src="${contextPath}/assets/img/profile2.jpg" alt="Img Profile">
												</div>
												<div class="notif-content">
													<span class="block">
														Reza send messages to you
													</span>
													<span class="time">12 minutes ago</span> 
												</div>
											</a>
										</div>
									</div>
								</li>
								<li>
									<a class="see-all" href="javascript:void(0);">See all notifications<i class="fa fa-angle-right"></i> </a>
								</li>
							</ul>
						</li>

						<li class="nav-item dropdown hidden-caret">
							<a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#" aria-expanded="false">
								<div class="avatar-sm">
									<img src="${contextPath}/assets/img/avtar.png" alt="..." class="avatar-img rounded-circle">
								</div>
							</a>
							<ul class="prof-list">
								<li>${principal.dbUser.firstName} &nbsp; ${principal.dbUser.lastName} <span>${principal.dbUser.designation}</span></li>
								<li class="prof-btn"><a href="${contextPath}/umt/user/change/password" title="Change Password"><i class="fa fa-key" aria-hidden="true"></i></a> <a href="${contextPath}/umt/user/profile" title="User Profile"><i class="fa fa-user"></i></a></li>
							</ul>
							 <!-- <ul class="dropdown-menu dropdown-user animated fadeIn">
                                <div class="dropdown-user-scroll scrollbar-outer">
									<li>
										<div class="user-box">
											<div class="avatar-lg"><img src="${contextPath}/assets/img/profile.jpg"
													alt="image profile" class="avatar-img rounded"></div>
											<div class="u-text">
											<h4>${principal.dbUser.firstName}
												${principal.dbUser.lastName}</h4>
											<p class="text-muted">${principal.dbUser.designation}</p>
											<a href="${contextPath}/umt/user/profile"
												class="btn btn-xs btn-primary btn-sm">View Profile</a>
										</div>
										</div>
									</li>
									<li>
									<div class="dropdown-divider"></div> <a class="dropdown-item"
									href="${contextPath}/umt/user/profile">Edit Profile</a>
									<div class="dropdown-divider"></div> <a class="dropdown-item"
									id="btnLogout" href="#">Logout</a>
								   </li>
								</div>
                            </ul>-->
						</li>
					</ul>
			</div>
			<ul class="nav nav-primary">
				<li class="nav-item "><a href="${contextPath}"> <i
						class="fas fa-home"></i>
						<p>Dashboard</p>
				</a></li>
				<sec:authorize access="isAuthenticated()">
					<c:forEach items="${sessionScope.USER_MENUS}" var="node">
						<c:if test="${node.isDisplay eq true}">
							<li class="nav-item"><c:choose>
									<c:when test="${node.isParent eq true}">
										<a data-toggle="collapse" href="#mnu${node.menuId}"> <i
											class="${node.menuIcon}"></i>
											<p>${node.menuText}</p> <span class="caret"></span>
										</a>
										<div class="collapse" id="mnu${node.menuId}">
											<ul class="nav nav-collapse">
												<c:forEach items="${node.children}" var="menu">
													<c:set var="node" value="${menu}" scope="request" />
													<jsp:include page="node.jsp" />
												</c:forEach>
											</ul>
										</div>
									</c:when>
									<c:when test="${node.isParent eq false}">
										<a href="${contextPath}${node.menuURL}"> <i
											class="${node.menuIcon}"></i>
											<p>${node.menuText}</p>
										</a>
									</c:when>
								</c:choose></li>
						</c:if>
					</c:forEach>
				</sec:authorize>
			</ul>
		</div>
	</div>
</div> --%>