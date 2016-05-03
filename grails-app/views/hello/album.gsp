<html>
<head>
    <title>Facebook Album</title>
    <meta name='layout' content='facebookLayout'/>
</head>

<body>

	<h3>Your Facebook Photo Album: ${album.name}</h3>


	<g:each in="${photos}" var="photo">
		<img src="${photo.albumImage.source}" align="middle"/>
	</g:each>


</body>
</html>
