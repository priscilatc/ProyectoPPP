package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import entidades.PPP;
import vistas.VW_ppp;

public class DTPPP {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsP = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	//Método para llenar el resultset ppp
	public void llenarRsPpp(Connection c)
	{
		String sql = "SELECT * FROM public.ppp where estado <>3";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsP = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DTPPP: Error en listar PPP" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<VW_ppp> listarPPP()
	{
		ArrayList<VW_ppp> listaPPP = new ArrayList<VW_ppp>();
		
		String sql = "SELECT * FROM public.vista_ppp";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				VW_ppp vwp = new VW_ppp();
				vwp.setIdppp(rs.getInt("idppp"));
				vwp.setArea_laboral(rs.getString("area_laboral"));
				vwp.setCampo_profesional(rs.getString("campo_profesional"));
				vwp.setCargo(rs.getString("cargo"));
				vwp.setDias_laborales(rs.getString("dias_laborales"));
				vwp.setFecha_inicio(rs.getString("fecha_inicio"));
				vwp.setFecha_fin(rs.getString("fecha_fin"));
				vwp.setFunciones(rs.getString("funciones"));
				vwp.setHorario(rs.getString("horario"));
				vwp.setComentario(rs.getString("comentario"));
				vwp.setEstudiante(rs.getString("estudiante"));
				vwp.setOrganizacion(rs.getString("organizacion"));
				
				listaPPP.add(vwp);
				
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DTPPP: Error en listar PPP" + e.getMessage());
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
				System.err.println("DTPPP: Error en listar PPP" + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return listaPPP;
	}
	public boolean guardarPPP(PPP p)
	{
		boolean guardado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsPpp(c);
			//AQUI INICIA EL GUARDAR
			rsP.moveToInsertRow();			
			rsP.updateString("area_laboral", p.getArea_laboral());
			rsP.updateString("campoprofesional", p.getCampoprofesional());
			rsP.updateString("cargo", p.getCargo());
			rsP.updateString("dias_laborales", p.getDias_laborales());
			rsP.updateString("fecha_inicio", p.getFecha_inicio());
			rsP.updateString("fecha_fin", p.getFecha_fin());
			rsP.updateString("funciones", p.getFunciones());
			rsP.updateString("horario", p.getHorario());
			rsP.updateString("comentario", p.getComentario());
			rsP.updateInt("idestudiante", p.getIdEstudiante());
			rsP.updateInt("idorg", p.getIdOrg());
			rsP.updateDate("fecha_creacion", sqlDate);
			rsP.updateInt("estado", 1);
			
			rsP.insertRow();
			rsP.moveToCurrentRow();
			guardado = true;
			//FIN DEL GUARDAR
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTPPP: Error al guardar PPP" + e.getMessage());
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
				System.err.println("DTPPP: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return guardado;
	}
	
	public boolean modificarPPP(PPP p)
	{
		boolean modificado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsPpp(c);
			rsP.beforeFirst();
					
			while(rsP.next())
			{
				if(rsP.getInt(1) == p.getIdPPP()) 
				{
					System.out.println("Id de la opcion: " + p.getIdPPP());
										
					rsP.updateString("area_laboral", p.getArea_laboral());
					rsP.updateString("campoprofesional", p.getCampoprofesional());
					rsP.updateString("cargo", p.getCargo());
					rsP.updateString("dias_laborales", p.getDias_laborales());
					rsP.updateString("fecha_inicio", p.getFecha_inicio());
					rsP.updateString("fecha_fin", p.getFecha_fin());
					rsP.updateString("funciones", p.getFunciones());
					rsP.updateString("horario", p.getHorario());
					rsP.updateString("comentario", p.getComentario());
					rsP.updateInt("idestudiante", p.getIdEstudiante());
					rsP.updateInt("idorg", p.getIdOrg());
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
			System.err.println("DTPPP: Error al modificar PPP" + e.getMessage());
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
				System.err.println("DTPPP: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return modificado;
	}
	
	public boolean eliminarPPP(int idppp)
	{
		boolean eliminado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsPpp(c);
			rsP.beforeFirst();
			while(rsP.next())
			{
				
				if(rsP.getInt(1) == idppp) 
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
			System.err.println("DTPPP: Error al eliminar PPP " + e.getMessage());
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
				System.err.println("DTPP: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return eliminado;
	}
	
	public PPP getPPP(int idppp)
	{
		PPP p = new PPP();
		String sql = "Select * from public.ppp where estado <> 3 and idppp = ?";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idppp);
			rs = ps.executeQuery();
			if(rs.next())
			{
				p.setIdPPP(rs.getInt("idppp"));
				p.setArea_laboral(rs.getString("area_laboral"));
				p.setCampoprofesional(rs.getString("campoprofesional"));
				p.setCargo(rs.getString("cargo"));
				p.setDias_laborales(rs.getString("dias_laborales"));
				p.setFecha_inicio(rs.getString("fecha_inicio"));
				p.setFecha_fin(rs.getString("fecha_fin"));
				p.setFunciones(rs.getString("funciones"));
				p.setHorario(rs.getString("horario"));
				p.setComentario(rs.getString("comentario"));
				p.setIdEstudiante(rs.getInt("idestudiante"));
				p.setIdOrg(rs.getInt("idorg"));
			}
		} 
		catch (SQLException e) 
		{
			System.err.println("DATOS: error getPPP(): " + e.getMessage());
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
				System.err.println("DTPPP: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return p;
	}
	
	public VW_ppp getPPPEst(int idusuario)
	{
		VW_ppp p = new VW_ppp();
		String sql = "Select * from public.vista_ppp where idusuario = ?";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idusuario);
			rs = ps.executeQuery();
			if(rs.next())
			{
				p.setArea_laboral(rs.getString("area_laboral"));
				p.setCampo_profesional(rs.getString("campo_profesional"));
				p.setCargo(rs.getString("cargo"));
				p.setDias_laborales(rs.getString("dias_laborales"));
				p.setFecha_inicio(rs.getString("fecha_inicio"));
				p.setFecha_fin(rs.getString("fecha_fin"));
				p.setFunciones(rs.getString("funciones"));
				p.setHorario(rs.getString("horario"));
				p.setComentario(rs.getString("comentario"));
				p.setEstudiante(rs.getString("estudiante"));
				p.setOrganizacion(rs.getString("organizacion"));
			}
		} 
		catch (SQLException e) 
		{
			System.err.println("DATOS: error getPPP(): " + e.getMessage());
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
				System.err.println("DTPPP: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return p;
	}
	
}
