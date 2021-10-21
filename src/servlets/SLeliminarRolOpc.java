package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTRolOpc;
import entidades.RolOpciones;

/**
 * Servlet implementation class SLeliminarRolOpc
 */
@WebServlet("/SLeliminarRolOpc")
public class SLeliminarRolOpc extends HttpServlet{
	private static final long serialVersionUID=1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public SLeliminarRolOpc() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try 
		{
			RolOpciones r = new RolOpciones();
			DTRolOpc dtr = new DTRolOpc();
			
			int idrolopc = 0;
			
			idrolopc = Integer.parseInt(request.getParameter("id"));
			r.setId_rol_opciones(idrolopc);
			if(dtr.eliminarRol(idrolopc))
			{
				response.sendRedirect("ListaRolOpciones.jsp?msj=5");
			}
			else
			{
				response.sendRedirect("ListaRolOpciones.jsp?msj=6");
			}
		} 
		catch (Exception e) 
		{
			System.err.println("SL Rol Opc: Error al eliminar Rol " +e.getMessage());
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
