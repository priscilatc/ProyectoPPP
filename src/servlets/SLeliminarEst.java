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
 * Servlet implementation class SLeliminarEst
 */
@WebServlet("/SLeliminarEst")
public class SLeliminarEst extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLeliminarEst() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try 
		{
			Estudiante es = new Estudiante();
			DTEstudiante dte = new DTEstudiante();
			
			int idestudiante = 0;
			
			idestudiante = Integer.parseInt(request.getParameter("id"));
			es.setIdEstudiante(idestudiante);
			if(dte.eliminarEst(idestudiante))
			{
				response.sendRedirect("ListaEstudiante.jsp?msj=5");
			}
			else
			{
				response.sendRedirect("ListaEstudiante.jsp?msj=6");
			}
		} 
		catch (Exception e) 
		{
			System.err.println("SL Estudiante: Error al eliminar estudiante " +e.getMessage());
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