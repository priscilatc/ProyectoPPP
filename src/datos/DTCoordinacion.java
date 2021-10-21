package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import entidades.Coordinacion;
import vistas.VW_coordinacion;

public class DTCoordinacion {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsC = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	//Método para llenar el resultset coordinacio
	public void llenarRsC(Connection c)
	{
		String sql = "SELECT * FROM public.coordinacion where estado <>3";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsC = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT Coordinacion: Error en listar Coordinacion" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<VW_coordinacion> listarCoordinaciones()
	{
		ArrayList<VW_coordinacion> listaCoordinaciones = new ArrayList<VW_coordinacion>();
		
		String sql = "SELECT * FROM public.vista_coordinacion";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				VW_coordinacion vwc = new VW_coordinacion();
				vwc.setIdcoordinacion(rs.getInt("idcoordinacion"));
				vwc.setNombre(rs.getString("nombre"));
				vwc.setCorreo(rs.getString("correo"));
				vwc.setTelefono(rs.getString("telefono"));
				vwc.setExtension_telefonica(rs.getString("extension_telefonica"));
				vwc.setDireccion(rs.getString("direccion"));
				vwc.setFacultad(rs.getString("facultad"));
				
				listaCoordinaciones.add(vwc);
				
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DT Coordinacion: Error en listar Coordinacion" + e.getMessage());
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
				System.err.println("DT Coordinacion: Error en listar Coordinacion" + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return listaCoordinaciones;
	}
	
	public boolean guardarCoordinacion(Coordinacion co)
	{
		boolean guardado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsC(c);
			//AQUI INICIA EL GUARDAR
			rsC.moveToInsertRow();
			rsC.updateString("nombre", co.getNombre());
			rsC.updateString("correo", co.getCorreo());
			rsC.updateString("telefono", co.getTelefono());
			rsC.updateString("extension_telefonica", co.getExtension_telefonica());
			rsC.updateString("direccion", co.getDireccion());
			rsC.updateInt("idFacultad", co.getIdFacultad());
			rsC.updateDate("fecha_creacion", sqlDate);
			rsC.updateInt("estado", 1);
			
			rsC.insertRow();
			rsC.moveToCurrentRow();
			guardado = true;
			//FIN DEL GUARDAR			
		} 
		catch (Exception e) 
		{
			System.err.println("DT Facultad: Error al guardar facultad " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsC != null)
				{
					rsC.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Coordinacion: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return guardado;
	}
	
	public boolean modificarCoordinacion(Coordinacion co)
	{
		boolean modificado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsC(c);
			rsC.beforeFirst();
			modificado=true;
					
			while(rsC.next())
			{
				if(rsC.getInt(1) == co.getIdCoordinacion()) 
				{
					System.out.println("Id del coordinacion: " + co.getIdCoordinacion());
					
					rsC.updateString("nombre", co.getNombre());
					rsC.updateString("correo", co.getCorreo());
					rsC.updateString("telefono", co.getTelefono());
					rsC.updateString("extension_telefonica", co.getExtension_telefonica());
					rsC.updateString("direccion", co.getDireccion());
					rsC.updateInt("idfacultad", co.getIdFacultad());
					rsC.updateDate("fecha_edicion", sqlDate);
					rsC.updateInt("estado", 2);
					
					rsC.updateRow();
					modificado = true;
					break;
				}
				
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTCoordinacion: Error al modificar coordinacion " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsC != null)
				{
					rsC.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTCoordinacion: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return modificado;
	}
	
	public boolean eliminarCoordinacion(int idcoordinacion)
	{
		boolean eliminado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsC(c);
			rsC.beforeFirst();
			while(rsC.next())
			{
				
				if(rsC.getInt(1) == idcoordinacion) 
				{
					rsC.updateDate("fecha_eliminacion", sqlDate);
					rsC.updateInt("estado", 3);	
					rsC.updateRow();
					
					eliminado = true;
					break;
				}
				
			}
			
		} 
		catch (SQLException e) 
		{
			System.err.println("DTCoordinacion: Error al eliminar coordinacion " + e.getMessage());
			e.printStackTrace();
			System.err.println(e.getSQLState());
		}
		finally 
		{
			try 
			{
				if(rsC != null)
				{
					rsC.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTCoordinacion: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return eliminado;
	}
	
	public Coordinacion getCoordinacion(int idcoordinacion)
	{
		Coordinacion co = new Coordinacion();
		String sql = "Select * from public.coordinacion where estado <> 3 and idcoordinacion = ?";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idcoordinacion);
			rs = ps.executeQuery();
			if(rs.next())
			{
				co.setIdCoordinacion(rs.getInt("idcoordinacion"));
				co.setNombre(rs.getString("nombre"));
				co.setCorreo(rs.getString("correo"));
				co.setTelefono(rs.getString("telefono"));
				co.setExtension_telefonica(rs.getString("extension_telefonica"));
				co.setDireccion(rs.getString("direccion"));
				co.setIdFacultad(rs.getInt("idfacultad"));
			}
		} 
		catch (SQLException e) 
		{
			System.err.println("DATOS: error getCoordinacion(): " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsC != null)
				{
					rsC.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTCoordinacion: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return co;
	}
}
