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
 * Servlet implementation class SLmodificarPerfil
 */
@WebServlet("/SLmodificarPerfil")
public class SLmodificarPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLmodificarPerfil() {
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
		// TODO Auto-generated method stub
		try 
		{
			Usuario u = new Usuario();
			DTUsuario dtu = new DTUsuario();
			
			int idusuario = 0;
			String usuario, pwd, newpwd = "";
			
			idusuario = Integer.parseInt(request.getParameter("idusuario"));
			usuario = request.getParameter("usuario_edit");
			pwd = request.getParameter("oldpwd");
			newpwd = request.getParameter("newpwd");
			
			u.setIdUsuario(idusuario);
			u.setUsuario(usuario);	
			u.setPwd(newpwd);
			
			if(dtu.verificarPwd(pwd)) 
			{
				if(dtu.modificarUsuario(u))
				{
					response.sendRedirect("index.jsp?msj=1");
				}
				else
				{
					response.sendRedirect("index.jsp?msj=2");
				}
				
			}
			else {
				  response.sendRedirect("index.jsp?msj=3");
				} 
		}
				catch (Exception e) 
				{
					System.err.println("SL USUARIO: Error al modificar usuario " +e.getMessage());
					e.printStackTrace();
				}
				
		
				
	}

}
