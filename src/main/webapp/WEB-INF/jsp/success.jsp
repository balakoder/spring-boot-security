<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
 <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
 <html>
<head>
	<jsp:include page="header.jsp"></jsp:include>
</head>
<body>

<div class="jumbotron">
  <h1>Success !!!</h1>
  <P> ${message}. </P>
   
  <p><a class="btn btn-primary btn-lg" href="login" role="button">Login</a></p>
</div>
 

 
 
</body>
</html>