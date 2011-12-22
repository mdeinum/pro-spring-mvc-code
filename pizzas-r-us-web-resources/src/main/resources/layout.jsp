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
<header>
    <img src="<c:url value="/images/slice_of_pizza.jpg"/>" align="right" alt="Slice of Pizza" height="200"/>
    <h1>Welcome to Pizzas-R-Us. Your online Pizza Store</h1>
    <h2><decorator:title /></h2>
    <nav>
        <li class="btn_1"><a href="<c:url value="/index.htm"/>">Home</a></li>
        <li class="line"></li>
        <li class="btn_4"><a href="<c:url value="/pizzas.htm"/>">Our Pizzas</a></li>
        <li class="line"></li>
        <li class="btn_4"><a href="<c:url value="/order.htm"/>">Place Order</a></li>
        </ul>
    </nav>

    <img src="images/logo.jpg" width="293" height="80" alt="" id="logo" />

</header>

<div id="container">

<div id="incontainer">

    <div id="content">
        <decorator:body />
    </div>
</div>
</div>
<footer>
    <p><a href="#">HOME PAGE</a> | <a href="#">ABOUT US</a> | <a href="#">HOME DLIVERY</a> | <a href="#">CONTACTS</a><br/>Copyright &copy; Pizza-R-Us</p>
</footer>
</body>
</html>
