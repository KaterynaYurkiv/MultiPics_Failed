<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Brands: </h2>

<ul>
	<c:forEach items="${brands}" var="brand">
		<li><a href="/user/brand/${brand.id}">${brand.brandName}</a></li>
	</c:forEach>
</ul> 
