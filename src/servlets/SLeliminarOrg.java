package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTOrganizacion;
import entidades.Organizacion;

/**
 * Servlet implementation class SLeliminarOrg
 */
@WebServlet("/SLeliminarOrg")
public class SLeliminarOrg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLeliminarOrg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try 
		{
			Organizacion o = new Organizacion();
			DTOrganizacion dto = new DTOrganizacion();
			
			int idorg = 0;
			
			idorg = Integer.parseInt(request.getParameter("id"));
			o.setIdOrg(idorg);
			if(dto.eliminarOrg(idorg))
			{
				response.sendRedirect("ListaOrganizacion.jsp?msj=5");
			}
			else
			{
				response.sendRedirect("ListaOrganizacion.jsp?msj=6");
			}
		} 
		catch (Exception e) 
		{
			System.err.println("SL Org: Error al eliminar organizacion " +e.getMessage());
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