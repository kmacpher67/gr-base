<%@ page import="bootstrap.Person" %>
<bootstrap:textFieldLabel label="person.firstName.label" default="First Name" name="firstName" value="${person?.firstName}" required="required" readonly="${readonly}" />
<bootstrap:textFieldLabel label="person.lastName.label" default="Last Name" name="lastName" value="${person?.lastName}" required="required" readonly="${readonly}"/>
<bootstrap:emailFieldLabel label="person.email.label" default="E-mail" name="email" value="${person?.email}" readonly="${readonly}"/>
<bootstrap:dateFieldLabel label="person.dateOfBirth.label" default="Date Of Birth" name="dateOfBirth" value="${person?.dateOfBirth?.format('dd/MM/yyyy')}" readonly="${readonly}"/>
<bootstrap:selectLabel label="person.age.label" default="Age" name="age" value="${person?.age}" from="${Person.constrainedProperties.age.inList}" readonly="${readonly}"/>

<g:if test="${!readonly}">
    <bootstrap:submitButtonInForm class="success"><g:message code="default.button.save.label"/></bootstrap:submitButtonInForm>
</g:if>
