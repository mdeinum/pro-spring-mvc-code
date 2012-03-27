<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form method="POST" modelAttribute="order">
    <div class="clear">
    <fieldset>
        <legend><spring:message code="cart.account.information"/></legend>
        <table>
            <tr><td><spring:message code="account.firstname" /></td><td>${order.account.firstName}</td></tr>
            <tr><td><spring:message code="account.lastname" /></td><td>${order.account.lastName}</td></tr>
        </table>
    </fieldset>
    <div class="clear">
        <div style="float: left;">
        <fieldset>
            <legend><spring:message code="cart.checkout.shipping"/></legend>
                <table>
                    <tr><td><form:label path="shippingAddress.street" cssErrorClass="error"><spring:message code="address.street" /></form:label></td><td><form:input path="shippingAddress.street" /></td><td><form:errors path="shippingAddress.street"/></td></tr>
                    <tr><td><form:label path="shippingAddress.houseNumber" cssErrorClass="error"><spring:message code="address.houseNumber" /></form:label></td><td><form:input path="shippingAddress.houseNumber" /></td><td><form:errors path="shippingAddress.houseNumber"/></td></tr>
                    <tr><td><form:label path="shippingAddress.boxNumber" cssErrorClass="error"><spring:message code="address.boxNumber" /></form:label></td><td><form:input path="shippingAddress.boxNumber" /></td><td><form:errors path="shippingAddress.boxNumber"/></td></tr>
                    <tr><td><form:label path="shippingAddress.city" cssErrorClass="error"><spring:message code="address.city" /></form:label></td><td><form:input path="shippingAddress.city" /></td><td><form:errors path="shippingAddress.city"/></td></tr>
                    <tr><td><form:label path="shippingAddress.postalCode" cssErrorClass="error"><spring:message code="address.postalCode" /></form:label></td><td><form:input path="shippingAddress.postalCode" /></td><td><form:errors path="shippingAddress.postalCode"/></td></tr>
                    <tr><td><form:label path="shippingAddress.country" cssErrorClass="error"><spring:message code="address.country" /></form:label></td><td><form:select path="shippingAddress.country" items="${countries}"/></td><td><form:errors path="shippingAddress.country"/></td></tr>
                </table>
        </fieldset>
        </div>
        <div>
        <fieldset>
            <legend><spring:message code="cart.checkout.billing"/></legend>
                <div><form:label path="billingSameAsShipping"><spring:message code="cart.checkout.billingSameAsShipping"/></form:label><form:checkbox path="billingSameAsShipping" /></div>
                
                <table style="display: ${order.billingSameAsShipping ? "none;" : "block;"}">
                    <tr><td><form:label path="billingAddress.street" cssErrorClass="error"><spring:message code="address.street" /></form:label></td><td><form:input path="billingAddress.street" disabled="${order.billingSameAsShipping}"/></td><td><form:errors path="billingAddress.street"/></td></tr>
                    <tr><td><form:label path="billingAddress.houseNumber" cssErrorClass="error"><spring:message code="address.houseNumber" /></form:label></td><td><form:input path="billingAddress.houseNumber" disabled="${order.billingSameAsShipping}"/></td><td><form:errors path="billingAddress.houseNumber"/></td></tr>
                    <tr><td><form:label path="billingAddress.boxNumber" cssErrorClass="error"><spring:message code="address.boxNumber" /></form:label></td><td><form:input path="billingAddress.boxNumber" disabled="${order.billingSameAsShipping}"/></td><td><form:errors path="billingAddress.boxNumber"/></td></tr>
                    <tr><td><form:label path="billingAddress.city" cssErrorClass="error"><spring:message code="address.city" /></form:label></td><td><form:input path="billingAddress.city" disabled="${order.billingSameAsShipping}"/></td><td><form:errors path="billingAddress.city"/></td></tr>
                    <tr><td><form:label path="billingAddress.postalCode" cssErrorClass="error"><spring:message code="address.postalCode" /></form:label></td><td><form:input path="billingAddress.postalCode" disabled="${order.billingSameAsShipping}"/></td><td><form:errors path="billingAddress.postalCode"/></td></tr>
                    <tr><td><form:label path="billingAddress.country" cssErrorClass="error"><spring:message code="address.country" /></form:label></td><td><form:select path="billingAddress.country" items="${countries}" disabled="${order.billingSameAsShipping}"/></td><td><form:errors path="billingAddress.country"/></td></tr>        
                </table>    
        </fieldset>
        </div>
    </div>
    <div class="clear">
        <fieldset>
            <legend><spring:message code="cart.checkout.order"/></legend>
            <table>
                <thead>
                    <tr><th><spring:message code="book.title"/></th><th><spring:message code="order.quantity"/></th><th><spring:message code="book.price"/></th></tr>
                </thead>
                <tbody>
                    <c:forEach items="${order.orderDetails}" var="detail" varStatus="status">
                            <tr><td>${detail.book.title}</td><td><form:input path="orderDetails[${status.index}].quantity" size="2"/></td><td>${detail.price}</td></tr>   
                    </c:forEach>
                    <tr><b><td colspan="2" align="right"><spring:message code="order.total"/></b></td><td>${order.totalOrderPrice}</td></tr>
                </tbody>
            </table>
        </fieldset>
    </div>
    </div>
    
    <div></div>
    <button id="order" name="order"><spring:message code="button.order"/></button>
    <button id="update" name="update"><spring:message code="button.update"/></button>
</form:form>