package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTOpcion;
import entidades.Opcion;

/**
 * Servlet implementation class SLgestionOpcion
 */
@WebServlet("/SLgestionOpcion")
public class SLgestionOpcion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLgestionOpcion() {
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
		
		
		DTOpcion dtr =  new DTOpcion();
		Opcion o = new Opcion();
				
		o.setDescripcion(request.getParameter("descripcion"));
		o.setUrl(request.getParameter("url"));
		
		switch (opc) 
		{
			case 1: 
				try 
				{
					if(dtr.guardarOpc(o))
					{
						response.sendRedirect("ListaOpcion.jsp?msj=1");
					}
					else
					{
						response.sendRedirect("ListaOpcion.jsp?msj=2");
					}
				} 
				catch (Exception e) 
				{
					System.err.println("SLgestionOpcion el error es: " +e.getLocalizedMessage());
					e.printStackTrace();
				}
			
				break;
				
			case 2:
				try 
				{
					o.setIdOpcion(Integer.parseInt(request.getParameter("idopcion")));
					if(dtr.modificarOpcion(o)) 
					{
						response.sendRedirect("ListaOpcion.jsp?msj=3");
					}
					else
					{
						response.sendRedirect("EditarOpcion.jsp?msj=4");
					}
				} 
				catch (Exception e) 
				{
					System.err.println("SLgestionOpcion el error es: " +e.getLocalizedMessage());
					e.printStackTrace();
				}
				break;

			default:
				break;
		}
		
	}

}