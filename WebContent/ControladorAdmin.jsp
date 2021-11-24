<%@page import="vistas.*" %>
<%@page import="datos.*" %>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
		              
		              if (cargo.equals("Tutor Academico")){
							response.sendRedirect("404.jsp");
						}
		              if (cargo.equals("Estudiante")){
							response.sendRedirect("404.jsp");
						}
		              if (cargo.equals("Tutor Tecnico")){
							response.sendRedirect("404.jsp");
						}
		              if (cargo.equals(null)){
		            	  response.sendRedirect("login.jsp");
		              }
    %>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Modulo Evaluación - Mapa de Sitios</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.css" rel="stylesheet">

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
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Mapa de Sitios</h1>
                    </div>
                    <hr>

                    <!-- Content Row -->
  <div class="py-5">
    <div class="container">
    <div class="row">
       
        <div class="col-md-5 offset-md-1">
          <div class="card" style="width: 55rem;"  style="height: 45rem;">
            <div class="card-block">
             <br>
              <img class="card-img-top" src="./img/map.svg"  width="300" height="300">
             <div class="card-body">
              
              <font color="#002663" size=10> 
              
             En esta sección encontrarás todos los accesos directos que posee el sistema.
              </font>
           
            </div>
            </div>
          </div>
        </div>
      </div>
      <br>
      <div class="row hidden-md-up">
        <div class="col-md-4">
          <div class="card">
            <h5 class="card-header">Tutores y estudiantes</h5>
          
            <div class="card-body">
           
             
              <a href="ListaAsignacionTutores.jsp" class="btn btn-primary">
                         <i class="fas fa-table fa-sm text-white-50"></i>
                             <span class="icon text-white-50">
                             </span>
                             <span class="text">Tabla Asignación Tutores</span>
                         </a>
                         
                         <div class="my-2"></div>
                         <a href="ListaDocente.jsp" class="btn btn-primary">
                         <i class="fas fa-table fa-sm text-white-50"></i>
                             <span class="icon text-white-50">
                             </span>
                             <span class="text">Tabla Docente</span>
                         </a>
   
           
                         <div class="my-2"></div>
                          <a href="ListaEstudiante.jsp" class="btn btn-primary">
                          <i class="fas fa-table fa-sm text-white-50"></i>
                             <span class="icon text-white-50">
                             </span>
                             <span class="text">Tabla Estudiante</span>
                         </a>
                         
						<div class="my-2"></div>
                         <a href="ListaCoordinacion.jsp" class="btn btn-primary">
                         <i class="fas fa-table fa-sm text-white-50"></i>
                             <span class="icon text-white-50">
                             </span>
                             <span class="text">Tabla Coordinación</span>
                         </a>
                         
                         <div class="my-2"></div>
                         <a href="ListaFacultad.jsp" class="btn btn-primary">
                         <i class="fas fa-table fa-sm text-white-50"></i>
                             <span class="icon text-white-50">
                             </span>
                             <span class="text">Tabla Facultad</span>
                         </a>
                         
                         <div class="my-2"></div>
                         <a href="ListaOrganizacion.jsp" class="btn btn-primary">
                         <i class="fas fa-table fa-sm text-white-50"></i>
                             <span class="icon text-white-50">
                             </span>
                             <span class="text">Tabla Organización</span>
                         </a>
                         
                         <div class="my-2"></div>
                         <a href="ListaTutorTecnico.jsp" class="btn btn-primary">
                         <i class="fas fa-table fa-sm text-white-50"></i>
                             <span class="icon text-white-50">
                             </span>
                             <span class="text">Tabla Tutor Técnico</span>
                         </a>
                         
                         <div class="my-2"></div>
                         <a href="ListaPPP.jsp" class="btn btn-primary">
                         <i class="fas fa-table fa-sm text-white-50"></i>
                             <span class="icon text-white-50">
                             </span>
                             <span class="text">Tabla PPP</span>
                         </a>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="card">
            <h5 class="card-header">Evaluación y Detalles</h5>
                        <div class="card-body">
            
            	<a href="ListaNivel.jsp" class="btn btn-primary">
                    		<i class="fas fa-table fa-sm text-white-50"></i>
                             <span class="icon text-white-50">
                             </span>
                             <span class="text">Tabla Nivel 180</span>
                            </a>
                            
                    		<div class="my-2"></div>
                    		<a href="ListaEvaluacion.jsp" class="btn btn-primary">
                    		<i class="fas fa-table fa-sm text-white-50"></i>
                             <span class="icon text-white-50">
                             </span>
                             <span class="text">Tabla Evaluación</span>
                            </a>
                            
                            <div class="my-2"></div>
                            <a href="ListaPeriodoPPP.jsp" class="btn btn-primary">
                            <i class="fas fa-table fa-sm text-white-50"></i>
                             <span class="icon text-white-50">
                             </span>
                             <span class="text">Tabla Periodo de Evaluaciones</span>
                            </a>
       
            
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="card">
            <div class="card-block">
              <h5 class="card-header">Usuario y Detalles</h5>
               <div class="card-body">
                <a href="ListaUsuario.jsp" class="btn btn-primary">
                         <i class="fas fa-table fa-sm text-white-50"></i>
                             <span class="icon text-white-50">
                             </span>
                             <span class="text">Tabla Usuario</span>
                         </a>  
                         
                         <div class="my-2"></div>
                         <a href="ListaUsuarioRol.jsp" class="btn btn-primary">
                         <i class="fas fa-table fa-sm text-white-50"></i>
                             <span class="icon text-white-50">
                             </span>
                             <span class="text">Tabla Usuario Rol</span>
                         </a>  
                         
                         <div class="my-2"></div>
                         <a href="ListaOpcion.jsp" class="btn btn-primary">
                         <i class="fas fa-table fa-sm text-white-50"></i>
                             <span class="icon text-white-50">
                             </span>
                             <span class="text">Tabla Opciones</span>
                         </a>  
                         
                         <div class="my-2"></div>
                         <a href="ListaRol.jsp" class="btn btn-primary">
                         <i class="fas fa-table fa-sm text-white-50"></i>
                             <span class="icon text-white-50">
                             </span>
                             <span class="text">Tabla Rol</span>
                         </a>  
                         
                         <div class="my-2"></div>
                         <a href="ListaRolOpciones.jsp" class="btn btn-primary">
                         <i class="fas fa-table fa-sm text-white-50"></i>
                             <span class="icon text-white-50">
                             </span>
                             <span class="text">Tabla Rol Opciones</span>
                         </a>  
               </div>
            
            </div>
          </div>
        </div>
      </div><br>
      
    </div>
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

</body>
</html>