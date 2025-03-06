package io.github.damianlebiedz.financemanager;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class DBConnectionTests {

    private static final Logger LOGGER = Logger.getLogger(DBConnectionTests.class.getName());

    @Test
    void testDBConnection() {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.getConnection();
        assertNotNull(connection, "Connection should not be null");
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Exception", e);
            fail("Exception during connection close: " + e.getMessage());
        }
    }

    @Test
    void testInitializeDatabase() {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.getConnection();
        assertNotNull(connection, "Connection should not be null");
        assertDoesNotThrow(() -> dbConnection.initializeDatabase(connection), "Should not throw an exception");
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL exception", e);
            fail("Exception during connection close: " + e.getMessage());
        }
    }

    @Test
    void testInsertData() {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.getConnection();
        assertNotNull(connection, "Connection should not be null");

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO DATA (NAME, CATEGORY, PRICE, DATE) VALUES ('Test Name', 'Test Category', 10.0, '2024-01-01')");
            connection.commit(); // Ensure data is written to the database
            statement.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL exception", e);
            fail("Exception during data insertion: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "SQL exception", e);
                fail("Exception during connection close: " + e.getMessage());
            }
        }
    }
}

