<%@page import="vistas.*" %>
<%@page import="datos.*" %>
<%@page import="java.util.ArrayList" %>
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

    <title>Modulo Evaluación-Agregar  PPP</title>

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
                     <h1 class="h3 mb-2 text-gray-800">Agregar  PPP</h1> 
                     <nav aria-label="breadcrumb">
								  <ol class="breadcrumb">
								    <li class="breadcrumb-item"><a href="index.jsp">Inicio</a></li>
								    <li class="breadcrumb-item"><a href="ListaPPP.jsp">Lista de PPP</a></li>
								    <li class="breadcrumb-item active" aria-current="AgregarPPP.jsp">Agregar PPP</li>
								  </ol>
								</nav>               

					<form class="form-group" method="post" action="./SLgestionPPP">
						<div class="form-group">
						<input type="hidden" id="opcion" name="opcion" value="1" required />
							<label class="col-sm-2 control-label text-rpromedix">Area laboral: </label>
							<div class="col-sm-6">
								<input id="arealaboral" name="arealaboral" type="text" class="form-control has-rpromedix" 
								placeholder="Ej:Construcción de software" data-toggle="tooltip"  minlength="4" maxlength="30"
								data-placement="bottom" title="Min:4 Max:30" required>
							</div>
					      <label class="col-sm-2 control-label text-rpromedix">Campo profesional: </label>
							<div class="col-sm-6">
								<input id="campoprofesional" name="campoprofesional" type="text" class="form-control has-rpromedix" 
								placeholder="Ej:Desarollo de software" data-toggle="tooltip" minlength="4" maxlength="30"
								data-placement="bottom" title="Min:4 Max:30" required>							
							</div>
							
							<label class="col-sm-2 control-label text-rpromedix">Cargo: </label>
							<div class="col-sm-6">
								<input id="cargo" name="cargo" type="text" class="form-control has-rpromedix" 
								placeholder="Ej:Gerente de sistemas" data-toggle="tooltip" minlength="4" maxlength="20"
								data-placement="bottom" title="Min:4 Max:20" required>
							</div>
				
						     <label class="col-sm-2 control-label text-rpromedix">Dias laborales: </label>
							<div class="col-sm-6">
								<input id="diaslaborales" name="diaslaborales" type="text" class="form-control has-rpromedix" 
								placeholder="Ej:Lunes a Martes" data-toggle="tooltip" minlength="5" maxlength="50"
								data-placement="bottom" title="Min:5 Max:50" required>
							</div>
							
						<label class="col-sm-2 control-label text-gpromedix">Fecha de inicio: </label>
						<div class="col-sm-6">
							<input id="fechainicio" name="fechainicio" type="date" class="form-control has-gpromedix" 
							placeholder="Fecha de inicio" data-toggle="tooltip" 
							data-placement="bottom" title="Ingresar la fecha de inicio" required>
						</div>	
						
						<label class="col-sm-2 control-label text-gpromedix">Fecha de fin: </label>
						<div class="col-sm-6">
							<input id="fechafin" name="fechafin" type="date" class="form-control has-gpromedix" 
							placeholder="Fecha de Fin" data-toggle="tooltip"  
							data-placement="bottom" title="Ingresar la fecha de Fin" required>
						</div>	
							
							<label class="col-sm-2 control-label text-rpromedix">Funciones: </label>
							<div class="col-sm-6">
								<input id="funciones" name="funciones" type="text" class="form-control has-rpromedix" 
								placeholder="Ej:Ayuda al desarrollo de un modulo" data-toggle="tooltip"  minlength="6" maxlength="50"
								data-placement="bottom" title="Min:6 Max:50" required>
							</div>
					      <label class="col-sm-2 control-label text-rpromedix">Horario: </label>
							<div class="col-sm-6">
								<input id="horario" name="horario" type="text" class="form-control has-rpromedix" 
								placeholder="Ej:06:00-19:00" data-toggle="tooltip"  minlength="5" maxlength="50"
								data-placement="bottom" title="Min:5 Max:50" required>
							</div>
				
						     <label class="col-sm-2 control-label text-rpromedix">Comentario: </label>
							<div class="col-sm-6">
								<input id="comentario" name="comentario" type="text" class="form-control has-rpromedix" 
								placeholder="Ej:Lo cambiaron de area" data-toggle="tooltip" minlength="10" maxlength="50"
								data-placement="bottom" title="Min:10 Max:50">
							</div>
							
							<label class="col-sm-2 control-label text-rpromedix">Estudiante:</label>
							    <div class="col-sm-6">
							      <select id="idestudiante" name="idestudiante"class="form-control" required>
							        <option value="">Seleccione</option>
							  		<%
							  		DTEstudiante dte = new DTEstudiante();
									ArrayList<VW_estudiante> listaEstudiantes = new ArrayList<VW_estudiante>();
									listaEstudiantes = dte.listarEstudiantes();
									for(VW_estudiante f : listaEstudiantes)
									{
									%>
									<option value="<%=f.getIdestudiante() %>"><%=f.getNombre() %></option>
									<%
									}
									%>				        
							      </select>
							      <div>
							  	 	 <a href="https://github.com/danielfarrell/bootstrap-combobox"></a>
							  	</div>
							    </div>
							
							
							<label class="col-sm-2 control-label text-rpromedix">Organizacion:</label>
							    <div class="col-sm-6">
							      <select id="idorg" name="idorg"class="form-control" required>
							        <option value="">Seleccione</option>
							  		<%
							  		DTOrganizacion dto = new DTOrganizacion();
									ArrayList<VW_organizacion> listaOrg = new ArrayList<VW_organizacion>();
									listaOrg = dto.listarOrg();
									for(VW_organizacion f : listaOrg)
									{
									%>
									<option value="<%=f.getIdOrg() %>"><%=f.getNombre() %></option>
									<%
									}
									%>					        
							      </select>
							       <div>
								    <a href="https://github.com/danielfarrell/bootstrap-combobox"></a>
								  </div>
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
								<button class="btn btn-outline-primary" type="submit" title="Guardar Registro">
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

    <!-- Page level custom scripts -->
    <script src="js/demo/chart-area-demo.js"></script>
    <script src="js/demo/chart-pie-demo.js"></script>
    

</body>
</html>