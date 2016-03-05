<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'person.label', default: 'Person')}" scope="request"/>
    <g:set var="entityNamePlural" value="${message(code: 'person.plural.label', default: 'People')}" scope="request"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
    <bootstrap:submenuCreate />

    <bootstrap:messages bean="${this.person}" />

    <g:form resource="${this.person}" method="POST" class="form-horizontal">
        <g:render template="form" />
    </g:form>
</body>
</html>
