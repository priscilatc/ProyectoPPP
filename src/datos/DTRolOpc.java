package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import entidades.RolOpciones;
import vistas.VW_rol_opciones;

public class DTRolOpc {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsRopc = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	//Método para llenar el resultset nivel
	public void llenarRsRopc(Connection c)
	{
		String sql = "SELECT * FROM public.rol_opciones where estado <>3";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsRopc= ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT Rol Opciones: Error en listar Rol Opciones" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<VW_rol_opciones> listarRolopc()
	{
		ArrayList<VW_rol_opciones> listaRolopc = new ArrayList<VW_rol_opciones>();
		
		String sql = "SELECT * FROM public.vista_rol_opciones";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				VW_rol_opciones vwr = new VW_rol_opciones();
				vwr.setId_rol_opciones(rs.getInt("id_rol_opciones"));
				vwr.setOpcion(rs.getString("opcion"));
				vwr.setRol(rs.getString("rol"));
				vwr.setDescripcion(rs.getString("descripcion"));
				
				listaRolopc.add(vwr);
				
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DT Rol Opciones: Error en listar Rol Opciones" + e.getMessage());
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
				System.err.println("DT Rol Opciones: Error en listar Rol Opciones" + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return listaRolopc;
	}
	public boolean guardarRolOpc(RolOpciones ro)
	{
		boolean guardado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
				
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsRopc(c);
			//AQUI INICIA EL GUARDAR
			rsRopc.moveToInsertRow();
			
			rsRopc.updateInt("idrol", ro.getIdRol());
			rsRopc.updateInt("idopcion", ro.getIdOpcion());
			rsRopc.updateDate("fecha_creacion", sqlDate);
			rsRopc.updateInt("estado", 1);
			rsRopc.insertRow();
			rsRopc.moveToCurrentRow();
			guardado = true;
			//FIN DEL GUARDAR
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTROLOPCIONES: Error al guardar rol opciones" + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsRopc != null)
				{
					rsRopc.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTROLOPCIONES: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return guardado;
	}
	
	public boolean modificarRolOpc(RolOpciones r)
	{
		boolean modificado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsRopc(c);
			rsRopc.beforeFirst();
					
			while(rsRopc.next())
			{
				if(rsRopc.getInt(1) == r.getId_rol_opciones()) 
				{
					System.out.println("Id del rol: " + r.getId_rol_opciones());
					
					rsRopc.updateInt("idrol", r.getIdRol());
					rsRopc.updateInt("idopcion", r.getIdOpcion());
					rsRopc.updateDate("fecha_edicion", sqlDate);
					rsRopc.updateInt("estado", 2);
					
					rsRopc.updateRow();
					modificado = true;
					break;
				}
				
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTROL: Error al modificar rol " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsRopc != null)
				{
					rsRopc.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTROL: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return modificado;
	}
	
	public boolean eliminarRol(int idrolopc)
	{
		boolean eliminado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsRopc(c);
			rsRopc.beforeFirst();
			while(rsRopc.next())
			{
				
				if(rsRopc.getInt(1) == idrolopc) 
				{
					rsRopc.updateDate("fecha_eliminacion", sqlDate);
					rsRopc.updateInt("estado", 3);	
					rsRopc.updateRow();
					
					eliminado = true;
					break;
				}
				
			}
			
		} 
		catch (SQLException e) 
		{
			System.err.println("DTROL Opciones: Error al eliminar rol opc " + e.getMessage());
			e.printStackTrace();
			System.err.println(e.getSQLState());
		}
		finally 
		{
			try 
			{
				if(rsRopc != null)
				{
					rsRopc.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTROL: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return eliminado;
	}
	
	public RolOpciones getRolOpciones (int idrolopcion) 
	{
		RolOpciones r = new RolOpciones();
		String sql = "Select * from public.rol_opciones where estado <> 3 and id_rol_opciones = ?";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idrolopcion);
			rs = ps.executeQuery();
			if(rs.next())
			{
				r.setId_rol_opciones(rs.getInt("id_rol_opciones"));
				r.setIdRol(rs.getInt("idrol"));
				r.setIdOpcion(rs.getInt("idopcion"));
			}
		}
		catch (SQLException e) 
		{
			System.err.println("DATOS: error getOpc(): " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsRopc != null)
				{
					rsRopc.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTRolpc: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return r;
	}
}
