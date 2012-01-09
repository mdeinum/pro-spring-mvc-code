<!DOCTYPE HTML>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Place Order</title>
</head>
<body>
    <form:form method="post" id="orderForm" modelAttribute="newOrder">
        <h1 style="{padding-left: 12px;}">Order</h1>
        <div id="leftPan">
        <fieldset>
            <legend>Personal</legend>
            <form:label path="name" cssErrorClass="error">Name</form:label> <form:input path="name" placeholder="John Doe"/> <form:errors path="name" cssClass="error"/> <br/>
            <form:label path="address" cssErrorClass="error">Address</form:label> <form:input path="address" placeholder="Somewherelane 123"/> <form:errors path="address" cssClass="error"/><br/>
            <form:label path="postcode" cssErrorClass="error">Postal Code</form:label> <form:input path="postcode" placeholder="1234AA" maxlength="8" size="8"/><br/>
            <form:label path="city">City</form:label> <form:input path="city" type="text" placeholder="New York"/> <form:errors path="city" cssClass="error"/><br/>
            <form:label path="country" cssErrorClass="error">Country</form:label> <form:select path="country" items="${countries}" /> <form:errors path="country" cssClass="error"/> <br/>
        </fieldset>
        </div>
        <div id="rightPan">
        <fieldset>
            <legend>Payment</legend>
            <form:label path="creditcard"  cssErrorClass="error">Credit Card</form:label>  <form:input path="creditcard" name="creditcard" placeholder="1234 5678 1234 1234"/> <form:errors path="creditcard" cssClass="error"/><br/>
        </fieldset>
        <br/>
        <fieldset>
            <legend>Comment</legend>
            <form:textarea path="comment" placeholder="Place your comment" cols="42"/>
        </fieldset>
        <form:errors path="lines" cssClass="error"/>
        <c:if test="${not empty newOrder.lines}">
            <fieldset>
                <legend>Pizzas</legend>
                <table>
                    <tr><th>Pizza</th><th>Price</th><th>Quantity</th><th>Total</th></tr>
                    <c:forEach items="${newOrder.lines}" var="line" varStatus="status">
                        <tr>
                            <td><form:select path="lines['${status.index}'].pizza" items="${pizzaList}" itemValue="id" itemLabel="name" /></td>
                            <td>${line.pizza.price}</td>
                            <td><form:input path="lines['${status.index}'].quantity" size="3" type="number"/></td>
                            <td align="right">${line.total}</td>
                        </tr>
                    </c:forEach>
                        <tr><td colspan="3" align="right"><b>Order Total:</b></td><td align="right">${newOrder.total}</td></tr>
                </table>
            </fieldset>
        </c:if>
        </div>
        <br/>
        <form:button value="order">Order!</form:button>
        <form:button value="add-line" id="add-line" name="add-line">Add Pizza</form:button>
        <form:button value="calculate" id="calculate" name="calculate">Calculate</form:button>
    </form:form>
</body>
</html>