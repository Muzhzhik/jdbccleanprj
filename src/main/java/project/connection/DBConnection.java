package project.connection;

import java.sql.Connection;

public abstract class DBConnection {

    protected static Connection connection = null;

    public abstract void connect();

    public static Connection getConnection() {
        return connection;
    }
}
