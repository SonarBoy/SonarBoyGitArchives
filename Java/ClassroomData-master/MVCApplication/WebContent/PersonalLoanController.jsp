<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">		
		<link rel="stylesheet" type="text/css" href="InputPage.css">
		<title>Calculate Period</title>
	</head>
	
	
	<body>
	
		<form action="LoanController" method="get">
			<p>Ammount Borrowed:<input type="number" name="ammountBorrowed"></input></p>
			<p>Payment Frequency: <select name="paymentFrequency">
	  			<option value="Weekly">Weekly</option>
	  			<option value="Bi-Weekly">Bi-Weekly</option>
	  			<option value="Semi-Monthly">Semi-Monthly</option>
	  			<option value="Monthly">Monthly</option>
			</select> </p>
			<p>Interest Rate:<input type="number" name="interestRate"></p>
			<p>Ammoritization Period<input type="number" name="period"></p>
			<input type="submit" value="Submit!"></input>
		</form>
		
	
	
	</body>
	
</html>