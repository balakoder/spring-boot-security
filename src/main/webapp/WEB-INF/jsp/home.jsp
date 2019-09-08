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
		<div class="alert alert-success alert-dismissible fade show" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<strong>Success!</strong> ${message}
			 <strong>Hello!</strong> ${user}
		</div>
	</c:if>


	<c:if test="${error != null }">
		<div class="alert alert-danger alert-dismissible fade show" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<strong>Success!</strong> ${error}
		</div>
	</c:if>
  <a href="${contextPath}/logout" class="btn btn-group btn-default btn-animated" >Click here to Logout </a>
  
  <a href="${contextPath}/xyz"> Go to another page</a></p>
 
 
</body>
</html>