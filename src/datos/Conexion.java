package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion 
{
	private final String url = "jdbc:postgresql://localhost/Modulo_evaluacion";
    private final String user = "postgres";
    private final String password = "1234";
    
    public Connection connect()
    {
    	Connection conn = null;
        try 
        {
            conn = DriverManager.getConnection(url, user, password);
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
