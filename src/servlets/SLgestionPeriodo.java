package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTPeriodoPPP;
import entidades.PeriodoPPP;

/**
 * Servlet implementation class SLgestionPeriodo
 */
@WebServlet("/SLgestionPeriodo")
public class SLgestionPeriodo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLgestionPeriodo() {
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
		DTPeriodoPPP dtp =  new DTPeriodoPPP();
		PeriodoPPP p = new PeriodoPPP();
				
		p.setDescripcion(request.getParameter("descripcion"));
		p.setFecha_inicio(request.getParameter("fechainicio"));
		p.setFecha_fin(request.getParameter("fechafin"));
		
		switch (opc) 
		{
			case 1: 
				try 
				{
					if(dtp.guardarPeriodo(p))
					{
						response.sendRedirect("ListaPeriodoPPP.jsp?msj=1");
					}
					else
					{
						response.sendRedirect("ListaPeriodoPPP.jsp?msj=2");
					}
				} 
				catch (Exception e) 
				{
					System.err.println("SLgestionPeriodo el error es: " +e.getLocalizedMessage());
					e.printStackTrace();
				}
			
				break;
				
			case 2:
				try 
				{
					p.setIdPeriodoPPP(Integer.parseInt(request.getParameter("idperiodoppp")));
					if(dtp.modificarPeriodo(p)) 
					{						
						response.sendRedirect("ListaPeriodoPPP.jsp?msj=3");
					}
					else
					{
						response.sendRedirect("ListaPeriodoPPP.jsp?msj=4");
					}
				} 
				catch (Exception e) 
				{
					System.err.println("SLgestionPeriodo el error es: " +e.getLocalizedMessage());
					e.printStackTrace();
				}
				break;

			default:
				break;
		}
		
	}

}