<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Name: </h2>

<ul>
	<c:forEach items="${itemNames}" var="itemName">
		<li><a href="/user/itemName/${itemName.id}">${itemName.name}</a></li>
	</c:forEach>
</ul> 