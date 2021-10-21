package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTEvaluacion;
import entidades.Evaluacion;

/**
 * Servlet implementation class SLgestionEvaluacion
 */
@WebServlet("/SLgestionEvaluacion")
public class SLgestionEvaluacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLgestionEvaluacion() {
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
		
		DTEvaluacion dte =  new DTEvaluacion();
		Evaluacion ev = new Evaluacion();
		
		ev.setIdNivel(Integer.parseInt(request.getParameter("idnivel")));
		ev.setIdPeriodoPPP(Integer.parseInt(request.getParameter("idperiodoppp")));
		ev.setUrl(request.getParameter("url"));
		ev.setEstado(Integer.parseInt(request.getParameter("estado")));
		
		switch (opc) 
		{
			case 1: 
				try 
				{
					if(dte.guardarEvaluacion(ev))
					{
						response.sendRedirect("ListaEvaluacion.jsp?msj=1");
					}
					else
					{
						response.sendRedirect("ListaEvaluacion.jsp?msj=2");
					}
				} 
				catch (Exception e) 
				{
					System.err.println("SLgestionEvaluacion el error es: " +e.getLocalizedMessage());
					e.printStackTrace();
				}
			
				break;
				
			case 2:
				try 
				{
					ev.setIdEvaluacion(Integer.parseInt(request.getParameter("idevaluacion")));
					if(dte.modificarEvaluacion(ev)) 
					{
						response.sendRedirect("ListaEvaluacion.jsp?msj=3");
					}
					else
					{
						response.sendRedirect("ListaEvaluacion.jsp?msj=4");
					}
				} 
				catch (Exception e) 
				{
					System.err.println("SLgestionEvaluacion el error es: " +e.getLocalizedMessage());
					e.printStackTrace();
				}
				break;

			default:
				break;				
	   }
	}
}