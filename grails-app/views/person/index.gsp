<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'person.label', default: 'Person')}" scope="request" />
		<g:set var="entityNamePlural" value="${message(code: 'person.plural.label', default: 'People')}" scope="request"/>
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
		<bootstrap:submenuList />

		<bootstrap:messages />

		<bootstrap:table>
			<thead>
				<tr>
					<th><g:message code="person.name.label" default="Name" /></th>
					<th><g:message code="person.email.label" default="E-mail" /></th>
					<th><g:message code="person.dateOfBirth.label" default="Date Of Birth" /></th>
					<th><g:message code="person.age.label" default="Age" /></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${personList}" var="person">
					<tr>
						<td><g:link action="show" id="${person.id}">${person.firstName} ${person.lastName}</g:link></td>
						<td>${person.email}</td>
						<td><g:formatDate type="date" style="MEDIUM" date="${person.dateOfBirth}" /></td>
						<td><g:formatNumber type="number" number="${person.age}" /></td>
						<td class="actions">
							<bootstrap:dialog class="primary btn-xs" styleId="dialog-show-${person.id}" icon="search" title="${message(code: 'default.show.label', args: [entityName], default: 'Show {0}')}" >
								<div class="form-horizontal">
									<g:render template="form" model="[readonly: 'readonly', person: person]"/>
								</div>
							</bootstrap:dialog>

							<bootstrap:modal styleId="dialog-delete-${person.id}" icon="trash" class="danger btn-xs">
								<g:form resource="${person}" method="DELETE" class="form-horizontal">
                                    <bootstrap:modalHeader><g:message code="default.button.delete.label"/></bootstrap:modalHeader>

                                    <bootstrap:modalBody>
                                        <g:message code="default.button.delete.confirm.message"/>
                                        <g:render template="form" model="[readonly: 'readonly', person: person]"/>
                                    </bootstrap:modalBody>

                                    <bootstrap:modalFooter>
                                        <bootstrap:button data-dismiss="modal"><g:message code="default.boolean.no" default="No" /></bootstrap:button>
                                        <bootstrap:submitButton class="danger" icon="none"><g:message code="default.boolean.yes" default="Yes"/></bootstrap:submitButton>
                                    </bootstrap:modalFooter>
								</g:form>
							</bootstrap:modal>
						</td>
					</tr>
				</g:each>
			</tbody>
		</bootstrap:table>

		<div class="pagination">
			<bootstrap:paginate total="${personCount ?: 0}" />
		</div>
    </body>
</html>