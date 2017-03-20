<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<style>
 	.filter .control-label{
 		text-align: left;
 	}
 </style>

 <div class="row">
                        <!-- це дів з чекбоксами і фільтрацією по ціні-->
	<div class="col-md-3 col-xs-12">
  		 <form:form class="form-horizontal filter" action="/shopping" method="GET" modelAttribute="filter">
			<div class="form-group">
			    <label class="control-label col-sm-12"><h4>Price</h4></label>
				<div class="col-sm-6">
					<form:input path="min" class="form-control" placeholder="Min"/>
				</div>
				<div class="col-sm-6">
					<form:input path="max" class="form-control" placeholder="Max"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-12"><h4>Brands</h4></label>
				<div class="col-sm-12">
					<form:checkboxes element="div" path="brandId" items="${brands}" itemValue="id" itemLabel="brandName"/>
				</div>
			</div> 	
			<div class="form-group">
				<label class="control-label col-sm-12"><h4>Target Audience</h4></label>
				<div class="col-sm-12">
					<form:checkboxes element="div" path="targetAudienceId" items="${targetAudiences}" itemValue="id" itemLabel="category"/>
				</div>
			</div>	
			<div class="form-group">
				<label class="control-label col-sm-12"><h4>Types</h4></label>
				<div class="col-sm-12">
					<form:checkboxes element="div" path="typeOfClothingId" items="${typesOfClothing}" itemValue="id" itemLabel="itemType"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-12"><h4>Colors</h4></label>
				<div class="col-sm-12">
					<form:checkboxes element="div" path="colorId" items="${colors}" itemValue="id" itemLabel="color"/>
				</div>
			</div>	
			<div class="form-group">
				<label class="control-label col-sm-12"><h4>Sizes</h4></label>
				<div class="col-sm-12">
					<form:checkboxes element="div" path="sizeId" items="${sizes}" itemValue="id" itemLabel="size"/>
				</div>
			</div>				
			<button type="submit" class="btn btn-primary">Ok</button>
		</form:form>  
	</div>
	<div class="col-md-7 col-xs-12">
		<div class="row">
			<div class="col-md-2 col-xs-2"><h4>Item name</h4></div>
			<div class="col-md-1 col-xs-1"><h4>Marking</h4></div>
			<div class="col-md-1 col-xs-1"><h4>Price</h4></div>			
			<div class="col-md-1 col-xs-1"><h4>Brand</h4></div>
			<div class="col-md-1 col-xs-1"><h4>Target audience</h4></div>
			<div class="col-md-1 col-xs-1"><h4>Type</h4></div>
			<div class="col-md-1 col-xs-1"><h4>Color</h4></div>
			<div class="col-md-1 col-xs-1"><h4>Size</h4></div>
			<div class="col-md-3 col-xs-3"><h4></h4></div>
		</div>
<!-- 		спеціальний тег для роботи з всім що можна прогорнути а точніше з тим що імплементує Iterator<T> -->
<!-- 		items -- це посилання на колекцію, ім'я потрібно  вказувати те яке ви передали в метод addAttribute першим параметром (в BrandController.java)-->
<!-- 		var -- це назва одного елемента колекції доступна лише в середині парного тегу c:forEach -->
			<c:forEach items="${page.content}" var="itemOfClothing">
				<div class="row">
<!-- 					тут все так само як на сервлетах -->
					<div class="col-md-2 col-xs-2">${itemOfClothing.itemName.name}</div>
					<div class="col-md-1 col-xs-1">${itemOfClothing.marking}</div>
					<div class="col-md-1 col-xs-1">${itemOfClothing.price}</div>
					<div class="col-md-1 col-xs-1">${itemOfClothing.brand.brandName}</div>
					<div class="col-md-1 col-xs-1">${itemOfClothing.targetAudience.category}</div>
					<div class="col-md-1 col-xs-1">${itemOfClothing.typeOfClothing.itemType}</div>
					<div class="col-md-1 col-xs-1">${itemOfClothing.color.color}</div>
					<div class="col-md-1 col-xs-1">${itemOfClothing.size.size}</div>
					<div class="col-md-3 col-xs-3"><a class="btn btn-success" href="/shopping/addToCart/${itemOfClothing.id}<custom:allParams/>">Buy</a></div>
				</div>
			</c:forEach>
	</div>
	<div class="col-md-2 col-xs-12">
 		<div class="row">
 					<div class="col-md-6 col-xs-6 text-center">
 						<div class="dropdown">
 							<button class="btn btn-primary dropdown-toggle" type="button"
 								data-toggle="dropdown">
 								Sort <span class="caret"></span>
 							</button>
 							<ul class="dropdown-menu">
 								<custom:sort innerHtml="Price asc" paramValue="price" />
 								<custom:sort innerHtml="Price desc" paramValue="price,desc" />
 								<custom:sort innerHtml="Item name asc" paramValue="itemName.name" />
 								<custom:sort innerHtml="Item name desc" paramValue="itemName.name,desc" />
 								<custom:sort innerHtml="Color asc" paramValue="color.color" />
 								<custom:sort innerHtml="Color desc" paramValue="color.color,desc" />
 							</ul>
 						</div>
 					</div>
 					<div class="col-md-6 col-xs-6 text-center">
 						<custom:size posibleSizes="1,2,5,10" size="${page.size}" />
 					</div>
 				</div>
 	</div>
 </div>
 <div class="row">
 	<div class="col-md-12 col-xs-12 text-center">
 		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
 	</div>
</div> 
<script>
	$('label').each(function() {
		if(!$(this).html()) $(this).parent('div').hide();
	});
</script>