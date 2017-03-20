<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav"> 
				    <li><a href="/admin/itemName">Item Name</a></li>
					<li><a href="/admin/brand">Brand</a></li>
					<li><a href="/admin/toc">Type of clothing</a></li>
					<li><a href="/admin/targeta">Target audience</a></li>
					<li><a href="/admin/color">Color</a></li>
					<li><a href="/admin/size">Size</a></li>
					<li><a href="/admin/ioc">Item of clothing</a></li>
					<li class="active"><a href="/admin/customer">Customer</a><span
						class="sr-only">(current)</span></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row">
	<div class="col-md-3 col-xs-12"></div>
	<div class="col-md-7 col-xs-12">
		<div class="row">
			<div class="col-md-4 col-xs-4"><h3>Customer login</h3></div>
			<div class="col-md-4 col-xs-4"><h3>Update</h3></div>
			<div class="col-md-4 col-xs-4"><h3>Delete</h3></div>
		</div>
<!-- 		спеціальний тег для роботи з всім що можна прогорнути а точніше з тим що імплементує Iterator<T> -->
<!-- 		items -- це посилання на колекцію, ім'я потрібно  вказувати те яке ви передали в метод addAttribute першим параметром (в BrandController.java)-->
<!-- 		var -- це назва одного елемента колекції доступна лише в середині парного тегу c:forEach -->
			<c:forEach items="${customers}" var="customer">
				<div class="row">
<!-- 					тут все так само як на сервлетах -->
					<div class="col-md-4 col-xs-4">${customer.userName}</div>
					<div class="col-md-4 col-xs-4"><a class="btn btn-warning" href="/admin/customer/update/${customer.id}">update</a></div>
					<div class="col-md-4 col-xs-4"><a class="btn btn-danger" href="/admin/customer/delete/${customer.id}">delete</a></div>
				</div>
			</c:forEach>
	</div>
	<div class="col-md-2 col-xs-12"></div>
</div>