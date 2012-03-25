<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${exception ne null}">
    <div class="error">
        ${exception.message}
    </div>
</c:if>
<form action="<c:url value="/login"/>" method="post">
    <fieldset>
        <legend>Login</legend>
        <table>
        <tr>
            <td>Username</td>
            <td><input type="text" id="username" name="username" placeholder="Usename"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" id="password" name="password" placeholder="Password"/></td>
        </tr>
        <tr><td colspan="2" align="center"><button id="login">Login</button></td></tr>
        </table>
    </fieldset>
</form>
