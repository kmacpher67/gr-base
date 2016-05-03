<html>
<head>
    <title>Facebook Feed</title>
    <meta name='layout' content='bootstrap'/>
</head>

<body>


<div class="container">
  <div class="row">
    <div class="span9">
      <div class="form-container">
        <g:render template="/springsocial/facebook/facebookMenu" model="['active':'feed']"/>

        <h3>Your Facebook Feed</h3>

        <g:form controller="springSocialFacebook" action="update">
            <p>Post to your Facebook wall:<p>
        		<textarea id="message" name="message" rows="2" cols="60"></textarea><br/>
        		<button type="submit" class="btn btn-lg btn-primary">Post</button>
        </g:form>

        <div class="feed">
            <ul class="list-unstyled">
                <g:each in="${feed}" var="post">

                  <li class="media">
                    <a class="pull-left" href="#">
                      <g:if test="${post.picture}">
                          <img src="${post.picture}" align="top"/>
                      </g:if>
                    </a>
                    <div class="media-body">
                      <g:if test="${post.message}">
                        ${post.message} -
                      </g:if>
                      ${post.name}
                    </div>
                  </li>

              </g:each>
          </ul>
      </div>
    </div>
  </div>
</div>
</div>


</body>
</html>
