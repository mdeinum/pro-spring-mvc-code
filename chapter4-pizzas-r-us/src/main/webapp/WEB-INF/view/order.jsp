<!DOCTYPE HTML>
<html>
<head>
<title>Place Order</title>
</head>
<body>
    <h1>Order</h1>
    <form method="post" id="orderForm">
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
</body>
</html>