package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import entidades.AsignacionTutores;
import vistas.VW_asignacionTutores;

public class DTAsignacion {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsAs = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	//Método para llenar el resultset nivel
	public void llenarRsAs(Connection c)
	{
		String sql = "SELECT * FROM public.asignaciontutores where estado <>3";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsAs = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT Asignacion: Error en listar Asignacion " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<VW_asignacionTutores> listarAsignaciones()
	{
		ArrayList<VW_asignacionTutores> listaAsignaciones = new ArrayList<VW_asignacionTutores>();
		
		String sql = "SELECT * FROM public.vista_asignacionTutores";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				VW_asignacionTutores vwa = new VW_asignacionTutores();
				vwa.setIdasignacion(rs.getInt("idasignacion"));
				vwa.setComentario(rs.getString("comentario"));
				vwa.setEstudiante(rs.getString("estudiante"));
				vwa.setTutor_academico(rs.getString("tutor_academico"));
				vwa.setTutor_tecnico(rs.getString("tutor_tecnico"));			
				
				listaAsignaciones.add(vwa);
				
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DT Asignacion: Error en listar Asignacion" + e.getMessage());
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
				System.err.println("DT Asignacion: Error en listar Asignacion" + e2.getMessage());
				e2.printStackTrace();
			}
		}	
		return listaAsignaciones;
	}
	public boolean guardarAsignacion(AsignacionTutores a)
	{
		boolean guardado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsAs(c);
			//AQUI INICIA EL GUARDAR
			rsAs.moveToInsertRow();			
			rsAs.updateString("comentario", a.getComentario());
			rsAs.updateInt("idtutor", a.getIdTutor());
			rsAs.updateInt("id_docente", a.getId_docente());
			rsAs.updateInt("idestudiante", a.getIdEstudiante());			
			rsAs.updateDate("fecha_creacion", sqlDate);
			rsAs.updateInt("estado", 1);
			
			rsAs.insertRow();
			rsAs.moveToCurrentRow();
			guardado = true;
			//FIN DEL GUARDAR
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTAsignacion: Error al guardar asignación" + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsAs != null)
				{
					rsAs.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTAsignacion: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return guardado;
	}
	
	public boolean modificarAsignacion(AsignacionTutores a)
	{
		boolean modificado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsAs(c);
			rsAs.beforeFirst();
					
			while(rsAs.next())
			{
				if(rsAs.getInt(1) == a.getIdAsignacion()) 
				{
					System.out.println("Id de la asignacion: " + a.getIdAsignacion());
										
					rsAs.updateString("comentario", a.getComentario());
					rsAs.updateInt("idtutor", a.getIdTutor());
					rsAs.updateInt("id_docente", a.getId_docente());
					rsAs.updateInt("idestudiante", a.getIdEstudiante());
					rsAs.updateDate("fecha_edicion", sqlDate);
					rsAs.updateInt("estado", 2);
					
					rsAs.updateRow();
					modificado = true;
					break;
				}
				
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTAsignacion: Error al modificar Asignacion " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsAs != null)
				{
					rsAs.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTAsignacion: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return modificado;
	}
	
	public boolean eliminarAsignacion(int idasignacion)
	{
		boolean eliminado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsAs(c);
			rsAs.beforeFirst();
			while(rsAs.next())
			{
				
				if(rsAs.getInt(1) == idasignacion) 
				{
					rsAs.updateDate("fecha_eliminacion", sqlDate);
					rsAs.updateInt("estado", 3);	
					rsAs.updateRow();
					
					eliminado = true;
					break;
				}
				
			}
			
		} 
		catch (SQLException e) 
		{
			System.err.println("DTAsignacion: Error al eliminar asignacion " + e.getMessage());
			e.printStackTrace();
			System.err.println(e.getSQLState());
		}
		finally 
		{
			try 
			{
				if(rsAs != null)
				{
					rsAs.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTAsignacion: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return eliminado;
	}
	
	public AsignacionTutores getAsignacion(int idasignacion)
	{
		AsignacionTutores a = new AsignacionTutores();
		String sql = "Select * from public.asignaciontutores where estado <> 3 and idasignacion = ?";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idasignacion);
			rs = ps.executeQuery();
			if(rs.next())
			{
				a.setIdAsignacion(rs.getInt("idasignacion"));
				a.setComentario(rs.getString("comentario"));
				a.setIdEstudiante(rs.getInt("idestudiante"));
				a.setId_docente(rs.getInt("id_docente"));
				a.setIdTutor(rs.getInt("idtutor"));
			}
		} 
		catch (SQLException e) 
		{
			System.err.println("DATOS: error getAsignacion(): " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsAs != null)
				{
					rsAs.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTAsignacion: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return a;
	}
}
