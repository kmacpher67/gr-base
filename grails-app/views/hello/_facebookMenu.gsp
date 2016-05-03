<ul class="nav nav-pills">
  <li <g:if test="${active == 'index'}">class="active"</g:if>><g:link controller="springSocialFacebook">User Profile</g:link></li>
  <li <g:if test="${active == 'feed'}">class="active"</g:if>><g:link controller="springSocialFacebook" action="feed">Feed</g:link></li>
  <li <g:if test="${active == 'friends'}">class="active"</g:if>><g:link controller="springSocialFacebook" action="friends" >Friends</g:link></li>
  <li <g:if test="${active == 'albums'}">class="active"</g:if>><g:link controller="springSocialFacebook" action="albums">Photo Albums</g:link></li>
  <li>
    <form class="form-inline" method="POST" action="${request.contextPath}/logout">
      <button class="btn"><i class="fa fa-sign-out"></i> Logout</button>
    </form>
  </li>
</ul>
