<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>

<head>
<title>Job details</title>


<link type="text/css" rel="stylesheet"
	href="/css/style.css" />

</head>

<body>
	<jsp:include page="nav.jsp" />
	<div id="wrapper">
		<div id="header">
			<h2>Job Portal</h2>
		</div>
	</div>

	<div id="container">
		<c:if test="${hasAlreadyApplied}"><p style="color:red;">You have already applied for this job.</p></c:if>
		<div id="content">
			<input type="button" value="Apply"
				onclick="window.location.href='application?jobId=${job.id}'; return false;"
				class="add-button" />
			<h3>${job.title}</h3>

			<p>${job.description}</p>
			<label class="left">Published by:${job.user.username}</label><br />
			<label class="left">Published date:
				${job.publishedDate.toString().split(" ")[0]}</label>
			<h3>Applicants</h3>
			<ul>
				<c:forEach var="applicant" items="${job.applications}">
					<li>${applicant.user.username}</li>
				</c:forEach>
			</ul>
		</div>

	</div>


</body>

</html>









