<!doctype html>
<html>
<head>
	<title>Bootstrap Framework</title>
	<meta name="layout" content="main">
</head>
<body>
<div class="container theme-showcase" role="main">

	<span>
	<sec:ifLoggedIn>
	Logged in as <sec:username/>(<g:link elementId='logout' controller='logoff'>Logoff</g:link>)
	</sec:ifLoggedIn>

	<sec:ifNotLoggedIn>
		<g:link controller='login' action='auth'>Login</g:link>
	</sec:ifNotLoggedIn>
	</span>
</div>
</body>
</html>

