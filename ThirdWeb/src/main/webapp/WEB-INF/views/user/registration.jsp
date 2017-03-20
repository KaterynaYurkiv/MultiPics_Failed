<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="row">
	<div class="col-sm-12 col-xs-12">
		<form:form class="form-horizontal" action="/registration" method="POST" modelAttribute="user">
  			<div class="form-group">
 				<label for="email" style="color:red;text-align:left;" class="col-sm-offset-2 col-sm-10 control-label"><form:errors path="name"/></label>
 			</div> 
			<div class="form-group">
    			<label for="email" class="col-sm-2 control-label">Name</label>
    			<div class="col-sm-10">
      				<form:input class="form-control" path="name" id="name"/>
    			</div>
  			</div>
  			<div class="form-group">
 				<label for="email" style="color:red;text-align:left;" class="col-sm-offset-2 col-sm-10 control-label"><form:errors path="email"/></label>
 			</div> 
			<div class="form-group">
    			<label for="email" class="col-sm-2 control-label">Email</label>
    			<div class="col-sm-10">
      				<form:input class="form-control" path="email" id="email"/>
    			</div>
  			</div>
  			<div class="form-group">
 				<label for="email" style="color:red;text-align:left;" class="col-sm-offset-2 col-sm-10 control-label"><form:errors path="phoneNumber"/></label>
 			</div> 
			<div class="form-group">
    			<label for="email" class="col-sm-2 control-label">Phone Number</label>
    			<div class="col-sm-10">
      				<form:input class="form-control" path="phoneNumber" id="phoneNumber"/>
    			</div>
  			</div>
  			<div class="form-group">
				<label for="password" class="col-sm-offset-2 col-sm-10"><form:errors path="password"/></label>
			</div>
			<div class="form-group">
    			<label for="password" class="col-sm-2 control-label">Password</label>
    			<div class="col-sm-10">
      				<form:password class="form-control" path="password" id="password"/>
    			</div>
  			</div>
  			<div class="form-group">
    			<div class="col-sm-offset-2 col-sm-10">
      				<button type="submit" class="btn btn-default">Register</button>
    			</div>
  			</div>
		</form:form>
	</div>
</div>
<script>
	$('label').each(function() {
		if(!$(this).html()) $(this).parent('div').hide();
	});
</script> 