<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'person.label', default: 'Person')}" scope="request" />
        <g:set var="entityNamePlural" value="${message(code: 'person.plural.label', default: 'People')}" scope="request"/>
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>

	<div>
	  <facebookAuth:connect />
            <sec:ifAllGranted roles="ROLE_FACEBOOK">
                Welcome! <sec:username/>

                <g:javascript> <!-- for client-side authentication -->
                function doLogout() {
                    if (typeof(FB) === 'object') {
                        FB.logout(function() {
                            window.location.href = "${createLink(controller: 'logout', action: 'index')}";
                        });
                        return false;
                    }
                    return true;
                }
                </g:javascript>


                <h2>Details</h2>

                <table>
                    <tr>
                        <td>Username:</td>
                        <td><sec:loggedInUserInfo field="username"/></td>
                    </tr>
                    <tr>
                        <td>Roles:</td>
                        <td><sec:loggedInUserInfo field="authorities"/></td>
                    </tr>
                    <tr>
                        <td>Full name:</td>
                        <td>${user?.name?.encodeAsHTML()}</td>
                    </tr>
                    <tr>
                        <td>E-Mail:</td>
                        <td>${user?.email?.encodeAsHTML()}</td>
                    </tr>
                </table>

                <h2>Logout</h2>
                <g:link controller="logout" action="index">Logout</g:link>

                <h2>More actions:</h2>
                <div class="actions">
                    <ul>
                    <li><g:link controller="testing" action="expireToken">Expire Facebook access_token</g:link> - to test
                    how plugin is going to reload it from facebook</li>
                    </ul>
                </div>
            </sec:ifAllGranted>
	</div>
	    </body>
</html>