<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Sizes: </h2>

<ul>
	<c:forEach items="${sizes}" var="size">
		<li><a href="/user/size/${size.id}">${size.size}</a></li>
	</c:forEach>
</ul> 