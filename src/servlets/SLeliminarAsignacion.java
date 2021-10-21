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
 * Servlet implementation class SLeliminarAsignacion
 */
@WebServlet("/SLeliminarAsignacion")
public class SLeliminarAsignacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLeliminarAsignacion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try 
		{
			AsignacionTutores a = new AsignacionTutores();
			DTAsignacion dta = new DTAsignacion();
			
			int idasignacion = 0;
			
			idasignacion = Integer.parseInt(request.getParameter("id"));
			a.setIdAsignacion(idasignacion);
			if(dta.eliminarAsignacion(idasignacion))
			{
				response.sendRedirect("ListaAsignacionTutores.jsp?msj=5");
			}
			else
			{
				response.sendRedirect("ListaAsignacionTutores.jsp?msj=6");
			}
		} 
		catch (Exception e) 
		{
			System.err.println("SLAsignacion: Error al eliminar asignacion " +e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}