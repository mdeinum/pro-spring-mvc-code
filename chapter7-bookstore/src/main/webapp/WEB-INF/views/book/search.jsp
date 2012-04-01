<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form method="GET" modelAttribute="bookSearchCriteria" id="bookSearchForm">
    <fieldset>
        <legend><spring:message code="book.searchcriteria"/></legend>
        <table>
            <tr>
                <td><form:label path="title"><spring:message code="book.title" /></form:label></td>
                <td><form:input path="title" /></td>
            </tr>
            <tr>
                <td><form:label path="category"><spring:message code="book.category" /></form:label></td>
                <td><form:select path="category" items="${categories}" itemValue="id" itemLabel="name"/></td></tr>
        </table>
    </fieldset>
    <button id="search"><spring:message code="button.search"/></button>
</form:form>
<script>
$('#bookSearchForm').submit(function(evt){
	evt.preventDefault();
	var title = $('#title').val();
	var category = $('#category').val();
	var json = { "title" : title, "category" : { "id" : category}};
	
	$.ajax({
		url: $('#bookSearchForm').action,
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		data: JSON.stringify(json),
		success: function(books) {
			var content = '';
			var baseDetailUrl = '<c:url value="/book/detail/"/>';
			var baseAddCartUrl = '<c:url value="/cart/add/" />';
			for (var i = 0; i<books.length; i++) {
				content += '<tr>';
				content += '<td><a href="'+ baseDetailUrl + books[i].id+'">'+books[i].title+'</a></td>';
				content += '<td>'+books[i].description+'</td>';
                content += '<td>'+books[i].price+'</td>';
                content += '<td><a href="'+ baseAddCartUrl +books[i].id+'"><spring:message code="book.addtocart"/></a></td></tr>';
			}
			$('#bookSearchResults tbody').html(content);
		}
	});	
});
</script>
<c:if test="${not empty bookList}">
    <table id="bookSearchResults">
        <thead>
        <tr>
            <th><spring:message code="book.title"/></th>
            <th><spring:message code="book.description"/></th>
            <th><spring:message code="book.price" /></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${bookList}" var="book">
            <tr>
                <td><a href="<c:url value="/book/detail/${book.id}"/>">${book.title}</a></td>
                <td>${book.description}</td>
                <td>${book.price}</td>
                <td><a href="<c:url value="/cart/add/${book.id}"/>"><spring:message code="book.addtocart"/></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>