<%@page import="entidades.*,java.util.*" %>
<%@page import="datos.*" %>
<%@page import="vistas.*" %>
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
		
		DTUsuario dtu = new DTUsuario();
		DTPPP dtp = new DTPPP();
		VW_ppp p = new VW_ppp();
		
		p = dtp.getPPPEst(dtu.conseguirID(loginUser));
		
		if(loginUser.equals(""))
		{
			response.sendRedirect("login.jsp");
		}
		
		ArrayList<VW_usuario_rol> listaUr = new ArrayList<VW_usuario_rol>(); 

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
		              if (cargo.equals("Tutor Tecnico")){
							response.sendRedirect("404.jsp");
						}
    %>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Modulo Evaluación</title>

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
                        <h1 class="h3 mb-0 text-gray-800">Practica Pre Profesional</h1>
                       
                    </div>

                    <!-- Content Row -->
                    <div class="row">
                    	<div class="card shadow mb-4"  style="width: 20rem;">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">Datos de la PPP</h6>
						
						</div>
						
							<div class="table-responsive">
								<table class="table borderless" id="tblInfo" width="160px"
									cellspacing="0">															
									<tbody>
									<tr><th WIDTH="50" >Area Laboral</th><td><%=p.getArea_laboral()%></td></tr>
									<tr><th WIDTH="50" >Campo Profesional</th><td><%=p.getCampo_profesional()%></td></tr>
                                    <tr><th WIDTH="50" >Cargo</th><td><%=p.getCargo()%></td></tr>
                                    <tr><th WIDTH="50" >Días Laborales</th><td><%=p.getDias_laborales()%></td></tr>
                                    <tr><th WIDTH="50" >Fecha de Inicio</th><td><%=p.getFecha_inicio()%></td></tr> 
                                    <tr><th WIDTH="50" >Fecha de Finalización</th><td><%=p.getFecha_fin()%></td></tr>                                                                   
									<tr><th WIDTH="50" >Funciones</th><td><%=p.getFunciones()%></td></tr>
									<tr><th WIDTH="50" >Horario</th><td><%=p.getHorario()%></td></tr>
									<tr><th WIDTH="50" >Comentario</th><td><%=p.getComentario()%></td></tr>
									<tr><th WIDTH="50" >Organización</th><td><%=p.getOrganizacion()%></td></tr>
									</tbody>
								</table>
						
					</div>

				      </div>
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