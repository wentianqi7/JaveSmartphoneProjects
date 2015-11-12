<%@ page import="servlets.BuildClientCommand, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Available Model Page</title>
</head>
<body>
	<h1>Available Models</h1>
	<%
		BuildClientCommand command = new BuildClientCommand();
		command.openConnection();
		ArrayList<String> models = null;
		try {
			models = command.showModelList();
		} catch (Exception e) {
			out.write("<p>No model available now</p>");
			command.closeSession();
			return;
		}
		command.closeSession();
	%>

	<h3>Choose One Model</h3>
	<form method="post" action="showOptionSet.jsp" name="chooseModel">
		<select name="model" onchange="document.chooseModel.submit();"
			style="width: 250px; height: 24px; font-size: 18px">
			<option value="choice">Please select one
			<option value="<%=models.get(0)%>"><%=models.get(0)%>
			<option value="<%=models.get(1)%>"><%=models.get(1)%>
		</select> <input type="hidden" name="choice" id="choice">
	</form>
</body>
</html>