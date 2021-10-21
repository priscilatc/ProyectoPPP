package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import entidades.Facultad;
import vistas.VW_facultad;

public class DTFacultad {

	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsF = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	//Método para llenar el resultset facultad
	public void llenarRsFacultad(Connection c)
	{
		String sql = "SELECT * FROM public.facultad where estado <>3";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsF = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT Facultad: Error en listar Facultad" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<VW_facultad> listarFacultades()
	{
		ArrayList<VW_facultad> listaFacultades = new ArrayList<VW_facultad>();
		
		String sql = "SELECT * FROM public.vista_facultad";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				VW_facultad vwf = new VW_facultad();
				vwf.setIdfacultad(rs.getInt("idfacultad"));
				vwf.setNombre(rs.getString("nombre"));
				vwf.setCorreo(rs.getString("correo"));
				vwf.setTelefono(rs.getString("telefono"));
				vwf.setExtension_telefonica(rs.getString("extension_telefonica"));
				vwf.setDireccion(rs.getString("direccion"));				
				listaFacultades.add(vwf);
				
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DT Facultad: Error en listar Facultades " + e.getMessage());
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
				System.err.println("DT Facultad: Error en cerrar conexión" + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return listaFacultades;
	}
	
	public boolean guardarFacultad(Facultad f)
	{
		boolean guardado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsFacultad(c);
			//AQUI INICIA EL GUARDAR
			rsF.moveToInsertRow();
			rsF.updateString("nombre", f.getNombre());
			rsF.updateString("correo", f.getCorreo());
			rsF.updateString("telefono", f.getTelefono());
			rsF.updateString("extension_telefonica", f.getExtension_telefonica());
			rsF.updateString("direccion", f.getDireccion());
			rsF.updateDate("fecha_creacion", sqlDate);
			rsF.updateInt("estado", 1);
			
			rsF.insertRow();
			rsF.moveToCurrentRow();
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
				if(rsF != null)
				{
					rsF.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Facultad: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return guardado;
	}
	
	public boolean modificarFacultad(Facultad f)
	{
		boolean modificado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsFacultad(c);
			rsF.beforeFirst();
			modificado=true;
					
			while(rsF.next())
			{
				if(rsF.getInt(1) == f.getIdFacultad()) 
				{
					System.out.println("Id del facultad: " + f.getIdFacultad());
					
					rsF.updateString("nombre", f.getNombre());
					rsF.updateString("correo", f.getCorreo());
					rsF.updateString("telefono", f.getTelefono());
					rsF.updateString("extension_telefonica", f.getExtension_telefonica());
					rsF.updateString("direccion", f.getDireccion());
					rsF.updateDate("fecha_edicion", sqlDate);
					rsF.updateInt("estado", 2);
					
					rsF.updateRow();
					modificado = true;
					break;
				}
				
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTFacultad: Error al modificar facultad " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsF != null)
				{
					rsF.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTFacultad: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return modificado;
	}
	
	public boolean eliminarFacultad(int idfacultad)
	{
		boolean eliminado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsFacultad(c);
			rsF.beforeFirst();
			while(rsF.next())
			{
				
				if(rsF.getInt(1) == idfacultad) 
				{
					rsF.updateDate("fecha_eliminacion", sqlDate);
					rsF.updateInt("estado", 3);	
					rsF.updateRow();
					
					eliminado = true;
					break;
				}
				
			}
			
		} 
		catch (SQLException e) 
		{
			System.err.println("DTFacultad: Error al eliminar facultad " + e.getMessage());
			e.printStackTrace();
			System.err.println(e.getSQLState());
		}
		finally 
		{
			try 
			{
				if(rsF != null)
				{
					rsF.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTFacultad: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return eliminado;
	}
	
	public Facultad getFacultad(int idfacultad)
	{
		Facultad f = new Facultad();
		String sql = "Select * from public.facultad where estado <> 3 and idfacultad = ?";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idfacultad);
			rs = ps.executeQuery();
			if(rs.next())
			{
				f.setIdFacultad(rs.getInt("idfacultad"));
				f.setNombre(rs.getString("nombre"));
				f.setCorreo(rs.getString("correo"));
				f.setTelefono(rs.getString("telefono"));
				f.setExtension_telefonica(rs.getString("extension_telefonica"));
				f.setDireccion(rs.getString("direccion"));
			}
		} 
		catch (SQLException e) 
		{
			System.err.println("DATOS: error getFacultad(): " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsF != null)
				{
					rsF.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTFacultad: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return f;
	}
}
