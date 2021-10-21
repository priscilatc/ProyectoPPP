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
 * Servlet implementation class SLeliminarPeriodoPPP
 */
@WebServlet("/SLeliminarPeriodoPPP")
public class SLeliminarPeriodoPPP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLeliminarPeriodoPPP() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try 
		{
			PeriodoPPP p = new PeriodoPPP();
			DTPeriodoPPP dtp = new DTPeriodoPPP();
			
			int idperiodo = 0;
			
			idperiodo = Integer.parseInt(request.getParameter("id"));
			p.setIdPeriodoPPP(idperiodo);
			if(dtp.eliminarPeriodo(idperiodo))
			{
				response.sendRedirect("ListaPeriodoPPP.jsp?msj=5");
			}
			else
			{
				response.sendRedirect("ListaPeriodoPPP.jsp?msj=6");
			}
		} 
		catch (Exception e) 
		{
			System.err.println("SL PeriodoPPP: Error al eliminar PeriodoPPP " +e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}