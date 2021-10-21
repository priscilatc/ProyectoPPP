package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import entidades.TutorTecnico;
import vistas.VW_alumnosasignados_tutort;
import vistas.VW_est_tutor;
import vistas.VW_tutor_tecnico;

public class DTTutorTecnico {

	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsTutort = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	//Método para llenar el resultset tutor
	public void llenarRsTutort(Connection c)
	{
		String sql = "SELECT * FROM public.tutortecnico where estado <>3";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsTutort = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT Tutor Tecnico: Error en listar Tutor Tecnico " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<VW_tutor_tecnico> listarTutoresT()
	{
		ArrayList<VW_tutor_tecnico> listaTutorT = new ArrayList<VW_tutor_tecnico>();
		
		String sql = "SELECT * FROM public.vista_tutortecnico";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				VW_tutor_tecnico vwt = new VW_tutor_tecnico();
				vwt.setIdTutor(rs.getInt("idTutor"));
				vwt.setNombre(rs.getString("nombre"));
				vwt.setDireccion(rs.getString("direccion"));
				vwt.setCelular(rs.getString("celular"));
				vwt.setCargo(rs.getString("cargo"));
				vwt.setTrato(rs.getString("trato"));
				vwt.setCarneUca(rs.getString("carneUca"));
				vwt.setCedula(rs.getString("cedula"));
				vwt.setCorreo(rs.getString("correo"));
				vwt.setOrganizacion(rs.getString("organizacion"));
				vwt.setUsuario(rs.getString("usuario"));
				
				listaTutorT.add(vwt);
				
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DT Tutor Tecnico: Error en listar Tutor tecnico " + e.getMessage());
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
				System.err.println("DT Tutor tecnico: Error en listar tutor tecnico " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return listaTutorT;
	}
	
	public boolean guardarTutort(TutorTecnico t)
	{
		boolean guardado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsTutort(c);
			//AQUI INICIA EL GUARDAR
			rsTutort.moveToInsertRow();
			rsTutort.updateString("nombres", t.getNombres());
			rsTutort.updateString("apellidos", t.getApellidos());
			rsTutort.updateString("celular", t.getCelular());
			rsTutort.updateString("direccion", t.getDireccion());
			rsTutort.updateString("cargo", t.getCargo());
			rsTutort.updateString("trato", t.getTrato());
			rsTutort.updateString("correo", t.getCorreo());
			rsTutort.updateString("carneUca", t.getCarneUca());
			rsTutort.updateString("cedula", t.getCedula());	
			rsTutort.updateInt("idOrg", t.getIdOrg());
			rsTutort.updateInt("idUsuario", t.getIdUsuario());
			rsTutort.updateDate("fecha_creacion", sqlDate);
			rsTutort.updateInt("estado", 1);
			
			rsTutort.insertRow();
			rsTutort.moveToCurrentRow();
			guardado = true;
			//FIN DEL GUARDAR
			
		} 
		catch (Exception e) 
		{
			System.err.println("DT Tutor Tecnico: Error al guardar Tutor" + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsTutort != null)
				{
					rsTutort.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Tutor: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}	
		return guardado;
	}
	
	public boolean modificarTutor(TutorTecnico t)
	{
		boolean modificado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsTutort(c);
			rsTutort.beforeFirst();
			modificado=true;
					
			while(rsTutort.next())
			{
				if(rsTutort.getInt(1) == t.getIdTutor()) 
				{
					System.out.println("Id del tutor: " + t.getIdTutor());
					
					rsTutort.updateString("nombres", t.getNombres());
					rsTutort.updateString("apellidos", t.getApellidos());			
					rsTutort.updateString("celular", t.getCelular());
					rsTutort.updateString("direccion", t.getDireccion());
					rsTutort.updateString("cargo", t.getCargo());			
					rsTutort.updateString("trato", t.getTrato());
					rsTutort.updateString("correo", t.getCorreo());
					rsTutort.updateString("carneUca", t.getCarneUca());
					rsTutort.updateString("cedula", t.getCedula());
					rsTutort.updateInt("idOrg", t.getIdOrg());
					rsTutort.updateInt("idusuario", t.getIdUsuario());
					rsTutort.updateDate("fecha_edicion", sqlDate);
					rsTutort.updateInt("estado", 2);
					
					rsTutort.updateRow();
					modificado = true;
					break;
				}
				
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("DT Tutor: Error al modificar tutor " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsTutort != null)
				{
					rsTutort.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Tutor: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return modificado;
	}
	
	public boolean eliminarTutor(int idtutor)
	{
		boolean eliminado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsTutort(c);
			rsTutort.beforeFirst();
			while(rsTutort.next())
			{
				
				if(rsTutort.getInt(1) == idtutor) 
				{
					rsTutort.updateDate("fecha_eliminacion", sqlDate);
					rsTutort.updateInt("estado", 3);	
					rsTutort.updateRow();
					
					eliminado = true;
					break;
				}
				
			}
			
		} 
		catch (SQLException e) 
		{
			System.err.println("DT Tutor: Error al eliminar tutor " + e.getMessage());
			e.printStackTrace();
			System.err.println(e.getSQLState());
		}
		finally 
		{
			try 
			{
				if(rsTutort != null)
				{
					rsTutort.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Tutor: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return eliminado;
	}
	
	public TutorTecnico getTutor(int idtutor)
	{
		TutorTecnico t = new TutorTecnico();
		String sql = "Select * from public.tutortecnico where estado <> 3 and idtutor = ?";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idtutor);
			rs = ps.executeQuery();
			if(rs.next())
			{
				t.setIdTutor(rs.getInt("idTutor"));
				t.setNombres(rs.getString("nombres"));
				t.setApellidos(rs.getString("apellidos"));
				t.setDireccion(rs.getString("direccion"));
				t.setCelular(rs.getString("celular"));
				t.setCargo(rs.getString("cargo"));
				t.setTrato(rs.getString("trato"));
				t.setCarneUca(rs.getString("carneUca"));
				t.setCedula(rs.getString("cedula"));
				t.setCorreo(rs.getString("correo"));
				t.setIdOrg(rs.getInt("idOrg"));
				t.setIdUsuario(rs.getInt("idUsuario"));
			}
		} 
		catch (SQLException e) 
		{
			System.err.println("DATOS: error getTutor(): " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsTutort != null)
				{
					rsTutort.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTTutor: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return t;
	}
	
	public VW_est_tutor getTutorTecnico(int idusuario)
	{
		VW_est_tutor vwt = new VW_est_tutor();
		String sql = "Select * from public.vista_est_tutor where idusuario = ?";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idusuario);
			rs = ps.executeQuery();
			if(rs.next())
			{
				vwt.setNombre(rs.getString("nombre"));				
				vwt.setCargo(rs.getString("cargo"));
				vwt.setTrato(rs.getString("trato"));
				vwt.setCorreo(rs.getString("correo"));
				vwt.setOrganizacion(rs.getString("organizacion"));
			}
		} 
		catch (SQLException e) 
		{
			System.err.println("DATOS: error DTTutor(): " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsTutort != null)
				{
					rsTutort.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Tutor: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return vwt;		
	}
	
	public ArrayList<VW_alumnosasignados_tutort> getAlumnosAsignados(int idusuario) {
		
		ArrayList<VW_alumnosasignados_tutort> alumnosasignados = new ArrayList<VW_alumnosasignados_tutort>();
		String sql = "Select * from public.vista_alumnosasignados_tutort where idusuario = ?";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idusuario);
			rs = ps.executeQuery();
			while(rs.next())
			{
				VW_alumnosasignados_tutort vwa = new VW_alumnosasignados_tutort();
				vwa.setIdestudiante(rs.getInt("idestudiante"));
				vwa.setNombre(rs.getString("nombre"));
				
				alumnosasignados.add(vwa);
			}
			}
		catch (SQLException e) 
		{
			System.err.println("DATOS: error DT Tutor(): " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsTutort != null)
				{
					rsTutort.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Tutor: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return alumnosasignados;		
	}
	
	public VW_tutor_tecnico getPerfil(int idusuario)
	{
		VW_tutor_tecnico vwp = new VW_tutor_tecnico();
		String sql = "Select * from public.vista_tutortecnico where idusuario = ?";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idusuario);
			rs = ps.executeQuery();
			if(rs.next())
			{
				vwp.setNombre(rs.getString("nombre"));				
				vwp.setCargo(rs.getString("cargo"));
				vwp.setTrato(rs.getString("trato"));
				vwp.setCorreo(rs.getString("correo"));
				vwp.setOrganizacion(rs.getString("organizacion"));
				vwp.setIdusuario(rs.getInt("idusuario"));
			}
		} 
		catch (SQLException e) 
		{
			System.err.println("DATOS: error DTTutor(): " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsTutort != null)
				{
					rsTutort.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Tutor: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return vwp;		
	}
			
}
