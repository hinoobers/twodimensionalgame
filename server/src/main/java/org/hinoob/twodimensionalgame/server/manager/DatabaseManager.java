package org.hinoob.twodimensionalgame.server.manager;

import java.io.File;
import java.sql.*;
import java.util.Base64;

public class DatabaseManager {

    private Connection connection;

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public boolean doesUserExist(String username, String password) {
        try {
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, Base64.getEncoder().encodeToString(password.getBytes()));
            return statement.executeQuery().next();
        } catch(SQLException e){
            return false;
        }
    }
}
