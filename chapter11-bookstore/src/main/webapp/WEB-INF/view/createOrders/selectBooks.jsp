<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<form:form modelAttribute="orderForm" action="${flowExecutionUrl}">
		<table style="width: 100%">
			<tr>
				<td colspan="2">
					<form:errors path="books" cssClass="error"/>
				</td>
			</tr>
 			<tr style="height: 10px;"/>
			<tr>
				<td><spring:message code="label.page.books.select.book" /></td>
				<td>
					<form:select path="book" items="${selectableBooks}" itemLabel="title" itemValue="id"/>
				</td>
			</tr>
			<tr>
				<td><spring:message code="label.page.books.select.quantity"/></td>
				<td>
					<form:input path="quantity" />
					<span style="margin-left: 5px">
						<form:errors path="quantity" cssClass="error"/>
					</span>
				</td>
			</tr>
			<tr height="10px"/>
			<tr align="right">
				<td colspan="2">
					<button type="submit" id="add" name="_eventId_add"><spring:message code="label.page.books.add.book"/></button>	
					<button type="submit" id="cancel" name="_eventId_reset"><spring:message code="button.reset"/></button>	
				</td>
			</tr>
		</table>
		
		<p />
			<h3><spring:message code="label.page.books.selected.books"/></h3>
			<div style="margin-top: 10px; margin-bottom: 10px;">
			<table style="width: 100%;" rules="groups">
				<thead>
					<tr>
						<th width="80%" align="left"><spring:message code="label.page.books.book.name"/></th>
						<th width="20%" align="left"><spring:message code="label.page.books.book.quantity"/></th>
					</tr>
				</thead>
				<tbody>
				<tr height="10px"/>
			<c:forEach items="${orderForm.books}" var="book">
				<tr>
					<td>${book.key.title}</td>
					<td>${book.value}</td>
				</tr>
			</c:forEach>
			<tr height="20px"/>
			</tbody>
		</table>
		</div>
	
	
		<div align="right" style="margin-bottom: 20px;" >
			<button type="submit" id="previous" name="_eventId_previous"><spring:message code="button.previous"/></button>
			<button type="submit" id="previous" name="_eventId_cancel"><spring:message code="button.cancel"/></button>
			<button type="submit" id="next" name="_eventId_next"><spring:message code="button.next"/></button>
		</div>
	</form:form>
