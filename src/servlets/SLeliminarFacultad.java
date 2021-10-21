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
 * Servlet implementation class SLeliminarFacultad
 */
@WebServlet("/SLeliminarFacultad")
public class SLeliminarFacultad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLeliminarFacultad() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try 
		{
			Facultad f = new Facultad();
			DTFacultad dtf = new DTFacultad();
			
			int idfacultad = 0;
			
			idfacultad = Integer.parseInt(request.getParameter("id"));
			f.setIdFacultad(idfacultad);
			if(dtf.eliminarFacultad(idfacultad))
			{
				response.sendRedirect("ListaFacultad.jsp?msj=5");
			}
			else
			{
				response.sendRedirect("ListaFacultad.jsp?msj=6");
			}
		} 
		catch (Exception e) 
		{
			System.err.println("SL Facultad: Error al eliminar facultad " +e.getMessage());
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