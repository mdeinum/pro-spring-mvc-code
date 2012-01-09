<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<head>
<title><spring:message code="home.title"/></title>
</head>
<body>
<h1>Pizzas</h1>

<div id="menu">
	<jsp:include page="menu.jsp" />
</div>
<table>
	<thead>
		<tr><th><spring:message code="pizzas.table.name" /></th><th><spring:message code="pizzas.table.description" /></th><th><spring:message code="pizzas.table.price" /></th></tr>
	</thead>
	<tbody>
		<c:forEach items="${pizzas}" var="pizza">
			<tr><td>${pizza.name}</td><td>${pizza.description}</td><td>${pizza.price}</td><tr>
		</c:forEach>
	</tbody>	
</table>
</body>