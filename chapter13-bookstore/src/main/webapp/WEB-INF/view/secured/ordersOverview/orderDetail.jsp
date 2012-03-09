<%@ page import="java.util.Enumeration"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<h2>Order detail</h2>
<div style="width: 550px; height: 100%; margin-top: 15px;">
	<table style="width: 100%;" rules="groups">
		<thead>
			<tr>
				<th align="left">Book titile</th>
				<th align="left">Book descriptipon</th>
				<th width="60px" align="left">Book price</th>
			</tr>
		</thead>
		<tbody>
			<tr height="10px" />
			<c:forEach items="${order.orderDetails}" var="orderDetail" varStatus="status">
				<tr>
					<td>${orderDetail.book.title}</td>
					<td>${orderDetail.book.description}</td>
					<td>${orderDetail.book.price}</td>
				</tr>
			</c:forEach>
			<tr height="20px" />
		</tbody>
	</table>
</div>