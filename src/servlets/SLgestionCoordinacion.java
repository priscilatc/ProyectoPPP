package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTCoordinacion;
import entidades.Coordinacion;

/**
 * Servlet implementation class SLgestionCoordinacion
 */
@WebServlet("/SLgestionCoordinacion")
public class SLgestionCoordinacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLgestionCoordinacion() {
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
		
		DTCoordinacion dtc =  new DTCoordinacion();
		Coordinacion c = new Coordinacion();
		
		
		c.setNombre(request.getParameter("nombre"));
		c.setCorreo(request.getParameter("correo"));
		c.setTelefono(request.getParameter("telefono"));
		c.setExtension_telefonica(request.getParameter("extension_telefonica"));		
		c.setDireccion(request.getParameter("direccion"));
		c.setIdFacultad(Integer.parseInt(request.getParameter("idfacultad")));
		
		switch (opc) 
		{
			case 1: 
				try 
				{
					if(dtc.guardarCoordinacion(c))
					{
						response.sendRedirect("ListaCoordinacion.jsp?msj=1");
					}
					else
					{
						response.sendRedirect("ListaCoordinacion.jsp?msj=2");
					}
				} 
				catch (Exception e) 
				{
					System.err.println("SLgestionCoordinacion el error es: " +e.getLocalizedMessage());
					e.printStackTrace();
				}
			
				break;
				
			case 2:
				try 
				{
					c.setIdCoordinacion(Integer.parseInt(request.getParameter("idcoordinacion")));
					
					if(dtc.modificarCoordinacion(c)) 
					{
						response.sendRedirect("ListaCoordinacion.jsp?msj=3");
					}
					else
					{
						response.sendRedirect("EditarCoordinacion.jsp?msj=4");
					}
				} 
				catch (Exception e) 
				{
					System.err.println("SLgestionCoordinacion el error es: " +e.getLocalizedMessage());
					e.printStackTrace();
				}
				break;

			default:
				break;				
	   }
	}
}