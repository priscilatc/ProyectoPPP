package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion 
{
	private static String db = "Modulo_evaluacion";
	private static String url = "jdbc:postgresql://165.98.12.158:5432/"+db;
	private static String user = "risw";
	private static String pass = "P0$GR3$2021*";
    
    public Connection connect()
    {
    	Connection conn = null;
        try 
        {
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }

        return conn;
    }
    
    public static void main(String[] args) 
    {
        Conexion app = new Conexion();
        app.connect();
    }
}
