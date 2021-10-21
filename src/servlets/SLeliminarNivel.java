package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTNivel;
import entidades.Nivel180;

/**
 * Servlet implementation class SLeliminarNivel
 */
@WebServlet("/SLeliminarNivel")
public class SLeliminarNivel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLeliminarNivel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try 
		{
			Nivel180 n = new Nivel180();
			DTNivel dtn = new DTNivel();
			
			int idnivel = 0;
			
			idnivel = Integer.parseInt(request.getParameter("id"));
			n.setIdNivel(idnivel);
			if(dtn.eliminarNivel(idnivel))
			{
				response.sendRedirect("ListaNivel.jsp?msj=5");
			}
			else
			{
				response.sendRedirect("ListaNivel.jsp?msj=6");
			}
		} 
		catch (Exception e) 
		{
			System.err.println("SL Nivel: Error al eliminar nivel " +e.getMessage());
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