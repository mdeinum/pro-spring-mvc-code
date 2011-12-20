<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<html>
<head>
<meta charset="utf-8">
<title>Welcome to Pizzas R Us | <decorator:title /></title>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/pizzas.css"/>" >
<decorator:head />
</head>
<body>
    <header>
        <img src="<c:url value="/images/slice_of_pizza.jpg"/>" align="right" alt="Slice of Pizza" height="200"/>    
        <h1>Welcome to Pizzas-R-Us. Your online Pizza Store</h1>
        <h2><decorator:title /></h2>
    </header>
    <nav>
        <a href="<c:url value="/index.htm"/>">Home</a>
        <a href="<c:url value="/pizzas.htm"/>">Our Pizzas</a> 
        <a href="<c:url value="/order.htm"/>">Place Order</a>
    </nav>
    <div id="content">
        <decorator:body />
    </div>
    <footer>
        <small>(C) Pizzas-R-Us Ltd.</small>
    </footer>
</body>
</html>
