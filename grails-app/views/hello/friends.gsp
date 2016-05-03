<html>
<head>
    <title>Facebook Friends</title>
    <meta name='layout' content='bootstrap'/>
</head>

<body>


<div class="container">
  <div class="row">
    <div class="span9">
      <div class="form-container">
        <g:render template="/springsocial/facebook/facebookMenu" model="['active':'friends']"/>
        <h3>Your Facebook Friends</h3>
        <ul class="list-unstyled">
          <g:each in="${friends}" var="friend">
            <li class="media">
              <a class="pull-left" href="#">
                <img src="http://graph.facebook.com/${friend.id}/picture" align="middle"/>
              </a>
              <div class="media-body">
                ${friend.name}
              </div>
            </li>
          </g:each>
        </ul>
    </div>
  </div>
</div>
</div>


</body>
</html>
