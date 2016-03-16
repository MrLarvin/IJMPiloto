<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<html>
<head>
	<title>Formulario de Registro de Proveedor</title>
</head>
<body id="content">
<script src="../resources/js/jquery-2.1.1.min.js"></script>
<script src="../resources/js/requestButtons.js"></script>
<h1>${message}</h1>
<form:errors path="student1.*"/>
<form id="form" data-entity="supplier">
  RIF:<input type="text" name="code">
  <br>
  Nombre:<input type="text" name="name">
  <br>
  <input type="button" data-type="input" value="Registrar" data-request="POST"/>
</form> 
</body>
</html>
