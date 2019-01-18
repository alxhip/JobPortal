<style type="text/css">
nav {
	position: fixed;
	z-index: 9999;
	top: 0;
	left: 0;
	width: 100%;
	height: 35px;
}

nav ul {
	list-style: none;
	margin: 0;
	padding: .2em 2em;
	float: right;
}

nav ul li {
	display: inline-block;
	margin: 0;
	padding: .2em .7em;
}

nav a {
	width: 100%;
	height: 100%;
	color: black;
	text-decoration: none;
	font-family: Ubuntu;
	font-size: 1.15em;
	font-weight: lighter;
	letter-spacing: 1px;
	transition: .25s ease-in-out;
}

nav a:hover {
	color: rgb(220, 120, 0);
}

.nav-bg {
	content: '';
	position: absolute;
	display: block;
	top: -100%;
	width: 100%;
	height: 100%;
	z-index: -1;
	background: rgb(50, 50, 50);
	transition: .45s ease-in-out;
}

.bg-hidden {
	top: -100%;
	opacity: 0;
}

.bg-visible {
	top: 0;
	opacity: 1;
}
</style>
<header>
	<nav>
		<div class="nav-bg"></div>
		<ul>
			<li><a href="${pageContext.request.contextPath}/job/showHome">Home</a></li>
			<li><a href="${pageContext.request.contextPath}/job/showProfile">Profile</a></li>
			<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
		</ul>
	</nav>
</header>