package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Evaluacion;
import vistas.VW_autoevaluacion;
import vistas.VW_evaluacion;
import vistas.VW_evaluaciontec;

public class DTEvaluacion {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsE = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	//Método para llenar el resultset evaluacion
	public void llenarRsEvaluacion(Connection c)
	{
		String sql = "SELECT * FROM public.evaluacion";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsE = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT Evaluacion: Error en listar evaluaciones " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<VW_evaluacion> listarEvaluaciones()
	{
		ArrayList<VW_evaluacion> listaEvaluaciones = new ArrayList<VW_evaluacion>();
		
		String sql = "SELECT * FROM public.vista_evaluacion";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				VW_evaluacion vwe = new VW_evaluacion();
				vwe.setIdevaluacion(rs.getInt("idevaluacion"));
				vwe.setNivel(rs.getString("nivel"));
				vwe.setPeriodo(rs.getString("periodo"));
				vwe.setFechainicio(rs.getString("fechainicio"));
				vwe.setFechafin(rs.getString("fechafin"));
				vwe.setUrl(rs.getString("url"));
				vwe.setEstado(rs.getString("estado"));
				
				listaEvaluaciones.add(vwe);
				
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DT Evaluacion: Error en listar evaluaciones " + e.getMessage());
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
				System.err.println("DT Evaluacion: Error en listar evaluaciones" + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return listaEvaluaciones;
	}
	
	public boolean guardarEvaluacion(Evaluacion ev)
	{
		boolean guardado = false;
		
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsEvaluacion(c);
			//AQUI INICIA EL GUARDAR
			rsE.moveToInsertRow();
			rsE.updateInt("idnivel",ev.getIdNivel());
			rsE.updateInt("idperiodoppp", ev.getIdPeriodoPPP());
			rsE.updateString("url", ev.getUrl());			
			rsE.updateInt("estado", ev.getEstado());
			
			rsE.insertRow();
			rsE.moveToCurrentRow();
			guardado = true;
			//FIN DEL GUARDAR			
		} 
		catch (Exception e) 
		{
			System.err.println("DT Evaluacion: Error al guardar " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsE != null)
				{
					rsE.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Evaluacion: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return guardado;
	}
	
	public boolean modificarEvaluacion(Evaluacion ev)
	{
		boolean modificado = false;
		
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsEvaluacion(c);
			rsE.beforeFirst();
			modificado=true;
			
			while(rsE.next()) {
				if(rsE.getInt(1)==ev.getIdEvaluacion())
				{
					System.out.println("ID evaluacion:" + ev.getIdEvaluacion());

						rsE.updateInt("idnivel",ev.getIdNivel());
						rsE.updateInt("idperiodoppp", ev.getIdPeriodoPPP());
						rsE.updateString("url", ev.getUrl());			
						rsE.updateInt("estado", ev.getEstado());
						
						rsE.updateRow();
						modificado = true;
						break;
			 }	
			}	
		} 
		catch (Exception e) 
		{
			System.err.println("DT Evaluacion: Error al modificar " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsE != null)
				{
					rsE.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Evaluacion: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return modificado;
	}
	
	public Evaluacion getEvaluacion(int idevaluacion)
	{
		Evaluacion ev = new Evaluacion();
		String sql = "Select * from public.evaluacion where idevaluacion = ?";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idevaluacion);
			rs = ps.executeQuery();
			if(rs.next())
			{

				ev.setIdEvaluacion(rs.getInt("idevaluacion"));
				ev.setIdNivel(rs.getInt("idnivel"));
				ev.setIdPeriodoPPP(rs.getInt("idperiodoppp"));
				ev.setUrl(rs.getString("url"));
				ev.setEstado(rs.getInt("estado"));
			}
		} 
		catch (SQLException e) 
		{
			System.err.println("DATOS: error getEvaluacion(): " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsE != null)
				{
					rsE.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Evaluacion: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return ev;
	}
	
	public VW_evaluaciontec  getEvTec()
	{
		VW_evaluaciontec ev = new VW_evaluaciontec();
		String sql = "Select * from public.vista_evaluaciontec";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			if(rs.next())
			{
				ev.setIdevaluacion(rs.getInt("idevaluacion"));
				ev.setNivel(rs.getString("nivel"));	
				ev.setPeriodo(rs.getString("periodo"));
				ev.setFechainicio(rs.getString("fechainicio"));
				ev.setFechafin(rs.getString("fechafin"));
				ev.setUrl(rs.getString("url"));
			}
		} 
		catch (SQLException e) 
		{
			System.err.println("DATOS: error getEvaluacion(): " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsE != null)
				{
					rsE.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Evaluacion: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return ev;
	}
	
	public VW_autoevaluacion  getAutoevaluacion()
	{
		VW_autoevaluacion ev = new VW_autoevaluacion();
		String sql = "Select * from public.vista_autoevaluacion";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			if(rs.next())
			{
				ev.setIdevaluacion(rs.getInt("idevaluacion"));
				ev.setNivel(rs.getString("nivel"));	
				ev.setPeriodo(rs.getString("periodo"));
				ev.setFechainicio(rs.getString("fechainicio"));
				ev.setFechafin(rs.getString("fechafin"));
				ev.setUrl(rs.getString("url"));
			}
		} 
		catch (SQLException e) 
		{
			System.err.println("DATOS: error getEvaluacion(): " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsE != null)
				{
					rsE.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Evaluacion: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return ev;
	}
	
	public boolean verificarURL(String url) {        
        boolean res = false;       
        String SQL = "SELECT url FROM public.evaluacion WHERE url = '?'";
        try{
        	c = PoolConexion.getConnection();
			ps = c.prepareStatement(SQL);
			ps.setString(1, url); 
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
