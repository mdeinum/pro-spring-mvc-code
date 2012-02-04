<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form method="POST" action="<c:url value="/book"/>">
    <fieldset>
        <legend>Search Criteria</legend>
        <table>
            <tr><td><label for="title">Title</label></td><td><input id="title" name="title" /></td></tr>
        </table>
    </fieldset>
    <button id="search">Search</button>
</form>

<c:if test="${not empty bookList}">
    <table>
        <tr><th>Title</th><th>Description</th><th>Price</th></tr>
        <c:forEach items="${bookList}" var="book">
            <tr>
                <td>${book.title}</td>
                <td>${book.description}</td>
                <td>${book.price}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>