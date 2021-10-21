package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTFacultad;
import entidades.Facultad;

/**
 * Servlet implementation class SLgestionFacultad
 */
@WebServlet("/SLgestionFacultad")
public class SLgestionFacultad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLgestionFacultad() {
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
		
		DTFacultad dtf =  new DTFacultad();
		Facultad f = new Facultad();
		
		
		f.setNombre(request.getParameter("nombre"));
		f.setCorreo(request.getParameter("correo"));
		f.setTelefono(request.getParameter("telefono"));
		f.setExtension_telefonica(request.getParameter("extension_telefonica"));		
		f.setDireccion(request.getParameter("direccion"));
		
		switch (opc) 
		{
			case 1: 
				try 
				{
					if(dtf.guardarFacultad(f))
					{
						response.sendRedirect("ListaFacultad.jsp?msj=1");
					}
					else
					{
						response.sendRedirect("ListaFacultad.jsp?msj=2");
					}
				} 
				catch (Exception e) 
				{
					System.err.println("SLgestionFacultad el error es: " +e.getLocalizedMessage());
					e.printStackTrace();
				}
			
				break;
				
			case 2:
				
				try 
				{
					f.setIdFacultad(Integer.parseInt(request.getParameter("idfacultad")));
					if(dtf.modificarFacultad(f)) 
					{
						response.sendRedirect("ListaFacultad.jsp?msj=3");
					}
					else
					{
						response.sendRedirect("EditarFacultad.jsp?msj=4");
					}
				} 
				catch (Exception e) 
				{
					System.err.println("SLgestion Facultad el error es: " +e.getLocalizedMessage());
					e.printStackTrace();
				}
				break;

			default:
				break;
		}
		
	}

}