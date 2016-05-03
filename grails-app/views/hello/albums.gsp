<html>
<head>
    <title>Facebook Albums</title>
    <meta name='layout' content='bootstrap'/>
</head>

<body>


<div class="container">
  <div class="row">
    <div class="span9">
      <div class="form-container">
        <g:render template="/springsocial/facebook/facebookMenu" model="['active':'albums']"/>
        <h3>Your Facebook Photo Albums</h3>
        <ul class="list-group">
          <g:each in="${albums}" var="album">
            <li class="list-group-item"><g:link action="album" controller="springSocialFacebook" id="${album.id}">${album.name}</g:link></li>
          </g:each>
        </ul>
    </div>
  </div>
</div>
</div>

</body>
</html>
