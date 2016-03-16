<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<html>
<head>
	<title>Formulario de Registro de Producto</title>
</head>
<body>
<h1>${message}</h1>
<form:errors path="student1.*"/>
<form action="/IJMpiloto/product/submit" method="POST">
  Código:<input type="text" name="code">
  <br>
  Descripción:<input type="text" name="description">
  <br>
  Proveedor:<input type="text" name="supplier">
  <input type="submit" value="Submit">
</form> 
</body>
</html>
