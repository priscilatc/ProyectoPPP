package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import entidades.Estudiante;
import vistas.VW_estudiante;

public class DTEstudiante {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsEst = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	//Método para llenar el resultset estudiante
	public void llenarRsEst(Connection c)
	{
		String sql = "SELECT * FROM public.estudiante where estado <>3";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsEst = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT Estudiante: Error en listar Estudiante" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<VW_estudiante> listarEstudiantes()
	{
		ArrayList<VW_estudiante> listaEstudiantes = new ArrayList<VW_estudiante>();
		
		String sql = "SELECT * FROM public.vista_estudiante";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				VW_estudiante vwe = new VW_estudiante();
				vwe.setIdestudiante(rs.getInt("idestudiante"));
				vwe.setNombre(rs.getString("nombre"));
				vwe.setCorreo(rs.getString("correo"));
				vwe.setCelular(rs.getString("celular"));
				vwe.setCondicion(rs.getString("condicion"));				
				vwe.setSexo(rs.getString("sexo"));
				vwe.setCoordinacion(rs.getString("coordinacion"));
				vwe.setUsuario(rs.getString("usuario"));
				
				listaEstudiantes.add(vwe);
				
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DT Estudiante: Error en listar Estudiante" + e.getMessage());
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
				System.err.println("DT Estudiantes: Error en cerrar conexión " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return listaEstudiantes;
	}
	public boolean guardarEst(Estudiante es)
	{
		boolean guardado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsEst(c);
			//AQUI INICIA EL GUARDAR
			rsEst.moveToInsertRow();
			rsEst.updateString("nombres", es.getNombres());
			rsEst.updateString("apellidos", es.getApellidos());
			rsEst.updateString("correoinstitucional", es.getCorreoInstitucional());
			rsEst.updateString("celular", es.getCelular());
			rsEst.updateString("condicion", es.getCondicion());
			rsEst.updateInt("sexo", es.getSexo());
			rsEst.updateString("carneuca", es.getCarneuca());
			rsEst.updateInt("idcoordinacion", es.getIdCoordinacion());
			rsEst.updateInt("idusuario", es.getIdUsuario());
			rsEst.updateDate("fecha_creacion", sqlDate);
			rsEst.updateInt("estado", 1);
			
			rsEst.insertRow();
			rsEst.moveToCurrentRow();
			guardado = true;
			//FIN DEL GUARDAR			
		} 
		catch (Exception e) 
		{
			System.err.println("DT Estudiante: Error al guardar estudiante " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsEst != null)
				{
					rsEst.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Estudiante: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return guardado;
	}
	
	public boolean modificarEst(Estudiante es)
	{
		boolean modificado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsEst(c);
			rsEst.beforeFirst();
			modificado=true;
					
			while(rsEst.next())
			{
				if(rsEst.getInt(1) == es.getIdEstudiante()) 
				{
					System.out.println("Id del Estudiante: " + es.getIdEstudiante());
					
					rsEst.updateString("nombres", es.getNombres());
					rsEst.updateString("apellidos", es.getApellidos());
					rsEst.updateString("correoinstitucional", es.getCorreoInstitucional());
					rsEst.updateString("celular", es.getCelular());
					rsEst.updateString("condicion", es.getCondicion());					
					rsEst.updateInt("sexo", es.getSexo());
					rsEst.updateString("carneuca", es.getCarneuca());
					rsEst.updateInt("idcoordinacion", es.getIdCoordinacion());
					rsEst.updateInt("idusuario", es.getIdUsuario());
					rsEst.updateDate("fecha_edicion", sqlDate);
					rsEst.updateInt("estado", 2);
					
					rsEst.updateRow();
					modificado = true;
					break;
				}
				
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("DT Estudiante: Error al modificar estudiante" + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsEst != null)
				{
					rsEst.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Estudiante: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return modificado;
	}
	
	public boolean eliminarEst(int idestudiante)
	{
		boolean eliminado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsEst(c);
			rsEst.beforeFirst();
			while(rsEst.next())
			{
				
				if(rsEst.getInt(1) == idestudiante) 
				{
					rsEst.updateDate("fecha_eliminacion", sqlDate);
					rsEst.updateInt("estado", 3);	
					rsEst.updateRow();
					
					eliminado = true;
					break;
				}
				
			}
			
		} 
		catch (SQLException e) 
		{
			System.err.println("DT Estudiante: Error al eliminar estudiante " + e.getMessage());
			e.printStackTrace();
			System.err.println(e.getSQLState());
		}
		finally 
		{
			try 
			{
				if(rsEst != null)
				{
					rsEst.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Estudiante: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return eliminado;
	}
	
	public Estudiante getEst(int idestudiante)
	{
		Estudiante es = new Estudiante();
		String sql = "Select * from public.estudiante where estado <> 3 and idestudiante = ?";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idestudiante);
			rs = ps.executeQuery();
			if(rs.next())
			{
				es.setIdEstudiante(rs.getInt("idestudiante"));
				es.setNombres(rs.getString("nombres"));
				es.setApellidos(rs.getString("apellidos"));
				es.setCorreoInstitucional(rs.getString("correoinstitucional"));
				es.setCelular(rs.getString("celular"));
				es.setCondicion(rs.getString("condicion"));	
				es.setCarneuca(rs.getString("carneuca"));
				es.setSexo(rs.getInt("sexo"));
				es.setIdCoordinacion(rs.getInt("idcoordinacion"));
				es.setIdUsuario(rs.getInt("idusuario"));
			}
		} 
		catch (SQLException e) 
		{
			System.err.println("DATOS: error getEst(): " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsEst != null)
				{
					rsEst.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Estudiante: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return es;
	}
	
	public Estudiante getEstudiante(int idusuario)
	{
		Estudiante vwe = new Estudiante();
		String sql = "Select * from public.estudiante where estado <> 3 and idusuario = ?";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idusuario);
			rs = ps.executeQuery();
			if(rs.next())
			{

				vwe.setIdEstudiante(rs.getInt("idestudiante"));
				vwe.setNombres(rs.getString("nombres"));
				vwe.setApellidos(rs.getString("apellidos"));
				vwe.setCorreoInstitucional(rs.getString("correoinstitucional"));
				vwe.setCelular(rs.getString("celular"));
				vwe.setCondicion(rs.getString("condicion"));	
				vwe.setCarneuca(rs.getString("carneuca"));
				vwe.setIdUsuario(rs.getInt("idusuario"));
			}
		} 
		catch (SQLException e) 
		{
			System.err.println("DATOS: error getEst(): " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsEst != null)
				{
					rsEst.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Estudiante: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return vwe;
	}
	
	public boolean verificarUsuarioExiste(int idusuario) {        
        boolean res = false;       
        String SQL = "SELECT idusuario FROM public.estudiante WHERE idusuario = ?";
        try{
        	c = PoolConexion.getConnection();
			ps = c.prepareStatement(SQL);
			ps.setInt(1, idusuario); 
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
