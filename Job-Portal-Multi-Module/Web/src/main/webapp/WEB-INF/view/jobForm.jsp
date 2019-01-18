<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>

<head>
<title>My Jobs</title>

<link type="text/css" rel="stylesheet"
	href="/css/style.css">

<link type="text/css" rel="stylesheet"
	href="/css/add-customer-style.css">
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Job Portal</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Job</h3>
		<jsp:include page="nav.jsp" />
		<form:form action="saveJob" modelAttribute="job" method="POST">
			<form:hidden path="id" />

			<table>
				<tr>
					<td>Title:</td>
					<td><form:input path="title" /></td>
				</tr>
				<tr>
					<td>Description:</td>
					<td><form:textarea path="description" /></td>
				</tr>
				<tr>
					<td><form:hidden path="user.id" /></td>
					<td><input type="submit" value="Save" /></td>
				</tr>
			</table>
		</form:form>

		<div style=""></div>

	</div>

</body>

</html>










