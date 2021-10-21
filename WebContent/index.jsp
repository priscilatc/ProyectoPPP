<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="vistas.*" %>
<%@page import="datos.*" %>
<%@page import="java.util.*" %>
<%@page session="true" %>
<!DOCTYPE html>
<html>
<%
	String varMSJ = request.getParameter("msj")== null?"":request.getParameter("msj");
%>
<%
    //Limpia la CACHE del navegador
	    response.setHeader("Pragma", "no-cache");
	    response.setHeader("Cache-Control", "no-store");
	    response.setDateHeader("Expires", 0);
	    response.setDateHeader("Expires", -1);
	      
		
		String loginUser = "";
		loginUser = (String)session.getAttribute("login");
		loginUser = loginUser==null?"":loginUser;
		
		
		if(loginUser.equals(""))
		{
			response.sendRedirect("login.jsp");
		}
				
		ArrayList<VW_usuario_rol> listaUr = new ArrayList<VW_usuario_rol>(); 
		DTUsuario dtu = new DTUsuario();
		 		 
		 listaUr = dtu.listarUR(loginUser);
		                            
		              for (VW_usuario_rol vwur : listaUr) 
		              {            	                
		               String Rol = vwur.getRol();

		    		   session.setAttribute("rol", Rol);
		      			 
		               }
		              String cargo ="";
		              cargo= (String) session.getAttribute("rol");
		              cargo = cargo ==null?"":cargo;		             
		              
    %>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Modulo Evaluación - Inicio</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.css" rel="stylesheet">
    <link rel=stylesheet href="jAlert/dist/jAlert.css"/>

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <jsp:include page="WEB-INF/layout/sidebar.jsp"></jsp:include>
        
        
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                
                <jsp:include page="WEB-INF/layout/header.jsp"></jsp:include>
                
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->                   

                    <!-- Content Row -->
                    <%
                    if (cargo.equals("Administrador")){
                    %>
						<div class="card shadow mb-4">						
                        	<div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">¡Bienvenid@! <%=(String)session.getAttribute("login")%></h6>
                        	 </div>
                             	<div class="card-body">
                             	     <div id="carouselExampleSlidesOnly" class="carousel slide" data-ride="carousel">
										  <div class="carousel-inner">
										    <div class="carousel-item active">
										      <img class="d-block w-100" src="./img/Banner1.png" alt="First slide">
										    </div>
										    <div class="carousel-item">
										      <img class="d-block w-100" src="./img/Banner2.png" alt="Second slide">
										    </div>
										  </div>
										</div>
                            	 </div>       
                    		</div>
                    <%
                    } if(cargo.equals("Tutor Tecnico")){
                    %>
                    <div id="myCarousel" class="carousel slide carousel-fade" data-ride="carousel">

					  <div class="carousel-inner">
					    <div class="carousel-item active">
					      <div class="mask flex-center">
					        <div class="container">
					          <div class="row align-items-center">
					            <div class="col-md-7 col-12 order-md-1 order-2">
					                  <font color="#002663" size=6> 
					              
					               <h1>Bienvenido al Sistema de Prácticas PreProfesionales</h1></font>
					            
					         <p>En este sistema podrás realizar la evaluación técnica <br>
					                 de tus practicantes </p>
					              <a ></a> </div>
					            <div class="col-md-5 col-12 order-md-2 order-1"><img src="./img/tutor.svg" class="mx-auto" alt="slide" width="445" height="350" ></div>
					          </div>
					        </div>
					      </div>
					    </div>
					    <div class="carousel-item">
					      <div class="mask flex-center">
					        <div class="container">
					          <div class="row align-items-center">
					            <div class="col-md-7 col-12 order-md-1 order-2">
					             <font color="#002663" size=6> 
					              <h1>Bienvenido al Sistema de Prácticas PreProfesionales</h1></font>
					       
					              <p>En este sistema podrás realizar la evaluación técnica <br>
					                 de tus practicantes </p>
					              <a ></a> </div>
					            <div class="col-md-5 col-12 order-md-2 order-1"><img src="./img/tutor2.svg" class="mx-auto" alt="slide" width="445" height="350" ></div>
					          </div>
					        </div>
					      </div>
					    </div>
					   
					  </div>
					  <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev"> <span class="carousel-control-prev-icon" aria-hidden="true"></span> <span class="sr-only">Previous</span> </a> <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next"> <span class="carousel-control-next-icon" aria-hidden="true"></span> <span class="sr-only">Next</span> </a> 
					  </div>
                    <%
                    }if (cargo.equals("Tutor Academico")){
                    %>
						<div class="card shadow mb-4">						
                        	<div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">¡Bienvenid@! <%=(String)session.getAttribute("login")%></h6>
                        	 </div>
                             	<div class="card-body">
                             	     <div id="carouselExampleSlidesOnly" class="carousel slide" data-ride="carousel">
										  <div class="carousel-inner">
										    <div class="carousel-item active">
										      <img class="d-block w-100" src="./img/Banner1.png" alt="First slide">
										    </div>
										    <div class="carousel-item">
										      <img class="d-block w-100" src="./img/Banner2.png" alt="Second slide">
										    </div>
										  </div>
										</div>
                            	 </div>       
                    		</div>
                    <%
                    } if (cargo.equals("Estudiante")){
                    %>
                    <div id="myCarousel" class="carousel slide carousel-fade" data-ride="carousel">
					  <div class="carousel-inner">
					    <div class="carousel-item active">
					      <div class="mask flex-center">
					        <div class="container">
					          <div class="row align-items-center">
					            <div class="col-md-7 col-12 order-md-1 order-2">
					                  <font color="#002663" size=6> 
					              
					               <h1>Bienvenido al Sistema de Prácticas PreProfesionales</h1></font>
					            
					              <p>En este sistema podrás realizar tu auto evaluación de PPP <br>
					               y accederás a los resultados de tu evaluación técnica</p>
					              <a ></a> </div>
					            <div class="col-md-5 col-12 order-md-2 order-1"><img src="./img/alumno.svg" class="mx-auto" alt="slide" width="445" height="350" ></div>
					          </div>
					        </div>
					      </div>
					    </div>
					    <div class="carousel-item">
					      <div class="mask flex-center">
					        <div class="container">
					          <div class="row align-items-center">
					            <div class="col-md-7 col-12 order-md-1 order-2">
					             <font color="#002663" size=6> 
					              <h1>Bienvenido al Sistema de Prácticas PreProfesionales</h1></font>
					       
					              <p>En este sistema podrás realizar tu auto evaluación de PPP <br>
					               y accederás a los resultados de tu evaluación técnica</p>
					              <a ></a> </div>
					            <div class="col-md-5 col-12 order-md-2 order-1"><img src="./img/alumno2.svg" class="mx-auto" alt="slide" width="445" height="350" ></div>
					          </div>
					        </div>
					      </div>
					    </div>
					   
					  </div>
					  <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev"> <span class="carousel-control-prev-icon" aria-hidden="true"></span> <span class="sr-only">Previous</span> </a> <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next"> <span class="carousel-control-next-icon" aria-hidden="true"></span> <span class="sr-only">Next</span> </a> 
					  </div>
					  <%
                       }
                       %>					

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            
            <jsp:include page="WEB-INF/layout/footer.jsp"></jsp:include>
            
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

   <!-- Logout Modal-->
		<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">¿Listo para irte?</h5>
						<button class="close" type="button" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
					</div>
					<div class="modal-body">Seleccione "Cerrar sesión" si está listo para finalizar su sesión actual.</div>
					<div class="modal-footer">
						<button class="btn btn-secondary" type="button"
							data-dismiss="modal">Cancelar</button>
						<a class="btn btn-primary" href="login.jsp">Cerrar sesión</a>
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

    <!-- Page level plugins -->
    <script src="vendor/chart.js/Chart.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="js/demo/chart-area-demo.js"></script>
    <script src="js/demo/chart-pie-demo.js"></script>
    
    <script src="jAlert/dist/jAlert.min.js"></script>
	 <script src="jAlert/dist/jAlert-functions.min.js"></script>
	<script type="text/javascript">	
	$(document).ready(function ()
    	    {
    	        var mensaje = "";
    	        mensaje = "<%=varMSJ%>";
    	        
    	        if(mensaje == "1")
    	        {
    	            successAlert('¡Actualizado!', 'Los datos de tu cuenta han sido actualizados exitosamente');
    	        }
    	        if(mensaje == "2")
    	        {
    	            errorAlert('Error', 'Revisa la contraseña antigua');
    	        }
    	    });
	</script>

</body>
</html>