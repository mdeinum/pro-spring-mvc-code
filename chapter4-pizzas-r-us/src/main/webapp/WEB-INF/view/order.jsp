<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome to Pizzas R Us</title>
</head>
<body>
<header>
    <h1>Welcome to Pizzas-R-Us. Your online Pizza Store</h1>
    <h2>Place your Order</h2>
</header>
<nav>
    <h2>Menu</h2>
    <a href="<c:url value="/index.htm"/>">Home</a>
    <a href="<c:url value="/pizzas.htm"/>">Our Pizzas</a>
    <a href="<c:url value="/order.htm"/>">Place Order</a>
</nav>

<h1>Order</h1>
<form method="post" action="<c:url value="/order.htm"/>" id="orderForm">
    <fieldset>
        <legend>Personal</legend>
        <label for="name">Name</label> <input id="name" name="name" type="text" placeholder="John Doe"/><br/>
        <label for="address">Address</label> <input id="address" name="address" type="text" placeholder="Somewherelane 123"/><br/>
        <label for="postcode">Postal Code</label> <input id="postcode" name="postcode" type="text" placeholder="1234AA"/><br/>
        <label for="city">City</label> <input id="city" name="city" type="text" placeholder="New York"/><br/>
        <label for="country">Country</label> <input id="country" name="country" type="text" placeholder="US"/><br/>
    </fieldset>
    <fieldset>
        <legend>Payment</legend>
        <label for="creditcard">Credit Card</label> <input id="creditcard" name="creditcard" type="text" placeholder="1234 5678 1234 1234"/><br/>
    </fieldset>
    <label for="comment">Comment</label>
    <textarea id="comment" name="comment" placeholder="Place your comment"></textarea>
    <br/>
    <input type="submit" value="Order!" />
</form>
<footer>
    <small>(C) Pizzas-R-Us Ltd.</small>
</footer>
</body>
</html>