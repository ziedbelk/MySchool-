<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="true" %>
<html>
<head>
<title>Login Page</title>
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>
</head>
<body onload='document.loginForm.username.focus();'>

	<h1>Ecole Supérieure Privée d'Ingénierie et des Technologies</h1>

	<div id="login-box">

		<h3>Login with Username and Password</h3>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:url var="addAction" value="/login/send" ></c:url>

<form:form class="form-horizontal" role="form" action="${addAction}" commandName="enseignant">
<table>

	<tr>
		<td>
			<form:label path="login">
				<spring:message text="login"/>
			</form:label>
		</td>
		<td>
			<form:input path="login" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="password">
				<spring:message text="password"/>
			</form:label>
		</td>
		<td>
			<form:input path="password" />
		</td> 
	</tr>
	<tr>
		<td colspan="2">
				<input type="submit"
					value="<spring:message text="Login"/>" />
			
		</td>
	</tr>
</table>
</form:form>
</div>

</body>
</html>