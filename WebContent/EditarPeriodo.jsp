<%@page import="datos.* , vistas.*" %>
<%@page import="entidades.PeriodoPPP" %>
<%@page import="java.util.*, java.text.*" %>
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
    %>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Modulo Evaluación-Editar  Periodo de Evaluación</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.css" rel="stylesheet" />

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
                     <h1 class="h3 mb-2 text-gray-800">Editar  Periodo de Evaluación</h1>
                     <nav aria-label="breadcrumb">		
						 <ol class="breadcrumb">
								    <li class="breadcrumb-item"><a href="index.jsp">Inicio</a></li>
								    <li class="breadcrumb-item"><a href="ListaPeriodoPPP.jsp">Lista de Periodo de Evaluación</a></li>
								    <li class="breadcrumb-item active" aria-current="EditarPeriodo.jsp">Editar Periodo de Evaluación</li>
								  </ol>
								</nav>
                     
					<%
					String idperiodoppp = request.getParameter("idperiodoppp");
					
					PeriodoPPP p = new PeriodoPPP();
					DTPeriodoPPP dtp = new DTPeriodoPPP();
					p =dtp.getPeriodo(Integer.parseInt(idperiodoppp));
					
					String fechaInicio = "";
				 	String fechaFin = "";
				 	
				 	try{
				 		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
				 		Date date = sdf.parse(p.getFecha_inicio());
				 		Date date1 = sdf.parse(p.getFecha_fin());
				 		
				 		SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
				 		fechaInicio = format.format(date);
				 		fechaFin = format.format(date1);
				 		
				 		
				 	}catch (ParseException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
            		 %>
					<form class="form-group" method="post" action="./SLgestionPeriodo">
						<div class="form-group">
						<input hidden="true" name="idperiodoppp" value="<%=idperiodoppp%>">
						<input type="hidden" id="opcion" name="opcion" value="2" required />
							<label class="col-sm-2 control-label text-rpromedix">Nombre del Periodo: </label>
							<div class="col-sm-6">
								<input id="descripcion" name="descripcion" type="text" class="form-control has-rpromedix" 
								placeholder="Ej:PPP 1S 2020" data-toggle="tooltip" minlength="6" maxlength="30"
								data-placement="bottom" title="Min:6 Max:30" value="<%=p.getDescripcion() %>" required>
							</div>
											
						<label class="col-sm-2 control-label text-gpromedix">Fecha de inicio: </label>
						<div class="col-sm-6">
							<input id="fechainicio" name="fechainicio" type="text" class="form-control has-gpromedix" 
							placeholder="Fecha de inicio" data-toggle="tooltip" 
							data-placement="bottom" title="Ingresar la fecha de inicio" value="<%=fechaInicio %>" required>
						</div>	
						
					<label class="col-sm-2 control-label text-gpromedix">Fecha de fin: </label>
						<div class="col-sm-6">
							<input id="fechafin" name="fechafin" type="text" class="form-control has-gpromedix" 
							placeholder="Fecha de finalización" data-toggle="tooltip" 
							data-placement="bottom" title="Ingresar la fecha de finalización" value="<%=fechaFin%>" required>
						</div>	
						  
					</div>
					
				
						
					
					
					<div class="form-group has-feedback middle">
					


						<div class="col-sm-12">
							<h4 class="page-header">Acciones</h4>
							
							<div class="col-sm-6 text-center">
								<a style="cursor: pointer;"
									id="regresar" onclick="regresar();" title="Regresar"> <i
									class="fa fa-undo fa-2x"></i>
								</a>
							</div>
							<div class="col-sm-6 text-center">
								<button class="btn btn-outline-primary" type="submit" title="Guardar Cambios">
									<i class="fas fa-save fa-2x"> </i>
								</button>
							</div>							
						</div>
					</div>
				</form>
					
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
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="js/demo/chart-area-demo.js"></script>
    <script src="js/demo/chart-pie-demo.js"></script>
    
    <script>
    var getDate = function(input) {
    	  return new Date(input.date.valueOf());
    	}

    	$('#fechainicio, #fechafin').datepicker({
    	  format: "dd/mm/yyyy",
    	  language: 'es'
    	});

    	$('#fechafin').datepicker({
    	  startDate: '+6d',
    	  endDate: '+36d',
    	});

    	$('#fechainicio').datepicker({
    	  startDate: '+5d',
    	  endDate: '+35d',
    	}).on('changeDate',
    	  function(selected) {
    	    $('#fechafin').datepicker('clearDates');
    	    $('#fechafin').datepicker('setStartDate', getDate(selected));
    	  });
    </script>
    
</body>
</html>