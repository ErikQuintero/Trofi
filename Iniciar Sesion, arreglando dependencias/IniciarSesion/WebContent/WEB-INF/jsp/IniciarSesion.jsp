<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html lang="en">

<title>Iniciar sesión</title>
<style>
	.error { 
		color: red; font-weight: bold; 
	}
</style>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<head>
<script src="jquery/jquery.min.js"></script>
<!---- jquery link local dont forget to place this in first than other script or link or may not work ---->

<link rel="stylesheet" href="css/bootstrap.min.css">
<!---- boostrap.min link local ----->

<link rel="stylesheet" href="css/style.css">
<!---- boostrap.min link local ----->

<script src="js/bootstrap.min.js"></script>
<!---- Boostrap js link local ----->

<link rel="icon" href="images/icon.png" type="image/x-icon" />
<!---- Icon link local ----->

<link href="https://fonts.googleapis.com/css?family=Lobster"
	rel="stylesheet">
<!---- Font awesom link local ----->
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" type="text/css" />
</head>

<body>
	<div class="container-fluid">
		<div class="container">
			<h2 class="text-center" id="title2"> &nbsp; Trofi &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Cliente</h2>
			<hr>
			<div class="row">

				<div class="col-md-2">
					<!-------null------>
				</div>
				<div class="col-md-5">
					<form role="form" method="post" action="SesionIniciada.html"
						modelAttribute="login">
						<fieldset>
							<h3>Iniciar sesión <br> </br>Correo electrónico</h3>
							
							
									
							<div class="form-group">
							
								<input type="email" name="email" id="email"
									class="form-control input-lg" placeholder="E-mail" >
							</div>
							<h3>Contraseña</h3>
							<div class="form-group">
								 <input type="password"
									name="password" id="password" class="form-control input-lg"
									placeholder="Contraseña" >
								<errors path="password" cssClass="error" />
							</div>
							<div style="text-align: right">
								<input type="submit" class="btn btn-success btn-lg"
									value="Aceptar">
							</div>
						</fieldset>
					</form>
				</div>		
			</div>
		</div><hr>
	</div>	
</body>
<div>
	<form role="form" method="post" action="Admin.html" modelAttribute="login">
		<input type="submit" class="btn btn-success btn-lg" value="Administrador">
	</form>	
</div>
<div>
<form role="form" method="post" action="Repartidor.html" modelAttribute="login">
<input type="submit" class="btn btn-success btn-lg" value="Repartidor">	
</div>
</html>