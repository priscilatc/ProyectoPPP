package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import entidades.Docente;
import vistas.VW_alumnosasignados_doc;
import vistas.VW_docente;
import vistas.VW_est_docente;

public class DTDocente {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsD = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	//Método para llenar el resultset docente
	public void llenarRsDocente(Connection c)
	{
		String sql = "SELECT * FROM public.docente where estado <>3";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsD = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT Docente: Error en listar Docente" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<VW_docente> listarDocentes()
	{
		ArrayList<VW_docente> listaDocentes = new ArrayList<VW_docente>();
		
		String sql = "SELECT * FROM public.vista_docente";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				VW_docente vwd = new VW_docente();
				vwd.setId_docente(rs.getInt("id_docente"));
				vwd.setNombre(rs.getString("nombre"));
				vwd.setDireccion(rs.getString("direccion"));
				vwd.setCiudad(rs.getString("ciudad"));
				vwd.setCelular(rs.getString("celular"));							
				vwd.setCarneuca(rs.getString("carneuca"));				
				vwd.setCedula(rs.getString("cedula"));
				vwd.setCargo(rs.getString("cargo"));
				vwd.setTrato(rs.getString("trato"));
				vwd.setCategoria(rs.getString("categoria"));
				vwd.setCorreo(rs.getString("correo"));	
				vwd.setCoordinacion(rs.getString("coordinacion"));
				vwd.setUsuario(rs.getString("usuario"));
				
				listaDocentes.add(vwd);
				
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DT Docente: Error en listar Docente" + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rs != null)
					rs.close();
				
				if(ps != null)
					ps.close();
				
				if(c != null)
					PoolConexion.cerrarConexion(c);
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Docente: Error en listar Docente" + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return listaDocentes;
	}
	

	public boolean guardarDocente(Docente d)
	{
		boolean guardado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsDocente(c);
			//AQUI INICIA EL GUARDAR
			rsD.moveToInsertRow();
			rsD.updateString("nombres", d.getNombres());
			rsD.updateString("apellidos", d.getApellidos());			
			rsD.updateString("celular", d.getCelular());
			rsD.updateString("direccion", d.getDireccion());
			rsD.updateString("ciudad", d.getCiudad());
			rsD.updateString("cargo", d.getCargo());
			rsD.updateString("trato", d.getTrato());
			rsD.updateString("correo", d.getCorreo());
			rsD.updateString("carneUca", d.getCarneUca());
			rsD.updateString("cedula", d.getCedula());	
			rsD.updateString("categoria", d.getCategoria());
			rsD.updateInt("idCoordinacion", d.getIdCoordinacion());
			rsD.updateInt("idUsuario", d.getIdUsuario());
			rsD.updateDate("fecha_creacion", sqlDate);
			rsD.updateInt("estado", 1);
			
			rsD.insertRow();
			rsD.moveToCurrentRow();
			guardado = true;
			//FIN DEL GUARDAR
			
		} 
		catch (Exception e) 
		{
			System.err.println("DT Docente: Error al guardar docente" + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsD != null)
				{
					rsD.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Docente: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}	
		return guardado;
	}
	
	public boolean modificarDocente(Docente d)
	{
		boolean modificado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsDocente(c);
			rsD.beforeFirst();
			modificado=true;
					
			while(rsD.next())
			{
				if(rsD.getInt(1) == d.getIdDocente()) 
				{
					System.out.println("Id del docente: " + d.getIdDocente());
					
					rsD.updateString("nombres", d.getNombres());
					rsD.updateString("apellidos", d.getApellidos());
					rsD.updateString("ciudad", d.getCiudad());
					rsD.updateString("celular", d.getCelular());
					rsD.updateString("direccion", d.getDireccion());
					rsD.updateString("cargo", d.getCargo());			
					rsD.updateString("trato", d.getTrato());
					rsD.updateString("correo", d.getCorreo());
					rsD.updateString("carneUca", d.getCarneUca());
					rsD.updateString("cedula", d.getCedula());
					rsD.updateString("categoria", d.getCategoria());
					rsD.updateInt("idcoordinacion", d.getIdCoordinacion());
					rsD.updateInt("idusuario", d.getIdUsuario());
					rsD.updateDate("fecha_edicion", sqlDate);
					rsD.updateInt("estado", 2);
					
					rsD.updateRow();
					modificado = true;
					break;
				}
				
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("DT Docente: Error al modificar docente " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsD != null)
				{
					rsD.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Docente: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return modificado;
	}
	
	public boolean eliminarDocente(int iddocente)
	{
		boolean eliminado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsDocente(c);
			rsD.beforeFirst();
			while(rsD.next())
			{
				
				if(rsD.getInt(1) == iddocente) 
				{
					rsD.updateDate("fecha_eliminacion", sqlDate);
					rsD.updateInt("estado", 3);	
					rsD.updateRow();
					
					eliminado = true;
					break;
				}
				
			}
			
		} 
		catch (SQLException e) 
		{
			System.err.println("DT Docente: Error al eliminar docente " + e.getMessage());
			e.printStackTrace();
			System.err.println(e.getSQLState());
		}
		finally 
		{
			try 
			{
				if(rsD != null)
				{
					rsD.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Tutor: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return eliminado;
	}
	
	public Docente getDocente(int iddocente)
	{
		Docente d = new Docente();
		String sql = "Select * from public.docente where estado <> 3 and id_docente = ?";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, iddocente);
			rs = ps.executeQuery();
			if(rs.next())
			{
				d.setIdDocente(rs.getInt("id_docente"));
				d.setNombres(rs.getString("nombres"));
				d.setApellidos(rs.getString("apellidos"));
				d.setDireccion(rs.getString("direccion"));
				d.setCelular(rs.getString("celular"));
				d.setCiudad(rs.getString("ciudad"));
				d.setCargo(rs.getString("cargo"));
				d.setTrato(rs.getString("trato"));
				d.setCarneUca(rs.getString("carneUca"));
				d.setCedula(rs.getString("cedula"));
				d.setCorreo(rs.getString("correo"));
				d.setCategoria(rs.getString("categoria"));
				d.setIdCoordinacion(rs.getInt("idCoordinacion"));
				d.setIdUsuario(rs.getInt("idUsuario"));
			}
		} 
		catch (SQLException e) 
		{
			System.err.println("DATOS: error getTutor(): " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsD != null)
				{
					rsD.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTTutor: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return d;
	}
	
	public VW_est_docente getTutorAcademico(int idusuario)
	{
		VW_est_docente vwd = new VW_est_docente();
		String sql = "Select * from public.vista_est_docente where idusuario = ?";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idusuario);
			rs = ps.executeQuery();
			if(rs.next())
			{
				vwd.setNombre(rs.getString("nombre"));
				vwd.setCargo(rs.getString("cargo"));
				vwd.setTrato(rs.getString("trato"));
				vwd.setCategoria(rs.getString("categoria"));
				vwd.setCorreo(rs.getString("correo"));	
				vwd.setCoordinacion(rs.getString("coordinacion"));
			}
			}
		catch (SQLException e) 
		{
			System.err.println("DATOS: error Docente(): " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsD != null)
				{
					rsD.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Docente: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return vwd;
	}
	
	public ArrayList<VW_alumnosasignados_doc> getAlumnosAsignados(int idusuario)
	{
		ArrayList<VW_alumnosasignados_doc> alumnosasignados = new ArrayList<VW_alumnosasignados_doc>();
		String sql = "Select * from public.vista_alumnosasignados_doc where idusuario=?";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idusuario);
			rs = ps.executeQuery();
			while(rs.next())
			{
				VW_alumnosasignados_doc vwa = new VW_alumnosasignados_doc();
				vwa.setIdestudiante(rs.getInt("idestudiante"));
				vwa.setNombre(rs.getString("nombre"));
				
				alumnosasignados.add(vwa);
			}
			}
		catch (SQLException e) 
		{
			System.err.println("DATOS: error DTDocente(): " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsD != null)
				{
					rsD.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Docente: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return alumnosasignados;
	}
	
	public VW_docente getPerfil(int idusuario)
	{
		VW_docente vwp = new VW_docente();
		String sql = "Select * from public.vista_docente where idusuario = ?";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idusuario);
			rs = ps.executeQuery();
			if(rs.next())
			{
				vwp.setNombre(rs.getString("nombre"));
				vwp.setCargo(rs.getString("cargo"));
				vwp.setTrato(rs.getString("trato"));
				vwp.setCategoria(rs.getString("categoria"));
				vwp.setCorreo(rs.getString("correo"));	
				vwp.setCoordinacion(rs.getString("coordinacion"));
				vwp.setIdusuario(rs.getInt("idusuario"));
			}
			}
		catch (SQLException e) 
		{
			System.err.println("DATOS: error DTDocente(): " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsD != null)
				{
					rsD.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Docente: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return vwp;
	}
	
	public boolean verificarUsuarioExiste(int idusuario) {        
        boolean res = false;       
        String SQL = "SELECT idusuario FROM public.docente WHERE idusuario = ?";
        try{
        	c = PoolConexion.getConnection();
			ps = c.prepareStatement(SQL);
			ps.setInt(1, idusuario); 
			rs = ps.executeQuery();
            if(rs.next())
                res = true;
        } catch(Exception e){
            System.err.print("Ha ocurrido un error: "+ e.getMessage());
        } 
        finally{
        	try {
				if(rs != null){
					rs.close();
				}
				if(ps != null){
					ps.close();
				}
				if(c != null){
					PoolConexion.cerrarConexion(c);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
        }        
        return res;
    }
}
