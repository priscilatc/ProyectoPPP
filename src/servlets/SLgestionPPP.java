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
 * Servlet implementation class SLgestionPPP
 */
@WebServlet("/SLgestionPPP")
public class SLgestionPPP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLgestionPPP() {
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
		
		
		DTPPP dtp =  new DTPPP();
		PPP p = new PPP();
				
		p.setArea_laboral(request.getParameter("arealaboral"));
		p.setCampoprofesional(request.getParameter("campoprofesional"));
		p.setCargo(request.getParameter("cargo"));
		p.setDias_laborales(request.getParameter("diaslaborales"));
		p.setFecha_inicio(request.getParameter("fechainicio"));
		p.setFecha_fin(request.getParameter("fechafin"));
		p.setFunciones(request.getParameter("funciones"));
		p.setHorario(request.getParameter("horario"));
		p.setComentario(request.getParameter("comentario"));
		p.setIdEstudiante(Integer.parseInt(request.getParameter("idestudiante")));
		p.setIdOrg(Integer.parseInt(request.getParameter("idorg")));
		
		switch (opc) 
		{
			case 1: 
				try 
				{
					if(dtp.guardarPPP(p))
					{
						response.sendRedirect("ListaPPP.jsp?msj=1");
					}
					else
					{
						response.sendRedirect("ListaPPP.jsp?msj=2");
					}
				} 
				catch (Exception e) 
				{
					System.err.println("SLgestionPPP el error es: " +e.getLocalizedMessage());
					e.printStackTrace();
				}
			
				break;
				
			case 2:
				try 
				{
					p.setIdPPP(Integer.parseInt(request.getParameter("idppp")));
					if(dtp.modificarPPP(p)) 
					{
						response.sendRedirect("ListaPPP.jsp?msj=3");
					}
					else
					{
						response.sendRedirect("EditarPPP.jsp?msj=4");
					}
				} 
				catch (Exception e) 
				{
					System.err.println("SLgestionPPP el error es: " +e.getLocalizedMessage());
					e.printStackTrace();
				}
				break;

			default:
				break;
		}
		
	}

}