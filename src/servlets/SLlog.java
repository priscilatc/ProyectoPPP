package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datos.DTUsuario;
import entidades.Usuario;

/**
 * Servlet implementation class SLlogin
 */
@WebServlet("/SLlog")
public class SLlog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLlog() {
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
		try {
			Usuario u = new Usuario();
			DTUsuario dtu = new DTUsuario();
			
			
			String usuario = request.getParameter("usuario");
			String pwd = request.getParameter("pwd");

			u.setUsuario(usuario);
			u.setPwd(pwd);
						
			if(dtu.dtverificarLogin(u)){
				
				
				HttpSession hts = request.getSession(true);
				 hts.setMaxInactiveInterval(60*30);
				 hts.setAttribute("login", usuario);
				 
				 response.sendRedirect("index.jsp");
			}
			else{
				response.sendRedirect("login.jsp?msj=1");
			}
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("ERROR EN SL_LOGIN: "+e.getMessage());
		}		 
		 
	}

}
