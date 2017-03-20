<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h2>Hello</h2>
<h3>Here you can choose the characteristic of the item of clothing: </h3>
   <h5><a href="/user/itemName">Item Name</a></h5>
   <h5><a href="/user/brand">Brand</a></h5>
   <h5><a href="/user/targeta">Target Audience</a></h5>
   <h5><a href="/user/type">Type of clothing</a></h5>
   <h5><a href="/user/color">Color</a></h5>
   <h5><a href="/user/size">Size</a></h5>
   <br>

<!-- <a href="/admin">admin</a>
<a href="/login">Login</a>
<a href="/registration">Registration</a>  -->

<sec:authorize access="isAuthenticated()">
    <!-- перевіряє чи зайшов адмін, якщо так дає відкриває йому посилання адмін -->
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<a href="/admin">admin</a>
	</sec:authorize>
	<!-- перевіряє чи користувач авторизований, якщо так з'являється кнопка вийти -->
<form:form action="/logout" method="POST">
	<button type="submit" class="btn btn-danger">Logout</button>
</form:form>
</sec:authorize>

<!-- перевіряє чи користувач авторизований, якщо ні з'являється можливість реєстрації або входу -->
<sec:authorize access="!isAuthenticated()">
	<a href="/login">Login</a>
	<a href="/registration">Registration</a>
</sec:authorize>
    <a href="/shopping">Shop now</a>