package datos;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import entidades.Usuario;
import vistas.VW_usuario;
import vistas.VW_usuario_rol;

public class DTUsuario 
{
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsUsuario = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	//Método para llenar el resultset usuario
	public void llenarRsUsuario(Connection c)
	{
		String sql = "SELECT * FROM public.usuario where estado <> 3";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsUsuario = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT USUARIO: Error en listar usuarios " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<VW_usuario> listarUsuarios()
	{
		ArrayList<VW_usuario> listaUsuarios = new ArrayList<VW_usuario>();
		
		String sql = "SELECT * FROM public.vista_usuario";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				VW_usuario vwu = new VW_usuario();
				vwu.setIdUsuario(rs.getInt("idusuario"));
				vwu.setUsuario(rs.getString("usuario"));
				
				listaUsuarios.add(vwu);
				
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DT USUARIO: Error en listar usuarios " + e.getMessage());
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
				System.err.println("DT USUARIO: Error en listar usuarios " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return listaUsuarios;
	}
	
	public boolean guardarUsuario(Usuario u)
	{
		boolean guardado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsUsuario(c);
			//AQUI INICIA EL GUARDAR
			rsUsuario.moveToInsertRow();
			rsUsuario.updateString("usuario", u.getUsuario());
			rsUsuario.updateString("pwd", u.getPwd());
			rsUsuario.updateDate("fecha_creacion", sqlDate);
			rsUsuario.updateInt("estado", 1);
			
			rsUsuario.insertRow();
			rsUsuario.moveToCurrentRow();
			guardado = true;
			//FIN DEL GUARDAR
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTUSUARIO: Error al guardar usuario " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsUsuario != null)
				{
					rsUsuario.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTUSUARIO: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return guardado;
	}
	
	public boolean modificarUsuario(Usuario u)
	{
		boolean modificado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsUsuario(c);
			rsUsuario.beforeFirst();
			modificado = true;
			
				
			while(rsUsuario.next())
			{
				if(rsUsuario.getInt(1) == u.getIdUsuario()) 
				{
					System.out.println("Id del usuario: " + u.getIdUsuario());
					
					rsUsuario.updateString("usuario", u.getUsuario());
					rsUsuario.updateString("pwd", u.getPwd());
					rsUsuario.updateDate("fecha_edicion", sqlDate);
					rsUsuario.updateInt("estado", 2);
					
					rsUsuario.updateRow();
					modificado = true;
					break;
				}
				
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTUSUARIO: Error al modificar usuario " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsUsuario != null)
				{
					rsUsuario.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTUSUARIO: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return modificado;
	}
	
	public boolean eliminarUsuario(int idusuario)
	{
		boolean eliminado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarRsUsuario(c);
			rsUsuario.beforeFirst();
			while(rsUsuario.next())
			{
				
				if(rsUsuario.getInt(1) == idusuario) 
				{
					rsUsuario.updateDate("fecha_eliminacion", sqlDate);
					rsUsuario.updateInt("estado", 3);	
					rsUsuario.updateRow();
					
					eliminado = true;
					break;
				}
				
			}
			
		} 
		catch (SQLException e) 
		{
			System.err.println("DTUSUARIO: Error al eliminar usuario " + e.getMessage());
			e.printStackTrace();
			System.err.println(e.getSQLState());
		}
		finally 
		{
			try 
			{
				if(rsUsuario != null)
				{
					rsUsuario.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTUSUARIO: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return eliminado;
	}
	
	public Usuario getUsuario(int idusuario)
	{
		Usuario u = new Usuario();
		String sql = "Select * from public.usuario where estado <> 3 and idusuario = ?";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idusuario);
			rs = ps.executeQuery();
			if(rs.next())
			{
				u.setIdUsuario(idusuario);
				u.setUsuario(rs.getString("usuario"));
				u.setPwd(rs.getString("pwd"));
			}
		} 
		catch (SQLException e) 
		{
			System.err.println("DATOS: error getUsuario(): " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsUsuario != null)
				{
					rsUsuario.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTUSUARIO: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		
		return u;
	}
	
	public boolean dtverificarLogin(Usuario u)
	{
		boolean encontrado = false;

		String sql = "Select * from public.usuario where usuario = ? and pwd = ?";
		
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);	
			ps.setString(1, u.getUsuario());
			ps.setString(2, u.getPwd());
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				encontrado = true;				
			}else {
				encontrado = false;
			}
			
		} catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR Elementos del Banner "+ e.getMessage());
			e.printStackTrace();
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
		
		return encontrado;
	}
	
	
	  public int conseguirID(String usuario) 
	  { 
		 
	  int id=0;
	  
	  String sql = "Select * from public.usuario where usuario = ?";
	  
	  try { 
		  c = PoolConexion.getConnection(); 
		  ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	      ps.setString(1, usuario);  
	      rs = ps.executeQuery();
	  
	  if(rs.next()) 
	  { 
		  id= rs.getInt("idusuario"); 
		  System.out.println("ID:" + id);
		  }
	  
	  } catch (Exception e)
	  {
		  System.out.println("DATOS: ERROR EN LISTAR Elementos del Banner "+ e.getMessage()); e.printStackTrace(); 
	  } 
	  finally{ 
		  try { 
			  if(rs != null)
			  {
	  rs.close(); 
	  } 
			  if(ps != null)
			  { 
				  ps.close(); 
			  } 
			  if(c != null)
			  {
				  PoolConexion.cerrarConexion(c);
	          }
	  
	  } catch (SQLException e) { // TODO Auto-generated catch block
	  e.printStackTrace(); }
	  
	  }
	  
	  return id; 
	  }
	  
	  public ArrayList<VW_usuario_rol> listarUR(String nombre ){
			ArrayList<VW_usuario_rol> listaUR= new ArrayList<VW_usuario_rol>();
			try{
				c = PoolConexion.getConnection();
				ps = c.prepareStatement("Select * from public.vista_usuario_rol where usuario ='"+ nombre +"'", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				rs = ps.executeQuery();
				
				while(rs.next()){
					VW_usuario_rol vwu = new VW_usuario_rol();
					 
					vwu.setRol(rs.getString("rol"));
					
					listaUR.add(vwu);
				}
			}
			catch (Exception e){
				System.out.println("DATOS: ERROR EN LISTAR LA INFORMACION DEL USUARIO  "+ e.getMessage());
				e.printStackTrace();
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
			return listaUR;
		}
	 	
	//Método para encriptar con MD5
	public String md5(String input)
	{
		try 
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger no = new BigInteger(1, messageDigest);
			
			String hashtext = no.toString(16);
			while (hashtext.length() < 32) 
			{
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} 
		catch (NoSuchAlgorithmException e) 
		{
			throw new RuntimeException(e);
		}
	}
	
}
