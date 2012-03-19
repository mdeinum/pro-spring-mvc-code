<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	
	 <spring:url value="/authenticate.htm" var="authenticate" />
	<form:form modelAttribute="authenticationForm" action="${flowExecutionUrl != null ? flowExecutionUrl  : 'authenticate.htm'}" method="POST">
		<table style="width: 100%">
			<tr>
				<td><spring:message code="label.page.login.username" /></td>
				<td><form:input  path="username" disabled="false" /><form:errors cssClass="error" path="username"/></td>
				<td><spring:message code="label.page.login.password" /></td>
				<td><form:password showPassword="false" path="password" /> <form:errors cssClass="error" path="password"/></td>
			</tr>
			<tr>
				<td colspan="4">
					<form:errors cssClass="error"/>
				</td>
			</tr>
		</table>
		
		<div align="right" style="margin-bottom: 20px; margin-top: 10px" >
			<button type="submit" id="previous" name="_eventId_previous"><spring:message code="label.page.login.previous"/></button>
			<button type="submit" id="login" name="_eventId_authenticate"><spring:message code="label.page.login.login"/></button>
		</div>
	</form:form>