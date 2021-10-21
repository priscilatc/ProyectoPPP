package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTRol;
import entidades.Rol;

/**
 * Servlet implementation class SLeliminarRol
 */
@WebServlet("/SLeliminarRol")
public class SLeliminarRol extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLeliminarRol() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try 
		{
			Rol r = new Rol();
			DTRol dtr = new DTRol();
			
			int idrol = 0;
			
			idrol = Integer.parseInt(request.getParameter("id"));
			r.setIdRol(idrol);
			if(dtr.eliminarRol(idrol))
			{
				response.sendRedirect("ListaRol.jsp?msj=5");
			}
			else
			{
				response.sendRedirect("ListaRol.jsp?msj=6");
			}
		} 
		catch (Exception e) 
		{
			System.err.println("SL Rol: Error al eliminar Rol " +e.getMessage());
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