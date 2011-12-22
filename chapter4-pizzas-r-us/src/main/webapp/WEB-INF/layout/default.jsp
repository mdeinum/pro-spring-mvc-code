<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Welcome to Pizzas R Us | <decorator:title /></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>" >
    <decorator:head />
</head>
<body>
<div id="container">

    <div id="incontainer">
<header>
    <nav>
        <li><a href="<c:url value="/index.htm"/>">Home</a></li>
        <li class="line"></li>
        <li><a href="<c:url value="/pizzas.htm"/>">Our Pizzas</a><li>
        <li class="line"></li>
        <li><a href="<c:url value="/order.htm"/>">Place Order</a></li>
    </nav>

    <img src="<c:url value="/images/logo.jpg"/>" width="293" height="80" alt="" id="logo" />

</header>


        <div id="content">
            <decorator:body />
        </div>
    <footer>
        <p><a href="#">HOME PAGE</a> | <a href="#">ABOUT US</a> | <a href="#">HOME DLIVERY</a> | <a href="#">CONTACTS</a><br/>Copyright &copy; Pizza-R-Us</p>
    </footer>
    </div>
</div>
</body>
</html>
