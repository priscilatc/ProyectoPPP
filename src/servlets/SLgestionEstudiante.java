package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTEstudiante;
import entidades.Estudiante;

/**
 * Servlet implementation class SLgestionEstudiante
 */
@WebServlet("/SLgestionEstudiante")
public class SLgestionEstudiante extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLgestionEstudiante() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int opc = 0;
		opc = Integer.parseInt(request.getParameter("opcion"));
		
		DTEstudiante dte =  new DTEstudiante();
		Estudiante es = new Estudiante();
		
		int idusuario=0;
		String carneuca = "";
		
		es.setNombres(request.getParameter("nombres"));
		es.setApellidos(request.getParameter("apellidos"));
		es.setCorreoInstitucional(request.getParameter("correoinstitucional"));
		es.setCelular(request.getParameter("celular"));
		es.setCondicion(request.getParameter("condicion"));
		carneuca = request.getParameter("carneuca");
	    es.setSexo(Integer.parseInt(request.getParameter("sexo")));
	    es.setIdCoordinacion(Integer.parseInt(request.getParameter("idcoordinacion")));
	    idusuario = Integer.parseInt(request.getParameter("idusuario"));
		es.setIdUsuario(idusuario);
		es.setCarneuca(carneuca);
	    
		switch (opc) 
		{
			case 1: 
				
				try 
				{
					if(dte.verificarUsuarioExiste(idusuario)) {
						response.sendRedirect("AgregarEstudiante.jsp?msj=1");
					}
					else {
					if(dte.guardarEst(es))
					{
						response.sendRedirect("ListaEstudiante.jsp?msj=1");
					}
					else
					{
						response.sendRedirect("ListaEstudiante.jsp?msj=2");
					}
					}
					
				} 
				catch (Exception e) 
				{
					System.err.println("SLgestionEstudiante el error es: " +e.getLocalizedMessage());
					e.printStackTrace();
				}
			
				break;
				
			case 2:
				try 
				{
					es.setIdEstudiante(Integer.parseInt(request.getParameter("idestudiante")));
					if(dte.modificarEst(es)) 
					{
						response.sendRedirect("ListaEstudiante.jsp?msj=3");
					}
					else
					{
						response.sendRedirect("EditarEstudiante.jsp?msj=4");
					}
				} 
				catch (Exception e) 
				{
					System.err.println("SLgestionEstudiante el error es: " +e.getLocalizedMessage());
					e.printStackTrace();
				}
				break;

			default:
				break;				
	   }
	}
}