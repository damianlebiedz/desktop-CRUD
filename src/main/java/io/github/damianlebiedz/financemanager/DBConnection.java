package io.github.damianlebiedz.financemanager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

public class DBConnection {
    private static final String url = "jdbc:h2:file:./src/main/resources/db/data";
    String username = "sa";
    String password = "123";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void initializeDatabase(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            InputStream is = getClass().getResourceAsStream("/db/init.sql");

            assert is != null;
            String sql = new BufferedReader(new InputStreamReader(is))
                    .lines().collect(Collectors.joining("\n"));
            stmt.execute(sql);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
