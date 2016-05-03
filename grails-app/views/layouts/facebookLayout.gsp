<!DOCTYPE html>
<html>
<head>
    <title>Grails Spring Social Facebook Plugin - <g:layoutTitle default="Grails"/></title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'socialplugins.css', plugin: 'spring-social-facebook')}"/>
    <g:layoutHead/>
</head>

<body>

<div id="container">
      <div id="fb-root"></div>
      <div id="main" role="main" class="clearfix">
              <hr class="space"/>

              <div id="leftNav" class="span-6 colspace">
                      <g:render template="/springsocial/facebook/facebookMenu"/>
              </div>
     
              <div id="content" class="span-16">
                      <g:layoutBody/>
              </div>

      </div>
</div>   
</body>
</html>    




