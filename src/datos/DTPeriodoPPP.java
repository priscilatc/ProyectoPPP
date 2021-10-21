package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import entidades.PeriodoPPP;
import vistas.VW_periodoppp;

public class DTPeriodoPPP {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsP = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	//Método para llenar el resultset PeriodoPPP
	public void llenarRsPe(Connection c)
	{
		String sql = "SELECT * FROM public.periodoppp where estado <>3";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsP = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT Periodo: Error en listar Periodo" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<VW_periodoppp> listarPeriodos()
	{
		ArrayList<VW_periodoppp> listaPeriodos = new ArrayList<VW_periodoppp>();
		
		String sql = "SELECT * FROM public.vista_periodoppp";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				VW_periodoppp vwp = new VW_periodoppp();
				vwp.setIdperiodoppp(rs.getInt("idperiodoppp"));
				vwp.setDescripcion(rs.getString("descripcion"));
				vwp.setFecha_inicio(rs.getString("fecha_inicio"));
				vwp.setFecha_fin(rs.getString("fecha_fin"));
				
				listaPeriodos.add(vwp);
				
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DT Periodo: Error en listar Periodo" + e.getMessage());
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
				System.err.println("DT Periodo: Error en listar Periodo" + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return listaPeriodos;
	}
	
	public boolean guardarPeriodo(PeriodoPPP p)
	{
		boolean guardado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsPe(c);
			//AQUI INICIA EL GUARDAR
			rsP.moveToInsertRow();			
			rsP.updateString("descripcion", p.getDescripcion());
			rsP.updateString("fecha_inicio", p.getFecha_inicio());
			rsP.updateString("fecha_fin", p.getFecha_fin());
			rsP.updateDate("fecha_creacion", sqlDate);
			rsP.updateInt("estado", 1);
			
			rsP.insertRow();
			rsP.moveToCurrentRow();
			guardado = true;
			//FIN DEL GUARDAR
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTPeriodo: Error al guardar periodo" + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsP != null)
				{
					rsP.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTPeriodo: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return guardado;
	}
	
	public boolean modificarPeriodo(PeriodoPPP p)
	{
		boolean modificado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsPe(c);
			rsP.beforeFirst();
					
			while(rsP.next())
			{
				if(rsP.getInt(1) == p.getIdPeriodoPPP()) 
				{
					System.out.println("Id del Periodo: " + p.getIdPeriodoPPP());
										
					rsP.updateString("descripcion", p.getDescripcion());
					rsP.updateString("fecha_inicio", p.getFecha_inicio());
					rsP.updateString("fecha_fin", p.getFecha_fin());
					rsP.updateDate("fecha_edicion", sqlDate);
					rsP.updateInt("estado", 2);
					
					rsP.updateRow();
					modificado = true;
					break;
				}
				
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTPeriodo: Error al modificar periodo " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsP != null)
				{
					rsP.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTPeriodo: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return modificado;
	}
	
	public boolean eliminarPeriodo(int idperiodoppp)
	{
		boolean eliminado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsPe(c);
			rsP.beforeFirst();
			while(rsP.next())
			{
				
				if(rsP.getInt(1) == idperiodoppp) 
				{
					rsP.updateDate("fecha_eliminacion", sqlDate);
					rsP.updateInt("estado", 3);	
					rsP.updateRow();
					
					eliminado = true;
					break;
				}
				
			}
			
		} 
		catch (SQLException e) 
		{
			System.err.println("DTPeriodo: Error al eliminar periodo" + e.getMessage());
			e.printStackTrace();
			System.err.println(e.getSQLState());
		}
		finally 
		{
			try 
			{
				if(rsP != null)
				{
					rsP.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTPeriodo: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return eliminado;
	}
	
	public PeriodoPPP getPeriodo(int idperiodoppp)
	{
		PeriodoPPP p = new PeriodoPPP();
		String sql = "Select * from public.periodoppp where estado <> 3 and idperiodoppp = ?";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idperiodoppp);
			rs = ps.executeQuery();
			if(rs.next())
			{
				p.setIdPeriodoPPP(rs.getInt("idperiodoppp"));			
				p.setDescripcion(rs.getString("descripcion"));
				p.setFecha_inicio(rs.getString("fecha_inicio"));
				p.setFecha_fin(rs.getString("fecha_fin"));
			}
		} 
		catch (SQLException e) 
		{
			System.err.println("DATOS: error getPeriodo(): " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsP != null)
				{
					rsP.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTPeriodo: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return p;
	}
}
