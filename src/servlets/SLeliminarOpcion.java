package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTOpcion;
import entidades.Opcion;

/**
 * Servlet implementation class SLeliminarOpcion
 */
@WebServlet("/SLeliminarOpcion")
public class SLeliminarOpcion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLeliminarOpcion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try 
		{
			Opcion o = new Opcion();
			DTOpcion dto = new DTOpcion();
			
			int idopcion = 0;
			
			idopcion = Integer.parseInt(request.getParameter("id"));
			o.setIdOpcion(idopcion);
			if(dto.eliminarOpc(idopcion))
			{
				response.sendRedirect("ListaOpcion.jsp?msj=5");
			}
			else
			{
				response.sendRedirect("ListaOpcion.jsp?msj=6");
			}
		} 
		catch (Exception e) 
		{
			System.err.println("SL Opcion: Error al eliminar opcion " +e.getMessage());
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