<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<script src="../resources/js/jquery-2.1.1.min.js"></script>
<script src="../resources/js/requestButtons.js"></script>
	<title>Lista de Proveedores</title>
</head>
<body id="content">
<h1>Lista de Proveedores</h1>
<table id="list" data-entity="supplier">
<c:forEach items="${suppliers}" var="supplier" varStatus="iterator" > 
  
  <tr data-row="${iterator.index}">
	    <td id="code" >${supplier.code}</td>
	    <td id="name" >${supplier.name}</td>
	    <td><input type="button" value="Actualizar" data-id="${supplier.id}" data-type="update"></td>
	    <td><input type="button" value="Borrar" data-id="${supplier.id}" data-type="delete"></td>
  </tr>
  
</c:forEach>
</table>
</body>
</html>
