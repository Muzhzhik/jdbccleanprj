package project;


import project.connection.DBConnection;
import project.connection.MySQLDBConnection;
import project.console.ConsoleController;


/**
 * @author Sergey Muzhzukhin
 * ¯\_(ツ)_/¯
 */
public class JDBCClean {

    public static void main(String[] args) {
        DBConnection dbConnection = new MySQLDBConnection();
        dbConnection.connect();
        new ConsoleController().showMainMenu();
    }
}
