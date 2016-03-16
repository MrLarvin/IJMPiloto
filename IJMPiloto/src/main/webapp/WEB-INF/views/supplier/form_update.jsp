<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<html>
<head>
	<title>Formulario de Registro de Proveedor</title>
</head>
<body>
<h1>${message}</h1>
<form:errors path="student1.*"/>
<form action="/IJMpiloto/supplier/update" method="POST">
  RIF:<input type="text" name="code" value="${supplier1.code}">
  <br>
  Nombre:<input type="text" name="name" value="${supplier1.name}" >
  <br>
  <input type="submit" value="Registrar">
</form> 
</body>
</html>
