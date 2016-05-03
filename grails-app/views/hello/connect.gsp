<html>
<head>
    <title>Facebook Connect</title>
    <meta name='layout' content='bootstrap'/>
</head>

<body>


<div class="container">
  <div class="row">
    <div class="span9">

      <g:form class="form-container" method="POST" mapping="springSocialConnect" params="[providerId:'facebook']">
        <h2>Connect with Facebook</h2>
        <input type="hidden" name="scope" value="${grails.util.Holders.getConfig().plugin.springSocialCore.providers.facebook.fields.scope}" />
        <div class="formInfo">
          <h6>You aren't connected to Facebook yet.</h6>
          <p> Click the button to connect with your Facebook account.</p>
        </div>
        <button class="btn btn-lg btn-primary"><i class="fa fa-facebook"></i> Login</button>
      </g:form>

    </div>
	<div> class="span2">
		this is the taglib supposedly
		<facebookAuth:connect />
	</div>
  </div>

</div> <!-- /container -->

</body>
</html>
