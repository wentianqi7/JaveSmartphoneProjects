<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="model.*, servlets.BuildClientCommand, client.Client, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show OptionSet Choices</title>
<%
	String model = (String) (request.getParameter("model")).replaceAll(
			" ", "@");
%>
<script>
	function sendRequest() {
		var xmlHttp = new XMLHttpRequest();
		xmlHttp
				.open("GET", "/CarConfigWeb/optionset?model="+"<%=model%>",
				false);
		xmlHttp.send(null);
		document.getElementById("form").innerHTML = xmlHttp.response;
	}
</script>
</head>
<body onload="sendRequest();">
	<h1>Basic Car Choice</h1>
	<div id="form"></div>

</body>
</html>