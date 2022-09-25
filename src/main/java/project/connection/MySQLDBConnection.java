package project.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Sergey Muzhzukhin
 * ¯\_(ツ)_/¯
 */
public class MySQLDBConnection extends DBConnection {

    final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    final String DB_URL = "jdbc:mysql://localhost:3306/my_db";
    final String USER = "bestuser";
    final String PASS = "bestuser";

    @Override
    public void connect() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC driver not found");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
