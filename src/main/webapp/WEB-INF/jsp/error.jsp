<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="cp" value="${pageContext.request.servletContext.contextPath}"   />

<title>The Evolve me | Page 404</title>
<%-- <jsp:include page="./main/header.jsp"></jsp:include> --%>
</head>
<body>
<style type="text/css">
	  .center {text-align: center; margin-left: auto; margin-right: auto; margin-bottom: auto; margin-top: auto;}
</style>

 
<div class="container">
  <div class="row">
    <div class="span12">
      <div class="hero-unit center">
          <h1>Page Not Found <small><font face="Tahoma" color="red">Error 404</font></small></h1>
          <br />
          <p>The page you requested could not be found. Use your browsers <b>Back</b> button to navigate to the page you have prevously come from</p>

        

          <p><b>Or you could just press this neat little button:</b></p>
          <a href="${cp}/login" class="btn btn-large btn-info"><i class="icon-home icon-white"></i> Take Me Home</a>
        </div>
        <br />
         </div>
          </div>
           </div>
<%--            	<jsp:include page="./main/footer.jsp"></jsp:include> --%>
</body>
</html>