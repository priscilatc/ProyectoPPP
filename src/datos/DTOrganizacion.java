package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import entidades.Organizacion;
import vistas.VW_organizacion;

public class DTOrganizacion {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsOrg = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	//Método para llenar el resultset organizacion
	public void llenarRsOrg(Connection c)
	{
		String sql = "SELECT * FROM public.organizacion where estado <>3";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsOrg = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT Organizacion: Error en listar Organizacion " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<VW_organizacion> listarOrg()
	{
		ArrayList<VW_organizacion> listaOrg = new ArrayList<VW_organizacion>();
		
		String sql = "SELECT * FROM public.vista_organizacion";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				VW_organizacion vwo = new VW_organizacion();
				vwo.setIdOrg(rs.getInt("idOrg"));
				vwo.setNombre(rs.getString("nombre"));
				vwo.setTelefono(rs.getString("telefono"));
				vwo.setExtension_telefonica(rs.getString("extension_telefonica"));
				vwo.setCorreo(rs.getString("correo"));
				vwo.setDepartamento(rs.getString("departamento"));
				vwo.setCiudad(rs.getString("ciudad"));
				vwo.setDireccion(rs.getString("direccion"));				
				listaOrg.add(vwo);
				
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DT Organizacion: Error en listar Organizaciones " + e.getMessage());
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
				System.err.println("DT Organizacion: Error en listar Organizaciones " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return listaOrg;
	}
	
	public boolean guardarOrg(Organizacion o)
	{
		boolean guardado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsOrg(c);
			//AQUI INICIA EL GUARDAR
			rsOrg.moveToInsertRow();
			rsOrg.updateString("nombre", o.getNombre());
			rsOrg.updateString("telefono", o.getTelefono());
			rsOrg.updateString("extension_telefonica", o.getExtension_telefonica());
			rsOrg.updateString("correo", o.getCorreo());
			rsOrg.updateString("departamento", o.getDepartamento());
			rsOrg.updateString("ciudad", o.getCiudad());
			rsOrg.updateString("direccion", o.getDireccion());
			rsOrg.updateDate("fecha_creacion", sqlDate);
			rsOrg.updateInt("estado", 1);
			
			rsOrg.insertRow();
			rsOrg.moveToCurrentRow();
			guardado = true;
			//FIN DEL GUARDAR
			
		} 
		catch (Exception e) 
		{
			System.err.println("DT Organizacion: Error en listar Organizaciones" + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsOrg != null)
				{
					rsOrg.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Organizacion: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return guardado;
	}
	
	public boolean modificarOrg(Organizacion o)
	{
		boolean modificado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsOrg(c);
			rsOrg.beforeFirst();
			modificado=true;
					
			while(rsOrg.next())
			{
				if(rsOrg.getInt(1) == o.getIdOrg()) 
				{
					System.out.println("Id de la Organización: " + o.getIdOrg());
					
					rsOrg.updateString("nombre", o.getNombre());
					rsOrg.updateString("correo", o.getCorreo());
					rsOrg.updateString("telefono", o.getTelefono());
					rsOrg.updateString("extension_telefonica", o.getExtension_telefonica());
					rsOrg.updateString("departamento", o.getDepartamento());
					rsOrg.updateString("ciudad", o.getCiudad());
					rsOrg.updateString("direccion", o.getDireccion());
					rsOrg.updateDate("fecha_edicion", sqlDate);
					rsOrg.updateInt("estado", 2);
					
					rsOrg.updateRow();
					modificado = true;
					break;
				}
				
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTOrganizacion: Error al modificar Organizacion " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsOrg != null)
				{
					rsOrg.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTOrganizacio: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return modificado;
	}
	
	public boolean eliminarOrg(int idorg)
	{
		boolean eliminado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsOrg(c);
			rsOrg.beforeFirst();
			while(rsOrg.next())
			{
				
				if(rsOrg.getInt(1) == idorg) 
				{
					rsOrg.updateDate("fecha_eliminacion", sqlDate);
					rsOrg.updateInt("estado", 3);	
					rsOrg.updateRow();
					
					eliminado = true;
					break;
				}
				
			}
			
		} 
		catch (SQLException e) 
		{
			System.err.println("DTOrganizacion: Error al eliminar organizacion " + e.getMessage());
			e.printStackTrace();
			System.err.println(e.getSQLState());
		}
		finally 
		{
			try 
			{
				if(rsOrg != null)
				{
					rsOrg.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTOrganizacion: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return eliminado;
	}
	
	public Organizacion getOrg(int idorg)
	{
		Organizacion o = new Organizacion();
		String sql = "Select * from public.organizacion where estado <> 3 and idorg = ?";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idorg);
			rs = ps.executeQuery();
			if(rs.next())
			{
				o.setIdOrg(rs.getInt("idOrg"));
				o.setNombre(rs.getString("nombre"));
				o.setTelefono(rs.getString("telefono"));
				o.setExtension_telefonica(rs.getString("extension_telefonica"));
				o.setCorreo(rs.getString("correo"));
				o.setDepartamento(rs.getString("departamento"));
				o.setCiudad(rs.getString("ciudad"));
				o.setDireccion(rs.getString("direccion"));
			}
		} 
		catch (SQLException e) 
		{
			System.err.println("DATOS: error getOrg(): " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsOrg != null)
				{
					rsOrg.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTOrganizacion: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return o;
	}
}
