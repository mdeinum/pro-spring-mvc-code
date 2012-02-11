<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<title>Booksearch</title>
</head>
<body>
<h1><spring:message code="book.title" /></h1>

<p>

    <form:form method="POST" modelAttribute="searchCriteria">
        <fieldset>
            <legend><spring:message code="book.searchcriteria"/></legend>
            <table>
                <tr><td><form:label path="title"><spring:message code="book.title" /></form:label></td><td><form:input path="title" /></td></tr>
                <tr><td><form:label path="category"><spring:message code="book.category" /></form:label></td><td><form:select path="category" items="${categories}" itemValue="id" itemLabel="name"/></td></tr>
            </table>
        </fieldset>
        <button id="search"><spring:message code="button.search"/></button>
    </form:form>

    <c:if test="${not empty bookList}">
        <table>
            <tr><th><spring:message code="book.title"/></th><th><spring:message code="book.description"/></th><th><spring:message code="book.price" /></th></tr>
            <c:forEach items="${bookList}" var="book">
                <tr><td>${book.title}</td><td>${book.description}</td><td>${book.price}</td><td><a href="<c:url value="/cart/add/${book.id}"/>"><spring:message code="book.addtocart"/></a></tr>
            </c:forEach>
        </table>
    </c:if>
</p>

</body>
</html>