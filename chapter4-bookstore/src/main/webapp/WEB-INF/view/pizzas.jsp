<!DOCTYPE HTML>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Our Pizzas</title>
</head>
<body>
    <h1>Pizzas</h1>
    <table>
        <tr><th>Name</th><th>Description</th><th>Price</th></tr>
        <c:forEach items="${pizzaList}" var="pizza">
            <tr><td>${pizza.name}</td><td>${pizza.description}</td><td>${pizza.price}</td></tr>
        </c:forEach>
    </table>
</body>
</html>