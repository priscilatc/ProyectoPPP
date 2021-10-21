package datos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vistas.VW_estinfo;
public class DTInfoEst {
	public static VW_estinfo consultarEst(String usuario){
		  VW_estinfo infoEst=null;
		  try{
		   Conexion c=new Conexion();
		   Connection con=c.connect();
		   Statement st=con.createStatement();
		   ResultSet rs=st.executeQuery("Select * from public.vista_info_est where usuario='"+usuario+"'");
		   while(rs.next()){
		    infoEst=new VW_estinfo(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), usuario);
		   }
		   rs.close();
		   st.close();
		  }catch(SQLException se){
		   se.printStackTrace();
		  }
		  return infoEst;
		 }

}
