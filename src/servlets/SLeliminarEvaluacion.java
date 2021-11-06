package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTEvaluacion;
import entidades.Evaluacion;

/**
 * Servlet implementation class SLeliminarEvaluacion
 */
@WebServlet("/SLeliminarEvaluacion")
public class SLeliminarEvaluacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLeliminarEvaluacion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try 
		{
			Evaluacion ev = new Evaluacion();
			DTEvaluacion dte = new DTEvaluacion();
			
			int idevaluacion = 0;
			
			idevaluacion = Integer.parseInt(request.getParameter("id"));
			ev.setIdEvaluacion(idevaluacion);
			if(dte.eliminarEvaluacion(idevaluacion))
			{
				response.sendRedirect("ListaEvaluacion.jsp?msj=5");
			}
			else
			{
				response.sendRedirect("ListaEvaluacion.jsp?msj=6");
			}
		} 
		catch (Exception e) 
		{
			System.err.println("SL Evaluacion: Error al eliminar evaluacion " +e.getMessage());
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