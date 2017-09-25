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


 <div class="login-page">
  <div class="form">
  
  
  <form:form method="POST" class="login-form"  action="register" modelAttribute="user">
   
      <form:input path="fname"  type="text" class="form-control" id="inputName" placeholder="Fisrt Name" />
      
      <form:input path="lname" type="text" class="form-control"  placeholder="Last Name" />
     	
      <form:input path="phone" type="text" class="form-control"  placeholder="phone No" />
     	
      <form:input path="email"  type="email" class="form-control"  placeholder="Email" />
      
      <form:input path="username"  type="username" class="form-control"  placeholder="username"   />
      
     <form:input path="password"  type="password"  class="form-control"   placeholder="Password"   />
      
     <!--  <label>
	<input type="checkbox" required> Accept our <a href="#">privacy policy</a> and <a href="#">customer agreement</a>
	</label> -->
												
     <button type="submit" class="btn btn-group btn-default btn-animated">Sign Up  </button>
      <p class="message">Already registered? <a href="login">Sign In</a></p>
   	</form:form>
    
   
   
</div>
</div>

 
		 
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
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