<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
 <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
 <html>
<head>
	<jsp:include page="./header.jsp"></jsp:include>
</head>
<body>
 




	<style type="text/css">
.error {
	color: #FF0000;
	font-weight: bold;
}

.modal-blue .modal-backdrop {
	background-color: #0000ff;
}

.modal-white .modal-backdrop {
	background-color: #ffffff;
}
</style>

 <p></p>
	<!-- breadcrumb end -->

	<!-- main-container start -->
	<!-- ================ -->
	<c:if test="${message != null }">
		<div class="alert alert-success alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<strong>Success!</strong> ${message}
		</div>
	</c:if>


	<c:if test="${error != null }">
		<div class="alert alert-danger alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<strong>Success!</strong> ${error}
		</div>
	</c:if>
  <a href="register" class="btn btn-group btn-default btn-animated" >Click here to register more </a>
 

	<div class="container-fluid">
		<div class="row">

		 
		 
			<div class="col-md-8 col-md-offset-2">
				<div class="panel panel-default">
				 
												 
					<div class="panel-body">

						<div class="table-container">
								<table class="table table-filter">
									<thead>

										<tr>
											<th>Id</th>
											<th>Fname</th>
											<th>Lame</th>
											<th>Username</th>
											<th>Phone</th>
											<th>Email</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${userlist}" var="user">

											<tr data-status="pagado">
												<td>${user.id}</td>
												<td>${user.fname}</td>
												<p class="summary">
												<td>${user.lname}</td>
												</p>
												<td>${user.username}</td>
												<td>${user.phone}</td>
												<td>${user.email}</td> 
											</tr>

										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>

				</div>

</div>
			</div>
 
</body>
</html>