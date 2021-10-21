package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTAsignacion;
import entidades.AsignacionTutores;

/**
 * Servlet implementation class SLgestionAsignacion
 */
@WebServlet("/SLgestionAsignacion")
public class SLgestionAsignacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLgestionAsignacion() {
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
		
		DTAsignacion dta =  new DTAsignacion();
		AsignacionTutores a = new AsignacionTutores();
		
		
		a.setComentario(request.getParameter("comentario"));
		a.setId_docente(Integer.parseInt(request.getParameter("id_docente")));
		a.setIdTutor(Integer.parseInt(request.getParameter("idtutor")));
		a.setIdEstudiante(Integer.parseInt(request.getParameter("idestudiante")));
		
		switch (opc) 
		{
			case 1: 
				try 
				{
					if(dta.guardarAsignacion(a))
					{
						response.sendRedirect("ListaAsignacionTutores.jsp?msj=1");
					}
					else
					{
						response.sendRedirect("ListaAsignacionTutores.jsp?msj=2");
					}
				} 
				catch (Exception e) 
				{
					System.err.println("SLgestionAsignacion el error es: " +e.getLocalizedMessage());
					e.printStackTrace();
				}
			
				break;
				
			case 2:
				try 
				{
					a.setIdAsignacion(Integer.parseInt(request.getParameter("idasignacion")));					
					if(dta.modificarAsignacion(a)) 
					{
						response.sendRedirect("ListaAsignacionTutores.jsp?msj=3");
					}
					else
					{
						response.sendRedirect("EditarAsignacionTutores.jsp?msj=4");
					}
				} 
				catch (Exception e) 
				{
					System.err.println("SLgestionCoordinacion el error es: " +e.getLocalizedMessage());
					e.printStackTrace();
				}
				break;

			default:
				break;				
	   }
	}
}