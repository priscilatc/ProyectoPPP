<%@page import="vistas.*" %>
<%@page import="datos.*" %>
<%@page import="java.util.*" %>
<%@page session="true" %>
<% 

HttpSession usuario=request.getSession();
String cargo = (String) usuario.getAttribute("rol");     
                          
%>

<nav
	class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
	<h1 class="h3 mb-2 text-blue-800">Modulo Evaluación</h1>

	<!-- Sidebar Toggle (Topbar) -->
	<button id="sidebarToggleTop"
		class="btn btn-link d-md-none rounded-circle mr-3">
		<i class="fa fa-bars"></i>
	</button>

	<!-- Topbar Navbar -->
	<ul class="navbar-nav ml-auto">
	

		<!-- Nav Item - Search Dropdown (Visible Only XS) -->
		<li class="nav-item dropdown no-arrow d-sm-none"><a
			class="nav-link dropdown-toggle" href="#" id="searchDropdown"
			role="button" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="false"> <i class="fas fa-search fa-fw"></i>
		</a> <!-- Dropdown - Messages -->
			<div
				class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
				aria-labelledby="searchDropdown">
				<form class="form-inline mr-auto w-100 navbar-search">
					<div class="input-group">
						<input type="text" class="form-control bg-light border-0 small"
							placeholder="Search for..." aria-label="Search"
							aria-describedby="basic-addon2">
						<div class="input-group-append">
							<button class="btn btn-primary" type="button">
								<i class="fas fa-search fa-sm"></i>
							</button>
						</div>
					</div>
				</form>
			</div></li>

		<!-- Nav Item - User Information -->
		<li class="nav-item dropdown no-arrow"><a
			class="nav-link dropdown-toggle" href="#" id="userDropdown"
			role="button" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="false"> <span
				class="mr-2 d-none d-lg-inline text-gray-600 small"> <%=(String)session.getAttribute("login")%> </span> <img class="img-profile rounded-circle"
				src="img/undraw_profile.svg">
		</a> 
		<!--  Dropdown - User Information-->
			<div
				class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
				aria-labelledby="userDropdown">
				<%
				
				if (cargo.equals("Estudiante")){
				%>
				<a class="dropdown-item" href="ViewPerfil.jsp"> <i
					class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Perfil
				</a> 
				<%
				} if(cargo.equals("Tutor Tecnico")){
				%>
				<a class="dropdown-item" href="ViewPerfilT.jsp"> <i
					class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Perfil
				</a>
				<%
				} if (cargo.equals("Tutor Academico")){
				%>
				<a class="dropdown-item" href="ViewPerfilD.jsp"> <i
					class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Perfil
				</a>
				<%
				} if(cargo.equals("")){
				%>
				<a class="dropdown-item" href="#"> <i
					class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Perfil
				</a>
				<%
				}
				%>
				 
				<div class="dropdown-divider"></div>
				<a class="dropdown-item" href="#" data-toggle="modal"
					data-target="#logoutModal"> <i
					class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
					Salir
				</a>
			</div></li>

	</ul>

</nav>