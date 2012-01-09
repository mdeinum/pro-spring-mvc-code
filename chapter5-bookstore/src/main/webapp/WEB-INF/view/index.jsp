<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Welcome to Pizzas R Us</title>
<link type="text/css" href="http://localhost:8080<c:url value="/css/pizzas-r-us.css"/>" ></link>
</head>
<body>
<p>How exciting!  Our first vertical slice!</p>

<div id="menu">
	<jsp:include page="menu.jsp" />
</div>

</body>
</html>