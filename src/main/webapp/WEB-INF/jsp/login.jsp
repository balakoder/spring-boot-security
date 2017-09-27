
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<jsp:include page="header.jsp"></jsp:include>
</head>

<body>

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

	<div class="login-page">
		<div class="form">
			<form:form method="POST" action="${contextPath}/login"
				class="login-form" modelAttribute="user">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />


				<form:input path="username" type="text" id="inputUserName"
					placeholder="User Name" />
				<span><form:errors cssClass="error" path="username" /></span>

				<form:input path="password" type="password" id="inputPassword"
					placeholder="Password" />
				<button type="submit" class="btn btn-group btn-default btn-animated">Log
					In</button>
				<p class="message">
					Not registered? <a href="register">Create an account</a>
				</p>
			</form:form>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
$(document).ready(function () {
console.log("hello");
 $('.message a').click(function(){
   $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
});
});
</script>
</body>
</html>