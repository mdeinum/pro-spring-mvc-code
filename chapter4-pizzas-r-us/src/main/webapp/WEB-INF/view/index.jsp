<!DOCTYPE HTML>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<title>Welcome to Pizzas R Us</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/pizzas.css"/>" >
</head>
<body>
    <header>
        <img src="<c:url value="/images/slice_of_pizza.jpg"/>" align="right" alt="Slice of Pizza" height="200"/>    
        <h1>Welcome to Pizzas-R-Us. Your online Pizza Store</h1>
        <h2>Home</h2>
    </header>
    <nav>
        <a href="<c:url value="/index.htm"/>">Home</a>
        <a href="<c:url value="/pizzas.htm"/>">Our Pizzas</a> 
        <a href="<c:url value="/order.htm"/>">Place Order</a>
    </nav>
    <p>Welcome to Pizzas-R-us.
    <footer>
        <small>(C) Pizzas-R-Us Ltd.</small>
    </footer>
</body>
</html>