package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTDocente;
import entidades.Docente;

/**
 * Servlet implementation class SLeliminarDocente
 */
@WebServlet("/SLeliminarDocente")
public class SLeliminarDocente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLeliminarDocente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try 
		{
			Docente d = new Docente();
			DTDocente dtd = new DTDocente();
			
			int iddocente = 0;
			
			iddocente = Integer.parseInt(request.getParameter("id"));
			d.setIdDocente(iddocente);
			if(dtd.eliminarDocente(iddocente))
			{
				response.sendRedirect("ListaDocente.jsp?msj=5");
			}
			else
			{
				response.sendRedirect("ListaDocente.jsp?msj=6");
			}
		} 
		catch (Exception e) 
		{
			System.err.println("SL Docente: Error al eliminar Docente " +e.getMessage());
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