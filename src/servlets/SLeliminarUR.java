package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTUsuarioRol;
import entidades.UsuarioRol;

/**
 * Servlet implementation class SLeliminarRol
 */
@WebServlet("/SLeliminarUR")
public class SLeliminarUR extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLeliminarUR() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try 
		{
			UsuarioRol r = new UsuarioRol();
			DTUsuarioRol dtr = new DTUsuarioRol();
			
			int idusuariorol = 0;
			
			idusuariorol = Integer.parseInt(request.getParameter("id"));
			r.setIdUsuarioRol(idusuariorol);
			if(dtr.eliminarUR(idusuariorol))
			{
				response.sendRedirect("ListaUsuarioRol.jsp?msj=5");
			}
			else
			{
				response.sendRedirect("ListaUsuarioRol.jsp?msj=6");
			}
		} 
		catch (Exception e) 
		{
			System.err.println("SL UsuarioRol: Error al eliminar Rol " +e.getMessage());
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