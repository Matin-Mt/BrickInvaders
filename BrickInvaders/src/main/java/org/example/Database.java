package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private final String url = "";
    private final String user = "";
    private final String password = "";

    private Connection connection;

    public Database() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Unable to connect...");
            System.out.println("Something went wrong!");
        }
    }
}
