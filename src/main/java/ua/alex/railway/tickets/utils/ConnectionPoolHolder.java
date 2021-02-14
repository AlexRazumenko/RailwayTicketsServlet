package ua.alex.railway.tickets.utils;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;

public class ConnectionPoolHolder {
    private static final String DB_URL =
            "jdbc:mysql://localhost:3306/tickets" +
                    "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";

    public static Connection getConnection() throws SQLException {
        MysqlDataSource source = new MysqlDataSource();
        source.setURL(DB_URL);
        source.setDatabaseName("tickets");
        source.setPassword(DB_PASSWORD);
        source.setUser(DB_USER);

        return source.getConnection();
    }

    public static Connection getConnectionByDriverManager() throws Exception {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement statement = conn.prepareStatement("select * from users");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("email"));
            }
            return conn;
        }
    }

    public static long getAndUpdateMaxId() {
        long maxId = -1;

        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            String getMaxIdQuery = "select max(next_val) from hibernate_sequence";
            System.out.println(maxId);

            try (Statement statement = connection.createStatement()) {
                ResultSet rs = statement.executeQuery(getMaxIdQuery);

                if (rs.next()) {
                    maxId = rs.getLong(1);
                } else return maxId;

                String updateMaxIdQuery = "UPDATE hibernate_sequence SET next_val=next_val+1";
                statement.executeUpdate(updateMaxIdQuery);
                connection.commit();
                connection.setAutoCommit(true);

            } catch (SQLException e) {
                connection.rollback();
                connection.setAutoCommit(true);
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxId;
    }
}
