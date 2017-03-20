<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Target Audience: </h2>

<ul>
	<c:forEach items="${targetAudiences}" var="targetAudience">
		<li><a href="/user/targeta/${targetAudience.id}">${targetAudience.category}</a></li>
	</c:forEach>
</ul> 