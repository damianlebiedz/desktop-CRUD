package com.damianlebiedz.financemanager.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    String url = "jdbc:h2:~/data"; //Adres URL bazy danych H2 (w folderze data)
    String username = "sa";
    String password = "123";

    public Connection getConnection() { //Metoda do uzyskiwania połączenia z bazą danych
        try {
            return DriverManager.getConnection(url, username, password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
