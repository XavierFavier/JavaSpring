package com.example.demo.dao;

import com.example.demo.dao.Users;
import com.example.demo.dto.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JbdcDao {

    Connection conn = null;

    public JbdcDao() {
        try {
            // connect way #1
            String url1 = "jdbc:mysql://localhost:6603/Spring";
            String user = "root";
            String password = "helloworld";

            conn = DriverManager.getConnection(url1, user, password);
            if (conn != null) {
                System.out.println("Connected to the database");
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        }
    }

    private List<Users> userList = new ArrayList<>();
    public List<Users> getAllUsers() {
        List<Users> returnList = new ArrayList<>();

        String sql = "SELECT * FROM users";

        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            int count = 0;

            while (result.next()){
                Users user = new Users();
                user.setName(result.getString(2));
                user.setUuid(UUID.fromString(result.getString(1)).toString());
                returnList.add(user);
            }
        } catch(SQLException ex) {
            System.out.println("An error occurred.");
            ex.printStackTrace();
        }

        return returnList;
    }

    public Users getUserById(String id) {
        String sql = "SELECT * FROM users WHERE id=?";

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();

            int count = 0;

            while (result.next()) {
                Users user = new Users();
                user.setName(result.getString(2));
                user.setUuid(UUID.fromString(result.getString(1)).toString());
                return user;
            }
        } catch(SQLException ex) {
            System.out.println("An error occurred.");
            ex.printStackTrace();
        }

        return null;
    }
    public Users getUserByName(String name) {
        String sql = "SELECT * FROM users WHERE name=?";

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();

            int count = 0;

            while (result.next()) {
                Users user = new Users();
                user.setName(result.getString(2));
                user.setUuid(UUID.fromString(result.getString(1)).toString());
                return user;
            }
        } catch(SQLException ex) {
            System.out.println("An error occurred.");
            ex.printStackTrace();
        }

        return null;
    }


    public void addUser(String id, String name) {
        try {
            String sql = "INSERT INTO users (id, name) VALUES (?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, name);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch(SQLException ex) {
            System.out.println("sql error");
        }
    }

    public void updateUser(String id, String new_id, String new_name) {
        try {
            String sql = "UPDATE users SET id=?, name=? WHERE id=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, new_id);
            statement.setString(2, new_name);
            statement.setString(3, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }
        } catch(SQLException ex) {
            System.out.println("sql error");
        }
    }

    public void deleteUser(String id) {
        String sql = "DELETE FROM users WHERE id=?";

        try {
             PreparedStatement statement = conn.prepareStatement(sql);
             statement.setString(1, id);

             int rowsDeleted = statement.executeUpdate();
             if (rowsDeleted > 0) {
                 System.out.println("A user was deleted successfully!");
             }
        } catch(SQLException ex) {
            System.out.println("sql error");
        }
    }
}
