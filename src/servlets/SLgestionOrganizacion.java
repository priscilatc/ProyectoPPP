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
 * Servlet implementation class SLgestionOrganizacion
 */
@WebServlet("/SLgestionOrganizacion")
public class SLgestionOrganizacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLgestionOrganizacion() {
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
		
		DTOrganizacion dto =  new DTOrganizacion();
		Organizacion o = new Organizacion();
		
		o.setNombre(request.getParameter("nombre"));
		o.setTelefono(request.getParameter("telefono"));
		o.setExtension_telefonica(request.getParameter("extension_telefonica"));
		o.setCorreo(request.getParameter("correo"));
		o.setDepartamento(request.getParameter("departamento"));
		o.setCiudad(request.getParameter("ciudad"));
		o.setDireccion(request.getParameter("direccion"));
		
		switch (opc) 
		{
			case 1: 
				try 
				{
					if(dto.guardarOrg(o))
					{
						response.sendRedirect("ListaOrganizacion.jsp?msj=1");
					}
					else
					{
						response.sendRedirect("ListaOrganizacion.jsp?msj=2");
					}
				} 
				catch (Exception e) 
				{
					System.err.println("SLgestionOrganizacion el error es: " +e.getLocalizedMessage());
					e.printStackTrace();
				}
			
				break;
				
			case 2:
				try 
				{
					o.setIdOrg(Integer.parseInt(request.getParameter("idorg")));
					if(dto.modificarOrg(o)) 
					{						
						response.sendRedirect("ListaOrganizacion.jsp?msj=3");
					}
					else
					{
						response.sendRedirect("EditarOrganizacion.jsp?msj=4");
					}
				} 
				catch (Exception e) 
				{
					System.err.println("SLgestionOrganizacion el error es: " +e.getLocalizedMessage());
					e.printStackTrace();
				}
				break;

			default:
				break;
		}
		
	}

}