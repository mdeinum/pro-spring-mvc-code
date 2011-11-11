<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<h3><spring:message code="menu.title" /></h3>
<ul>
	<li><a href="<spring:url value="/pizzas.htm" />"><spring:message code="menu.pizzas"/></a></li>
	<li><a href="<spring:url value="/accounts.htm" />"><spring:message code="menu.accounts" /></a></li>
</ul>