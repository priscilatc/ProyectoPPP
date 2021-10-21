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
 * Servlet implementation class SLgestionTutor
 */
@WebServlet("/SLgestionTutor")
public class SLgestionTutor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLgestionTutor() {
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
		
		DTTutorTecnico dtt =  new DTTutorTecnico();
		TutorTecnico t = new TutorTecnico();
		
		
		t.setNombres(request.getParameter("nombres"));
		t.setApellidos(request.getParameter("apellidos"));
		t.setDireccion(request.getParameter("direccion"));
		t.setCelular(request.getParameter("celular"));
		t.setCargo(request.getParameter("cargo"));
		t.setTrato(request.getParameter("trato"));
		t.setCorreo(request.getParameter("correo"));
		t.setCarneUca(request.getParameter("carneUca"));
		t.setCedula(request.getParameter("cedula"));
		t.setIdOrg(Integer.parseInt(request.getParameter("idorg")));
		t.setIdUsuario(Integer.parseInt(request.getParameter("idusuario")));
		
		switch (opc) 
		{
			case 1: 
				try 
				{
					if(dtt.guardarTutort(t))
					{
						response.sendRedirect("ListaTutorTecnico.jsp?msj=1");
					}
					else
					{
						response.sendRedirect("ListaTutorTecnico.jsp?msj=2");
					}
				} 
				catch (Exception e) 
				{
					System.err.println("SLgestionTutor el error es: " +e.getLocalizedMessage());
					e.printStackTrace();
				}
			
				break;
				
			case 2:
				try 
				{
					t.setIdTutor(Integer.parseInt(request.getParameter("idtutor")));
					if(dtt.modificarTutor(t)) 
					{
						response.sendRedirect("ListaTutorTecnico.jsp?msj=3");
					}
					else
					{
						response.sendRedirect("EditarTutorTecnico.jsp?msj=4");
					}
				} 
				catch (Exception e) 
				{
					System.err.println("SLgestionTutor el error es: " +e.getLocalizedMessage());
					e.printStackTrace();
				}
				break;

			default:
				break;				
	   }
	}
}