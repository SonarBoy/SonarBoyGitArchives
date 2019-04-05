<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
	</head>
	
	<body>
		<%
			Exception errorMessage = (Exception)request.getAttribute("ErrorMessage");
		%>
		<h1>There was an error</h1>
		<h3>Error Cause: <%=errorMessage.getCause() %></h3>
		<h3>Error Message: <%=errorMessage.getMessage() %></h3>
		<h3>Stack Trace: <%=errorMessage.getStackTrace() %></h3>
		
		
	</body>
	
</html>