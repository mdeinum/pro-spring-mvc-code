<!DOCTYPE HTML>
<!DOCTYPE HTML>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome to Pizzas R Us</title>
</head>
<body>
<header>
    <h1>Welcome to Pizzas-R-Us. Your online Pizza Store</h1>
    <h2>Our Pizzas</h2>
</header>
<nav>
    <h2>Menu</h2>
    <a href="<c:url value="/index.htm"/>">Home</a>
    <a href="<c:url value="/pizzas.htm"/>">Our Pizzas</a>
    <a href="<c:url value="/order.htm"/>">Place Order</a>
</nav>

<h1>Pizzas</h1>
<table>
    <tr><th>Name</th><th>Description</th><th>Price</th></tr>
    <c:forEach items="${pizzas}" var="pizza">
        <tr><td>${pizza.name}</td><td>${pizza.description}</td><td>${pizza.price}</td></tr>
    </c:forEach>
</table>
<footer>
    <small>(C) Pizzas-R-Us Ltd.</small>
</footer>
</body>




</html>
</body>