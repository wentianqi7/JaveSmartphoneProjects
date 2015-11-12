<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="servlets.BuildClientCommand"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	BuildClientCommand command = (BuildClientCommand) request
			.getSession().getAttribute("command");
	String model = request.getParameter("model").replaceAll("@", " ");
	String color = request.getParameter("Color").replaceAll("@", " ");
	String trans = request.getParameter("Transmission").replaceAll("@",
			" ");
	String brake = request.getParameter("Brakes/Traction@Control")
			.replaceAll("@", " ");
	String airbag = request.getParameter("Side@Impact@Air@Bags")
			.replaceAll("@", " ");
	String roof = request.getParameter("Power@Moonroof").replaceAll(
			"@", " ");
	int basePrice = command.getBasePrice();
	int cp = command.getOptionPrice("Color", color);
	int tp = command.getOptionPrice("Transmission", trans);
	int bp = command.getOptionPrice("Brakes/Traction Control", brake);
	int ap = command.getOptionPrice("Side Impact Air Bags", airbag);
	int rp = command.getOptionPrice("Power Moonroof", roof);
	int total = basePrice + cp + tp + bp + ap + rp;
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show User Choice</title>
</head>
<body>
	<title>Here is what you selected</title>
	<table border="1">
		<tr>
			<td><%=model%></td>
			<td>base price</td>
			<td><%=basePrice%></td>
		</tr>
		<tr>
			<td>Color</td>
			<td><%=color%></td>
			<td><%=cp%></td>
		</tr>
		<tr>
			<td>Transmission</td>
			<td><%=trans%></td>
			<td><%=tp%></td>
		</tr>
		<tr>
			<td>Brakes/Traction Control</td>
			<td><%=brake%></td>
			<td><%=bp%></td>
		</tr>
		<tr>
			<td>Side Impact Air Bags</td>
			<td><%=airbag%></td>
			<td><%=ap%></td>
		</tr>
		<tr>
			<td>Power Moonroof</td>
			<td><%=roof%></td>
			<td><%=rp%></td>
		</tr>
		<tr>
			<td><b>Total Cost</b></td>
			<td></td>
			<td>$<%=total%></td>
		</tr>
	</table>
</body>
</html>