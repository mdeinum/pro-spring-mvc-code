<!DOCTYPE HTML>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Place Order</title>
</head>
<body>
    <form:form method="post" id="orderUploadForm" enctype="multipart/form-data" modelAttribute="orderForm">
        <h1 style="{padding-left: 12px;}">Order Upload (Form)</h1>
        <div id="leftPan">
        <form:input type="file" path="order" />
        </div>
        <button>Order!</button>
    </form:form>
</body>
</html>