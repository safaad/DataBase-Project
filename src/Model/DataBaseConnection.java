package Model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {

    public static String username = "admin" ;
    public static String password = "admin" ;
    public static String ID;

    public static Connection getConnection () {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBProject", username, password);
            System.out.println("Connected");
            return connection;
        }catch (Exception e){
            System.out.println("N");
            return null;
        }
    }

}
