package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTCoordinacion;
import entidades.Coordinacion;

/**
 * Servlet implementation class SLeliminarCoordinacion
 */
@WebServlet("/SLeliminarCoordinacion")
public class SLeliminarCoordinacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLeliminarCoordinacion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try 
		{
			Coordinacion n = new Coordinacion();
			DTCoordinacion dtn = new DTCoordinacion();
			
			int idcoordinacion = 0;
			
			idcoordinacion = Integer.parseInt(request.getParameter("idcoordinacion"));
			n.setIdCoordinacion(idcoordinacion);
			if(dtn.eliminarCoordinacion(idcoordinacion))
			{
				response.sendRedirect("ListaCoordinacion.jsp?msj=5");
			}
			else
			{
				response.sendRedirect("ListaCoordinacion.jsp?msj=6");
			}
		} 
		catch (Exception e) 
		{
			System.err.println("SL Coordinacion: Error al eliminar coordinacion " +e.getMessage());
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