<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'person.label', default: 'Person')}" scope="request"/>
    <g:set var="entityNamePlural" value="${message(code: 'person.plural.label', default: 'People')}" scope="request"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>
<body>
    <bootstrap:submenuEdit/>

    <bootstrap:messages bean="${this.person}" />

    <g:form resource="${this.person}" method="PUT" class="form-horizontal">
        <g:hiddenField name="version" value="${this.person?.version}"/>
        <g:render template="form" />
    </g:form>
</div>
</body>
</html>
