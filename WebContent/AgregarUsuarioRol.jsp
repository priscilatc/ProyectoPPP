<%@page import="vistas.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.*"%>
<%@page import="entidades.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
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

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Modulo Evaluación - Agregar Usuario Rol</title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">


<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">

<!-- Custom styles for this page -->
<link href="vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet">

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
					<h1 class="h3 mb-2 text-gray-800">Agregar Roles al usuario</h1>
					 <hr>
						
						<a href="index.jsp">Inicio</a> /
						<a href="ListaUsuario.jsp">Lista de Usuarios</a> /
						<a href="AgregarUsuarioRol.jsp"> Agregar Roles al usuario</a> 
					<hr>
                     
					<%
						String idrol = request.getParameter("idrol");
						
						String idusuario = request.getParameter("idusuario");
						
						Usuario u = new Usuario();
						u = dtu.getUsuario(Integer.parseInt(idusuario));
						
						Rol r = new Rol();
						DTRol dtr =  new DTRol();
            		 %>
					<form class="form-group" method="post" action="./SLgestionUsuarioRol">
						<div class="form-group">
						
							<label class="col-sm-2 control-label">Usuario: </label>
							<div class="col-sm-6">
							<input hidden="true" name="idusuario" value="<%=idusuario%>">
								<input type="hidden" id="opcion" name="opcion" value="1" required />
								<input id="usuarionombre" name="usuarionombre" class="form-control has-gpromedix" disabled="disabled"/>
							</div>	
							<label class="col-sm-2 control-label">Rol: </label>
							<div class="col-sm-6">
								<select name="idrol" id="idrol" class="custom-select" required>
								<option value="">Seleccione</option>
								<%
								ArrayList<VW_rol> listaRol = new ArrayList<VW_rol>();
								listaRol = dtr.listarRoles();
								for(VW_rol r1 : listaRol)
								{
								%>
								<option value="<%=r1.getIdRol() %>"><%=r1.getNombre() %></option>
								<%
								}
								%>
								</select>
								
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
									<i class="fas fa-save"> </i>
								</button>
							</div>							
						</div>
					</div>
				</form>
					
				</div>
				
				<h1 class="h3 mb-2 text-gray-800">Roles de Usuario</h1>


					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">Roles del usuario</h6>
							
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="rolusuario" width="100%"	cellspacing="0">
								
									<thead>
										<tr>
											<th>ID</th>
											<th>Usuario</th>
											<th>Descripcion</th>
											
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>ID</th>
											<th>Usuario</th>
											<th>Descripcion</th>
											
										</tr>
									</tfoot>
									<tbody>
									<%
										ArrayList<VW_usuario_rol> listaRolUsuario = new ArrayList<VW_usuario_rol>();

										listaRolUsuario = dtr.getRolUsuario(Integer.parseInt(idusuario));

										for (VW_usuario_rol vwr : listaRolUsuario) {
								       %>
										
										<tr>
											<td><%=vwr.getIdrol()%></td>
											<td><%=vwr.getUsuario() %></td>										
											<td><%=vwr.getRol()%></td>
										</tr>
											<%
										}
											%>
									</tbody>
								</table>
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
		<a class="scroll-to-top rounded" href="#page-top"> <i
			class="fas fa-angle-up"></i>
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
		<script src="vendor/datatables/jquery.dataTables.min.js"></script>
		<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

		<!-- Page level custom scripts -->
		<script src="js/demo/datatables-demo.js"></script>
		<script type="text/javascript">
		
		$(document).ready(function()
		{
			$("#usuarionombre").val("<%=u.getUsuario() %>");
			
		});
		</script>
		<script>
	    $(document).ready(function() {
	        $('#rolusuario').DataTable({
	            responsive: true,
	            language: {
	                url: './vendor/datatables/es-ar.json'
	            }
	        });
	    });
	    </script>
		
</body>

</html>