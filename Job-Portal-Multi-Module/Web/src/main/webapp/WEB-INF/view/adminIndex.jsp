<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>

<html>

<head>
<title>My Jobs</title>

<link type="text/css" rel="stylesheet"
	href="/css/style.css" />

</head>

<body>
	<jsp:include page="adminNav.jsp" />
	<div id="wrapper">
		<div id="header">
			<h2>Job Portal</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">
			<form:form action="searchJob" method="POST">
                Search jobs: <input type="text" name="input" />
                <input type="submit" value="Search" class="add-button" />
            </form:form>
			<table>
				<tr>
					<th>Title</th>
					<th>Published by</th>
					<th>Published date</th>
					<th>Actions</th>
				</tr>
				<c:forEach var="tempJob" items="${jobs}">
					<c:url var="updateLink" value="/job/jobForm">
						<c:param name="jobId" value="${tempJob.id}" />
					</c:url>
					<c:url var="deleteLink" value="/job/deleteJob">
						<c:param name="jobId" value="${tempJob.id}"></c:param>
					</c:url>
					<c:url var="detailedViewLink" value="/job/detailedJobView">
						<c:param name="jobId" value="${tempJob.id}" />
					</c:url>
					<tr>

						<td><a href="${detailedViewLink}">${tempJob.title}</a></td>
						<td>${tempJob.user.username}</td>
						<td>${tempJob.publishedDate.toString().split(" ")[0]}</td>
						<td><a href="${updateLink}">Update | </a> <a
							href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this job ?'))) return false">Delete</a></td>
					</tr>
				</c:forEach>

			</table>
		</div>

	</div>


</body>

</html>









