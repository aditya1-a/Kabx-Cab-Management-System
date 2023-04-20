<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h1>Login</h1>
    <form:form method="POST" modelAttribute="user" action = "/kabx/home">
        <table>
            <tr>
                <td><form:label path="email">Email</form:label></td>
                <td><form:input path="email" /></td>
            </tr>
            <tr>
                <td><form:label path="password">Password</form:label></td>
                <td><form:password path="password" /></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Login" /></td>
            </tr>
        </table>
    </form:form>
    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>
    
    <br>
	<a href="/register">Don't have an account? Register here.</a>
</body>
</html>
