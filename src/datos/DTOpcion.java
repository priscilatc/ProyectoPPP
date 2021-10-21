package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import entidades.Opcion;
import vistas.VW_opcion;

public class DTOpcion {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsOpc = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	//Método para llenar el resultset opcion
	public void llenarRsOpcion(Connection c)
	{
		String sql = "SELECT * FROM public.opciones where estado <>3";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsOpc = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT Opcion: Error en listar Opciones" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<VW_opcion> listarOpciones()
	{
		ArrayList<VW_opcion> listaOpciones = new ArrayList<VW_opcion>();
		
		String sql = "SELECT * FROM public.vista_opcion";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				VW_opcion vwo = new VW_opcion();
				vwo.setIdopcion(rs.getInt("idopcion"));
				vwo.setDescripcion(rs.getString("descripcion"));
				vwo.setUrl(rs.getString("url"));
				
				listaOpciones.add(vwo);
				
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DT Opcion: Error en listar Opciones" + e.getMessage());
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
				System.err.println("DT Opcion: Error en listar Opciones" + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return listaOpciones;
	}
	public boolean guardarOpc(Opcion o)
	{
		boolean guardado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsOpcion(c);
			//AQUI INICIA EL GUARDAR
			rsOpc.moveToInsertRow();			
			rsOpc.updateString("descripcion", o.getDescripcion());
			rsOpc.updateString("url", o.getUrl());
			rsOpc.updateDate("fecha_creacion", sqlDate);
			rsOpc.updateInt("estado", 1);
			
			rsOpc.insertRow();
			rsOpc.moveToCurrentRow();
			guardado = true;
			//FIN DEL GUARDAR
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTOpcion: Error al guardar opcion" + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsOpc != null)
				{
					rsOpc.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTOpcion: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return guardado;
	}
	
	public boolean modificarOpcion(Opcion o)
	{
		boolean modificado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsOpcion(c);
			rsOpc.beforeFirst();
					
			while(rsOpc.next())
			{
				if(rsOpc.getInt(1) == o.getIdOpcion()) 
				{
					System.out.println("Id de la opcion: " + o.getIdOpcion());
										
					rsOpc.updateString("descripcion", o.getDescripcion());
					rsOpc.updateString("url", o.getUrl());
					rsOpc.updateDate("fecha_edicion", sqlDate);
					rsOpc.updateInt("estado", 2);
					
					rsOpc.updateRow();
					modificado = true;
					break;
				}
				
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTOpcion: Error al modificar opcion " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsOpc != null)
				{
					rsOpc.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTOpcion: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return modificado;
	}
	
	public boolean eliminarOpc(int idopcion)
	{
		boolean eliminado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsOpcion(c);
			rsOpc.beforeFirst();
			while(rsOpc.next())
			{
				
				if(rsOpc.getInt(1) == idopcion) 
				{
					rsOpc.updateDate("fecha_eliminacion", sqlDate);
					rsOpc.updateInt("estado", 3);	
					rsOpc.updateRow();
					
					eliminado = true;
					break;
				}
				
			}
			
		} 
		catch (SQLException e) 
		{
			System.err.println("DTOpcion: Error al eliminar opcion " + e.getMessage());
			e.printStackTrace();
			System.err.println(e.getSQLState());
		}
		finally 
		{
			try 
			{
				if(rsOpc != null)
				{
					rsOpc.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTOpcion: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return eliminado;
	}
	
	public Opcion getOpc(int idopcion)
	{
		Opcion o = new Opcion();
		String sql = "Select * from public.opciones where estado <> 3 and idopcion = ?";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idopcion);
			rs = ps.executeQuery();
			if(rs.next())
			{
				o.setIdOpcion(rs.getInt("idopcion"));			
				o.setDescripcion(rs.getString("descripcion"));
				o.setUrl(rs.getString("url"));
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
				if(rsOpc != null)
				{
					rsOpc.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTOpcion: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return o;
	}
}
