package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConfig {

    private static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/db_crm_java20";
    private static String USER_NAME = "crm_account";
    private static String PASSWORD = "crm@123";

    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(DRIVER_NAME);
            connection = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
        }catch (Exception e){
            System.out.println("Cannot connect to DB: " + e.getMessage());
        }

        return connection;
    }

}
