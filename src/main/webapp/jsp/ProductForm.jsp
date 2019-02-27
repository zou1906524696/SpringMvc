<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<form action="save-product" method="POST">
	<h1> Add Product
	<span>Please use this form to enter product details</span>
	</h1>
	${empty requestScope.errors? "": "<P style='color:red'>"
		+="Error(s)!"
		+="<ul>"}
		<!--${requestScope.errors.stream().map( x->" -->
		<li style='color:red'>"+=x+="</li>
		<!-- ").toList()} -->
		${empty requestScope.errors? "": "</ul></p>"} 
		<br>
	<label>
		<span>Product Name:</span>
		<input id="name" type="text" name="name"
		placeholder ="The complete product name"
		value="${form.name} "/>
	</label>
	
	<label>
		<span>Description:</span>
		<input id="description" type="text" name="description" 
		placeholder ="product description"
		value="${form.description} "/>
	</label>
	
	<label>
		<span>Price:</span>
		<input id="price" type="text" name="price" 
		placeholder ="product price in #.## format"
		value="${form.price} "/>
	</label>
	
	<label>
		<span>&nbsp;</span>
		<input type="submit">
		
	</label>
	
</form>
</body>
</html>