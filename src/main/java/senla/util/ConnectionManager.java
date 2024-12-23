package senla.util;

import senla.exception.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ConnectionManager {

    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";

    private static final int POOL_SIZE = 15;
    private static final BlockingQueue<Connection> connectionPool = new ArrayBlockingQueue<>(POOL_SIZE);

    static {
        try {
            Class.forName("org.postgresql.Driver");
            for (int i = 0; i < POOL_SIZE; i++) {
                connectionPool.add(createConnection());
            }
        } catch (ClassNotFoundException e) {
            throw new ConnectionException("PostgreSQL JDBC Driver not found.", e);
        } catch (SQLException e) {
            throw new ConnectionException(ErrorMessages.CONNECTION_POOL_INIT_ERROR.getDescription(), e);
        }
    }

    private static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(
                PropertyUtil.getProperty(URL_KEY),
                PropertyUtil.getProperty(USERNAME_KEY),
                PropertyUtil.getProperty(PASSWORD_KEY)
        );
    }

    public static Connection getConnection() {
        try {
            return connectionPool.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ConnectionException(ErrorMessages.CONNECTION_ACQUIRE_ERROR.getDescription(), e);
        }
    }

    public static void releaseConnection(Connection connection) {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connectionPool.offer(connection);
                }
            } catch (SQLException e) {
                throw new ConnectionException(ErrorMessages.CONNECTION_STATE_ERROR.getDescription(), e);
            }
        }
    }

    private ConnectionManager() {}
}