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
				    <li><a href="/admin/itemName">Item name</a></li>
					<li><a href="/admin/brand">Brand</a></li>
					<li><a href="/admin/toc">Type of clothing</a></li>
					<li><a href="/admin/targeta">Target audience</a></li>
					<li><a href="/admin/color">Color</a></li>
					<li><a href="/admin/size">Size</a></li>
					<li class="active"><a href="/admin/ioc<custom:allParams/>">Item of clothing</a><span
						class="sr-only">(current)</span></li>
					<li><a href="/admin/customer">Customer</a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row">
                        <!-- це дів з чекбоксами і фільтрацією по ціні-->
	<div class="col-md-3 col-xs-12">
  		 <form:form class="form-horizontal filter" action="/admin/ioc" method="GET" modelAttribute="filter">
			<div class="form-group">
			    <label class="control-label col-sm-12"><h4>Marking</h4></label>
				<div class="col-sm-6">
					<form:input path="minM" class="form-control" placeholder="Min"/>
				</div>
				<div class="col-sm-6">
					<form:input path="maxM" class="form-control" placeholder="Max"/>
				</div>
			</div>
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
				<label class="control-label col-sm-12"><h4>Item names</h4></label>
				<div class="col-sm-12">
					<form:checkboxes element="div" path="itemNameId" items="${itemNames}" itemValue="id" itemLabel="name"/>
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
			<div class="col-md-12 col-xs-12">
				<form:form class="form-horizontal" action="/admin/ioc" method="POST" modelAttribute="itemOfClothing" enctype="multipart/form-data">
				<custom:hiddenInputs excludeParams="itemName, marking, price, brand, targetAudience, typeOfClothing, color, size"/>
				    <!-- тут пишемо дів для помилки бо це верхнє перше поле і над ним будемо виводити повідомлення про те, що такий об'єкт вже був в базі, якщо це відповідатиме дійсності -->
				    <div class="form-group">
						<label for="itemName" style="color:red;text-align:left;" class="col-sm-offset-2 col-sm-10 control-label"><form:errors path="itemName"/></label>
					</div>
				    <div class="form-group">
    					<label for="itemName" class="col-sm-2 control-label">Item Name</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="itemName" id="itemName" items="${itemNames}" itemValue="id" itemLabel="name"/>
    					</div>
  					</div>
  					<!-- тут пишемо дів для можливої помилки, якщо некоректно буде введене маркування -->
  					<div class="form-group">
 						<label for="marking" style="color:red;text-align:left;" class="col-sm-offset-2 col-sm-10 control-label"><form:errors path="marking"/></label>
 					</div>
  					<div class="form-group">
  					<div class="form-group">
    					<label for="marking" class="col-sm-2 control-label">Marking</label>
    					<div class="col-sm-10">
      						<form:input type="text" class="form-control" path="marking" id="marking"/>
    					</div>
  					</div>
  					<!-- тут пишемо дів для можливої помилки, якщо некоректно буде введена ціна -->
  					<div class="form-group">
 						<label for="price" style="color:red;text-align:left;" class="col-sm-offset-2 col-sm-10 control-label"><form:errors path="price"/></label>
 					</div>
  					<div class="form-group">
    					<label for="price" class="col-sm-2 control-label">Price</label>
    					<div class="col-sm-10">
      						<form:input type="text" class="form-control" path="price" id="price"/>
    					</div>
  					</div>
					<div class="form-group">
    					<label for="brand" class="col-sm-2 control-label">Brand</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="brand" id="brand" items="${brands}" itemValue="id" itemLabel="brandName"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="targetAudience" class="col-sm-2 control-label">Target Audience</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="targetAudience" id="targetAudience" items="${targetAudiences}" itemValue="id" itemLabel="category"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="typeOfClothing" class="col-sm-2 control-label">Type Of Clothing</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="typeOfClothing" id="typeOfClothing" items="${typesOfClothing}" itemValue="id" itemLabel="itemType"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="color" class="col-sm-2 control-label">Color</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="color" id="color" items="${colors}" itemValue="id" itemLabel="color"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="size" class="col-sm-2 control-label">Size</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="size" id="size" items="${sizes}" itemValue="id" itemLabel="size"/>
    					</div>
  					</div>
  					<div class="form-group">
     					<label for="file" class="col-sm-2 control-label">Image</label>
     					<div class="col-sm-10">
       						<input name="file" id="file" type="file">
     					</div>
   					</div>
  					<div class="form-group">
    					<div class="col-sm-offset-2 col-sm-10">
      						<button type="submit" class="btn btn-default">Create</button>
    					</div>
  					</div>
				</form:form>
			</div>
		</div>
		<%-- <div class="row">
			<div class="col-md-12 col-xs-12">
				<form class="form-horizontal" action="/admin/ioc" method="POST">
					<div class="form-group">
    					<label for="itemName" class="col-sm-2 control-label">Item Name</label>
    					<div class="col-sm-10">
      						<select class="form-control" name="itemNameId" id="itemName">
      							<c:forEach items="${itemNames}" var="itemName">
      								<option value="${itemName.id}">${itemName.name}</option>
      							</c:forEach>
      						</select>
    					</div>
  					</div>
					<div class="form-group">
    					<label for="marking" class="col-sm-2 control-label">Marking</label>
    					<div class="col-sm-10">
      						<input type="text" class="form-control" name="marking" id="marking">
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="price" class="col-sm-2 control-label">Price</label>
    					<div class="col-sm-10">
      						<input type="text" class="form-control" name="price" id="price">
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="brand" class="col-sm-2 control-label">Brand</label>
    					<div class="col-sm-10">
      						<select class="form-control" name="brandId" id="brand">
      							<c:forEach items="${brands}" var="brand">
      								<option value="${brand.id}">${brand.brandName}</option>
      							</c:forEach>
      						</select>
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="targetAudience" class="col-sm-2 control-label">Target Audience</label>
    					<div class="col-sm-10">
      						<select class="form-control" name="targetAudienceId" id="targetAudience">
      							<c:forEach items="${targetAudiences}" var="targetAudience">
      								<option value="${targetAudience.id}">${targetAudience.category}</option>
      							</c:forEach>
      						</select>
    					</div>
  					</div> 
  					<div class="form-group">
    					<label for="typeOfClothing" class="col-sm-2 control-label">Type of clothing</label>
    					<div class="col-sm-10">
      						<select class="form-control" name="typeOfClothingId" id="typeOfClothing">
      							<c:forEach items="${typesOfClothing}" var="typeOfClothing">
      								<option value="${typeOfClothing.id}">${typeOfClothing.itemType}</option>
      							</c:forEach>
      						</select>
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="size" class="col-sm-2 control-label">Size</label>
    					<div class="col-sm-10">
      						<select class="form-control" name="sizeId" id="size">
      							<c:forEach items="${sizes}" var="size">
      								<option value="${size.id}">${size.size}</option>
      							</c:forEach>
      						</select>
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="color" class="col-sm-2 control-label">Color</label>
    					<div class="col-sm-10">
      						<select class="form-control" name="colorId" id="color">
      							<c:forEach items="${colors}" var="color">
      								<option value="${color.id}">${color.color}</option>
      							</c:forEach>
      						</select>
    					</div>
  					</div>
  					<div class="form-group">
    					<div class="col-sm-offset-2 col-sm-10">
      						<button type="submit" class="btn btn-default">Create</button>
    					</div>
  					</div>
				</form>
			</div>
		</div>--%>
		<div class="row">
			<div class="col-md-2 col-xs-2"><h4>Item name</h4></div>
			<div class="col-md-1 col-xs-1"><h4>Marking</h4></div>
			<div class="col-md-1 col-xs-1"><h4>Price</h4></div>			
			<div class="col-md-1 col-xs-1"><h4>Brand</h4></div>
			<div class="col-md-1 col-xs-1"><h4>Target audience</h4></div>
			<div class="col-md-1 col-xs-1"><h4>Type</h4></div>
			<div class="col-md-1 col-xs-1"><h4>Color</h4></div>
			<div class="col-md-1 col-xs-1"><h4>Size</h4></div>
			<div class="col-md-3 col-xs-3"><h4>Options</h4></div>
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
					<div class="col-md-3 col-xs-3"><a class="btn btn-warning" href="/admin/ioc/update/${itemOfClothing.id}<custom:allParams/>">update</a>
					                               <a class="btn btn-danger" href="/admin/ioc/delete/${itemOfClothing.id}<custom:allParams/>">delete</a>
					</div>
				</div>
     			<div class="row">
					<c:forEach items="${itemOfClothing.images}" var="image"> 
                          <div class="col-md-1 col-xs-1"><img src="/images/clothes/${image.id}.jpg?version=${image.version}" width="100%"></div>
                    </c:forEach>  
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