package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import entidades.UsuarioRol;
import vistas.VW_usuario_rol;


public class DTUsuarioRol {
	
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsUr = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	//Método para llenar el resultset usuario
	public void llenarRsUsuarioRol(Connection c)
	{
		String sql = "SELECT * FROM public.usuario_rol where estado <> 3";
		try 
		{
			//c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsUr = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT USUARIO ROL: Error en listar usuario rol " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<VW_usuario_rol> listarUR()
	{
		ArrayList<VW_usuario_rol> listaUR = new ArrayList<VW_usuario_rol>();
		
		String sql = "SELECT * FROM public.vista_usuario_rol";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				VW_usuario_rol vwu = new VW_usuario_rol();
				vwu.setId_usuario_rol(rs.getInt("id_usuario_rol"));
				vwu.setRol(rs.getString("rol"));
				vwu.setUsuario(rs.getString("usuario"));
				
				listaUR.add(vwu);
				
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DT USUARIO ROL: Error en listar usuario rol " + e.getMessage());
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
				System.err.println("DT USUARIO ROL: Error en listar usuario rol " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return listaUR;
	}
	
	public boolean guardarUsuarioRol(UsuarioRol ur)
	{
		boolean guardado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
				
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsUsuarioRol(c);
			//AQUI INICIA EL GUARDAR
			rsUr.moveToInsertRow();
			
			rsUr.updateInt("idusuario", ur.getIdUsuario());
			rsUr.updateInt("idrol", ur.getIdRol());
			rsUr.updateDate("fecha_creacion", sqlDate);
			rsUr.updateInt("estado", 1);
			rsUr.insertRow();
			rsUr.moveToCurrentRow();
			guardado = true;
			//FIN DEL GUARDAR
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTUSUARIOROL: Error al guardar usuario rol" + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsUr != null)
				{
					rsUr.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTUSUARIOROL: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return guardado;
	}
	
	public boolean eliminarUR(int idusuariorol)
	{
		boolean eliminado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsUsuarioRol(c);
			rsUr.beforeFirst();
			while(rsUr.next())
			{
				
				if(rsUr.getInt(1) == idusuariorol) 
				{
					rsUr.updateDate("fecha_eliminacion", sqlDate);
					rsUr.updateInt("estado", 3);	
					rsUr.updateRow();
					
					eliminado = true;
					break;
				}
				
			}
			
		} 
		catch (SQLException e) 
		{
			System.err.println("DTUsuarioROL: Error al eliminar rol " + e.getMessage());
			e.printStackTrace();
			System.err.println(e.getSQLState());
		}
		finally 
		{
			try 
			{
				if(rsUr != null)
				{
					rsUr.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTUsuarioROL: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}		
		return eliminado;
	}
	
	public boolean loginUsuario(VW_usuario_rol vwr) {
		boolean encontrado = false;
		
		PreparedStatement ps;
		String sql = ("Select * from public.vista_usuario_rol where usuario = ? and pwd = ? and idrol = ?");
		
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			  ps.setString(1, vwr.getUsuario()); 
			  ps.setString(2, vwr.getPwd());
			  ps.setInt(3, vwr.getIdrol());
			 
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				encontrado = true;
			}else {
				encontrado = false;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("DATOS: ERROR AL VERIFICAR EL LOGIN "+ e.getMessage());
			e.printStackTrace();
		}
		
		return encontrado;
	}
}
