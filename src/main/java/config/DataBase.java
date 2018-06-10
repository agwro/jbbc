package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class DataBase {

    //https://github.com/agwro
    //jdbc:mysql//localhost:3306/java_app

    private final static String URL = "jdbc:mysql//localhost:3306/java_app";
    private final static String USER = "root";
    private final static String PASSWORD = "root";

    private Connection connection;

    public DataBase()Z{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("brak drivera do bazy");
            System.exit(-1);
        }

    }

    public Connection getConnetion() throws SQLException {
        if (connection == null) {
            Connection connection = DriverManager
                    .getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
}
