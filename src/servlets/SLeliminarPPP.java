package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTPPP;
import entidades.PPP;

/**
 * Servlet implementation class SLeliminarPPP
 */
@WebServlet("/SLeliminarPPP")
public class SLeliminarPPP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLeliminarPPP() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try 
		{
			PPP p = new PPP();
			DTPPP dtp = new DTPPP();
			
			int idppp = 0;
			
			idppp = Integer.parseInt(request.getParameter("id"));
			p.setIdPPP(idppp);
			if(dtp.eliminarPPP(idppp))
			{
				response.sendRedirect("ListaPPP.jsp?msj=5");
			}
			else
			{
				response.sendRedirect("ListaPPP.jsp?msj=6");
			}
		} 
		catch (Exception e) 
		{
			System.err.println("SL PPP: Error al eliminar PPP " +e.getMessage());
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