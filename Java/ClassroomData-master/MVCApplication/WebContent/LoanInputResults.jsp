<%@ page language="java" import="patternsPackage.PersonalLoanCalculator" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
	</head>
	
	<body>
		<% PersonalLoanCalculator x = (PersonalLoanCalculator)request.getAttribute("LoanInfo");
		%>
		
		<h1>Borrowed Ammount: <%=x.getAmmountBorrowed() %></h1>
		<h1>Answer: <%=x.getAnswer() %></h1>
	</body>
	
</html>