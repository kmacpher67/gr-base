<%--
  Created by IntelliJ IDEA.
  User: 310237766
  Date: 5/15/2016
  Time: 9:22 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Image Viewer!!! haha</title>
</head>

<body>

<b>LISTING NAME: </b>
${imageList}<BR/>
<b>LISTING Size(): </b>
${imageList.size()}<BR/>
<HR/>
<g:each in="${imageList}" var="image">
    <g:if test="${image.contains('jpg')}">
        ${image}<BR/>
        <img src="${resource(dir: 'images', file: image)}" alt="Grails"/>
    </g:if>
</g:each>
<HR/>
</body>
</html>