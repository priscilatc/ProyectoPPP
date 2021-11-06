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
 * Servlet implementation class SLgestionDocente
 */
@WebServlet("/SLgestionDocente")
public class SLgestionDocente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLgestionDocente() {
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
		
		DTDocente dtd =  new DTDocente();
		Docente d = new Docente();
		
		int idusuario =0;
		
		d.setNombres(request.getParameter("nombres"));
		d.setApellidos(request.getParameter("apellidos"));
		d.setCiudad(request.getParameter("ciudad"));
		d.setDireccion(request.getParameter("direccion"));
		d.setCelular(request.getParameter("celular"));
		d.setCargo(request.getParameter("cargo"));
		d.setTrato(request.getParameter("trato"));
		d.setCorreo(request.getParameter("correo"));
		d.setCarneUca(request.getParameter("carneUca"));
		d.setCedula(request.getParameter("cedula"));
		d.setCategoria(request.getParameter("categoria"));
		d.setIdCoordinacion(Integer.parseInt(request.getParameter("idcoordinacion")));
		idusuario = Integer.parseInt(request.getParameter("idusuario"));
		d.setIdUsuario(idusuario);
		switch (opc) 
		{
			case 1: 
				try 
				{
					if(dtd.verificarUsuarioExiste(idusuario)) {
						response.sendRedirect("AgregarDocente.jsp?msj=1");
					}
					else {
					if(dtd.guardarDocente(d))
					{
						response.sendRedirect("ListaDocente.jsp?msj=1");
					}
					else
					{
						response.sendRedirect("ListaDocente.jsp?msj=2");
					}
				} 
			}
				catch (Exception e) 
				{
					System.err.println("SLgestionDocente el error es: " +e.getLocalizedMessage());
					e.printStackTrace();
				}
			
				break;
				
			case 2:
				try 
				{
					d.setIdDocente(Integer.parseInt(request.getParameter("id_docente")));
					if(dtd.modificarDocente(d)) 
					{
						response.sendRedirect("ListaDocente.jsp?msj=3");
					}
					else
					{
						response.sendRedirect("ListaDocente.jsp?msj=4");
					}
				} 
				catch (Exception e) 
				{
					System.err.println("SLgestionDocente el error es: " +e.getLocalizedMessage());
					e.printStackTrace();
				}
				break;

			default:
				break;				
	   }
	}
}