<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<title>Welcome to Pizzas R Us</title>
</head>
<body>
<p>How exciting!  Our first vertical slice!</p>

<table>
	<thead>
		<tr><th>Name</th><th>Description</th><th>Price</th></tr>
	</thead>
	<tbody>
		<c:forEach items="${pizzas}" var="pizza">
			<tr><td>${pizza.name}</td><td>${pizza.description}</td><td>${pizza.price}</td><tr>
		</c:forEach>
	</tbody>	
</table>
</body>