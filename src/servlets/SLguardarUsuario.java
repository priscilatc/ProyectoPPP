package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTUsuario;
import entidades.Usuario;

/**
 * Servlet implementation class SLguardarUsuario
 */
@WebServlet("/SLguardarUsuario")
public class SLguardarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLguardarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try 
		{
			Usuario u = new Usuario();
			DTUsuario dtu = new DTUsuario();
			
			String usuario, pwd = "";
			
			usuario = request.getParameter("usuario");
			pwd = request.getParameter("pwd");
			
			u.setUsuario(usuario);
			u.setPwd(pwd);
			
			if(dtu.guardarUsuario(u))
			{
				response.sendRedirect("ListaUsuario.jsp?msj=1");
			}
			else
			{
				response.sendRedirect("ListaUsuario.jsp?msj=2");
			}
		} 
		catch (Exception e) 
		{
			System.err.println("SL USUARIO: Error al guardar usuario " +e.getMessage());
			e.printStackTrace();
		}
		
		
		
	}

}
