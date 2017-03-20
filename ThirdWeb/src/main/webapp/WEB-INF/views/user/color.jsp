<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Colors: </h2>

<ul>
	<c:forEach items="${colors}" var="color">
		<li><a href="/user/color/${color.id}">${color.color}</a></li>
	</c:forEach>
</ul> 