<%@page session="true"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String cargo = (String) session.getAttribute("rol");
%>
<ul
	class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
	id="accordionSidebar">

	<!-- Sidebar - Brand -->
	<a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.jsp">
			<img src="./img/logo-UCA-2021-blanco.png" width="60%">
	</a>
	

	<!-- Divider -->
	<hr class="sidebar-divider my-0">


	<!-- Divider -->
	<hr class="sidebar-divider">


	<!-- Nav Item - Pages Collapse Menu -->

	<!-- Nav Item - Utilities Collapse Menu -->
	<!-- Divider -->
	<hr class="sidebar-divider">
	<%
		if (cargo.equals("Administrador")){
	%>
	<!-- Nav Item - Seguridad-->
	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapseUtilities"
		aria-expanded="true" aria-controls="collapseUtilities"> <i
			class="fas fa-fw fa-wrench"></i> <span>Administración de Seguridad</span>
	</a>
		<div id="collapseUtilities" class="collapse"
			aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header">Gestiones</h6>
				<a class="collapse-item" href="ListaUsuario.jsp">Gestión de Usuarios</a> <a
					class="collapse-item" href="ListaRol.jsp">Gestión de Roles</a> <a
					class="collapse-item" href="ListaOpcion.jsp">Gestión de Opciones</a>
			</div>
		</div></li>
		
	<!-- Nav Item - Administración de Tutores-->
	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapseTwo"
		aria-expanded="true" aria-controls="collapseTwo"> <i
			class="fas fa-fw fa-wrench"></i> <span>Administración de Tutores</span>
	</a>
		<div id="collapseTwo" class="collapse"
			aria-labelledby="headingTwo" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header">Gestiones</h6>
				<a class="collapse-item" href="ListaDocente.jsp">Gestión de Tutor Academico</a> <a
					class="collapse-item" href="ListaTutorTecnico.jsp">Gestión de Tutor Técnico</a> 
			</div>
		</div></li>
		
	<!-- Nav Item - Administración de Evaluaciones-->
	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapsePages"
		aria-expanded="true" aria-controls="collapsePages"> <i
			class="fas fa-fw fa-wrench"></i> <span>Administración de Evaluaciones</span>
	</a>
		<div id="collapsePages" class="collapse"
			aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header">Gestiones</h6>
				<a class="collapse-item" href="ListaEvaluacion.jsp">Gestión de Evaluación</a> 
				<a class="collapse-item" href="ListaNivel.jsp">Gestión de Nivel 180</a> 
				<a class="collapse-item" href="ListaPeriodoPPP.jsp">Gestión de Período</a>
			</div>
		</div></li>
	
	<li class="nav-item">
       <a class="nav-link" href="ControladorAdmin.jsp">
           <i class="fas fa-fw fa-table"></i>
           <span>Mapa de Sitios</span></a>
     </li>
	<%
		} if(cargo.equals("Tutor Academico")){
	%>
	<li class="nav-item">
    
	<li class="nav-item">
        <a class="nav-link" href="ListaAlumnosAsignadosD.jsp">
          <i class="fas fa-user-graduate"></i>      
          <span>Alumnos asignados </span></a>
     </li>
     
	<%
		} if(cargo.equals("Estudiante")){
	%>
	<li class="nav-item">
    
	<li class="nav-item">
       <a class="nav-link" href="ViewPPP.jsp">
           <i class="fas fa-briefcase"></i>
           <span>Practica Pre-Profesional</span></a>
     </li>
		
	<!-- Nav Item - Administración de Evaluaciones-->
	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapseTwo"
		aria-expanded="true" aria-controls="collapseTwo"> 
		<i class="fab fa-wpforms"></i> <span>Administración de evaluaciones</span>
	</a>
		<div id="collapseTwo" class="collapse"
			aria-labelledby="headingTwo" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header">Evaluaciones</h6>
				<a href="ViewAutoevaluacion.jsp" class="collapse-item" >Autoevaluación</a> 
			</div>
		</div></li>
		
	<!-- Nav Item - Administración de Tutores-->
	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapsePages"
		aria-expanded="true" aria-controls="collapsePages"> <i class="fas fa-chalkboard-teacher"></i>
		 <span>Tutores asignados</span>
	</a>
		<div id="collapsePages" class="collapse"
			aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header">Tutores</h6>
				<a class="collapse-item" href="ViewTutorTecnico.jsp">Tutor Técnico</a> 
				<a class="collapse-item" href="ViewTutorAcademico.jsp">Tutor Academico</a> 
			</div>
		</div></li>
	
	<%
	} if (cargo.equals("Tutor Tecnico")){
	%>
	<li class="nav-item">
    
	<li class="nav-item">
        <a class="nav-link" href="ListaAlumnosAsignadosT.jsp">
          <i class="fas fa-user-graduate"></i>      
          <span>Alumnos asignados </span></a>
     </li>
     
     
         <li class="nav-item">
           <a class="nav-link" href="ViewEvaluacionTecnica.jsp">
           <i class="fab fa-wpforms"></i> 
           <span>Evaluacion tecnica </span></a>
             </li>
	<%
	}
	%>
	

	<!-- Divider -->
	<hr class="sidebar-divider d-none d-md-block">

	<!-- Sidebar Toggler (Sidebar) -->
	<div class="text-center d-none d-md-inline">
		<button class="rounded-circle border-0" id="sidebarToggle"></button>
	</div>

	

</ul>