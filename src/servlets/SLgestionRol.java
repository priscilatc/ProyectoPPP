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
 * Servlet implementation class SLgestionRol
 */
@WebServlet("/SLgestionRol")
public class SLgestionRol extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLgestionRol() {
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
		int opc = 0;
		opc = Integer.parseInt(request.getParameter("opcion"));
		
		//Objetos de Rol
		DTRol dtr =  new DTRol();
		Rol r = new Rol();
		
		
		r.setNombre(request.getParameter("nombre"));
		r.setDescripcion(request.getParameter("descripcion"));
		
		switch (opc) 
		{
			case 1: 
				try 
				{
					if(dtr.guardarRol(r))
					{
						response.sendRedirect("ListaRol.jsp?msj=1");
					}
					else
					{
						response.sendRedirect("ListaRol.jsp?msj=2");
					}
				} 
				catch (Exception e) 
				{
					System.err.println("SLgestionRol el error es: " +e.getLocalizedMessage());
					e.printStackTrace();
				}
			
				break;
				
			case 2:
				try 
				{
					r.setIdRol(Integer.parseInt(request.getParameter("idrol")));
					if(dtr.modificarRol(r)) 
					{						
						response.sendRedirect("ListaRol.jsp?msj=3");
					}
					else
					{
						response.sendRedirect("EditarRol.jsp?msj=4");
					}
				} 
				catch (Exception e) 
				{
					System.err.println("SLgestionRol el error es: " +e.getLocalizedMessage());
					e.printStackTrace();
				}
				break;

			default:
				break;
		}
		
	}

}