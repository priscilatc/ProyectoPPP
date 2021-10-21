 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   String varMSJ = request.getParameter("msj")== null?"":request.getParameter("msj");
%>
    <%    
    //Limpia la CACHE del navegador
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setDateHeader("Expires", 0);
    response.setDateHeader("Expires", -1);
    %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Modulo Evaluación - Iniciar Sesión</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link rel=stylesheet href="jAlert/dist/jAlert.css"/>
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.css" rel="stylesheet">

</head>

<%
		//DESTRUYE LA SESIÓN
		HttpSession hts = request.getSession(false);
		hts.removeAttribute("login");
		hts.invalidate();
%>
<body class="bg-gradient-primary">

    <div class="container">

        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                            <div class="col-lg-6">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">Inicio de Sesión</h1>
                                    </div>
                                    <form class="user" action="./SLlog" method="post">
                                    <form action="./SlInfoEst" method="get">
                                        <div class="form-group">
                                            <input id="usuario" name="usuario" type="text" class="form-control has-rpromedix" 
											placeholder="Nombre del Usuario" data-toggle="tooltip" 
											data-placement="bottom" title="Escriba el nombre del usuario" required>
                                        </div>
                                        <div class="form-group">
                                            <input id="pwd" name="pwd" type="password" class="form-control has-gpromedix" 
											placeholder="Contraseña" data-toggle="tooltip" 
											data-placement="bottom" title="Contraseña ">
                                        </div>                                   
                                     
										<div class="text-center">
                                        <input type="submit"  class="btn btn-outline-primary" value="Iniciar Sesión" />
                                        </div>
                                    </form>
                                    </form>
                                    <hr>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>
    
    <script src="jAlert/dist/jAlert.min.js"></script>
	<script src="jAlert/dist/jAlert-functions.min.js"></script>
	<script>
		$(document).ready(function ()
	    	    {
	    	        var mensaje = "";
	    	        mensaje = "<%=varMSJ%>";
	    	        
	    	        if(mensaje == "1")
	    	        {
	    	            errorAlert('Error al Iniciar Sesión', 'Revise los datos ingresados');
	    	        }
	    	 });
	</script>

</body>

</html>