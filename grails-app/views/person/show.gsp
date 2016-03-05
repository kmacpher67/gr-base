<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'person.label', default: 'Person')}" scope="request" />
        <g:set var="entityNamePlural" value="${message(code: 'person.plural.label', default: 'People')}" scope="request"/>
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <bootstrap:submenuShow/>

        <bootstrap:messages bean="${this.person}" />

        <g:form resource="${this.person}" method="DELETE" class="form-horizontal">
            <g:render template="form" model="[readonly:'readonly']" />

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <bootstrap:linkAsButton action="edit" id="${person.id}" icon="pencil"><g:message code="default.button.edit.label" default="Edit" /></bootstrap:linkAsButton>
                    <bootstrap:confirmation class="danger" yesClass="danger" onconfirm="\$(form).submit()" icon="trash-o" buttonText="${g.message(code: 'default.button.delete.label', default: 'Delete')}" styleId="modalDelete"><g:message code="default.button.delete.confirm.message" default="Are you sure?"/></bootstrap:confirmation>
                </div>
            </div>
        </g:form>
    </body>
</html>
