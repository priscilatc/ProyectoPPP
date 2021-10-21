package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import entidades.Nivel180;
import vistas.VW_nivel;

public class DTNivel {
	
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsNivel = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	//Método para llenar el resultset nivel
	public void llenarRsNivel(Connection c)
	{
		String sql = "SELECT * FROM public.nivel180 where estado <>3";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsNivel = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT Nivel: Error en listar Nivel180 " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<VW_nivel> listarNiveles()
	{
		ArrayList<VW_nivel> listaNiveles = new ArrayList<VW_nivel>();
		
		String sql = "SELECT * FROM public.vista_nivel180";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				VW_nivel vwn = new VW_nivel();
				vwn.setIdNivel(rs.getInt("idNivel"));
				vwn.setDescripcion(rs.getString("descripcion"));
				
				listaNiveles.add(vwn);
				
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DT Nivel: Error en listar Nivel180 " + e.getMessage());
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
				System.err.println("DT Nivel: Error en listar Nivel180 " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return listaNiveles;
	}
	
	public boolean guardarNivel(Nivel180 n)
	{
		boolean guardado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsNivel(c);
			//AQUI INICIA EL GUARDAR
			rsNivel.moveToInsertRow();
			rsNivel.updateString("descripcion", n.getDescripcion());
			rsNivel.updateDate("fecha_creacion", sqlDate);
			rsNivel.updateInt("estado", 1);
			
			rsNivel.insertRow();
			rsNivel.moveToCurrentRow();
			guardado = true;
			//FIN DEL GUARDAR
			
		} 
		catch (Exception e) 
		{
			System.err.println("DT Nivel: Error al guardar nivel " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsNivel != null)
				{
					rsNivel.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Nivel: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return guardado;
	}
	
	public boolean modificarNivel(Nivel180 n)
	{
		boolean modificado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsNivel(c);
			rsNivel.beforeFirst();
			modificado = true;
			
				
			while(rsNivel.next())
			{
				if(rsNivel.getInt(1) == n.getIdNivel()) 
				{
					System.out.println("Id del Nivel: " + n.getIdNivel());
					
					rsNivel.updateString("descripcion", n.getDescripcion());
					rsNivel.updateDate("fecha_edicion", sqlDate);
					rsNivel.updateInt("estado", 2);
					
					rsNivel.updateRow();
					modificado = true;
					break;
				}
				
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("DT Nivel: Error al modificar nivel" + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsNivel != null)
				{
					rsNivel.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Nivel: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return modificado;
	}
	
	public boolean eliminarNivel(int idnivel)
	{
		boolean eliminado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsNivel(c);
			rsNivel.beforeFirst();
			while(rsNivel.next())
			{
				
				if(rsNivel.getInt(1) == idnivel)
					rsNivel.updateInt("estado", 3);	
					rsNivel.updateDate("fecha_eliminacion", sqlDate);
					rsNivel.updateRow();
					
					eliminado = true;
					break;
				}
				
			}
			 
		catch (SQLException e) 
		{
			System.err.println("DT Nivel: Error al eliminar nivel " + e.getMessage());
			e.printStackTrace();
			System.err.println(e.getSQLState());
		}
		finally 
		{
			try 
			{
				if(rsNivel != null)
				{
					rsNivel.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Nivel: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return eliminado;
	}
	
	public Nivel180 getNivel(int idnivel)
	{
		Nivel180 n = new Nivel180();
		String sql = "Select * from public.nivel180 where estado <> 3 and idnivel = ?";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idnivel);
			rs = ps.executeQuery();
			if(rs.next())
			{
				n.setIdNivel(idnivel);
				n.setDescripcion(rs.getString("descripcion"));
			}
		} 
		catch (SQLException e) 
		{
			System.err.println("DATOS: error getnivel(): " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsNivel != null)
				{
					rsNivel.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Nivel: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}				
		return n;
	}

}
