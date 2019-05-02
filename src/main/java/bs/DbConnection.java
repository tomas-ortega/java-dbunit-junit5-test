package bs;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    private static final String CADENA_CONEXION = "jdbc:mysql://localhost:3306/test_tdd";
    private static final String USER_DB = "root";
    private static final String PASSWORD_DB = "RjUvuH&9w(>A";
    
    public static Connection getDbConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        
        Connection newConnection = null;
	
        newConnection = DriverManager.getConnection(CADENA_CONEXION,USER_DB,PASSWORD_DB);
        
        return newConnection;
    }
    
    public static void closeConnection(Connection connectionReference) {
        try {
            connectionReference.close();
        } catch(Exception ex) {
            
        }
    }
}
