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
 * Servlet implementation class SLgestionRolOpciones
 */
@WebServlet("/SLgestionRolOpciones")
public class SLgestionRolOpciones extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLgestionRolOpciones() {
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
		int opc = 0;
		opc = Integer.parseInt(request.getParameter("opcion"));
		
		//Objetos de Rol
		DTRolOpc dtro =  new DTRolOpc();
		RolOpciones ro = new RolOpciones();				
		
		ro.setIdRol(Integer.parseInt(request.getParameter("idrol")));
		ro.setIdOpcion(Integer.parseInt(request.getParameter("idopcion")));
	
		
		switch (opc) 
		{
			case 1: 
				try 
				{
					if(dtro.guardarRolOpc(ro))
					{
						response.sendRedirect("ListaRolOpciones.jsp?msj=1");
					}
					else
					{
						response.sendRedirect("ListaRolOpciones.jsp?msj=2");
					}
				} 
				catch (Exception e) 
				{
					System.err.println("SLgestionRolOpciones el error es: " +e.getLocalizedMessage());
					e.printStackTrace();
				}
			
				break;
			case 2:
				try 
				{
					ro.setId_rol_opciones(Integer.parseInt(request.getParameter("idrolopcion")));
					if(dtro.modificarRolOpc(ro)) 
					{						
						response.sendRedirect("ListaRolOpciones.jsp?msj=3");
					}
					else
					{
						response.sendRedirect("EditarRolOpc.jsp?msj=4");
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