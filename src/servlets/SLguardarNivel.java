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
 * Servlet implementation class SLguardarNivel
 */
@WebServlet("/SLguardarNivel")
public class SLguardarNivel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLguardarNivel() {
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
			Nivel180 n = new Nivel180();
			DTNivel dtn = new DTNivel();
			
			String descripcion = "";
			
			descripcion = request.getParameter("descripcion");
			
			n.setDescripcion(descripcion);
			
			if(dtn.guardarNivel(n))
			{
				response.sendRedirect("ListaNivel.jsp?msj=1");
			}
			else
			{
				response.sendRedirect("ListaNivel.jsp?msj=2");
			}
		} 
		catch (Exception e) 
		{
			System.err.println("SL Nivel: Error al guardar nivel " +e.getMessage());
			e.printStackTrace();
		}
		
		
		
	}

}