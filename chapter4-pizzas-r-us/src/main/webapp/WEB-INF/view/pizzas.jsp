<!DOCTYPE HTML>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>Welcome to Pizzas R Us</title>
<head>
</head>
<body>
<h1>Pizzas</h1>
<table>
    <tr><th>Name</th><th>Description</th><th>Price</th></tr>
    <c:forEach items="${pizzas}" var="pizza">
        <tr><td>${pizza.name}</td><td>${pizza.description}</td><td>${pizza.price}</td></tr>
    </c:forEach>
</table>
</body>