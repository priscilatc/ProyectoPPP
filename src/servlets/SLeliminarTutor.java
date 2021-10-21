package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTTutorTecnico;
import entidades.TutorTecnico;

/**
 * Servlet implementation class SLeliminarTutor
 */
@WebServlet("/SLeliminarTutor")
public class SLeliminarTutor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLeliminarTutor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try 
		{
			TutorTecnico t = new TutorTecnico();
			DTTutorTecnico dtn = new DTTutorTecnico();
			
			int idtutor = 0;
			
			idtutor = Integer.parseInt(request.getParameter("id"));
			t.setIdTutor(idtutor);
			if(dtn.eliminarTutor(idtutor))
			{
				response.sendRedirect("ListaTutorTecnico.jsp?msj=5");
			}
			else
			{
				response.sendRedirect("ListaTutorTecnico.jsp?msj=6");
			}
		} 
		catch (Exception e) 
		{
			System.err.println("SL TutorTecnico: Error al eliminar TutorTecnico" +e.getMessage());
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