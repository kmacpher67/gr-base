<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title><g:layoutTitle default="${meta(name: 'app.name')}"/></title>
    <asset:javascript src="application"/>
    <asset:stylesheet src="application"/>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link rel="shortcut icon" href="${asset.image(src: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${asset.image(src: 'images', file: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${asset.image(src: 'images', file: 'apple-touch-icon-retina.png')}">
    <g:layoutHead/>
</head>

<body role="document">
    <bootstrap:mainMenu />
	<br/>
	<br/>
    <div class="container-fluid">
        <g:layoutBody/>
    </div>
	<div class="footer">
		<a href="/privacy.html"  target="_blank" />Privacy Policy Details</a>
	</div>
</body>
</html>
