<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>

<head>
<title>Profile</title>

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
		<h3>Save Profile</h3>
		<jsp:include page="nav.jsp" />
	<form:form modelAttribute="profile" action="saveProfile">
		<form:hidden path="id" />
		<table>
			<tr>
				<td>Username:</td>
				<td><form:input path="username" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:input path="password" /></td>
			</tr>

			<tr>
				<td></td>
				<td><input type="submit" value="Save" /></td>
			</tr>
		</table>
	</form:form>

		<div style=""></div>

	</div>

</body>

</html>










